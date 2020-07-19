

--1. Altering order_statuses table.

INSERT INTO order_statuses (name) 
VALUES ('new');

INSERT INTO order_statuses (name) 
VALUES ('processing');

INSERT INTO order_statuses (name) 
VALUES ('shipped');

INSERT INTO order_statuses (name) 
VALUES ('invoiced');

INSERT INTO order_statuses (name) 
VALUES ('paid');


--3. Altering email_log_types table.

INSERT INTO email_log_types (name) 
VALUES ('subscribe');

INSERT INTO email_log_types (name) 
VALUES ('unsubscribe');

INSERT INTO email_log_types (name) 
VALUES ('campaign_mailer');



--5. Update opt_out BOOLEAN to false

UPDATE customers SET opt_out = 'f';



-- 7. Update all orders status to New

UPDATE orders 
SET order_status_id = 1;



