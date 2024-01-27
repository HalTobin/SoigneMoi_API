pipeline {
    agent { label 'bbq2' }
     
    stages {
        stage('Build env') {
            steps {
                script {
                    SERVER = ${SERVER};
					JAVA_HOME = ${JDK};
					MAVEN = ${MAVEN_HOME};
                }
            }
        }

        stage('Build Jar') {
            steps {
                withEnv(['JAVA_HOME='+JAVA_HOME, 'MAVEN='+MAVEN]){
                    sh '''export JAVA_HOME=${JAVA_HOME} && ${MAVEN} clean install \
                        -Dstyle.color=never \
                        -Dmaven.test.skip=true
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
                    ssh -p ${PORT} jenkins@${SERVER} "sudo systemctl stop soignemoi.service;rm /opt/SoigneMoi/soignemoi*.jar"                    
                    scp -P ${PORT} ${WORKSPACE}/target/soignemoi*.jar jenkins@${SERVER}:/opt/SoigneMoi/  
                    ssh -p ${PORT} jenkins@${SERVER} "sudo systemctl start soignemoi.service"                
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
