package com.project.ssback.entities;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@ToString
@Builder
@Entity
@Table(name="customers")
public class Customer {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String firstname;

    @Column(nullable=false)
    private String lastname;

    @Column(nullable=false, unique=true)
    private String email;

    @Column(name="date_of_birth", nullable=false)
    private LocalDate dateOfBirth;

    @CreationTimestamp
    @Column(name="create_at")
    private LocalDateTime createAt;

    private String photo;

}
