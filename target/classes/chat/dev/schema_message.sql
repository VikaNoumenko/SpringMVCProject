CREATE TABLE message (
  id SERIAL PRIMARY KEY,
  text VARCHAR(50),
  chat_id INTEGER REFERENCES chat(id),
  author_id INTEGER REFERENCES service_user(id)
);