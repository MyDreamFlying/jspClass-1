# ************************************************************
# Sequel Pro SQL dump
# Version 5446
#
# https://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 8.0.23)
# Database: jsp
# Generation Time: 2021-04-18 23:59:36 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
SET NAMES utf8mb4;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table alba
# ------------------------------------------------------------

DROP TABLE IF EXISTS `alba`;

CREATE TABLE `alba` (
  `al_id` char(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT 'ex) ''A00000001''',
  `al_name` varchar(20) NOT NULL DEFAULT '',
  `al_age` int NOT NULL,
  `al_zip` varchar(7) NOT NULL DEFAULT '',
  `al_addr1` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '',
  `al_addr2` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '',
  `al_hp` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '',
  `gr_code` char(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '',
  `al_gen` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT 'ex) ''F'' / ''M''',
  `al_mail` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '',
  `al_career` varchar(200) DEFAULT NULL,
  `al_spec` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `al_desc` varchar(500) DEFAULT NULL,
  `al_img` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`al_id`),
  KEY `gradeCode` (`gr_code`),
  CONSTRAINT `gradeCode` FOREIGN KEY (`gr_code`) REFERENCES `grade` (`gr_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `alba` WRITE;
/*!40000 ALTER TABLE `alba` DISABLE KEYS */;

INSERT INTO `alba` (`al_id`, `al_name`, `al_age`, `al_zip`, `al_addr1`, `al_addr2`, `al_hp`, `gr_code`, `al_gen`, `al_mail`, `al_career`, `al_spec`, `al_desc`, `al_img`)
VALUES
	('A0000001','김두한',30,'35036','대전','중구 석교동','010-1234-5678','G006','M','abc@gmail.com','경력 없음','종로 접수 다수','4달러','90562534-b671-4e6d-891e-61fb03f99faf'),
	('A0000002','홍길동',40,'12345','조선','연산군','0401-859-994','G001','F','hong@naver.com','아버지',NULL,'','A0000002'),
	('A0000003','무야호',42,'12345','미국','알레스카','999-888-444','G006','M','muya@ho.net','무한도전 출연','알래스카 한인 회장 경험 다수','무야호!','4a3e446f-3cc1-4f4e-a61f-5f52dbfb4388'),
	('A0000004','이현기',21,'12345','대전광역시','중구 대흥동 영민빌딩','412-312-4123','G004','M','leehk@playddit.net','버그 해결 다수','소주 3병 이상','안녕하세요~',NULL),
	('A0000005','고길동',29,'54321','서울','강북구','02-4124-4221','G003','M','gogildong@naver.com','무료 하숙',NULL,'둘리는 나의 원수','71328541-1593-483a-9d8e-e8da37eef555'),
	('A0000008','일론머스크',40,'1241','미국 서구','캘리포니아','512-412-3311','G002','M','elon@spacex.com','Tesla\r\nPaypal\r\n','자산 1위','Hello everyone','c6a96872-1b75-4331-8dcd-12c242064b29'),
	('A0000010','아이유',15,'35012','일본','나카시마','123-412-3412','G002','F','iu@email.com','음악방송 1위','괴물스펙','하요~','8370cb46-6843-4acd-a4e5-4d35f5eeb3b3'),
	('A0000011','곽철용',51,'41221','서울특별시','마포대교','02-1422-1111','G001','M','mapon@dagyo.net','타짜 출연 1회','순정남','마포대교는 무너졌냐?','3d4d9a75-b8c3-4020-a75f-a4dab54fe2d2'),
	('A0000012','마동석',41,'1235','중국','청두','01241-2411','G006','M','ma@dongsuk.net','에뛰드 알바','3대 800','뭘봐','a932f585-5a6d-4f0e-ba92-2a5a7753ceab'),
	('A0000013','엄준식',17,'12341','서울','유성구','11-231-4112','G001','M','um@junsik.com','없음','없음','없음','70bccfc7-6630-47f3-a7fe-5d8f05db6637'),
	('A0000014','도우너',5,'121','서울','고길동네','123-1231','G006','M','donut@naver.com','마법','깐따삐아','하이','409d8ca6-abf9-4dde-a885-37f5cbee7b67'),
	('A0000015','노사진',82,'41422','사진없군','그러면 기본사진','123-456-789','G006','F','nopic@picture.com','사진 없이 지낸지 5년','사진 없음','난 사진이 없다',NULL);

/*!40000 ALTER TABLE `alba` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table grade
# ------------------------------------------------------------

DROP TABLE IF EXISTS `grade`;

CREATE TABLE `grade` (
  `gr_code` char(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '',
  `gr_name` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '',
  PRIMARY KEY (`gr_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `grade` WRITE;
/*!40000 ALTER TABLE `grade` DISABLE KEYS */;

INSERT INTO `grade` (`gr_code`, `gr_name`)
VALUES
	('G001','고졸'),
	('G002','초대졸'),
	('G003','대졸'),
	('G004','대학원졸'),
	('G005','석사'),
	('G006','박사');

/*!40000 ALTER TABLE `grade` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table lic_alba
# ------------------------------------------------------------

DROP TABLE IF EXISTS `lic_alba`;

CREATE TABLE `lic_alba` (
  `al_id` char(8) NOT NULL,
  `lic_code` char(4) NOT NULL,
  `lic_date` varchar(30) NOT NULL,
  `lic_img` blob,
  PRIMARY KEY (`al_id`,`lic_code`),
  KEY `lic` (`lic_code`),
  CONSTRAINT `alba_licAlba` FOREIGN KEY (`al_id`) REFERENCES `alba` (`al_id`) ON DELETE CASCADE,
  CONSTRAINT `lic` FOREIGN KEY (`lic_code`) REFERENCES `license` (`lic_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `lic_alba` WRITE;
/*!40000 ALTER TABLE `lic_alba` DISABLE KEYS */;

INSERT INTO `lic_alba` (`al_id`, `lic_code`, `lic_date`, `lic_img`)
VALUES
	('A0000001','L001','2001-01-01',NULL),
	('A0000001','L003','2002-02-02',NULL),
	('A0000002','L002','2002-02-03',NULL),
	('A0000003','L006','2021-04-18 21:16:26',NULL),
	('A0000004','L004','2021-04-18 14:54:28',NULL),
	('A0000005','L002','2021-04-18 21:18:27',NULL),
	('A0000008','L001','2021-04-18 21:25:29',NULL),
	('A0000008','L002','2021-04-18 21:25:29',NULL),
	('A0000010','L001','2021-04-18 18:53:23',NULL),
	('A0000010','L002','2021-04-18 18:53:23',NULL),
	('A0000010','L003','2021-04-18 19:48:39',NULL),
	('A0000010','L004','2021-04-18 20:37:52',NULL),
	('A0000010','L006','2021-04-18 19:48:58',NULL),
	('A0000012','L004','2021-04-18 21:39:09',NULL),
	('A0000015','L002','2021-04-19 08:50:06',NULL),
	('A0000015','L004','2021-04-19 08:50:06',NULL);

/*!40000 ALTER TABLE `lic_alba` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table license
# ------------------------------------------------------------

DROP TABLE IF EXISTS `license`;

CREATE TABLE `license` (
  `lic_code` char(4) NOT NULL,
  `lic_name` varchar(50) NOT NULL,
  PRIMARY KEY (`lic_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `license` WRITE;
/*!40000 ALTER TABLE `license` DISABLE KEYS */;

INSERT INTO `license` (`lic_code`, `lic_name`)
VALUES
	('L001','정보처리산업기사'),
	('L002','정보처리기사'),
	('L003','정보보안산업기사'),
	('L004','정보보안기사'),
	('L005','SQLD'),
	('L006','SQLP');

/*!40000 ALTER TABLE `license` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
