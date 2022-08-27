-- liquibase formatted sql
-- changeset test_table:1
create table test_table(
    id serial primary key ,
    name varchar(45) not null
)