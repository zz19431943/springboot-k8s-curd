pipeline{
    agent any
    
    tools{
        maven 'Maven 3.9.5'
    }
    stages{
        
        stage("Build Maven"){
            steps{
                script{
                    sh 'mvn clean install'
                }
            }
        }
    
    }
}