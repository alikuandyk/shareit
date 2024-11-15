drop table if exists comments;
drop table if exists bookings;
drop table if exists items;
drop table if exists users;

create table users
(
    id    serial primary key,
    name  varchar(100) not null,
    email varchar(100) not null unique
);

create table items
(
    id           serial primary key,
    name         varchar(100) not null,
    description  text,
    is_available boolean default true,
    owner_id     int references users (id)
);

create table bookings
(
    id         serial primary key,
    start_date timestamp with time zone not null,
    end_date   timestamp with time zone not null,
    item_id    int references items (id),
    booker_id  int references users (id),
    status     varchar(50)
);

create table comments
(
    id         serial primary key,
    text       text not null,
    item_id    int references items (id),
    author_id  int references users (id),
    created_at timestamp with time zone default current_timestamp
)

