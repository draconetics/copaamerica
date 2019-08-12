
pipeline{
    agent none
    stages{
        stage("Clone Repository"){
            agent { label 'master' }
            steps{
                git 'https://github.com/draconetics/copaamerica'
                sh "echo Cloned!"
            }
            
        }
        stage("Build"){
            agent{
                docker 'maven:3-alpine'
            }
            steps{
                sh "mvn -q clean package"
            }
        }
        stage("Package"){
            agent { label ' master' }
            steps{
                sh "docker build -t copaamerica/news ."
                sh "docker save -o news.tar copaamerica/news"
                stash name: "stash-artifact", includes: "news.tar"
                archiveArtifacts 'target/*jar'
            }
        }
        stage("Archive artifact"){
            agent {label 'master'}
            steps{
                archiveArtifacts 'target/*jar'
            }
        }
        stage("Deployment QA"){
            agent { label 'master' }
            steps{
                sh "docker rm news -f || true"
                sh "docker run -d -p 8090:8090 --name news copaamerica/news"
            }
        }
 
        stage("Deployment PROD"){
            agent{label 'PROD'}
            steps{
                 unstash "stash-artifact"
                 sh "docker load -i news.tar"
                 sh "docker rm news -f || true"
                 sh "docker run -d -p 8080:8080 --name news copaamerica/news"
            }
        }
    }
}
