-- Creating locations table.

CREATE TABLE locations (
   id BIGSERIAL PRIMARY KEY,
   address VARCHAR(50),
   city VARCHAR(50),
   province_state VARCHAR(50),
   country VARCHAR(50),
   created TIMESTAMP,
   modified TIMESTAMP
   );



-- Creating departments table.

CREATE TABLE departments (
   id BIGSERIAL PRIMARY KEY,
   name VARCHAR(50)
   );



-- Creating employees table.

CREATE TABLE employees (
   id BIGSERIAL PRIMARY KEY,
   name VARCHAR(50),
   mailing_address VARCHAR(80),
   email VARCHAR(80),
   login VARCHAR(50),
   password VARCHAR(30),
   home_phone VARCHAR(30),
   work_phone VARCHAR(30),
   cellphone VARCHAR(30),
   emergency_contact_name VARCHAR(50),
   emergency_phone VARCHAR(30),
   department_id BIGSERIAL REFERENCES departments(id),
   location_id BIGSERIAL REFERENCES locations(id),
   created TIMESTAMP,
   modified TIMESTAMP
   );



-- Creating asset states table.

CREATE TABLE asset_states (
   id BIGSERIAL PRIMARY KEY,
   name VARCHAR(50),
   description TEXT
   );



-- Creating asset types table.

CREATE TABLE asset_types (
   id BIGSERIAL PRIMARY KEY,
   name VARCHAR(50),
   description TEXT
   );



-- Creating assets issues table.

CREATE TABLE asset_issues (
   id BIGSERIAL PRIMARY KEY,
   name VARCHAR(50),
   description TEXT,
   history TEXT,
   employee_id BIGSERIAL REFERENCES employees(id),
   eta_return_to_service TIMESTAMP,
   created TIMESTAMP,
   modified TIMESTAMP
   );




-- Creating assets table.

CREATE TABLE assets (
   id BIGSERIAL PRIMARY KEY,
   name VARCHAR(50),
   description TEXT,
   serial_number VARCHAR(30),
   asset_state_id BIGSERIAL REFERENCES asset_states(id),
   location_id BIGSERIAL REFERENCES locations(id),
   asset_type_id BIGSERIAL REFERENCES asset_types(id),
   asset_issue_id BIGSERIAL REFERENCES asset_issues(id),
   employee_id BIGSERIAL REFERENCES employees(id),
   created TIMESTAMP,
   modified TIMESTAMP
   );