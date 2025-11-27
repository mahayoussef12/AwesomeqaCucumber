pipeline {
	agent any

	tools {
		maven 'm3'   // Nom configuré dans Global Tool Configuration
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

		stage('Run Tests') {
			steps {
				bat "mvn test"
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
