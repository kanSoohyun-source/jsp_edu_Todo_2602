-- 스키마 생성
CREATE DATABASE IF NOT EXISTS `jsp_edu_todo_2602`;

-- 스키마 지정
USE `jsp_edu_todo_2602`;

-- 테이블 생성
CREATE TABLE IF NOT EXISTS `todo` (
                                      `tno` INT AUTO_INCREMENT PRIMARY KEY ,
                                      `title` VARCHAR(100) NOT NULL ,
                                      `due_date` DATE NOT NULL ,
                                      `finish` TINYINT DEFAULT 0
);

-- 전용 사용자 생성 - id = todo_user, pw = 3432
CREATE USER `todo_user`@`localhost` IDENTIFIED BY '6800';

-- 사용자에게 DB 권한 부여
GRANT ALL PRIVILEGES ON `jsp_edu_todo_2602`.* TO `todo_user`@`localhost`;


-- 새 테이블 생성
CREATE TABLE IF NOT EXISTS member (
                                      `member_id` VARCHAR(50) PRIMARY KEY ,
                                      `passwd` VARCHAR(50) NOT NULL ,
                                      `name` VARCHAR(10) NOT NULL
);

-- 사용자 추가
INSERT INTO `member` values ('user00','1111','사용자0');
INSERT INTO `member` values ('user01','1111','사용자1');
INSERT INTO `member` values ('user02','1111','사용자2');

-- column 추가
ALTER TABLE `member` ADD COLUMN `uuid` VARCHAR(50);
/*
 VO -> DAO -> DAO Test -> DTO -> service -> service Test -> controller 수정
*/

/*
법적으로 회원데이터의 비밀번호는 암호화(복호화가 안되는)해서 저장
암호화 -> 일반 문자열을 의미를 알아보기 힘든 문자열로 변경
복호화 -> 암호화된 문자열을 원래 문자열로 변경 => 안 되도록 해야함(법으로 규정)

- 비밀번호가 암호화되어 DB에 저장되기 때문에
1. Column의 길이가 길어야 함

2. 법이 적용되면서 비밀번호 찾기를 하면 임시 비밀번호가 부여됨
-> 개발자도 비밀번호가 뭔지 모름
-> 적용 전에는 실제 비밀번호를 알려줬음

- 가장 간단한 암호화는 DB의 password()인데, 비추
  => 구현이 간단해서 로직 이해하기에는 좋음
  => DB에 종속화 -> DB를 못 바꾸는 문제 발생
- 포트폴리오에서는 JAVA가 지원하는 암호화 사용.
- 수업에서는 스프링 시큐리티에서 사용
*/
