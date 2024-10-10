CREATE TABLE IF NOT EXISTS t_expense (
    id SERIAL PRIMARY KEY,
    description TEXT NOT NULL,
    amount BIGINT NOT NULL,
    date DATE NOT NULL
);