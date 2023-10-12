pipeline{
    agent any
    
    tools{
        maven 'Maven 3.9.5'
    }
    
    environment {
        registry = "zz19431943/springboot-k8s-curd:1.0"
    }
    
    stages{
        
        stage("Build JAR"){
            steps{
                script{
                    sh 'mvn clean install'
                }
            }
        }
    	stage('Building image') {
      		steps{
        		script {
          			dockerImage = docker.build registry 
        		}
      		}
    	}
}