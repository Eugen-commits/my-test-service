create sequence if not exists driver_rating_seq
    start 1;


create table if not exists driver_rating
(
  driver_rating_id bigint not null default nextval('driver_rating_seq' :: regclass),
  driver_id bigint not null,
  order_id bigint not null,
  grade int ,
  constraint driver_rating_id_pk primary key (driver_rating_id)
);

alter table driver_rating
    add constraint driver_rating_id_fk foreign key (driver_id)
        references taxi_drive_info (driver_id);
alter table driver_rating
    add constraint order_rating_id_fk foreign key (order_id)
        references orders (order_id);