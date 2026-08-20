package com.projecthub.backend.repository;

import com.projecthub.backend.entity.Application;
import com.projecthub.backend.entity.Project;
import com.projecthub.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    List<Application> findByProject(Project project);

    List<Application> findByStudent(User student);

    List<Application> findByStatus(Application.ApplicationStatus status);

    boolean existsByProjectAndStudent(Project project, User student);
}
