#!/bin/bash
handle_error(){
	if [ -z "$1" ]
	  then exit
	fi
}
NAME=$1
handle_error $NAME
VPC_NAME=$NAME-csye6225-vpc
VPC_ID=$(aws ec2 describe-vpcs --query "Vpcs[?Tags[?Key=='Name']|[?Value=='$VPC_NAME']].VpcId" --output text)
echo "VPC ID is :$VPC_ID"
SUBNET_NAME1=$NAME-csye6225-subnet1
SUBNET_ID1=$(aws ec2 describe-subnets --query "Subnets[?Tags[?Key=='Name']|[?Value=='$SUBNET_NAME1']].SubnetId" --output text)
echo "SUBNETID1 is :$SUBNET_ID1"
SUBNET_NAME2=$NAME-csye6225-subnet2
SUBNET_ID2=$(aws ec2 describe-subnets --query "Subnets[?Tags[?Key=='Name']|[?Value=='$SUBNET_NAME2']].SubnetId" --output text)
echo "SUBNETID2 is :$SUBNET_ID2"
SUBNET_NAME3=$NAME-csye6225-subnet3
SUBNET_ID3=$(aws ec2 describe-subnets --query "Subnets[?Tags[?Key=='Name']|[?Value=='$SUBNET_NAME3']].SubnetId" --output text)
echo "SUBNETID3 is :$SUBNET_ID3"
IG_NAME="$NAME-csye6225-ig"
IG_ID=$(aws ec2 describe-internet-gateways --query "InternetGateways[?Tags[?Key=='Name']|[?Value=='$IG_NAME']].InternetGatewayId" --output text)
echo "IG ID: $IG_ID"

RT_NAME="$NAME-csye6225-rt"
RT_ID=$(aws ec2 describe-route-tables --query "RouteTables[?Tags[?Key=='Name']|[?Value=='$RT_NAME']].RouteTableId" --output text)
echo "Route Table ID: '$RT_ID'"







#Delete Subnets
aws ec2 delete-subnet --subnet-id $SUBNET_ID1
delete1_subnet=$?
if [ $delete1_subnet -eq 0 ]; then
	echo "Subnet 1 deleted successfully"
else
  	echo "Failed to delete subnet 1"
	exit 0
fi


aws ec2 delete-subnet --subnet-id $SUBNET_ID2
delete2_subnet=$?
if [ $delete2_subnet -eq 0 ]; then
	echo "Subnet 2 deleted successfully"
else
  	echo "Failed to delete subnet 2"
	exit 0
fi


aws ec2 delete-subnet --subnet-id $SUBNET_ID3
delete3_subnet=$?
if [ $delete3_subnet -eq 0 ]; then
	echo "Subnet 3 deleted successfully"
else
  	echo "Failed to delete subnet 3"
	exit 0
fi


#Delete Route Table
aws ec2 delete-route-table --route-table-id $RT_ID
delete_rt=$?
if [ $delete_rt -eq 0 ]; then
	echo "Route Table deleted successfully"
else
  	echo "Failed to delete route table"
	exit 0
fi

#Detach Internet Gateway
aws ec2 detach-internet-gateway --internet-gateway-id $IG_ID --vpc-id $VPC_ID
detach_ig=$?
if [ $detach_ig -eq 0 ]; then
	echo "Internet Gateway detached from VPC successfully"
else
  	echo "Failed to detach IG from VPC"
	exit 0
fi


#Delete Internet Gateway
aws ec2 delete-internet-gateway --internet-gateway-id $IG_ID
delete_ig=$?
if [ $delete_ig -eq 0 ]; then
	echo "Internet Gateway deleted successfully"
else
  	echo "Failed to delete IG"
	exit 0
fi


#Delete VPC
aws ec2 delete-vpc --vpc-id $VPC_ID
delete_vpc=$?
if [ $delete_vpc -eq 0 ]; then
	echo "VPC deleted successfully"
else
  	echo "Failed to delete VPC"
	exit 0
fi










