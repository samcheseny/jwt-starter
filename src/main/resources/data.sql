DROP TABLE IF EXISTS role_users;
DROP TABLE IF EXISTS permission_roles;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS permissions;

CREATE TABLE users
(
    id       INT AUTO_INCREMENT PRIMARY KEY,
    email    VARCHAR(250) NOT NULL,
    password VARCHAR(250) NOT NULL
);

CREATE TABLE roles
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(250) NOT NULL,
    description VARCHAR(500) NULL,
    UNIQUE (name)
);

CREATE TABLE permissions
(
    id   INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(250) NOT NULL,
    UNIQUE (name)
);

CREATE TABLE permission_roles
(
    role_id       INT NOT NULL,
    permission_id INT NOT NULL,
    FOREIGN KEY (role_id) REFERENCES roles (id) ON DELETE CASCADE,
    FOREIGN KEY (permission_id) REFERENCES permissions (id) ON DELETE CASCADE
);

CREATE TABLE role_users
(
    role_id INT NOT NULL,
    user_id INT NOT NULL,
    FOREIGN KEY (role_id) REFERENCES roles (id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

INSERT INTO roles (id, name, description)
VALUES (1, 'ROLE_ADMIN', 'School system administrator'),
       (2, 'ROLE_STUDENT', 'Student attending the school');

INSERT INTO permissions (id, name)
VALUES (1, 'course_create'),
       (2, 'course_read'),
       (3, 'course_update'),
       (4, 'course_delete'),
       (5, 'student_create'),
       (6, 'student_read'),
       (7, 'student_update'),
       (8, 'student_delete');

INSERT INTO permission_roles (role_id, permission_id)
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (1, 4),
       (1, 5),
       (1, 6),
       (1, 8),
       (2, 2),
       (2, 6),
       (2, 7);