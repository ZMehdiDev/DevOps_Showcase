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