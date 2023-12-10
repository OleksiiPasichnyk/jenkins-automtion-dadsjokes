# DevOps_jenkins_tf_ans_node_js_setup
terraform + ansible + nodejs app infra an setup

This repo contains multiple tech insights. 
The primary purpose is to show off the Jenkins + Terraform + Ansible + NodeJs combination. 
Prerequisites to make it work
0. Understanding of 
  `Groovy`
  `HSL` `(*.tf)`
  `Ansible` `(YAML)`
  
1. Jenkins with access to your AWS account and cloud
   It could be AWS Cloud-based EC2 instance with proper role and permissions attached
     or
   It could be Jenkins running on your localhost with aws credentials set to provide access to:
     1. EC2
     2. Role
     3. S3
2. The second part involves understanding of Jenkins pipeline and how to set them up using Jenkins `groovy` language
3. The app's directories are used as open-source products available in public repositories on the web. without any commercial or any other intent. Any improvements to the app desighn highly encouraged and appreciated.
     The apps in question:
         Battleships
         DadJokes generator 
