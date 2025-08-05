package com.movieflix.mapper;

import com.movieflix.entity.StreamingEntity;
import com.movieflix.request.StreamingRequest;
import com.movieflix.response.StreamingResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class StreamingMapper {

    public static StreamingEntity toStreamingEntity (StreamingRequest request){
        return StreamingEntity
                .builder()
                .name(request.name())
                .build();
    }

    public static StreamingResponse toStreamingResponse (StreamingEntity entity){
        return StreamingResponse
                .builder()
                .name(entity.getName())
                .id(entity.getId())
                .build();
    }

}
