CREATE TABLE IF NOT EXISTS
user_table (
  user_id VARCHAR(50) PRIMARY KEY,
  password VARCHAR(50),
  authority VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS
token_table (
  user_id VARCHAR(50),
  access_token VARCHAR(50) PRIMARY KEY,
  scope VARCHAR(50),
  expired_at DATETIME

)