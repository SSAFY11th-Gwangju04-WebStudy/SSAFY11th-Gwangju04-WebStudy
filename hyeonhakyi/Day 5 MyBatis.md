# Day 5

- MyBatis
    - MyBatis는 Java Object와 SQL문 사이의 자동 Mapping 기능을 지원하는 ORM Framework
        - MyBatis는 SQL을 별도의 파일로 분리해서 관리
        - Object - SQL 사이의 parameter mapping 작업을 자동으로 해 줌
        - MyBatis는 Hibernate나 JPA처럼 새로운 DB 프로그래밍 패러다임을 익혀야 하는 부담이 없이, 개발자가 익숙한 SQL을 그대로 이용하면서 JDBC코드 작성의 불편함을 제거해 주고, 도메인 객체나 VO 객체를 중심으로 개발이 가능.
    - 특징
        - 쉬운 접근성과 코드의 간결함
            - 가장 간단한 persistence framework
            - XML형태로 서술된 JDBC 코드라 생각해도 될 만큼 JDBC의 모든 기능을 MyBatis가 대부분 제공.
            - 복잡한 JDBC 코드를 걷어내며 깔끔한 소스코드를 유지
            - 수동적인 parameter 설정과 Query 결과에 대한 mapping 구문을 제거
        - SQL문과 프로그래밍 코드의 분리.
            - SQL에 변경이 있을 때마다 자바 코드를 수정하거나 컴파일 하지 않아도 됨.
            - SQL 작성과 관리 또는 검토를 DBA와 같은 개발자가 아닌 다른 사람에게 맡길 수 있음
        - 다양한 프로그래밍 언어로 구현가능
- MyBatis - Spring
    - MyBatis와 MyBatis-Spring의 주요 Component
        - MyBatis와 MyBatis-Spring을 사용한 DB Access Architecture
            
       
            
        - MyBatis를 사용하는 DAta Access Layer
            
        - MyBatis 3의 주요 Component
            
        - MyBatis 3의 주요 Complnent의 역할
            
        - MyBatis-Spring의 주요 Component
            
        - MyBatis-Spring의 주요 Component의 역할
            
- Mapper Interface
    - Mapper Interface는 mapping 파일에 기재된 SQL을 호출하기 위한 Interface
        - Mapper Interface는 SQL을 호출하는 프로그램을 Type Safe하게 기술하기 위해 MyBatis 3.x부터 등장.
        - Mapping 파일에 있는 SQL을 java interface를 통해 호출할 수 있도록 해 줌.
    - Mapper Interface를 사용하지 않았을 경우
        - Mapper Interface를 사용하지 않으면
            - SQL을 호출하는 프로그램은 SqlSession의 method의 argument에 문자열로 namespace + “.” + SQL ID로 지정
            - 문자열로 지정하기때문에 오타에 의한 버그가 생기거나, IDE에서 제공하는 code assist를 사용할 수 없음.
    - Mapper Interface를 사용했을 경우
        
        - UserMapper Interface는 개발자가 작성.
        - packagename + “.” + InterfaceName + “.” + methodName이 namespace + “.” + SQL ID가 되도록 Namespace와 SQL ID를 설정해야 함.
        - Namespace 속성에는 package를 포함한 Mapper Interface의 이름을 작성.
        - SQL ID에는 mapping하는 method의 이름을 지정
- MyBatis - Spring 연동
    - 개요
        - MyBatis를 Standalone 형태로 사용하는 경우, SqlSessionFactory 객체를 직접 사용.
        - 스프링을 사용하는 경우, 스프링 컨테니어네 MyBatis 관련 빈을 등록하여 MyBatis를 사용.
        - 또한 스프링에서 제공하는 트랜잭션 기능을 사용하면 손쉽게 트랜잭션 처리
        - MyBatis를 스프링과 연동하기 위해서는 MyBatis에서 제공하는 Spring 연동 라이브러리가 필요.
            
    - DataSource 설정
        - 스프링을 사용하는 경우, 스프링에서 데이터 소스를 관리하므로 MyBatis 설정파일에서는 일부 설정을 생략
        - 스프링 환경 설정 파일(application-context.xml)에 데이터소스를 설정.
        - 데이터 소스는 dataSource 아이디를 가진 빈으로 데이터베이스 연결정보를 가진 객체
        - MyBatis와 스프링을 연동하면 데이터베이스 설정과 트랜잭션 처리는 스프링에서 관리
            
    - 트랜잭션과 관리자 설정
        - transactionManager 아이디를 가진 빈은 트랜잭션을 관리하는 객체
        - MyBatis는 JDBC를 그대로 사용하기 때문에 DataSourceTransactionManager 타입의 빈을 사용
        - tx:annotation-driven 요소는 틓랜잭션 관리 방법을 어노테이션으로 선언하도록 설정.
        - 스프링은 메소드나 클래스에 @Transactional이 선언되어 있으며, AOP를 통해 트랜잭션을 처리
            
    - SqlSessionFactoryBean 설정
        - MyBatis애플리케이션은 SqlSessionFactory를 중심으로 수행
        - 스프링에서 SqlSessionFatory객체를 생성하기 위해서는 SqlSessionFactoryBean을 빈으로 등록해야 함.
        - SqlSessionFactoryBean을 빈으로 등록할 때, 사용할 데이터 소스와 mybatis설정 파일 정보가 필요
            
    - mapper 빈 등록
        - Mapper 인터페이스를 사용하기 위해서 스캐너를 사용하여 자동으로 등록하거나, 직접 빈으로 등록
        - mapperScannerConfigurer을 설정하면, Mapper 인터페이스를 자동으로 검색하여 빈으로 등록
            - basePackage로 패키지를 설정하면, 해당 패키지 하위의 모든 매퍼 인터페이스가 자동으로 등록
        - MapperFactoryBean 클래스는 매퍼 인터페이스를 직접 등록할 때 사용
            
    - Service Class
        
    - Mapper Interface