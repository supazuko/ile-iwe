create database if not exists iwedb;

create user if not exists 'learner'@'localhost' identified by 'learn123';
grant all privileges on iwedb.* to 'learner'@'localhost';
flush privileges ;