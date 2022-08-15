package com.project.ssback.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@ToString
@Builder
public class RegionRequestDto {
    @NotEmpty(message ="No puede estar vacio")
    @Size(min=1, max=30, message="El tamaño tiene que estar entre 1 y 30 caracteres")
    private String name;
}
