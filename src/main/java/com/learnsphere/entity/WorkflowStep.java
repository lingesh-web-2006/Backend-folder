package com.learnsphere.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "workflow_steps")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkflowStep {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;
    private Integer stepOrder;
    private Boolean completed;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;
}
