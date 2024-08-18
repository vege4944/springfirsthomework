
#schedule

#ERD 이미지

![scheduleERD](https://github.com/user-attachments/assets/767874f9-6d5a-452a-82a6-306e91e2d3fb)


#SQL 코드

create table schedule
(
id       bigint auto_increment
primary key,
task     varchar(255) not null,
name     varchar(255) not null,
password varchar(255) not null,
regDate  datetime(6)  null,
modDate  datetime(6)  null
);


//Api 명세서
https://documenter.getpostman.com/view/37679486/2sA3s9CoKv