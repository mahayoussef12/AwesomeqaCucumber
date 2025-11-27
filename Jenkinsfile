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
				echo "Compile du projet"
				bat "mvn clean compile -DskipTests"
			}
		}

		stage('Run Tests') {
			steps {
				echo "Exécution des tests Cucumber"
				bat "mvn test"
			}
		}

		stage('Archive Cucumber HTML Report') {
			steps {
				echo "Archivage du rapport HTML dans Jenkins"
				archiveArtifacts artifacts: 'target/cucumber-report/**', fingerprint: true
			}
		}

		stage('Publish HTML Report in Jenkins') {
			steps {
				publishHTML(target: [
					allowMissing: false,
					keepAll: true,
					reportDir: 'target/cucumber-report',
					reportFiles: 'rapport.html',
					reportName: 'Cucumber HTML Report'
				])
			}
		}
	}
}
