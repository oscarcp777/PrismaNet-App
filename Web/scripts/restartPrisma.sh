# !/bin/bash.
PATH_TOMCAT="/home/prismanet/tomcat-7"
PATH_WEBAPPS="/home/prismanet/tomcat-7/webapps"
PATH_PRISMA="/home/prismanet/Prisma-net"

echo -e  "1.- detengo el tomcat"
cd $PATH_TOMCAT/bin
./shutdown.sh 30
sleep 3

echo -e  "2.- borro los log" 
rm -r $PATH_TOMCAT/logs/*


echo -e  "3.- mato el proceso prismanet.jar"
ID_PROCESS=`ps ax | grep "prismanet-twitter-api.jar"|grep -v "grep"|awk '{print $1}'`
kill $ID_PROCESS

echo -e  "levanto el tomcat"
cd $PATH_TOMCAT/bin
./startup.sh
echo -e  "FIN tomcat corriendo"

