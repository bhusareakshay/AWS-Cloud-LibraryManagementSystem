# CSYE 6225 - Summer 2019

This repository contains shell scripts to create and delete AWS Virtual Private Cloud (VPC) using the AWS Command Line Interface (AWS CLI).


## Contents
- csye6225-aws-cf-create-stack.sh : This script automates the creation of a custom IPv4 VPC, having 3 public subnets, and Internet Gateway, a public route table and a route with stack name input from user
- csye6225-aws-cf-terminate-stack.sh : The script automates the deletion of a VPC Stack with the Stack Name input from user


## Prerequisites
- AWS CLI
- JQ Library for Bash


## Configuration
- AWS Cloud formation uses templates in either JSON or YAML format.  
- [Sample Reference Templates](https://aws.amazon.com/cloudformation/aws-cloudformation-templates/)
   

## Usage
1. Clone the repository into your local folder 
2. Navigate to the AWS CloudFormation folder 
   ```
   cd <local-folder-path>/infrastructure/aws/cloudformation/
   ```
3. Create new AWS Cloudformation template as per requirements and place it in same folder as the script.
   ```
   vim template.json
   ```
4. Run shell script to create a new VPC.
   ```
   sh script.sh <Template_FILE> <STACK_NAME>
   ```
