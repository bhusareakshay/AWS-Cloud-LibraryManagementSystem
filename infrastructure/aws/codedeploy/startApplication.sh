#! /bin/bash

sudo /opt/aws/amazon-cloudwatch-agent/bin/amazon-cloudwatch-agent-ctl \
    -a fetch-config \
    -m ec2 \
    -c file:/opt/cloudwatch-config.json \
    -s
sudo systemctl restart amazon-cloudwatch-agent
cd /opt/tomcat/webapps/
chown -R centos:centos *
echo "source /etc/profile" > /opt/tomcat/bin/startup_new.sh
cat /opt/tomcat/bin/startup.sh >> /opt/tomcat/bin/startup_new.sh
sudo sh /opt/tomcat/bin/startup_new.sh 
echo "Starting tomcat complete $DB_HOST" > /tmp/tempFile.txt

