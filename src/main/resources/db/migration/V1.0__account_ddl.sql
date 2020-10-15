CREATE TABLE account (
    id uuid primary key not null,
    login varchar(255) not null unique,
    password text not null,
    created_at timestamptz not null
);