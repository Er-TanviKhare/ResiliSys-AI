pipeline {
    agent any

    stages {

        stage('Update Repository') {
            steps {
                sh '''
                    cd /home/ubuntu/ResiliSys-AI
                    git config --global --add safe.directory /home/ubuntu/ResiliSys-AI
                    git pull origin main
                '''
            }
        }

        stage('Build & Deploy') {
            steps {
                sh '''
                    cd /home/ubuntu/ResiliSys-AI/deployment/docker
                    docker compose up --build -d
                '''
            }
        }

        stage('Verify Deployment') {
            steps {
                sh 'docker ps'
            }
        }
    }

    post {
        success {
            echo 'ResiliSys AI deployed successfully.'
        }
        failure {
            echo 'Deployment failed.'
        }
    }
}