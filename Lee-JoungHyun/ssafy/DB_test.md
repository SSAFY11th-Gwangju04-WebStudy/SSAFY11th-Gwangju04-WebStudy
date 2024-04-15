# DB 과목평가 준비!


# 예상 문제

### 1. SQL 쿼리문 결과 예측 (allias 연산자 사용 포함)

### 2. 테이블 제약사항

- not null
- deafault value
- unique
- auto increment
- primary key
- foreign key
- check : 값의 범위나 종류 지

### 3. SQL 주요 내장함수 (주로 날짜, 문자 관련 + 집계함수)

**1) 함수**

- 단일 행 함수(Single Row Function): 문자형, 숫자형, 날짜형, 변환형
- 다중 행 함수(Multi Row Function): 그룹 함수
- 여러 함수를 겹쳐서 사용 가능

**2) 문자형 함수**

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/60d66ae7-5f64-44bc-b4b0-f484626a8090/2634cfd3-e73f-482c-a7db-6e12c71891cb/Untitled.png)

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/60d66ae7-5f64-44bc-b4b0-f484626a8090/b45658f9-9c05-4f38-8f2b-f024a3ec510a/Untitled.png)

**3) 숫자형 함수**

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/60d66ae7-5f64-44bc-b4b0-f484626a8090/58291186-cf4d-4ea7-9e98-ebb204112acc/Untitled.png)

**4) 날짜형 함수**

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/60d66ae7-5f64-44bc-b4b0-f484626a8090/d5eaa891-a093-4867-a520-14fe67c0624f/Untitled.png)

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/60d66ae7-5f64-44bc-b4b0-f484626a8090/ebde48e7-1cef-4c90-b635-3dabd85c0997/Untitled.png)

**5) 논리 함수**

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/60d66ae7-5f64-44bc-b4b0-f484626a8090/e96a3e1d-f57d-46a0-a816-747e69b7dca4/Untitled.png)

- **6) 그룹함수 (집계 함수)**

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/60d66ae7-5f64-44bc-b4b0-f484626a8090/c17455b0-fb74-45c2-b235-41d61581e0dd/Untitled.png)

### 4. Group by + Having 절 (결과예측, 빈칸 채우기)

### 5. 조인 (Join을 사용한 결과 예측)

### 6. 모델링 (모델링 단계 구분)

### 7. DML, TCL, DDL → (select문 외 명령어 구분, 사용법)

### 8. 인덱스 뷰 (기본 사용법 )

### 9. JDBC 특징, 주요 인터페이스 (class, method 포함)

- JDBC : 자바 프로그래밍 언어로 만들어진 class, interface API, 함수 호출 인터페이스
- 특징
    - DBMS 종류에 독립적인 자바 프로그래밍 가능
    - 데이터베이스가 달라지더라도 동일 API 사용 가능 (드라이버, URL만 수정)
    - 자바가 가지는 플랫폼 독립성 + DBMS 독립성 을 가짐!

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/60d66ae7-5f64-44bc-b4b0-f484626a8090/a022494e-1d4a-4c7c-9f9d-8342eaea8d9e/Untitled.png)

인터페이스, 클래스 설명

- Driver (interface) - java.sql.package
    - 드라이버에 대한 정보 가짐
    - 모든 드라이버가 반드시 구현해야 하는 인터페이스
    - 드라이버 버전, 연결 정보 알 수 있다
- Connection (interface) - java.sql.package
    - 데이터베이스에 대한 하나의 세션 표현
    - `DrivetManager`(class)의  `.getConnection()` 메서드로 얻어옴
- Statement (Interface) - java.sql.package
    - SQL문을 실행하고 결과를 가져오기 위해 사용됨
    - DML, DDL, 등 구분 X, 결과가 ResultSet 이면 true, 특별 결과가 없으면 false 리턴

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/60d66ae7-5f64-44bc-b4b0-f484626a8090/d2c294fa-a2e3-4894-ab56-fafa6d83b623/Untitled.png)

- PreparedStatement (interface) - java.sql.package
    - 동일한 SQL문이 여러번 반복될때 수행되는 객체
    - 다용량 문자, 바이너리 데이터 등 저장을 위해서도 사용
    - 주로 DML 수행시 사용
- CallableStatement (interface) - java.sql.package
    - 실제 sql문 실행이 아니라 Stored Procedures 를 호출함
- ResultSet (interface) - java.sql.package
    - 쿼리에 대한 결과값 처리, 첫 레코드 바로 이전 값을 가리킴
    - `.next()` : ResultSet  객체의 커서 이동
    - `.getXxx(index or “name”)` : 데이터 얻어오기

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/60d66ae7-5f64-44bc-b4b0-f484626a8090/33ee452f-2acb-4917-b2c7-b8a23c6c9ace/Untitled.png)

### 10. 쿼리문 작성, 빈칸 채우기 등

## 기본 쿼리문

- DML
    - INSERT INTO 테이블명 (필드명1, 필드명2) VALUES(”값1”, “값2”)
    - UPDATE 테이블명 SET 필드명 = “값” WHERE 조건
    - DELETE FROM 테이블명 WHERE 조건
    - SELECT ~ from where group by having order by
- DDL
    - CREATE
    - ALTER
    - DROP
    - RENAME (?)
- DCL
    - GRANT
    - REVOKE
- TCL
    - GRANT
    - REVOKE
    

## DDL

: (Data Definition Language) 데이터 정의어, 테이터 베이스 객체의 구조 정의, 생성, 추가 등

- create: 데이터베이스 객체 생성
- drop: 데이터베이스 객체 삭제
- alter: 기존 데이터베이스 객체 수정

### 데이터베이스 생성

```sql
create database 데이터베이스명;
create database dbtest
	default character set utf8mb3
	collate utf8mb3_general_ci;
```

### 데이터베이스 변경

```sql
alter database dbtest
	default character set utf8mb3
	collate utf8mb3_general_ci;
```

### 데이터베이스 삭제, 사용

```sql
drop database dbtest;
// 사용
use dbtest;
```

### Table 생성

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/60d66ae7-5f64-44bc-b4b0-f484626a8090/3ee30303-b827-4538-b22e-5d4ae86b9ad2/Untitled.png)

- Data Type
    - Char[(M)] : 고정 길이 갖는 문자열
    - VARCHAR[(M)] : 가변 길이 갖는 문자열
    - ENUM(”value1”, “value2”) : 정해진 몇가지만 저장 (열거형)
    - INT [(M)] : 인트형 (~21억)
    - DATETIME : YYYY-MM-DD HH:MM:SS
    - TIMESTAMP[(M)] : 1970-01-01 ~ 2038-01-19 03:14:07 까지 지원, Index가 더 빠르게 생성됨
- 제약조건 `constraint 조건명 조건 (컬럼명)`
    - not null
    - deafault value
    - unique
    - auto increment
    - primary key
    - foreign key
    - check : 값의 범위나 종류 지

### 테이블 변경

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/60d66ae7-5f64-44bc-b4b0-f484626a8090/e273e2ab-ecd9-487a-9401-08dddd2f4428/Untitled.png)

## INDEX

데이터 베이스에 동작속도를 높여주는 자료구조 (색인)

컬럼 값과 주소를 key-value 로 저장 (DB크기의 10% 정도 추가 사용됨)

처음 생성시 많은 시간 소요, select는 성능 좋아지지만 insert update delete 자주 발생시 성능저하

- 인덱스 생성 전략
    - where 절에 자주 사용되는 컬럼
    - 데이터 중복도 낮은 곳
    - 조인에 자주 사용되는 곳
    - 사용하지 않는 인덱스 제거하기!

### 인덱스 종류

- Clustered Index
    - 데이터들을 일정 기준으로 정렬해줌, 테이블당 하나!
    - 보조 인덱스보다 검색은 빠르고 입력,수정,삭제는 느림
    - MySQL의 경우 PK > unique, not null > 임의 순으로 만들어 저장함
    - unique 시 not null 필수!!!
- 보조 인덱스 (Secondary or Non-Clustered indexs)
    - 개념적으로 후보키에만 부여 가능
    - 여러개 생성 가능
    - 별도 페이지에 인덱스 구성
    - Unique Key 로 지정된 경우 모두 Secondary index 가 됨

## Index 생성

```sql
create [unique] index 인덱스명 on 테이블명 (컬럼명) [asc | desc]
```

unique 생략 시 보조 인덱스 생성

unique 시 고유한 인덱스 만듬

클러스터형 인덱스는 만들 수 없고 alter table 을 사용해야함 

컬럼은 조합 가능

```sql
// index 설정 확
show index from dbtest; 
// 단순 보조 인덱스 생성
create index area_idx on dbtest (area);
// 고유 인덱스 생성 unique 컬럼 이어야 
create unique index addr_idx on dbtest (addr);
```

- 삭제

```sql
drop index 인덱스명 on 테이블명;
// 클러스터형 인덱스 삭제
alter table 테이블명 drop primary key;
```

## VIEW

: 데이터베이스에 존재하지 않는 가상의 테이블 (물리적 X), 번거로움 줄일 수 있음

`table 변경시 view 자동 업데이트`

- 장점
    1. 특정 사용자에게 특정 필드만 보여줌 (보안성)
    2. 복잡한 쿼리 단순화
    3. 쿼리의 재사용
- 단점
    1. 삽입, 삭제, 갱신 작업에 제한사항 가짐
    2. view 만의 인덱스 생성 불가

### view 생성, 수정, 삭제

- 생성

```sql
create [or replace] view 뷰이름
as 
select ~ from ~ where ~ ;
```

- 수정

```sql
alter view 뷰이름
as
select ~ from ~ where ~ ;
```

- 삭제

```sql
drop view 뷰이름
```

---

## JOIN

= 둘 이상의 테이블에서 데이터가 필요한 경우 사용, where 절에 사용, 조인 조건은 PK, FK로 구성

- Join 종류
    - inner join
    - outer join
        - left, right
- 조건의 명시에 따른 분류
    - natural join
    - cross join (full join, cartesian join)
- 주의사항
    - 조인의 경우 어느 테이블을 먼저 읽는것을 결정하는 것이 중요 (outer조인땐 자동 X)

## Inner Join

```sql
// 기본
from table1 t1, table2 t2 
where t1.column = t2.column;
// on 사용
from table1 t1 inner join table2 t2 on t1.column = t2.column;
// using 사용
from table1 t1 inner join table2 t2 using (column);
// natural join
from table1 natural join table2
```

  

## Outer Join

```sql
// left
from table1 t1 left outer join table2 on ~ ;
// right
from table1 t1 left outer join table2 using (~);
// full --> MySql은 지원 X
from table1 t1 left full join table2 on ~ ;
```

### SELF Join

같은 테이블 끼리 join

## Subquery

다른 쿼리 내부에 포함되어 있는 select 문을 의미

- 서브쿼리 종류
    - 중첩 서브 쿼리(Nested Subquery) where 문에 작성
        - 단일 행
        - 다중 행
        - 다중 컬럼
    - 인라인 뷰 - from 문에 작성
    - 스칼라 서브쿼리 - select 문에 작성
- 주의 사항
    - 서브 쿼리는 반드시 () 로 감싸야 함
    - 단일행 또는 다중행 비교 연산자와 함께 사용

### Nested Subquery

- 단일 행

= 서브 쿼리 결과가 단일행 리턴 

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/60d66ae7-5f64-44bc-b4b0-f484626a8090/a70bb6d0-d7b0-4e28-bf63-4d48225b81f8/Untitled.png)

- 다중 행

= 서브 쿼리 결과가 다중행 리턴 : In, Any(최소 급여자 보다), All (최대 급여자 보다)

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/60d66ae7-5f64-44bc-b4b0-f484626a8090/12461324-1988-46d3-b483-42d22e50aeee/Untitled.png)

- 다중 열

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/60d66ae7-5f64-44bc-b4b0-f484626a8090/68c31e66-ba52-4b29-a08e-9e4b1f9f1417/Untitled.png)

### 인라인 뷰

= from 절에 사용되는 쿼리, 임시적이기 때문에 저장 X, alias 사용 필수적

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/60d66ae7-5f64-44bc-b4b0-f484626a8090/1d7d23cd-d2d0-44bf-b666-477884432407/Untitled.png)

### 스칼라 서브쿼리

= select 절에만 사용, 한개의 행만 반환

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/60d66ae7-5f64-44bc-b4b0-f484626a8090/96f62710-ee4b-4197-88f8-de8778aa38d3/Untitled.png)

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/60d66ae7-5f64-44bc-b4b0-f484626a8090/aa5d482f-85c7-4c3b-80b3-60bbf6f3dfe0/Untitled.png)

create, insert, update, delet 에 활용 가능

(구조만 복사시 where 1 = 0; 으로 컬럼 선택 X)

---

## 데이터베이스 모델링

: 정보 시스템 구축을 위해 어떤 데이터 존재해야 하는지. 업무에 필요한 정보는 무엇인지 분석하는 법

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/60d66ae7-5f64-44bc-b4b0-f484626a8090/b155b870-4298-4a2b-a5a8-c117de2d05ed/Untitled.png)

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/60d66ae7-5f64-44bc-b4b0-f484626a8090/2e6a38f3-964c-4466-9a78-34c8e49eb1fe/Untitled.png)

### 개념적 모델링

: 업무 분석 결과로 Entity 추출, Entity의 속성, Entity간의 관계 정의해 ER-Diagram 작

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/60d66ae7-5f64-44bc-b4b0-f484626a8090/6ac87630-1e79-45b4-b461-89c30656fc84/Untitled.png)

- 관계 부여 방법
    1. 관계가 있는 두 Entity 실선으로 연결하고 사이에 관계 부여
    2. 관계 차수 표현
    3. 선택성 표시
    

### 논리적 데이터베이스 모델링

: ER-Diagram 을 Mapping rule 을 적용하여 스키마 설계, 정규화 하는 과정 

한마디로 테이블 생

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/60d66ae7-5f64-44bc-b4b0-f484626a8090/2a6bdd75-2c3a-4436-b6ad-21a05ef29a6e/Untitled.png)

### 물리적 데이터베이스 모델링

: DBMS 특성에 맞게 실제 데이터베이스 내의 개체들을 정의하는 단계

인덱스 정의, trigger설정, 역정규
