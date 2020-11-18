DROP DATABASE 	pos_db;
CREATE DATABASE pos_db;
show databases;

show tables;
use pos_db;
drop table POS;
drop table RED;
drop table WHITE;
drop table SPA;
drop table GRATIN;
drop table PASTA;
drop table PIZZA;

CREATE TABLE POS (
id int not null,
할인횟수 int ,
할인금액 int ,
최종금액 int ,
PRIMARY KEY (id) 
);

CREATE TABLE RED (
rid int not null,
말벡 INT ,
앙케 INT,
라크라사드 INT,
몬테스 INT,
엘레베 INT,
페레제 INT,
primary key (rid),
FOREIgN KEY (rid) REFERENCES POS(id)
);

CREATE TABLE WHITE (
wid int not null,
리즐링 INT,
앙시앙 INT,
지아콘디 INT,
소비뇽 INT,
비안코 INT,
샤르도네 INT,
primary key (wid),
FOREIgN KEY (wid) REFERENCES POS(id)
);

CREATE TABLE SPA (
spaid int not null,
아리온 INT,
로제타 INT,
비안코 INT,
아스티 INT,
카바 INT,
브룻 INT,
primary key (spaid),
FOREIgN KEY (spaid) REFERENCES POS(id)
);

CREATE TABLE GRATIN (
gid int not null,
토마토그라탕 INT,
크림그라탕 INT,
로제그라탕 INT,
닭고기그라탕 INT,
소고기그라탕 INT,
돼지고기그라탕 INT,
primary key (gid),
FOREIgN KEY (gid) REFERENCES POS(id)
);

CREATE TABLE PASTA (
paid int not null,
토마토파스타 INT,
크림파스타 INT,
로제파스타 INT,
알리오올리오 INT,
연어파스타 INT,
넹파스타 INT,
primary key (paid),
FOREIgN KEY (paid) REFERENCES POS(id)
);

CREATE TABLE PIZZA (
piid int not null,
마토파스타 INT,
비스마르크 INT,
픙기피자 INT,
크림피자 INT,
포테이토피자 INT,
스파이스초리죠 INT,
primary key (piid),
FOREIgN KEY (piid) REFERENCES POS(id)
);

CREATE TABLE beforesum (
beid int not null,
직원할인 INT,
지인할인 INT,
만원 INT,
이만원 INT,
이만오천원 INT,
오만원 INT,
primary key (beid),
FOREIgN KEY (beid) REFERENCES POS(id)
);

select * 
from POS, RED,WHITE,SPA,GRATIN,PASTA,PIZZA 
where POS.id1 = RED.rid and POS.id1 = WHITE.wid and POS.id1 = SPA.spaid and POS.id1 = GRATIN.gid and POS.id1 = PASTA.paid and POS.id1 = PIZZA.piid;




select count(*)
from POS, RED,WHITE,SPA,GRATIN,PASTA,PIZZA; 



SELECT *
FROM POS, RED, WHITE, SPA, GRATIN, PASTA, PIZZA;

select * from beforesum;

show tables;
show databases;

desc beforesum;
desc gratin;
desc pasta;
desc pizza;
desc pos;
desc red;
desc spa;
desc white;



