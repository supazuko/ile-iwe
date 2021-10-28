create database ileiwe_db;

create user 'learner'@'localhost' identified by 'learn123';
grant all privileges on ileiwe_db.* to 'learner'@'localhost';
flush privileges ;