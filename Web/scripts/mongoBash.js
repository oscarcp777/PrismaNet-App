
var CompactCommand= function(){
  var self = this;
  this.start = new Date(); // measure running time
  this.exec = function(){
    printDbStats(); // before dbstats
    execCommand();
    printDbStats(); // after dbstats
  }
  this.calcExecTime = function(){
    var end = new Date();
    return end.getTime() - self.start.getTime();
  }
  function printDbStats(){
    print('dbstats are');
    printjson(db.runCommand('dbstats'));
  }
  function execCommand(){
    
        var commandStart = new Date(); // measure running time per collections
        var result =db.tweets.renameCollection("old_tweets");
        var result1 =db.createCollection("tweets");
	var result4= db.tweets.ensureIndex({created_at:1});
        var result2 =db.old_tweets.drop();
        var result3=db.repairDatabase();
        var commandEnd = new Date();
        print('finish : ' + tojson(result) + tojson(result1) + tojson(result2) +  tojson(result3) + ' [' + (commandEnd.getTime() - commandStart.getTime()) + ' mesc.]');
  }
}

print('=== compact collections start.  ===');
var command = new CompactCommand();
command.exec();
print('=== compact collections done. [' + command.calcExecTime() + ' mesc.] ===');
