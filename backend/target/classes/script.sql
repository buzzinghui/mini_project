-- authentication
CREATE TABLE IF NOT EXISTS user_session
(
    session_key      varchar(50)         NOT NULL,
    id              varchar(50)     NOT NULL,
    username        varchar(50)     NOT NULL,
    token           varchar(10000)  NOT NULL,
    CONSTRAINT user_session_pkey PRIMARY KEY(session_key)
);
CREATE TABLE IF NOT EXISTS user
(
    id              varchar(50)         NOT NULL,
    username        varchar(100) NOT NULL,
    email_address   varchar(50)         NOT NULL,
    role            varchar(25)  NOT NULL,
    password        varchar(100),
    created_by      varchar(50)         NOT NULL,
    created         timestamp    NOT NULL,
    updated_by      varchar(50),
    updated         timestamp,
    CONSTRAINT user_pkey PRIMARY KEY(id)
);
-- create admin password
INSERT INTO user(id, username, email_address, role, password, created_by, created)
VALUES('a8d4229d-bce3-41dd-8ff4-a89e44eefafa', 'admin', 'test@gmail.com', 'ADMIN', '$2a$06$1p1VZGW3vBvmkfHrTnsND.zf3T0bsjWpvegBT2lfDfa4H4OEDLO2q', 'SYSTEM', current_timestamp);
-- create customer password
INSERT INTO user(id, username, email_address, role, password, created_by, created)
VALUES('b8d4229d-bce3-41dd-8ff4-a89e44eefafa', 'yaiwen', 'test@gmail.com', 'CUSTOMER', '$2a$06$xIm7p7ynyDwaLNVD0MnIAuI.nbpvydQ8pPoT3mAg.UQWfyartTXda', 'SYSTEM', current_timestamp);
INSERT INTO user(id, username, email_address, role, password, created_by, created)
VALUES('b8d4229d-bce3-41dd-8ff4-a89e44eefafb', 'lenze', 'test@gmail.com', 'CUSTOMER', '$2a$06$xIm7p7ynyDwaLNVD0MnIAuI.nbpvydQ8pPoT3mAg.UQWfyartTXda', 'SYSTEM', current_timestamp);


-- product
CREATE TABLE IF NOT EXISTS product
(
    id              varchar(50)  NOT NULL,
    name            varchar(100) NOT NULL,
    description     varchar(1000),
    price           numeric      NOT NULL,
    stock           numeric      NOT NULL,
    thumbnail       varchar(10000),
    created_by      varchar(50)         NOT NULL,
    created         timestamp    NOT NULL,
    updated_by       varchar(50),
    updated         timestamp,
    CONSTRAINT product_pkey PRIMARY KEY(id)
);
-- create sample product
INSERT INTO product(id, name , description, price, stock, created_by, created)
VALUES
       ('71a6e34f-256d-4b90-bdef-0ba496333fc9', 'tiger', 'tiger toy for baby', 39.90, 10, 'SYSTEM', current_timestamp),
       ('43cb0648-01e0-4cdf-84a2-03c118ea9eb3', 'polar', 'polar toy for baby', 29.90, 10, 'SYSTEM', current_timestamp),
       ('ce121611-feff-4a91-b8b6-80c2439d02ed', 'mouse', 'mouse toy for baby', 10.90, 10, 'SYSTEM', current_timestamp),
       ('26655b32-94c5-42ae-b992-803fd8717338', 'cat', 'cat toy for baby', 13.90, 10, 'SYSTEM', current_timestamp),
       ('2765a6df-75b3-4377-b80d-7425f64271db', 'rabbit', 'rabbit toy for baby', 14.90, 1, 'SYSTEM', current_timestamp);


-- cart
CREATE TABLE IF NOT EXISTS cart
(
    id              varchar(50)  NOT NULL,
    user_id         varchar(50)  NOT NULL,
    product_id      varchar(50)  NOT NULL,
    quantity        numeric      NOT NULL,
    created_by      varchar(50)         NOT NULL,
    created         timestamp    NOT NULL,
    updated_by       varchar(50),
    updated         timestamp,
    CONSTRAINT cart_pkey PRIMARY KEY(id)
);


