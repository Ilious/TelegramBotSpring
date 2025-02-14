CREATE TABLE IF NOT EXISTS received_message (
    id BIGSERIAL PRIMARY KEY,
    received_at TIMESTAMP NOT NULL,
    user_id BIGINT NOT NULL
);
