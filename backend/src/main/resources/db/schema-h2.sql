drop table if exists user;

create table user (
    id bigint not null auto_increment,
    name varchar(255),
    email varchar(255) not null,
    password varchar(255),
    birth date,
    phone_number varchar(14),
    profile_image varchar(255),
    role varchar(255) not null,
    created_at datetime(6) not null,
    modified_at datetime(6) not null,
    child_id BIGINT NULL,
    primary key (id)
);

alter table user
    add CONSTRAINT FK_USER_ON_CHILD FOREIGN KEY (child_id) REFERENCES user (id);

create table persistent_logins (
    username varchar(64) not null,
    series varchar(64) not null,
    token varchar(64) not null,
    last_used timestamp not null,
    primary key (series)
);

create table monitor_room (
    id bigint auto_increment NOT NULL,
    created_at datetime NOT NULL,
    modified_at datetime NOT NULL,
    user_id bigint NULL,
    roomuuid VARCHAR(255) NULL,
    CONSTRAINT pk_monitorroom PRIMARY KEY (id)
);

alter table monitor_room
    add CONSTRAINT uc_monitorroom_roomuuid UNIQUE (roomuuid);

alter table monitor_room
    add CONSTRAINT FK_MONITORROOM_ON_USER FOREIGN KEY (user_id) REFERENCES user (id);