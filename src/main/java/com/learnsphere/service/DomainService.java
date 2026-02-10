package com.learnsphere.service;

import com.learnsphere.dto.DomainResponse;
import com.learnsphere.dto.SkillResponse;
import com.learnsphere.entity.Domain;
import com.learnsphere.entity.Skill;
import com.learnsphere.repository.DomainRepository;
import com.learnsphere.repository.SkillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DomainService {

    private final DomainRepository domainRepository;
    private final SkillRepository skillRepository;

    public List<DomainResponse> getAllDomains() {
        return domainRepository.findAll().stream()
                .map(this::mapToDomainResponse)
                .collect(Collectors.toList());
    }

    public DomainResponse getDomainById(Long id) {
        Domain domain = domainRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Domain not found"));
        return mapToDomainResponse(domain);
    }

    public List<SkillResponse> getSkillsByDomain(Long domainId) {
        return skillRepository.findByDomainId(domainId).stream()
                .map(this::mapToSkillResponse)
                .collect(Collectors.toList());
    }

    private DomainResponse mapToDomainResponse(Domain domain) {
        List<SkillResponse> skills = domain.getSkills() != null ?
                domain.getSkills().stream()
                        .map(this::mapToSkillResponse)
                        .collect(Collectors.toList()) :
                List.of();

        return DomainResponse.builder()
                .id(domain.getId())
                .name(domain.getName())
                .description(domain.getDescription())
                .icon(domain.getIcon())
                .difficulty(domain.getDifficulty())
                .skillCount(domain.getSkillCount())
                .topSkills(domain.getTopSkills())
                .skills(skills)
                .build();
    }

    private SkillResponse mapToSkillResponse(Skill skill) {
        return SkillResponse.builder()
                .id(skill.getId())
                .name(skill.getName())
                .description(skill.getDescription())
                .level(skill.getLevel())
                .importance(skill.getImportance())
                .build();
    }
}
