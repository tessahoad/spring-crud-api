CREATE TABLE books (
  book_id varchar(36) NOT NULL,
  book_title varchar(100) NOT NULL,
  book_publish_year smallint NOT NULL,
  PRIMARY KEY (book_id)
);

INSERT INTO books (book_id, book_title, book_publish_year)
VALUES
    ('72c10c03-adcb-4e53-83a4-b7c2e839480a', 'book-1', 2000),
    ('2a7c1b08-48f6-4350-99cf-a37a60690ce7', 'book-2', 2001),
    ('451312a4-aee1-4c2b-bc84-7f706cdfbeec', 'book-3', 2002),
    ('06fda117-411f-4d6b-b670-3b6655648227', 'book-4', 2003),
    ('bb4f4980-4b18-42e4-a43e-1ec13528bed6', 'book-5', 2004),
    ('f7e31e65-0ad8-43c2-8dd8-87353521e329', 'book-6', 2005),
    ('1545f1c4-b5a0-4cdd-aa6c-e44f0170c20e', 'book-7', 2006),
    ('efa05f6b-5161-444a-8413-29c9194f0b06', 'book-8', 2007),
    ('e8f159d1-175f-4b50-aff8-3032c8b7ce43', 'book-9', 2008),
    ('ef6d04ce-4caf-4ed9-8fab-faa451056425', 'book-10', 2009)