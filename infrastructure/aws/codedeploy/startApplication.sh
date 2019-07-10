#! /bin/bash

echo "Test File" > /tmp/tempFile.txt
echo "Starting tomcat" > /tmp/tempFile.txt
sudo cd /opt/tomcat/webapps/
sudo chown -R centos:centos *
sudo -u centos sh /opt/tomcat/bin/startup.sh
echo "Starting tomcat complete" > /tmp/tempFile.txt
