package fr.balijon.centrale.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class Listing {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String uuid;

    @Column(nullable = false)
    private Long price;

    @Column(nullable = false)
    private Long mileage;

    @Column(nullable = false)
    private LocalDateTime producedAt;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false, columnDefinition = "text")
    private String description;

    @Column(nullable = false)
    private String title;

    //relations

    @OneToMany(mappedBy = "listing")
    private List<Favorite> favorites;

    @OneToMany(mappedBy = "listing")
    private List<Image> images;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User owner;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Address address;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Model model;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Fuel fuel;
}