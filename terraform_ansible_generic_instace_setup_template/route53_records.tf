resource "aws_route53_record" "battleship_nodejs" {
  zone_id = data.aws_route53_zone.paxel_ca.zone_id # Replace with your actual Hosted Zone ID
  name    = "battleship.${var.domain_name}" # Replace with the desired domain
  type    = "A"
  ttl     = "300"
  records = [aws_instance.nodejs-apps-server.public_ip]

  depends_on = [
    aws_instance.nodejs-apps-server
  ]
}

resource "aws_route53_record" "dadsjokes_nodejs" {
  zone_id = data.aws_route53_zone.paxel_ca.zone_id # Replace with your actual Hosted Zone ID
  name    = "dadjokes.${var.domain_name}" # Replace with the desired domain
  type    = "A"
  ttl     = "300"
  records = [aws_instance.nodejs-apps-server.public_ip]

  depends_on = [
    aws_instance.nodejs-apps-server
  ]
}