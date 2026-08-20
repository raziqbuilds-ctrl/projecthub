package com.projecthub.backend.dto.profile;

public class ProfileResponse {

    private Long userId;
    private String email;
    private String role;

    private String name;
    private String bio;
    private String college;
    private String course;
    private String academicYear;
    private String githubUrl;
    private String linkedinUrl;
    private String profileImageUrl;

    public ProfileResponse() {
    }

    public ProfileResponse(
            Long userId,
            String email,
            String role,
            String name,
            String bio,
            String college,
            String course,
            String academicYear,
            String githubUrl,
            String linkedinUrl,
            String profileImageUrl
    ) {
        this.userId = userId;
        this.email = email;
        this.role = role;
        this.name = name;
        this.bio = bio;
        this.college = college;
        this.course = course;
        this.academicYear = academicYear;
        this.githubUrl = githubUrl;
        this.linkedinUrl = linkedinUrl;
        this.profileImageUrl = profileImageUrl;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(String academicYear) {
        this.academicYear = academicYear;
    }

    public String getGithubUrl() {
        return githubUrl;
    }

    public void setGithubUrl(String githubUrl) {
        this.githubUrl = githubUrl;
    }

    public String getLinkedinUrl() {
        return linkedinUrl;
    }

    public void setLinkedinUrl(String linkedinUrl) {
        this.linkedinUrl = linkedinUrl;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }
}
