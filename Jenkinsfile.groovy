node{
    properties([parameters([string(defaultValue: 'IP', description: 'Where should I build? ', name: 'Environment', trim: true)]), pipelineTriggers([pollSCM('* * * * *')])])

    stage("Pull Repo"){
        git 'git@github.com:farrukh90/cool_website.git'
    }
    stage("Webserver Install"){
        sh "ssh  ec2-user@${Environment}       sudo yum install httpd -y"
    }
    stage("Index file"){
        sh "scp  index.html          ec2-user@${Environment}:/tmp"
    }
    stage("Move index"){
        sh "ssh  ec2-user@${Environment}     sudo mv /tmp/index.html        /var/www/html/index.html"
    }
    stage("Restart webserver"){
        sh "ssh  ec2-user@${Environment}      sudo systemctl restart httpd "
    }
}
