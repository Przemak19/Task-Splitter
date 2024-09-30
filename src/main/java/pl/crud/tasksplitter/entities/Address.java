package pl.crud.tasksplitter.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String street;
    private String number;
    private String city;
    @Column(name = "postal_code")
    private String postalCode;
    private String country;

    @Column(name = "created_at")
    private final LocalDateTime createdAt = LocalDateTime.now();
}