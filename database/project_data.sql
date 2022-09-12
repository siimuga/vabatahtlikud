INSERT INTO public.category (id, name) VALUES (DEFAULT, 'kõik');
INSERT INTO public.category (id, name) VALUES (DEFAULT, 'sport');
INSERT INTO public.category (id, name) VALUES (DEFAULT, 'muusika');
INSERT INTO public.category (id, name) VALUES (DEFAULT, 'loodus');
INSERT INTO public.category (id, name) VALUES (DEFAULT, 'muu');

INSERT INTO public.language (id, name) VALUES (DEFAULT, 'eesti');
INSERT INTO public.language (id, name) VALUES (DEFAULT, 'vene');
INSERT INTO public.language (id, name) VALUES (DEFAULT, 'inglise');

INSERT INTO public.county (id, name) VALUES (DEFAULT, 'Valgamaa');
INSERT INTO public.county (id, name) VALUES (DEFAULT, 'Tartumaa');
INSERT INTO public.county (id, name) VALUES (DEFAULT, 'Harjumaa');
INSERT INTO public.county (id, name) VALUES (DEFAULT, 'Saaremaa');
INSERT INTO public.county (id, name) VALUES (DEFAULT, 'Hiiumaa');
INSERT INTO public.county (id, name) VALUES (DEFAULT, 'Võrumaa');
INSERT INTO public.county (id, name) VALUES (DEFAULT, 'Pärnumaa');
INSERT INTO public.county (id, name) VALUES (DEFAULT, 'Põlvamaa');
INSERT INTO public.county (id, name) VALUES (DEFAULT, 'Läänemaa');
INSERT INTO public.county (id, name) VALUES (DEFAULT, 'Lääne-Virumaa');
INSERT INTO public.county (id, name) VALUES (DEFAULT, 'Ida-Virumaa');
INSERT INTO public.county (id, name) VALUES (DEFAULT, 'Jõgevamaa');
INSERT INTO public.county (id, name) VALUES (DEFAULT, 'Raplamaa');
INSERT INTO public.county (id, name) VALUES (DEFAULT, 'Järvamaa');
INSERT INTO public.county (id, name) VALUES (DEFAULT, 'Viljandimaa');

INSERT INTO public.role (id, name) VALUES (DEFAULT, 'admin');
INSERT INTO public.role (id, name) VALUES (DEFAULT, 'kasutaja');

INSERT INTO public.contact (id, first_name, last_name, sex, email) VALUES (DEFAULT, 'Siim', 'Türk', 'M', 'asdas@gmail.com');
INSERT INTO public.contact (id, first_name, last_name, sex, email) VALUES (DEFAULT, 'Gabi', 'Khalifa', 'N', 'abudhabi@hot.ru');

INSERT INTO public."user" (id, username, password, status, contact_id, role_id) VALUES (DEFAULT, 'admin', '123', DEFAULT, null, 1);
INSERT INTO public."user" (id, username, password, status, contact_id, role_id) VALUES (DEFAULT, 'siim', 'piim', DEFAULT, 1, 2);
INSERT INTO public."user" (id, username, password, status, contact_id, role_id) VALUES (DEFAULT, 'qwe', 'asd', false, 1, 2);

INSERT INTO public.event_register (id) VALUES (DEFAULT);
INSERT INTO public.event_register (id) VALUES (DEFAULT);

INSERT INTO public.task (id, name, event_register_id, status) VALUES (DEFAULT, 'Silka', 1, DEFAULT);
INSERT INTO public.task (id, name, event_register_id, status) VALUES (DEFAULT, 'Poolita', 2, DEFAULT);

INSERT INTO public.additional_info (id, name, event_register_id, status) VALUES (DEFAULT, 'Füüsiline töö', 1, DEFAULT);
INSERT INTO public.additional_info (id, name, event_register_id, status) VALUES (DEFAULT, 'Joomine', 1, DEFAULT)



