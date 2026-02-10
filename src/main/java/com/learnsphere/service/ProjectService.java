package com.learnsphere.service;

import com.learnsphere.dto.ProjectResponse;
import com.learnsphere.dto.WorkflowResponse;
import com.learnsphere.dto.WorkflowStepResponse;
import com.learnsphere.entity.Project;
import com.learnsphere.entity.WorkflowStep;
import com.learnsphere.repository.ProjectRepository;
import com.learnsphere.repository.WorkflowStepRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final WorkflowStepRepository workflowStepRepository;

    public List<ProjectResponse> getProjectsByDomain(Long domainId) {
        return projectRepository.findByDomainId(domainId).stream()
                .map(this::mapToProjectResponse)
                .collect(Collectors.toList());
    }

    public ProjectResponse getProjectById(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));
        return mapToProjectResponse(project);
    }

    public WorkflowResponse getProjectWorkflow(Long projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        List<WorkflowStep> steps = workflowStepRepository.findByProjectIdOrderByStepOrder(projectId);

        return WorkflowResponse.builder()
                .projectId(project.getId())
                .projectName(project.getName())
                .steps(steps.stream()
                        .map(step -> WorkflowStepResponse.builder()
                                .id(step.getId())
                                .name(step.getName())
                                .description(step.getDescription())
                                .stepOrder(step.getStepOrder())
                                .completed(step.getCompleted())
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }

    private ProjectResponse mapToProjectResponse(Project project) {
        return ProjectResponse.builder()
                .id(project.getId())
                .name(project.getName())
                .description(project.getDescription())
                .icon(project.getIcon())
                .difficulty(project.getDifficulty())
                .estimatedHours(project.getEstimatedHours())
                .starterHtml(project.getStarterHtml())
                .starterCss(project.getStarterCss())
                .starterJs(project.getStarterJs())
                .resources(project.getResources())
                .build();
    }
}
