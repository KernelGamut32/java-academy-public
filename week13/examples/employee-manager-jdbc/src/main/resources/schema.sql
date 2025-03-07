CREATE TABLE hourlyEmployee (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    jobTitle VARCHAR(100) NOT NULL,
    hoursWorked DECIMAL(4,2) NOT NULL,
    hourlyPayRate DECIMAL(4,2) NOT NULL
);

CREATE TABLE salariedEmployee (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    jobTitle VARCHAR(100) NOT NULL,
    yearlySalary DECIMAL(10,2) NOT NULL
);
