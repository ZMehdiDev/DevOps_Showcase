variable "aws_region" {
  type        = string
  description = "AWS region"
  default     = "eu-west-1"
}

variable "project_name" {
  type        = string
  description = "Prefix for resource names"
  default     = "devops-showcase"
}

variable "github_repo" {
  type        = string
  description = "GitHub repo in the form owner/repo"
}