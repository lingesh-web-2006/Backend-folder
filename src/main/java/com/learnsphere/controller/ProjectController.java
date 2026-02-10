package com.learnsphere.controller;
import com.learnsphere.dto.ProjectResponse;
import com.learnsphere.dto.WorkflowResponse;
import com.learnsphere.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/projects")
@RequiredArgsConstructor
@CrossOrigin(origins = {"*", "*"})
public class ProjectController {
private final ProjectService projectService;
 @GetMapping("/domain/{domainId}")
    public ResponseEntity<List<ProjectResponse>> getProjectsByDomain(@PathVariable Long domainId) {
        List<ProjectResponse> projects = projectService.getProjectsByDomain(domainId);
        return ResponseEntity.ok(projects);
    }
@GetMapping("/{id}")
    public ResponseEntity<ProjectResponse> getProjectById(@PathVariable Long id) {
        ProjectResponse project = projectService.getProjectById(id);
        return ResponseEntity.ok(project);
    }
@GetMapping("/{projectId}/workflow")
    public ResponseEntity<WorkflowResponse> getProjectWorkflow(@PathVariable Long projectId) {
        WorkflowResponse workflow = projectService.getProjectWorkflow(projectId);
        return ResponseEntity.ok(workflow);
    }
}
