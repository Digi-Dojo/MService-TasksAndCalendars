--
-- PostgreSQL database dump
--

-- Dumped from database version 15.2
-- Dumped by pg_dump version 15.2

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

DROP DATABASE IF EXISTS postgres;
--
-- Name: postgres; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE postgres WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = icu LOCALE = 'en_US.UTF-8' ICU_LOCALE = 'en-US';


ALTER DATABASE postgres OWNER TO postgres;

\connect postgres

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: DATABASE postgres; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON DATABASE postgres IS 'default administrative connection database';


--
-- Name: public; Type: SCHEMA; Schema: -; Owner: pg_database_owner
--

CREATE SCHEMA public;


ALTER SCHEMA public OWNER TO pg_database_owner;

--
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: pg_database_owner
--

COMMENT ON SCHEMA public IS 'standard public schema';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: calendar_event_tags; Type: TABLE; Schema: public; Owner: mirko
--

CREATE TABLE public.calendar_event_tags (
    calendar_event_id integer NOT NULL,
    tag_id integer NOT NULL
);


ALTER TABLE public.calendar_event_tags OWNER TO mirko;

--
-- Name: calendar_events; Type: TABLE; Schema: public; Owner: mirko
--

CREATE TABLE public.calendar_events (
    id integer NOT NULL,
    description character varying(255) NOT NULL,
    start_date timestamp without time zone NOT NULL,
    end_date timestamp without time zone NOT NULL,
    user_id integer,
    place_id integer,
    tags character varying(255)[],
    title character varying(255),
    trello_card_id character varying(255)
);


ALTER TABLE public.calendar_events OWNER TO mirko;

--
-- Name: calendar_events_id_seq; Type: SEQUENCE; Schema: public; Owner: mirko
--

CREATE SEQUENCE public.calendar_events_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.calendar_events_id_seq OWNER TO mirko;

--
-- Name: calendar_events_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: mirko
--

ALTER SEQUENCE public.calendar_events_id_seq OWNED BY public.calendar_events.id;


--
-- Name: place; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.place (
    id bigint NOT NULL,
    name character varying(255)
);


ALTER TABLE public.place OWNER TO postgres;

--
-- Name: place_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.place_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.place_id_seq OWNER TO postgres;

--
-- Name: place_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.place_id_seq OWNED BY public.place.id;


--
-- Name: places; Type: TABLE; Schema: public; Owner: mirko
--

CREATE TABLE public.places (
    id integer NOT NULL,
    name character varying(50) NOT NULL,
    address character varying(100) NOT NULL
);


ALTER TABLE public.places OWNER TO mirko;

--
-- Name: places_id_seq; Type: SEQUENCE; Schema: public; Owner: mirko
--

CREATE SEQUENCE public.places_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.places_id_seq OWNER TO mirko;

--
-- Name: places_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: mirko
--

ALTER SEQUENCE public.places_id_seq OWNED BY public.places.id;


--
-- Name: tags; Type: TABLE; Schema: public; Owner: mirko
--

CREATE TABLE public.tags (
    id integer NOT NULL,
    name character varying(50) NOT NULL
);


ALTER TABLE public.tags OWNER TO mirko;

--
-- Name: tags_id_seq; Type: SEQUENCE; Schema: public; Owner: mirko
--

CREATE SEQUENCE public.tags_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tags_id_seq OWNER TO mirko;

--
-- Name: tags_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: mirko
--

ALTER SEQUENCE public.tags_id_seq OWNED BY public.tags.id;


--
-- Name: task_tags; Type: TABLE; Schema: public; Owner: mirko
--

CREATE TABLE public.task_tags (
    task_id integer NOT NULL,
    tag_id integer NOT NULL
);


ALTER TABLE public.task_tags OWNER TO mirko;

--
-- Name: tasks; Type: TABLE; Schema: public; Owner: mirko
--

CREATE TABLE public.tasks (
    id bigint NOT NULL,
    description character varying(255) NOT NULL,
    status character varying(10) NOT NULL,
    user_id integer,
    place_id integer,
    tags character varying(255)[]
);


ALTER TABLE public.tasks OWNER TO mirko;

--
-- Name: tasks_id_seq; Type: SEQUENCE; Schema: public; Owner: mirko
--

CREATE SEQUENCE public.tasks_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tasks_id_seq OWNER TO mirko;

--
-- Name: tasks_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: mirko
--

ALTER SEQUENCE public.tasks_id_seq OWNED BY public.tasks.id;


--
-- Name: users; Type: TABLE; Schema: public; Owner: mirko
--

CREATE TABLE public.users (
    id integer NOT NULL,
    name character varying(50) NOT NULL
);


ALTER TABLE public.users OWNER TO mirko;

--
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: mirko
--

CREATE SEQUENCE public.users_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.users_id_seq OWNER TO mirko;

--
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: mirko
--

ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;


--
-- Name: calendar_events id; Type: DEFAULT; Schema: public; Owner: mirko
--

ALTER TABLE ONLY public.calendar_events ALTER COLUMN id SET DEFAULT nextval('public.calendar_events_id_seq'::regclass);


--
-- Name: place id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.place ALTER COLUMN id SET DEFAULT nextval('public.place_id_seq'::regclass);


--
-- Name: places id; Type: DEFAULT; Schema: public; Owner: mirko
--

ALTER TABLE ONLY public.places ALTER COLUMN id SET DEFAULT nextval('public.places_id_seq'::regclass);


--
-- Name: tags id; Type: DEFAULT; Schema: public; Owner: mirko
--

ALTER TABLE ONLY public.tags ALTER COLUMN id SET DEFAULT nextval('public.tags_id_seq'::regclass);


--
-- Name: tasks id; Type: DEFAULT; Schema: public; Owner: mirko
--

ALTER TABLE ONLY public.tasks ALTER COLUMN id SET DEFAULT nextval('public.tasks_id_seq'::regclass);


--
-- Name: users id; Type: DEFAULT; Schema: public; Owner: mirko
--

ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);


--
-- Data for Name: calendar_event_tags; Type: TABLE DATA; Schema: public; Owner: mirko
--



--
-- Data for Name: calendar_events; Type: TABLE DATA; Schema: public; Owner: mirko
--



--
-- Data for Name: place; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- Data for Name: places; Type: TABLE DATA; Schema: public; Owner: mirko
--



--
-- Data for Name: tags; Type: TABLE DATA; Schema: public; Owner: mirko
--



--
-- Data for Name: task_tags; Type: TABLE DATA; Schema: public; Owner: mirko
--



--
-- Data for Name: tasks; Type: TABLE DATA; Schema: public; Owner: mirko
--

INSERT INTO public.tasks (id, description, status, user_id, place_id, tags) VALUES (5869473925487263, '"Task Description"', '1', NULL, NULL, NULL);
INSERT INTO public.tasks (id, description, status, user_id, place_id, tags) VALUES (2, 'Task description', '0', NULL, NULL, NULL);
INSERT INTO public.tasks (id, description, status, user_id, place_id, tags) VALUES (3, 'Task descript6666', '0', NULL, NULL, NULL);


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: mirko
--



--
-- Name: calendar_events_id_seq; Type: SEQUENCE SET; Schema: public; Owner: mirko
--

SELECT pg_catalog.setval('public.calendar_events_id_seq', 1, false);


--
-- Name: place_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.place_id_seq', 1, false);


--
-- Name: places_id_seq; Type: SEQUENCE SET; Schema: public; Owner: mirko
--

SELECT pg_catalog.setval('public.places_id_seq', 1, false);


--
-- Name: tags_id_seq; Type: SEQUENCE SET; Schema: public; Owner: mirko
--

SELECT pg_catalog.setval('public.tags_id_seq', 1, false);


--
-- Name: tasks_id_seq; Type: SEQUENCE SET; Schema: public; Owner: mirko
--

SELECT pg_catalog.setval('public.tasks_id_seq', 3, true);


--
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: mirko
--

SELECT pg_catalog.setval('public.users_id_seq', 1, false);


--
-- Name: calendar_event_tags calendar_event_tags_pkey; Type: CONSTRAINT; Schema: public; Owner: mirko
--

ALTER TABLE ONLY public.calendar_event_tags
    ADD CONSTRAINT calendar_event_tags_pkey PRIMARY KEY (calendar_event_id, tag_id);


--
-- Name: calendar_events calendar_events_pkey; Type: CONSTRAINT; Schema: public; Owner: mirko
--

ALTER TABLE ONLY public.calendar_events
    ADD CONSTRAINT calendar_events_pkey PRIMARY KEY (id);


--
-- Name: place place_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.place
    ADD CONSTRAINT place_pkey PRIMARY KEY (id);


--
-- Name: places places_pkey; Type: CONSTRAINT; Schema: public; Owner: mirko
--

ALTER TABLE ONLY public.places
    ADD CONSTRAINT places_pkey PRIMARY KEY (id);


--
-- Name: tags tags_pkey; Type: CONSTRAINT; Schema: public; Owner: mirko
--

ALTER TABLE ONLY public.tags
    ADD CONSTRAINT tags_pkey PRIMARY KEY (id);


--
-- Name: task_tags task_tags_pkey; Type: CONSTRAINT; Schema: public; Owner: mirko
--

ALTER TABLE ONLY public.task_tags
    ADD CONSTRAINT task_tags_pkey PRIMARY KEY (task_id, tag_id);


--
-- Name: tasks tasks_pkey; Type: CONSTRAINT; Schema: public; Owner: mirko
--

ALTER TABLE ONLY public.tasks
    ADD CONSTRAINT tasks_pkey PRIMARY KEY (id);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: mirko
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- Name: calendar_event_tags calendar_event_tags_calendar_event_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: mirko
--

ALTER TABLE ONLY public.calendar_event_tags
    ADD CONSTRAINT calendar_event_tags_calendar_event_id_fkey FOREIGN KEY (calendar_event_id) REFERENCES public.calendar_events(id);


--
-- Name: calendar_event_tags calendar_event_tags_tag_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: mirko
--

ALTER TABLE ONLY public.calendar_event_tags
    ADD CONSTRAINT calendar_event_tags_tag_id_fkey FOREIGN KEY (tag_id) REFERENCES public.tags(id);


--
-- Name: calendar_events calendar_events_place_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: mirko
--

ALTER TABLE ONLY public.calendar_events
    ADD CONSTRAINT calendar_events_place_id_fkey FOREIGN KEY (place_id) REFERENCES public.places(id);


--
-- Name: calendar_events calendar_events_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: mirko
--

ALTER TABLE ONLY public.calendar_events
    ADD CONSTRAINT calendar_events_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- Name: task_tags task_tags_tag_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: mirko
--

ALTER TABLE ONLY public.task_tags
    ADD CONSTRAINT task_tags_tag_id_fkey FOREIGN KEY (tag_id) REFERENCES public.tags(id);


--
-- Name: task_tags task_tags_task_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: mirko
--

ALTER TABLE ONLY public.task_tags
    ADD CONSTRAINT task_tags_task_id_fkey FOREIGN KEY (task_id) REFERENCES public.tasks(id);


--
-- Name: tasks tasks_place_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: mirko
--

ALTER TABLE ONLY public.tasks
    ADD CONSTRAINT tasks_place_id_fkey FOREIGN KEY (place_id) REFERENCES public.places(id);


--
-- Name: tasks tasks_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: mirko
--

ALTER TABLE ONLY public.tasks
    ADD CONSTRAINT tasks_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- PostgreSQL database dump complete
--

