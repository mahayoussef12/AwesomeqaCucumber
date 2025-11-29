pipeline {
	agent any

	environment {
		IMAGE_NAME = 'awesomeqa:latest'
	}

	stages {
		stage('Checkout') {
			steps {
				checkout scm
			}
		}

		stage('Build Docker Image') {
			steps {
				bat """
                docker build -t %IMAGE_NAME% .
                """
			}
		}

		stage('Run Tests in Docker') {
			steps {

				bat """
                docker run --rm ^
                  -v "C:\\jenkins\\.m2:/root/.m2" ^
                  -v "%WORKSPACE%:/app" ^
                  %IMAGE_NAME%
                """
			}
		}

		stage('Generate HTML Report') {
			steps {
				cucumber([
					buildStatus: 'UNSTABLE',
					fileIncludePattern: 'target/cucumber-report/*.json',
					reportTitle: 'AwesomeQA Chromium Headless Report',
					classifications: [
						[ key: 'Browser', value: 'Chromium Headless' ],
						[ key: 'Env',     value: 'Docker/Jenkins' ]
					]
				])
			}
		}

		stage('Send Email Report') {
			steps {
				emailext(
					attachLog: true,
					attachmentsPattern: 'target/cucumber-report/rapport.html',
					subject: "Rapport d'exécution automatique AwesomeQA",
					body: """Bonjour,

Le rapport d'exécution automatique est prêt.

Lien vers le build : ${env.BUILD_URL}

Cordialement,
Jenkins""",
					to: 'youssefmaha299@gmail.com'
				)
			}
		}
	}

	post {
		always {
			// Archive les rapports HTML et JSON
			archiveArtifacts artifacts: 'target/cucumber-report/**', allowEmptyArchive: true
			junit 'target/surefire-reports/*.xml'
		}
	}
}
