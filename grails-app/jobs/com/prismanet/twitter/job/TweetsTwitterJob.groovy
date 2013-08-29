package com.prismanet.twitter.job

import twitter4j.*

class TweetsTwitterJob {

//	TweetService tweetService

	/*public static void main(String[] args) {
		new TweetsTwitterJob().execute();
	}*/

	
	//	UserJSONService userJSONService;
	//    static triggers = {
	//      simple repeatInterval: 600000l, repeatCount:-1 , startDelay: 50000000// execute job once in 5 seconds
	//	}

	def execute(StatusListener listener) {
		//		def json =loadUserTwitter("oscarcp777")
		//		UserJSON userJson = parserUser(json)
		//        userJSONService.saveUserJSON(userJson);
//		tweetService = service
		String args = "@CFKArgentina"

		
		TwitterStream twitterStream = new TwitterStreamFactory().getInstance();
		twitterStream.addListener(listener);
		ArrayList<Long> follow = new ArrayList<Long>();
		ArrayList<String> track = new ArrayList<String>();
//		for (String arg : args) {
			if (isNumericalArgument(args)) {
				for (String id : args.split(",")) {
					follow.add(Long.parseLong(id));
				}
			} else {
				track.addAll(Arrays.asList(args.split(",")));
			}
//		}
		long[] followArray = new long[follow.size()];
		for (int i = 0; i < follow.size(); i++) {
			followArray[i] = follow.get(i);
		}
		String[] trackArray = track.toArray(new String[track.size()]);

		// filter() method internally creates a thread which manipulates TwitterStream and calls these adequate listener methods continuously.
		twitterStream.filter(new FilterQuery(0, followArray, trackArray));

		println "Running Job!" + new Date()
	}

	/*def void saveTweet(Status status){
		Tweet tweet = new Tweet(content:status.getText());
		tweetService.saveFromTwitter(tweet);
	}*/

	static boolean isNumericalArgument(String argument) {
		def String arguments = argument.split(",");
		boolean isNumericalArgument = true;
		for (String arg : arguments) {
			try {
				Integer.parseInt(arg);
			} catch (NumberFormatException nfe) {
				isNumericalArgument = false;
				break;
			}
		}
		return isNumericalArgument;
	}


	//	String loadUserTwitter(def user4j){
	//		Twitter twitter = new TwitterFactory().getInstance()
	//        User user = twitter.showUser(user4j)
	//		return DataObjectFactory.getRawJSON(user)
	//	}
	//	UserJSON parserUser(def json){
	//		Gson myGson = new GsonBuilder().setDateFormat("EEE MMM dd HH:mm:ss z yyyy").serializeNulls().create()
	//		JsonReader jsonReader = new JsonReader(new StringReader(json))
	//		UserJSON userJson=myGson.fromJson(jsonReader,UserJSON)
	//		userJson.id=null //TODO el parser pisa el id auto por eso hay que reinciarlo
	//		return userJson
	//	}
}
