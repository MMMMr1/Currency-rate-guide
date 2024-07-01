INSERT INTO currencies (cur_id, cur_code, cur_abbreviation, cur_scale, cur_name, cur_date_start, cur_date_end)
VALUES ('145', '840', 'USD', '1', 'Доллар США', DATE '1991-01-01', DATE '2021-07-08'),
       ('431', '840', 'USD', '1', 'Доллар США', DATE '2021-07-09', DATE '2050-01-01'),
       ('451', '978', 'EUR', '1', 'Евро', DATE '2021-07-09', DATE '2050-01-01');

INSERT INTO rates (cur_id, date, cur_official_rate)
VALUES ('431', DATE '2024-06-30', '3.1624'),
       ('451', DATE '2024-06-30', '3.3821');