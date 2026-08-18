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