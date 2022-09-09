-- Kustutab public schema (mis põhimõtteliselt kustutab kõik tabelid)
DROP SCHEMA public CASCADE;
-- Loob uue public schema vajalikud õigused
CREATE SCHEMA public
-- taastab vajalikud andmebaasi õigused
    GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2022-09-08 13:06:35.748

-- tables
-- Table: category
CREATE TABLE category (
    id serial  NOT NULL,
    name varchar(255)  NOT NULL,
    CONSTRAINT category_pk PRIMARY KEY (id)
);

-- Table: contact
CREATE TABLE contact (
    id serial  NOT NULL,
    first_name varchar(255)  NULL,
    last_name varchar(255)  NULL,
    sex varchar(1)  NULL,
    email varchar(255)  NULL,
    CONSTRAINT contact_ak_1 UNIQUE (email) NOT DEFERRABLE  INITIALLY IMMEDIATE,
    CONSTRAINT contact_pk PRIMARY KEY (id)
);

-- Table: county
CREATE TABLE county (
    id serial  NOT NULL,
    name varchar(255)  NOT NULL,
    CONSTRAINT county_pk PRIMARY KEY (id)
);

-- Table: event
CREATE TABLE event (
    id serial  NOT NULL,
    user_id int  NOT NULL,
    category_id int  NOT NULL,
    event_name varchar(255)  NOT NULL,
    start_date date  NOT NULL,
    end_date date  NOT NULL,
    location_id int  NOT NULL,
    volunteers_required int  NOT NULL,
    language_id int  NOT NULL,
    additional_info varchar(1000)  NOT NULL,
    picture bytea  NULL,
    status char(1)  NOT NULL,
    CONSTRAINT event_pk PRIMARY KEY (id)
);

-- Table: event_date
CREATE TABLE event_date (
    id serial  NOT NULL,
    event_id int  NOT NULL,
    date date  NOT NULL,
    volunteers_required int  NOT NULL,
    volunteers_assigned int  NOT NULL DEFAULT 0,
    is_active boolean  NOT NULL,
    CONSTRAINT event_date_pk PRIMARY KEY (id)
);

-- Table: language
CREATE TABLE language (
    id serial  NOT NULL,
    name varchar(255)  NOT NULL,
    CONSTRAINT language_pk PRIMARY KEY (id)
);

-- Table: location
CREATE TABLE location (
    id serial  NOT NULL,
    county_id int  NOT NULL,
    address varchar(255)  NOT NULL,
    longtitude decimal(8,6)  NULL,
    latitude decimal(8,6)  NULL,
    CONSTRAINT location_pk PRIMARY KEY (id)
);

-- Table: role
CREATE TABLE role (
    id serial  NOT NULL,
    name varchar(11)  NOT NULL,
    CONSTRAINT role_pk PRIMARY KEY (id)
);

-- Table: task
CREATE TABLE task (
    id serial  NOT NULL,
    name varchar(255)  NULL,
    event_id int  NOT NULL,
    CONSTRAINT task_pk PRIMARY KEY (id)
);

-- Table: user
CREATE TABLE "user" (
    id serial  NOT NULL,
    role_id int  NOT NULL,
    contact_id int  NULL,
    username varchar(255)  NOT NULL,
    password varchar(255)  NOT NULL,
    status boolean  NOT NULL DEFAULT TRUE,
    CONSTRAINT user_ak_1 UNIQUE (username) NOT DEFERRABLE  INITIALLY IMMEDIATE,
    CONSTRAINT user_pk PRIMARY KEY (id)
);

-- Table: volunteer
CREATE TABLE volunteer (
    id serial  NOT NULL,
    user_id int  NOT NULL,
    event_id int  NOT NULL,
    volunteers_size int  NOT NULL DEFAULT 1,
    info varchar(1000)  NULL,
    CONSTRAINT volunteer_pk PRIMARY KEY (id)
);

-- Table: volunteer_event_date
CREATE TABLE volunteer_event_date (
    id serial  NOT NULL,
    volunteer_id int  NOT NULL,
    event_date_id int  NOT NULL,
    CONSTRAINT volunteer_event_date_pk PRIMARY KEY (id)
);

-- Table: volunteer_task
CREATE TABLE volunteer_task (
    id serial  NOT NULL,
    volunteer_id int  NOT NULL,
    task_id int  NOT NULL,
    CONSTRAINT volunteer_task_pk PRIMARY KEY (id)
);

-- foreign keys
-- Reference: event_category (table: event)
ALTER TABLE event ADD CONSTRAINT event_category
    FOREIGN KEY (category_id)
    REFERENCES category (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: event_date_event (table: event_date)
ALTER TABLE event_date ADD CONSTRAINT event_date_event
    FOREIGN KEY (event_id)
    REFERENCES event (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: event_language (table: event)
ALTER TABLE event ADD CONSTRAINT event_language
    FOREIGN KEY (language_id)
    REFERENCES language (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: event_location (table: event)
ALTER TABLE event ADD CONSTRAINT event_location
    FOREIGN KEY (location_id)
    REFERENCES location (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: event_user (table: event)
ALTER TABLE event ADD CONSTRAINT event_user
    FOREIGN KEY (user_id)
    REFERENCES "user" (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: location_county (table: location)
ALTER TABLE location ADD CONSTRAINT location_county
    FOREIGN KEY (county_id)
    REFERENCES county (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: task_event (table: task)
ALTER TABLE task ADD CONSTRAINT task_event
    FOREIGN KEY (event_id)
    REFERENCES event (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: user_contact (table: user)
ALTER TABLE "user" ADD CONSTRAINT user_contact
    FOREIGN KEY (contact_id)
    REFERENCES contact (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: user_role (table: user)
ALTER TABLE "user" ADD CONSTRAINT user_role
    FOREIGN KEY (role_id)
    REFERENCES role (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: volunteer_event (table: volunteer)
ALTER TABLE volunteer ADD CONSTRAINT volunteer_event
    FOREIGN KEY (event_id)
    REFERENCES event (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: volunteer_event_date_event_date (table: volunteer_event_date)
ALTER TABLE volunteer_event_date ADD CONSTRAINT volunteer_event_date_event_date
    FOREIGN KEY (event_date_id)
    REFERENCES event_date (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: volunteer_event_date_volunteer (table: volunteer_event_date)
ALTER TABLE volunteer_event_date ADD CONSTRAINT volunteer_event_date_volunteer
    FOREIGN KEY (volunteer_id)
    REFERENCES volunteer (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: volunteer_task_task (table: volunteer_task)
ALTER TABLE volunteer_task ADD CONSTRAINT volunteer_task_task
    FOREIGN KEY (task_id)
    REFERENCES task (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: volunteer_task_volunteer (table: volunteer_task)
ALTER TABLE volunteer_task ADD CONSTRAINT volunteer_task_volunteer
    FOREIGN KEY (volunteer_id)
    REFERENCES volunteer (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: volunteer_user (table: volunteer)
ALTER TABLE volunteer ADD CONSTRAINT volunteer_user
    FOREIGN KEY (user_id)
    REFERENCES "user" (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- End of file.

