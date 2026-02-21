CREATE TABLE roles (
    -- Use SERIAL here because Java Role ID is an Integer
    id SERIAL PRIMARY KEY, 
    name VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE users (
    -- Use BIGSERIAL here to match Java User ID (Long)
    id BIGSERIAL PRIMARY KEY, 
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255),
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    avatar_url TEXT,
    provider VARCHAR(20) DEFAULT 'local',
    provider_id VARCHAR(255),
    enabled BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE user_roles (
    user_id BIGINT REFERENCES users(id),
    role_id INTEGER REFERENCES roles(id),
    PRIMARY KEY (user_id, role_id)
);

INSERT INTO roles (name) VALUES ('ROLE_USER'), ('ROLE_ADMIN'), ('ROLE_SALES_REP');