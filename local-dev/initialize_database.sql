-- Create the contacts table
CREATE TABLE contacts
(
    id           SERIAL PRIMARY KEY,
    first_name   VARCHAR(50) NOT NULL,
    last_name    VARCHAR(50) NOT NULL,
    phone_number VARCHAR(20) NOT NULL,
    email        VARCHAR(100) UNIQUE,
    address      VARCHAR(255)
);


