create user fleamarket with password 'fleamarket';

create schema fleamarketapi_service;
alter user fleamarket set search_path = 'fleamarketapi_service, public';

grant usage, create on schema fleamarketapi_service to fleamarket;
grant all on all tables in schema fleamarketapi_service to fleamarket;
grant all privileges on all tables in schema fleamarketapi_service to fleamarket;
grant all privileges on all sequences in schema fleamarketapi_service to fleamarket;
grant execute on all functions in schema fleamarketapi_service to fleamarket;