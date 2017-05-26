CREATE TABLE chat (
  id SERIAL PRIMARY KEY,
  creator_id INTEGER REFERENCES service_user(id),
  name VARCHAR(20)
);

