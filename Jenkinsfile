pipeline {
    agent any

    stages {

        // ===== FRONTEND BUILD =====
        stage('Build Frontend') {
            steps {
                dir('frontend/vite-project') {
                    // Use npm ci to install dependencies from package-lock.json
                    bat 'npm ci'
                    // Build the frontend for production
                    bat 'npm run build'
                }
            }
        }

        // ===== FRONTEND DEPLOY =====
        stage('Deploy Frontend to Tomcat') {
            steps {
                // The deployment directory for the frontend is now 'recipe-frontend',
                // matching the desired URL: http://localhost:2030/recipe-frontend/
                bat '''
                echo "Deploying frontend to Tomcat webapps/recipe-frontend"
                if exist "C:\\Program Files\\Apache Software Foundation\\Tomcat 10.1\\webapps\\recipe-frontend" (
                    rmdir /S /Q "C:\\Program Files\\Apache Software Foundation\\Tomcat 10.1\\webapps\\recipe-frontend"
                )
                mkdir "C:\\Program Files\\Apache Software Foundation\\Tomcat 10.1\\webapps\\recipe-frontend"
                xcopy /E /I /Y frontend\\vite-project\\dist\\* "C:\\Program Files\\Apache Software Foundation\\Tomcat 10.1\\webapps\\recipe-frontend"
                '''
            }
        }

        // ===== BACKEND BUILD =====
        stage('Build Backend') {
            steps {
                // Correct path to the backend Maven project
                dir('backend/JobPortalS21') {
                    bat 'mvn clean package'
                }
            }
        }

        // ===== BACKEND DEPLOY =====
        stage('Deploy Backend to Tomcat') {
            steps {
                // This stage now only handles the backend deployment, without
                // affecting the frontend deployment directory.
                bat '''
                echo "Deploying backend to Tomcat webapps/jobportal.war"
                if exist "C:\\Program Files\\Apache Software Foundation\\Tomcat 10.1\\webapps\\jobportal.war" (
                    del /Q "C:\\Program Files\\Apache Software Foundation\\Tomcat 10.1\\webapps\\jobportal.war"
                )
                // The 'jobportal' folder created by the frontend deploy should not be deleted here
                rem Deleting the expanded 'jobportal' directory is handled automatically by Tomcat on redeployment
                for %%f in (backend\\JobPortalS21\\target\\*.war) do copy "%%f" "C:\\Program Files\\Apache Software Foundation\\Tomcat 10.1\\webapps\\jobportal.war"
                '''
            }
        }

    }

    post {
        success {
            echo 'Deployment Successful!'
        }
        failure {
            echo 'Pipeline Failed.'
        }
        always {
            cleanWs()
        }
    }
}
