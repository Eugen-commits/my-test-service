create sequence if not exists orders_seq
    start 1;

create table if not exists orders
(
    clientNumber   bigint not null          default nextval('orders_seq' :: regclass),
    level       int    not null,
    carModel   text   not null,
    constraint orders_id_pk
        primary key (clientNumber)
);