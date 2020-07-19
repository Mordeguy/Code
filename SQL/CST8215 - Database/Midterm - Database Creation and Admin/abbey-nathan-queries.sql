-- 1. Selecting an employee by phone number.

SELECT * FROM employees
WHERE home_phone ILIKE '1-467%';


-- 2. Selecting all assets related to a given employee.

SELECT * FROM assets
WHERE employee_id = 70;


-- 3. Selecting all assets that are currently being repaired. 

SELECT * FROM assets
WHERE asset_state_id = 4;


-- 4. Selecting all assets assigned to an employee, the type of the asset and the current state of the asset.

SELECT employee_id,
       employees.name, 
       assets.asset_type_id, 
       asset_types.name AS asset_type, 
       assets.asset_state_id,
       asset_states.name AS asset_state  
from ASSETS
JOIN employees ON employee_id = employees.id
JOIN asset_types ON asset_type_id = asset_types.id
JOIN asset_states ON asset_state_id = asset_states.id;