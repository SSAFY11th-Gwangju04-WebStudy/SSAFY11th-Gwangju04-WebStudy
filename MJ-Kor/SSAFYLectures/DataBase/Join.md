## JOIN

- 둘 이상의 테이블에서 데이터가 필요한 경우 테이블 조인이 필요
- 일반적으로 JOIN 조건을 포함하는 WHERE 절을 작성해야 한다 ($n-1$개의 JOIN 조건을 걸어야 함)
- JOIN 조건은 일반적으로 각 테이블의 PK 및 FK로 구성된다

> JOIN의 종류

- INNER JOIN
- OUTER JOIN
    - LEFT OUTER JOIN
    - RIGHT OUTER JOIN

> JOIN 조건의 명시에 따른 구분

- NATURAL JOIN
- CROSS JOIN(FULL JOIN, CARTESIAN JOIN)

> JOIN시 주의

- JOIN의 처리는 어느 테이블을 먼저 읽을지를 결정하는 것이 중요 (처리할 작업량이 상당히 달라진다.)
- INNER JOIN: 어느 테이블을 먼저 읽어도 결과가 달라지지 않아 MySQL 옵티마이저가 JOIN의 순서를 조절해서 다양한 방법으로 최적화를 수행할 수 있다.
- OUTER JOIN: 반드시 OUTER가 되는 테이블을 먼저 읽어야 하므로 옵티마이저가 JOIN 순서를 선택할 수 없다.

> JOIN의 필요성

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/609d46e8-84fd-4b9e-9a41-bd47c7d63d8e/c9228230-ddab-47d4-a260-436b34507b79/Untitled.png)

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/609d46e8-84fd-4b9e-9a41-bd47c7d63d8e/9875631f-b2a5-47b2-bf55-725cf31dbc57/Untitled.png)

> INNER JOIN

- 가장 일반적인 JOIN의 종류이며, 교집합이다.
- 동등 조인이라고도 하며, N개의 테이블 JOIN 시 N - 1개의 JOIN 조건이 필요하다.
    - 이 때 조건은 on 명령어로 지정을 해준다
    - join 조건문: on, 일반 조건문: where
- on 대신 using을 사용해서 조건을 지정할수도 있다. (alias 사용 x)
    - using 키워드 다음 (공통 col)이 오는 형식이다.
- 만약 조건이 없다면 cross join과 같은 결과가 나온다
- 형식
    
    ```sql
    select col1, col2, ... , colN
    from table1 inner join table2
    on table1.col1 = table2.col1;
    
    // alias 사용 가능
    select alias1.col1, alias2.col2, ... , alias.colN
    from table1 alias1 inner join table2 alias2
    on alias1.col1 = alias2.col1;
    
    // using 키워드 사용
    select alias1.col1, alias2.col2, ... , alias.colN
    from table1 alias1 inner join table2 alias2
    using (col1)
    ```
    

> NATURAL JOIN

- 두 테이블의 공통된 속성의 값을 자동으로 찾아 테이블을 합친다.
- 주의점: 공통된 속성을 모두 매칭하기 때문에 원하는 결과가 안나올수도 있다.
    - 테이블의 공통된 속성을 잘 확인하자
    
    ![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/609d46e8-84fd-4b9e-9a41-bd47c7d63d8e/4acca424-459f-4fc5-be50-6fbda89448cd/Untitled.png)
    
- 형식
    
    ```sql
    select col1, col2, ... , colN
    from table1 natural join table2
    where 조건;
    ```
    

> OUTER JOIN

- LEFT OUTER JOIN, RIGHT OUTER JOIN, FULL OUTER JOIN으로 구분 됨
- 어느 한쪽 테이블에는 해당하는 데이터가 존재하는데 다른 쪽 테이블에는 데이터가 존재하지 않을 경우 그 테이터가 검색되지 않는 문제점을 해결하기 위해 사용

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/609d46e8-84fd-4b9e-9a41-bd47c7d63d8e/7c840b58-392d-47fd-9d1c-1a7035411efb/Untitled.png)

> LEFT OUTER JOIN

- 왼쪽 테이블을 기준으로 JOIN 조건에 일치하지 않는 데이터까지 출력
- 형식
    
    ```sql
    select col1, col2, ... , colN
    from table1 left outer join table2
    on or using;
    ```
    

> RIGHT OUTER JOIN

- 오른쪽 테이블을 기준으로 JOIN 조건에 일치하지 않는 데이터까지 출력
- 형식
    
    ```sql
    select col1, col2, ... , colN
    from table1 right outer join table2
    on or using;
    ```
    

> FULL OUTER JOIN

- 양쪽 테이블을 기준으로 JOIN 조건에 일치하지 않는 데이터까지 출력
- 형식
    
    ```sql
    select col1, col2, ... , colN
    from table1 full outer join table2
    on or using;
    ```
    
- MySQL은 지원하지 않는다.

> SELF JOIN

- 같은 테이블끼리 JOIN한다
- ex) 모든 사원의 사번, 이름, 매니저 사번, 매니저 이름

> None-Equi JOIN

- table의 PK, FK가 아닌 일반 column을 join 조건으로 지정
- ex) 모든 사원의 사번, 이름, 급여, 급여등급