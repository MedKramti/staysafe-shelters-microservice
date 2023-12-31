package com.kramti.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
    @NotNull(message = "Name is null")
    @Pattern(regexp = "[a-zA-z ]{3,64}")
    private String name;
    @Size(max = 256, message = "Description too long")
    private String description;
    private int capacity;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id", referencedColumnName = "id")
    private Location location;

    @Column(name = "added_by")
    private String addedBy;
    @Column(name = "added_date")
    private LocalDate addedDate;
    private Boolean approved;
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
