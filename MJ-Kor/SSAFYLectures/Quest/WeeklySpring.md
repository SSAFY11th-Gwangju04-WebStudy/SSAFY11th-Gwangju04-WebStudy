1. rest
    1. rest 서비스란?
        - client가 Rest 아키텍처 방식으로 Server에게 요청을 보내고, Server는 요청을 처리하여 Client에게 JSON/XML 형식의 data만 전달하는 형식
    2. http method와 관련 annotation
        - @GetMapping
            - Get 방식의 메소드 처리
            - @RequestParam이나 @ModelAttribute를 통해 파라미터 받음
        - @PostMapping
            - @RequestBody나 @ModelAttribute를 통해 전달 받은 요청 파라미터를 처리
        - @PutMapping, @PatchMapping
            - PostMapping 처럼 동작
            - Put은 부분수정, Patch는 전체수정
        - @DeleteMapping
    3. 그 외 rest 관련 annotation
        - @RestController
            - Controller가 Rest 방식을 처리하기 위한 것임을 명시
        - @ResponseBody
            - JSP 같은 뷰로 전달되는 것이 아니라 데이터 자체를 전달
        - @PathVariable
            - URL 경로에 있는 값을 파라미터로 추출(단 1개만)
        - @CrossOrigin
            - Ajax의 cross domain 문제를 해결
        - @RequestBody
            - JSON 데이터를 원하는 타입으로 바인딩
        - @RequestParam
            - 요청 파라미터를 받는 방식 중 하나
            - primitive 타입만 받음
            - 파라미터 변수와 메소드의 파라미터 변수와 같으면 생략이 가능함
2. spring
    1. spring mvc 요청 처리 과정
        1. WAS가 실행되면 web.xml이 로딩
        2. web.xml의 ContextLoaderListener가 생성, ServletContextListener interface를 구현, ApplicationContext를 생성하는 역할
        3. ContextLoaderListener는 root-context.xml을 로딩
        4. root-context.xml에 등록되어 있는 Spring Container 구동, Service와 Dao 객체들이 생성
        5. DispatcherServlet(Servlet)이 생성. FrontController의 역할을 수행, Client로부터 요청 온 메시지를 분석하여 알맞은 PageController에게 전달하고 응답을 받아 요청에 따른 응답을 어떻게 할 지 결정, 실질적인 작업은 PageController에서 이루어짐 이러한 class 들을 HandlerMapping, ViewResolver class 라고 함
        6. DispatcherServlet은 servlet-context.xml을 로딩
        7. client로부터 요청이 들어옴
        8. 두번째 Spring Container가 구동되며 응답에 맞는 PageController 동작. 이 때 첫번째 Spring Container가 구동되면서 생성된 Dao, Service 클래스들과 협업하여 알맞은 작업을 처리
    2. spring 구조 및 개념
        - 구조
            1. POJO
                - 객체지향 원리에 충실한 자바 객체
            2. PSA
                - 환경과 세부기술의 변경과 관계없이 일관된 방식으로 기술에 접근할 수 있게 해주는 설게원칙
                - 기술적 복잡함은 추상화를 통해 인터페이스 분리
            3. IOC/DI
                - DI는 유연하게 확장 가능한 객체를 만들어 두고 객체 간의 의존관계는 외부에서 다이나믹하게 설정
            4. AOP
                - 관심사의 분리를 통해 소프트웨어의 모듈성 향상
                - 공통 모듈을 여러 코드에 쉽게 적용 가능
    3. spring di
        - Spring Container가 DI 조립기를 제공
            - 스프링 설정 파일을 통해 객체 간의 의존관계를 설정
            - Spring Container가 제공하는 API를 통해 객체를 사용
        - Bean
            - 스프링이 IOC 방식으로 관리하는 오브젝트
            - 스프링이 직접 그 생성과 제어를 담당하는 오브젝트만을 Bean이라고 함
            - POJO로 정의
        - BeanFactory
            - IOC를 담당하는 핵심 컨테이너
            - Bean을 등록, 생성, 조회, 반환하는 기능을 담당
            - ApplicationContext를 통해 BeanFatory를 사용
        - Application Context
            - BeanFactory를 확장한 IOC 컨테이너
            - 기본적인 기능은 동일
            - 스프링이 제공하는 애플리케이션 지원 기능을 모두 포함해서 이야기 하는 것이라고 보면 됨
    4. bean 생성 옵션 및 주입 관련 annotation, xml 생성 함께 알아 둘 것
        - Bean 생성 옵션
            - Bean 생성 범위
                1. Singleton Bean
                    - 기본적으로 Singleton
                    - 컨테이너가 제공하는 모든 Bean의 인스턴스는 항상 동일
                2. Prototype
                    - 컨테이너에 빈을 요청할 때마다 새로운 인스턴스 생성
                3. request
                    - HTTP Request별로 새로운 인스턴스 생성
                4. session
                    - HTTP Session별로 새로운 인스턴스 생성
        - Bean 주입 annotation
            - @autowired
                - 빈으로 사용될 클래스에 사용하면 자동으로 빈 등록 가능
                - context:component-scan
                - 동일한 타입의 빈이 여러개인 경우 @Qulifier(”name”)으로 식별
            - @Resource
                - 타입에 맞춰서 연결
                - 멤버변수, setter
            - @inject
                - 이름으로 연결
                - setter, 멤버변수, constructor, 일반 method
            - @Repository
                - Dao, Repository 클래스에 사용
            - @Service
                - Service Layer에 사용
            - @Controller
                - Presentation Layer의 MVC Controller에 사용
                - Controller bean으로 설정
            - @Component
                - 위의 Layer를 구분하기 어려울 때 사용’
        - Bean 주입 xml
            - 일반적으로 클래스 이름을 빈의 아이디로 사용(클래스 이름의 첫 글자만 소문자로 바꾼 것)
            - id: 주입 받을 곳에서 호출할 이름
            - class: 주입 할 객체의 클래스
            - factory-method: Singleton 패턴으로 작성된 객체의 factory 메소드 호출
            - <bean id=”” class=””> </bean>
            - Constructor injection
                - ref는 객체 주입, value는 문자열 ,primitive data 주입
                - 객체 또는 값을 생성자를 통해 주입
                - 하위 태그 이용
                    - <ref bean=”bean name”/>
                - 속성 이용
                    - <constructor-arg ref=”bean name”/>
            - Property injection
                - 하위 태그 이용
                    - <ref bean=”bean name”/>
                - 속성 이용
                    - <property name=”propertyname” ref=”bean name”/>
    5. 요청처리 관련 annotation
3. 환경 설정
    1. bean 등록
    2. web.xml, root-context.xml, servlet-context.xml 각 설정 내용 구분
    3. spring boot에서의 환경 설정 - application properties
        
        ![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/609d46e8-84fd-4b9e-9a41-bd47c7d63d8e/cbdc7714-f05b-4bc9-99d7-155464e6e2a4/Untitled.png)
        
4. my batis
    1. 개념 및 특징
        - 개념
            - Java Object와 SQL문 사이의 자동 Mapping 기능을 지원하는 ORM Framework
        - 특징
            1. 쉬운 접근성과 코드의 간결함
                - 수동적인 parameter 설정과 Query 결과에 대한 mapping 구문을 제거.
                - JDBC의 모든 기능을 MyBtis가 대부분 제공.
            2. SQL문과 프로그래밍 코드의 분리
                - SQL에 변경이 있을때마다 자바 코드를 수정하거나 컴파일하지 않아도 됨.
                - SQL 작성과 관리 또는 검토를 DBA와 같은 개발자가 아닌 다름 사람에게 맡길 수 있음.
            3. 다양한 프로그래밍 언어로 구현 가능
                - JAVA, C#, NET, Ruby,…
    2. mapper에서 사용하는 태그들, 동적 쿼리 관련 포함
        
        ![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/609d46e8-84fd-4b9e-9a41-bd47c7d63d8e/a7752ff1-afbe-4539-b540-628e2b883644/Untitled.png)
        
        - line 6~13 - Dto 객체에서의 속성과 DB 컬럼의 속성을 mapping 시켜주는 것
        위 코드에서는 user_id의 DB 컬럼을 MemberDto의 userId로 mapping, DB에 나온 결과를 Dto에 입력하기 위한 것
        - line 15~18, line 20~24, line 26~30 - 사용할 Query문, id에는 Query문을 쓸 Dao method의 이름, parameterType은 Dao method의 parameterType, resultType은 Query문을 실행하고 난 결과의 Type이다 (이때 Query문을 실행한 결과는 ResultSet, int가 있다). resultMap은 returnType이 Dto일 때, Query 문의 결과를 Dto에 싣기 위해 user를 사용한다.
        
5. spring aop
    1. 개념 및 특징
        - AOP는 부가기능을 Aspect로 정의하여, 핵심 기능에서 부가기능을 분리함으로써 핵심기능을 설계하고, 구현할 때 객체지향적인 가치를 지킬 수 있도록 도와주는 개념.
        - Proxy 기반 AOP 지원
        - Proxy가 호출을 가로챈다
        - Spring AOP는 method JoinPoint만 지원
    2. 종류 및 실행 시점
        1. around - 감싸는 것
        2. before - method 수행 전
        3. after returning - method 정상 수행 후
        4. after throwing - method 오류 발생 후
        5. after - 정상이든 오류든 상관 없이
6. spring boot
    1. 개념 및 특징
    2. boot 관련 annotation
        - @SpringBootApplication
            - 해당 클래스가 스프링부트를 설정하는 클래스임을 의미
        - @ComponentScan
            - @Component와 @Service, @Repository, @Controller, @Configuration이 붙은 클래스 Bean들을 찾아서 Context에 bean등록을 해주는 Annotation
        - @Value
            - ${}안의 변수들을 application.properties에서 찾는다.
        - @EnableAutoConfiguration
            - **@EnableAutoConfiguration**
7. interceptor
    1. 개념 및 특징
        - Controller가 요청을 처리하기 전/후 처리.
        - 공통 코드 사용으로 코드 재사용성 증가.
        - 로깅, 모니터링 정보 수집, 접근 제어 처리 등의 실제 Business Logic과는 분리되어 처리해야 하는 기능들을 넣고 싶을 때 유용함.
        - interceptor를 여러 개 설정할 수 있다. (순서 주의!)
        - AOP와 유사한 점이 많지만 사용 예는 다르다.
            - AOP는 비 web
            - Interceptor는 web (controller 기준)
    2. 사용할 수 있는 method(prehandle? 그외)
        
        
    3. interceptor와 filter의 다른 점
