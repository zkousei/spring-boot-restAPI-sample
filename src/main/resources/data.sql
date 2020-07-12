INSERT INTO token_table (user_id, access_token, scope, expired_at)
VALUES('admin','admintoken',null, '2037-12-31 00:00:00'),('expired','expiredtoken',null,'1994-10-20 00:00:00');

INSERT INTO user_table (user_id, password, authority)
VALUES('admin', 'admin', 'admin');
