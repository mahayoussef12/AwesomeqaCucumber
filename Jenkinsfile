pipeline {
	agent any

	stages {

		stage('Checkout') {
			steps {
				checkout scm
			}
		}

		stage('Build Docker Image') {
			steps {
				script {
					bat 'docker build -t test-framework .'
				}
			}
		}

		stage('Run Tests in Docker') {
			steps {
				script {
					// Exécution des tests dans le container
					bat 'docker run --name automation-tests test-framework || true'
				}
			}
		}

		stage('Extract Allure Results') {
			steps {
				script {
					// Copier les résultats hors du container
					bat 'docker cp automation-tests:/app/target/allure-results ./allure-results || true'
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
				bat 'docker rm -f automation-tests || true'
				bat 'docker rmi test-framework || true'
			}
			cleanWs()
		}
	}
}
