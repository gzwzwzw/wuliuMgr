-- 删除旧数据（如果存在）
DELETE FROM delivery_task;
DELETE FROM order_item;
DELETE FROM orders;
DELETE FROM inventory;
DELETE FROM freight_rule;
DELETE FROM driver;
DELETE FROM vehicle;
DELETE FROM warehouse;
DELETE FROM product;
DELETE FROM customer;

-- 重置自增ID
ALTER TABLE customer AUTO_INCREMENT = 1;
ALTER TABLE product AUTO_INCREMENT = 1;
ALTER TABLE warehouse AUTO_INCREMENT = 1;
ALTER TABLE vehicle AUTO_INCREMENT = 1;
ALTER TABLE driver AUTO_INCREMENT = 1;
ALTER TABLE orders AUTO_INCREMENT = 1;
ALTER TABLE order_item AUTO_INCREMENT = 1;
ALTER TABLE inventory AUTO_INCREMENT = 1;
ALTER TABLE delivery_task AUTO_INCREMENT = 1;
ALTER TABLE freight_rule AUTO_INCREMENT = 1;

-- 客户数据
INSERT INTO customer (name, address, contact_person) VALUES
                                                         ('张氏贸易', '北京市朝阳区建国路88号', '张三'),
                                                         ('李氏科技', '上海市浦东新区张江高科技园区', '李四'),
                                                         ('王记食品', '广州市天河区体育西路120号', '王五'),
                                                         ('赵氏电器', '深圳市福田区深南大道6005号', '赵六'),
                                                         ('刘氏服饰', '杭州市西湖区文三路199号', '刘七');

-- 商品数据
INSERT INTO product (name) VALUES
                               ('iPhone 15'),
                               ('华为Mate60'),
                               ('小米电视'),
                               ('戴尔笔记本'),
                               ('索尼相机');

-- 仓库数据
INSERT INTO warehouse (location) VALUES
                                     ('北京顺义物流园A区'),
                                     ('上海嘉定仓储中心'),
                                     ('广州白云国际仓'),
                                     ('深圳宝安集散中心'),
                                     ('杭州萧山智慧仓');

-- 车辆数据
INSERT INTO vehicle (license_plate, warehouse_id) VALUES
                                                      ('京A·A8888', 1),
                                                      ('沪B·B9999', 2),
                                                      ('粤C·C7777', 3),
                                                      ('粤D·D6666', 4),
                                                      ('浙E·E5555', 5);

-- 驾驶员数据
INSERT INTO driver (name, birth_date, contact_info, vehicle_id) VALUES
                                                                    ('张师傅', '1985-03-12', '13800138001', 1),
                                                                    ('李师傅', '1990-07-25', '13900139002', 2),
                                                                    ('王师傅', '1988-11-05', '13700137003', 3),
                                                                    ('赵师傅', '1992-01-30', '13600136004', 4),
                                                                    ('刘师傅', '1983-09-18', '13500135005', 5);

-- 库存数据
INSERT INTO inventory (warehouse_id, product_id, quantity) VALUES
                                                               (1, 1, 50), (1, 2, 30), (1, 3, 20), (1, 4, 15), (1, 5, 10),
                                                               (2, 1, 30), (2, 2, 40), (2, 3, 25), (2, 4, 20), (2, 5, 15),
                                                               (3, 1, 20), (3, 2, 25), (3, 3, 40), (3, 4, 30), (3, 5, 20),
                                                               (4, 1, 15), (4, 2, 20), (4, 3, 30), (4, 4, 40), (4, 5, 25),
                                                               (5, 1, 10), (5, 2, 15), (5, 3, 20), (5, 4, 25), (5, 5, 50);

-- 运费规则
INSERT INTO freight_rule (min_distance, max_distance, unit_price) VALUES
                                                                      (0, 10, 5.00),
                                                                      (10.01, 20, 4.50),
                                                                      (20.01, 50, 4.00),
                                                                      (50.01, 100, 3.50),
                                                                      (100.01, 9999, 3.00);