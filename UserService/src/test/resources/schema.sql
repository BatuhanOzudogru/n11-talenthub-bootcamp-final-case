DROP TABLE if exists usr_user;
CREATE TABLE usr_user (
    birth_date date ,
    latitude numeric(38,2) ,
    longitude numeric(38,2) ,
    created_at timestamp(6) ,
    id bigint NOT NULL,
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





