CREATE TABLE "USERS"
(
    uid IDENTITY NOT NULL PRIMARY KEY,
    email         VARCHAR(255),
    phone_num     VARCHAR(255),
    name          VARCHAR(255),
    username      VARCHAR(255),
    password      VARCHAR(255),
    registered_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_login_at TIMESTAMP,
    salt          VARCHAR(128)
);
CREATE TABLE inventory
(
    inventory_id IDENTITY NOT NULL PRIMARY KEY,
    isbn             VARCHAR(255),
    added_at         TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status           INTEGER   DEFAULT 0, -- 0: avail, 1: out, 2: pending, 3: lost, 4: broken, 5: abandoned
    expect_return_at TIMESTAMP
);
CREATE TABLE book
(
    isbn            VARCHAR(255) PRIMARY KEY,
    name            VARCHAR(255),
    author          VARCHAR(255),
    intro           VARCHAR(255),
    available_count INTEGER,
    total_count     INTEGER
);
CREATE TABLE borrow_log
(
    log_id IDENTITY NOT NULL PRIMARY KEY,
    user_id      INTEGER,
    inventory_id INTEGER,
    borrowed_at  TIMESTAMP,
    returned_at  TIMESTAMP,
    note         VARCHAR(255)
);

CREATE SEQUENCE "USERS_SEQ"
    MINVALUE 0
    MAXVALUE 999999999
    INCREMENT BY 50 NOCACHE
    NOCYCLE;

CREATE SEQUENCE "BOOK_SEQ"
    MINVALUE 0
    MAXVALUE 999999999
    INCREMENT BY 1 NOCACHE
    NOCYCLE;

CREATE SEQUENCE "INVENTORY_SEQ"
    MINVALUE 0
    MAXVALUE 999999999
    INCREMENT BY 1 NOCACHE
    NOCYCLE;

CREATE SEQUENCE "BORROW_LOG_SEQ"
    MINVALUE 0
    MAXVALUE 999999999
    INCREMENT BY 1 NOCACHE
    NOCYCLE;