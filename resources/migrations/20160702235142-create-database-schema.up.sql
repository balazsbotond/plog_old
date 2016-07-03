--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.3
-- Dumped by pg_dump version 9.5.3

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: -
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: -
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: post; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE post (
    id bigint NOT NULL,
    created timestamp without time zone DEFAULT timezone('utc'::text, now()) NOT NULL,
    last_modified timestamp without time zone DEFAULT timezone('utc'::text, now()) NOT NULL,
    text text NOT NULL
);


--
-- Name: post_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE post_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: post_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE post_id_seq OWNED BY post.id;


--
-- Name: post_tag; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE post_tag (
    post_id bigint NOT NULL,
    tag_id bigint NOT NULL
);


--
-- Name: tag; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE tag (
    id bigint NOT NULL,
    name text NOT NULL,
    CONSTRAINT chk_tag_name_max_length CHECK ((length(name) <= 50))
);


--
-- Name: tag_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE tag_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: tag_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE tag_id_seq OWNED BY tag.id;


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY post ALTER COLUMN id SET DEFAULT nextval('post_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY tag ALTER COLUMN id SET DEFAULT nextval('tag_id_seq'::regclass);


--
-- Name: pk_post; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY post
    ADD CONSTRAINT pk_post PRIMARY KEY (id);


--
-- Name: pk_post_tag; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY post_tag
    ADD CONSTRAINT pk_post_tag PRIMARY KEY (post_id, tag_id);


--
-- Name: pk_tag; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY tag
    ADD CONSTRAINT pk_tag PRIMARY KEY (id);


--
-- Name: fk_post_tag_post_id; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY post_tag
    ADD CONSTRAINT fk_post_tag_post_id FOREIGN KEY (post_id) REFERENCES post(id);


--
-- Name: fk_post_tag_tag_id; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY post_tag
    ADD CONSTRAINT fk_post_tag_tag_id FOREIGN KEY (tag_id) REFERENCES tag(id);


--
-- Name: public; Type: ACL; Schema: -; Owner: -
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

