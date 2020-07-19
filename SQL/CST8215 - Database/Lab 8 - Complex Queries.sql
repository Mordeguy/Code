-- Question 1.

SELECT * 
   FROM customers
JOIN countries ON countries.id = customers.country_id
WHERE countries.name = 'Canada';



-- Question 2.

SELECT 
   customers.name AS customer_name,
   customers.city AS customer_city,
   orders.id AS order_id,
   orders.order_date
FROM orders
JOIN customers ON customers.id = orders.customer_id
WHERE customer_id >4563 AND customer_id < 5678
ORDER BY orders.order_date ASC;



-- Question 3.

SELECT 
   customers.name AS customer_name,
   customers.city AS customer_city,
   orders.id AS order_id,
   orders.order_date
FROM orders
JOIN customers ON customers.id = orders.customer_id
JOIN countries ON countries.id = customers.country_id
WHERE customer_id >4563 AND customer_id < 5678 
AND countries.name = 'Canada'
ORDER BY orders.order_date ASC;



-- Question 4.

SELECT 
   customers.name AS customer_name,
   customers.city AS customer_city,
   orders.id AS order_id,
   orders.order_date,
   state_provinces.name AS province_name
FROM orders
JOIN customers ON customers.id = orders.customer_id
JOIN countries ON countries.id = customers.country_id
JOIN state_provinces ON state_provinces.id = customers.state_province_id
WHERE customer_id BETWEEN 4563 AND 5678 
AND countries.name = 'Canada'
ORDER BY orders.order_date ASC;



-- Question 5.

SELECT product_version_id,
AVG(price) AS average_price
FROM order_lines
WHERE product_version_id = 198
GROUP BY product_version_id;



-- Question 6.

SELECT product_version_id,
MAX (price) AS maximum_price
FROM order_lines
WHERE product_version_id = 198
GROUP BY product_version_id;





-- Question 7.

SELECT 
    order_id,
    COUNT (*),
    orders.order_date,
    SUM(order_lines.extended_price)
FROM order_lines
JOIN orders ON orders.id = order_lines.order_id
WHERE order_id = 34567
GROUP BY order_lines.order_id, orders.order_date;




-- Question 8.

SELECT products.name, 
   AVG(price) AS average_selling_price
   FROM order_lines 
JOIN product_versions ON product_versions.id = order_lines.product_version_id
JOIN products ON products.id = product_versions.product_id
WHERE products.name = 'Groovecom'
GROUP BY products.name;



-- Question 9.


SELECT a.name, a.price
FROM(
SELECT products.name, 
  AVG(price) AS price 
   FROM order_lines 
JOIN product_versions ON product_versions.id = order_lines.product_version_id
JOIN products ON products.id = product_versions.product_id
WHERE products.name = 'Groovecom'
GROUP BY products.name) AS a;



-- Question 10.

SELECT  MIN(count), MAX(count), AVG(count)
FROM(
 SELECT COUNT(*) AS count
 FROM order_lines
 JOIN orders ON orders.id = order_lines.order_id
 GROUP BY order_id) AS a;



-- Question 11.

SELECT AVG (number), a.province_name
FROM(
SELECT  COUNT(*) as number,
   
    state_provinces.name AS province_name
FROM customers
JOIN state_provinces ON state_provinces.id = customers.state_province_id
JOIN orders ON orders.customer_id = customers.id
WHERE state_provinces.name = 'Ontario'
GROUP BY  state_provinces.name) AS a
GROUP BY  a.province_name;


-- Question 12.

SELECT AVG(price)
FROM(
SELECT 
   SUM(extended_price) AS price,
   customers.name
   FROM order_lines
JOIN orders ON orders.id = order_lines.order_id
JOIN customers ON customers.id = orders.customer_id
GROUP BY customers.name
LIMIT 500) AS a;