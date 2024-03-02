INSERT INTO candidates (name, lastname, document_type, document_number, birthdate) VALUES ('John', 'Doe', 'DNI', '12345678', '1990-05-15'), ('Alice', 'Smith', 'LE', '00123456', '1985-08-22'), ('Bob', 'Johnson', 'LC', '987654321', '1978-03-10'), ('Eva', 'Brown', 'DNI', '54321987', '1995-12-01'), ('Michael', 'Wang', 'LE', '00098765', '1982-06-28');

INSERT INTO technologies (name_technology, version) VALUES ('Java', '14'), ('Python', '3.8'), ('Maven', '3.6.3'), ('Java', '17'), ('Java', '21');

INSERT INTO candidates_technologies (candidate_id, technology_id, experience_age) VALUES ((SELECT id FROM candidates WHERE name = 'John' AND lastname = 'Doe'), (SELECT id FROM technologies WHERE name_technology = 'Java' AND version = '14'), 3);

INSERT INTO candidates_technologies (candidate_id, technology_id, experience_age) VALUES ((SELECT id FROM candidates WHERE name = 'Bob' AND lastname = 'Johnson'), (SELECT id FROM technologies WHERE name_technology = 'Java' AND version = '17'), 7);

INSERT INTO candidates_technologies (candidate_id, technology_id, experience_age) VALUES ((SELECT id FROM candidates WHERE name = 'Michael' AND lastname = 'Wang'), (SELECT id FROM technologies WHERE name_technology = 'Java' AND version = '21'), 10);

INSERT INTO candidates_technologies (candidate_id, technology_id, experience_age) VALUES ((SELECT id FROM candidates WHERE name = 'John' AND lastname = 'Doe'), (SELECT id FROM technologies WHERE name_technology = 'Python' AND version = '3.8'), 25);

INSERT INTO candidates_technologies (candidate_id, technology_id, experience_age) VALUES ((SELECT id FROM candidates WHERE name = 'Alice' AND lastname = 'Smith'), (SELECT id FROM technologies WHERE name_technology = 'Java' AND version = '17'), 5);