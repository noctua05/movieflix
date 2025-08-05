package com.movieflix.entity;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jdk.jfr.Name;
import lombok.*;


@Builder
@AllArgsConstructor //Pois o builder precisa de um construtor para todos os campos
@NoArgsConstructor  // Exigido pleo Spring
@Getter
@Setter
@Entity
@Table(name = "streaming")

public class StreamingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100,nullable = false)
    private String name;
}
