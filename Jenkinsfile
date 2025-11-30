pipeline {
	agent any

	environment {
		BROWSERSTACK_CREDENTIALS = credentials('BROWSERSTACK_CREDENTIALS')
		BROWSERSTACK_USERNAME = "${BROWSERSTACK_CREDENTIALS_USR}"
		BROWSERSTACK_ACCESS_KEY = "${BROWSERSTACK_CREDENTIALS_PSW}"
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
		stage('Build') {
			steps {
				bat 'mvn -B compile'
			}
		}

		stage('Run Tests on BrowserStack') {
			steps {
				echo "Running tests on BrowserStack..."
				bat '''
                    mvn -B test ^
                    -Dbrowserstack.username=%BROWSERSTACK_USERNAME% ^
                    -Dbrowserstack.accessKey=%BROWSERSTACK_ACCESS_KEY%
                '''
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
