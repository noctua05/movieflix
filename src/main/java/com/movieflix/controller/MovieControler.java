package com.movieflix.controller;

import com.movieflix.Service.MovieService;
import com.movieflix.entity.MovieEntity;
import com.movieflix.mapper.MovieMapper;
import com.movieflix.request.MovieRequest;
import com.movieflix.response.MovieResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movieflix/movie")
@RequiredArgsConstructor
public class MovieControler {

    @Autowired
    private final MovieService movieService;

    @PostMapping
    public ResponseEntity<MovieResponse> saveMovie (@RequestBody MovieRequest request){
        MovieEntity savedMovie = movieService.save(MovieMapper.toMovieEntity(request));
        return ResponseEntity.ok(MovieMapper.toMovieResponse(savedMovie));
    }

    @GetMapping
    public ResponseEntity<List<MovieResponse>> getAllMovie (){
        List<MovieResponse> listMovie = movieService.findAll().stream()
                .map(movie -> MovieMapper.toMovieResponse(movie))
                .toList();
        return ResponseEntity.ok(listMovie);
    }

    @GetMapping("{id}")
    public ResponseEntity<MovieResponse> findMovie (@PathVariable Long id){
        return movieService.findById(id)
                .map(movie ->ResponseEntity.ok(MovieMapper.toMovieResponse(movie)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("{id}")
    public ResponseEntity<MovieResponse> update (@PathVariable Long id, @RequestBody MovieRequest request){
        return movieService.update(id,MovieMapper.toMovieEntity(request))
                .map(movie->ResponseEntity.ok(MovieMapper.toMovieResponse(movie)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public ResponseEntity<List<MovieResponse>> findMoviesByCategory (@RequestParam Long id){
        List<MovieResponse> movies = movieService.findByCategory(id).stream()
                .map(MovieMapper::toMovieResponse)
                .toList();
        return ResponseEntity.ok(movies);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie (@PathVariable Long id){
        Optional<MovieEntity> optMovie = movieService.findById(id);

        if(optMovie.isPresent()){
            movieService.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
