drop table if exists user;

create table user (
    id bigint not null auto_increment,
    name varchar(255),
    email varchar(255) not null,
    password varchar(255),
    birth date,
    profile_image varchar(255),
    role varchar(255) not null,
    created_at datetime(6) not null,
    modified_at datetime(6) not null,
    primary key (id)
);