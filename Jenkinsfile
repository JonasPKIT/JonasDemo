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
		def image = docker.image('jonasped/jonasdemo:latest')
		image.push("dev")
	}

	stage('Tag docker image') {
	    echo scmInfo.TAG_NAME
	    if (scmInfo.TAG_NAME != null && scmInfo.TAG_NAME.beginsWith("v"))
	    {
            echo "Tagging"
            image.push(tag.substring(1))
            image.push('latest')
	    }
	}
}