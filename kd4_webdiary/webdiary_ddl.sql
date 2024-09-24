-- 2024년 9월 12일 DIMA 4기 Web Diary 테이블

USE dima;

-- 아래 코드를 복사하시오
DROP TABLE dima.diary ;
CREATE TABLE dima.diary
(
    diary_seq int AUTO_INCREMENT PRIMARY KEY
    , username varchar(100) NOT NULL
    , feeling varchar(200) DEFAULT 'smile.png'
    , title varchar(200) NOT NULL
    , records varchar(3000) NOT NULL
    , regdate DATETIME DEFAULT current_timestamp
);