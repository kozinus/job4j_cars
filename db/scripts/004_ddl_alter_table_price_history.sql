alter table price_history add column
post_id int not null references post(id);