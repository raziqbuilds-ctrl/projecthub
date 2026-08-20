package com.projecthub.backend.dto.team;

import com.projecthub.backend.entity.TeamMember;

import java.time.LocalDateTime;

public class TeamMemberResponse {

    private Long userId;
    private String name;
    private TeamMember.TeamRole role;
    private LocalDateTime joinedAt;

    public TeamMemberResponse() {
    }

    public TeamMemberResponse(
            Long userId,
            String name,
            TeamMember.TeamRole role,
            LocalDateTime joinedAt
    ) {
        this.userId = userId;
        this.name = name;
        this.role = role;
        this.joinedAt = joinedAt;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TeamMember.TeamRole getRole() {
        return role;
    }

    public void setRole(TeamMember.TeamRole role) {
        this.role = role;
    }

    public LocalDateTime getJoinedAt() {
        return joinedAt;
    }

    public void setJoinedAt(LocalDateTime joinedAt) {
        this.joinedAt = joinedAt;
    }
}
