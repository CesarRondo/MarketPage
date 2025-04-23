CREATE TABLE users(
    id int auto_increment PRIMARY KEY,
    username varchar(50) NOT NULL UNIQUE
--     balance int DEFAULT 0
    --loggedIn int DEFAULT 0 --this is a boolean used to control if a user is loggedIn, not used as this is not a real application
);

CREATE TABLE passwords(
    password_id int auto_increment PRIMARY KEY,
    password varchar(50) NOT NULL,
    id int NOT NULL UNIQUE,
    FOREIGN KEY (id) REFERENCES users(id)
);

CREATE TABLE sessions(
    session_id int auto_increment PRIMARY KEY,
    session_code varchar(150) UNIQUE,
    id int NOT NULL UNIQUE,
    FOREIGN KEY (id) REFERENCES users(id)
);

CREATE TABLE products(
    id int auto_increment PRIMARY KEY,
    name varchar(50) NOT NULL,
    description varchar(255),
    price int NOT NULL,
    visible int NOT NULL default 1
);
CREATE TABLE purchases(
    id int auto_increment PRIMARY KEY,
    user_id int,
    product_id int,
    amount int,
    price int,
    status varchar(20) NOT NULL default 'Delivering',
    FOREIGN KEY (product_id) REFERENCES products(id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);
-- CREATE TABLE baskets(
--     id int auto_increment PRIMARY KEY,
--     session
-- )


INSERT INTO products (name, description, price) VALUES
                                                            ('Wireless Mouse', 'Ergonomic wireless mouse with adjustable DPI and long battery life.', 25),
                                                            ('Gaming Keyboard', 'Mechanical keyboard with RGB lighting and programmable keys.', 80),
                                                            ('Bluetooth Headphones', 'Noise-canceling headphones with deep bass and 20-hour battery life.', 120),
                                                            ('Smartphone Stand', 'Adjustable phone stand for hands-free video calls and streaming.', 15),
                                                            ('Portable Charger', '10,000mAh power bank with fast charging and dual USB ports.', 35),
                                                            ('USB-C Hub', 'Multi-port adapter with HDMI, USB, and SD card reader support.', 50),
                                                            ('External Hard Drive', '1TB portable external hard drive with fast data transfer speeds.', 90),
                                                            ('Smart LED Bulb', 'Wi-Fi-enabled LED bulb with adjustable brightness and color.', 20),
                                                            ('Fitness Tracker', 'Waterproof fitness band with heart rate monitor and step counter.', 60),
                                                            ('Laptop Cooling Pad', 'Ultra-slim cooling pad with dual fans for better airflow.', 30);


