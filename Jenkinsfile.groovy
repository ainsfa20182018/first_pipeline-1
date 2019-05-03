node{
    properties([parameters([string(defaultValue: 'IP', description: 'Where should I build? ', name: 'Environment', trim: true)]), pipelineTriggers([pollSCM('* * * * *')])])

    stage("Pull Repo"){
        git 'git@github.com:farrukh90/cool_website.git'
    }
    stage("Webserver Install"){
        sh "ssh  ec2-user@34.254.229.99       sudo yum install httpd -y"
    }
    stage("Index file"){
        sh "scp  index.html          ec2-user@34.254.229.99:/tmp"
    }
    stage("Move index"){
        sh "ssh  ec2-user@34.254.229.99     sudo mv /tmp/index.html        /var/www/html/index.html"
    }
    stage("Restart webserver"){
        sh "ssh  ec2-user@34.254.229.99      sudo systemctl restart httpd "
    }
}
