package com.movieflix.controller;


import com.movieflix.Service.StreamingService;
import com.movieflix.entity.StreamingEntity;
import com.movieflix.mapper.StreamingMapper;
import com.movieflix.request.StreamingRequest;
import com.movieflix.response.StreamingResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movieflix/streaming")
@RequiredArgsConstructor
public class StreamingController {

    @Autowired
    private StreamingService streamingService;

    @GetMapping()
    public ResponseEntity<List<StreamingResponse>> getAllStreaming(){
        List<StreamingResponse> streamings = streamingService.findAll()
                .stream()
                .map(StreamingMapper :: toStreamingResponse)
                .toList();

        return ResponseEntity.ok(streamings);
    }

    @PostMapping()
    public ResponseEntity<StreamingResponse> saveStreaming(@RequestBody StreamingRequest request){
        StreamingEntity entity = StreamingMapper.toStreamingEntity(request);
        StreamingEntity savedEntity = streamingService.saveEntity(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(StreamingMapper.toStreamingResponse(savedEntity));


    }

    @GetMapping("{id}")
    public ResponseEntity<StreamingResponse> findStreaming(@PathVariable Long id){
       return streamingService.findById(id)
               .map(entity -> ResponseEntity.ok(StreamingMapper.toStreamingResponse(entity)))
               .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteStreaming(@PathVariable Long id){
            streamingService.deleteId(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
