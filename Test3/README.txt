프로젝트 주제
Fitness 관리

1. Controller 
MainController.java
FitnessContorller.java

2. DTO
FitnessDTO
	seq, [name, height, weight, gender(체크 0,1), joinDate] stdWeight, bmi, bmiResult
	
3. Entity
FitnessEntity
	seq, [name, height, weight, gender(체크 0,1), joinDate(변경불가)] stdWeight, bmi, bmiResult

4. Service
FitnessService 
	insertMember( )
	selectMember( )
	deleteMember ( )
	updateMember( )
	selectAll( )
5. Repository
<< FitnessRepository.hava >> - interface

6. Fontend 
index.html
insertMember.html
listMember.html
updateMember.html

7. db설계
use dima;

