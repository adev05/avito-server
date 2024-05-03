DROP TABLE IF EXISTS roles, users, types, notifications, notification_role;

CREATE TABLE IF NOT EXISTS roles
(
    id SERIAL PRIMARY KEY,
    name VARCHAR(31) NOT NULL
);

CREATE TABLE IF NOT EXISTS users
(
    id SERIAL PRIMARY KEY,
    username VARCHAR(31) NOT NULL,
    password_hash TEXT NOT NULL,
    role INTEGER NOT NULL REFERENCES roles (id),
    created_at TIMESTAMP DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS types
(
    id SERIAL PRIMARY KEY,
    name VARCHAR(31) NOT NULL
);

CREATE TABLE IF NOT EXISTS notifications
(
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    date TIMESTAMP DEFAULT NOW(),
    author INTEGER NOT NULL REFERENCES users (id),
    type INTEGER NOT NULL REFERENCES types (id),
    is_read BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS notification_role
(
    notification_id INTEGER NOT NULL REFERENCES notifications (id),
    role_id INTEGER NOT NULL REFERENCES roles (id),
    PRIMARY KEY (notification_id, role_id)
);