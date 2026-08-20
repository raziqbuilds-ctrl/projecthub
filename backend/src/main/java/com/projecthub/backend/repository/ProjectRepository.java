package com.projecthub.backend.repository;

import com.projecthub.backend.entity.Project;
import com.projecthub.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    List<Project> findByOwner(User owner);

    List<Project> findByStatus(Project.ProjectStatus status);

    List<Project> findByProjectType(Project.ProjectType projectType);
}
