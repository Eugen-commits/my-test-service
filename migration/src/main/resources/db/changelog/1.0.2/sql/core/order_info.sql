/*create sequence if not exists orders_seq
    start 1;

create table if not exists orders
(
    clientNumber   bigint not null          default nextval('orders_seq' :: regclass),
    level       int    not null,
    carModel   text   not null,
    constraint orders_id_pk
        primary key (clientNumber)
);*/
create sequence if not exists order_seq
    start 1;

create table if not exists orders
(
    order_id      bigint not null default nextval('order_seq' :: regclass),
    client_number bigint not null,
    startPointAddress text not null,
    endPointAddress text not null,
    level int,
    driver_id     bigint not null,
    constraint order_pk primary key (order_id)
);

comment on table orders is 'Заказы';
comment on column orders.order_id is 'Идентификатор заказа';
comment on column orders.client_number is 'Идентификатор клиента';
comment on column orders.driver_id is 'Идентификатор водителя';
comment on column orders.level is 'Уровень автомобиля';
comment on column orders.startPointAddress is 'Начальная точка поездки';
comment on column orders.endPointAddress is 'Конечная точка поездки';

alter table orders
    add constraint order_driver_id_fk foreign key (driver_id)
        references taxi_drive_info (driver_id);