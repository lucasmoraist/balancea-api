CREATE TABLE IF NOT EXISTS t_income (
    id SERIAL PRIMARY KEY,
    description TEXT NOT NULL,
    amount BIGINT NOT NULL,
    date DATE NOT NULL
);