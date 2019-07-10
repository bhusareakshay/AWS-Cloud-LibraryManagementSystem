#! /bin/bash

cd /opt/tomcat/webapps/
chown -R centos:centos *
cat "source /etc/profile" > /opt/tomcat/bin/startup_new.sh
cat /opt/tomcat/bin/startup.sh >> /opt/tomcat/bin/startup_new.sh
sudo sh /opt/tomcat/bin/startup_new.sh 
echo "Starting tomcat complete 2" > /tmp/tempFile.txt
