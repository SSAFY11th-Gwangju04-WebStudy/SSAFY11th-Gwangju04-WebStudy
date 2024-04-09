---
date_daily: 2024-04-09
achievement: 
emotion: 
important_date: false
tags:
  - join
  - subquery
---
# JOIN

## JOIN 이란
- 둘 이상의 테이블에서 데이터가 필요한 경우 테이블 조인  이 필요
- 일반적으로 조인 조건을 포함하는 WHERE 절을 작성해야한다.
- 조인 조건을 일반적으로 각 테이블의 PK 및 FK 로 구성된다.

## JOIN의 종류
- INNER JOIN
- OUTER JOIN
	- LEFT OUTER JOIN
	- RIGHT OUTER JOIN

## JOIN 조건의 명시 따른 구분
- NATURAL JOIN
- CROSS JOIN(FULL JOIN, CARTESIAN JOIN) -- 일반적으로 만들일 이 없다.

## JOIN시 주의
- 조인의 처리는 어느 테이블을 먼저 읽을지를 결정하는 것이 중요(처리 처리할 작업량이 상당히 달라딘다.)
- INNER JOIN : 어느 테이블을 먼저 읽어도 결과가 달라지지 않아 MySQL 옵티마이저가 조인의 순서를 조절해서 다양한 방법으로 최적화를 수행합니다.
- OUTER JOIN : 반드시 OUTER가 되는 테이블을 먼저 읽어야 하므로 옵티마이저가 조인 순서를 선택할 수 없습니다.

## JOIN의 필요성
- 사번이 100인 사원의 사번, 이름, 급여 부서 이름을 출력
```SQL
select employee_id, first_name, salary, department_name
from employees
where employee_id = 100;
```

위 코드는 문제가 있다. 왜냐하면 부서 이름은 employees 테이블에 없고 department 테이블에 있기 떄문이다.
```SQL
select employee_id, first_name, salary, department_name
from employees
join departments
where employee_id = 100 and 
employees.department_id = departments.department_id;
```

위 코드와 같이 JOIN으로 묶어서 SQL을 작성해야한다. JOIN으로 묶은 후 where 절에서 동일 한 부서 ID를 찾아준다. where 절 쓸 때 확실히 스키마 이름도 명시해준다.
# INENER JOIN

- 가장 일반적인 JOIN의 종류이며 교집합니다.
- 동등 조인(Equi-join) 이라고도 하며, N개의 테이블 조인 시 N-1개의 조인 조건이 필요함
```SQL
-- inner join
select e.employee_id, e.first_name, e.salary, d.department_name
from employees e
inner join departments d
on e.department_id = d.department_id
where employee_id = 100;
```
join 다음에 on 절에조건을 붙인다. (inner 생략 가능)

테이블 3개 JOIN
```SQL
select e.employee_id, e.first_name, e.salary, d.department_name , l.street_address
from employees e
join departments d 
join locations l
on e.department_id = d.department_id
and l.location_id = d.location_id
where e.department_id is not null and
l.city = 'Seattle';
```
보통 3개 이상이 되면 on절을 join뒤에 바로바로 붙여준다. join on join on.

### using 을 이용한 join 조건 지정

```SQL
select e.employee_id, e.first_name, e.salary, d.department_name , l.street_address
from employees e
join departments d
using (department_id)
join locations l
on l.location_id = d.location_id
where l.city = 'Seattle';
```
on절 대신에 쓴다. 같은 컬럼 하나로 묶는다..!


## NATURAL JOIN
- 말 그래도 기본적인 동일한 컬럼을 가지고 있는 컬럼으로 조인 하겠다.
```SQL
select e.employee_id, e.first_name, e.salary, d.department_name , l.street_address
from employees e
natural join departments d
natural join locations l
where l.city = 'Seattle';
```
같은 컬럼을 모두 찾고 엮어 버린다. 교집합의 최대 느낌.

# OUTER JOIN

- LEFT OUTER JOIN, RIGHT OUTER, FULL OUTER JOIN으로 구분됨
- 어느 한쪽 테이블에는 해당 데이터가 존재하는데 다른 쪽 테이블에는 데이터가 존재하지 않을 경우 그 데이터가 검색되지 않는 문제점을 해결하기 위해 사용.

## LEFT OUTER JOIN
- 왼쪽 테이블을 기준으로 JOIN 조건에 일치 하지 않는 데이터까지 출력

```sql
select e.employee_id, e.first_name, d.department_name
from employees e left join departments d
on e.department_id = d.department_id;
```


# SELF, Non-Equi JOIN

# SUBQUERY

- 서브 쿼리란 다른 쿼리 내부에 포함되어 있는 select 문을 의미한다.
- 서브 쿼리를 포함하고 있는 쿼리를 외부 쿼리 또는 메인 쿼리라고 부르며, 서브 쿼리는 내부 쿼리라고도 부른다.
- 서브쿼리는 비교 연산자의 오른쪽에 기술해야 하고 반드시 괄호로 감싸져 있어야만한다.

### 서브쿼리의 종류
- 중첩 서브 쿼리 where 문에 작성하는 서브 쿼리
	- 단일 행
	- 복수 행
	- 다중 컬럼
- 인라인 뷰 - FROM 문에 작성하는 서브 쿼리
- 스칼라 서브 쿼리 - select 문에 작성하는 서브 쿼리

### 주의 사항
- 서브쿼리는 반드시 `()` 로 감싸야 한다.
- 서브쿼리는 단일 행 또는 다중 행 비교 연산자와 함께 사용된다.

### 서브 쿼리가 사용이 가능한 곳
- select
- from
- where
- having
- order by
- insert문의 values
- update문의 set

```sql
select * from locations where binary(city) = 'Seattle';
```

바이너리를 알고있으면 로그인 할 떄 유용하다.
# NESTED SUBQUERY

in any all



# INLINE VIEW

# SCALAR SUBQUERY

# SUBQUERY 활용

