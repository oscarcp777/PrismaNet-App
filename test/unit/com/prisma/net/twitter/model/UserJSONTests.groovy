package com.prisma.net.twitter.model






import grails.test.mixin.*

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.stream.JsonReader
import com.prismanet.model.twitter.user.UserJSON

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(UserJSON)
class UserJSONTests {

	UserJSON user;
	def json="""
{
    "contributors_enabled": false,
    "created_at": "Fri May 20 13:37:04 +0000 2011",
    "default_profile": true,
    "default_profile_image": false,
    "description": "",
    "entities": {
        "description": {
            "urls": []
        }
    },
    "favourites_count": 8,
    "follow_request_sent": false,
    "followers_count": 14,
    "following": false,
    "friends_count": 107,
    "geo_enabled": false,
    "id": 302026952,
    "id_str": "302026952",
    "is_translator": false,
    "lang": "es",
    "listed_count": 0,
    "location": "capital federal ",
    "name": "Oscar Caceres",
    "notifications": false,
    "profile_background_color": "C0DEED",
    "profile_background_image_url": "http://a0.twimg.com/images/themes/theme1/bg.png",
    "profile_background_image_url_https": "https://si0.twimg.com/images/themes/theme1/bg.png",
    "profile_background_tile": false,
    "profile_image_url": "http://a0.twimg.com/profile_images/2670960637/379fb2e81c5d384d551b304edcfa1242_normal.jpeg",
    "profile_image_url_https": "https://si0.twimg.com/profile_images/2670960637/379fb2e81c5d384d551b304edcfa1242_normal.jpeg",
    "profile_link_color": "0084B4",
    "profile_sidebar_border_color": "C0DEED",
    "profile_sidebar_fill_color": "DDEEF6",
    "profile_text_color": "333333",
    "profile_use_background_image": true,
    "protected": false,
    "screen_name": "oscarcp777",
    "status": {
        "contributors": null,
        "coordinates": null,
        "created_at": "Tue Jun 25 01:07:04 +0000 2013",
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
        "id": 349332884345196544,
        "id_str": "349332884345196544",
        "in_reply_to_screen_name": "RichyDubini",
        "in_reply_to_status_id": 349331778294652928,
        "in_reply_to_status_id_str": "349331778294652928",
        "in_reply_to_user_id": 1523291540,
        "in_reply_to_user_id_str": "1523291540",
        "lang": "es",
        "place": null,
        "retweet_count": 0,
        "retweeted": false,
        "source": "<a href=\\"http://twitter.com/download/android\\" rel=\\"nofollow\\">Twitter for Android</a>",
        "text": "@RichyDubini  estamos muy orgullosa de vos vieja sos un grande !!!",
        "truncated": false
    },
    "statuses_count": 9,
    "time_zone": null,
    "url": null,
    "utc_offset": null,
    "verified": false
}
"""
	@Before
	void setUp() {
		Gson myGson = new GsonBuilder().setDateFormat("EEE MMM dd HH:mm:ss z yyyy").serializeNulls().create()
		JsonReader jsonReader = new JsonReader(new StringReader(json))
		user = myGson.fromJson(jsonReader,UserJSON)
	}
	void testParser() {


		assert user.id==302026952
		assert user.lang =="es"
		assert user.favourites_count==8
		assert user.statuses_count==9
		assert user.follow_request_sent==false
		assert user.entities.description.urls.size()==0
		assert user.screen_name=="oscarcp777"
		assert user.status.entities.urls.size()==1
		assert user.status.entities.user_mentions.size()==1;
		assert user.status.entities.hashtags[0].text=="lol"

	}
	void testSave() {

//		mockDomain(UserJSON)
		UserJSON s= new UserJSON(statuses_count:9)
		s.save()

		assert s.id != null

		s = UserJSON.get(s.id)

		assert s != null
		assert s.statuses_count == 9
	}
}
