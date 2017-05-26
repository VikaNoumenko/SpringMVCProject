DROP TABLE IF EXISTS chat;
CREATE TABLE chat (
  id INTEGER AUTO_INCREMENT,
  creator_id INTEGER REFERENCES service_user(id),
  name VARCHAR(20)
);

