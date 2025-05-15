CREATE TABLE users
(
    id        BIGSERIAL PRIMARY KEY,
    username VARCHAR(50)  NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    name     VARCHAR(50)  NOT NULL,
    surname  VARCHAR(50)  NOT NULL,
    role     VARCHAR(10)  NOT NULL,
    email    VARCHAR(50)  NOT NULL UNIQUE,
    phone    VARCHAR(20)  NOT NULL
);
