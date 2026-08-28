-- Enable PGVector extension (Crucial for Spring AI)
CREATE EXTENSION IF NOT EXISTS vector;

-- 1. Create Business Tables
CREATE TABLE department (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT
);

CREATE TABLE employee (
    id BIGSERIAL PRIMARY KEY,
    employee_code VARCHAR(50) UNIQUE NOT NULL,
    full_name VARCHAR(255) NOT NULL,
    email VARCHAR(255),
    phone VARCHAR(30),
    department_id BIGINT,
    position VARCHAR(100),
    salary DECIMAL(15,2),
    status VARCHAR(30)
);

CREATE TABLE leave_request (
    id BIGSERIAL PRIMARY KEY,
    employee_id BIGINT NOT NULL,
    start_date DATE,
    end_date DATE,
    reason TEXT,
    status VARCHAR(30)
);

-- 2. Insert Sample Data
INSERT INTO department(name, description)
VALUES
('IT', 'Phòng Công nghệ thông tin'),
('HR', 'Phòng Nhân sự'),
('Marketing', 'Phòng Marketing'),
('Accounting', 'Phòng Kế toán');

INSERT INTO employee(
    employee_code,
    full_name,
    email,
    phone,
    department_id,
    position,
    salary,
    status
)
VALUES
(
    'NV001',
    'Nguyễn Văn An',
    'an@example.com',
    '0900000001',
    1,
    'Java Developer',
    25000000,
    'ACTIVE'
),
(
    'NV002',
    'Trần Thị Bình',
    'binh@example.com',
    '0900000002',
    2,
    'HR Specialist',
    20000000,
    'ACTIVE'
);
