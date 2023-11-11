package com.kramti.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "locations")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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

    @OneToOne(mappedBy = "location")
    @JsonIgnore
    private Shelter shelter;
}
