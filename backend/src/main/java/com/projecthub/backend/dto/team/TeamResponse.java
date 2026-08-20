package com.projecthub.backend.dto.team;

import java.util.List;

public class TeamResponse {

    private Long teamId;
    private Long projectId;
    private String projectTitle;
    private Integer maxTeamSize;
    private Integer currentSize;
    private List<TeamMemberResponse> members;

    public TeamResponse() {
    }

    public TeamResponse(
            Long teamId,
            Long projectId,
            String projectTitle,
            Integer maxTeamSize,
            Integer currentSize,
            List<TeamMemberResponse> members
    ) {
        this.teamId = teamId;
        this.projectId = projectId;
        this.projectTitle = projectTitle;
        this.maxTeamSize = maxTeamSize;
        this.currentSize = currentSize;
        this.members = members;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getProjectTitle() {
        return projectTitle;
    }

    public void setProjectTitle(String projectTitle) {
        this.projectTitle = projectTitle;
    }

    public Integer getMaxTeamSize() {
        return maxTeamSize;
    }

    public void setMaxTeamSize(Integer maxTeamSize) {
        this.maxTeamSize = maxTeamSize;
    }

    public Integer getCurrentSize() {
        return currentSize;
    }

    public void setCurrentSize(Integer currentSize) {
        this.currentSize = currentSize;
    }

    public List<TeamMemberResponse> getMembers() {
        return members;
    }

    public void setMembers(List<TeamMemberResponse> members) {
        this.members = members;
    }
}
