-- liquibase formatted sql
-- changeset users:1
create table users(
    id serial primary key ,
    name varchar(45) not null,
    password varchar(255)
)