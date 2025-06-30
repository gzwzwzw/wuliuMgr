-- 客户表
CREATE TABLE Customer (
    customer_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    address VARCHAR(255) NOT NULL,  -- 收货地址
    contact_person VARCHAR(100)
);

-- 商品表
CREATE TABLE Product (
    product_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL
);

-- 仓库表
CREATE TABLE Warehouse (
    warehouse_id INT PRIMARY KEY AUTO_INCREMENT,
    location VARCHAR(255) NOT NULL  -- 仓库物理地址（用于距离计算）
);

-- 车辆表
CREATE TABLE Vehicle (
    vehicle_id INT PRIMARY KEY AUTO_INCREMENT,
    license_plate VARCHAR(20) UNIQUE NOT NULL,
    warehouse_id INT NOT NULL,
    FOREIGN KEY (warehouse_id) REFERENCES Warehouse(warehouse_id)
);

-- 驾驶员表
CREATE TABLE Driver (
    driver_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    birth_date DATE,
    contact_info VARCHAR(50) NOT NULL,
    vehicle_id INT UNIQUE,  -- 一对一绑定车辆
    FOREIGN KEY (vehicle_id) REFERENCES Vehicle(vehicle_id)
);

-- 订单主表
CREATE TABLE Order (
    order_id INT PRIMARY KEY AUTO_INCREMENT,
    customer_id INT NOT NULL,
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    shipping_address VARCHAR(255) NOT NULL,
    status ENUM('pending', 'confirmed', 'preparing', 'shipping', 'completed', 'cancelled') DEFAULT 'pending',
    FOREIGN KEY (customer_id) REFERENCES Customer(customer_id)
);

-- 订单明细表
CREATE TABLE OrderDetail (
    detail_id INT PRIMARY KEY AUTO_INCREMENT,
    order_id INT NOT NULL,
    product_id INT NOT NULL,
    quantity INT NOT NULL CHECK (quantity > 0),
    warehouse_id INT,  -- 实际发货仓库（拣货时确定）
    FOREIGN KEY (order_id) REFERENCES Order(order_id),
    FOREIGN KEY (product_id) REFERENCES Product(product_id),
    FOREIGN KEY (warehouse_id) REFERENCES Warehouse(warehouse_id)
);

-- 库存表
CREATE TABLE Inventory (
    inventory_id INT PRIMARY KEY AUTO_INCREMENT,
    warehouse_id INT NOT NULL,
    product_id INT NOT NULL,
    quantity INT NOT NULL DEFAULT 0 CHECK (quantity >= 0),
    UNIQUE KEY (warehouse_id, product_id),  -- 防止重复库存记录
    FOREIGN KEY (warehouse_id) REFERENCES Warehouse(warehouse_id),
    FOREIGN KEY (product_id) REFERENCES Product(product_id)
);

-- 运输任务表
CREATE TABLE DeliveryTask (
    task_id INT PRIMARY KEY AUTO_INCREMENT,
    order_id INT UNIQUE NOT NULL,  -- 一对一关联订单
    vehicle_id INT NOT NULL,
    driver_id INT NOT NULL,
    from_warehouse_id INT NOT NULL,
    to_address VARCHAR(255) NOT NULL,
    distance DECIMAL(8,2) NOT NULL,  -- 公里数（用于运费计算）
    freight_cost DECIMAL(10,2) NOT NULL,
    status ENUM('pending', 'shipping', 'arrived', 'completed') DEFAULT 'pending',
    FOREIGN KEY (order_id) REFERENCES Order(order_id),
    FOREIGN KEY (vehicle_id) REFERENCES Vehicle(vehicle_id),
    FOREIGN KEY (driver_id) REFERENCES Driver(driver_id),
    FOREIGN KEY (from_warehouse_id) REFERENCES Warehouse(warehouse_id)
);

-- 运费规则表（分段计价）
CREATE TABLE FreightRule (
    rule_id INT PRIMARY KEY AUTO_INCREMENT,
    min_distance DECIMAL(8,2) NOT NULL,
    max_distance DECIMAL(8,2) NOT NULL,
    unit_price DECIMAL(6,2) NOT NULL,
    CHECK (min_distance < max_distance)
);