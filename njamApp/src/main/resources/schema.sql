CREATE TABLE restaurants (
                             id BIGINT AUTO_INCREMENT PRIMARY KEY,
                             name VARCHAR(255) NOT NULL,
                             address VARCHAR(255) NOT NULL,
                             telephone_number BIGINT,
                             email VARCHAR(255),
                             working_hours INT,
                             currently_open BOOLEAN,
                             average_delivery_time INT,
                             average_rating DOUBLE,
                             max_number_of_orders REAL,
                             michelin_star INT,
                             description TEXT
);

CREATE TABLE reviews (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         restaurant_id BIGINT NOT NULL,
                         title VARCHAR(255) NOT NULL,
                         description TEXT NOT NULL,
                         grade INT NOT NULL,
                         date DATE NOT NULL,
                         FOREIGN KEY (restaurant_id) REFERENCES restaurants(id)
);

