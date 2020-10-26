create table product_question
(
    id uuid primary key not null,
    question varchar(255) not null,
    created_at timestamptz not null,
    account_id uuid not null,
    product_id uuid not null,

    constraint fk_account_id foreign key (account_id) references account (id),
    constraint fk_product_id foreign key (product_id) references product (id)
);