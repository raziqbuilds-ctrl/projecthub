package com.projecthub.backend.repository;

import com.projecthub.backend.entity.Milestone;
import com.projecthub.backend.entity.Project;
import com.projecthub.backend.entity.Task;
import com.projecthub.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByProject(Project project);

    List<Task> findByMilestone(Milestone milestone);

    List<Task> findByAssignedTo(User assignedTo);

    List<Task> findByStatus(Task.TaskStatus status);

    List<Task> findByPriority(Task.TaskPriority priority);
}
