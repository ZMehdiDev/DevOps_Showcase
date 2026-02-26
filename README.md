# DevOps_Showcase
DevOps Showcase — A small containerized app (Spring Boot API + static Nginx frontend) deployed to AWS App Runner using Terraform. GitHub Actions CI/CD uses OIDC (no long-lived keys) to build, test, push images to ECR, and deploy on every push to main. Security-first: zero secrets in git and reproducible apply/destroy.

## Goal
A portfolio-ready repo showing: Docker, Terraform IaC, CI/CD (GitHub Actions), AWS (ECR + App Runner) with OIDC auth and zero secrets in git.

## Local (TODO)
- docker compose up

## Cloud (TODO)
- terraform apply
- terraform destroy