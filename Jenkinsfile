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

		stage('Generate HTML report') {
			cucumber buildStatus: 'UNSTABLE',
			reportTitle: 'My report',
			fileIncludePattern: '**/*.json',
			trendsLimit: 10,
			classifications: [
				[
					'key': 'Browser',
					'value': 'Chrome'
				]
			]
		}

	}
}