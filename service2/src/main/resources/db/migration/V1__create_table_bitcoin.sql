create table if not exists bitcoin (
    id serial primary key,
    currency varchar(255) not null,
    amount decimal(20, 8) not null,
    created_at timestamp not null
);