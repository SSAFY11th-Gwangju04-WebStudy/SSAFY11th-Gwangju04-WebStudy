# DML(Select)

> SELECT, FROM

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/609d46e8-84fd-4b9e-9a41-bd47c7d63d8e/7de4f3d5-e6da-491b-b992-85a0241aa6bd/Untitled.png)

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

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/609d46e8-84fd-4b9e-9a41-bd47c7d63d8e/90dac09f-cb1f-432e-aece-ee2c971f78d8/Untitled.png)

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

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/609d46e8-84fd-4b9e-9a41-bd47c7d63d8e/6a20bdce-8c18-4502-9bff-216aa4000ace/Untitled.png)

> ORDER BY

```sql
select employee_id, first_name, salary
from employees
order by salary desc;
```

- 일반적으로 sql문의 맨 마지막에 작성
- desc: 내림차순
