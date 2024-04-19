## MyBatis

> MyBatis 개요

- Java Obj와 SQL문 사이의 자동 Mapping 기능을 지원하는 ORM Framework.
- MyBatis는 SQL을 별도의 파일로 분리해서 관리.
- Object - SQL 사이의 parameter mapping 작업을 자동으로 해줌.
- MyBatis는 Hibernate나 JPA처럼 새로운 DB 프로그래밍 패러다임을 익혀야하는 부담이 없이, 개발자가 익숙한 SQL을 그대로 이용하면서 JDBC코드 작성의 불편함을 제거해주고, 도메인 객체나 VO 객체를 중심으로 개발 가능.

> MyBatis 특징

1. 쉬운 접근성과 코드의 간결함
    - 수동적인 parameter 설정과 Query 결과에 대한 mapping 구문을 제거.
    - JDBC의 모든 기능을 MyBtis가 대부분 제공.
2. SQL문과 프로그래밍 코드의 분리
    - SQL에 변경이 있을때마다 자바 코드를 수정하거나 컴파일하지 않아도 됨.
    - SQL 작성과 관리 또는 검토를 DBA와 같은 개발자가 아닌 다름 사람에게 맡길 수 있음.
3. 다양한 프로그래밍 언어로 구현 가능
    - JAVA, C#, NET, Ruby,…

> mybatis-config.xml (resources/com/ssafy/mybatis/config)

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/609d46e8-84fd-4b9e-9a41-bd47c7d63d8e/91375e26-0e9d-4ead-abb3-ce6a1a9b597f/Untitled.png)

- MyBatis Database info를 설정하는 파일.
- line 10 - Database에 대한 세부 정보를 가진 파일을 알려줌.
- line 13~15 - Dto 패키지 경로가 너무 길기 때문에 alias를 설정.
- line 18~30 - Database에 연결하는 부분, 반드시 필요하고 property에 들어가는 value들은 dbinfo.properties에 존재. 
** 이 부분이 있다면 root-context.xml에서의 database 연결이 필요없다.
- line 35~37 - 실행되어야 할 Query문을 모아둔 파일들을 설정

> Member(Class)Mapper.xml

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/609d46e8-84fd-4b9e-9a41-bd47c7d63d8e/a7752ff1-afbe-4539-b540-628e2b883644/Untitled.png)

- line 6~13 - Dto 객체에서의 속성과 DB 컬럼의 속성을 mapping 시켜주는 것
위 코드에서는 user_id의 DB 컬럼을 MemberDto의 userId로 mapping, DB에 나온 결과를 Dto에 입력하기 위한 것
- line 15~18, line 20~24, line 26~30 - 사용할 Query문, id에는 Query문을 쓸 Dao method의 이름, parameterType은 Dao method의 parameterType, resultType은 Query문을 실행하고 난 결과의 Type이다 (이때 Query문을 실행한 결과는 ResultSet, int가 있다). resultMap은 returnType이 Dto일 때, Query 문의 결과를 Dto에 싣기 위해 user를 사용한다.
