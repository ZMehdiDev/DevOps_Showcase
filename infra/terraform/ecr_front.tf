resource "aws_ecr_repository" "front" {
  name                 = "${var.project_name}-front"
  image_tag_mutability = "MUTABLE"
  force_delete         = true
}

resource "aws_ecr_lifecycle_policy" "front" {
  repository = aws_ecr_repository.front.name
  policy = jsonencode({
    rules = [{
      rulePriority = 1
      description  = "Keep last 20 images"
      selection = {
        tagStatus   = "any"
        countType   = "imageCountMoreThan"
        countNumber = 20
      }
      action = { type = "expire" }
    }]
  })
}

output "ecr_front_repository_url" {
  value = aws_ecr_repository.front.repository_url
}