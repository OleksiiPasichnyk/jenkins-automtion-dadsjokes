data "aws_vpc" "main" {
  default = true
}

data "aws_subnets" "vpcsubnets" {
  filter {
    name   = "vpc-id"
    values = [data.aws_vpc.main.id]
  }
  filter {
    name   = "default-for-az"
    values = [true]
  }
  filter {
    name   = "state"
    values = ["available"]
  }
}

data "aws_subnet" "vpcsubnet" {
  for_each = { for index, subnetid in data.aws_subnets.vpcsubnets.ids : index => subnetid }
  id       = each.value
}

data "aws_caller_identity" "current" {}

# Fetch the Route 53 Hosted Zone ID
data "aws_route53_zone" "paxel_ca" {
  name         = "paxel.ca." # Replace with your domain name, ending with a period
  private_zone = false
}

output "hosted_zone_id" {
  value = data.aws_route53_zone.paxel_ca.zone_id
}
