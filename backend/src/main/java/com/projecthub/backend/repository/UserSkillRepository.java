package com.projecthub.backend.repository;

import com.projecthub.backend.entity.UserSkill;
import com.projecthub.backend.entity.UserSkillId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserSkillRepository extends JpaRepository<UserSkill, UserSkillId> {

    List<UserSkill> findByUserId(Long userId);

    List<UserSkill> findBySkillId(Long skillId);

    boolean existsByUserIdAndSkillId(Long userId, Long skillId);
}
