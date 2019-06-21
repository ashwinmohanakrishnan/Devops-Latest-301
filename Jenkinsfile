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
                 
                 context="sonarqube/qualitygate"
        setBuildStatus ("${context}", 'Checking Sonarqube quality gate', 'PENDING')
        timeout(time: 1, unit: 'MINUTES') { // Just in case something goes wrong, pipeline will be killed after a timeout
            def qg = waitForQualityGate() // Reuse taskId previously collected by withSonarQubeEnv
            if (qg.status != 'OK') {
                setBuildStatus ("${context}", "Sonarqube quality gate fail: ${qg.status}", 'FAILURE')
                error "Pipeline aborted due to quality gate failure: ${qg.status}"
            } else {
                setBuildStatus ("${context}", "Sonarqube quality gate pass: ${qg.status}", 'SUCCESS')
            }    
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
