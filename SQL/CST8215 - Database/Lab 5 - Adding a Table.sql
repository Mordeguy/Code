--CREATE TABLE order_logs (
	--id BIGSERIAL NOT NULL PRIMARY KEY,
	--log_date timestamp default now(),
	--log_note text,
	--order_id int8 references orders(id)
	--);

--CREATE TABLE order_statuses (
--id BIGSERIAL NOT NULL PRIMARY KEY,
--name varchar(50) NOT NULL
--);

alter table customers add column phone_number integer;
alter table customers add column opt_out boolean;
alter table orders add column order_status_id int8 references order_statuses(id);
