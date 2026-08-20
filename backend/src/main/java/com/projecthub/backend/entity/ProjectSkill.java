package com.projecthub.backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "project_skills")
public class ProjectSkill {

    @EmbeddedId
    private ProjectSkillId id;

    @ManyToOne(optional = false)
    @MapsId("projectId")
    @JoinColumn(
            name = "project_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_project_skill_project")
    )
    private Project project;

    @ManyToOne(optional = false)
    @MapsId("skillId")
    @JoinColumn(
            name = "skill_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_project_skill_skill")
    )
    private Skill skill;

    public ProjectSkill() {
    }

    public ProjectSkillId getId() {
        return id;
    }

    public void setId(ProjectSkillId id) {
        this.id = id;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }
}
