/*
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
				bat "mvn clean test "
			}
		}

		stage('Generate HTML report') {
			steps {
				cucumber buildStatus: 'UNSTABLE',
				reportTitle: 'AwesomeQA Report',
				fileIncludePattern: 'target/cucumber-report*/
/*.json',
				trendsLimit: 10,
				classifications: [
					[ key: 'Browser', value: 'Chrome' ],
					[ key: 'Env',     value: 'Local'  ]
				]
			}
		}

		stage('Send Email Report') {
			steps {
				emailext(
					attachLog: true,
					attachmentsPattern: 'target/cucumber-report/rapport.html',
					subject: "Rapport d'exécution automatique AwesomeQA",
					body: """
Bonjour,

Votre rapport quotidien d'exécution automatique est prêt.

Lien vers le build : ${env.BUILD_URL}

Cordialement,
Jenkins
""",
					to: 'youssefmaha299@gmail.com'
				)
			}
		}

	}
}
*/
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
				script {
					// Construire l'image Docker à partir du Dockerfile du projet
					bat "docker build -t ${IMAGE_NAME} ."
				}
			}
		}

		stage('Run Tests in Docker') {
			steps {
				script {
					// Exécuter le conteneur pour lancer les tests
					bat "docker run --rm --privileged -v /dev/shm:/dev/shm ${IMAGE_NAME}"
				}
			}
		}

		stage('Generate HTML Report') {
			steps {
				cucumber([
					buildStatus: 'UNSTABLE',
					fileIncludePattern: 'target/cucumber-report/*.json',
					reportTitle: 'AwesomeQA Chromium Headless Report',
					trendsLimit: 10,
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
