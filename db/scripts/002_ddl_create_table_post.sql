create table post(
    id   serial primary key,
    description varchar,
    created timestamp,
    user_id int not null references auto_user(id)
);