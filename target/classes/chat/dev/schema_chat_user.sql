CREATE TABLE chat_user (
  chat_id INTEGER REFERENCES chat(id),
  user_id INTEGER REFERENCES service_user(id)
);