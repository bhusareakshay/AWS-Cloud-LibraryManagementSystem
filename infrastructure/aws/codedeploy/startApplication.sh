#! /bin/bash

cd /opt/tomcat/webapps/
chown -R centos:centos *
sudo source /etc/profile
sudo -H -u centos bash -c "sh /opt/tomcat/bin/startup.sh" 
echo "Starting tomcat complete" > /tmp/tempFile.txt
