#!/bin/sh
echo "starting "
cf target
sleep 3
cf login --email james.b.northrop@googlemail.com
echo "any apps ?"
cf -V apps > xxx.txt
cat xxx.txt
echo "ending "
