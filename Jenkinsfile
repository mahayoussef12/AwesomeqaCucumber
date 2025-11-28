pipeline {
	agent any

	tools {
		maven 'm3'
		jdk 'jdk11'
	}

	stages {

		stage('Checkout') {
			steps {
				checkout scm
			}
		}

		stage('Build') {
			steps {
				bat "mvn clean test"
			}
		}

		stage('Generate HTML report') {
			steps {
				cucumber buildStatus: 'UNSTABLE',
				reportTitle: 'AwesomeQA Report',
				fileIncludePattern: 'target/cucumber-report/*.json',
				trendsLimit: 10,
				classifications: [
					[ key: 'Browser', value: 'Chrome' ],
					[ key: 'Env', value: 'Local' ]
				]
			}
		}
		stage('Send Email Report') {
		steps
				{
			emailext attachLog: true,
			attachBuildLog: true,
			attachmentsPattern: 'target/cucumber-report/rapport.html',
			subject: "Rapport d'exécution automatiquAwesomeQA",
			body: """
Bonjour,

Votre rapport quotidien d'exécution automatique est prêt.

Lien vers le build : ${env.BUILD_URL}

Cordialementjenkins""" ,
					to: 'youssefmaha299@gmail.com'
		}
	}
}
}
