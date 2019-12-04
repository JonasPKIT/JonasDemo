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

	stage('Push dev image to registry') {
		def image = docker.image('kvalitetsit/jonasdemo:latest')
		image.push("jonasped/jonasdemo:dev")
	}

	stage('Push version tag to registry') {
	    if (env.TAG_NAME != null && env.TAG_NAME.startsWith("v"))
	    {
            echo "Tagging"
            def image = docker.image('jonasped/jonasdemo:latest')
            image.push("dev")
            image.push(env.TAG_NAME.substring(1))
            image.push('latest')
	    }
	}
}