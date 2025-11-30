pipeline {
	agent any

	environment {
		BROWSERSTACK_USERNAME = credentials('BROWSERSTACK_USERNAME')
		BROWSERSTACK_ACCESS_KEY = credentials('BROWSERSTACK_ACCESS_KEY')
	}

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

		stage('Run Tests (BrowserStack)') {
			steps {
				bat 'mvn test'
			}
		}

		stage('Generate Report') {
			steps {
				cucumber buildStatus: 'UNSTABLE',
				fileIncludePattern: 'target/cucumber-report/*.json',
				reportTitle: 'AwesomeQA - BrowserStack Report'
			}
		}
	}
}
