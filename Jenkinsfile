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

		stage('Publish Test Results') {
			steps {
				echo "Publication des résultats JUnit"
				junit '**/target/surefire-reports/*.xml'
			}
		}

		stage('Publish Cucumber Pretty Report') {
			steps {
				echo "Publication du rapport HTML Cucumber"
				publishHTML([
					allowMissing: false,
					alwaysLinkToLastBuild: true,
					keepAll: true,
					reportDir: 'target/cucumber',
					reportFiles: 'rapport.html',
					reportName: 'Cucumber Pretty Report'
				])
			}
		}
	}
}
