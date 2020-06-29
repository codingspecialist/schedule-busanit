# practiceProgress


### 배포시 주의할 점
WebContent/excelfile 폴더 생성해서 war만들기


### MySQL 한글 설정
[client]
default-character-set=utf8

[mysql]
default-character-set=utf8

[mysqld]
collation-server = utf8_unicode_ci
init-connect='SET NAMES utf8'
init_connect='SET collation_connection = utf8_general_ci'
character-set-server=utf8


### 스키마 생성

```sql
CREATE DATABASE `test_sch` /*!40100 DEFAULT CHARACTER SET utf8 */;
```



### db유저 아이디 비밀번호

```sql
-- 유저이름@아이피주소
create user 'bitc'@'%' identified by 'bitc5600';

-- ON DB이름.테이블명
-- TO 유저이름@아이피주소
GRANT ALL PRIVILEGES ON *.* TO 'bitc'@'%';
ALTER DATABASE test_sch CHARACTER SET utf8 DEFAULT COLLATE utf8_general_ci;
use test_sch;
```



### class_table
```sql
CREATE TABLE `class_table` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `room` int(11) DEFAULT NULL,
  `class_name` varchar(300) DEFAULT NULL,
  `class_part` varchar(10) DEFAULT NULL,
  `class_open` date DEFAULT NULL,
  `class_close` date DEFAULT NULL,
  `homeroom_prof` varchar(20) DEFAULT NULL,
  `excel_name` varchar(300) DEFAULT NULL,
  `status` varchar(8) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

```



### practice_table

```sql
CREATE TABLE `practice_table` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `class_name` varchar(300) DEFAULT NULL,
  `class_date` date DEFAULT NULL,
  `day_week` varchar(4) DEFAULT NULL,
  `class_time` int(11) DEFAULT NULL,
  `start_time` varchar(11) DEFAULT NULL,
  `end_time` varchar(11) DEFAULT NULL,
  `subject1` varchar(100) DEFAULT NULL,
  `subject2` varchar(100) DEFAULT NULL,
  `prof` varchar(20) DEFAULT NULL,
  `room` int(11) DEFAULT NULL,
  `class_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

```

