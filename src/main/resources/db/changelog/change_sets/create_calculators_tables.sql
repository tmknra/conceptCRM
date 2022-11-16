create table calculators(

    id serial primary key,
    name varchar(255) unique,
    icon varchar(255) unique

);

create type field_type as enum ('input', 'select');

create table fields(

    id  serial primary key,
    name varchar(255) unique,
    tooltip varchar(255),
    code varchar(255),
    field_type field_type
);

create table calculator_related_fields(

    id serial primary key,
    calculator_id int constraint calculators references calculators(id),
    field_id int constraint fields references fields(id)

);