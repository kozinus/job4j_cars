alter table files add column
file_id int references post(id);
alter table car add column
brand_id int references brands(id);