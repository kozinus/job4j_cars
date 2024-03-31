alter table post add column
file_id int references files(id);
alter table car add column
brand_id int references brands(id);