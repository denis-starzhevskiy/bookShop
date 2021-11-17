DROP TABLE IF EXISTS category;
DROP TABLE IF EXISTS book;

create table category
(
    category_id serial,
    category_name varchar(100) not null,
    constraint category_category_id_uindex
        unique (category_id),
    constraint category_category_name_uindex
        unique (category_name)
);

alter table category
    add primary key (category_id);

create table author
(
    author_id serial,
    author_name varchar(100) not null,
    constraint author_author_id_uindex
        unique (author_id)
);

alter table author
    add primary key (author_id);

create table statistics
(
    statistics_id serial,
    created_date varchar(100) not null,
    version smallint not null,
    views int not null,
    constraint statistics_statistics_id_uindex
        unique (statistics_id)
);

alter table statistics
    add primary key (statistics_id);


create table book
(
    id serial primary key,
    created_date timestamp default CURRENT_TIMESTAMP not null,
    version integer default 0 not null,
    book_name varchar(100) not null,
    description varchar(500) not null,
    page_amount integer not null,
    photo_name varchar(200) not null,
    ISBN varchar(13) not null,
    author_id integer not null,
    category_id int not null,
    statistics_id int not null,
    constraint category_fk
        foreign key (category_id) references category (category_id)
            on update cascade on delete cascade,
    constraint author_fk
            foreign key (author_id) references author (author_id)
            on update cascade on delete cascade,
    constraint statistics_fk
            foreign key (statistics_id) references statistics (statistics_id)
            on update cascade on delete cascade
);

ALTER TABLE book ADD constraint statistics_fk
    foreign key (statistics_id) references statistics (statistics_id)
    on update cascade on delete cascade;

