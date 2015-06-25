now=`date +"%d_%m_%Y"`
out="/home/oscar/dumps/mongodump-"$now
mkdir $out
mongoexport --db prismanet --collection users --out $out/users.json
mongoexport --db prismanet --collection tweets --out $out/tweets.json
mongoexport --db prismanet --collection posts --out $out/posts.json
mongoexport --db prismanet --collection postsStats --out $out/postsStats.json

tar -zcvf "mongodump-"$now.tar.gz "mongodump-"$now
rm  -r $out
mongo prismanet mongoBash.js