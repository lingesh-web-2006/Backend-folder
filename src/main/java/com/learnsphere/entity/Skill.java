package com.learnsphere.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "skills")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;
    private String level;
    private Integer importance;

    @ManyToOne
    @JoinColumn(name = "domain_id", nullable = false)
    private Domain domain;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userProgress;
}
