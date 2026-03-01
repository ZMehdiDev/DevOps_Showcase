resource "aws_apprunner_service" "front" {
  service_name = "${var.project_name}-front"

  source_configuration {
    authentication_configuration {
      access_role_arn = aws_iam_role.apprunner_ecr_access.arn
    }

    image_repository {
      image_identifier      = "${aws_ecr_repository.front.repository_url}:latest"
      image_repository_type = "ECR"

      image_configuration {
        port = "80"
      }
    }

    auto_deployments_enabled = true
  }

  health_check_configuration {
    protocol            = "TCP"
    interval            = 10
    timeout             = 5
    healthy_threshold   = 1
    unhealthy_threshold = 5
  }

  tags = {
    project = var.project_name
  }
}

output "apprunner_front_url" {
  value = aws_apprunner_service.front.service_url
}

output "apprunner_front_arn" {
  value = aws_apprunner_service.front.arn
}