8월 23일 fitness 테이블 설계
CREATE TABLE dima.fitness
(
	seq		 int AUTO_INCREMENT PRIMARY KEY,
	name  varchar(50) NOT NULL,
	gender char(1) DEFAULT '1',
	height	decimal(5,2),
	weight	decimal(5,2),
	join_date datetime DEFAULT current_timestamp,
	std_weight decimal(5,2),
	bmi decimal(5,2),
	bmi_result varchar(100)
	
);