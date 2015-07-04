function fileSize(size) {var i = Math.floor( Math.log(size) / Math.log(1024) );return ( size / Math.pow(1024, i) ).toFixed(2) * 1 + ' ' + ['B', 'kB', 'MB', 'GB', 'TB'][i];};
print('tweets : '+db.tweets.count());
print('posts : '+db.posts.count());
print('postsStats : '+db.postsStats.count());
print('users : '+db.users.count());
print(db.config.findOne().config);
print('config count: '+db.config.findOne().config.split(',').length);
print('fileSize de la base : '+fileSize(db.stats().fileSize));
