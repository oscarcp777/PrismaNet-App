# !/bin/bash.
URL_GIT="https://github.com/sdonik"
PATH_PRISMA="/home/prismanet/PrismaNet-App"

echo -e  "1.-Bajo los ultimos cambios del repositorio"
cd $PATH_PRISMA
git pull $URL_GIT/PrismaNet-App.git

echo -e  "2.-Borro target genero el war PrismaNet"
cd $PATH_PRISMA/Web
rm -r $PATH_PRISMA/Web/target*
grails prod war

echo -e  "3.-Borro target genero el war PrismaNet-jobs"
cd $PATH_PRISMA/Jobs
rm -r $PATH_PRISMA/Jobs/target*
grails prod war
