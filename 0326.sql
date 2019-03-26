

use mysql;
use sample;
-- 회원테이블
create table usertbl(
userid char(15) not null primary key,
name varchar(20) not null,
birthyear int not null, 
addr char(100),
mobile char(11),
mdate date)ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- 구매테이블
create table buytbl(
num int auto_increment primary key,
userid char(8) not null,
productname char(10),
groupname char(10),
price int not null,
amount int not null,
foreign key (userid) references usertbl(userid) on delete cascade)ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- 데이터 삽입
insert into usertbl values('kty', '김태연',1989,'전주','01011111111', '1989-3-9');
insert into usertbl values('bsj', '배수지',1994,'광주','01022222222', '1994-10-10');
insert into usertbl values('ksh', '김설현',1995,'부천','01033333333', '1995-1-3');
insert into usertbl values('bjh', '배주현',1991,'대구','01044444444', '1991-3-29');
insert into usertbl values('ghr', '구하라',1991,'광주','01055555555', '1991-1-13');
insert into usertbl values('san', '산다라박',1984,'부산','01066666666', '1984-11-12');
insert into usertbl values('jsm', '전소미',2001,'캐나다','01077777777', '2001-3-9');
insert into usertbl values('lhl', '이효리',1979,'서울','01088888888', '1979-5-10');
insert into usertbl values('iyou', '아이유',1993,'서울','01099999999', '1993-5-19');
insert into usertbl values('ailee', '에일리',1989,'미국','01000000000', '1989-5-30');

commit;

insert into buytbl values(null, 'kty', '운동화', '잡화', 30, 2);
insert into buytbl values(null, 'kty', '노트북', '전자', 1000, 1);
insert into buytbl values(null, 'jsm', '운동화', '잡화', 30, 1);
insert into buytbl values(null, 'lhl', '모니터', '전자', 200, 1);
insert into buytbl values(null, 'bsj', '모니터', '전자', 200, 1);
insert into buytbl values(null, 'kty', '청바지', '잡화', 100, 1);
insert into buytbl values(null, 'lhl', '책', '서적', 15, 2);
insert into buytbl values(null, 'iyou', '책', '서적', 15, 7);
insert into buytbl values(null, 'iyou', '컴퓨터', '전자', 500, 1);
insert into buytbl values(null, 'bsj', '노트북', '전자', 1000, 1);
insert into buytbl values(null, 'bjh', '메모리', '전자', 50, 4);
insert into buytbl values(null, 'ailee', '운동화', '잡화', 30, 2);
insert into buytbl values(null, 'ghr', '운동화', '잡화', 30, 1);

commit;


select * from buytbl;

-- usertbl 테이블의 모든 데이터를 조회
select * from usertbl;

-- usertbl 테이블에서 userid와 name만 조회
select userid, name from usertbl;

-- usertbl 테이블에서 name이 '김태연'인 데이터 조회
select * 
from usertbl 
where name = '김태연';

-- usertbl 테이블에서 birthyear가 1990년 이후(>)이고 addr이 '서울'인 데이터의 모든 컬럼을 조회
select *
from usertbl
where birthyear>1990 and addr='서울';

-- usertbl 테이블에서 birthyear가 1990년 이후이거나 addr이 '서울'인 데이터의 userid와 name을 조회
select userid, name
from usertbl
where birthyear >= 1990 or addr='서울';

-- usertbl 테이블에서 birthyear가 1990년부터 1993년 사이인 데이터의 모든 컬럼 조회
select *
from usertbl
where birthyear>=1990 and birthyear<=1993;

-- usertbl 테이블에서 name에 '라'가 포함된 데이터의 모든 컬럼 조회
select *
from usertbl
where name like '%라%';

-- usertbl 테이블에서 name이 '배'로 시작하는 데이터의 모든 컬럼 조회
select *
from usertbl
where name like '배%';

-- buytbl 테이블에서 중복되지 않도록 userid를 조회
select distinct userid
from usertbl;

select userid
from usertbl
group by userid;

-- buytbl 테이블에서 userid별로 데이터 개수와 price의 합계를 조회
select userid, count(*), sum(price)
from buytbl
group by userid;

-- buytbl 테이블에서 userid별로 데이터 개수와 price의 합계를 조회하는데 데이터가 2개 이상인 데이터만 조회
select userid, count(*), sum(price)
from buytbl
group by userid
having count(*) >=2;

-- usertbl 테이블의 데이터를 userid의 내림차순으로 정렬해서 출력
select *
from usertbl
order by userid desc;

-- usertbl 테이블의 데이터를 birthyear의 오름차순으로 정렬해서 출력하는데 birthyear가 동일하면 name의 내림차순으로 정
select *
from usertbl
order by birthyear, name desc;

-- usertbl의 데이터를 birthyear 오름차순, name 내림차순 정렬하고 앞에서부터 5개 가져오기
select *
from usertbl
order by birthyear, name desc
limit 5;

-- usertbl의 데이터를 birthyear 오름차순, name 내림차순 정렬하고 두번째 부터 5개 가져오기
select *
from usertbl
order by birthyear, name desc
limit 1,5;	-- 이친구는 말입니다. 0부터 숫자를 셉니다. 두번째자리부터면 1부터. 이쯤이면 0이면 생략한다고 보는 게 좋을 듯??? 

-- usetbl 테이블의 name 과 buytbl 테이블의 productname을 함께 조회 /2개 테이블에서 컬럼을 가져와서 출력하기 때문에 join을 사용
select usertbl.name, buytbl.productname
from usertbl, buytbl
WHERE usertbl.userid = buytbl.userid;

-- usertbl 테이블에서 userid와 mobile 그리고 buytbl에서 groupname을 조회 birthyear가 1993 이상인 데이터만 조회
select usertbl.userid, usertbl.mobile, buytbl.groupname
FROM usertbl, buytbl
where usertbl.userid = buytbl.userid and birthyear >=1993;

-- name이 전소미인 데이터가 구매한 데이터의 userid와 productname 그리고 groupname을 조회

     -- join을 이용한 방법
select buytbl.userid, productname, groupname
from usertbl, buytbl
where usertbl.userid=buytbl.userid and name = '전소미';

     -- subquery를 이용한 방법
Select userid, productname, groupname
From buytbl
Where userid = (select userid from usertbl where name='전소미');

-- usertbl 테이블의 name이 전소미 또는 배수지인 데이터의 buytbl의 userid, productname, groupname을 조회
select userid, productname, groupname
from buytbl
where userid in(select userid from usertbl where name in('전소미','배수지'));

-- 데이터 삽입 _ 외래키가 없을 때는 not null, unique에 주의해서 삽입.
INSERT into usertbl(userid, name, birthyear, addr, mobile, mdate) values('jennie','김제니',1996,'서울','01012345678','1996-01-16');

-- 데이터 삽입 _ 외래키 고려 필수. auto_increment 입력할 필요 없음.
INSERT into buytbl(userid, productname, groupname, price, amount) values('ggangpae','스파클링','음료수',5700,1);


-- buytbl 테이블에서 num이 9번인 데이터의 price를 700,000으로 수정
	-- buytbl 테이블에 num이 9번인 데이터가 있는지 확인
		select * from buytbl where num=9;
	-- 수정하는 SQL
		update buytbl set price=700000 where num=9;

	
-- 삭제 _ buytbl 테이블에서 num이 9번인 데이터를 삭제
delete from buytbl where num=9;

-- usertbl 테이블에 데이터를 삽입하는 프로시저
create procedure insertuser(vuserid char(15), vname varchar(10)character set utf8, 
			birthday int, vaddr char(100) character set utf8, vmobile char(11), vmdate date)
BEGIN
	insert into usertbl(userid,name, birthyear, addr, mobile, mdate) values(vuserid, vname, birthday, vaddr, vmobile, vmdate);
END ; 

-- 프로시저 실행
call insertuser('joy','조이',1996,'제주','01098765432','1996-09-03');

select avg(price) from buytbl;

select * from usertbl;

select * from buytbl;


