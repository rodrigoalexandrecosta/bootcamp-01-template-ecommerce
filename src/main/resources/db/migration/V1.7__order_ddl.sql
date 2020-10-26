create table orders
(
    id uuid primary key not null,
    quantity integer not null,
    total_price numeric(9, 2) not null,
    payment_gateway varchar(255) not null,
    status varchar(255) not null,
    account_id uuid not null,
    product_id uuid not null
);