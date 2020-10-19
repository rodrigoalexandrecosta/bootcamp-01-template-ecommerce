create table product_characteristic
(
    id         uuid primary key not null,
    name       varchar(255)     not null,
    value      varchar(255)     not null,
    product_id uuid             not null,

    constraint fk_product_id foreign key (product_id) references product (id)
);