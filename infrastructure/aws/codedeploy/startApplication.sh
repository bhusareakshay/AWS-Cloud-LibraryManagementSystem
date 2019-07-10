#! /bin/bash

echo "Test File" > /tmp/tempFile.txt
echo "Starting tomcat" > /tmp/tempFile.txt
cd /opt/tomcat/webapps/
chown -R centos:centos *
sh /opt/tomcat/bin/startup.sh
echo "Starting tomcat complete" > /tmp/tempFile.txt
