node('base') {

   stage('Preparation') { // for display purposes
        println "Preparation"
        git credentialsId: 'xiaopen-AD', url: 'https://code-management.mercedes-benz.com.cn/XIAOPEN/jira-demo.git'

   }
   stage('Build') {
        println "Build"
        sh "mvn clean install -B"
   }
   stage('Results') {
        println "Result"
        
   }
}
