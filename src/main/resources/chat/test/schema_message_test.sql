DROP TABLE IF EXISTS message;
CREATE TABLE message (
  id INTEGER AUTO_INCREMENT,
  text VARCHAR(50),
  chat_id INTEGER REFERENCES chat(id),
  author_id INTEGER REFERENCES service_user(id)
);