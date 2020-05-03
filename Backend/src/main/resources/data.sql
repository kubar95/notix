insert into user (username, password) values ('test', '$2a$10$BuxuN4qXSFM.4EpnFXuiv.Zk4UZFuAvDPLrdA.cHeHTv/7EhLdCoW')

insert into subject (name, owner_id) values ('pierwszy przedmiot', '1')
insert into subject (name, owner_id) values ('drugi przedmiot', '1')

insert into event (description, title, start, end, owner_id) values ('testetst','pierwszy Event', current_timestamp , current_timestamp, '1')
insert into event (description, title, start, end, owner_id) values ('testetst','drugi Event', current_timestamp +1 , current_timestamp, '1')
insert into event (description, title, start, end, owner_id) values ('testetst','trzeci Event', current_timestamp +2, current_timestamp, '1')
insert into event (description, title, start, end, owner_id) values ('testetst','czwary Event', current_timestamp +3, current_timestamp, '1')



insert into note (content, date, title, subject_id) values ('bla bla bla', current_timestamp , 'tytul notatki 1','1')
insert into note (content, title, subject_id) values ('bla bla bla' , 'tytul notatki 2','1')
insert into note (content, date, title, subject_id) values ('bla bla bla', current_timestamp , 'sama notatka','2')
