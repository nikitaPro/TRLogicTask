DROP TABLE IF EXISTS phones;
DROP TABLE IF EXISTS user_table;
CREATE TABLE user_table (
  id BIGSERIAL PRIMARY KEY,
  name VARCHAR(40),
  lastname VARCHAR(40),
  email VARCHAR(80) UNIQUE,
  pass VARCHAR(80)
);

CREATE TABLE phones (/*User may have more then one phone number*/
  user_id BIGINT REFERENCES user_table(id) ON DELETE CASCADE,
  phone VARCHAR(17)
);