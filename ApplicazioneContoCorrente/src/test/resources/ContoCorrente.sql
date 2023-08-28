drop database if exists banca;
create database if not exists banca;
use banca;
create table ContiCorrenti(
	numeroConto bigint not null,
    CIN char(1) not null,
    ABI int not null,
    CAB int not null,
    nomeTitolare varchar(50) not null,
    cognomeTitolare varchar(50) not null,
    codiceFiscaleTitolare varchar(16) not null,
    primary key (numeroConto)
);

insert into ContiCorrenti
(numeroConto, CIN, ABI, CAB, nomeTitolare, cognomeTitolare, codiceFiscaleTitolare)
values
(123456789101,'1',  12345, 67891, 'Giulio', 'Foglietta', 'FGLGLU27U38L478E'),
(123456789102,'2',  12346, 67892, 'Pippo', 'Fumagalli', 'PPOFMG27S38L789I');