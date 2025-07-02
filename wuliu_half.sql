CREATE TABLE Customer (
    customer_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL COMMENT '客户名称',
    address VARCHAR(255) NOT NULL COMMENT '默认收货地址',
    contact_person VARCHAR(100) COMMENT '联系人姓名',
    phone VARCHAR(20) COMMENT '联系电话',
    email VARCHAR(100) COMMENT '电子邮箱',
    credit_level TINYINT DEFAULT 80 COMMENT '信用评分(0-100)',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) COMMENT '客户基本信息表';

INSERT INTO Customer (customer_id, name, address, contact_person, phone, email, credit_level) VALUES
(1, '北京朝阳科技有限公司', '北京市朝阳区建国路88号', '张经理', '13800138001', 'zhang@cytech.com', 90),
(2, '上海浦东贸易有限公司', '上海市浦东新区张江高科园区', '李女士', '13900139002', 'li@pudongtrade.com', 85),
(3, '广州白云电商中心', '广州市白云区机场路131号', '王主管', '13700137003', 'wang@byec.com', 75),
(4, '成都高新制造厂', '成都市高新区天府三街', '刘厂长', '13600136004', 'liu@cdmanufacture.com', 88);

CREATE TABLE Product (
    product_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL COMMENT '商品名称',
    sku_code VARCHAR(50) UNIQUE COMMENT '商品SKU编码',
    specification VARCHAR(100) COMMENT '规格参数',
    category VARCHAR(50) COMMENT '商品类别',
    unit VARCHAR(20) DEFAULT '件' COMMENT '计量单位',
    is_active BOOLEAN DEFAULT TRUE COMMENT '是否上架',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'
) COMMENT '商品信息表';

INSERT INTO Product (product_id, name, sku_code, specification, category, unit) VALUES
(1, '华为Mate60 Pro', 'HW-M60P-256', '8GB+256GB 雅丹黑', '智能手机', '部'),
(2, '联想ThinkPad X1', 'LEN-TPX1-01', 'i7/16GB/512GB', '笔记本电脑', '台'),
(3, '海尔变频冰箱', 'HAIER-BX-501', '501L 十字对开门', '家用电器', '台'),
(4, '九阳破壁机', 'JOYOUNG-PB-01', '1.75L 多功能', '厨房电器', '台');

CREATE TABLE Warehouse (
    warehouse_id INT PRIMARY KEY AUTO_INCREMENT,
    code VARCHAR(20) UNIQUE NOT NULL COMMENT '仓库编码',
    name VARCHAR(100) NOT NULL COMMENT '仓库名称',
    location VARCHAR(255) NOT NULL COMMENT '详细地址',
    contact_phone VARCHAR(20) COMMENT '联系电话',
    manager VARCHAR(50) COMMENT '负责人',
    longitude DECIMAL(10,6) COMMENT '经度坐标',
    latitude DECIMAL(10,6) COMMENT '纬度坐标',
    capacity INT COMMENT '仓储容量(单位:托盘)',
    status ENUM('运营中','维护中','已关闭') DEFAULT '运营中' COMMENT '仓库状态',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'
) COMMENT '仓库信息表';

INSERT INTO Warehouse (warehouse_id, code, name, location, contact_phone, longitude, latitude) VALUES
(1, 'WH-BJ-001', '北京中央仓库', '北京市大兴区物流园A区', '010-88215566', 116.404269, 39.915378),
(2, 'WH-SH-001', '上海浦东仓库', '上海市浦东新区航津路158号', '021-58682233', 121.589490, 31.251863),
(3, 'WH-GZ-001', '广州白云仓库', '广州市白云区石井大道', '020-36351188', 113.259294, 23.181466),
(4, 'WH-CD-001', '成都双流仓库', '成都市双流区物流大道', '028-85779966', 103.952678, 30.574269);

CREATE TABLE Vehicle (
    vehicle_id INT PRIMARY KEY AUTO_INCREMENT,
    license_plate VARCHAR(20) UNIQUE NOT NULL COMMENT '车牌号',
    warehouse_id INT NOT NULL COMMENT '所属仓库',
    vehicle_type ENUM('小型货车','中型货车','大型货车','冷藏车') COMMENT '车辆类型',
    load_capacity DECIMAL(10,2) COMMENT '载重能力(kg)',
    volume_capacity DECIMAL(10,2) COMMENT '容积(m³)',
    purchase_date DATE COMMENT '购买日期',
    insurance_number VARCHAR(50) COMMENT '保险单号',
    insurance_expiry DATE COMMENT '保险到期日',
    status ENUM('可用','任务中','维修中','已报废') DEFAULT '可用' COMMENT '当前状态',
    last_maintenance_date DATE COMMENT '上次保养日期',
    next_maintenance_date DATE COMMENT '下次保养日期',
    FOREIGN KEY (warehouse_id) REFERENCES Warehouse(warehouse_id),
    INDEX idx_warehouse (warehouse_id),
    INDEX idx_status (status)
) COMMENT '运输车辆表';

INSERT INTO Vehicle (vehicle_id, license_plate, warehouse_id, vehicle_type, load_capacity, volume_capacity) VALUES
(1, '京A·12345', 1, '中型货车', 5000.00, 30.00),
(2, '沪B·67890', 2, '大型货车', 15000.00, 80.00),
(3, '粤A·ABCDE', 3, '小型货车', 2000.00, 15.00),
(4, '川A·XYZ12', 4, '冷藏车', 8000.00, 45.00);

CREATE TABLE Driver (
    driver_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL COMMENT '驾驶员姓名',
    gender ENUM('男','女','其他') COMMENT '性别',
    birth_date DATE COMMENT '出生日期',
    id_number VARCHAR(18) UNIQUE COMMENT '身份证号',
    contact_phone VARCHAR(20) NOT NULL COMMENT '联系电话',
    emergency_contact VARCHAR(50) COMMENT '紧急联系人',
    emergency_phone VARCHAR(20) COMMENT '紧急联系电话',
    license_type VARCHAR(20) COMMENT '驾照类型',
    license_number VARCHAR(50) UNIQUE COMMENT '驾照编号',
    license_expiry DATE COMMENT '驾照到期日',
    vehicle_id INT UNIQUE COMMENT '绑定车辆',
    status ENUM('在职','休假','离职') DEFAULT '在职' COMMENT '在职状态',
    hire_date DATE COMMENT '入职日期',
    FOREIGN KEY (vehicle_id) REFERENCES Vehicle(vehicle_id),
    INDEX idx_vehicle (vehicle_id),
    INDEX idx_status (status)
) COMMENT '驾驶员信息表';

INSERT INTO Driver (driver_id, name, gender, birth_date, contact_phone, license_number, vehicle_id) VALUES
(1, '张师傅', '男', '1985-03-15', '13800138005', '110105198503150012', 1),
(2, '李师傅', '男', '1978-11-22', '13900139006', '310115197811220034', 2),
(3, '王师傅', '女', '1990-07-08', '13700137007', '440111199007080056', 3),
(4, '刘师傅', '男', '1982-09-30', '13600136008', '510107198209300078', 4);