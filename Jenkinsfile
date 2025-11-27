pipeline {
	agent any

	tools {
		maven 'm3'
		jdk 'jdk11'
	}

	stages {

		stage('Checkout') {
			steps {
				echo "Checkout du code"
				checkout scm
			}
		}

		stage('Build') {
			steps {
				echo "Build Maven"
				bat "mvn clean compile -DskipTests"
			}
		}

		stage('Prepare Report Directory') {
			steps {
				echo "Création dossier Cucumber si inexistant"
				bat 'mkdir target\\cucumber || echo dossier existe déjà'
			}
		}

		stage('Run Tests') {
			steps {
				echo "Exécution des tests Cucumber"
				bat "mvn test"
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
}
