pipeline {
	agent any

	tools {
		maven 'm3'  // Nom configuré dans Jenkins > Global Tool Configuration
		jdk 'jdk11'  // Optionnel si Maven nécessite Java
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

		stage('Run Tests') {
			steps {
				echo "Exécution des tests Cucumber"
				// Exécution de tous les tests
				bat "mvn test"
			}
		}

		stage('Publish Test Results') {
			steps {
				echo "Publication des résultats JUnit"
				// Jenkins détecte les fichiers JUnit XML générés par Maven Surefire
				junit '**/target/surefire-reports/*.xml'
			}
		}

		stage('Publish Cucumber Pretty Report') {
			steps {
				echo "Publication du rapport HTML Cucumber"
				publishHTML([allowMissing: false,
					alwaysLinkToLastBuild: true,
					keepAll: true,
					reportDir: 'target/cucumber',
					reportFiles: 'rapport.html',
					reportName: 'Cucumber Pretty Report'])
			}
		}
	}

	post {
		always {
			echo "Nettoyage de l'espace de travail"
			cleanWs()
		}
	}
}
