--ɾ����--
DROP TABLE member PURGE;
--������--
CREATE TABLE member (
	mid 	varchar2(50),
	name	varchar2(50),
	age	number,
	phone 	varchar2(20),
	birthday	date,
	note	clob,
	constraint pk_mid primary key(mid)
);