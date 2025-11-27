pipeline {
	agent any

	tools {
		maven 'm3'
	}

	stages {

		stage('Checkout') {
			steps {
				checkout scm
			}
		}

		stage('Build') {
			steps {
				bat "mvn clean install -DskipTests"
			}
		}

		stage('Run Cucumber JUnit Tests') {
			steps {
				bat "mvn test"
			}
		}

		stage('Publish JUnit Results') {
			steps {
				junit 'target/surefire-reports/*.xml'
			}
		}

		stage('Publish Cucumber HTML Report') {
			steps {
				archiveArtifacts artifacts: 'target/cucumber/rapport.html', fingerprint: true
			}
		}
	}

	post {
		always {
			cleanWs()
		}
	}
}
