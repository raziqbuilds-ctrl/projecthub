package com.projecthub.backend.repository;

import com.projecthub.backend.entity.ProjectSkill;
import com.projecthub.backend.entity.ProjectSkillId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectSkillRepository
        extends JpaRepository<ProjectSkill, ProjectSkillId> {

    List<ProjectSkill> findByProjectId(Long projectId);

    List<ProjectSkill> findBySkillId(Long skillId);

    boolean existsByProjectIdAndSkillId(Long projectId, Long skillId);
}
