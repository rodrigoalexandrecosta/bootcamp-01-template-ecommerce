CREATE TABLE account
(
    id         uuid primary key not null,
    email      varchar(255)     not null,
    password   text             not null,
    created_at timestamptz      not null,

    constraint uk_email_unique unique (email)
);