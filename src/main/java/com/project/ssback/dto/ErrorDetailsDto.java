package com.project.ssback.dto;

import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
public class ErrorDetailsDto {
    private LocalDateTime timeStamp;
    private String message;
    private String details;
}
