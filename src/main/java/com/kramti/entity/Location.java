package com.kramti.entity;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Embeddable
public class Location {
    private Double log;
    private Double lat;
    @Size(max = 56, message = "Country name too long")
    private String country;
}
