-- Question 1.

CREATE OR REPLACE FUNCTION fn_random_phone_number ()
RETURNS text AS
$$
DECLARE phone_number text;
   BEGIN
      phone_number := CAST ('(' || ROUND(RANDOM () * 9) || ROUND(RANDOM () * 9) || ROUND(RANDOM () * 9) || ')' 
      || ROUND(RANDOM () * 9) || ROUND(RANDOM () * 9) || ROUND(RANDOM () * 9) || '-' || ROUND(RANDOM () * 9) || 
      ROUND(RANDOM () * 9) || ROUND(RANDOM () * 9) || ROUND(RANDOM () * 9) AS text);
RETURN phone_number;
   END
$$
LANGUAGE 'plpgsql';




-- Question 2.

CREATE OR REPLACE FUNCTION fn_strip_nonnumber (string text)
RETURNS text AS
$$
   DECLARE step1 text;
            step2 text;
             step3 text;
      BEGIN
       step1 := regexp_replace(string, '[(]', '');
       step2 := regexp_replace(step1, '[)]', '');
       step3 := regexp_replace(step2, '[-]', '');

   RETURN step3;
      END
$$
LANGUAGE 'plpgsql';




-- Question 3.

ALTER TABLE customers
ADD COLUMN clean_phone VARCHAR(15);



-- Question 4.

CREATE OR REPLACE FUNCTION fn_pop_clean_phone()
RETURNS trigger AS
$$
  BEGIN

    NEW.clean_phone := fn_strip_nonnumber(NEW.phone_number);

  RETURN NEW;

  END;
$$
LANGUAGE 'plpgsql';



CREATE TRIGGER trg_pop_cleanphone
BEFORE UPDATE OR INSERT
ON customers
FOR EACH ROW
EXECUTE PROCEDURE fn_pop_clean_phone();




-- Question 5.


update customers set phone_number = fn_random_phone_number();





-- Question 6.

CREATE OR REPLACE FUNCTION fn_log_changes_optout()
RETURNS trigger AS 
$$
DECLARE msg text;
        msg2 integer;
        
BEGIN

   IF NEW.opt_out <> 't'
   THEN
     msg := 'Customer opted out';
     msg2 := 2;
   END IF;

   IF NEW.opt_out <> 'f'
   THEN
     msg := 'Customer opted in';
     msg2 := 1;
   END IF;

  IF OLD.opt_out <> NEW.opt_out
  THEN
   INSERT INTO customer_email_log(log_date, log_note, customer_id, email_log_type_id)
     VALUES (current_timestamp, msg, OLD.id, msg2 );
     END IF;

     RETURN NEW;

END;
$$
LANGUAGE "plpgsql";



CREATE TRIGGER trg_log_optout_changes
BEFORE UPDATE OR INSERT
ON customers
FOR EACH ROW
EXECUTE PROCEDURE fn_log_changes_optout();