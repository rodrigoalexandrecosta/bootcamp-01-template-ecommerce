create table category
(
    id        uuid primary key not null,
    name      varchar(255)     not null,
    parent_id uuid,

    constraint uk_name_unique unique (name),
    constraint fk_parent_id foreign key (parent_id) references category (id)
);