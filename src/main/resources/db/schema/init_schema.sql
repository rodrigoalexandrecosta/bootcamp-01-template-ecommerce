CREATE USER fleamarket WITH password 'fleamarket';

CREATE SCHEMA fleamarketapi_service;
ALTER USER fleamarket SET search_path = 'fleamarketapi_service, public';

GRANT USAGE, CREATE ON SCHEMA fleamarketapi_service TO fleamarket;
GRANT ALL ON ALL TABLES IN SCHEMA fleamarketapi_service TO fleamarket;
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA fleamarketapi_service TO fleamarket;
GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA fleamarketapi_service TO fleamarket;
GRANT EXECUTE ON ALL FUNCTIONS IN SCHEMA fleamarketapi_service TO fleamarket;

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
GRANT EXECUTE ON FUNCTION uuid_generate_v4() TO fleamarket;