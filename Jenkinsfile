#!groovy
import java.text.SimpleDateFormat

// pod utilisé pour la compilation du projet
podTemplate(label: 'meltingpoc-gestion-images-pod', nodeSelector: 'medium', containers: [

        // le slave jenkins
        containerTemplate(name: 'jnlp', image: 'jenkinsci/jnlp-slave:alpine'),

        // un conteneur pour le build maven
        containerTemplate(name: 'maven', image: 'maven', privileged: true, ttyEnabled: true, command: 'cat'),

        // un conteneur pour construire les images docker
        containerTemplate(name: 'docker', image: 'tmaier/docker-compose', command: 'cat', ttyEnabled: true),

        // un conteneur pour déployer les services kubernetes
        containerTemplate(name: 'kubectl', image: 'lachlanevenson/k8s-kubectl', command: 'cat', ttyEnabled: true)],

        // montage nécessaire pour que le conteneur docker fonction (Docker In Docker)
        volumes: [hostPathVolume(hostPath: '/var/run/docker.sock', mountPath: '/var/run/docker.sock')]
) {

    node('meltingpoc-gestion-images-pod') {

        def branch = env.JOB_NAME.replaceFirst('.+/', '');

        properties([
                buildDiscarder(
                        logRotator(
                                artifactDaysToKeepStr: '1',
                                artifactNumToKeepStr: '1',
                                daysToKeepStr: '3',
                                numToKeepStr: '3'
                        )
                )
        ])

        def now = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())

        stage('checkout sources') {
            checkout scm
        }

        container('maven') {

            stage('build sources') {
                sh 'mvn clean install -DskipTests sonar:sonar -Dsonar.host.url=http://sonarqube-sonarqube:9000 -Dsonar.java.binaries=target'
            }
        }

        container('docker') {

            stage('build docker image') {

                sh 'mkdir /etc/docker'

                // le registry est insecure (pas de https)
                sh 'echo {"insecure-registries" : ["registry.k8.wildwidewest.xyz"]} > /etc/docker/daemon.json'

                withCredentials([string(credentialsId: 'nexus_password', variable: 'NEXUS_PWD')]) {

                    sh "docker login -u admin -p ${NEXUS_PWD} registry.k8.wildwidewest.xyz"
                }

                sh "tag=$now docker-compose build"

                sh "tag=$now docker-compose push"
            }
        }

        container('kubectl') {

            stage('deploy') {

                build job: "/SofteamOuest/gestion-images-run/master",
                        wait: false,
                        parameters: [[$class: 'StringParameterValue', name: 'image', value: "$now"]]

            }
        }
    }
}
