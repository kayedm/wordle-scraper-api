create schema wordle_bank;

create table wordle_bank.answers
(
    number int,
    word char(5),
    date date,
    primary key (number)
);
