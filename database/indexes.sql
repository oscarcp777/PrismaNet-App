-- mongo
db.tweets.ensureIndex({created_at:1})

--sql
create index mention_created on mention (created)
