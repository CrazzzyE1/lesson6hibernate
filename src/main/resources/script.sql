BEGIN;

DROP TABLE IF EXISTS customers CASCADE;
CREATE TABLE customers (id serial PRIMARY KEY, name VARCHAR(255));

DROP TABLE IF EXISTS products CASCADE;
CREATE TABLE products (
    id serial PRIMARY KEY,
    title VARCHAR(255),
    price numeric(8, 2)
);

DROP TABLE IF EXISTS customers_products CASCADE;
CREATE TABLE customers_products (
    id serial PRIMARY KEY,
    customer_id serial,
    product_id serial,
    selling_price numeric(8, 2),
    FOREIGN KEY (customer_id) REFERENCES customers(id),
    FOREIGN KEY (product_id) REFERENCES products(id)
);

CREATE OR REPLACE FUNCTION sellingprice() RETURNS trigger AS $selling_price_trg$
BEGIN
    UPDATE customers_products SET selling_price = (SELECT price FROM products WHERE id = NEW.product_id) WHERE id = NEW.id;
    RETURN NEW;
END;
$selling_price_trg$ LANGUAGE plpgsql;

CREATE TRIGGER selling_price_trg AFTER INSERT ON customers_products
FOR EACH ROW execute Procedure sellingprice();

INSERT INTO customers (name) VALUES ('Ivan'), ('Petr'), ('Sidor');

INSERT INTO products (title, price)
VALUES ('iPhone', 1330.00),
       ('iPad', 1540.00),
       ('Watch', 540.00),
       ('Macbook', 12500.00);


INSERT INTO customers_products (customer_id, product_id)
VALUES (1, 1), (1, 2), (1, 3), 
(2, 2), (2, 3), (2, 4), (3, 3);

COMMIT;
