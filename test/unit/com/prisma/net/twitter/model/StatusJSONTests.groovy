package com.prisma.net.twitter.model

import grails.test.mixin.TestFor

import org.junit.Before

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.stream.JsonReader
import com.prismanet.model.twitter.tweet.StatusJSON

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(StatusJSON)
class StatusJSONTests {
	StatusJSON statusUser;
	def json="""
    {
        "contributors": null,
        "coordinates": null,
        "created_at": "Tue Aug 06 00:52:58 +0000 2013",
        "entities": {
            "hashtags":[{"indices":[32,36],"text":"lol"}],
            "symbols": [{  "text": "PEP","indices": [  114,118] },
                        {"text": "COKE","indices": [128,133] } ],
            "urls":[{"indices":[32,52], "url":"http://t.co/IOwBrTZR", "display_url":"youtube.com/watch?v=oHg5SJ\u2026", "expanded_url":"http://www.youtube.com/watch?v=oHg5SJYRHA0"}],
            "user_mentions": [
                {
                    "id": 1523291540,
                    "id_str": "1523291540",
                    "indices": [
                        0,
                        12
                    ],
                    "name": "Richard Dubini",
                    "screen_name": "RichyDubini"
                }
            ]
        },
        "favorite_count": 0,
        "favorited": false,
        "geo": null,
        "id": 364549624725778432,
        "id_str": "364549624725778432",
        "in_reply_to_screen_name": null,
        "in_reply_to_status_id": null,
        "in_reply_to_status_id_str": null,
        "in_reply_to_user_id": null,
        "in_reply_to_user_id_str": null,
        "lang": "es",
        "place": null,
        "retweet_count": 0,
        "retweeted": false,
        "text": "Out a 25 minutos de terminar el d\u00eda pozo de 180k (avg 50K) AA vs 88 vs TT all in pre flop, me metieron el 8 en turn. Bendito re entry!",
        "truncated": false,
         "source": "<a href=\\"http://twitter.com/download/iphone\\" rel=\\"nofollow\\">Twitter for iPhone</a>"
    }
"""
	@Before
	void setUp() {
		Gson myGson = new GsonBuilder().setDateFormat("EEE MMM dd HH:mm:ss z yyyy").serializeNulls().create()
		JsonReader jsonReader = new JsonReader(new StringReader(json))
		statusUser = myGson.fromJson(jsonReader,StatusJSON)
	}
	void testParser() {
		

		assert statusUser.id==364549624725778432
		assert statusUser.lang =="es"
		assert statusUser.retweeted==false
		assert statusUser.retweet_count==0
		
		assert statusUser.entities.hashtags.size()==1
		assert statusUser.entities.symbols.size()==2
		assert statusUser.entities.urls.size()==1
		assert statusUser.entities.user_mentions.size()==1;
		assert statusUser.entities.hashtags[0].text=="lol"
		
	}
	void testSave() {
		
		 mockDomain(StatusJSON)
		 StatusJSON s= new StatusJSON(lang:"es")
        s.save()

        assert s.id != null

        s = StatusJSON.get(s.id)

        assert s != null
		assert s.lang == "es"
	}
}
