CREATE TABLE car.`car_product`
(
    `product_id`     bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'car product id',
    `car_product_no`   varchar(255)        NOT NULL DEFAULT '' COMMENT 'car商品编号',
    `car_model`   varchar(255)        NOT NULL DEFAULT '' COMMENT '主题名称',
    `stock`       bigint(20) unsigned        not null default 0 comment '库存',
    `gmt_create`    datetime            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `gmt_modified`    datetime            NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`product_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
    comment 'car商品';

CREATE TABLE car.`car`
(
    `car_id`        bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'car id',
    `car_no`   varchar(255)        NOT NULL DEFAULT '' COMMENT 'car编号',
    `product_id` bigint(20) unsigned not null default 0 comment 'car product id',
    `gmt_create`       datetime            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `gmt_modified`       datetime            NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`car_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
    comment 'car';

CREATE TABLE car.`car_reserve`
(
    `reserve_id`       bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
    `car_no`   varchar(255)        NOT NULL DEFAULT '' COMMENT 'car编号',
    `user_id`       bigint(20) unsigned        not null default 0 comment '用户id',
    `reserve_start_time`        varchar(25)        NOT NULL DEFAULT '' COMMENT '预约开始时间',
    `reserve_end_time`        varchar(25)        NOT NULL DEFAULT '' COMMENT '预约结束时间',
    `gmt_create`       datetime            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `gmt_modified`       datetime            NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`reserve_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
    comment '车子预约明细';

insert into car.`car_product` (product_id, car_product_no, car_model, stock) values (1, "ToyotaCamry", "Toyota Camry", 2), (2, "BMW650", "BMW 650", 2);

insert into car.car (car_no, product_id) values ("ToyotaCamry1", 1);
insert into car.car (car_no, product_id) values ("ToyotaCamry1", 1);
insert into car.car (car_no, product_id) values ("BMW6501", 2);
insert into car.car (car_no, product_id) values ("BMW6502", 2);
