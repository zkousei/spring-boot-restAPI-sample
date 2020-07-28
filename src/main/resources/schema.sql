CREATE TABLE IF NOT EXISTS
user_table (
  user_no INT AUTO_INCREMENT PRIMARY KEY,
  user_id VARCHAR(100),
  password VARCHAR(100),
  authority VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS
token_table (
  user_no INT NOT NULL,
  access_token VARCHAR(100) PRIMARY KEY,
  scope VARCHAR(50),
  expired_at DATETIME

)