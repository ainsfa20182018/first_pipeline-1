node{
    stage("Pull Repo"){
        git 'git@github.com:farrukh90/cool_website.git'
    }
    stage("Webserver Install"){
        sh "ssh  ec2-user@34.254.229.99       sudo yum install httpd -y"
    }
    stage("Index file"){
        sh "scp  index.html          ec2-user@34.254.229.99:/tmp"
    }
}
