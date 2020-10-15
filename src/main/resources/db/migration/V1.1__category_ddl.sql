CREATE TABLE category
(
    id   uuid         not null,
    name varchar(255) not null,

    constraint uk_name_unique unique (name)
);