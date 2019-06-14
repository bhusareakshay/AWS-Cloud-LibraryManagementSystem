# CSYE 6225 - Summer 2019

This repository contains shell scripts to create and delete AWS Virtual Private Cloud (VPC) using the AWS Command Line Interface (AWS CLI).


## Contents
- csye6225-aws-networking-setup.sh : This script automates the creation of a custom IPv4 VPC, having 3 public subnets, an Internet Gateway, a public route table and a public route with stack name input from user.
- csye6225-aws-networking-teardown.sh : This script automates the deletion of a VPC Stack with stack name input from user.


## Prerequisites
- AWS CLI
- JQ Library for Bash


## Configuration
1. AWS CLI should be installed. To install AWS CLI using pip:
- sudo pip3 install awscli --upgrade --user

## Usage
1. Clone the repository into your local folder 
2. Navigate to the AWS scripts folder 
   ```
   cd <local-folder-path>/infrastructure/aws/scripts/
   ```
3. Run shell script to create a new VPC along with all its components:
   ```
   sh csye6225-aws-networking-setup.sh <STACK_NAME>
   ```
4. Run shell script to delete all the networking resources:
   ```
   sh csye6225-aws-networking-teardown.sh <STACK_NAME>
   ```
