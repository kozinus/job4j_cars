alter table price_history add column
post_id int references auto_post(id);