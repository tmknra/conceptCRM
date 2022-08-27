create table roles
(
    id   serial primary key,
    name varchar(255) not null unique
);

create table user_to_roles
(
    id      serial primary key,
    user_id bigint not null
            references users(id),
    role_id bigint not null
            references roles(id),
        unique (user_id, role_id)
);