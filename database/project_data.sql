INSERT INTO public.category (id, name) VALUES (DEFAULT, 'sport');
INSERT INTO public.category (id, name) VALUES (DEFAULT, 'muusika');
INSERT INTO public.category (id, name) VALUES (DEFAULT, 'loodus');
INSERT INTO public.category (id, name) VALUES (DEFAULT, 'muu');
INSERT INTO public.category (id, name) VALUES (DEFAULT, 'kõik');

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
INSERT INTO public.county (id, name) VALUES (DEFAULT, 'Kõik');

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

-- INSERT INTO public.event (id, event_name, start_date, end_date, volunteers_required, category_id, event_register_id, language_id, link, location_id, status, user_id) VALUES (DEFAULT, 'Auramine', '2022-09-15', '2022-09-17', 5, 2, 1, 1, null, 1, DEFAULT, 2);
-- INSERT INTO public.event (id, event_name, start_date, end_date, volunteers_required, category_id, event_register_id, language_id, link, location_id, status, user_id) VALUES (DEFAULT, 'Kalasta', '2022-11-15', '2022-11-17', 15, 4, 2, 2, null, 2, DEFAULT, 2);
-- INSERT INTO public.event (id, event_name, start_date, end_date, volunteers_required, category_id, event_register_id, language_id, link, location_id, status, user_id) VALUES (DEFAULT, 'Suusata', '2022-10-15', '2022-10-17', 500, 1, 3, 3, null, 1, DEFAULT, 4);
-- INSERT INTO public.event (id, event_name, start_date, end_date, volunteers_required, category_id, event_register_id, language_id, link, location_id, status, user_id) VALUES (DEFAULT, 'Küpseta', '2022-09-25', '2022-09-27', 55, 3, 4, 1, null, 3, DEFAULT, 4)

--
-- INSERT INTO public.task (id, name, event_register_id, status) VALUES (DEFAULT, 'Silka', 1, DEFAULT);
-- INSERT INTO public.task (id, name, event_register_id, status) VALUES (DEFAULT, 'Poolita', 2, DEFAULT);
-- INSERT INTO public.task (id, name, event_register_id, status) VALUES (DEFAULT, 'KAnaöa', 3, DEFAULT);
-- INSERT INTO public.task (id, name, event_register_id, status) VALUES (DEFAULT, 'Ussa', 3, DEFAULT);
--
-- INSERT INTO public.additional_info (id, name, event_register_id, status) VALUES (DEFAULT, 'Füüsiline töö', 1, DEFAULT);
-- INSERT INTO public.additional_info (id, name, event_register_id, status) VALUES (DEFAULT, 'Joomine', 1, DEFAULT);
-- INSERT INTO public.additional_info (id, name, event_register_id, status) VALUES (DEFAULT, 'Arvutamine', 2, DEFAULT);
-- INSERT INTO public.additional_info (id, name, event_register_id, status) VALUES (DEFAULT, 'Lugemine', 3, DEFAULT);
