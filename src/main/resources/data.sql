INSERT INTO employee(id,	days_off,	email_address,	first_name,	hourly_wage,	last_name) VALUES(1, 25, "example1@yc.com", "Kardoula1", 10, "kouklaki1");
INSERT INTO employee(id,	days_off,	email_address,	first_name,	hourly_wage,	last_name) VALUES(2, 25, "example2@yc.com", "Kardoula2", 10, "kouklaki2");
INSERT INTO employee(id,	days_off,	email_address,	first_name,	hourly_wage,	last_name) VALUES(3, 25, "example3@yc.com", "Kardoula3", 10, "kouklaki3");

INSERT INTO work_day_info(id, activity, date, hours, workplace, employee_id) VALUES (1,"work","2018-01-06",8,"office",1);

--Role data
INSERT INTO role(id, name) VALUES(1,"admin");
INSERT INTO role(id, name) VALUES(2,"ceo");
INSERT INTO role(id, name) VALUES(3,"manager");
INSERT INTO role(id, name) VALUES(4,"trainee");