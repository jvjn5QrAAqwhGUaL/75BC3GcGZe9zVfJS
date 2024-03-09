INSERT INTO book (isbn, name, author, intro) VALUES ('0', 'Creating your first Spring Boot web application', '030', 'Nope');
INSERT INTO book (isbn, name, author, intro) VALUES ('1', 'How to fail your interview miserably', '030', ';-;');
INSERT INTO book (isbn, name, author, intro) VALUES ('2', 'How to make a anime waifu chatbot', '030', 'llama.cpp + piper + Telegram Bot API');

INSERT INTO inventory (inventory_id, isbn) VALUES ((SELECT NEXT VALUE FOR PUBLIC.BOOK_SEQ), '0');
INSERT INTO inventory (inventory_id, isbn) VALUES ((SELECT NEXT VALUE FOR PUBLIC.BOOK_SEQ), '0');
INSERT INTO inventory (inventory_id, isbn) VALUES ((SELECT NEXT VALUE FOR PUBLIC.BOOK_SEQ), '0');
INSERT INTO inventory (inventory_id, isbn) VALUES ((SELECT NEXT VALUE FOR PUBLIC.BOOK_SEQ), '1');
INSERT INTO inventory (inventory_id, isbn) VALUES ((SELECT NEXT VALUE FOR PUBLIC.BOOK_SEQ), '1');
INSERT INTO inventory (inventory_id, isbn) VALUES ((SELECT NEXT VALUE FOR PUBLIC.BOOK_SEQ), '1');
INSERT INTO inventory (inventory_id, isbn) VALUES ((SELECT NEXT VALUE FOR PUBLIC.BOOK_SEQ), '1');
INSERT INTO inventory (inventory_id, isbn) VALUES ((SELECT NEXT VALUE FOR PUBLIC.BOOK_SEQ), '1');
INSERT INTO inventory (inventory_id, isbn) VALUES ((SELECT NEXT VALUE FOR PUBLIC.BOOK_SEQ), '2');
INSERT INTO inventory (inventory_id, isbn) VALUES ((SELECT NEXT VALUE FOR PUBLIC.BOOK_SEQ), '2');
INSERT INTO inventory (inventory_id, isbn) VALUES ((SELECT NEXT VALUE FOR PUBLIC.BOOK_SEQ), '2');
INSERT INTO inventory (inventory_id, isbn) VALUES ((SELECT NEXT VALUE FOR PUBLIC.BOOK_SEQ), '2');
INSERT INTO inventory (inventory_id, isbn) VALUES ((SELECT NEXT VALUE FOR PUBLIC.BOOK_SEQ), '2');
INSERT INTO inventory (inventory_id, isbn) VALUES ((SELECT NEXT VALUE FOR PUBLIC.BOOK_SEQ), '2');
INSERT INTO inventory (inventory_id, isbn) VALUES ((SELECT NEXT VALUE FOR PUBLIC.BOOK_SEQ), '2');
INSERT INTO inventory (inventory_id, isbn) VALUES ((SELECT NEXT VALUE FOR PUBLIC.BOOK_SEQ), '2');
INSERT INTO inventory (inventory_id, isbn) VALUES ((SELECT NEXT VALUE FOR PUBLIC.BOOK_SEQ), '2');

