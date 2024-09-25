package fr.balijon.centrale.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String uuid;

    @Column(nullable = false, columnDefinition = "text")
    private String path;


    //relations

    @ManyToOne
    @JoinColumn(nullable = false)
    private Listing listing;


}