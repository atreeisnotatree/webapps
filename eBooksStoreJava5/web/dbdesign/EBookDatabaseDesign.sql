drop table EBOOKS.EBOOKS_AUTHORS;
drop table EBOOKS.EBOOKS_RATINGS_USERS;
drop table EBOOKS.EBOOKS;
drop table EBOOKS.BOOK_TYPES;
drop table EBOOKS.USERS;
drop table EBOOKS.BOOK_PAPER_QUALITIES;
drop table EBOOKS.RATINGS;
drop table EBOOKS.BOOK_GENRES;
drop table EBOOKS.BOOK_AUTHOR;
drop table EBOOKS.ROLES;

create table EBOOKS.BOOK_TYPES(
id  integer primary key,
type varchar(25)
);

create table EBOOKS.USERS(
SSN varchar(13) primary key,
NAME varchar(25),
PASSWORD varchar(25),
ROLE varchar(10)
);

create table EBOOKS.ROLES(
ROLE varchar(10) primary key
);

create table EBOOKS.BOOK_PAPER_QUALITIES(
id integer  primary key,
QUALITY varchar(25)
);

create table EBOOKS.RATINGS(
id integer  primary key,
RATING varchar(5)
);

create table EBOOKS.BOOK_GENRES(
id integer  primary key,
genre varchar(25)
);

create table EBOOKS.EBOOKS
(ISBN varchar(50) primary key,
DENUMIRE varchar(50),
ID_TYPE  integer,
ID_QUALITY  integer,
PAGES integer,
ID_GENRE integer,
PRET numeric(10,2)
--,FOREIGN KEY(id_type) REFERENCES BOOK_TYPES(ID),
--FOREIGN KEY(ID_QUALITY) REFERENCES BOOK_PAPER_QUALITIES(ID),
--FOREIGN KEY(ID_GENRE) REFERENCES BOOK_GENRES(ID)
);

create table EBOOKS.BOOK_AUTHOR(
SSN varchar(13) primary key,
FIRST_NAME varchar(30),
FAMILY_NAME varchar(30)
);

create table EBOOKS.EBOOKS_AUTHORS
(
ID integer primary key,
ID_ISBN varchar(50),
ID_SSN varchar(13)
--,FOREIGN KEY(ID_ISBN) REFERENCES EBOOKS(ISBN),
--FOREIGN KEY(ID_SSN) REFERENCES BOOK_AUTHOR(SSN)
);

create table EBOOKS.EBOOKS_RATINGS_USERS
(
ID integer primary key,
ID_RATING integer,
ID_SSN varchar(13),
ID_ISBN varchar(50)
--,FOREIGN KEY(ID_RATING) REFERENCES RATINGS(ID),
--FOREIGN KEY(ID_SSN) REFERENCES USERS(SSN),
--FOREIGN KEY(ID_ISBN) REFERENCES EBOOKS(ISBN)
);

------------------------------------------------------
insert into EBOOKS.BOOK_TYPES(id, type)
values (1, 'EBOOKS');

insert into EBOOKS.BOOK_TYPES(id, type)
values (2, 'KINDLE');

insert into EBOOKS.BOOK_TYPES(id, type)
values (3, 'PDF');

---------------------------------------------------
insert into EBOOKS.BOOK_GENRES(id, genre)
values (1, 'Fictiune');

insert into EBOOKS.BOOK_GENRES(id, genre)
values (2, 'Biografii si memorii');

insert into EBOOKS.BOOK_GENRES(id, genre)
values (3, 'Dictionare');

insert into EBOOKS.BOOK_GENRES(id, genre)
values (4, 'Gastronomie');

insert into EBOOKS.BOOK_GENRES(id, genre)
values (5, 'Stiinte');
------------------------
insert into EBOOKS.USERS(SSN, NAME, PASSWORD, ROLE)
values('2801010121111', 'Pop Ana', 'iniana123','user');

insert into EBOOKS.USERS(SSN, NAME, PASSWORD, ROLE)
values('1801010121122', 'Popa Andrei', 'iniandrei123','user');

insert into EBOOKS.USERS(SSN, NAME, PASSWORD, ROLE)
values('2800910121133', 'Popa Andra', 'iniandra123','user');

insert into EBOOKS.USERS(SSN, NAME, PASSWORD, ROLE)
values('0000000000001', 'admin', 'admin','admin');
------------------------
insert into EBOOKS.ROLES(ROLE)
values('admin');

insert into EBOOKS.ROLES(ROLE)
values('user');

------------------------

insert into EBOOKS.BOOK_PAPER_QUALITIES(id, quality)
values(1, 'Normal');

insert into EBOOKS.BOOK_PAPER_QUALITIES(id, quality)
values(2, 'Fine');
------------------------
insert into EBOOKS.RATINGS(id, RATING)
values (1, '*');

insert into EBOOKS.RATINGS(id, RATING)
values (2, '**');

insert into EBOOKS.RATINGS(id, RATING)
values (3, '***');

insert into EBOOKS.RATINGS(id, RATING)
values (4, '****');

insert into EBOOKS.RATINGS(id, RATING)
values (5, '*****');

-----------------------
insert into EBOOKS.EBOOKS(ISBN, DENUMIRE, PRET, ID_TYPE, ID_QUALITY, PAGES, ID_GENRE)
values('978-606-40-0091-0', 'Imi pare rau, sunt asteptata', 20.99, 3, 2, 368, 1);--Agnes Martin Lugand

insert into EBOOKS.EBOOKS(ISBN, DENUMIRE, PRET, ID_TYPE, ID_QUALITY, PAGES, ID_GENRE)
values('978-606-40-0114-6', 'In ape adanci', 23.99, 1, 1, 432, 1);  --Paula Hawkins

insert into EBOOKS.EBOOKS(ISBN, DENUMIRE, PRET, ID_TYPE, ID_QUALITY, PAGES, ID_GENRE)
values('978-606-33-1229-8', 'Dupa ce te-am pierdut', 18.99, 2, 2, 234, 1);  --JojoMoyes

insert into EBOOKS.EBOOKS(ISBN, DENUMIRE, PRET, ID_TYPE, ID_QUALITY, PAGES, ID_GENRE)
values('978-606-33-1228-8', 'Un barbat si o femeie', 19.99, 1, 1, 302, 1);  --JojoMoyes

insert into EBOOKS.EBOOKS(ISBN, DENUMIRE, PRET, ID_TYPE, ID_QUALITY, PAGES, ID_GENRE)
values('978-606-33-1329-8', 'Coroana de foc', 69.99, 3, 2, 654, 1);  --KenFollet

insert into EBOOKS.EBOOKS(ISBN, DENUMIRE, PRET, ID_TYPE, ID_QUALITY, PAGES, ID_GENRE)
values('978-973-46-2698-4', 'Cuvinde incrucisate', 13.99, 2, 1, 219, 2);  --Aurora Liiceanu

insert into EBOOKS.EBOOKS(ISBN, DENUMIRE, PRET, ID_TYPE, ID_QUALITY, PAGES, ID_GENRE)
values('978-973-46-3396-8', 'Supuse sau rebele', 25.96, 3, 1, 256, 2);  --Aurora Liiceanu

insert into EBOOKS.EBOOKS(ISBN, DENUMIRE, PRET, ID_TYPE, ID_QUALITY, PAGES, ID_GENRE)
values('978-973-46-2130-9', 'Patru femei, patru povesti', 24.71, 2, 1, 230, 2);  --Aurora Liiceanu

insert into EBOOKS.EBOOKS(ISBN, DENUMIRE, PRET, ID_TYPE, ID_QUALITY, PAGES, ID_GENRE)
values('978-606-79-3128-0', 'Disparuta in Moscova', 34.90, 2, 1, 464, 2);  --mARGUERITE

insert into EBOOKS.EBOOKS(ISBN, DENUMIRE, PRET, ID_TYPE, ID_QUALITY, PAGES, ID_GENRE)
values('978-606-68-3174-1', 'Dictionar de sinonime', 16.90, 1, 2, 160, 3);  --Marius Dulgheru

insert into EBOOKS.EBOOKS(ISBN, DENUMIRE, PRET, ID_TYPE, ID_QUALITY, PAGES, ID_GENRE)
values('978-606-68-3173-1', 'Dictionar de antonime', 19.90, 1, 1, 210, 3);  --Marius Dulgheru

insert into EBOOKS.EBOOKS(ISBN, DENUMIRE, PRET, ID_TYPE, ID_QUALITY, PAGES, ID_GENRE)
values('978-606-68-3172-1', 'Dictionar de omonime', 14.95, 2, 2, 130, 3);  --Marius Dulgheru

insert into EBOOKS.EBOOKS(ISBN, DENUMIRE, PRET, ID_TYPE, ID_QUALITY, PAGES, ID_GENRE)
values('978-606-68-0393-9', 'Caiet de matematica clasa a IX-a', 25.95, 2, 2, 160, 5);  --cLEOPATRA mIHAILESCU

insert into EBOOKS.EBOOKS(ISBN, DENUMIRE, PRET, ID_TYPE, ID_QUALITY, PAGES, ID_GENRE)
values('978-606-68-0394-9', 'Caiet de matematica clasa a X-a', 24.10, 2, 2, 150, 5);  --Mariana Mogus
-----------------------
insert into EBOOKS.BOOK_AUTHOR(SSN, FIRST_NAME, FAMILY_NAME)
values('1901209162221', 'PAULA', 'HAWKINS');

insert into EBOOKS.BOOK_AUTHOR(SSN, FIRST_NAME, FAMILY_NAME)
values('1901209162222', 'JOJO', 'MOYES');

insert into EBOOKS.BOOK_AUTHOR(SSN, FIRST_NAME, FAMILY_NAME)
values('1901209162223', 'AGNES', 'MARTIN-LUGAND');

insert into EBOOKS.BOOK_AUTHOR(SSN, FIRST_NAME, FAMILY_NAME)
values('1901209162224', 'KEN', 'FOLLET');

insert into EBOOKS.BOOK_AUTHOR(SSN, FIRST_NAME, FAMILY_NAME)
values('1901209162225', 'AURORA', 'LIICEANU');

insert into EBOOKS.BOOK_AUTHOR(SSN, FIRST_NAME, FAMILY_NAME)
values('1901209162226', 'MARGUERITE', 'HARISSON');

insert into EBOOKS.BOOK_AUTHOR(SSN, FIRST_NAME, FAMILY_NAME)
values('1901209162227', 'MARIUS-EMIL', 'DULGHERU');

insert into EBOOKS.BOOK_AUTHOR(SSN, FIRST_NAME, FAMILY_NAME)
values('1901209162228', 'MARIANA', 'MOGUS');

insert into EBOOKS.BOOK_AUTHOR(SSN, FIRST_NAME, FAMILY_NAME)
values('1901209162229', 'CLEOPATRA', 'MIHAILESCU');

-----------------------

insert into EBOOKS.EBOOKS_AUTHORS(ID, ID_ISBN, ID_SSN)
values (1, '978-606-40-0091-0', '1901209162223');

insert into EBOOKS.EBOOKS_AUTHORS(ID, ID_ISBN, ID_SSN)
values (2, '978-606-40-0114-6', '1901209162221');

insert into EBOOKS.EBOOKS_AUTHORS(ID, ID_ISBN, ID_SSN)
values (3, '978-606-33-1229-8', '1901209162222');

insert into EBOOKS.EBOOKS_AUTHORS(ID, ID_ISBN, ID_SSN)
values (4, '978-606-33-1228-8', '1901209162222');

insert into EBOOKS.EBOOKS_AUTHORS(ID, ID_ISBN, ID_SSN)
values (5, '978-606-33-1229-8', '1901209162222');

insert into EBOOKS.EBOOKS_AUTHORS(ID, ID_ISBN, ID_SSN)
values (6, '978-973-46-2698-4', '1901209162225');

insert into EBOOKS.EBOOKS_AUTHORS(ID, ID_ISBN, ID_SSN)
values (7, '978-606-33-1229-8', '1901209162225');

insert into EBOOKS.EBOOKS_AUTHORS(ID, ID_ISBN, ID_SSN)
values (8, '978-606-33-1229-8', '1901209162225');

insert into EBOOKS.EBOOKS_AUTHORS(ID, ID_ISBN, ID_SSN)
values (9, '978-606-79-3128-0', '1901209162226');

insert into EBOOKS.EBOOKS_AUTHORS(ID, ID_ISBN, ID_SSN)
values (10, '978-606-68-3174-1', '1901209162227');

insert into EBOOKS.EBOOKS_AUTHORS(ID, ID_ISBN, ID_SSN)
values (11, '978-606-68-3173-1', '1901209162227');

insert into EBOOKS.EBOOKS_AUTHORS(ID, ID_ISBN, ID_SSN)
values (12, '978-606-68-3172-1', '1901209162227');

insert into EBOOKS.EBOOKS_AUTHORS(ID, ID_ISBN, ID_SSN)
values (13, '978-606-68-0393-9', '1901209162229');

insert into EBOOKS.EBOOKS_AUTHORS(ID, ID_ISBN, ID_SSN)
values (14, '978-606-68-0394-9', '1901209162228');

----------------------------

insert into EBOOKS.EBOOKS_RATINGS_USERS (ID, ID_RATING, ID_SSN, ID_ISBN) 
	values (1, 3, '2800910121133', '978-606-40-0091-0');
insert into EBOOKS.EBOOKS_RATINGS_USERS (ID, ID_RATING, ID_SSN, ID_ISBN) 
	values (2, 1, '2800910121133', '978-606-68-0393-9');
insert into EBOOKS.EBOOKS_RATINGS_USERS (ID, ID_RATING, ID_SSN, ID_ISBN) 
	values (3, 2, '2800910121133', '978-606-68-3173-1');
insert into EBOOKS.EBOOKS_RATINGS_USERS (ID, ID_RATING, ID_SSN, ID_ISBN) 
	values (4, 4, '2800910121133', '978-606-33-1329-8');
insert into EBOOKS.EBOOKS_RATINGS_USERS (ID, ID_RATING, ID_SSN, ID_ISBN) 
	values (5, 5, '1801010121122', '978-606-33-1329-8');
insert into EBOOKS.EBOOKS_RATINGS_USERS (ID, ID_RATING, ID_SSN, ID_ISBN) 
	values (6, 4, '1801010121122', '978-606-79-3128-0');
insert into EBOOKS.EBOOKS_RATINGS_USERS (ID, ID_RATING, ID_SSN, ID_ISBN) 
	values (7, 5, '1801010121122', '978-973-46-2698-4');
insert into EBOOKS.EBOOKS_RATINGS_USERS (ID, ID_RATING, ID_SSN, ID_ISBN) 
	values (8, 3, '1801010121122', '978-606-40-0114-6');
insert into EBOOKS.EBOOKS_RATINGS_USERS (ID, ID_RATING, ID_SSN, ID_ISBN) 
	values (9, 3, '2801010121111', '978-606-68-0394-9');


----------------------------


select count(1), 'EBOOKS_AUTHORS' from EBOOKS.EBOOKS_AUTHORS
union
select count(1), 'EBOOKS_RATINGS_USERS' from EBOOKS.EBOOKS_RATINGS_USERS
union
select count(1), 'EBOOKS' from EBOOKS.EBOOKS
union
select count(1), 'BOOK_TYPES' from EBOOKS.BOOK_TYPES
union
select count(1), 'USERS' from EBOOKS.USERS
union
select count(1), 'BOOK_PAPER_QUALITIES' from EBOOKS.BOOK_PAPER_QUALITIES
union
select count(1), 'RATINGS' from EBOOKS.RATINGS
union
select count(1), 'BOOK_GENRES' from EBOOKS.BOOK_GENRES
union
select count(1), 'BOOK_AUTHOR' from EBOOKS.BOOK_AUTHOR;
