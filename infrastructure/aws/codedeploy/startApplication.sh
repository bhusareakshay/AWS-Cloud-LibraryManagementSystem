#! /bin/bash

cd /opt/tomcat/webapps/
chown -R centos:centos *
sudo source /etc/profile
sudo sh /opt/tomcat/bin/startup.sh 
echo "Starting tomcat complete 2" > /tmp/tempFile.txt
