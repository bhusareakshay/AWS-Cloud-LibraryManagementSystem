#!/bin/bash
sudo cd /opt/tomcat
sudo chmod 777 tomcat
cd webapps
sudo chmod 777 webapps
vi test.txt
sudo ./startup.sh
echo "start application called"
