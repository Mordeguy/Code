

-- Question 1.

SELECT * FROM products
JOIN product_versions 
ON product_versions.product_id = products.id;



-- Question 2.

SELECT * FROM products
LEFT JOIN product_versions 
ON product_versions.product_id = products.id;

The difference is there are 8 more columns. As it grabs everything from products even those that don't match.




-- Question 3.

SELECT * FROM products
LEFT JOIN product_versions 
ON product_versions.product_id = products.id
WHERE version_id IS NULL;



-- Question 4.

SELECT email FROM customers
UNION
SELECT email FROM tradeshow_leads;



-- Question 5.

SELECT email FROM tradeshow_leads
EXCEPT
SELECT email FROM customers;



-- Question 6.

SELECT email FROM tradeshow_leads
INTERSECT
SELECT email FROM customers;




