node {
	def scmInfo

	stage('Clone repository') {
		scmInfo = checkout scm
		currentBuild.displayName = "$currentBuild.displayName-${scmInfo.GIT_COMMIT}"
	}

	stage('Run Maven build') {
		def maven = docker.image('maven:3-jdk-11')
		maven.pull()
		maven.inside {
			sh 'mvn install'
		}
	}

	stage('Tag Docker image and push to registry') {
		docker.withRegistry('https://kitdocker.kvalitetsit.dk/') {
            image = docker.image("kvalitetsit/jonasdemo:latest")
            image.push("dev")
		}
	}
}