package fr.balijon.centrale.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    //relations

    @ManyToOne
    @JoinColumn(nullable = false)
    private Brand brand;

    @OneToMany(mappedBy = "model")
    private List<Listing> listings = new ArrayList<Listing>();



}