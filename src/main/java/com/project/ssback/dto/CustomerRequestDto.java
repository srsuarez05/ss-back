package com.project.ssback.dto;

import com.project.ssback.entities.Region;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@ToString
@Builder
public class CustomerRequestDto {
    @NotEmpty(message ="No puede estar vacio")
    @Size(min=1, max=30, message="El tamaño tiene que estar entre 1 y 30 caracteres")
    private String firstname;

    @NotEmpty(message ="No puede estar vacio")
    private String lastname;

    @NotEmpty(message ="No puede estar vacio")
    @Email(regexp = ".*@.*\\..*", message="No es una dirección de correo bien formada")
    private String email;

    @NotNull(message ="No puede estar vacio")
    private LocalDate dateOfBirth;

    private String photo;

    @NotNull(message ="No puede estar vacio")
    private Region region;

}
