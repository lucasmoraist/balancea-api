CREATE TABLE IF NOT EXISTS t_category (
    id SERIAL PRIMARY KEY,
    name VARCHAR(80) NOT NULL
);

ALTER TABLE t_expense ADD COLUMN category_id SERIAL;
ALTER TABLE t_expense ADD CONSTRAINT fk_categort_id FOREIGN KEY (category_id) REFERENCES t_category(id);