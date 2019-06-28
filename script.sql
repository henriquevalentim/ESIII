CREATE TABLE public.tb_perfilatendimento
(
    per_id integer NOT NULL,
    per_nome character varying(100),
    CONSTRAINT tb_perfilusuario_pkey PRIMARY KEY (per_id)
)
WITH (
    OIDS = FALSE
)
----------------------------------------------------------------

CREATE TABLE public.tb_usuario
(
    usu_id integer NOT NULL ,
    usu_login character varying(50),
    usu_senha character varying(50),
    usu_per_id integer,
    CONSTRAINT tb_usuario_pkey PRIMARY KEY (usu_id),
    CONSTRAINT usu_per_id_fk FOREIGN KEY (usu_per_id)
        REFERENCES public.tb_perfilatendimento (per_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)

----------------------------------------------------------------
--TABELA CARGO
CREATE TABLE tb_cargo
(
    car_id integer NOT NULL,
    car_descricao character varying(200),
    CONSTRAINT tb_cargo_pkey PRIMARY KEY (car_id)
)
WITH (
    OIDS = FALSE
)
ALTER TABLE public.tb_cargo OWNER to postgres;

----------------------------------------------------------------

--TABELA REGIONAL
CREATE TABLE tb_regional
(
    reg_id integer NOT NULL,
    reg_nome character varying(100),
    CONSTRAINT tb_regional_pkey PRIMARY KEY (reg_id)
)
WITH (
    OIDS = FALSE
)
ALTER TABLE public.tb_regional OWNER to postgres;

----------------------------------------------------------------

CREATE TABLE tb_setor
(
    set_id integer NOT NULL,
    set_nome character varying,
    CONSTRAINT tb_setor_pkey PRIMARY KEY (set_id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;
ALTER TABLE public.tb_setor OWNER to postgres;

----------------------------------------------------------------

CREATE TABLE tb_funcionario
(
    fun_id integer NOT NULL,
    fun_nome character varying(100),
    fun_cpf character varying(11),
    fun_email character varying(50),
    fun_matricula character varying(50),
    fun_reg_id integer NOT NULL,
    fun_car_id integer NOT NULL,
    fun_set_id integer NOT NULL,
    fun_dtcontratacao date,
    CONSTRAINT tb_funcionario_pkey PRIMARY KEY (fun_id),
    CONSTRAINT fun_car_id_fk FOREIGN KEY (fun_car_id)
        REFERENCES public.tb_cargo (car_id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT fun_reg_id_fk FOREIGN KEY (fun_reg_id)
        REFERENCES public.tb_regional (reg_id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT fun_set_id_fk FOREIGN KEY (fun_set_id)
        REFERENCES public.tb_setor (set_id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.tb_funcionario
    OWNER to postgres;
