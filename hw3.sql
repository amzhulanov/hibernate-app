BEGIN;

DROP TABLE IF EXISTS customers CASCADE;
CREATE TABLE customers (customer_id bigserial PRIMARY KEY, name_fld VARCHAR(255), lastname_fld varchar(255));
INSERT INTO customers (name_fld, lastname_fld) VALUES
('Ivan', 'Ivanovich'),
('Nikolay', 'Nikolaich'),
('Petr', 'Petrovich');

DROP TABLE IF EXISTS products CASCADE;
CREATE TABLE products (product_id bigserial PRIMARY KEY, title_fld VARCHAR(255), cost_fld float);
INSERT INTO products (title_fld, cost_fld) VALUES
('milk', 10.5),
('meat', 37.2),
('coke', 2.2);

DROP TABLE IF EXISTS items CASCADE;
CREATE TABLE items (item_id bigserial PRIMARY KEY, title_fld VARCHAR(255), cost_fld int, customer_id bigint REFERENCES customers(customer_id));
INSERT INTO items (title_fld, cost_fld) VALUES
('comp', 10),
('mouse', 37),
('keyboard', 2);


DROP TABLE IF EXISTS customers_products CASCADE;
CREATE TABLE customers_products (customer_id bigint, product_id bigint, cost float, FOREIGN KEY (customer_id) REFERENCES customers (customer_id), FOREIGN KEY (product_id) REFERENCES products (product_id));
INSERT INTO customers_products (customer_id, product_id,cost) VALUES
(1, 1,10.5),
(2, 1,10.5),
(3, 1,10.5),
(2, 2,37.2),
(1, 3,2.2);


COMMIT;