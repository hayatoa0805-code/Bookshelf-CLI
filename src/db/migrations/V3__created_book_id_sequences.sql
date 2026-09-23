CREATE TABLE book_id_sequences (
    user_id BIGINT PRIMARY KEY,
    next_book_id BIGINT NOT NULL DEFAULT 1,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE OR REPLACE FUNCTION set_book_id()
RETURNS TRIGGER AS $$
BEGIN
    INSERT INTO book_id_sequences (user_id)
    VALUES (NEW.user_id)
    ON CONFLICT (user_id) DO NOTHING;

    SELECT next_book_id
    INTO NEW.book_id
    FROM book_id_sequences
    WHERE user_id = NEW.user_id
    FOR UPDATE;

    UPDATE book_id_sequences
    SET next_book_id = next_book_id + 1
    WHERE user_id = NEW.user_id;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER books_set_book_id
BEFORE INSERT ON books
FOR EACH ROW
EXECUTE FUNCTION set_book_id();