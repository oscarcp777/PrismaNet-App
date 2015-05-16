# !/bin/bash.
PATH_TOMCAT="/home/prismanet/tomcat-7"
PATH_WEBAPPS="/home/prismanet/tomcat-7/webapps"
PATH_PRISMA="/home/prismanet/PrismaNet-App"
PATH_BACKUP="/home/prismanet/backup-war"


echo -e  "1.- detengo el tomcat"
rm $PATH_TOMCAT/pids/*
cd $PATH_TOMCAT/bin
./shutdown.sh 30
sleep 3

echo -e  "2.- backupeo los war del tomcat "
now=`date +"%m_%d_%Y"`
cp  $PATH_WEBAPPS/ROOT.war $PATH_BACKUP/ROOT-$now.war
cp  $PATH_WEBAPPS/PrismaNet-jobs.war $PATH_BACKUP/PrismaNet-jobs-$now.war

echo -e  "3.- borro los log y los war" 

rm -r $PATH_TOMCAT/logs/*
rm -r $PATH_WEBAPPS/*

echo -e  "4.- mato el proceso prismanet.jar"
ID_PROCESS=`ps ax | grep "prismanet-twitter-api.jar"|grep -v "grep"|awk '{print $1}'`
kill $ID_PROCESS

echo -e  "5.- muevo el war al tomcat ROOT"
cp $PATH_PRISMA/Web/target/PrismaNet-0.1.war $PATH_WEBAPPS/ROOT.war

echo -e  "6.- muevo el war al tomcat jobs"
cp $PATH_PRISMA/Jobs/target/PrismaNet-jobs-0.1.war $PATH_WEBAPPS/PrismaNet-jobs.war

echo -e  "7.- copio la presentacion al tomcat"
cp -r $PATH_PRISMA/Web/presentacion  $PATH_WEBAPPS/demo

echo -e  "levanto el tomcat"
cd $PATH_TOMCAT/bin
./startup.sh
echo -e  "FIN tomcat corriendo"

