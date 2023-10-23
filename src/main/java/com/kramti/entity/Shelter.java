package com.kramti.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "shelters")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Shelter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private int capacity;
    @Embedded
    private Location location;

    @Column(name = "added_by")
    private String addedBy;
    @Column(name = "added_date")
    private LocalDate addedDate;
    private boolean approved;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Shelter shelter)) return false;
        return Objects.equals(id, shelter.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
