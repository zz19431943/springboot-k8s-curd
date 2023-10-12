pipeline{
    agent any
    
    tools{
        maven 'Maven 3.9.5'
    }
    
    environment {
        registry = "zz19431943/springboot-k8s-curd:1.0"
        registryCredential = 'docker_hub_access_token_10122023'
        dockerImage=''
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
    	
    	stage("Pushing image to Docker Hub"){
            steps{
                script{
                    docker.withRegistry('',registryCredential)
                    dockerImage.push()
                }
            }
        }
    }
}