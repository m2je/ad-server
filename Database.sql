create database if not exists ad_server ;
use ad_server;
create table if not exists ad(
 partner_id varchar(100) not null,
 duration int not null,
 ad_content text,
 ad_date timestamp not null default CURRENT_TIMESTAMP,
 	unique (partner_id),
  primary key(partner_id,ad_date,duration)
)
