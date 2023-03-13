INSERT INTO tb_role (authority) VALUES ('ADMIN');
INSERT INTO tb_role (authority) VALUES ('CLIENT');
INSERT INTO tb_role (authority) VALUES ('SELLER');
INSERT INTO tb_role (authority) VALUES ('MANAGER');

INSERT INTO tb_user (name, email, password, is_active) VALUES ('douglas', 'douglas@gmail.com', '123', true);
INSERT INTO tb_user (name, email, password, is_active) VALUES ('lucas', 'lucas@gmail.com', '123', true);

INSERT INTO tb_phone (user_id, phone) VALUES (1, '+5561999999999');
INSERT INTO tb_phone (user_id, phone) VALUES (2, '+5561999999999');

INSERT INTO tb_user_roles (user_id, role_id) VALUES (1, 1);
INSERT INTO tb_user_roles (user_id, role_id) VALUES (2, 2);
INSERT INTO tb_user_roles (user_id, role_id) VALUES (2, 3);
INSERT INTO tb_user_roles (user_id, role_id) VALUES (2, 4);