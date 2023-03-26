INSERT INTO tb_role (authority) VALUES ('ADMIN');
INSERT INTO tb_role (authority) VALUES ('CLIENT');
INSERT INTO tb_role (authority) VALUES ('SELLER');
INSERT INTO tb_role (authority) VALUES ('MANAGER');

INSERT INTO tb_user (name, email, password, is_active) VALUES ('douglas', 'douglas@gmail.com', '123', true);
INSERT INTO tb_user (name, email, password, is_active) VALUES ('lucas', 'lucas@gmail.com', '123', true);

INSERT INTO tb_phone (user_id, phone) VALUES (1, '+5561999999999');
INSERT INTO tb_phone (user_id, phone) VALUES (1, '+5561999999998');
INSERT INTO tb_phone (user_id, phone) VALUES (2, '+5561999999999');

INSERT INTO tb_user_roles (user_id, role_id) VALUES (1, 1);
INSERT INTO tb_user_roles (user_id, role_id) VALUES (2, 2);
INSERT INTO tb_user_roles (user_id, role_id) VALUES (2, 3);
INSERT INTO tb_user_roles (user_id, role_id) VALUES (2, 4);

INSERT INTO tb_category (name) VALUES ('Eletrônico');
INSERT INTO tb_category (name) VALUES ('Cama, mesa e banho');
INSERT INTO tb_category (name) VALUES ('Alimento perecível');
INSERT INTO tb_category (name) VALUES ('Alimento não perecível');
INSERT INTO tb_category (name) VALUES ('Esporte');

INSERT INTO tb_product (name, price, description, measurement_type, measurement, is_available) VALUES ('Arroz', 10.99, 'Arroz bom de gosto', 1, 12, true);
INSERT INTO tb_product (name, price, description, measurement_type, measurement, is_available) VALUES ('Toalha', 20.99, 'Toalha do bem 10 (antes do reboot)', 2, 2, true);

INSERT INTO tb_product_category (product_id, category_id) VALUES (1, 3);
INSERT INTO tb_product_category (product_id, category_id) VALUES (2, 2);

