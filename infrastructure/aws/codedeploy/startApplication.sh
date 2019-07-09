#!/bin/bash
sudo cd /opt/tomcat
chmod 777 tomcat
cd webapps
chmod 777 webapps
vi test.txt
sudo ./startup.sh
echo "start application called"
