package com.movieflix.controller;

import com.movieflix.Service.MovieService;
import com.movieflix.entity.MovieEntity;
import com.movieflix.mapper.MovieMapper;
import com.movieflix.request.MovieRequest;
import com.movieflix.response.MovieResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

}
