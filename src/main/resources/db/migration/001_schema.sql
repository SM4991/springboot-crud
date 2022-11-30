CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS users (
    id uuid DEFAULT uuid_generate_v4 () PRIMARY KEY,
    name varchar(100) NOT NULL,
    mobile varchar(10) NOT NULL,
    email VARCHAR(255) NOT NULL,
    dob DATE,
    password VARCHAR(100) NOT NULL,
    role text NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);