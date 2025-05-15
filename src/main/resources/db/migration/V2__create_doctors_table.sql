CREATE TABLE doctors
(
    id           BIGSERIAL PRIMARY KEY,
    personal_no    VARCHAR(255) NOT NULL UNIQUE,
    first_name     VARCHAR(255) NOT NULL,
    last_name      VARCHAR(255) NOT NULL,
    specialization VARCHAR(255),
    email          VARCHAR(255) NOT NULL UNIQUE,
    phone_number   VARCHAR(255),
    address        VARCHAR(255),
    gender         CHAR(1)      NOT NULL,
    birth_date     DATE,
    active         BOOLEAN      NOT NULL,
    photo          VARCHAR(255),
    notes          VARCHAR(255),
    city           VARCHAR(255),
    country        VARCHAR(255),
    postal_code    VARCHAR(255)
);
