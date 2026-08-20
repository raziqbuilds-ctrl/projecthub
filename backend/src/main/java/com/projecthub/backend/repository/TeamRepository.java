package com.projecthub.backend.repository;

import com.projecthub.backend.entity.Project;
import com.projecthub.backend.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team, Long> {

    Optional<Team> findByProject(Project project);

    boolean existsByProject(Project project);
}
