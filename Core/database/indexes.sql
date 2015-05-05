-- mongo
db.tweets.ensureIndex({created_at:1})

--sql
create index mention_created on mention (created);
create index author_name on author (name);
create index author_account_name on author (account_name);
create index post_created on post (created);
create index post_post_id on post (post_id);

