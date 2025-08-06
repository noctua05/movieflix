package com.movieflix.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.movieflix.entity.CategoryEntity;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record MovieRequest(
        String title,
        String description,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="dd/MM/yyyy")
        @Schema(type = "string", pattern = "dd/MM/yyyy", example = "dd/MM/yyyy")
        LocalDate releaseDate,
        Double rating,
        List<Long> streamings,
        List<Long> categories
        ) { }
