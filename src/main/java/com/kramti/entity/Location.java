package com.kramti.entity;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Embeddable
public class Location {
    private Double lng;
    private Double lat;
    @Size(max = 128, message = "Street too long")
    private String street;
    @Size(max = 64, message = "City too long")
    private String city;
    @Size(max = 64, message = "State too long")
    private String state;
    @Size(max = 8, message = "Post code too long")
    private String postcode;

    @Size(max = 56, message = "Country name too long")
    private String country;
}
