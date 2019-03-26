


show DATABASES; -- 사용할 수 있는 데이터베이스 확인

create DATABASE sample; -- 데이터베이스 생성

use sample; -- 데이터베이스 사용

drop table test;
create table test(
	num integer,
	name char(20)
) default CHARSET=utf8;

insert into test(num, name) values(1, 'park');
insert into test(num, name) values(2, '송희정');

select * from test;

