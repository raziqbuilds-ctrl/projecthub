package com.projecthub.backend.entity;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ProjectSkillId implements Serializable {

    private Long projectId;
    private Long skillId;

    public ProjectSkillId() {
    }

    public ProjectSkillId(Long projectId, Long skillId) {
        this.projectId = projectId;
        this.skillId = skillId;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getSkillId() {
        return skillId;
    }

    public void setSkillId(Long skillId) {
        this.skillId = skillId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProjectSkillId)) return false;
        ProjectSkillId that = (ProjectSkillId) o;
        return Objects.equals(projectId, that.projectId)
                && Objects.equals(skillId, that.skillId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectId, skillId);
    }
}
