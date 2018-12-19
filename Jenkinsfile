node('base') {

   properties([pipelineTriggers([githubPush()])])

   stage('Preparation') { // for display purposes
        println "Preparation"       
	git branch: 'master', url: 'https://github.com/xpdable/jira-demo.git'

   }
	
   stage('Build') {
        println "Build"
        sh "mvn clean install -B -Dmaven.test.skip=true"
   }
   stage('Results') {
        println "Result"  
      
   }
}
