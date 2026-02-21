CREATE TABLE events (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    total_capacity INT NOT NULL,
    available_capacity INT NOT NULL,
    event_time TIMESTAMP NOT NULL,
    created_at TIMESTAMP NOT NULL
);