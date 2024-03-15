DROP TABLE if exists usr_user;
CREATE SEQUENCE if not exists usr_user_id_seq;
CREATE TABLE usr_user (
                          birth_date date ,
                          latitude numeric(38,2) ,
                          longitude numeric(38,2) ,
                          created_at timestamp(6) ,
                          id bigint NOT NULL DEFAULT nextval('usr_user_id_seq'::regclass),
                          updated_at timestamp(6),
                          turkish_republic_id_number character varying(11) ,
                          username character varying(30) ,
                          first_name character varying(50) ,
                          last_name character varying(50) ,
                          status character varying(255) ,
                          CONSTRAINT usr_user_pkey PRIMARY KEY (id),
                          CONSTRAINT usr_user_turkish_republic_id_number_key UNIQUE (turkish_republic_id_number),
                          CONSTRAINT usr_user_username_key UNIQUE (username),
                          CONSTRAINT usr_user_status_check CHECK (status::text = ANY (ARRAY['ACTIVE'::character varying, 'PASSIVE'::character varying]::text[]))
    );

DROP TABLE if exists review;
CREATE SEQUENCE if not exists review_id_seq;
CREATE SEQUENCE if not exists review_user_id_seq;
CREATE TABLE review(
                       created_at timestamp(6),
                       id bigint NOT NULL DEFAULT nextval('review_id_seq'::regclass),
                       updated_at timestamp(6),
                       user_id integer NOT NULL DEFAULT nextval('review_user_id_seq'::regclass),
                       review character varying(500) ,
                       rate character varying(255) ,
                       restaurant_id character varying(255),
                       CONSTRAINT review_pkey PRIMARY KEY (id),
                       CONSTRAINT review_rate_check CHECK (rate::text = ANY (ARRAY['ONE'::character varying, 'TWO'::character varying, 'THREE'::character varying, 'FOUR'::character varying, 'FIVE'::character varying]::text[]))
    );