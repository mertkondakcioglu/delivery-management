create table if not exists tbl_vehicle
(
    id                  bigint unsigned not null auto_increment,
    license_plate       varchar(25) not null,
    created_at          timestamp null default current_timestamp,
    updated_at          timestamp null default null on update current_timestamp,
    primary key (id),
    unique key uq_vehicle (license_plate)
) engine = innodb;

create table if not exists tbl_delivery_point
(
    id                  bigint unsigned not null auto_increment,
    delivery_point      varchar(50) not null,
    value               int not null,
    created_at          timestamp null default current_timestamp,
    updated_at          timestamp null default null on update current_timestamp,
    primary key (id),
    unique key uq_delivery_point (value)
) engine = innodb;

create table if not exists tbl_bag
(
    id                  bigint unsigned not null auto_increment,
    barcode             varchar(7) not null,
    delivery_point      int not null,
    status              varchar(25) not null default 'CREATED' COMMENT 'CREATED, LOADED, UNLOADED',
    created_at          timestamp null default current_timestamp,
    updated_at          timestamp null default null on update current_timestamp,
    primary key (id),
    key fk_bag_delivery_point_idx (delivery_point),
    unique key uq_bag (barcode),
    constraint fk_bag_delivery_point foreign key (delivery_point) references tbl_delivery_point (value)
) engine = innodb;

create table if not exists tbl_shipment
(
    id                  bigint unsigned not null auto_increment,
    barcode             varchar(11) not null,
    delivery_point      int not null,
    volumetric_weight   int not null,
    status              varchar(25) not null default 'CREATED' COMMENT 'CREATED, LOADED_INTO_BAG, LOADED, UNLOADED',
    created_at          timestamp null default current_timestamp,
    updated_at          timestamp null default null on update current_timestamp,
    primary key (id),
    key fk_shipment_delivery_point_idx (delivery_point),
    unique key uq_shipment (barcode),
    constraint fk_shipment_delivery_point foreign key (delivery_point) references tbl_delivery_point (value)
) engine = innodb;

create table if not exists tbl_shipment_bag
(
    id                  bigint unsigned not null auto_increment,
    barcode             varchar(11) not null,
    bag_barcode         varchar(7) not null,
    created_at          timestamp null default current_timestamp,
    updated_at          timestamp null default null on update current_timestamp,
    primary key (id),
    key fk_shipment_barcode_idx (barcode),
    key fk_shipment_bag_barcode_idx (bag_barcode),
    unique key uq_shipment_barcode (barcode),
    constraint fk_shipment_barcode foreign key (barcode) references tbl_shipment (barcode),
    constraint fk_shipment_bag_barcode foreign key (bag_barcode) references tbl_bag (barcode)
) engine = innodb;

create table if not exists tbl_delivery_error
(
    id                  bigint unsigned not null auto_increment,
    barcode             varchar(11) not null,
    delivery_point      int not null,
    created_at          timestamp null default current_timestamp,
    primary key (id)
) engine = innodb;
