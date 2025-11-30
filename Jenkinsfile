pipeline {
	agent any

	tools {
		maven 'm3'     // Maven installé sur Jenkins
		jdk 'jdk11'    // JDK installé sur Jenkins
	}


	stages {

		stage('Checkout') {
			steps {
				checkout scm
			}
		}

		stage('Build & Run Tests') {
			steps {
				// Exécute directement les tests Maven
				bat "mvn clean test"
			}
		}

		stage('Generate HTML Report') {
			steps {
				cucumber(
					buildStatus: 'UNSTABLE',
					reportTitle: 'AwesomeQA Report',
					fileIncludePattern: 'target/cucumber-report/*.json',
					trendsLimit: 10,
					classifications: [
						[ key: 'Browser', value: 'Chrome' ],
						[ key: 'Env',     value: 'Local Jenkins' ]
					]
				)
			}
		}

		stage('Send Email Report') {
			steps {
				emailext(
					attachLog: true,
					attachmentsPattern: 'target/cucumber-report/rapport.html',
					subject: "Rapport d'exécution automatique AwesomeQA",
					body: """Bonjour,

Votre rapport quotidien d'exécution automatique est prêt.

Lien vers le build : ${env.BUILD_URL}

Cordialement,
Jenkins""",
					to: 'youssefmaha299@gmail.com'
				)
			}
		}

	}
}
