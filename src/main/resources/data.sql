--Miejsce na dane permanentne wstawiane do bazy

insert ignore users values(1, 'admin@gmail.com', 1, 'admin', '$2a$10$.4zOJc0JxY0WlIB/Hua1puUclDg2htRZTYi6Tl72YGIR5zFSAi5Ta', 'admin', 'admin');

INSERT INTO user_role
SELECT 1, 'ADMIN'
FROM dual
WHERE (select count(*) from user_role where user_id= 1 and role='ADMIN') = 0;