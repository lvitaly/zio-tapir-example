create table if not exists book(
    id        serial primary key,
    title     varchar(255) not null,
    author    varchar(255),
    genre     varchar(50)  not null,
    sub_genre varchar(50)  not null,
    height    int          not null,
    publisher varchar(50)
) as select
    rownum() as id,
    title,
    author,
    genre,
    sub_genre,
    height,
    publisher
from csvread('classpath:/books.csv', NULL, 'caseSensitiveColumnNames=true');
