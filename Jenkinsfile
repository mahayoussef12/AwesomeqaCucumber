pipeline {
	agent any

	tools {
		maven 'm3'     // Maven installé sur Jenkins
		jdk 'jdk11'    // JDK installé sur Jenkins
	}

	environment {
		BROWSERSTACK_CREDENTIALS = credentials('BROWSERSTACK_CREDENTIALS')
		BROWSERSTACK_USERNAME = "${BROWSERSTACK_CREDENTIALS_USR}"
		BROWSERSTACK_ACCESS_KEY = "${BROWSERSTACK_CREDENTIALS_PSW}"
	}

	stages {

		stage('Checkout') {
			steps {
				checkout scm
			}
		}
		stage('Build') {
			steps {
				bat 'mvn compile'
			}
		}

		stage('Run Tests on BrowserStack') {
			steps {
				echo "Running tests on BrowserStack..."
				bat '''
                    mvn test ^
                    -Dbrowserstack.username=%BROWSERSTACK_USERNAME% ^
                    -Dbrowserstack.accessKey=%BROWSERSTACK_ACCESS_KEY% || exit 0
                '''
			}
		}


		stage('Generate HTML Report') {
			steps {
				cucumber(
					reportTitle: 'AwesomeQA Report',
					fileIncludePattern: 'target/cucumber-report/*.json',
					trendsLimit: 10,
					classifications: [
						[ key: 'Browser', value: 'Chrome' ],
						[ key: 'Env',     value: 'Local Jenkins' ]
					]
				)
			}
		}

		stage('Send Email Report') {
			steps {
				emailext(
					attachLog: true,
					attachmentsPattern: 'target/cucumber-report/rapport.html',
					subject: "Rapport d'exécution automatique AwesomeQA",
					body: """Bonjour,

Votre rapport quotidien d'exécution automatique est prêt.

Lien vers le build : ${env.BUILD_URL}

Cordialement,
Jenkins""",
					to: 'youssefmaha299@gmail.com'
				)
			}
		}

	}
}
