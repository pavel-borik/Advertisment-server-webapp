ALTER TABLE `inzeraty`.`adverts`
  CHANGE COLUMN `description` `description` LONGTEXT NOT NULL ;

ALTER TABLE `inzeraty`.`adverts`
  CHANGE COLUMN `image` `image` LONGBLOB ;

insert into categories (id, name) values (1, 'Cars');
insert into categories (id, name) values (2, 'Smartphones');
insert into categories (id, name) values (3, 'Music');
insert into categories (id, name) values (4, 'House');
insert into categories (id, name) values (5, 'PC');
insert into categories (id, name) values (6, 'Children');
insert into categories (id, name) values (7, 'Books');
insert into categories (id, name) values (8, 'Clothes');
insert into categories (id, name) values (9, 'Animals');

insert into roles (id, name) values (1, 'USER');
insert into roles (id, name) values (2, 'ADMIN');
