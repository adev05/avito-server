DROP TABLE IF EXISTS users, user_roles, notification_types, notifications, notifications_to_roles;

CREATE TABLE IF NOT EXISTS user_roles
(
    id SERIAL PRIMARY KEY,
    role_name VARCHAR(31) NOT NULL
);

CREATE TABLE IF NOT EXISTS users
(
    id SERIAL PRIMARY KEY,
    username VARCHAR(31) NOT NULL,
    password_hash TEXT NOT NULL,
    role INTEGER NOT NULL REFERENCES user_roles (id),
    created_at TIMESTAMP DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS notification_types
(
    id SERIAL PRIMARY KEY,
    type_name VARCHAR(31) NOT NULL
);

CREATE TABLE IF NOT EXISTS notifications
(
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    date TIMESTAMP DEFAULT NOW(),
    author INTEGER NOT NULL REFERENCES users (id),
    type INTEGER NOT NULL REFERENCES notification_types (id),
    roles_to INTEGER[] NOT NULL,
    is_read BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS notifications_to_roles
(
    id SERIAL PRIMARY KEY,
    notification INTEGER NOT NULL REFERENCES notifications (id),
    role INTEGER NOT NULL REFERENCES user_roles (id)
);