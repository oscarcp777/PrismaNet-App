al = new java.util.concurrent.atomic.AtomicLong(new Date().time)
username = "oscarcp777"
password = "caycay"
authString = (username + ":" + password).getBytes().encodeBase64().toString()

def th = mThread()
while(true){
   if(th == null) {
     println "\n***********************************************\n STARTING NEW THREAD in 10 seconds!!\n\n"
     Thread.sleep(10000)
     th = mThread()
   }
   if( (new Date().time - al.get()) > 5000 ){
     println "looks like thread is dead... destroying";
     th = null
   }
   Thread.sleep(1000)
}

synchronized out(message) {
    println(message)
}

def mThread(){
 return Thread.start{
  def conn = 'https://stream.twitter.com/1/statuses/sample.json?delimited=1'.toURL().openConnection()
  conn.setRequestProperty("Authorization", "Basic ${authString}")
  conn.setRequestProperty("Connection", "keep-alive")

  def count = 0;
  conn.inputStream.eachLine{
  al.set(new Date().time)
    try{
      def js = new groovy.json.JsonSlurper().parseText(it)
      def text = js
      if(js.user?.lang == 'en')
       out( " -> " + ++count + ":"  +text )
    } catch (Exception e) {
      out( e.toString() )
    }
  }
 }
}