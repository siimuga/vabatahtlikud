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
INSERT INTO public."user" (id, username, password, status, contact_id, role_id) VALUES (DEFAULT, 'maamees', '8989', DEFAULT, 1, 2);



INSERT INTO public.location (id, address, longtitude, latitude, county_id) VALUES (DEFAULT, 'Pöösas 4-6-8', null, null, 1);
INSERT INTO public.location (id, address, longtitude, latitude, county_id) VALUES (DEFAULT, 'Klakh 9', null, null, 2);
INSERT INTO public.location (id, address, longtitude, latitude, county_id) VALUES (DEFAULT, 'Susal 998', null, null, 4);

INSERT INTO public.event (id, event_name, start_date, end_date, volunteers_required, category_id, language_id, link, location_id, status, user_id) VALUES (DEFAULT, 'Seewald', '2022-09-16', '2022-09-17', 10, 1, 1, null, 1, 'v', 2);
INSERT INTO public.event (id, event_name, start_date, end_date, volunteers_required, category_id, language_id, link, location_id, status, user_id) VALUES (DEFAULT, 'Huumor', '2022-09-29', '2022-09-30', 100, 2, 2, null, 2, 'v', 4);
INSERT INTO public.event (id, event_name, start_date, end_date, volunteers_required, category_id, language_id, link, location_id, status, user_id) VALUES (DEFAULT, 'Koolja', '2022-10-12', '2022-10-21', 11, 2, 1, null, 3, 'v', 2);
INSERT INTO public.event (id, event_name, start_date, end_date, volunteers_required, category_id, language_id, link, location_id, status, user_id) VALUES (DEFAULT, 'Neoplan', '2022-10-20', '2022-10-29', 65, 3, 1, null, 2, 'v', 4);
INSERT INTO public.event (id, event_name, start_date, end_date, volunteers_required, category_id, language_id, link, location_id, status, user_id) VALUES (DEFAULT, 'Lada', '2022-10-18', '2022-10-21', 23, 1, 1, null, 1, 'v', 2);
INSERT INTO public.event (id, event_name, start_date, end_date, volunteers_required, category_id, language_id, link, location_id, status, user_id) VALUES (DEFAULT, 'Volkar', '2022-11-18', '2022-11-21', 41, 2, 2, null, 2, 'c', 4);
INSERT INTO public.event (id, event_name, start_date, end_date, volunteers_required, category_id, language_id, link, location_id, status, user_id) VALUES (DEFAULT, 'Audi', '2022-10-03', '2022-10-05',68, 1, 2, null, 1, 'v', 2);
INSERT INTO public.event (id, event_name, start_date, end_date, volunteers_required, category_id, language_id, link, location_id, status, user_id) VALUES (DEFAULT, 'Part', '2022-10-10', '2022-10-21', 78, 3, 3, null, 3, 'c', 4);

INSERT INTO public.task (id, name, status, event_id) VALUES (DEFAULT, 'Tere', DEFAULT, 1);
INSERT INTO public.task (id, name, status, event_id) VALUES (DEFAULT, 'Toord', DEFAULT, 1);
INSERT INTO public.task (id, name, status, event_id) VALUES (DEFAULT, 'korja', DEFAULT, 2);
INSERT INTO public.task (id, name, status, event_id) VALUES (DEFAULT, 'Koolja', DEFAULT, 3);

INSERT INTO public.additional_info (id, name, status, event_id) VALUES (DEFAULT, 'Paranda', DEFAULT, 2);
INSERT INTO public.additional_info (id, name, status, event_id) VALUES (DEFAULT, 'Tuulika', DEFAULT, 1);
INSERT INTO public.additional_info (id, name, status, event_id) VALUES (DEFAULT, 'Lammuta', DEFAULT, 2);
INSERT INTO public.additional_info (id, name, status, event_id) VALUES (DEFAULT, 'Emma', DEFAULT, 3)
