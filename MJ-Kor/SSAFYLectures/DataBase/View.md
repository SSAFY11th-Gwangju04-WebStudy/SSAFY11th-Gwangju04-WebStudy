## View

> View란?

- DB에 존재하는 가상의 테이블
- 실제 행과 열을 가지고 있지만 데이터를 저장하고 있지는 않음
- 테이블처럼 물리적으로 저장되는 것이 아님
- join과 subquery와 같이 여러 개의 테이블을 참조하여 데이터를 조회할 때 번거로움을 줄일 수 있다.
- 단, MySQL에서 View는 단지 다른 테이블이나 View에 있는 데이터를 보여주는 역할만 수행
- View와 Table의 차이점은, Table은 실질적인 데이터가 있지만 View는 데이터가 없고 SQL만 저장한다.

> 장점

- 특정 사용자에게 테이블 전체가 아닌 필요한 필드만 보여줄 수 있다.
- 복잡한 쿼리를 단순화해서 사용할 수 있다.
- 쿼리를 재사용할 수 있다.
- 여러 방법의 데이터 조회에 알맞은 다양한 구조의 데이터 분석 기반을 구축할 수 있다.
    - 기존 테이블의 구조를 변경하지 않는다.

> 단점

- 삽입, 삭제, 갱신 작업에 많은 제한 사항을 가짐
- View는 자신만의 인덱스를 가질 수 없다.

> View data 변경

- View를 생성한 기존 테이블의  data가 update되면 View의 내용도 update 된다.
- View를 조회하면, Optimizer에서 View를 생성할 때 저장해놓은 Select문이 실행되는 것이기 때문에 View의 data 또한 update가 된 것으로 보임

> View 종류

- 단순 뷰
    - 하나의 테이블로 생성
    - 그룹 함수의 사용이 불가능
    - distinct 사용 불가능
    - DML 사용 가능
- 복합 뷰
    - 여러개의 테이블로 생성(join)
    - 그룹 함수의 사용이 가능
    - distinct 사용이 가능
    - DML 사용 불가능
- 인라인 뷰
    - 일반적으로 가장 많이 사용
    - from 절 안에 SQL 문장이 들어가는 것을 인라인 뷰라 볼 수 있다.

> 단순 뷰

- 단순 뷰에서 DML 명령어 사용이 불가능한 경우
    - View 정의 시 포함되지 않은 컬럼 중 not null 제약조건이 지정되어 있는 경우
    - 산술 표현식을 포함한 컬럼이 포함되어 있는 경우
    - distinct를 포함한 경우
    - 그룹 함수나 group by 절을 포함한 경우

> View 생성

```sql
create view view_name
as
select field_name1, field_name2, ...
from table_nmae
where 조건;
```

- select 문에서 선택된 필드를 이용하여 새로운 View를 생성
- View는 원본 테이블의 이름과 같은 이름을 사용할 수 없다.

> View 대체

```sql
create or replace view view_name
as
select field_name1, field_name2, ...
from 테이블 이름
where 조건;
```

- View name에 해당하는 View가 존재하면 replace, 존재하지 않으면 create 가 일어남

> View 수정

```sql
alter view view_name
as
select field_name1, field_name2, ...
from 테이블 이름
```

> View 삭제

```sql
drop view view_name;
```
