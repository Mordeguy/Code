
Question1

SELECT COUNT (id) FROM survey_responders;


Question 2

SELECT * FROM survey_responders;


Question 3

SELECT COUNT (question) FROM survey_questions;


Question 4

SELECT question FROM survey_questions;


Question 5

SELECT COUNT (id)FROM survey_responders 
WHERE province = 'Alberta';


Question 6

SELECT COUNT (id) FROM survey_responses 
WHERE survey_question_id = 4;


Question 7

SELECT AVG(survey_response) FROM survey_responses;



Question 8

SELECT AVG(survey_response) FROM survey_responses 
GROUP BY survey_question_id;



Question 9

SELECT first_name, last_name FROM survey_responders 
INNER JOIN survey_responses 
ON survey_responders.id = survey_responses.survey_responder_id 
WHERE survey_question_id = 1;



Question 10 

SELECT AVG (survey_response) FROM survey_responses 
JOIN survey_responders ON survey_responders.id = survey_responses.survey_responder_id 
WHERE province = 'Ontario';



Question 11

SELECT AVG (survey_response) FROM survey_responses 
INNER JOIN survey_responders ON survey_responses.survey_responder_id = survey_responders.id 
WHERE province = 'Ontario' GROUP BY survey_question_id;


Question 12

SELECT province, question, AVG(survey_response) FROM survey_responses 
INNER JOIN survey_responders ON survey_responder_id = survey_responders.id 
INNER JOIN survey_questions ON survey_question_id = survey_questions.id 
GROUP BY province, question ORDER BY province;
