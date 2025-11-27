pipeline {
	agent any

	tools {
		maven 'Maven-3.9'
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
					sh 'docker build -t test-framework .'
				}
			}
		}

		stage('Run Tests in Docker') {
			steps {
				script {
					// Exécution des tests dans le container
					sh 'docker run --name automation-tests test-framework || true'
				}
			}
		}

		stage('Extract Allure Results') {
			steps {
				script {
					// Copier les résultats hors du container
					sh 'docker cp automation-tests:/app/target/allure-results ./allure-results || true'
				}
			}
		}

		stage('Publish Allure Report') {
			steps {
				allure includeProperties: false, jdk: '', results: [[path: 'allure-results']]
			}
		}
	}

	post {
		always {
			script {
				// Nettoyage Docker
				sh 'docker rm -f automation-tests || true'
				sh 'docker rmi test-framework || true'
			}
			cleanWs()
		}
	}
}
