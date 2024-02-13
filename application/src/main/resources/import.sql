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
INSERT INTO tb_product (name, price, description, measurement_type, measurement, is_available) VALUES ('Sabonete', 5.99, 'Sabonete hidratante de lavanda', 1, 1, true);
INSERT INTO tb_product (name, price, description, measurement_type, measurement, is_available) VALUES ('Shampoo', 15.49, 'Shampoo suave para cabelos secos', 1, 1, true);
INSERT INTO tb_product (name, price, description, measurement_type, measurement, is_available) VALUES ('Condicionador', 12.99, 'Condicionador revitalizante para cabelos danificados', 1, 1, true);
INSERT INTO tb_product (name, price, description, measurement_type, measurement, is_available) VALUES ('Creme para o Rosto', 25.99, 'Creme hidratante para pele sensível', 1, 1, true);
INSERT INTO tb_product (name, price, description, measurement_type, measurement, is_available) VALUES ('Escova de Dentes', 3.99, 'Escova de dentes macia com cerdas naturais', 1, 1, true);
INSERT INTO tb_product (name, price, description, measurement_type, measurement, is_available) VALUES ('Fio Dental', 2.49, 'Fio dental encerado para limpeza profunda', 1, 1, true);
INSERT INTO tb_product (name, price, description, measurement_type, measurement, is_available) VALUES ('Desodorante', 8.99, 'Desodorante roll-on antitranspirante', 1, 1, true);
INSERT INTO tb_product (name, price, description, measurement_type, measurement, is_available) VALUES ('Cotonetes', 1.99, 'Cotonetes de algodão para higiene auricular', 1, 1, true);
INSERT INTO tb_product (name, price, description, measurement_type, measurement, is_available) VALUES ('Pasta de Dentes', 4.99, 'Pasta de dente com flúor para proteção contra cáries', 1, 1, true);
INSERT INTO tb_product (name, price, description, measurement_type, measurement, is_available) VALUES ('Hidratante Corporal', 18.99, 'Hidratante corporal de manteiga de karité', 1, 1, true);
INSERT INTO tb_product (name, price, description, measurement_type, measurement, is_available) VALUES ('Protetor Solar', 21.99, 'Protetor solar FPS 50+ resistente à água', 1, 1, true);
INSERT INTO tb_product (name, price, description, measurement_type, measurement, is_available) VALUES ('Perfume', 45.99, 'Perfume floral com notas de jasmim e musk', 1, 1, true);
INSERT INTO tb_product (name, price, description, measurement_type, measurement, is_available) VALUES ('Loção Pós-Barba', 14.99, 'Loção refrescante para pós-barba', 1, 1, true);
INSERT INTO tb_product (name, price, description, measurement_type, measurement, is_available) VALUES ('Gel de Banho', 10.99, 'Gel de banho hidratante com fragrância de laranja', 1, 1, true);
INSERT INTO tb_product (name, price, description, measurement_type, measurement, is_available) VALUES ('Removedor de Maquiagem', 6.99, 'Removedor de maquiagem suave para pele sensível', 1, 1, true);
INSERT INTO tb_product (name, price, description, measurement_type, measurement, is_available) VALUES ('Creme para Mãos', 7.99, 'Creme hidratante para mãos ásperas e ressecadas', 1, 1, true);
INSERT INTO tb_product (name, price, description, measurement_type, measurement, is_available) VALUES ('Gel Antisséptico', 4.49, 'Gel antisséptico para limpeza das mãos', 1, 1, true);
INSERT INTO tb_product (name, price, description, measurement_type, measurement, is_available) VALUES ('Óleo Corporal', 23.99, 'Óleo corporal para pele seca com aroma de coco', 1, 1, true);
INSERT INTO tb_product (name, price, description, measurement_type, measurement, is_available) VALUES ('Esfoliante Facial', 11.99, 'Esfoliante facial suave com partículas de apricot', 1, 1, true);
INSERT INTO tb_product (name, price, description, measurement_type, measurement, is_available) VALUES ('Tônico Facial', 9.99, 'Tônico facial para limpeza profunda dos poros', 1, 1, true);
INSERT INTO tb_product (name, price, description, measurement_type, measurement, is_available) VALUES ('Máscara Facial', 16.99, 'Máscara facial de argila purificante', 1, 1, true);
INSERT INTO tb_product (name, price, description, measurement_type, measurement, is_available) VALUES ('Gel para Olhos', 13.99, 'Gel refrescante para área dos olhos com efeito anti-inchaço', 1, 1, true);
INSERT INTO tb_product (name, price, description, measurement_type, measurement, is_available) VALUES ('Sérum Facial', 19.99, 'Sérum facial antioxidante para revitalização da pele', 1, 1, true);
INSERT INTO tb_product (name, price, description, measurement_type, measurement, is_available) VALUES ('Batom', 8.49, 'Batom matte de longa duração', 1, 1, true);
INSERT INTO tb_product (name, price, description, measurement_type, measurement, is_available) VALUES ('Base Líquida', 12.99, 'Base líquida de cobertura média', 1, 1, true);
INSERT INTO tb_product (name, price, description, measurement_type, measurement, is_available) VALUES ('Corretivo', 7.99, 'Corretivo cremoso de alta cobertura', 1, 1, true);
INSERT INTO tb_product (name, price, description, measurement_type, measurement, is_available) VALUES ('Pó Compacto', 9.99, 'Pó compacto translúcido para acabamento matte', 1, 1, true);
INSERT INTO tb_product (name, price, description, measurement_type, measurement, is_available) VALUES ('Sombra', 4.99, 'Sombra compacta de longa duração', 1, 1, true);
INSERT INTO tb_product (name, price, description, measurement_type, measurement, is_available) VALUES ('Blush', 10.99, 'Blush em pó para efeito natural', 1, 1, true);
INSERT INTO tb_product (name, price, description, measurement_type, measurement, is_available) VALUES ('Delineador', 5.99, 'Delineador líquido de secagem rápida', 1, 1, true);
INSERT INTO tb_product (name, price, description, measurement_type, measurement, is_available) VALUES ('Kit de Pincéis', 24.99, 'Kit de pincéis profissionais para maquiagem', 1, 1, true);
INSERT INTO tb_product (name, price, description, measurement_type, measurement, is_available) VALUES ('Removedor de Esmalte', 3.49, 'Removedor de esmalte sem acetona', 1, 1, true);
INSERT INTO tb_product (name, price, description, measurement_type, measurement, is_available) VALUES ('Esmalte', 2.99, 'Esmalte de longa duração com acabamento brilhante', 1, 1, true);
INSERT INTO tb_product (name, price, description, measurement_type, measurement, is_available) VALUES ('Creme para os Pés', 8.99, 'Creme hidratante para pés ressecados', 1, 1, true);
INSERT INTO tb_product (name, price, description, measurement_type, measurement, is_available) VALUES ('Lixa de Unha', 1.49, 'Lixa de unha dupla face para acabamento suave', 1, 1, true);
INSERT INTO tb_product (name, price, description, measurement_type, measurement, is_available) VALUES ('Creme Depilatório', 7.99, 'Creme depilatório para peles sensíveis', 1, 1, true);
INSERT INTO tb_product (name, price, description, measurement_type, measurement, is_available) VALUES ('Tesoura de Manicure', 4.99, 'Tesoura de manicure com ponta arredondada', 1, 1, true);
INSERT INTO tb_product (name, price, description, measurement_type, measurement, is_available) VALUES ('Pente', 2.49, 'Pente de plástico para desembaraçar os cabelos', 1, 1, true);
INSERT INTO tb_product (name, price, description, measurement_type, measurement, is_available) VALUES ('Spray Fixador de Cabelo', 6.99, 'Spray fixador de cabelo de longa duração', 1, 1, true);
INSERT INTO tb_product (name, price, description, measurement_type, measurement, is_available) VALUES ('Gel para Cabelo', 8.99, 'Gel para cabelo com fixação forte', 1, 1, true);
INSERT INTO tb_product (name, price, description, measurement_type, measurement, is_available) VALUES ('Shampoo a Seco', 11.99, 'Shampoo a seco para limpeza rápida dos cabelos', 1, 1, true);
INSERT INTO tb_product (name, price, description, measurement_type, measurement, is_available) VALUES ('Condicionador a Seco', 13.99, 'Condicionador a seco para hidratação instantânea', 1, 1, true);
INSERT INTO tb_product (name, price, description, measurement_type, measurement, is_available) VALUES ('Máscara Capilar', 14.99, 'Máscara capilar reparadora para cabelos danificados', 1, 1, true);
INSERT INTO tb_product (name, price, description, measurement_type, measurement, is_available) VALUES ('Creme para Pentear', 9.99, 'Creme para pentear com proteção térmica', 1, 1, true);
INSERT INTO tb_product (name, price, description, measurement_type, measurement, is_available) VALUES ('Spray Protetor Térmico', 17.99, 'Spray protetor térmico para uso com secador e prancha', 1, 1, true);
INSERT INTO tb_product (name, price, description, measurement_type, measurement, is_available) VALUES ('Serum Capilar', 16.99, 'Serum capilar para reparação de pontas duplas', 1, 1, true);
INSERT INTO tb_product (name, price, description, measurement_type, measurement, is_available) VALUES ('Óleo Capilar', 19.99, 'Óleo capilar nutritivo para cabelos ressecados', 1, 1, true);
INSERT INTO tb_product (name, price, description, measurement_type, measurement, is_available) VALUES ('Cera Modeladora', 8.99, 'Cera modeladora para estilizar os cabelos', 1, 1, true);
INSERT INTO tb_product (name, price, description, measurement_type, measurement, is_available) VALUES ('Gel para Sobrancelhas', 5.99, 'Gel para sobrancelhas com efeito fixador', 1, 1, true);
INSERT INTO tb_product (name, price, description, measurement_type, measurement, is_available) VALUES ('Tintura para Cabelo', 12.49, 'Tintura para cabelo permanente em diversas cores', 1, 1, true);
INSERT INTO tb_product (name, price, description, measurement_type, measurement, is_available) VALUES ('Shampoo Anticaspa', 9.99, 'Shampoo anticaspa com fórmula suave', 1, 1, true);
INSERT INTO tb_product (name, price, description, measurement_type, measurement, is_available) VALUES ('Condicionador Anticaspa', 8.99, 'Condicionador anticaspa para hidratação do couro cabeludo', 1, 1, true);
INSERT INTO tb_product (name, price, description, measurement_type, measurement, is_available) VALUES ('Loção Hidratante para o Corpo', 20.99, 'Loção hidratante para o corpo com aroma de rosas', 1, 1, true);
INSERT INTO tb_product (name, price, description, measurement_type, measurement, is_available) VALUES ('Gel para Banho de Espuma', 14.99, 'Gel para banho de espuma com fragrância de frutas tropicais', 1, 1, true);

INSERT INTO tb_product_category (product_id, category_id) VALUES (1, 3);
INSERT INTO tb_product_category (product_id, category_id) VALUES (2, 2);

INSERT INTO tb_picture (path, type, product_id) VALUES ('https://img.itdg.com.br/tdg/images/recipes/000/000/770/323683/323683_original.jpg', 1, 1);
INSERT INTO tb_picture (path, type, product_id) VALUES ('https://karsten.vtexassets.com/arquivos/ids/171343/3582923_01.jpg?v=637256802131200000', 1, 2);

INSERT INTO tb_order (`date`, client_id) VALUES ('2024-02-03T14:05:12.024', 1);

INSERT INTO tb_ordered_item (discount, price, quantity, order_id, product_id) VALUES (2, 10.99, 1, 1, 1);
INSERT INTO tb_ordered_item (discount, price, quantity, order_id, product_id) VALUES (0, 10.99, 2, 1, 2);

INSERT INTO tb_payment (status, order_id) VALUES (0, 1);

INSERT INTO tb_money_payment (change, order_id) VALUES (0, 1);


