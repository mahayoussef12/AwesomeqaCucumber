pipeline {
	agent any

	stages {

		stage('Checkout') {
			steps {
				checkout scm
			}
		}

		stage('Build') {
			steps {
				script {
					// Compilation du projet (adapter selon Maven / Gradle)
					bat 'mvn clean install -DskipTests'
				}
			}
		}

		stage('Run Tests') {
			steps {
				script {
					// Exécution des tests (adapter selon votre projet)
					bat 'mvn test'
				}
			}
		}

		stage('Collect Allure Results') {
			steps {
				script {
					// Vérification du dossier Allure
					bat 'echo "Allure results collected from target/allure-results"'
				}
			}
		}

		stage('Publish Allure Report') {
			steps {
				allure includeProperties: false, jdk: '', results: [[path: 'target/allure-results']]
			}
		}
	}

	post {
		always {
			cleanWs()
		}
	}
}
