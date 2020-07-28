INSERT INTO token_table (user_no, access_token, scope, expired_at)
VALUES(1,'admintoken',null, '2037-12-31 00:00:00'),(2,'expiredtoken',null,'1994-10-20 00:00:00');

INSERT INTO user_table (user_id, password, authority)
VALUES('admin', '$2a$10$7zgt5r.9nhNmv5Yi5Tev1.g/vDhG31cZD6NInOlwC5MPjfpyDiKAq', 'admin');
