create table car(
    id   serial primary key,
    name varchar,
    engine_id int not null references engine(id)
);