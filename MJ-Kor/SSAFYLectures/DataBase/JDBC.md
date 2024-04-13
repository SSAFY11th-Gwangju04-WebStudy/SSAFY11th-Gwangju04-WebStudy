## JDBC

> JDBC

- java로 만들어진 클래스와 인터페이스로 이루어진 API.
- SQL문을 실행할 수 있는 함수 호출 인터페이스.

> JDBC 특징

- DBMS 종류에 독립적인 java 프로그래밍 기능.
- 데이터베이스가 달라지더라도 동일한 API를 사용하게 해준다.
- 자바가 가지는 플랫폼에 독립적이라는 특성과 DBMS에 독립적인 특성을 가진다.

> JDBC 기능

- 데이터베이스에 연결 설정 한다
- SQL 문장을 DBMS에 전송한다
- SQL 문장 전송 후 결과를 처리할 수 있게 해준다

> JDBC interface

- DB를 만드는 업체에게 제공되는 인터페이스
- 프로그래머에게 제공되는 인터페이스
    - SQL 패키지가 제공하고 있는 라이브러리로서 프로그래머는 이 라이브러리를 기반으로 DB 프로그램을 작성할 수 있다.

**java.sql: JDBC 1.0, javax.sql: JDBC 2.0

> JDBC 블록 다이어그램

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/609d46e8-84fd-4b9e-9a41-bd47c7d63d8e/1b9fd3c9-47c7-4906-80a4-dec37c850d65/Untitled.png)

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/609d46e8-84fd-4b9e-9a41-bd47c7d63d8e/ef68aafe-2f87-4845-93d3-97b3bc04328d/Untitled.png)

- JDBC가 없다면 직접 만들어야 함
- driver loading은 생성자에서 1번 시도

> JDBC API (interface들, 이유는 각 db마다 내용이 다르기 때문)

- Driver
    - 드라이버에 대한 정보를 가지고 있다.
    - 모든 드라이버가 반드시 구현해야 하는 인터페이스
    - 드라이버의 버전이나 연결에 대한 정보를 알아볼 수 있는 메소드를 가지고 있다.
- Connection - db 연결
    - 데이터베이스에 대한 하나의 세션을 표현한다.
        - 세션은 하나의 클라이언트가 서버에 요청을 하기 위해 연결을 맺은 상태를 의미
    - DriverManager 클래스의 getConnection() 메소드를 이용하여 얻어 올 수 있다.
    - default로 setAutoCommit(true)로 설정된다.
    - 개발자가 원하는 경우에 commit을 해주고 싶거나 트랜잭션이 아주 중요한 부분에 있어서 Rollback처리를 하고자 할 경우에는 setAutoCommit(false)로 설정한다.
        - 단 이 경우에는 SQL 문을 수행할 후 명시적으로 commit()을 호출해야 한다.
- Statement - sql 문장 실행
    - SQL문장을 실행하고 그것에 대한 결과 값을 가져오기 위해 사용한다.
    - public boolean execute(String sql) throws SQLException
        - 특별히 SQL문을 구분하지 않고 DML(delete, update, insert), Query(select), DDL(create, drop) 등을 수행할 수 있다. 결과가 DML이거나 특별한 결과가 없으면 false를 리턴한다.
    - public ResultSet executeQuery(String sql) throws SQLException
        - Select를 처리할 때 사용된다.
    - public int executeUpdate(String sql) throws SQLException
        - 주로 DML(delete, update, insert) 등의 SQL을 수행할 때 사용한다.
- PreparedStatement
    - 동일한 SQL 문장이 여러 번 반복적으로 수행될 때 사용하는 객체
    - 대용량의 문자나 바이너리 타입의 데이터를 저장하기 위해서도 사용될 수 있다.
    - SQL 문장이 미리 컴파일 되어 PreparedStatement 객체에 저장된다.
    - 여러 번 반복 수행 시 clearParameters() 메소드를 이용해 Statement에 남겨진 값을 초기화 한다.
    - public ResultSet executeQuery() throws SQLException
        - Select를 처리할 때 사용된다.
    - public int executeUpdate() throws SQLException
        - 주로 DML(delete, update, insert) 등의 SQL을 수행할 때 사용한다.
- CallableStatement
    - 데이터베이스에 대하여 실제 SQL문을 실행하는 것이 아니라 Stored Procedures를 호출한다.
    - Stored Procedures란 연속되는 SQL문으로 데이터베이스에 저장해두고 마치 함수의 호출처럼 사용한다.
    - 데이터베이스에 Stored Procedures를 만들어 두고 자바에서 호출하여 사용할 수 있게 한다.
    - Stored Procedures 사용 시 속도의 향상을 기대할 수 있고, 자바 코드에 SQL문장이 들어가지 않으므로 자바 코드가 SQL에 독립적이게 된다.
- ResultSet
    - 쿼리에 대한 결과값 처리
    - ResultSet 객체의 커서는 첫번째 레코드보다 바로 이전을 가리킨다.
    - next()
        - ResultSet 객체의 커서를 이동
    - getXXX(index or name) 메소드를 이용하여 데이터를 얻을 수 있다.
        - getString(index or name)
        - getInt(index or name)
        - getDate(index or name)
        - …

> JDBC 개발 순서

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/609d46e8-84fd-4b9e-9a41-bd47c7d63d8e/24eb8b7a-8028-4196-ae72-e44352e149b6/Untitled.png)

> JDBC Driver Loading
> 
- 데이터베이스와의 접속을 오픈하기 위해 애플리케이션의 JVM안으로 특정 드라이버 클래스를 적재
- Class.forName(”Driver ClassName”);
- 각 DB별 Driver Class
    - MySQL: com.mysql.cj.jdbc.Driver
    - Oracle: oracle.jdbc.driver.OracleDriver
    - MSSQL: com.microsoft.sqlserver.jdbc.SQLServerDriver

> DBMS와 연결(Connection 생성)
> 
- DriverManager 클래스를 이용하여 URL 형태로 주어진 데이터베이스에 대한 접속을 요청
- Connection conn = DriverManager.getConnection(URL.dbid,dbpassword);
- 각 DB별 URL
    
    ![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/609d46e8-84fd-4b9e-9a41-bd47c7d63d8e/041461a2-abd2-49aa-a043-93efcea2d3c1/Untitled.png)
    

> SQL 실행 준비(Statement, PreparedStatement 생성)
> 
- String sql = “insert, update, delete, select, …”;
- Statement 생성
    - Statement stmt = conn.createStatement();
- PreparedStatement 생성
    - PreparedStatement pstmt = conn.preparedStatement(sql);

> SQL 실행
> 
- Statement
    - DML
        
        ```java
        int cnt = stmt.executeUpdate(sql);
        ```
        
    - Select
        
        ```java
        ResultSet rs = stmt.executeQuery(sql);
        ```
        
- PreparedStatement
    - sql문의 치환변수(?) 값 설정
        
        ```java
        pstmt.setXXX(index, value);
        ```
        
    - DML
        
        ```java
        int cnt = pstmt.executeUpdate();
        ```
        
    - Select
        
        ```java
        ResultSet rs = pstmt.executeQuery();
        ```
        
- ResultSet
    - ResultSet을 얻어온 후 rs.next()를 실행해야 한다.
        - select의 결과가 반드시 하나가 나오는 경우: rs.next()
        - select의 결과가 하나 또는 못 얻어오는 경우: if(rs.next) {}
        - select의 결과가 여러 개가 나올 수 있는 경우: while(rs.next()) {}
    - 값 얻기
        
        ```java
        String str = rs.getString(index or name);
        int cnt = rs.getInt(index or name);
        ```
        

> DBMS 연결 끊기
> 
- 모든 작업이 끝난 후, ResultSet, Statement(PreparedStatement), Connection 객체의 close()를 사용하여 작업을 종료한다. - 연결한 순의 역순으로 종료
- Connection은 상당한 Overhead를 가져온다. 따라서 최적화된 상태를 유지하기 위해서는 반드시 Connection을 닫아주어야 한다.
