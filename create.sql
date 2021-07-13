create table categorie (id bigint not null auto_increment, date_creation date, date_modif date, nom varchar(255), qt integer, primary key (id)) engine=InnoDB
create table produit (id_produit bigint not null auto_increment, date_creation date, date_modif date, disponible bit, nom varchar(255), qt integer, id bigint, primary key (id_produit)) engine=InnoDB
alter table produit add constraint FK8exheyusegu5acr1lt8xx0s25 foreign key (id) references categorie (id)
