pipeline {
    agent { label 'bbq2' }
     
    stages {
        stage('Build env') {
            steps {
                script {
                    SERVER = "$SERVER";
					JAVA_HOME = "$JAVA_VER";
                }
            }
        }

        stage('Build Jar') {
            steps {
                withEnv(['JAVA_HOME='+JAVA_HOME]){
                    sh '''export JAVA_HOME=${JAVA_HOME} && chmod +x ./gradlew && ./gradlew build -x test \
                        -Dstyle.color=never
                        '''
                }
            }
        }

        stage('Unit Test') {
            steps {
                script {
                    echo 'TODO'
                }
            }
        }

        stage('Deploy') {
            steps {
                withEnv(['SERVER='+SERVER]){
                    sh '''
                    ssh -o StrictHostKeyChecking=no -p ${PORT} jenkins@${SERVER} "sudo systemctl stop soignemoi.service;rm /opt/SoigneMoi/soignemoi*.jar"                    
                    scp -o StrictHostKeyChecking=no -P ${PORT} ${WORKSPACE}/target/soignemoi*.jar jenkins@${SERVER}:/opt/SoigneMoi/  
                    ssh -o StrictHostKeyChecking=no -p ${PORT} jenkins@${SERVER} "sudo systemctl start soignemoi.service"               
                    '''
               }
            }
        }
    }

    post {
        cleanup {
            sh '''
            rm -rf ./*
            '''
        }
    }
}
