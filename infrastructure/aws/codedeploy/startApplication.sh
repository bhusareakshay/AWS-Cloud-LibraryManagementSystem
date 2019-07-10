#! /bin/bash

cd /opt/tomcat/webapps/
chown -R centos:centos *
echo "source /etc/profile" > /opt/tomcat/bin/startup_new.sh
cat /opt/tomcat/bin/startup.sh >> /opt/tomcat/bin/startup_new.sh
sudo sh /opt/tomcat/bin/startup_new.sh 
echo "Starting tomcat complete $DB_HOST" > /tmp/tempFile.txt
