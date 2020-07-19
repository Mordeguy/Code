-- Task 1.

ALTER TABLE survey_responders
ADD COLUMN last_modified timestamp;



-- Task 2.

ALTER TABLE survey_questions
ADD COLUMN last_modified timestamp;



-- Task 3.

CREATE INDEX index_question_responder_id
ON survey_responses (survey_question_id, survey_responder_id);




-- Task 4.

ALTER TABLE survey_responders
ADD COLUMN username VARCHAR(50);



-- Task 5.

ALTER TABLE survey_responders
ADD COLUMN password VARCHAR(75);



-- Task 6.
 
UPDATE survey_responders
SET username = SUBSTRING (survey_responders.first_name from 0 for 2) || last_name 



-- Task 7.

CREATE OR REPLACE FUNCTION fn_random_pass()
RETURNS text AS
$$
DECLARE
   options text[] := '{a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z,A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z,0,1,2,3,4,5,6,7,8,9}';
    password text;
BEGIN

   password := options [ROUND(RANDOM()*61) +1] || options [ROUND(RANDOM()*61) + 1] || options [ROUND(RANDOM()*61) +1] || options [ROUND(RANDOM()*61) + 1] ||
               options [ROUND(RANDOM()*61) +1] || options [ROUND(RANDOM()*61) + 1] || options [ROUND(RANDOM()*61) +1] || options [ROUND(RANDOM()*61) + 1];

 
   RETURN MD5(password);

END;
$$
LANGUAGE 'plpgsql';



-- Task 8.

UPDATE survey_responders 
SET password = fn_random_pass();



-- Task 9.

CREATE OR REPLACE FUNCTION fn_last_modified_responders()
RETURNS trigger AS
$$
  BEGIN

  NEW.last_modified = now();

  RETURN NEW;
  END
$$
LANGUAGE 'plpgsql';



CREATE TRIGGER trg_last_mod_responders
BEFORE UPDATE OR INSERT
ON survey_responders
FOR EACH ROW
EXECUTE PROCEDURE fn_last_modified_responders();




-- Task 10.

CREATE TRIGGER trg_last_mod_questions
BEFORE UPDATE OR INSERT
ON survey_questions
FOR EACH ROW
EXECUTE PROCEDURE fn_last_modified_responders();




-- Task 11.

CREATE OR REPLACE FUNCTION fn_delete_orphan_record()
RETURNS trigger AS
$$
BEGIN

DELETE FROM survey_responses WHERE survey_responder_id = OLD.id;

RETURN OLD;


END;
$$
LANGUAGE 'plpgsql';


CREATE TRIGGER trg_del_orphan_response
AFTER DELETE 
ON survey_responders
FOR EACH ROW
EXECUTE PROCEDURE fn_delete_orphan_record();