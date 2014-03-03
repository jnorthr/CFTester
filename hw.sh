#!/bin/sh
echo "starting "
cf target
sleep 3
cf login --email james.northrop@orange.fr 
echo "any apps ?"
cf -V apps > xxx.txt
cat xxx.txt
echo "ending "
