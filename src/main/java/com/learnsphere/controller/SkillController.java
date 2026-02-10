package com.learnsphere.controller;
import com.learnsphere.dto.SkillResponse;
import com.learnsphere.service.DomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/skills")
@RequiredArgsConstructor
@CrossOrigin(origins = {"*", "*"})
public class SkillController {
private final DomainService domainService;
@GetMapping("/{domainId}")
    public ResponseEntity<List<SkillResponse>> getSkillsByDomain(@PathVariable Long domainId) {
        List<SkillResponse> skills = domainService.getSkillsByDomain(domainId);
        return ResponseEntity.ok(skills);
    }
}
