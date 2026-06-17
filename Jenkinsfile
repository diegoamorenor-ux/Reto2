pipeline {
    agent any

    environment {
        // Set headless execution environment variable
        HEADLESS = 'true'
    }

    stages {
        stage('Stage 0: Environment Diagnostic') {
            steps {
                echo '=== Checking Installed Environment Tools ==='
                sh 'java -version'
                sh 'mvn -version'
                sh 'python3 --version'
                sh 'pip3 --version'
            }
        }

        stage('Stage 1: Ejecución Serenity') {
            steps {
                echo '=== Running Serenity BDD (Java) Tests ==='
                dir('serenity-bdd-java') {
                    // Running clean verify executes tests and generates aggregate Serenity reports
                    sh 'mvn clean verify'
                }
            }
            post {
                always {
                    echo '=== Archiving Serenity BDD Reports ==='
                    // Archives the complete Serenity HTML report for Jenkins download/viewing
                    archiveArtifacts artifacts: 'serenity-bdd-java/target/site/serenity/**/*', allowEmptyArchive: true
                }
            }
        }

        stage('Stage 2: Ejecución Playwright') {
            steps {
                echo '=== Running Playwright (Python) Tests ==='
                dir('playwright-python') {
                    // Create python virtual environment, upgrade pip, install dependencies, and install browsers
                    sh 'python3 -m venv venv'
                    sh './venv/bin/pip install --upgrade pip'
                    sh './venv/bin/pip install -r requirements.txt'
                    sh './venv/bin/playwright install chromium'
                    
                    // Execute behave tests
                    sh './venv/bin/behave'
                }
            }
            post {
                always {
                    echo '=== Archiving Playwright Evidence ==='
                    // Archives screenshots generated on test failures
                    archiveArtifacts artifacts: 'playwright-python/reports/screenshots/**/*.png', allowEmptyArchive: true
                }
            }
        }
    }

    post {
        always {
            echo '=== Cleaning Workspace ==='
            cleanWs()
        }
    }
}
