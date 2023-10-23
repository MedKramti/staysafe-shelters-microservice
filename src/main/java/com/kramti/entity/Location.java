package com.kramti.entity;

import jakarta.persistence.Embeddable;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Embeddable
public class Location {
    private String log;
    private String lat;
    private String country;
}
