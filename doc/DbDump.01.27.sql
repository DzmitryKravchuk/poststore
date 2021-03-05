--
-- PostgreSQL database dump
--

-- Dumped from database version 10.5
-- Dumped by pg_dump version 10.0

-- Started on 2019-01-27 19:02:59

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 1 (class 3079 OID 12924)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2993 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 196 (class 1259 OID 26803)
-- Name: country; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE country (
    id integer NOT NULL,
    name character varying NOT NULL,
    express_zone_id integer NOT NULL,
    letter_zone_id integer NOT NULL,
    parcel_zone_id integer NOT NULL,
    created timestamp without time zone NOT NULL,
    updated timestamp without time zone NOT NULL
);


ALTER TABLE country OWNER TO postgres;

--
-- TOC entry 197 (class 1259 OID 26809)
-- Name: country_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE country_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE country_id_seq OWNER TO postgres;

--
-- TOC entry 2994 (class 0 OID 0)
-- Dependencies: 197
-- Name: country_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE country_id_seq OWNED BY country.id;


--
-- TOC entry 198 (class 1259 OID 26811)
-- Name: courier; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE courier (
    id integer NOT NULL,
    shipping_from character varying NOT NULL,
    shipping_to character varying NOT NULL,
    price numeric NOT NULL,
    user_account_id integer NOT NULL,
    addressee character varying NOT NULL,
    created timestamp without time zone NOT NULL,
    updated timestamp without time zone NOT NULL
);


ALTER TABLE courier OWNER TO postgres;

--
-- TOC entry 199 (class 1259 OID 26817)
-- Name: courier_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE courier_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE courier_id_seq OWNER TO postgres;

--
-- TOC entry 2995 (class 0 OID 0)
-- Dependencies: 199
-- Name: courier_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE courier_id_seq OWNED BY courier.id;


--
-- TOC entry 200 (class 1259 OID 26819)
-- Name: express_zone; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE express_zone (
    id integer NOT NULL,
    price4g100 numeric NOT NULL,
    created timestamp without time zone NOT NULL,
    updated timestamp without time zone NOT NULL
);


ALTER TABLE express_zone OWNER TO postgres;

--
-- TOC entry 201 (class 1259 OID 26825)
-- Name: express_zone_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE express_zone_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE express_zone_id_seq OWNER TO postgres;

--
-- TOC entry 2996 (class 0 OID 0)
-- Dependencies: 201
-- Name: express_zone_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE express_zone_id_seq OWNED BY express_zone.id;


--
-- TOC entry 202 (class 1259 OID 26827)
-- Name: inventory; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE inventory (
    id integer NOT NULL,
    product_id integer NOT NULL,
    store_id integer NOT NULL,
    quantity integer NOT NULL,
    created timestamp without time zone NOT NULL,
    updated timestamp without time zone NOT NULL,
    version integer NOT NULL
);


ALTER TABLE inventory OWNER TO postgres;

--
-- TOC entry 203 (class 1259 OID 26830)
-- Name: inventory_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE inventory_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE inventory_id_seq OWNER TO postgres;

--
-- TOC entry 2997 (class 0 OID 0)
-- Dependencies: 203
-- Name: inventory_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE inventory_id_seq OWNED BY inventory.id;


--
-- TOC entry 204 (class 1259 OID 26832)
-- Name: letter_zone; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE letter_zone (
    id integer NOT NULL,
    price4g100 numeric NOT NULL,
    created timestamp without time zone NOT NULL,
    updated timestamp without time zone NOT NULL
);


ALTER TABLE letter_zone OWNER TO postgres;

--
-- TOC entry 205 (class 1259 OID 26838)
-- Name: letter_zone_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE letter_zone_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE letter_zone_id_seq OWNER TO postgres;

--
-- TOC entry 2998 (class 0 OID 0)
-- Dependencies: 205
-- Name: letter_zone_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE letter_zone_id_seq OWNED BY letter_zone.id;


--
-- TOC entry 206 (class 1259 OID 26840)
-- Name: mailing; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE mailing (
    id integer NOT NULL,
    mailing_type character varying NOT NULL,
    weight numeric NOT NULL,
    user_account_id integer NOT NULL,
    addressee character varying NOT NULL,
    country_id integer NOT NULL,
    address character varying NOT NULL,
    price numeric NOT NULL,
    created timestamp without time zone NOT NULL,
    updated timestamp without time zone NOT NULL
);


ALTER TABLE mailing OWNER TO postgres;

--
-- TOC entry 207 (class 1259 OID 26846)
-- Name: mailing_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE mailing_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mailing_id_seq OWNER TO postgres;

--
-- TOC entry 2999 (class 0 OID 0)
-- Dependencies: 207
-- Name: mailing_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE mailing_id_seq OWNED BY mailing.id;


--
-- TOC entry 208 (class 1259 OID 26848)
-- Name: money_transfer; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE money_transfer (
    id integer NOT NULL,
    payer_name character varying NOT NULL,
    payer_adress character varying NOT NULL,
    beneficiary_name character varying NOT NULL,
    beneficiary_adress character varying NOT NULL,
    amount numeric NOT NULL,
    transaction_price numeric NOT NULL,
    user_account_id integer NOT NULL,
    created timestamp without time zone NOT NULL,
    updated timestamp without time zone NOT NULL
);


ALTER TABLE money_transfer OWNER TO postgres;

--
-- TOC entry 209 (class 1259 OID 26854)
-- Name: money_transfer_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE money_transfer_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE money_transfer_id_seq OWNER TO postgres;

--
-- TOC entry 3000 (class 0 OID 0)
-- Dependencies: 209
-- Name: money_transfer_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE money_transfer_id_seq OWNED BY money_transfer.id;


--
-- TOC entry 210 (class 1259 OID 26856)
-- Name: order_item; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE order_item (
    id integer NOT NULL,
    product_id integer NOT NULL,
    quantity integer NOT NULL,
    order_product_id integer NOT NULL,
    created timestamp without time zone NOT NULL,
    updated timestamp without time zone NOT NULL
);


ALTER TABLE order_item OWNER TO postgres;

--
-- TOC entry 211 (class 1259 OID 26859)
-- Name: order_item_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE order_item_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE order_item_id_seq OWNER TO postgres;

--
-- TOC entry 3001 (class 0 OID 0)
-- Dependencies: 211
-- Name: order_item_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE order_item_id_seq OWNED BY order_item.id;


--
-- TOC entry 212 (class 1259 OID 26861)
-- Name: order_product; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE order_product (
    id integer NOT NULL,
    shipping_to character varying,
    shipping_type character varying NOT NULL,
    user_account_id integer NOT NULL,
    cost numeric NOT NULL,
    status character varying NOT NULL,
    created timestamp without time zone NOT NULL,
    updated timestamp without time zone NOT NULL
);


ALTER TABLE order_product OWNER TO postgres;

--
-- TOC entry 213 (class 1259 OID 26867)
-- Name: order_product_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE order_product_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE order_product_id_seq OWNER TO postgres;

--
-- TOC entry 3002 (class 0 OID 0)
-- Dependencies: 213
-- Name: order_product_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE order_product_id_seq OWNED BY order_product.id;


--
-- TOC entry 214 (class 1259 OID 26869)
-- Name: paper_details; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE paper_details (
    id integer NOT NULL,
    name character varying NOT NULL,
    price numeric NOT NULL,
    created timestamp without time zone NOT NULL,
    updated timestamp without time zone NOT NULL
);


ALTER TABLE paper_details OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 26875)
-- Name: paper_details_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE paper_details_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE paper_details_id_seq OWNER TO postgres;

--
-- TOC entry 3003 (class 0 OID 0)
-- Dependencies: 215
-- Name: paper_details_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE paper_details_id_seq OWNED BY paper_details.id;


--
-- TOC entry 216 (class 1259 OID 26877)
-- Name: parcel_zone; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE parcel_zone (
    id integer NOT NULL,
    price4g100 numeric NOT NULL,
    created timestamp without time zone NOT NULL,
    updated timestamp without time zone NOT NULL
);


ALTER TABLE parcel_zone OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 26883)
-- Name: parcel_zone_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE parcel_zone_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE parcel_zone_id_seq OWNER TO postgres;

--
-- TOC entry 3004 (class 0 OID 0)
-- Dependencies: 217
-- Name: parcel_zone_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE parcel_zone_id_seq OWNED BY parcel_zone.id;


--
-- TOC entry 218 (class 1259 OID 26885)
-- Name: polygraphy; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE polygraphy (
    id integer NOT NULL,
    image_format character varying NOT NULL,
    copy_count integer NOT NULL,
    is_coloured boolean NOT NULL,
    is_duplex_printing boolean NOT NULL,
    price numeric NOT NULL,
    user_account_id integer NOT NULL,
    paper_details_id integer NOT NULL,
    created timestamp without time zone NOT NULL,
    updated timestamp without time zone NOT NULL
);


ALTER TABLE polygraphy OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 26891)
-- Name: polygraphy_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE polygraphy_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE polygraphy_id_seq OWNER TO postgres;

--
-- TOC entry 3005 (class 0 OID 0)
-- Dependencies: 219
-- Name: polygraphy_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE polygraphy_id_seq OWNED BY polygraphy.id;


--
-- TOC entry 220 (class 1259 OID 26893)
-- Name: product; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE product (
    id integer NOT NULL,
    name character varying NOT NULL,
    price numeric NOT NULL,
    created timestamp without time zone NOT NULL,
    updated timestamp without time zone NOT NULL
);


ALTER TABLE product OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 26899)
-- Name: product_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE product_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE product_id_seq OWNER TO postgres;

--
-- TOC entry 3006 (class 0 OID 0)
-- Dependencies: 221
-- Name: product_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE product_id_seq OWNED BY product.id;


--
-- TOC entry 222 (class 1259 OID 26901)
-- Name: store; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE store (
    id integer NOT NULL,
    store_type character varying NOT NULL,
    created timestamp without time zone NOT NULL,
    updated timestamp without time zone NOT NULL
);


ALTER TABLE store OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 26907)
-- Name: store_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE store_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE store_id_seq OWNER TO postgres;

--
-- TOC entry 3007 (class 0 OID 0)
-- Dependencies: 223
-- Name: store_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE store_id_seq OWNED BY store.id;


--
-- TOC entry 224 (class 1259 OID 26909)
-- Name: user_account; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE user_account (
    id integer NOT NULL,
    e_mail character varying NOT NULL,
    password character varying NOT NULL,
    user_role character varying NOT NULL,
    created timestamp without time zone NOT NULL,
    updated timestamp without time zone NOT NULL
);


ALTER TABLE user_account OWNER TO postgres;

--
-- TOC entry 225 (class 1259 OID 26915)
-- Name: user_account_details; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE user_account_details (
    id integer NOT NULL,
    name character varying NOT NULL,
    adress character varying,
    phone integer,
    created timestamp without time zone NOT NULL,
    updated timestamp without time zone NOT NULL
);


ALTER TABLE user_account_details OWNER TO postgres;

--
-- TOC entry 226 (class 1259 OID 26921)
-- Name: user_account_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE user_account_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE user_account_id_seq OWNER TO postgres;

--
-- TOC entry 3008 (class 0 OID 0)
-- Dependencies: 226
-- Name: user_account_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE user_account_id_seq OWNED BY user_account.id;


--
-- TOC entry 2772 (class 2604 OID 26923)
-- Name: country id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY country ALTER COLUMN id SET DEFAULT nextval('country_id_seq'::regclass);


--
-- TOC entry 2773 (class 2604 OID 26924)
-- Name: courier id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY courier ALTER COLUMN id SET DEFAULT nextval('courier_id_seq'::regclass);


--
-- TOC entry 2774 (class 2604 OID 26925)
-- Name: express_zone id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY express_zone ALTER COLUMN id SET DEFAULT nextval('express_zone_id_seq'::regclass);


--
-- TOC entry 2775 (class 2604 OID 26926)
-- Name: inventory id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY inventory ALTER COLUMN id SET DEFAULT nextval('inventory_id_seq'::regclass);


--
-- TOC entry 2776 (class 2604 OID 26927)
-- Name: letter_zone id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY letter_zone ALTER COLUMN id SET DEFAULT nextval('letter_zone_id_seq'::regclass);


--
-- TOC entry 2777 (class 2604 OID 26928)
-- Name: mailing id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY mailing ALTER COLUMN id SET DEFAULT nextval('mailing_id_seq'::regclass);


--
-- TOC entry 2778 (class 2604 OID 26929)
-- Name: money_transfer id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY money_transfer ALTER COLUMN id SET DEFAULT nextval('money_transfer_id_seq'::regclass);


--
-- TOC entry 2779 (class 2604 OID 26930)
-- Name: order_item id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY order_item ALTER COLUMN id SET DEFAULT nextval('order_item_id_seq'::regclass);


--
-- TOC entry 2780 (class 2604 OID 26931)
-- Name: order_product id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY order_product ALTER COLUMN id SET DEFAULT nextval('order_product_id_seq'::regclass);


--
-- TOC entry 2781 (class 2604 OID 26932)
-- Name: paper_details id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY paper_details ALTER COLUMN id SET DEFAULT nextval('paper_details_id_seq'::regclass);


--
-- TOC entry 2782 (class 2604 OID 26933)
-- Name: parcel_zone id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY parcel_zone ALTER COLUMN id SET DEFAULT nextval('parcel_zone_id_seq'::regclass);


--
-- TOC entry 2783 (class 2604 OID 26934)
-- Name: polygraphy id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY polygraphy ALTER COLUMN id SET DEFAULT nextval('polygraphy_id_seq'::regclass);


--
-- TOC entry 2784 (class 2604 OID 26935)
-- Name: product id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY product ALTER COLUMN id SET DEFAULT nextval('product_id_seq'::regclass);


--
-- TOC entry 2785 (class 2604 OID 26936)
-- Name: store id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY store ALTER COLUMN id SET DEFAULT nextval('store_id_seq'::regclass);


--
-- TOC entry 2786 (class 2604 OID 26937)
-- Name: user_account id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY user_account ALTER COLUMN id SET DEFAULT nextval('user_account_id_seq'::regclass);


--
-- TOC entry 2957 (class 0 OID 26803)
-- Dependencies: 196
-- Data for Name: country; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO country (id, name, express_zone_id, letter_zone_id, parcel_zone_id, created, updated) VALUES (5, 'США', 2, 2, 3, '2019-01-23 16:44:04.117', '2019-01-23 16:44:04.117');
INSERT INTO country (id, name, express_zone_id, letter_zone_id, parcel_zone_id, created, updated) VALUES (6, 'Россия', 4, 1, 2, '2019-01-23 16:44:36.99', '2019-01-23 16:44:36.99');
INSERT INTO country (id, name, express_zone_id, letter_zone_id, parcel_zone_id, created, updated) VALUES (7, 'Беларусь', 3, 3, 1, '2019-01-23 16:45:12.186', '2019-01-23 16:45:12.186');


--
-- TOC entry 2959 (class 0 OID 26811)
-- Dependencies: 198
-- Data for Name: courier; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO courier (id, shipping_from, shipping_to, price, user_account_id, addressee, created, updated) VALUES (7, 'Пушкина,3', 'Горького,5', 9, 1, 'Горький М.', '2019-01-26 21:25:23.763', '2019-01-26 21:25:23.763');
INSERT INTO courier (id, shipping_from, shipping_to, price, user_account_id, addressee, created, updated) VALUES (8, 'Пушкина,3', 'Дубко, 5', 9, 1, 'Хреновский Б.А.', '2019-01-26 21:26:16.054', '2019-01-26 21:26:16.054');


--
-- TOC entry 2961 (class 0 OID 26819)
-- Dependencies: 200
-- Data for Name: express_zone; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO express_zone (id, price4g100, created, updated) VALUES (3, 3.13, '2019-01-08 20:39:21.105', '2019-01-08 20:39:21.105');
INSERT INTO express_zone (id, price4g100, created, updated) VALUES (2, 7.12, '2019-01-08 20:39:11.646', '2019-01-08 20:40:50.804');
INSERT INTO express_zone (id, price4g100, created, updated) VALUES (4, 4.42, '2019-01-09 13:58:08.634', '2019-01-26 21:02:59.459');


--
-- TOC entry 2963 (class 0 OID 26827)
-- Dependencies: 202
-- Data for Name: inventory; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO inventory (id, product_id, store_id, quantity, created, updated, version) VALUES (16, 3, 2, 3, '2019-01-23 17:04:50.433', '2019-01-27 15:59:11.448', 6);
INSERT INTO inventory (id, product_id, store_id, quantity, created, updated, version) VALUES (17, 2, 2, 42, '2019-01-23 17:04:50.499', '2019-01-27 15:59:11.453', 9);
INSERT INTO inventory (id, product_id, store_id, quantity, created, updated, version) VALUES (18, 4, 2, 5, '2019-01-23 17:04:50.581', '2019-01-27 15:59:11.457', 5);
INSERT INTO inventory (id, product_id, store_id, quantity, created, updated, version) VALUES (19, 5, 2, 0, '2019-01-23 17:04:50.626', '2019-01-27 15:59:11.462', 8);
INSERT INTO inventory (id, product_id, store_id, quantity, created, updated, version) VALUES (20, 7, 2, 100, '2019-01-23 17:04:50.706', '2019-01-27 15:59:11.467', 11);
INSERT INTO inventory (id, product_id, store_id, quantity, created, updated, version) VALUES (21, 9, 2, 1, '2019-01-23 17:04:50.773', '2019-01-27 15:59:11.478', 7);
INSERT INTO inventory (id, product_id, store_id, quantity, created, updated, version) VALUES (15, 8, 2, 0, '2019-01-23 16:57:41.307', '2019-01-27 15:59:25.724', 10);
INSERT INTO inventory (id, product_id, store_id, quantity, created, updated, version) VALUES (3, 2, 1, 4373, '2019-01-18 12:09:15.712', '2019-01-27 16:19:17.754', 7);
INSERT INTO inventory (id, product_id, store_id, quantity, created, updated, version) VALUES (22, 10, 1, 5000, '2019-01-27 16:19:29.12', '2019-01-27 16:19:29.168', 1);
INSERT INTO inventory (id, product_id, store_id, quantity, created, updated, version) VALUES (5, 5, 1, 4320, '2019-01-18 12:09:59.601', '2018-12-23 06:56:40.668', 4);
INSERT INTO inventory (id, product_id, store_id, quantity, created, updated, version) VALUES (12, 6, 1, 4420, '2019-01-23 16:55:45.624', '2018-12-23 06:56:40.68', 5);
INSERT INTO inventory (id, product_id, store_id, quantity, created, updated, version) VALUES (13, 8, 1, 4335, '2019-01-23 16:56:00.154', '2018-12-23 06:56:40.698', 6);
INSERT INTO inventory (id, product_id, store_id, quantity, created, updated, version) VALUES (14, 6, 2, 150, '2019-01-23 16:57:41.257', '2018-12-23 06:59:26.088', 7);
INSERT INTO inventory (id, product_id, store_id, quantity, created, updated, version) VALUES (2, 3, 1, 4841, '2019-01-18 12:08:33.852', '2019-01-27 15:37:00.289', 4);
INSERT INTO inventory (id, product_id, store_id, quantity, created, updated, version) VALUES (4, 4, 1, 4494, '2019-01-18 12:09:46.12', '2019-01-27 15:37:00.335', 3);
INSERT INTO inventory (id, product_id, store_id, quantity, created, updated, version) VALUES (7, 7, 1, 4319, '2019-01-18 12:10:24.738', '2019-01-27 15:45:40.593', 6);
INSERT INTO inventory (id, product_id, store_id, quantity, created, updated, version) VALUES (9, 9, 1, 4866, '2019-01-18 12:10:50.116', '2019-01-27 15:45:40.628', 4);


--
-- TOC entry 2965 (class 0 OID 26832)
-- Dependencies: 204
-- Data for Name: letter_zone; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO letter_zone (id, price4g100, created, updated) VALUES (1, 2.15, '2019-01-08 20:39:34.366', '2019-01-08 20:40:01.591');
INSERT INTO letter_zone (id, price4g100, created, updated) VALUES (3, 1.12, '2019-01-08 20:39:51.626', '2019-01-26 21:03:25.356');
INSERT INTO letter_zone (id, price4g100, created, updated) VALUES (2, 3.22, '2019-01-08 20:39:43.755', '2019-01-26 21:03:46.023');


--
-- TOC entry 2967 (class 0 OID 26840)
-- Dependencies: 206
-- Data for Name: mailing; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO mailing (id, mailing_type, weight, user_account_id, addressee, country_id, address, price, created, updated) VALUES (14, 'letter', 0.25, 1, 'Ivanov', 5, 'New York, 5-th avenue, 24-85', 8.05, '2019-01-26 22:08:22.72', '2019-01-26 22:08:22.72');
INSERT INTO mailing (id, mailing_type, weight, user_account_id, addressee, country_id, address, price, created, updated) VALUES (15, 'parcel', 5, 1, 'Хреновский', 6, 'Москва, ул. Весенняя, 5', 156.5, '2019-01-26 22:08:50.433', '2019-01-26 22:08:50.433');
INSERT INTO mailing (id, mailing_type, weight, user_account_id, addressee, country_id, address, price, created, updated) VALUES (16, 'letter', 0.3, 1, 'Хреновский', 7, 'Минск, ул. Зимняя, 5', 3.36, '2019-01-26 22:12:20.19', '2019-01-26 22:12:20.19');


--
-- TOC entry 2969 (class 0 OID 26848)
-- Dependencies: 208
-- Data for Name: money_transfer; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO money_transfer (id, payer_name, payer_adress, beneficiary_name, beneficiary_adress, amount, transaction_price, user_account_id, created, updated) VALUES (9, 'Петров', 'г.Гродно, ул. Пушкина,5', 'Водкин', 'г.Гродно, ул. Победы,7', 10, 0.3, 1, '2019-01-26 21:36:26.818', '2019-01-26 21:36:26.818');
INSERT INTO money_transfer (id, payer_name, payer_adress, beneficiary_name, beneficiary_adress, amount, transaction_price, user_account_id, created, updated) VALUES (10, 'Мамин М.М.', 'г.Минск, пр. Победителей, 100', 'Сибиряк С.С.', 'г.Минск, ул. Василька, 5', 155, 4.65, 1, '2019-01-26 21:39:03.052', '2019-01-26 21:39:03.052');


--
-- TOC entry 2971 (class 0 OID 26856)
-- Dependencies: 210
-- Data for Name: order_item; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO order_item (id, product_id, quantity, order_product_id, created, updated) VALUES (41, 8, 10, 48, '2019-01-23 16:57:25.754', '2019-01-23 16:57:25.754');
INSERT INTO order_item (id, product_id, quantity, order_product_id, created, updated) VALUES (42, 6, 20, 48, '2019-01-23 16:57:36.284', '2019-01-23 16:57:36.284');
INSERT INTO order_item (id, product_id, quantity, order_product_id, created, updated) VALUES (43, 9, 1, 49, '2019-01-23 17:01:54.589', '2019-01-23 17:01:54.589');
INSERT INTO order_item (id, product_id, quantity, order_product_id, created, updated) VALUES (44, 8, 1, 49, '2019-01-23 17:02:09.127', '2019-01-23 17:02:09.127');
INSERT INTO order_item (id, product_id, quantity, order_product_id, created, updated) VALUES (45, 7, 1, 49, '2019-01-23 17:02:18.714', '2019-01-23 17:02:18.714');
INSERT INTO order_item (id, product_id, quantity, order_product_id, created, updated) VALUES (46, 5, 1, 49, '2019-01-23 17:02:28.762', '2019-01-23 17:02:28.762');
INSERT INTO order_item (id, product_id, quantity, order_product_id, created, updated) VALUES (47, 4, 1, 49, '2019-01-23 17:02:40.996', '2019-01-23 17:02:40.996');
INSERT INTO order_item (id, product_id, quantity, order_product_id, created, updated) VALUES (48, 3, 1, 49, '2019-01-23 17:03:12.883', '2019-01-23 17:03:12.883');
INSERT INTO order_item (id, product_id, quantity, order_product_id, created, updated) VALUES (49, 2, 1, 49, '2019-01-23 17:03:29.659', '2019-01-23 17:03:29.659');
INSERT INTO order_item (id, product_id, quantity, order_product_id, created, updated) VALUES (50, 8, 400, 50, '2019-01-23 17:05:59.986', '2019-01-23 17:05:59.986');
INSERT INTO order_item (id, product_id, quantity, order_product_id, created, updated) VALUES (51, 7, 100, 51, '2019-01-23 17:07:36.845', '2019-01-23 17:07:36.845');
INSERT INTO order_item (id, product_id, quantity, order_product_id, created, updated) VALUES (52, 6, 150, 51, '2019-01-23 17:07:59.922', '2019-01-23 17:07:59.922');
INSERT INTO order_item (id, product_id, quantity, order_product_id, created, updated) VALUES (53, 2, 12, 52, '2019-01-23 17:10:12.709', '2019-01-23 17:10:12.709');
INSERT INTO order_item (id, product_id, quantity, order_product_id, created, updated) VALUES (54, 2, 20, 53, '2019-01-23 17:11:09.427', '2019-01-23 17:11:09.427');
INSERT INTO order_item (id, product_id, quantity, order_product_id, created, updated) VALUES (55, 3, 1, 54, '2019-01-23 17:12:54.422', '2019-01-23 17:12:54.422');
INSERT INTO order_item (id, product_id, quantity, order_product_id, created, updated) VALUES (56, 7, 122, 55, '2018-12-23 06:48:52.491', '2018-12-23 06:48:52.491');
INSERT INTO order_item (id, product_id, quantity, order_product_id, created, updated) VALUES (57, 5, 100, 55, '2018-12-23 06:49:01.012', '2018-12-23 06:49:01.012');
INSERT INTO order_item (id, product_id, quantity, order_product_id, created, updated) VALUES (58, 4, 500, 55, '2018-12-23 06:49:13.613', '2018-12-23 06:49:13.613');
INSERT INTO order_item (id, product_id, quantity, order_product_id, created, updated) VALUES (59, 3, 155, 55, '2018-12-23 06:49:24.723', '2018-12-23 06:49:24.723');
INSERT INTO order_item (id, product_id, quantity, order_product_id, created, updated) VALUES (60, 2, 359, 55, '2018-12-23 06:49:34.999', '2018-12-23 06:49:34.999');
INSERT INTO order_item (id, product_id, quantity, order_product_id, created, updated) VALUES (61, 8, 25, 57, '2018-12-23 06:53:44.529', '2018-12-23 06:53:44.529');
INSERT INTO order_item (id, product_id, quantity, order_product_id, created, updated) VALUES (62, 7, 85, 57, '2018-12-23 06:53:54.514', '2018-12-23 06:53:54.514');
INSERT INTO order_item (id, product_id, quantity, order_product_id, created, updated) VALUES (63, 6, 10, 57, '2018-12-23 06:54:04.753', '2018-12-23 06:54:04.753');
INSERT INTO order_item (id, product_id, quantity, order_product_id, created, updated) VALUES (64, 5, 500, 57, '2018-12-23 06:54:14.239', '2018-12-23 06:54:14.239');
INSERT INTO order_item (id, product_id, quantity, order_product_id, created, updated) VALUES (65, 2, 325, 57, '2018-12-23 06:54:32.285', '2018-12-23 06:54:32.285');
INSERT INTO order_item (id, product_id, quantity, order_product_id, created, updated) VALUES (66, 9, 129, 58, '2018-12-23 06:55:48.571', '2018-12-23 06:55:48.571');
INSERT INTO order_item (id, product_id, quantity, order_product_id, created, updated) VALUES (67, 8, 229, 58, '2018-12-23 06:55:57.238', '2018-12-23 06:55:57.238');
INSERT INTO order_item (id, product_id, quantity, order_product_id, created, updated) VALUES (68, 7, 358, 58, '2018-12-23 06:56:07.978', '2018-12-23 06:56:07.978');
INSERT INTO order_item (id, product_id, quantity, order_product_id, created, updated) VALUES (69, 6, 400, 58, '2018-12-23 06:56:21.021', '2018-12-23 06:56:21.021');
INSERT INTO order_item (id, product_id, quantity, order_product_id, created, updated) VALUES (70, 5, 79, 58, '2018-12-23 06:56:33.112', '2018-12-23 06:56:33.112');
INSERT INTO order_item (id, product_id, quantity, order_product_id, created, updated) VALUES (71, 9, 1, 59, '2019-01-27 15:09:40.216', '2019-01-27 15:09:40.216');
INSERT INTO order_item (id, product_id, quantity, order_product_id, created, updated) VALUES (75, 2, 10, 61, '2019-01-27 15:27:41.978', '2019-01-27 15:27:41.978');
INSERT INTO order_item (id, product_id, quantity, order_product_id, created, updated) VALUES (76, 3, 2, 61, '2019-01-27 15:27:55.06', '2019-01-27 15:27:55.06');
INSERT INTO order_item (id, product_id, quantity, order_product_id, created, updated) VALUES (74, 4, 5, 61, '2019-01-27 15:23:19.578', '2019-01-27 15:28:39.696');
INSERT INTO order_item (id, product_id, quantity, order_product_id, created, updated) VALUES (77, 7, 15, 62, '2019-01-27 15:37:37.912', '2019-01-27 15:37:37.912');
INSERT INTO order_item (id, product_id, quantity, order_product_id, created, updated) VALUES (78, 9, 3, 62, '2019-01-27 15:37:54.409', '2019-01-27 15:37:54.409');


--
-- TOC entry 2973 (class 0 OID 26861)
-- Dependencies: 212
-- Data for Name: order_product; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO order_product (id, shipping_to, shipping_type, user_account_id, cost, status, created, updated) VALUES (51, 'г.Гродно, ул. Пушкина, 2', 'courierDelivery', 6, 145, 'submitted', '2019-01-23 17:07:32.129', '2019-01-23 17:08:12.323');
INSERT INTO order_product (id, shipping_to, shipping_type, user_account_id, cost, status, created, updated) VALUES (52, 'г.Гродно, ул. Пушкина, 2', 'courierDelivery', 1, 1.2, 'submitted', '2019-01-23 17:10:08.523', '2019-01-23 17:10:54.861');
INSERT INTO order_product (id, shipping_to, shipping_type, user_account_id, cost, status, created, updated) VALUES (53, 'г.Гродно, ул. Горького, 5', 'courierDelivery', 1, 2, 'submitted', '2019-01-23 17:11:04.863', '2019-01-23 17:11:44.213');
INSERT INTO order_product (id, shipping_to, shipping_type, user_account_id, cost, status, created, updated) VALUES (54, 'г.Гродно, ул. Горького, 5', 'courierDelivery', 1, 0.01, 'submitted', '2019-01-23 17:12:49.808', '2019-01-23 17:14:02.695');
INSERT INTO order_product (id, shipping_to, shipping_type, user_account_id, cost, status, created, updated) VALUES (48, 'г.Гомель, ул. Советская,5', 'courierDelivery', 1, 20, 'executed', '2019-01-23 16:57:20.774', '2019-01-23 16:59:13.011');
INSERT INTO order_product (id, shipping_to, shipping_type, user_account_id, cost, status, created, updated) VALUES (58, 'г.Брест, ул. Ленина, 55', 'courierDelivery', 6, 789.89, 'executed', '2018-12-23 06:55:44.224', '2018-12-23 06:59:19.361');
INSERT INTO order_product (id, shipping_to, shipping_type, user_account_id, cost, status, created, updated) VALUES (57, 'г.Брест, ул. Ленина, 55', 'parcelDelivery', 2, 322, 'executed', '2018-12-23 06:53:39.275', '2018-12-23 06:59:26.12');
INSERT INTO order_product (id, shipping_to, shipping_type, user_account_id, cost, status, created, updated) VALUES (55, 'г.Щучин, ул. Школьная, 8', 'courierDelivery', 1, 412.85, 'executed', '2018-12-23 06:48:46.287', '2018-12-23 06:59:30.477');
INSERT INTO order_product (id, shipping_to, shipping_type, user_account_id, cost, status, created, updated) VALUES (59, 'г.Гродно, ул.Пушкина, 10', 'courierDelivery', 1, 0.61, 'submitted', '2019-01-27 15:09:35.177', '2019-01-27 15:10:50.323');
INSERT INTO order_product (id, shipping_to, shipping_type, user_account_id, cost, status, created, updated) VALUES (60, 'г.Гродно, ул.Пушкина, 10', 'parcelDelivery', 1, 0, 'productCart', '2019-01-27 15:11:31.179', '2019-01-27 15:11:31.179');
INSERT INTO order_product (id, shipping_to, shipping_type, user_account_id, cost, status, created, updated) VALUES (61, 'г.Гродно, ул.Лермонтова, 8', 'parcelDelivery', 2, 3.52, 'submitted', '2019-01-27 15:23:04.382', '2019-01-27 15:37:00.35');
INSERT INTO order_product (id, shipping_to, shipping_type, user_account_id, cost, status, created, updated) VALUES (62, 'г.Гродно, ул.Лермонтова, 8', 'parcelDelivery', 2, 12.33, 'executed', '2019-01-27 15:37:33.275', '2019-01-27 15:59:00.071');
INSERT INTO order_product (id, shipping_to, shipping_type, user_account_id, cost, status, created, updated) VALUES (49, 'г.Гродно, ул. Пушкина, 2', 'courierDelivery', 2, 3.32, 'executed', '2019-01-23 17:01:48.778', '2019-01-27 15:59:11.485');
INSERT INTO order_product (id, shipping_to, shipping_type, user_account_id, cost, status, created, updated) VALUES (50, 'г.Гродно, ул. Горького, 5', 'courierDelivery', 1, 400, 'executed', '2019-01-23 17:05:48.339', '2019-01-27 15:59:25.728');


--
-- TOC entry 2975 (class 0 OID 26869)
-- Dependencies: 214
-- Data for Name: paper_details; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO paper_details (id, name, price, created, updated) VALUES (4, 'xerox performer, 120 г/м2', 0.07, '2019-01-11 05:55:37.451', '2019-01-11 05:55:37.451');
INSERT INTO paper_details (id, name, price, created, updated) VALUES (3, 'xerox performer, 80 г/м2', 0.05, '2019-01-11 05:54:58.841', '2019-01-11 05:54:58.841');
INSERT INTO paper_details (id, name, price, created, updated) VALUES (6, 'colour copy, 250 г/м2', 0.1, '2019-01-11 06:01:18.694', '2019-01-11 06:01:18.694');
INSERT INTO paper_details (id, name, price, created, updated) VALUES (5, 'colour copy, 200 г/м2', 0.08, '2019-01-11 06:00:24.745', '2019-01-11 06:00:24.745');
INSERT INTO paper_details (id, name, price, created, updated) VALUES (7, 'xerox performer, 300 г/м2', 0.15, '2019-01-12 02:31:22.846', '2019-01-12 02:31:38.131');


--
-- TOC entry 2977 (class 0 OID 26877)
-- Dependencies: 216
-- Data for Name: parcel_zone; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO parcel_zone (id, price4g100, created, updated) VALUES (1, 2.22, '2019-01-08 20:40:10.738', '2019-01-08 20:40:10.738');
INSERT INTO parcel_zone (id, price4g100, created, updated) VALUES (2, 3.13, '2019-01-08 20:40:15.771', '2019-01-08 20:40:15.771');
INSERT INTO parcel_zone (id, price4g100, created, updated) VALUES (3, 4.33, '2019-01-08 20:40:29.623', '2019-01-26 21:04:28.895');


--
-- TOC entry 2979 (class 0 OID 26885)
-- Dependencies: 218
-- Data for Name: polygraphy; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO polygraphy (id, image_format, copy_count, is_coloured, is_duplex_printing, price, user_account_id, paper_details_id, created, updated) VALUES (4, 'A4', 2, true, true, 0.68, 2, 7, '2019-01-12 07:23:17.382', '2019-01-12 07:23:17.382');
INSERT INTO polygraphy (id, image_format, copy_count, is_coloured, is_duplex_printing, price, user_account_id, paper_details_id, created, updated) VALUES (11, 'A4', 2, true, false, 0.45, 2, 7, '2019-01-12 08:57:33.776', '2019-01-12 08:57:33.776');
INSERT INTO polygraphy (id, image_format, copy_count, is_coloured, is_duplex_printing, price, user_account_id, paper_details_id, created, updated) VALUES (19, 'A3', 1, true, false, 0.15, 1, 3, '2019-01-12 11:33:38.327', '2019-01-12 11:33:38.327');
INSERT INTO polygraphy (id, image_format, copy_count, is_coloured, is_duplex_printing, price, user_account_id, paper_details_id, created, updated) VALUES (20, 'A4', 1, true, false, 0.11, 1, 4, '2019-01-14 12:47:38.349', '2019-01-14 12:47:38.349');


--
-- TOC entry 2981 (class 0 OID 26893)
-- Dependencies: 220
-- Data for Name: product; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO product (id, name, price, created, updated) VALUES (3, 'Марка почтовая, номинал 0,01 руб.', 0.01, '2019-01-08 20:38:14.58', '2019-01-14 12:56:49.692');
INSERT INTO product (id, name, price, created, updated) VALUES (2, 'Марка почтовая, номинал 0,10 руб.', 0.1, '2019-01-08 20:37:44.194', '2019-01-14 12:57:29.929');
INSERT INTO product (id, name, price, created, updated) VALUES (4, 'Марка почтовая, номинал 0,50 руб.', 0.5, '2019-01-14 12:57:59.179', '2019-01-14 12:57:59.179');
INSERT INTO product (id, name, price, created, updated) VALUES (5, 'Конверт А6', 0.4, '2019-01-14 18:11:12.982', '2019-01-14 18:11:12.982');
INSERT INTO product (id, name, price, created, updated) VALUES (6, 'Конверт А5', 0.5, '2019-01-14 18:11:43.424', '2019-01-14 18:11:55.176');
INSERT INTO product (id, name, price, created, updated) VALUES (7, 'Конверт А4', 0.7, '2019-01-14 18:12:21.92', '2019-01-14 18:12:21.92');
INSERT INTO product (id, name, price, created, updated) VALUES (8, 'Конверт пузырчатый', 1, '2019-01-14 18:16:00.842', '2019-01-14 18:16:00.842');
INSERT INTO product (id, name, price, created, updated) VALUES (9, 'Посылочный ящик из гофрокартона', 0.61, '2019-01-14 18:17:37.898', '2019-01-14 18:17:37.898');
INSERT INTO product (id, name, price, created, updated) VALUES (10, 'Почтовая карточка', 0.45, '2019-01-27 16:11:37.181', '2019-01-27 16:11:37.181');


--
-- TOC entry 2983 (class 0 OID 26901)
-- Dependencies: 222
-- Data for Name: store; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO store (id, store_type, created, updated) VALUES (1, 'available', '2019-01-17 08:11:12.6982', '2019-01-17 08:11:12.6982');
INSERT INTO store (id, store_type, created, updated) VALUES (2, 'reserved', '2019-01-23 16:50:12.988', '2019-01-23 16:50:12.988');


--
-- TOC entry 2985 (class 0 OID 26909)
-- Dependencies: 224
-- Data for Name: user_account; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO user_account (id, e_mail, password, user_role, created, updated) VALUES (1, '1111@mail.com', '1111', 'customer', '2019-01-09 14:49:03.052', '2019-01-09 14:49:03.052');
INSERT INTO user_account (id, e_mail, password, user_role, created, updated) VALUES (2, '2222@mail.com', '2222', 'customer', '2019-01-09 14:49:18.094', '2019-01-09 14:49:18.094');
INSERT INTO user_account (id, e_mail, password, user_role, created, updated) VALUES (4, 'manager@mail.com', 'reganam', 'manager', '2019-01-23 15:59:20.944255', '2019-01-23 15:59:20.944255');
INSERT INTO user_account (id, e_mail, password, user_role, created, updated) VALUES (5, 'admin', 'nimda', 'admin', '2019-01-23 15:59:20.944255', '2019-01-23 15:59:20.944255');
INSERT INTO user_account (id, e_mail, password, user_role, created, updated) VALUES (6, '3333@mail.com', '3333', 'customer', '2019-01-23 16:46:58.654', '2019-01-23 16:46:58.654');


--
-- TOC entry 2986 (class 0 OID 26915)
-- Dependencies: 225
-- Data for Name: user_account_details; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO user_account_details (id, name, adress, phone, created, updated) VALUES (1, 'Пушкин А.С.', 'г.Гродно, ул.Пушкина, 10', 292831108, '2019-01-23 15:57:16.351129', '2019-01-23 15:57:16.351129');
INSERT INTO user_account_details (id, name, adress, phone, created, updated) VALUES (2, 'Лермонтов М.Ю.', 'г.Гродно, ул.Лермонтова, 8', 333491528, '2019-01-23 15:57:16.351129', '2019-01-23 15:57:16.351129');
INSERT INTO user_account_details (id, name, adress, phone, created, updated) VALUES (6, 'Чапаев В.И.', 'г.Гродно, ул.Чапаева, 1', 152624390, '2019-01-23 16:46:58.654', '2019-01-23 16:46:58.654');


--
-- TOC entry 3009 (class 0 OID 0)
-- Dependencies: 197
-- Name: country_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('country_id_seq', 7, true);


--
-- TOC entry 3010 (class 0 OID 0)
-- Dependencies: 199
-- Name: courier_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('courier_id_seq', 8, true);


--
-- TOC entry 3011 (class 0 OID 0)
-- Dependencies: 201
-- Name: express_zone_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('express_zone_id_seq', 4, true);


--
-- TOC entry 3012 (class 0 OID 0)
-- Dependencies: 203
-- Name: inventory_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('inventory_id_seq', 22, true);


--
-- TOC entry 3013 (class 0 OID 0)
-- Dependencies: 205
-- Name: letter_zone_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('letter_zone_id_seq', 4, true);


--
-- TOC entry 3014 (class 0 OID 0)
-- Dependencies: 207
-- Name: mailing_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('mailing_id_seq', 16, true);


--
-- TOC entry 3015 (class 0 OID 0)
-- Dependencies: 209
-- Name: money_transfer_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('money_transfer_id_seq', 10, true);


--
-- TOC entry 3016 (class 0 OID 0)
-- Dependencies: 211
-- Name: order_item_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('order_item_id_seq', 78, true);


--
-- TOC entry 3017 (class 0 OID 0)
-- Dependencies: 213
-- Name: order_product_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('order_product_id_seq', 62, true);


--
-- TOC entry 3018 (class 0 OID 0)
-- Dependencies: 215
-- Name: paper_details_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('paper_details_id_seq', 7, true);


--
-- TOC entry 3019 (class 0 OID 0)
-- Dependencies: 217
-- Name: parcel_zone_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('parcel_zone_id_seq', 3, true);


--
-- TOC entry 3020 (class 0 OID 0)
-- Dependencies: 219
-- Name: polygraphy_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('polygraphy_id_seq', 20, true);


--
-- TOC entry 3021 (class 0 OID 0)
-- Dependencies: 221
-- Name: product_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('product_id_seq', 10, true);


--
-- TOC entry 3022 (class 0 OID 0)
-- Dependencies: 223
-- Name: store_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('store_id_seq', 2, true);


--
-- TOC entry 3023 (class 0 OID 0)
-- Dependencies: 226
-- Name: user_account_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('user_account_id_seq', 6, true);


--
-- TOC entry 2788 (class 2606 OID 26939)
-- Name: country country_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY country
    ADD CONSTRAINT country_pk PRIMARY KEY (id);


--
-- TOC entry 2790 (class 2606 OID 26941)
-- Name: courier courier_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY courier
    ADD CONSTRAINT courier_pk PRIMARY KEY (id);


--
-- TOC entry 2792 (class 2606 OID 26943)
-- Name: express_zone express_zone_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY express_zone
    ADD CONSTRAINT express_zone_pk PRIMARY KEY (id);


--
-- TOC entry 2794 (class 2606 OID 26945)
-- Name: inventory inventory_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY inventory
    ADD CONSTRAINT inventory_pk PRIMARY KEY (id);


--
-- TOC entry 2796 (class 2606 OID 26947)
-- Name: inventory inventory_store_id_product_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY inventory
    ADD CONSTRAINT inventory_store_id_product_id_key UNIQUE (store_id, product_id);


--
-- TOC entry 2798 (class 2606 OID 26949)
-- Name: letter_zone letter_zone_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY letter_zone
    ADD CONSTRAINT letter_zone_pk PRIMARY KEY (id);


--
-- TOC entry 2800 (class 2606 OID 26951)
-- Name: mailing mailing_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY mailing
    ADD CONSTRAINT mailing_pk PRIMARY KEY (id);


--
-- TOC entry 2802 (class 2606 OID 26953)
-- Name: money_transfer money_transfer_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY money_transfer
    ADD CONSTRAINT money_transfer_pk PRIMARY KEY (id);


--
-- TOC entry 2804 (class 2606 OID 26955)
-- Name: order_item order_item_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY order_item
    ADD CONSTRAINT order_item_pk PRIMARY KEY (id);


--
-- TOC entry 2806 (class 2606 OID 26957)
-- Name: order_product order_product_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY order_product
    ADD CONSTRAINT order_product_pk PRIMARY KEY (id);


--
-- TOC entry 2808 (class 2606 OID 26959)
-- Name: paper_details paper_details_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY paper_details
    ADD CONSTRAINT paper_details_pk PRIMARY KEY (id);


--
-- TOC entry 2810 (class 2606 OID 26961)
-- Name: parcel_zone parcel_zone_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY parcel_zone
    ADD CONSTRAINT parcel_zone_pk PRIMARY KEY (id);


--
-- TOC entry 2812 (class 2606 OID 26963)
-- Name: polygraphy polygraphy_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY polygraphy
    ADD CONSTRAINT polygraphy_pk PRIMARY KEY (id);


--
-- TOC entry 2814 (class 2606 OID 26965)
-- Name: product product_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY product
    ADD CONSTRAINT product_pk PRIMARY KEY (id);


--
-- TOC entry 2816 (class 2606 OID 26967)
-- Name: store store_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY store
    ADD CONSTRAINT store_pk PRIMARY KEY (id);


--
-- TOC entry 2820 (class 2606 OID 26969)
-- Name: user_account_details user_account_details_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY user_account_details
    ADD CONSTRAINT user_account_details_pk PRIMARY KEY (id);


--
-- TOC entry 2818 (class 2606 OID 26971)
-- Name: user_account user_account_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY user_account
    ADD CONSTRAINT user_account_pk PRIMARY KEY (id);


--
-- TOC entry 2821 (class 2606 OID 26972)
-- Name: country country_fk0; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY country
    ADD CONSTRAINT country_fk0 FOREIGN KEY (express_zone_id) REFERENCES express_zone(id);


--
-- TOC entry 2822 (class 2606 OID 26977)
-- Name: country country_fk1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY country
    ADD CONSTRAINT country_fk1 FOREIGN KEY (letter_zone_id) REFERENCES letter_zone(id);


--
-- TOC entry 2823 (class 2606 OID 26982)
-- Name: country country_fk2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY country
    ADD CONSTRAINT country_fk2 FOREIGN KEY (parcel_zone_id) REFERENCES parcel_zone(id);


--
-- TOC entry 2824 (class 2606 OID 26987)
-- Name: courier courier_fk0; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY courier
    ADD CONSTRAINT courier_fk0 FOREIGN KEY (user_account_id) REFERENCES user_account(id);


--
-- TOC entry 2825 (class 2606 OID 26992)
-- Name: inventory inventory_fk0; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY inventory
    ADD CONSTRAINT inventory_fk0 FOREIGN KEY (product_id) REFERENCES product(id);


--
-- TOC entry 2826 (class 2606 OID 26997)
-- Name: inventory inventory_fk1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY inventory
    ADD CONSTRAINT inventory_fk1 FOREIGN KEY (store_id) REFERENCES store(id);


--
-- TOC entry 2827 (class 2606 OID 27002)
-- Name: mailing mailing_fk0; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY mailing
    ADD CONSTRAINT mailing_fk0 FOREIGN KEY (user_account_id) REFERENCES user_account(id);


--
-- TOC entry 2828 (class 2606 OID 27007)
-- Name: mailing mailing_fk1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY mailing
    ADD CONSTRAINT mailing_fk1 FOREIGN KEY (country_id) REFERENCES country(id);


--
-- TOC entry 2829 (class 2606 OID 27012)
-- Name: money_transfer money_transfer_fk0; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY money_transfer
    ADD CONSTRAINT money_transfer_fk0 FOREIGN KEY (user_account_id) REFERENCES user_account(id);


--
-- TOC entry 2830 (class 2606 OID 27017)
-- Name: order_item order_item_fk0; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY order_item
    ADD CONSTRAINT order_item_fk0 FOREIGN KEY (product_id) REFERENCES product(id);


--
-- TOC entry 2831 (class 2606 OID 27022)
-- Name: order_item order_item_fk1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY order_item
    ADD CONSTRAINT order_item_fk1 FOREIGN KEY (order_product_id) REFERENCES order_product(id);


--
-- TOC entry 2832 (class 2606 OID 27027)
-- Name: order_product order_product_fk0; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY order_product
    ADD CONSTRAINT order_product_fk0 FOREIGN KEY (user_account_id) REFERENCES user_account(id);


--
-- TOC entry 2833 (class 2606 OID 27032)
-- Name: polygraphy polygraphy_fk0; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY polygraphy
    ADD CONSTRAINT polygraphy_fk0 FOREIGN KEY (user_account_id) REFERENCES user_account(id);


--
-- TOC entry 2834 (class 2606 OID 27037)
-- Name: polygraphy polygraphy_fk1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY polygraphy
    ADD CONSTRAINT polygraphy_fk1 FOREIGN KEY (paper_details_id) REFERENCES paper_details(id);


--
-- TOC entry 2835 (class 2606 OID 27042)
-- Name: user_account_details user_account_details_fk0; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY user_account_details
    ADD CONSTRAINT user_account_details_fk0 FOREIGN KEY (id) REFERENCES user_account(id);


-- Completed on 2019-01-27 19:03:01

--
-- PostgreSQL database dump complete
--

