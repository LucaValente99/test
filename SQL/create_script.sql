-- Tabelle del Client

create table utente(
	nome varchar2(30) not null,
	cognome varchar2(30) not null,
	indirizzo varchar2(50) not null,
	cap char(5) not null,
	nascita date not null,
	username varchar2(10),
	password varchar2(500) not null,
	email varchar2(100) not null unique,
	constraint p_username_utente primary key (username)
);

create table articolo(
	id_articolo int,
	marca varchar2(30) not null,
	modello varchar2(30) not null,
	prezzo number(8,2) not null,
	constraint p_id_articolo primary key (id_articolo)
);

create table ordine(
	id_ordine int,
	totale number(9,2) not null,
	data date not null,
	username varchar2(10),
	constraint f_username foreign key (username) references utente(username) on delete cascade,
	constraint p_id_ordine primary key (id_ordine) 
);

-- relazione molti a molti tra articolo e ordine

create table ordine_articolo(
	id_ordine int,
	id_articolo int,
	qta number(3) default 1,
	constraint f_id_ordine foreign key (id_ordine) references ordine(id_ordine) on delete cascade,
	constraint f_id_articolo foreign key (id_articolo) references articolo(id_articolo) on delete cascade,
	constraint p_oa primary key (id_ordine, id_articolo)
);

-- relazione 1 a 1 tra immagine e articolo

create table immagine(
	id_immagine int primary key references articolo(id_articolo),
	url varchar2(500) default 'https://upload.wikimedia.org/wikipedia/commons/a/ac/No_image_available.svg',
	descrizione varchar2(1000) not null
);

create sequence ordine_seq;
create sequence articolo_seq;


-- Tabelle dell'amministratore

create table amministratore(
	username varchar2(10),
	password varchar2(500) not null,
	email varchar2(100) not null unique,
	constraint p_admin primary key(username)
);

-- report degli ordini (riassunto dati sensibili ordine)

create or replace view report as
select u.username, u.email, o.id_ordine, o.totale, sum(oa.qta) as qta
from utente u, ordine o, articolo a, ordine_articolo oa
where 
o.id_ordine = oa.id_ordine
and
a.id_articolo = oa.id_articolo
and 
u.username = o.username
group by u.username, u.email, o.id_ordine, o.totale
order by u.username, o.id_ordine;

create sequence admin_seq;
