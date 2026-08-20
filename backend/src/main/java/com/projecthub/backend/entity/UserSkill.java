package com.projecthub.backend.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_skills")
public class UserSkill {

    @EmbeddedId
    private UserSkillId id;

    @ManyToOne(optional = false)
    @MapsId("userId")
    @JoinColumn(
            name = "user_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_user_skill_user")
    )
    private User user;

    @ManyToOne(optional = false)
    @MapsId("skillId")
    @JoinColumn(
            name = "skill_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_user_skill_skill")
    )
    private Skill skill;
@CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public UserSkill() {
    }

    public UserSkillId getId() {
        return id;
    }

    public void setId(UserSkillId id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
