pipeline {
    agent any
    
    parameters {
        string(name: 'ACTION', defaultValue: 'proceed', description: 'Action to take')
    }
    tools {
        terraform 'tf1.6'
    }

    environment {
        // Define a variable to hold the output from the previous stage
        PREVIOUS_STAGE_OUTPUT = ''
    }

    stages {
        stage('Clone Git repo') {
            steps {
                checkout([
                    $class: 'GitSCM',
                    branches: [[name: 'op/setting_up_instance_and_node_js_app']],
                    userRemoteConfigs: [[
                        url: 'https://github.com/LocalCoding/DevOps_jenkins_tf_ans_node_js_setup.git',
                        credentialsId: 'git_access_PAT'
                    ]]
                ])
            }
        }
        stage('Terraform Plan') {
            steps {
                dir('terraform_ansible_generic_instace_setup_template') {
                    sh '''
                    echo "yes" | terraform init
                    terraform plan -destroy -out=destroyplan.tfplan
                    '''
                    script {
                        env.PREVIOUS_STAGE_OUTPUT = sh(script: 'echo "Output from previous stage"', returnStdout: true).trim()
                    }
                }
            }
        }
        stage('Approval') {
            steps {
                // Echo the output from the previous stage
                echo "Output from the Previous Stage: ${env.PREVIOUS_STAGE_OUTPUT}"
                // Ask for the input to proceed or abort the build
                script {
                    def userInput = input(
                        id: 'userInput', 
                        message: 'Choose to proceed or abort the build:', 
                        parameters: [choice(name: 'Proceed?', choices: ['proceed', 'abort'], description: 'Proceed or Abort')]
                    )
                    if (userInput == 'abort') {
                        error('Aborting the build.')
                    }
                }
            }
        }
        stage('Terraform Apply Destroy') {
            steps {
                dir('terraform_ansible_generic_instace_setup_template'){
                sh '''
                terraform apply destroyplan.tfplan
                '''
                }
            }
        }
    }
}
