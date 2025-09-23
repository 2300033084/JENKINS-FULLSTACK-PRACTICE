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
                // The deployment directory for the frontend must match the backend's context path.
                // It should be 'jobportal' to match the backend WAR file name.
                bat '''
                echo "Deploying frontend to Tomcat webapps/jobportal"
                if exist "C:\\Program Files\\Apache Software Foundation\\Tomcat 10.1\\webapps\\jobportal" (
                    rmdir /S /Q "C:\\Program Files\\Apache Software Foundation\\Tomcat 10.1\\webapps\\jobportal"
                )
                mkdir "C:\\Program Files\\Apache Software Foundation\\Tomcat 10.1\\webapps\\jobportal"
                xcopy /E /I /Y frontend\\vite-project\\dist\\* "C:\\Program Files\\Apache Software Foundation\\Tomcat 10.1\\webapps\\jobportal"
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
                // The backend WAR file is renamed and deployed as jobportal.war,
                // so the frontend must be deployed to the 'jobportal' directory.
                bat '''
                if exist "C:\\Program Files\\Apache Software Foundation\\Tomcat 10.1\\webapps\\jobportal.war" (
                    del /Q "C:\\Program Files\\Apache Software Foundation\\Tomcat 10.1\\webapps\\jobportal.war"
                )
                if exist "C:\\Program Files\\Apache Software Foundation\\Tomcat 10.1\\webapps\\jobportal" (
                    rmdir /S /Q "C:\\Program Files\\Apache Software Foundation\\Tomcat 10.1\\webapps\\jobportal"
                )
                rem Copy the generated .war file from the correct target folder
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
