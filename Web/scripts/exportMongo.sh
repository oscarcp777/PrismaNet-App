#!/bin/bash
now=`date +"%d_%m_%Y"`
nowH=`date +"%d_%m_%Y_%T"`
echo "se ejecuto el bash a las :"$nowH >> /home/prismanet/dumps/log-dumps.log
out="/home/prismanet/dumps/mongodump-"$now
mkdir $out

mongoexport --db prismanet --collection users --out $out/users.json
mongoexport --db prismanet --collection tweets --out $out/tweets.json
mongoexport --db prismanet --collection posts --out $out/posts.json
mongoexport --db prismanet --collection postsStats --out $out/postsStats.json
cd "/home/prismanet/dumps"
tar -zcvf "mongodump-"$now.tar.gz $out
rm -r $out
mongo prismanet mongoBash.js
nowH=`date +"%d_%m_%Y_%T"`
echo "hasta las :"$nowH >> /home/prismanet/dumps/log-dumps.log
