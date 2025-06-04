INSERT INTO public.user_t (id, firstname, lastname, email, password)
VALUES (DEFAULT, 'admin', 'admin', 'admin@studentportal.ru', '$2a$12$2NPcg3T3Xsnr.cBWIfpWne6e5Bq24f.QamWMNJwmEhMejxzqx3hy6');

INSERT INTO public.user_authorities (user_id, authorities)
VALUES (1, 'ADMIN');