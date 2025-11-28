pipeline {
	agent any

	tools {
		maven 'm3'
		jdk 'jdk11'
	}

	environment {
		CHROME_DRIVER_DIR = "${WORKSPACE}/drivers"
	}

	stages {
		stage('Checkout') {
			steps {
				checkout scm
			}
		}

		stage('Setup ChromeDriver') {
			steps {
				script {
					// Créer le dossier pour ChromeDriver
					bat "mkdir \"${env.CHROME_DRIVER_DIR}\" || exit 0"

					// Windows : récupérer la version de Chrome installée
					def chromeVersionOutput = bat(script: 'reg query "HKEY_CURRENT_USER\\Software\\Google\\Chrome\\BLBeacon" /v version', returnStdout: true).trim()
					def chromeVersion = (chromeVersionOutput =~ /([0-9]+\\.[0-9]+\\.[0-9]+\\.[0-9]+)/)[0][0]
					echo "Chrome version detected: ${chromeVersion}"

					// Télécharger ChromeDriver correspondant
					def driverVersion = chromeVersion.split("\\.")[0]  // prend le major version
					def driverUrl = "https://chromedriver.storage.googleapis.com/LATEST_RELEASE_${driverVersion}"
					def latestDriver = bat(script: "curl -s ${driverUrl}", returnStdout: true).trim()
					echo "Downloading ChromeDriver version: ${latestDriver}"

					bat """
                    curl -L -o ${env.CHROME_DRIVER_DIR}/chromedriver_win32.zip https://chromedriver.storage.googleapis.com/${latestDriver}/chromedriver_win32.zip
                    powershell -Command "Expand-Archive -Path '${env.CHROME_DRIVER_DIR}/chromedriver_win32.zip' -DestinationPath '${env.CHROME_DRIVER_DIR}' -Force"
                    """

					// Ajouter au PATH
					env.PATH = "${env.CHROME_DRIVER_DIR};${env.PATH}"
				}
			}
		}

		stage('Build & Run Selenium Tests') {
			steps {
				bat 'mvn clean test'
			}
		}

		stage('Generate HTML Report') {
			steps {
				cucumber buildStatus: 'UNSTABLE',
				reportTitle: 'AwesomeQA Report',
				fileIncludePattern: 'target/cucumber-report/*.json',
				trendsLimit: 10,
				classifications: [
					[ key: 'Browser', value: 'Chrome' ],
					[ key: 'Env',     value: 'Jenkins' ]
				]
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
