package com.learnsphere.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "domains")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Domain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;
    private String icon;
    private String difficulty;

    @Column(name = "skill_count")
    private Integer skillCount;

    @ElementCollection
    @CollectionTable(name = "domain_top_skills", joinColumns = @JoinColumn(name = "domain_id"))
    @Column(name = "skill")
    private List<String> topSkills;

    @OneToMany(mappedBy = "domain", cascade = CascadeType.ALL)
    private List<Skill> skills;

    @OneToMany(mappedBy = "domain", cascade = CascadeType.ALL)
    private List<Project> projects;

    @OneToMany(mappedBy = "domain", cascade = CascadeType.ALL)
    private List<Quiz> quizzes;
}
