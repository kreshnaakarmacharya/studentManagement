CREATE TABLE std_management.student(
    id BIGINT AUTO_INCREMENT,
    name VARCHAR(100),
    email VARCHAR(100),
    address VARCHAR(100),
    math_marks FLOAT,
    social_marks FLOAT,
    total_marks FLOAT,
    PRIMARY KEY(id)
);