create table if not exists tbl_vehicle
(
    id                  bigint unsigned not null auto_increment,
    license_plate       varchar(25) not null,
    created_at          timestamp null default current_timestamp,
    updated_at          timestamp null default null on update current_timestamp,
    primary key (id),
    unique key uq_vehicle (license_plate)
) engine = innodb;
