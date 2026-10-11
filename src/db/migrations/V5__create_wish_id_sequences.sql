CREATE TABLE wish_id_sequences (
    user_id BIGINT PRIMARY KEY,
    next_wish_id BIGINT NOT NULL DEFAULT 1,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

 CREATE OR REPLACE FUNCTION set_wish_id()
 RETURNS TRIGGER AS $$
 BEGIN
     INSERT INTO wish_id_sequences (user_id)
     VALUES (NEW.user_id)
     ON CONFLICT (user_id) DO NOTHING;

     SELECT next_wish_id
     INTO NEW.wish_id
     FROM wish_id_sequences
     WHERE user_id = NEW.user_id
     FOR UPDATE;

     UPDATE wish_id_sequences
     SET next_wish_id = next_wish_id + 1
     WHERE user_id = NEW.user_id;

     RETURN NEW;
 END;
 $$ LANGUAGE plpgsql;
 
 CREATE TRIGGER wish_lists_set_wish_id
 BEFORE INSERT ON wish_lists
 FOR EACH ROW
 EXECUTE FUNCTION set_wish_id();