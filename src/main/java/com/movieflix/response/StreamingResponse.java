package com.movieflix.response;

import lombok.Builder;

@Builder
public record StreamingResponse(String name,Long id ) {
}
