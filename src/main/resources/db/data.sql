insert into user_tb(username, password, email, created_at) values('Kenneth', '1234', 'Kenneth@nate.com', now());
insert into user_tb(username, password, email, created_at) values('Minsu', '1234', 'Minsu@nate.com', now());
insert into user_tb(username, password, email, created_at) values('Kim', '1234', 'Kim@nate.com', now());

insert into board_tb(title, content, user_id, created_at) values('제목1','내용1',1,now());
insert into board_tb(title, content, user_id, created_at) values('제목2','내용2',1,now());
insert into board_tb(title, content, user_id, created_at) values('제목3','내용3',2,now());
insert into board_tb(title, content, user_id, created_at) values('제목4','내용4',3,now());

INSERT INTO reply_tb(comment, created_at) VALUES('댓글 1', now());
INSERT INTO reply_tb(comment, created_at) VALUES('댓글 2', now());
INSERT INTO reply_tb(comment, created_at) VALUES('댓글 3', now());
INSERT INTO reply_tb(comment, created_at) VALUES('댓글 4', now());