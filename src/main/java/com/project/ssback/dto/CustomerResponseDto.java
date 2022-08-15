package com.project.ssback.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.ssback.entities.Region;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@ToString
@Builder
public class CustomerResponseDto {
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private LocalDate dateOfBirth;
    private String photo;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Region region;
}
