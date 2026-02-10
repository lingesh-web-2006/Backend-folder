package com.learnsphere.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "projects")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;
    private String icon;
    private String difficulty;
    private Integer estimatedHours;

    @Column(columnDefinition = "TEXT")
    private String starterHtml;

    @Column(columnDefinition = "TEXT")
    private String starterCss;

    @Column(columnDefinition = "TEXT")
    private String starterJs;

    @ManyToOne
    @JoinColumn(name = "domain_id", nullable = false)
    private Domain domain;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<WorkflowStep> workflowSteps;

    @ElementCollection
    @CollectionTable(name = "project_resources", joinColumns = @JoinColumn(name = "project_id"))
    private List<String> resources;
}
