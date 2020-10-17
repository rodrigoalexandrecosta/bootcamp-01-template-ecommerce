create table product
(
    id          uuid primary key not null,
    name        varchar(255)     not null,
    price       numeric(9, 2)    not null,
    quantity    integer          not null,
    description varchar(1000)    not null,
    created_at  timestamptz      not null,
    category_id uuid             not null,
    account_id  uuid             not null,

    constraint fk_category_id foreign key (category_id) references category (id),
    constraint fk_account_id foreign key (account_id) references account (id)
);