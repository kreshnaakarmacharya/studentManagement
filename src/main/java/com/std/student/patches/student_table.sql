CREATE TABLE student (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         std_code VARCHAR(255) NOT NULL UNIQUE,
                         NAME VARCHAR(255) NOT NULL,
                         email VARCHAR(255) NOT NULL,
                         phone_number VARCHAR(255) NOT NULL,
                         address VARCHAR(255) NOT NULL,
                         gender VARCHAR(255) NOT NULL,
                         course VARCHAR(255) NOT NULL
);