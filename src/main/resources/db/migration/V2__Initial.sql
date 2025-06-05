INSERT INTO public.user_t (id, firstname, lastname, email, password, role)
VALUES (DEFAULT, 'admin', 'admin', 'admin@studentportal.ru', '$2a$12$2NPcg3T3Xsnr.cBWIfpWne6e5Bq24f.QamWMNJwmEhMejxzqx3hy6', 'ADMIN'),
       (DEFAULT, 'Prepor', 'Prepod', 'prepod@studentportal.ru', '$2a$12$2NPcg3T3Xsnr.cBWIfpWne6e5Bq24f.QamWMNJwmEhMejxzqx3hy6', 'TEACHER'),
       (DEFAULT, 'Ivan', 'Ivanov', 'ivanov@studentportal.ru', '$2a$12$2NPcg3T3Xsnr.cBWIfpWne6e5Bq24f.QamWMNJwmEhMejxzqx3hy6', 'STUDENT'),
       (DEFAULT, 'Petr', 'Petrov', 'petrov@studentportal.ru', '$2a$12$2NPcg3T3Xsnr.cBWIfpWne6e5Bq24f.QamWMNJwmEhMejxzqx3hy6', 'STUDENT'),
       (DEFAULT, 'Sidr', 'Sidorov', 'sidorov@studentportal.ru', '$2a$12$2NPcg3T3Xsnr.cBWIfpWne6e5Bq24f.QamWMNJwmEhMejxzqx3hy6', 'STUDENT');

INSERT INTO public.subject (id, name, teacher_id)
VALUES (DEFAULT, 'Математика', 2),
       (DEFAULT, 'Информатика', 2);

INSERT INTO public.student_group(id, name)
VALUES (DEFAULT, 'ИС2-21'),
       (DEFAULT, 'П2-21'),
       (DEFAULT, 'БТС1-21');