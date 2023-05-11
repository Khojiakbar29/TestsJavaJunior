a)
SELECT *
FROM customers
JOIN orders
ON customers.id = orders.customer_id;

b)
UPDATE customers
SET type = 'Corn'
WHERE id = 2;

c)
SELECT SUM(countryamount) AS TotalAmount
FROM customers
WHERE country = 'UK';
