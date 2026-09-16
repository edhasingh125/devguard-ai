# DevGuard AI

## Product Requirements Document

**Version:** 1.0
**Status:** Draft
**Product Type:** AI-powered Developer Engineering Intelligence Platform

---

# 1. Product Overview

DevGuard AI is an AI-powered engineering intelligence platform designed to help software development teams understand, review, secure, and maintain their codebases.

A developer connects a GitHub repository to DevGuard AI. The platform analyzes the repository using a combination of deterministic software-analysis techniques and AI capabilities. It identifies potential security vulnerabilities, code-quality problems, dependency risks, architectural issues, test gaps, and other engineering risks.

The platform then uses Large Language Models (LLMs), Retrieval-Augmented Generation (RAG), embeddings, and AI agents to provide contextual explanations and actionable recommendations.

Unlike a traditional code scanner, DevGuard AI aims to understand the context of a software project and communicate findings in a way that developers can act upon.

---

# 2. Problem Statement

Modern software projects can contain thousands of files, dependencies, APIs, services, database interactions, and configuration files. As projects grow, developers and engineering teams have difficulty understanding the entire codebase and identifying issues quickly.

Developers commonly use multiple tools for:

* Code reviews
* Security scanning
* Dependency checking
* CI/CD monitoring
* Documentation
* Error investigation
* Architecture understanding
* Repository management

These tools often operate independently, forcing developers to switch between different systems and manually connect information.

DevGuard AI aims to provide a unified engineering intelligence layer that combines repository analysis, security insights, code understanding, and AI assistance in a single platform.

---

# 3. Product Vision

### Vision

> Build an AI engineering assistant that understands a software team's codebase and helps developers identify, understand, and resolve engineering problems.

DevGuard AI should eventually become a system where developers can ask:

> "Why is this API failing?"

> "How does authentication work in this project?"

> "Are there security risks in this pull request?"

> "Which parts of the system are most difficult to maintain?"

> "What changed that caused this deployment failure?"

and receive answers grounded in the actual repository and engineering data.

---

# 4. Goals

## Primary Goals

1. Allow developers to connect GitHub repositories.
2. Analyze repository structure and source code.
3. Detect security and engineering risks.
4. Provide an AI-powered codebase question-answering system.
5. Implement RAG for repository-aware responses.
6. Provide AI-assisted pull request reviews.
7. Explain detected issues using contextual information.
8. Provide actionable remediation suggestions.
9. Visualize software architecture and engineering health.
10. Gradually introduce an AI engineering agent capable of interacting with development workflows.

## Secondary Goals

* Provide developer onboarding assistance.
* Analyze CI/CD failures.
* Track engineering health over time.
* Provide repository-level analytics.
* Generate documentation from existing code.
* Support multiple repositories for a team.

---

# 5. Non-Goals

The first versions of DevGuard AI will NOT attempt to:

* Automatically modify production code without approval.
* Replace human code reviewers.
* Guarantee that all security vulnerabilities are detected.
* Automatically deploy code to production.
* Act as an unrestricted autonomous coding agent.
* Support every programming language initially.

Human approval will remain important for actions that modify repositories or development workflows.

---

# 6. Target Users

## Primary User — Software Developer

A developer who wants to:

* Understand an unfamiliar codebase.
* Find bugs and security issues.
* Understand architecture.
* Investigate CI failures.
* Review pull requests.
* Ask questions about existing code.

## Secondary User — Tech Lead

A tech lead who wants to:

* Monitor repository health.
* Identify technical debt.
* Understand architectural risks.
* Review security findings.
* Track engineering quality.

## Future User — Engineering Manager

An engineering manager could use the platform to understand:

* Engineering health.
* Security risks.
* Technical debt.
* Repository activity.
* Development trends.

---

# 7. Core Product Features

## 7.1 Authentication

Users should be able to:

* Create an account.
* Log in securely.
* Log out.
* Manage their profile.
* Connect GitHub.

Authentication will support secure token-based authentication and eventually OAuth-based GitHub authentication.

---

# 8. GitHub Repository Integration

Users can connect their GitHub account and select repositories.

### User Flow

```text
Login
 ↓
Connect GitHub
 ↓
Authorize DevGuard
 ↓
Select Repository
 ↓
Repository Ingestion
 ↓
Analysis
 ↓
Dashboard
```

The system should retrieve relevant repository information such as:

* Repository metadata
* Branches
* Commits
* Pull requests
* Issues
* Source files
* Dependency files
* Configuration files

---

# 9. Repository Analysis Engine

After a repository is connected, DevGuard analyzes its structure.

The system should identify:

* Programming languages
* Frameworks
* Project structure
* API endpoints
* Major modules
* Dependencies
* Configuration files
* Authentication mechanisms
* Database integrations
* External services
* Test files

Example:

```text
Repository

Frontend
 ├── Next.js
 └── TypeScript

Backend
 ├── Spring Boot
 └── Java

Database
 └── PostgreSQL

Authentication
 └── JWT

External Services
 ├── Stripe
 └── AWS S3
```

---

# 10. Engineering Health Score

Each repository will receive an overall engineering health score based on measurable signals.

Possible categories:

* Code Quality
* Security
* Dependency Health
* Test Coverage
* Architecture
* Documentation
* CI/CD
* Maintainability

Example:

```text
ENGINEERING HEALTH

78 / 100

Code Quality       84
Security           72
Testing            68
Architecture       81
Dependencies       76
Documentation      59
CI/CD              91
```

The score should be transparent and explain how it was calculated rather than being an unexplained AI-generated number.

---

# 11. Security Intelligence

DevGuard will scan repositories for common security risks.

Potential categories include:

### Secret Detection

Detect:

* API keys
* Passwords
* Access tokens
* Private keys
* Cloud credentials

### Code Security

Potentially detect:

* SQL injection
* Cross-site scripting
* Insecure authentication
* Broken access control
* Unsafe file handling
* Insecure configurations

### Dependency Security

Identify:

* Outdated dependencies
* Known vulnerable packages
* High-risk dependencies

Security findings should contain:

```text
Severity
Affected file
Affected line
Description
Potential impact
Evidence
Recommended remediation
```

---

# 12. AI Codebase Assistant

This is one of the core AI features.

Developers can ask natural-language questions about their repository.

Examples:

> "How does authentication work?"

> "Where are payments processed?"

> "Which service handles order creation?"

> "Explain this class."

> "Why could this endpoint return a 401?"

> "Where is the database connection configured?"

The system should answer using information retrieved from the actual repository.

---

# 13. Retrieval-Augmented Generation (RAG)

DevGuard will use RAG to provide repository-aware responses.

### Pipeline

```text
Repository
     ↓
File Processing
     ↓
Code Chunking
     ↓
Embeddings
     ↓
Vector Database
     ↓
Semantic Search
     ↓
Relevant Code
     ↓
LLM
     ↓
Contextual Answer
```

The system should avoid sending an entire repository to the LLM unnecessarily.

Relevant files and code sections should be retrieved based on the user's question.

---

# 14. Code-Aware Chunking

Traditional text chunking may not work well for source code.

DevGuard should eventually support code-aware chunking based on:

* Classes
* Methods
* Functions
* Interfaces
* Modules
* Configuration sections

Each chunk should maintain useful metadata such as:

```text
Repository
File
Language
Class
Function
Module
Line range
```

This metadata can improve retrieval quality and explainability.

---

# 15. AI Pull Request Review

DevGuard will analyze GitHub Pull Requests.

### Workflow

```text
Developer creates PR
        ↓
GitHub Webhook
        ↓
DevGuard receives changes
        ↓
Static Analysis
        ↓
Security Analysis
        ↓
Relevant Code Retrieval
        ↓
LLM Review
        ↓
Review Result
```

The review can identify:

* Potential bugs
* Security concerns
* Performance problems
* Maintainability issues
* Missing tests
* Inconsistent implementation

Example:

```text
PR #142

HIGH RISK

PaymentService.java

Potential issue:
The payment status is updated before
the transaction is successfully completed.

Potential impact:
The system may report a successful
payment even if the transaction fails.

Suggested approach:
Perform the status update after
successful transaction confirmation.
```

---

# 16. AI Explanation Layer

Every important finding should have an explanation.

Instead of simply:

```text
SQL Injection detected.
```

DevGuard should explain:

```text
What happened:
User input is directly included in a
database query.

Why it matters:
An attacker may manipulate the input
to alter the query.

Where:
UserRepository.java
Line 84

Recommended approach:
Use parameterized queries.
```

---

# 17. Architecture Intelligence

DevGuard should analyze relationships between components.

It can generate an architecture graph such as:

```text
Frontend
   ↓
API
   ↓
Authentication
   ↓
Business Services
   ↓
Repositories
   ↓
Database
```

The system should eventually identify:

* Highly coupled modules
* Circular dependencies
* Large classes
* Potential architectural bottlenecks
* Service relationships
* Database dependencies

---

# 18. AI Repository Documentation

DevGuard can generate documentation from the existing repository.

Potential outputs:

### Project Overview

### Architecture Documentation

### API Documentation

### Authentication Flow

### Database Overview

### Setup Instructions

### Developer Onboarding Guide

This is particularly useful for large or poorly documented repositories.

---

# 19. CI/CD Intelligence

DevGuard can eventually integrate with GitHub Actions.

When a workflow fails:

```text
GitHub Actions
       ↓
Failure detected
       ↓
Logs retrieved
       ↓
Relevant commit identified
       ↓
Repository context retrieved
       ↓
LLM analyzes failure
       ↓
Explanation + recommendation
```

Example:

```text
CI FAILURE

Workflow:
Backend Tests

Likely cause:
Database connection configuration

Related change:
Commit a91f82c

Suggested fix:
Verify DATABASE_URL in CI environment.
```

---

# 20. AI Engineering Agent

The advanced version of DevGuard will include an AI engineering agent.

The agent will have controlled access to tools.

Possible tools:

```text
GitHub
 ├── Read repository
 ├── Read PR
 ├── Read issues
 ├── Read commits
 ├── Create issue
 └── Create PR

DevGuard
 ├── Search code
 ├── Run analysis
 ├── Search findings
 └── Inspect architecture
```

The agent can reason across these tools.

---

# 21. Human-in-the-Loop

The AI agent should not have unrestricted permissions.

For actions such as:

* Creating PRs
* Modifying code
* Creating issues
* Changing configurations

the system should request user approval.

Example:

```text
AI Agent

I identified a possible authentication bug.

I generated a proposed fix.

[View Changes]

[Create Pull Request]

[Reject]
```

This ensures that the developer remains in control.

---

# 22. LLM Architecture

The AI layer will consist of several components.

```text
                AI LAYER

                    │
          ┌─────────┴─────────┐
          │                   │
       RAG System          Agent System
          │                   │
     ┌────┴────┐         ┌────┴────┐
     │         │         │         │
Embeddings Retrieval    Tools    Reasoning
     │         │         │         │
     └────┬────┘         └────┬────┘
          │                   │
          └─────────┬─────────┘
                    ↓
                   LLM
```

The exact LLM provider can remain configurable.

---

# 23. LLM Responsibilities

The LLM should primarily be used for tasks where contextual reasoning is useful:

* Explaining issues
* Summarizing code
* Answering repository questions
* Reviewing changes
* Generating documentation
* Suggesting remediation
* Reasoning over retrieved context
* Interpreting CI failures
* Supporting agent workflows

The LLM should NOT be solely responsible for deterministic security checks where specialized scanners or rules can provide more reliable detection.

---

# 24. Technology Stack

## Frontend

* Next.js
* TypeScript
* Tailwind CSS
* Component library
* Recharts / visualization library

## Backend

* Java
* Spring Boot
* Spring Security
* REST APIs
* Webhooks
* Server-Sent Events or WebSockets where appropriate

## Database

* PostgreSQL
* pgvector

## Caching

* Redis

## AI

* LLM API
* Embedding models
* RAG
* Vector search
* Tool calling
* Structured outputs
* AI agents

## DevOps

* Docker
* Docker Compose
* GitHub Actions
* Cloud deployment

## Integrations

* GitHub API
* GitHub Webhooks

---

# 25. High-Level Architecture

```text
                         USER
                           │
                           ▼
                    Next.js Frontend
                           │
                           ▼
                    Spring Boot API
                           │
        ┌──────────────────┼──────────────────┐
        │                  │                  │
        ▼                  ▼                  ▼
 Authentication       Repository          Analysis
 Service              Service             Engine
        │                  │                  │
        │                  ▼                  │
        │             GitHub API              │
        │                                     │
        └──────────────────┬──────────────────┘
                           ▼
                     PostgreSQL
                           │
                     pgvector
                           │
                           ▼
                       AI Layer
                           │
                ┌──────────┼──────────┐
                ▼          ▼          ▼
               RAG        LLM       Agent
                │          │          │
                └──────────┼──────────┘
                           ▼
                     GitHub Tools
```

---

# 26. Core Database Entities

Initial entities:

### User

```text
id
name
email
password_hash
created_at
```

### Repository

```text
id
user_id
github_id
name
owner
url
language
created_at
```

### Analysis

```text
id
repository_id
status
started_at
completed_at
```

### Finding

```text
id
analysis_id
category
severity
file_path
line_number
description
recommendation
status
```

### CodeChunk

```text
id
repository_id
file_path
content
embedding
metadata
```

### PullRequest

```text
id
repository_id
github_pr_id
title
status
analysis_status
```

### AIConversation

```text
id
user_id
repository_id
created_at
```

### AIMessage

```text
id
conversation_id
role
content
created_at
```

The schema will evolve as the product develops.

---

# 27. MVP Scope

The first working version should NOT contain every feature.

### MVP:

```text
Authentication
      ↓
GitHub connection
      ↓
Repository selection
      ↓
Repository ingestion
      ↓
Basic analysis
      ↓
Security findings
      ↓
Engineering dashboard
      ↓
Ask Your Codebase
```

The first AI capability should be:

### RAG-powered repository assistant.

This gives us a functional AI product early.

---

# 28. Future Features

After the MVP:

### Version 2

* AI PR review
* Architecture visualization
* Dependency intelligence
* Documentation generation
* Improved security analysis

### Version 3

* CI/CD failure analysis
* Engineering health trends
* Multi-repository dashboards
* Team management
* RBAC

### Version 4

* AI Engineering Agent
* Automated issue creation
* Proposed code patches
* AI-generated tests
* Pull-request creation with approval

---

# 29. Success Metrics

The platform should eventually measure:

### Technical

* Repository analysis success rate
* RAG retrieval accuracy
* AI response latency
* False-positive rate
* Security finding accuracy
* CI analysis success rate

### Product

* Number of repositories analyzed
* Questions asked
* PRs reviewed
* Issues identified
* Suggested fixes accepted
* Developer time saved

The metrics should be based on observable product behavior rather than unsupported claims.

---

# 30. Security Requirements

Because DevGuard itself handles source code, potentially sensitive information, security is a core requirement.

The platform should:

* Encrypt sensitive credentials.
* Never expose GitHub access tokens to the frontend unnecessarily.
* Follow least-privilege access principles.
* Implement role-based authorization.
* Secure webhook endpoints.
* Validate GitHub webhook signatures.
* Avoid logging secrets.
* Protect repository data.
* Apply rate limiting.
* Maintain audit logs for sensitive agent actions.

AI-generated output should also be treated as potentially incorrect and should not automatically be trusted for security decisions.

---

# 31. Privacy Requirements

Repository source code can be proprietary.

The system should clearly define:

* What repository data is stored.
* How long it is stored.
* What data is sent to an external LLM.
* Whether AI providers retain submitted data.
* How users can delete repository data.
* What permissions are required from GitHub.

---

# 32. User Experience Principles

DevGuard should follow these principles:

### 1. Explainability

Every important AI result should explain its evidence.

### 2. Developer-first

The platform should help developers solve problems rather than overwhelm them with metrics.

### 3. Actionable insights

Every finding should ideally answer:

```text
What happened?
Why does it matter?
Where is it?
How can I fix it?
```

### 4. Human control

AI can recommend and propose actions, but important repository changes require approval.

### 5. Progressive complexity

The dashboard should provide simple summaries while allowing developers to drill into technical details.

---

# 33. Example End-to-End User Journey

```text
1. Developer creates DevGuard account.

2. Developer connects GitHub.

3. Developer selects a repository.

4. DevGuard starts repository ingestion.

5. Repository is parsed and indexed.

6. Static and security analysis runs.

7. Engineering Health Score is generated.

8. Dashboard displays findings.

9. Developer asks:

   "How does authentication work?"

10. RAG retrieves relevant authentication files.

11. LLM generates an explanation.

12. Developer opens a Pull Request.

13. DevGuard analyzes the changes.

14. AI identifies potential issues.

15. Developer reviews AI suggestions.

16. Developer approves a proposed fix.

17. DevGuard creates a GitHub PR.

18. Developer merges the change.
```

---

# 34. Long-Term Vision

The long-term goal is to evolve DevGuard AI from a repository analysis tool into an **AI engineering intelligence platform**.

Eventually, DevGuard should understand the relationship between:

```text
Code
 ↓
Architecture
 ↓
Dependencies
 ↓
Security
 ↓
Tests
 ↓
CI/CD
 ↓
Production
```

and allow developers to interact with this information through natural language.

The ultimate experience should be:

> **"DevGuard understands my software system and helps me make better engineering decisions."**

---

# 35. Project Philosophy

DevGuard AI is not intended to demonstrate that an LLM can generate code.

The project is intended to demonstrate how AI can be responsibly integrated into a real software engineering workflow.

The key technical principles are:

```text
Deterministic Analysis
        +
Repository Context
        +
RAG
        +
LLM Reasoning
        +
Tool-Using Agents
        +
Human Approval
        =
AI Engineering Platform
```

This architecture allows the project to demonstrate practical knowledge of modern backend development, distributed systems, databases, security, DevOps, AI/LLMs, RAG, vector search, agentic workflows, and software architecture.
