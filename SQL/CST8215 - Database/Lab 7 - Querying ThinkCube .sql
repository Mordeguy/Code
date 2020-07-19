-- Question 1.

SELECT * FROM customers;


-- Question 2.

SELECT * FROM customers
WHERE city = 'Fairbanks';


-- Question 3.

SELECT * FROM customers
WHERE email LIKE '%.edu';


-- Question 4.

SELECT * FROM customers
WHERE email LIKE '%edu' AND 
city = 'Fairbanks';


-- Question 5.

SELECT * FROM customers
WHERE name LIKE '%dan%';


-- Question 6.

SELECT * FROM orders
WHERE order_date < '2015-11-1';


-- Question 7.

SELECT * FROM orders
WHERE id = 39357;


-- Question 8. 

SELECT * FROM orders
WHERE id BETWEEN 41200 AND 41323;

-- Question 9.

SELECT * FROM orders
WHERE id > 41200 AND id < 41323;


-- Question 10.

SELECT employees.name, 
	departments.id AS department_id,
        departments.name AS department FROM employees
JOIN departments ON departments.id = employees.department_id
WHERE departments.id = 5;


