# DML(Select)

> SELECT, FROM

![Untitled](https://github.com/MJ-Kor/SSAFY11th-Gwangju04-WebStudy/blob/main/MJ-Kor/SSAFYLectures/DataBase/imgs/dml1.png)

- all: 모든 데이터
- distinct: 중복 데이터는 하나만 출력
- alias, 사칙연산, NULL Value
    - IFNULL(a, b) : a가 null일 경우 b, null이 아닐 경우 a
- case when exp1 then exp2
        when exp3 then exp4
        …
        else exp5

> NULL

- 프로그램에서는 X, 공란의 의미
- DB에서는 존재하긴하나 알 수 없는 것을 의미

> WHERE

![Untitled](https://github.com/MJ-Kor/SSAFY11th-Gwangju04-WebStudy/blob/main/MJ-Kor/SSAFYLectures/DataBase/imgs/dml2.png)

- and, or, not
    - 일반적인 조건식 연산자
- in, not in
    - ( )안의 값에 해당하는 속성을 가진 것
    - ( )안의 값에 해당하는 속성을 갖지 않는 것
- between a and b
    - a와 b 사이의 값에 해당하는 속성
- is null, is not null
    - null은 = 연산자로 찾는 것이 아닌 null, is not null로 찾는 것
- like [% / _]
    - where column like ‘% / _’ 형식으로 사용
    - %은 모든 문자를 의미, _는 한글자를 의미한다.
- NULL의 논리연산

![Untitled](https://github.com/MJ-Kor/SSAFY11th-Gwangju04-WebStudy/blob/main/MJ-Kor/SSAFYLectures/DataBase/imgs/dml3.png)

> ORDER BY

```sql
select employee_id, first_name, salary
from employees
order by salary desc;
```

- 일반적으로 sql문의 맨 마지막에 작성
- desc: 내림차순
