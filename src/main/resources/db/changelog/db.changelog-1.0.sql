--liquibase formatted sql

--changeset mmichalenok:1
CREATE TABLE IF NOT EXISTS currencies
(
    cur_id varchar(255) not null,
    cur_code varchar(255),
    cur_abbreviation varchar(255),
    cur_scale varchar(255),
    cur_name varchar(255),
    cur_date_start date,
    cur_date_end date,
    primary key (cur_id)
    );
   --rollback DROP TABLE currencies;

--changeset mmichalenok:2
CREATE TABLE IF NOT EXISTS rates
(
    cur_id varchar(255) not null,
    date date not null,
    cur_official_rate varchar(255),
    primary key (cur_id, date))

--rollback DROP TABLE rates;