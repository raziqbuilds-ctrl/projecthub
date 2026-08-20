package com.projecthub.backend.repository;

import com.projecthub.backend.entity.Milestone;
import com.projecthub.backend.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MilestoneRepository extends JpaRepository<Milestone, Long> {

    List<Milestone> findByProject(Project project);

    List<Milestone> findByStatus(Milestone.MilestoneStatus status);
}
