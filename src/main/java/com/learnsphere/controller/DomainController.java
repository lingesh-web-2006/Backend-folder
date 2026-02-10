package com.learnsphere.controller;
import com.learnsphere.dto.DomainResponse;
import com.learnsphere.service.DomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/domains")
@RequiredArgsConstructor
@CrossOrigin(origins = {"*", "*"})
public class DomainController {
private final DomainService domainService;
@GetMapping
    public ResponseEntity<List<DomainResponse>> getAllDomains() {
        List<DomainResponse> domains = domainService.getAllDomains();
        return ResponseEntity.ok(domains);
    }
 @GetMapping("/{id}")
    public ResponseEntity<DomainResponse> getDomainById(@PathVariable Long id) {
        DomainResponse domain = domainService.getDomainById(id);
        return ResponseEntity.ok(domain);
    }
}
