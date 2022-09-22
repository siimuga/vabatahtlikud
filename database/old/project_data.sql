INSERT INTO public_vaba.category (id, name) VALUES (DEFAULT, 'sport');
INSERT INTO public_vaba.category (id, name) VALUES (DEFAULT, 'muusika');
INSERT INTO public_vaba.category (id, name) VALUES (DEFAULT, 'loodus');
INSERT INTO public_vaba.category (id, name) VALUES (DEFAULT, 'muu');

INSERT INTO public_vaba.language (id, name) VALUES (DEFAULT, 'eesti');
INSERT INTO public_vaba.language (id, name) VALUES (DEFAULT, 'vene');
INSERT INTO public_vaba.language (id, name) VALUES (DEFAULT, 'inglise');

INSERT INTO public_vaba.county (id, name) VALUES (DEFAULT, 'Valgamaa');
INSERT INTO public_vaba.county (id, name) VALUES (DEFAULT, 'Tartumaa');
INSERT INTO public_vaba.county (id, name) VALUES (DEFAULT, 'Harjumaa');
INSERT INTO public_vaba.county (id, name) VALUES (DEFAULT, 'Saaremaa');
INSERT INTO public_vaba.county (id, name) VALUES (DEFAULT, 'Hiiumaa');
INSERT INTO public_vaba.county (id, name) VALUES (DEFAULT, 'Võrumaa');
INSERT INTO public_vaba.county (id, name) VALUES (DEFAULT, 'Pärnumaa');
INSERT INTO public_vaba.county (id, name) VALUES (DEFAULT, 'Põlvamaa');
INSERT INTO public_vaba.county (id, name) VALUES (DEFAULT, 'Läänemaa');
INSERT INTO public_vaba.county (id, name) VALUES (DEFAULT, 'Lääne-Virumaa');
INSERT INTO public_vaba.county (id, name) VALUES (DEFAULT, 'Ida-Virumaa');
INSERT INTO public_vaba.county (id, name) VALUES (DEFAULT, 'Jõgevamaa');
INSERT INTO public_vaba.county (id, name) VALUES (DEFAULT, 'Raplamaa');
INSERT INTO public_vaba.county (id, name) VALUES (DEFAULT, 'Järvamaa');
INSERT INTO public_vaba.county (id, name) VALUES (DEFAULT, 'Viljandimaa');

INSERT INTO public_vaba.role (id, name) VALUES (DEFAULT, 'admin');
INSERT INTO public_vaba.role (id, name) VALUES (DEFAULT, 'kasutaja');

INSERT INTO public_vaba.contact (id, first_name, last_name, sex, email) VALUES (DEFAULT, 'Mart', 'Tamm', 'M', 'asdas@gmail.com');
INSERT INTO public_vaba.contact (id, first_name, last_name, sex, email) VALUES (DEFAULT, 'Gulba', 'Hammas', 'N', 'abudhabi@hot.ru');

INSERT INTO public_vaba."user" (id, username, password, status, contact_id, role_id) VALUES (DEFAULT, 'admin', '123', DEFAULT, null, 1);
INSERT INTO public_vaba."user" (id, username, password, status, contact_id, role_id) VALUES (DEFAULT, 'siim', 'piim', DEFAULT, 1, 2);
INSERT INTO public_vaba."user" (id, username, password, status, contact_id, role_id) VALUES (DEFAULT, 'qwe', 'asd', false, 1, 2);
INSERT INTO public_vaba."user" (id, username, password, status, contact_id, role_id) VALUES (DEFAULT, 'maamees', '8989', DEFAULT, 1, 2);



INSERT INTO public_vaba.location (id, address, longtitude, latitude, county_id) VALUES (DEFAULT, 'Pöösas 4-6-8', null, null, 1);
INSERT INTO public_vaba.location (id, address, longtitude, latitude, county_id) VALUES (DEFAULT, 'Klakh 9', null, null, 2);
INSERT INTO public_vaba.location (id, address, longtitude, latitude, county_id) VALUES (DEFAULT, 'Susal 998', null, null, 5);
INSERT INTO public_vaba.location (id, address, longtitude, latitude, county_id) VALUES (DEFAULT, 'Eras 998', null, null, 1);
INSERT INTO public_vaba.location (id, address, longtitude, latitude, county_id) VALUES (DEFAULT, 'Velasd 55', null, null, 11);

INSERT INTO public_vaba.event (id, event_name, start_date, end_date, volunteers_required, category_id, language_id, link, location_id, status, user_id) VALUES (DEFAULT, 'Murutraktor', '2022-09-16', '2022-09-17', 10, 1, 1, null, 1, 'e', 2);
INSERT INTO public_vaba.event (id, event_name, start_date, end_date, volunteers_required, category_id, language_id, link, location_id, status, user_id) VALUES (DEFAULT, 'Huumor', '2021-09-29', '2021-09-30', 100, 2, 2, null, 2, 'e', 4);
INSERT INTO public_vaba.event (id, event_name, start_date, end_date, volunteers_required, category_id, language_id, link, location_id, status, user_id) VALUES (DEFAULT, 'Koolja', '2022-10-12', '2022-10-21', 11, 2, 1, null, 3, 'v', 2);
INSERT INTO public_vaba.event (id, event_name, start_date, end_date, volunteers_required, category_id, language_id, link, location_id, status, user_id) VALUES (DEFAULT, 'Neoplan', '2022-10-20', '2022-10-29', 65, 3, 1, null, 2, 'v', 4);
INSERT INTO public_vaba.event (id, event_name, start_date, end_date, volunteers_required, category_id, language_id, link, location_id, status, user_id) VALUES (DEFAULT, 'Lada', '2022-10-18', '2022-10-21', 23, 1, 1, null, 1, 'v', 2);
INSERT INTO public_vaba.event (id, event_name, start_date, end_date, volunteers_required, category_id, language_id, link, location_id, status, user_id) VALUES (DEFAULT, 'Volkar', '2022-11-18', '2022-11-21', 41, 2, 2, null, 2, 'c', 4);
INSERT INTO public_vaba.event (id, event_name, start_date, end_date, volunteers_required, category_id, language_id, link, location_id, status, user_id) VALUES (DEFAULT, 'Audi', '2022-10-03', '2022-10-05',68, 1, 2, null, 1, 'v', 2);
INSERT INTO public_vaba.event (id, event_name, start_date, end_date, volunteers_required, category_id, language_id, link, location_id, status, user_id) VALUES (DEFAULT, 'Part', '2022-10-10', '2022-10-21', 78, 3, 3, null, 3, 'c', 4);
INSERT INTO public_vaba.event (id, event_name, start_date, end_date, volunteers_required, category_id, language_id, link, location_id, status, user_id) VALUES (DEFAULT, 'Proov', '2023-09-02', '2023-09-30', 3, 4, 1, null, 2, DEFAULT, 3);
INSERT INTO public_vaba.event (id, event_name, start_date, end_date, volunteers_required, category_id, language_id, link, location_id, status, user_id) VALUES (DEFAULT, 'Kallas', '2022-11-25', '2022-12-06', 71, 1, 1, null, 2, 'c', 3);
INSERT INTO public_vaba.event (id, event_name, start_date, end_date, volunteers_required, category_id, language_id, link, location_id, status, user_id) VALUES (DEFAULT, 'Rullik', '2022-12-22', '2022-12-26', 500, 2, 1, null, 3, 'c', 3);

INSERT INTO public_vaba.task (id, name, status, event_id) VALUES (DEFAULT, 'Tere', DEFAULT, 1);
INSERT INTO public_vaba.task (id, name, status, event_id) VALUES (DEFAULT, 'Mängima', DEFAULT, 1);
INSERT INTO public_vaba.task (id, name, status, event_id) VALUES (DEFAULT, 'Marssima', DEFAULT, 2);
INSERT INTO public_vaba.task (id, name, status, event_id) VALUES (DEFAULT, 'Koolja', DEFAULT, 3);
INSERT INTO public_vaba.task (id, name, status, event_id) VALUES (DEFAULT, 'Seda', DEFAULT, 4);
INSERT INTO public_vaba.task (id, name, status, event_id) VALUES (DEFAULT, 'Toda', DEFAULT, 5);
INSERT INTO public_vaba.task (id, name, status, event_id) VALUES (DEFAULT, 'Wer', DEFAULT, 5);
INSERT INTO public_vaba.task (id, name, status, event_id) VALUES (DEFAULT, 'Man', DEFAULT, 3);
INSERT INTO public_vaba.task (id, name, status, event_id) VALUES (DEFAULT, 'WH', DEFAULT, 3);
INSERT INTO public_vaba.task (id, name, status, event_id) VALUES (DEFAULT, 'Poolik', DEFAULT, 2);

INSERT INTO public_vaba.additional_info (id, name, status, event_id) VALUES (DEFAULT, 'Paranda', DEFAULT, 2);
INSERT INTO public_vaba.additional_info (id, name, status, event_id) VALUES (DEFAULT, 'Tuulika', DEFAULT, 1);
INSERT INTO public_vaba.additional_info (id, name, status, event_id) VALUES (DEFAULT, 'Lammuta', DEFAULT, 2);
INSERT INTO public_vaba.additional_info (id, name, status, event_id) VALUES (DEFAULT, 'Emma', DEFAULT, 3);
INSERT INTO public_vaba.additional_info (id, name, status, event_id) VALUES (DEFAULT, 'Gabi', DEFAULT, 3);
INSERT INTO public_vaba.additional_info (id, name, status, event_id) VALUES (DEFAULT, 'Mabi', DEFAULT, 4);
INSERT INTO public_vaba.additional_info (id, name, status, event_id) VALUES (DEFAULT, 'Savi', DEFAULT, 5);
INSERT INTO public_vaba.additional_info (id, name, status, event_id) VALUES (DEFAULT, 'Liiv', DEFAULT, 3);

INSERT INTO public_vaba.volunteer (id, volunteers_size, info, user_id, status, event_id) VALUES (DEFAULT, 10, null, 2, DEFAULT, 2);
INSERT INTO public_vaba.volunteer (id, volunteers_size, info, user_id, status, event_id) VALUES (DEFAULT, 15, null, 2, DEFAULT, 10);
INSERT INTO public_vaba.volunteer (id, volunteers_size, info, user_id, status, event_id) VALUES (DEFAULT, 22, null, 3, DEFAULT, 6);

INSERT INTO public_vaba.event_date (id, date, volunteers_required, volunteers_assigned, is_active, event_id) VALUES (DEFAULT, '2021-09-29', 100, DEFAULT, true, 2);
INSERT INTO public_vaba.event_date (id, date, volunteers_required, volunteers_assigned, is_active, event_id) VALUES (DEFAULT, '2021-09-30', 100, DEFAULT, true, 2);

INSERT INTO public_vaba.volunteer_event_date (id, event_date_id, volunteer_id) VALUES (DEFAULT, 2, 1)
-- SET search_path TO public;