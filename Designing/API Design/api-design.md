# ProjectHub API Design

## 1. Authentication

### 1.1 Register User

**Endpoint**

POST /api/auth/register

**Authentication**

Not required.

**Request**

```json
{
  "email": "student@example.com",
  "password": "MyStrongPassword123"
}

### 1.2 Login User

**Endpoint**

POST /api/auth/login

**Authentication**

Not required.

**Request**

```json
{
  "email": "student@example.com",
  "password": "MyStrongPassword123"
}

## 2. Users / Profiles

### 2.1 Get User Profile

**Endpoint**

GET /api/users/{userId}

**Authentication**

Required.

**Success Response**

HTTP 200 OK

```json
{
  "id": 3,
  "email": "student@example.com",
  "role": "STUDENT",
  "profile": {
    "name": "Rahul",
    "bio": "BCA student interested in backend development.",
    "college": "ABC College",
    "course": "BCA",
    "academicYear": "2",
    "githubUrl": "https://github.com/example",
    "linkedinUrl": "https://linkedin.com/in/example",
    "profileImageUrl": null
  }
}


### One important point

The `PUT` request contains **profile fields only**. It does not contain:

```text
id
email
role
password

## 3.1 Get Available Skills

**Endpoint**

GET /api/skills

**Authentication**

Required.

**Purpose**

Get the list of skills available on ProjectHub.

**Success Response**

HTTP 200 OK

```json
[
  {
    "id": 1,
    "name": "Java"
  },
  {
    "id": 2,
    "name": "Spring Boot"
  },
  {
    "id": 3,
    "name": "Docker"
  }
]

## 3.2 Add Skill to User

**Endpoint**

POST /api/users/{userId}/skills/{skillId}

**Authentication**

The `{userId}` must match the ID of the authenticated user.

A user cannot add a skill to another user's profile.

**Purpose**

Add an existing skill to a user's profile.

**Request Body**

No request body is required.

The `userId` and `skillId` are provided in the URL.

**Example**

POST /api/users/3/skills/1

This means:

- User `3` wants to add skill `1`.
- Skill `1` might be `Java`.

**Success Response**

HTTP 201 Created

```json
{
  "userId": 3,
  "skill": {
    "id": 1,
    "name": "Java"
  }
}

## 3.3 Remove Skill from User

**Endpoint**

DELETE /api/users/{userId}/skills/{skillId}

**Authentication**

Required.

**Purpose**

Remove an existing skill from a user's profile.

**Request Body**

No request body is required.

The `userId` and `skillId` are provided in the URL.

**Example**

DELETE /api/users/3/skills/1

This means:

- User `3` wants to remove skill `1`.
- Skill `1` might be `Java`.

**Success Response**

HTTP 204 No Content

No response body is returned.

**Errors**

404 Not Found:

User, skill, or user-skill relationship does not exist.

403 Forbidden:

The authenticated user is not allowed to modify this user's skills.

**Database Operation**

Delete the corresponding row from the `user_skills` table.

Example:

Before:

```text
user_skills

user_id | skill_id
--------+---------
3       | 1
3       | 2

## 4.1 Get All Projects

**Endpoint**

GET /api/projects

**Authentication**

Not required.

**Purpose**

Retrieve projects that are available on ProjectHub.

**Query Parameters**

Optional:

- `status` - Filter projects by status.
- `projectType` - Filter projects by project type.
- `skillId` - Filter projects requiring a specific skill.
- `page` - Page number.
- `size` - Number of projects per page.

**Example**

GET /api/projects?status=RECRUITING&page=0&size=10

**Success Response**

HTTP 200 OK

```json
{
  "content": [
    {
      "id": 1,
      "title": "Smart Campus Bus Tracker",
      "description": "A platform for tracking college buses.",
      "projectType": "COLLEGE_PROJECT",
      "status": "RECRUITING",
      "maxTeamSize": 4,
      "owner": {
        "id": 3,
        "name": "Rahul"
      },
      "skills": [
        {
          "id": 1,
          "name": "Java"
        },
        {
          "id": 2,
          "name": "Spring Boot"
        }
      ]
    }
  ],
  "page": 0,
  "size": 10,
  "totalElements": 1,
  "totalPages": 1
}

## 4.2 Get Project Details

**Endpoint**

GET /api/projects/{projectId}

**Authentication**

Not required.

**Purpose**

Retrieve complete information about a specific project.

**Example**

GET /api/projects/1

**Success Response**

HTTP 200 OK

```json
{
  "id": 1,
  "title": "Smart Campus Bus Tracker",
  "description": "A platform for tracking college buses.",
  "projectType": "COLLEGE_PROJECT",
  "status": "RECRUITING",
  "maxTeamSize": 4,
  "owner": {
    "id": 3,
    "name": "Rahul"
  },
  "skills": [
    {
      "id": 1,
      "name": "Java"
    },
    {
      "id": 2,
      "name": "Spring Boot"
    },
    {
      "id": 3,
      "name": "Docker"
    }
  ],
  "team": {
    "currentSize": 2
  }
}

## 4.3 Create Project

**Endpoint**

POST /api/projects

**Authentication**

Required.

Only authenticated students can create projects.

**Purpose**

Create a new project on ProjectHub.

**Request**

```json
{
  "title": "Smart Campus Bus Tracker",
  "description": "A platform for tracking college buses.",
  "projectType": "COLLEGE_PROJECT",
  "maxTeamSize": 4,
  "skillIds": [1, 2, 3]
}

## 4.4 Update Project

**Endpoint**

PUT /api/projects/{projectId}

**Authentication**

Required.

Only the project owner can update the project.

**Purpose**

Update the information of an existing project.

**Request**

```json
{
  "title": "Smart Campus Bus Tracker - Updated",
  "description": "An improved platform for tracking college buses.",
  "projectType": "COLLEGE_PROJECT",
  "maxTeamSize": 5
}

## 4.5 Delete Project

**Endpoint**

DELETE /api/projects/{projectId}

**Authentication**

Required.

Only the project owner can delete the project.

**Purpose**

Delete an existing project and its associated project data.

**Example**

DELETE /api/projects/1

**Success Response**

HTTP 204 No Content

No response body is returned.

**Errors**

401 Unauthorized:

User is not authenticated.

403 Forbidden:

Authenticated user is not the project owner.

404 Not Found:

Project does not exist.

409 Conflict:

Project cannot be deleted because it is already completed or has another business restriction that prevents deletion.

**Database Operation**

Delete the corresponding row from the `projects` table.

Related records are handled according to the foreign-key rules defined in the database schema.

This may include:

- Project skills
- Applications
- Team
- Team members
- Milestones
- Tasks

**Business Rules**

- Only the project owner can delete the project.
- The project must exist.
- The backend must verify ownership before deletion.
- A completed project may not be deleted depending on the application's business rules.
- Deleting a project must not leave orphaned project-related records.

**Related Data**

When a project is deleted:

- Project skills are deleted.
- Applications are deleted.
- Team membership records are deleted.
- Milestones are deleted.
- Tasks are deleted.
- Notifications are not automatically deleted.

**Transaction**

Project deletion and deletion of its dependent records must occur within a database transaction.


**Security**

The client cannot delete a project merely by knowing its ID.

The backend must verify that the authenticated user is the owner of the project before performing the deletion.


## 4.6 Add Required Skill to Project

**Endpoint**

POST /api/projects/{projectId}/skills/{skillId}

**Authentication**

Required.

Only the project owner can add required skills.

**Purpose**

Add an existing skill as a required skill for a project.

**Request Body**

No request body is required.

The `projectId` and `skillId` are provided in the URL.

**Example**

POST /api/projects/1/skills/2

This means:

- Project `1` wants to add skill `2`.
- Skill `2` might be `Spring Boot`.

**Success Response**

HTTP 201 Created

```json
{
  "projectId": 1,
  "skill": {
    "id": 2,
    "name": "Spring Boot"
  }
}


## 4.7 Remove Required Skill from Project

**Endpoint**

DELETE /api/projects/{projectId}/skills/{skillId}

**Authentication**

Required.

Only the project owner can remove required skills.

**Purpose**

Remove a skill from the list of skills required by a project.

**Request Body**

No request body is required.

The `projectId` and `skillId` are provided in the URL.

**Example**

DELETE /api/projects/1/skills/2

This means:

- Project `1` wants to remove skill `2`.
- Skill `2` might be `Spring Boot`.

**Success Response**

HTTP 204 No Content

No response body is returned.

**Errors**

401 Unauthorized:

User is not authenticated.

403 Forbidden:

Authenticated user is not the project owner.

404 Not Found:

Project, skill, or project-skill relationship does not exist.

**Database Operation**

Delete the corresponding row from the `project_skills` table.

Example:

Before:

```text
project_skills

project_id | skill_id
-----------+---------
1          | 1
1          | 2
1          | 3


## 5.1 Apply to a Project

**Endpoint**

POST /api/projects/{projectId}/applications

**Authentication**

Required.

Only authenticated students can apply.

**Purpose**

Allow a student to apply to join a project team.

**Request**

```json
{
  "message": "I have experience with Java and Spring Boot and would like to contribute to this project."
}

## 5.2 Get Project Applications

**Endpoint**

GET /api/projects/{projectId}/applications

**Authentication**

Required.

Only the project owner can view applications for the project.

**Purpose**

Retrieve applications submitted by students who want to join the project.

**Query Parameters**

Optional:

- `status` - Filter applications by status.
- `page` - Page number.
- `size` - Number of applications per page.

**Example**

GET /api/projects/1/applications?status=PENDING&page=0&size=10

**Success Response**

HTTP 200 OK

```json
{
  "content": [
    {
      "id": 15,
      "applicant": {
        "id": 7,
        "name": "Aman",
        "college": "ABC College"
      },
      "message": "I have experience with Java and Spring Boot and would like to contribute to this project.",
      "status": "PENDING",
      "createdAt": "2026-08-17T19:30:00"
    }
  ],
  "page": 0,
  "size": 10,
  "totalElements": 1,
  "totalPages": 1
}

## 5.3 Get My Applications

**Endpoint**

GET /api/applications/me

**Authentication**

Required.

**Purpose**

Retrieve all project applications submitted by the currently authenticated user.

**Query Parameters**

Optional:

- `status` - Filter applications by status.
- `page` - Page number.
- `size` - Number of applications per page.

**Example**

GET /api/applications/me?status=PENDING&page=0&size=10

**Success Response**

HTTP 200 OK

```json
{
  "content": [
    {
      "id": 15,
      "project": {
        "id": 1,
        "title": "Smart Campus Bus Tracker",
        "status": "RECRUITING"
      },
      "message": "I have experience with Java and Spring Boot and would like to contribute to this project.",
      "status": "PENDING",
      "createdAt": "2026-08-17T19:30:00"
    }
  ],
  "page": 0,
  "size": 10,
  "totalElements": 1,
  "totalPages": 1
}

## 5.4 Update Application Status

**Endpoint**

PATCH /api/applications/{applicationId}

**Authentication**

Required.

Only the project owner can accept or reject an application.

**Purpose**

Allow the project owner to accept or reject a student's application.

**Request**

```json
{
  "status": "ACCEPTED"
}

## 6.1 Get Project Team

**Endpoint**

GET /api/projects/{projectId}/team

**Authentication**

Required.

A user can view the team if they have access to the project.

**Purpose**

Retrieve the current members of a project team.

**Example**

GET /api/projects/1/team

**Success Response**

HTTP 200 OK

```json
{
  "projectId": 1,
  "projectTitle": "Smart Campus Bus Tracker",
  "maxTeamSize": 4,
  "currentSize": 2,
  "members": [
    {
      "userId": 3,
      "name": "Rahul",
      "role": "OWNER",
      "joinedAt": "2026-08-17T19:00:00"
    },
    {
      "userId": 7,
      "name": "Aman",
      "role": "MEMBER",
      "joinedAt": "2026-08-17T20:00:00"
    }
  ]
}

## 6.2 Remove Team Member

**Endpoint**

DELETE /api/teams/{teamId}/members/{userId}

**Authentication**

Required.

Only the project owner can remove another team member.

**Purpose**

Remove a member from a project team.

**Example**

DELETE /api/teams/1/members/7

This means:

- Team `1`
- Remove user `7`

**Request Body**

No request body is required.

**Success Response**

HTTP 204 No Content

No response body is returned.

**Errors**

401 Unauthorized:

User is not authenticated.

403 Forbidden:

Authenticated user is not the project owner.

404 Not Found:

Team or team member does not exist.

409 Conflict:

The requested operation is not allowed because the selected member is the project owner.

**Database Operation**

Delete the corresponding row from the `team_members` table.

Example:

Before:

```text
team_members

team_id | user_id | role
--------+---------+--------
1       | 3       | OWNER
1       | 7       | MEMBER
1       | 9       | MEMBER


## 6.3 Leave Project Team

**Endpoint**

DELETE /api/projects/{projectId}/team/me

**Authentication**

Required.

**Purpose**

Allow the authenticated team member to voluntarily leave a project team.

**Example**

DELETE /api/projects/1/team/me

**Request Body**

No request body is required.

**Success Response**

HTTP 204 No Content

No response body is returned.

**Errors**

401 Unauthorized:

User is not authenticated.

403 Forbidden:

User is not a member of the project team.

404 Not Found:

Project or team does not exist.

409 Conflict:

The project owner cannot leave the team while the project is active.

**Database Operation**

Delete the corresponding row from the `team_members` table.

**Business Rules**

- Only the authenticated user can remove themselves through this endpoint.
- A team member can voluntarily leave the project.
- The project owner cannot leave their own project through this endpoint.
- Leaving the project does not delete the user's account or profile.
- Leaving the project frees one position in the team.
- The user's previous application remains unchanged.
- The user may apply to the project again if the project's business rules allow it.

**Security**

The backend determines the user from the authentication mechanism.

The client cannot specify another user's ID to remove them from the team.


## 7.1 Get Project Milestones

**Endpoint**

GET /api/projects/{projectId}/milestones

**Authentication**

Required.

**Purpose**

Retrieve all milestones belonging to a project.

**Example**

GET /api/projects/1/milestones

**Success Response**

HTTP 200 OK

```json
[
  {
    "id": 1,
    "title": "Requirements Analysis",
    "description": "Define project requirements and architecture.",
    "status": "COMPLETED",
    "dueDate": "2026-08-25"
  },
  {
    "id": 2,
    "title": "Backend Development",
    "description": "Implement the Spring Boot backend.",
    "status": "IN_PROGRESS",
    "dueDate": "2026-09-10"
  }
]

## 7.2 Create Milestone

**Endpoint**

POST /api/projects/{projectId}/milestones

**Authentication**

Required.

Only the project owner can create milestones.

**Purpose**

Create a new milestone for a project.

**Request**

```json
{
  "title": "Backend Development",
  "description": "Implement the Spring Boot backend.",
  "dueDate": "2026-09-10"
}


## 7.3 Update Milestone

**Endpoint**

PUT /api/milestones/{milestoneId}

**Authentication**

Required.

Only the project owner can update a milestone.

**Purpose**

Update the information or status of an existing milestone.

**Request**

```json
{
  "title": "Backend Development - Phase 1",
  "description": "Implement authentication and project APIs.",
  "status": "IN_PROGRESS",
  "dueDate": "2026-09-12"
}


## 7.4 Delete Milestone

**Endpoint**

DELETE /api/milestones/{milestoneId}

**Authentication**

Required.

Only the project owner can delete a milestone.

**Purpose**

Delete an existing milestone from a project.

**Example**

DELETE /api/milestones/2

**Success Response**

HTTP 204 No Content

No response body is returned.

**Errors**

401 Unauthorized:

User is not authenticated.

403 Forbidden:

Authenticated user is not the project owner.

404 Not Found:

Milestone does not exist.

409 Conflict:

The milestone cannot be deleted because of a project business rule.

**Database Operation**

Delete the corresponding row from the `milestones` table.

**Business Rules**

- Only the project owner can delete a milestone.
- The milestone must exist.
- The milestone's associated project must exist.
- Deleting a milestone must not delete the project.
- A completed milestone may be protected from deletion depending on the project's business rules.

**Security**

The backend must determine the project associated with the milestone and verify that the authenticated user is the project owner before deleting it.


## 8.1 Get Project Tasks

**Endpoint**

GET /api/projects/{projectId}/tasks

**Authentication**

Required.

The authenticated user must have access to the project.

**Purpose**

Retrieve tasks belonging to a project.

**Query Parameters**

Optional:

- `status` - Filter tasks by status.
- `milestoneId` - Filter tasks by milestone.
- `assigneeId` - Filter tasks by assigned user.
- `page` - Page number.
- `size` - Number of tasks per page.

**Example**

GET /api/projects/1/tasks?status=IN_PROGRESS&page=0&size=10

**Success Response**

HTTP 200 OK

```json
{
  "content": [
    {
      "id": 10,
      "title": "Implement Authentication API",
      "description": "Implement registration and login endpoints.",
      "status": "IN_PROGRESS",
      "priority": "HIGH",
      "milestoneId": 2,
      "assignee": {
        "id": 7,
        "name": "Aman"
      },
      "dueDate": "2026-09-05"
    }
  ],
  "page": 0,
  "size": 10,
  "totalElements": 1,
  "totalPages": 1
}

## 8.2 Create Task

**Endpoint**

POST /api/projects/{projectId}/tasks

**Authentication**

Required.

Only the project owner can create tasks.

**Purpose**

Create a new task for a project.

**Request**

```json
{
  "title": "Implement Authentication API",
  "description": "Implement registration and login endpoints.",
  "milestoneId": 2,
  "priority": "HIGH",
  "dueDate": "2026-09-05"
}

## 8.3 Update Task

**Endpoint**

PUT /api/tasks/{taskId}

**Authentication**

Required.

Only the project owner or the task assignee can update the task.

**Purpose**

Update the details of an existing task.

**Request**

```json
{
  "title": "Implement Authentication API - Phase 1",
  "description": "Implement registration and login endpoints with JWT authentication.",
  "priority": "URGENT",
  "dueDate": "2026-09-07"
}

## 8.4 Update Task Status

**Endpoint**

PATCH /api/tasks/{taskId}/status

**Authentication**

Required.

The project owner can update the status of any task.

A team member can update the status only of a task assigned to them.

**Purpose**

Change the current status of a task.

**Request**

```json
{
  "status": "IN_PROGRESS"
}

Business rules - 

**Allowed Status Transitions**

- TODO → IN_PROGRESS
- TODO → BLOCKED
- IN_PROGRESS → TODO
- IN_PROGRESS → COMPLETED
- IN_PROGRESS → BLOCKED
- BLOCKED → IN_PROGRESS
- COMPLETED → IN_PROGRESS

The backend must reject any status transition not listed above.



## 8.5 Assign Task to Team Member

**Endpoint**

PATCH /api/tasks/{taskId}/assignee

**Authentication**

Required.

Only the project owner can assign or reassign tasks.

**Purpose**

Assign an existing task to a member of the project team.

**Request**

```json
{
  "userId": 7
}

## 8.6 Delete Task

**Endpoint**

DELETE /api/tasks/{taskId}

**Authentication**

Required.

Only the project owner can delete tasks.

**Purpose**

Delete an existing task from a project.

**Example**

DELETE /api/tasks/10

**Success Response**

HTTP 204 No Content

No response body is returned.

**Errors**

401 Unauthorized:

User is not authenticated.

403 Forbidden:

User does not have permission to delete the task.

404 Not Found:

Task does not exist.

409 Conflict:

The task cannot be deleted because of a project business rule.

**Database Operation**

Delete the corresponding row from the `tasks` table.

**Business Rules**

- The task must exist.
- The task must belong to an existing project.
- Only users with the required project permissions can delete the task.
- Deleting a task must not delete its project or milestone.
- Deleting a completed task may be restricted depending on the project's business rules.
- Any related records must be handled according to the database foreign-key rules.

**Security**

The backend must determine the project associated with the task and verify that the authenticated user has permission to delete the task before performing the deletion.

The client cannot delete a task merely by knowing its ID.


## 9.1 Get My Notifications

**Endpoint**

GET /api/notifications

**Authentication**

Required.

**Purpose**

Retrieve notifications belonging to the currently authenticated user.

**Query Parameters**

Optional:

- `unreadOnly` - Return only unread notifications.
- `page` - Page number.
- `size` - Number of notifications per page.

**Example**

GET /api/notifications?unreadOnly=true&page=0&size=10

**Success Response**

HTTP 200 OK

```json
{
  "content": [
    {
      "id": 25,
      "type": "APPLICATION_RECEIVED",
      "title": "New Project Application",
      "message": "Aman applied to join your project.",
      "referenceId": 15,
      "isRead": false,
      "createdAt": "2026-08-17T22:15:00"
    },
    {
      "id": 26,
      "type": "APPLICATION_ACCEPTED",
      "title": "Application Accepted",
      "message": "Your application to Smart Campus Bus Tracker was accepted.",
      "referenceId": 15,
      "isRead": true,
      "createdAt": "2026-08-17T22:20:00"
    }
  ],
  "page": 0,
  "size": 10,
  "totalElements": 2,
  "totalPages": 1
}

## 9.2 Mark Notification as Read

**Endpoint**

PATCH /api/notifications/{notificationId}/read

**Authentication**

Required.

**Purpose**

Mark a notification belonging to the authenticated user as read.

**Example**

PATCH /api/notifications/25/read

**Request Body**

No request body is required.

**Success Response**

HTTP 200 OK

```json
{
  "id": 25,
  "type": "APPLICATION_RECEIVED",
  "title": "New Project Application",
  "message": "Aman applied to join your project.",
  "referenceId": 15,
  "isRead": true,
  "createdAt": "2026-08-17T22:15:00"
}

## 9.3 Mark All Notifications as Read

**Endpoint**

PATCH /api/notifications/read-all

**Authentication**

Required.

**Purpose**

Mark all unread notifications belonging to the authenticated user as read.

**Request Body**

No request body is required.

**Success Response**

HTTP 204 No Content

No response body is returned.

**Errors**

401 Unauthorized:

User is not authenticated.

**Database Operation**

Update all unread notifications belonging to the authenticated user:

```text
is_read = true


