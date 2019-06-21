pipeline {
    agent any
    environment {
        branch = 'master'
        scmUrl = 'https://github.com/ashwinmohanakrishnan/Devops-301.git'
        serverPort = '8080'
        scannerHome = tool 'sonar'
        developmentServer = 'dev-myproject.mycompany.com'
        stagingServer = 'staging-myproject.mycompany.com'
        productionServer = 'production-myproject.mycompany.com'
    }
    
    tools {
    maven 'maven'
  }
    stages {
        stage('checkout git') {
            steps {
                git branch: branch, credentialsId: 'git', url: scmUrl
            }
        }

        stage('Sonarqube') {
  
             steps {
                   withSonarQubeEnv('sonar') {
                        sh "mvn sonar:sonar"
                     }
             }
        }
        
        
        
        
        stage('build') {
            steps {
                
                sh 'mvn clean install'
            }
        }

        stage ('test') {
            steps {
                parallel (
                    "unit tests": { sh 'mvn test' },
                    "integration tests": { sh 'mvn integration-test' }
                )
            }
        }
        

       
    }
    post {
        failure {
            mail to: 'team@example.com', subject: 'Pipeline failed', body: "${env.BUILD_URL}"
        }
    }
}
