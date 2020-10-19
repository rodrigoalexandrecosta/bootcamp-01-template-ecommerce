create table product_photo
(
    id         uuid primary key not null,
    photo_url  varchar(500)     not null,
    product_id uuid             not null,

    constraint fk_product_id foreign key (product_id) references product (id)
);