INSERT INTO employee(id,	days_off,	email_address,	first_name,	hourly_wage,	last_name , role, last_workplace) VALUES(1, 25, "makis@yc.com", "makis", 10, "giannopoulos" ,4, 0);
INSERT INTO employee(id,	days_off,	email_address,	first_name,	hourly_wage,	last_name, role, last_workplace) VALUES(2, 25, "eleni@yc.com", "eleni", 10, "tzanakou", 0, 0);
INSERT INTO employee(id,	days_off,	email_address,	first_name,	hourly_wage,	last_name, role, last_workplace) VALUES(3, 25, "kostas@yc.com", "kostas", 10, "norgias", 2, 0);

INSERT INTO account (id, password, username , employee_id) VALUES (1 , "1234", "makbut" , 1);
INSERT INTO account (id, password, username , employee_id) VALUES (2 , "6789", "etzanakou" , 2);
INSERT INTO account (id, password, username , employee_id) VALUES (3 , "0000", "knorgias" , 3);


-- INSERT INTO work_day_info (id, activity, date, finalized, hours, workplace, related_employee) VALUES ('2', '0', "0x30312f30362f32303137", b'1', '10', '1', '1');


