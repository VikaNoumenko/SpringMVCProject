CREATE TABLE service_user (
  id SERIAL PRIMARY KEY,
  login VARCHAR(20),
  password VARCHAR(20),
  name VARCHAR(20),
  age INTEGER
);