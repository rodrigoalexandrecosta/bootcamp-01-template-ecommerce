create table product_opinion
(
    id uuid primary key not null,
    stars integer not null,
    title varchar(255) not null,
    description varchar(500) not null,
    account_id uuid not null,
    product_id uuid not null,

    constraint fk_account_id foreign key (account_id) references account (id),
    constraint fk_product_id foreign key (product_id) references product (id)
);