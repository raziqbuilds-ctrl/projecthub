package com.projecthub.backend.repository;

import com.projecthub.backend.entity.TeamMember;
import com.projecthub.backend.entity.TeamMemberId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamMemberRepository
        extends JpaRepository<TeamMember, TeamMemberId> {

    List<TeamMember> findByTeamId(Long teamId);

    List<TeamMember> findByUserId(Long userId);

    boolean existsByTeamIdAndUserId(Long teamId, Long userId);

    long countByTeamId(Long teamId);
}
