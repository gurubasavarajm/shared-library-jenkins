def call(String git_branch = 'main', String gitCredentials = null, String gitRepoURL = null ){

    pipeline {
        agents any

        environment {
        GITBRANCH = "${git_branch}"
        GITCRED =  "${gitCredentials}"
        GITURL =  "${gitRepoURL}"
    }
        
            stages {
                stage('Checkout code') {
                    steps {
                    checkout([$class: 'GitSCM', 
                       branches: [[name: '*/$GITBRANCH']], 
                       userRemoteConfigs: [[credentialsId: '${GITCRED}', url: '${GITURL}']]    ])
                    sh 'ls; lrt'
                }
            }
        }
    }
}