package com.learnsphere.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "achievements")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Achievement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;
    private String icon;
    private String requirement;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Boolean unlocked;
    private String unlockedDate;
}
