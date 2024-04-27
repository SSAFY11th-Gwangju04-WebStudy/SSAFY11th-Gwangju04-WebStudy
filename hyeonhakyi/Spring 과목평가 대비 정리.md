# Spring 과목평가 대비 정리

1. REST 
    1. REST서비스란?(서술형), Http method와 관련 어노테이션, REST 관련 어노테이션
2. Spring 
    1. Spring MVC 요청 처리 과정(서술형)
    2. 스프링 구조 및 개념
    3. 스프링 DI
    4. Bean 생성 옵션 및 주입 관련 어노테이션 xml설정 또한 알아 둘것
    5. 요청 처리 관련 어노테이션
3. 환경 설정
    1. 빈등록 xml에 빈등록하는 형태 생성자 주입, setter 주입
    2. web.xml,root-context.xml,servlet-context.xml
    3. Spring boot에서의 환경설정 application properties
4. mybatis
    1. 개념 및 특징
    2. mapper.xml에서 사용하는 태그 (동적쿼리 관련 포함)
5. spring aop
    1. aop개념 및 특징
    2. 종류 및 실행 시점
6. 스프링 부트
    1. 개념 및 특징
    2. 부트 관련 어노테이션
7. 인터셉터 
    1. 개념 및 특징
    2. 사용할수 있는 method prehandler 및 여러가지들
    3. 인터셉터와 필터의 다른점 

- Spring
    - 개념
        - Spring Framework는 자바로 EnterPrise Application을 만들 때 포괄적으로 사용하는 Programming 및 Configuration Model을 제공해 주는 Framework로 Application 수준의 인프라 스트럭쳐를 제공.
        - 즉, 개발자가 복잡하고 실수하기 쉬운 Low Level에 신경 쓰지 않고 Business Logic개발에 전념할 수 있도록 해준다.
    - 구조
        - Spring 삼각형(1/5)
            - Enterprise Application 개발 시 복잡함을 해결하는 Spring의 핵심
                1. POJO
                2. PSA
                3. IoC/DI
                4. AOP
                    
                    ![Untitled](Spring%20%E1%84%80%E1%85%AA%E1%84%86%E1%85%A9%E1%86%A8%E1%84%91%E1%85%A7%E1%86%BC%E1%84%80%E1%85%A1%20%E1%84%83%E1%85%A2%E1%84%87%E1%85%B5%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%205f9ac827ab0a4c5c8c781921bc159e34/Untitled.png)
                    
    - 구조(2/5)
        - POJO(Plain Old Java Object)
            - 특정 환경이나 기술에 종속적이지 않은 객체지향 원리에 충실한 자바객체
            - 테스트하기 용이하며, 객체지향 설계를 자유롭게 적용할 수 있다.
    - 구조(3/5)
        - PSA(Portable Service Abstraction)
            - 환경과 세부기술의 변경과 관계없이 일관된 방식으로 기술에 접근할 수 있게 해주는 설계 원칙.
            - 트랜잭션 추상화, OXM 추상화, 데이터 액세스의 Exception 변환기능..등 기술적인 복잡함은 추상화를 통해 Low Level의 기술 구현 부분과 기술을 사용하는 인터페이스로 분리.
            - 예를 들어 데이터베이스에 관계없이 동일하게 적용 할 수 있는 트랜잭션 처리방식.
    - 구조 (4/5)
        - Ioc/DI(Dependency Injection)
            - DI는 유연하게 확장 가능한 객체를 만들어 두고 객체 간의 의존관계는 외부에서 다이나믹하게 설정.
    - 구조 (5/5)
        - AOP(Aspect Oriented Programming)
            - 관심사의 분리를 통해서 소프트웨어의 모듈성을 향상
            - 공통 모듈을 여러 코드에 쉽게 적용 가능
    
    ## 요청 관련 어노테이션
    
    1. @RequestMapping 
        - HTTP 요청(request)을 처리하는 어노테이션이다. @RequestMapping의 경우 기본적으로 GET, POST, PUT, DELETE 등 모든 HTTP 메소드에 대한 요청을 처리하며, 인자에 값을 주어 특정 메서드의 요청만 받아 들이도록 설정할 수 도 있다.
            
            ![Untitled](Spring%20%E1%84%80%E1%85%AA%E1%84%86%E1%85%A9%E1%86%A8%E1%84%91%E1%85%A7%E1%86%BC%E1%84%80%E1%85%A1%20%E1%84%83%E1%85%A2%E1%84%87%E1%85%B5%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%205f9ac827ab0a4c5c8c781921bc159e34/Untitled%201.png)
            
    2. @GetMapping
        - HTTP GET 요청(request)을 처리하는 어노테이션이다.
            
            ![Untitled](Spring%20%E1%84%80%E1%85%AA%E1%84%86%E1%85%A9%E1%86%A8%E1%84%91%E1%85%A7%E1%86%BC%E1%84%80%E1%85%A1%20%E1%84%83%E1%85%A2%E1%84%87%E1%85%B5%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%205f9ac827ab0a4c5c8c781921bc159e34/Untitled%202.png)
            
    3. **@PostMapping**
        - HTTP POST 요청(request)을 처리하는 어노테이션이다.
            
            ![Untitled](Spring%20%E1%84%80%E1%85%AA%E1%84%86%E1%85%A9%E1%86%A8%E1%84%91%E1%85%A7%E1%86%BC%E1%84%80%E1%85%A1%20%E1%84%83%E1%85%A2%E1%84%87%E1%85%B5%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%205f9ac827ab0a4c5c8c781921bc159e34/Untitled%203.png)
            
    4. **@PutMapping**
        - HTTP PUT 요청(request)을 처리하는 어노테이션이다.
            
            ![Untitled](Spring%20%E1%84%80%E1%85%AA%E1%84%86%E1%85%A9%E1%86%A8%E1%84%91%E1%85%A7%E1%86%BC%E1%84%80%E1%85%A1%20%E1%84%83%E1%85%A2%E1%84%87%E1%85%B5%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%205f9ac827ab0a4c5c8c781921bc159e34/Untitled%204.png)
            
    5. **@DeleteMapping**
        - HTTP DELETE 요청(request)을 처리하는 어노테이션이다.
            
            ![Untitled](Spring%20%E1%84%80%E1%85%AA%E1%84%86%E1%85%A9%E1%86%A8%E1%84%91%E1%85%A7%E1%86%BC%E1%84%80%E1%85%A1%20%E1%84%83%E1%85%A2%E1%84%87%E1%85%B5%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%205f9ac827ab0a4c5c8c781921bc159e34/Untitled%205.png)
            
    6. **@RequestParam**
        - HTTP 요청(request)의 query string 파라미터를 추출하는 어노테이션이다.
            
            ![Untitled](Spring%20%E1%84%80%E1%85%AA%E1%84%86%E1%85%A9%E1%86%A8%E1%84%91%E1%85%A7%E1%86%BC%E1%84%80%E1%85%A1%20%E1%84%83%E1%85%A2%E1%84%87%E1%85%B5%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%205f9ac827ab0a4c5c8c781921bc159e34/Untitled%206.png)
            
    7. **@PathVariable**
        - HTTP 요청(request)의 URI 경로에서 파라미터를 추출하는 어노테이션이다.
            
            ![Untitled](Spring%20%E1%84%80%E1%85%AA%E1%84%86%E1%85%A9%E1%86%A8%E1%84%91%E1%85%A7%E1%86%BC%E1%84%80%E1%85%A1%20%E1%84%83%E1%85%A2%E1%84%87%E1%85%B5%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%205f9ac827ab0a4c5c8c781921bc159e34/Untitled%207.png)
            
    8. **@RequestBody**
        - HTTP 요청(request)의 body를 추출하는 어노테이션이다.
            
            ![Untitled](Spring%20%E1%84%80%E1%85%AA%E1%84%86%E1%85%A9%E1%86%A8%E1%84%91%E1%85%A7%E1%86%BC%E1%84%80%E1%85%A1%20%E1%84%83%E1%85%A2%E1%84%87%E1%85%B5%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%205f9ac827ab0a4c5c8c781921bc159e34/Untitled%208.png)
            
    9. **@RequestHeader**
        - HTTP 요청(request)의 헤더를 추출하는 어노테이션이다..
            
            ![Untitled](Spring%20%E1%84%80%E1%85%AA%E1%84%86%E1%85%A9%E1%86%A8%E1%84%91%E1%85%A7%E1%86%BC%E1%84%80%E1%85%A1%20%E1%84%83%E1%85%A2%E1%84%87%E1%85%B5%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%205f9ac827ab0a4c5c8c781921bc159e34/Untitled%209.png)
            
    10. **@CookieValue**
        - @CookieValue 어노테이션을 HTTP 요청의 쿠키 값을 추출한다. 쿠키는 클라이언트와 서버 간에 상태 정보를 유지하기 위해 사용되는 것으로, 해당 어노테이션을 통해 쿠키의 이름을 지정하여, 쿠키값을 받아올 수 있다.
            
            ![Untitled](Spring%20%E1%84%80%E1%85%AA%E1%84%86%E1%85%A9%E1%86%A8%E1%84%91%E1%85%A7%E1%86%BC%E1%84%80%E1%85%A1%20%E1%84%83%E1%85%A2%E1%84%87%E1%85%B5%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%205f9ac827ab0a4c5c8c781921bc159e34/Untitled%2010.png)
            
    11. **@MatrixVariable**
        - URI의 행렬 변수를 추출한다. 행렬 변수란 URI 경로에 ;로 구분된 변수를 의미한다. 예를 들어 /users/1;name=mihee;age=28와 같이 되어있는 것을 의미한다. 매트릭스 변수의 경우 기본적으로 required=true로 필수값이다. 해당 변수가 값이 없을 때 기본값을 설정하려는 경우에는 defaultValue 속성을 사용한다.
            
            ![Untitled](Spring%20%E1%84%80%E1%85%AA%E1%84%86%E1%85%A9%E1%86%A8%E1%84%91%E1%85%A7%E1%86%BC%E1%84%80%E1%85%A1%20%E1%84%83%E1%85%A2%E1%84%87%E1%85%B5%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%205f9ac827ab0a4c5c8c781921bc159e34/Untitled%2011.png)
            
            ![Untitled](Spring%20%E1%84%80%E1%85%AA%E1%84%86%E1%85%A9%E1%86%A8%E1%84%91%E1%85%A7%E1%86%BC%E1%84%80%E1%85%A1%20%E1%84%83%E1%85%A2%E1%84%87%E1%85%B5%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%205f9ac827ab0a4c5c8c781921bc159e34/Untitled%2012.png)
            
    12. **@RequestPart**
        - 파일 업로드의 경우에 사용되며, @RequestPart를 통해 아래와 같이 HTTP 요청의 특정 부분을 구분하여 읽을 수 있다. 해당 어노테이션을 사용할 때에 주의점은 각각의 부분이 개별 요청으로 처리되기 때문에, 파트를 처리하는 메서드는 멀티쓰레드 환경에서 안전하게 동작할 수 있도록 해야 한다.
            
            ![Untitled](Spring%20%E1%84%80%E1%85%AA%E1%84%86%E1%85%A9%E1%86%A8%E1%84%91%E1%85%A7%E1%86%BC%E1%84%80%E1%85%A1%20%E1%84%83%E1%85%A2%E1%84%87%E1%85%B5%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%205f9ac827ab0a4c5c8c781921bc159e34/Untitled%2013.png)
            
- DI
    - 객체 간의 의존관계를 자신이 아닌 외부의 조립기가 수행
    - 제어의 역행(inversion of Control, IoC)이라는 의미로 사용.
    - DI를 통해 시스템에 있는 각 객체를 조정하는 외부 개체가 객체들에게 생성시에 의존관계를 주어짐
    - 느슨한 결합(loose cupling)의 주요 강점
        - 객체는 인터페이스에 의한 의존 관계만을 알고 있으며, 이 의존 관계는 구현 클래스에 대한 차이를 모르는 채 서로 다른 구현으로 대체가 가능
    - 빈 생성범위
        - 싱글톤 빈(Singleton Bean)
            - 스프링 빈은 기본적으로 ‘singleton’으로 만들어짐
            - 그러므로, 컨테이너가 제공하는 모든 빈의 인스턴스는 항상 동일함
            - 컨테이너가 항상 새로운 인스턴스를 반환하게 만들고 싶을 경우 scope를 ‘prototype’으로 설정해야 함.
        - annotation
            
            ![Untitled](Spring%20%E1%84%80%E1%85%AA%E1%84%86%E1%85%A9%E1%86%A8%E1%84%91%E1%85%A7%E1%86%BC%E1%84%80%E1%85%A1%20%E1%84%83%E1%85%A2%E1%84%87%E1%85%B5%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%205f9ac827ab0a4c5c8c781921bc159e34/Untitled%2014.png)
            
        - xml
            
            ![Untitled](Spring%20%E1%84%80%E1%85%AA%E1%84%86%E1%85%A9%E1%86%A8%E1%84%91%E1%85%A7%E1%86%BC%E1%84%80%E1%85%A1%20%E1%84%83%E1%85%A2%E1%84%87%E1%85%B5%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%205f9ac827ab0a4c5c8c781921bc159e34/Untitled%2015.png)
            
        - 빈의 생성 범위 지정
            
            ![Untitled](Spring%20%E1%84%80%E1%85%AA%E1%84%86%E1%85%A9%E1%86%A8%E1%84%91%E1%85%A7%E1%86%BC%E1%84%80%E1%85%A1%20%E1%84%83%E1%85%A2%E1%84%87%E1%85%B5%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%205f9ac827ab0a4c5c8c781921bc159e34/Untitled%2016.png)
            
    - 스프링 빈 설정
        - 스프링 빈 설정 메타정보
            
            ![Untitled](Spring%20%E1%84%80%E1%85%AA%E1%84%86%E1%85%A9%E1%86%A8%E1%84%91%E1%85%A7%E1%86%BC%E1%84%80%E1%85%A1%20%E1%84%83%E1%85%A2%E1%84%87%E1%85%B5%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%205f9ac827ab0a4c5c8c781921bc159e34/Untitled%2017.png)
            
    - 스프링 빈 설정 : XML
        - XML 문서
            - XML문서 형태의 빈의 설정 메타 정보를 기술
            - 단순하며 사용하기 쉬움
            - <bean> 태그를 통해 세밀한 제어 가능
                
                ![Untitled](Spring%20%E1%84%80%E1%85%AA%E1%84%86%E1%85%A9%E1%86%A8%E1%84%91%E1%85%A7%E1%86%BC%E1%84%80%E1%85%A1%20%E1%84%83%E1%85%A2%E1%84%87%E1%85%B5%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%205f9ac827ab0a4c5c8c781921bc159e34/Untitled%2018.png)
                
    - 스프링 빈 설정 : Annotation
        - Annotation
            - 어플리케이션의 규모가 커지고 빈의 개수가 많아질 경우 XML 파일을 관리하는 것이 번거러움
            - 빈으로 사용될 클래스에 특별한 annotation(@autowired)을 부여해 주면 자동으로 빈 등록 가능
            - “오브젝트 빈 스캐너”로 “빈 스캐닝”을 통해 자동 등록
                - 빈 스캐너는 기본적으로 클래스 이름을 빈의 아이디로 사용 (클래스 이름의 첫 글자만 소문자로 바꾼 것)
                
                ![Untitled](Spring%20%E1%84%80%E1%85%AA%E1%84%86%E1%85%A9%E1%86%A8%E1%84%91%E1%85%A7%E1%86%BC%E1%84%80%E1%85%A1%20%E1%84%83%E1%85%A2%E1%84%87%E1%85%B5%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%205f9ac827ab0a4c5c8c781921bc159e34/Untitled%2019.png)
                
            - Annotation으로 빈을 설정 할 경우 반드시 component-scan을 설정 해야 한다.
                
                ![Untitled](Spring%20%E1%84%80%E1%85%AA%E1%84%86%E1%85%A9%E1%86%A8%E1%84%91%E1%85%A7%E1%86%BC%E1%84%80%E1%85%A1%20%E1%84%83%E1%85%A2%E1%84%87%E1%85%B5%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%205f9ac827ab0a4c5c8c781921bc159e34/Untitled%2020.png)
                
    - 스프링 빈 설정 : Java Configuration File
        - Annotation 기반 : @Configuration 적용
            
            ![Untitled](Spring%20%E1%84%80%E1%85%AA%E1%84%86%E1%85%A9%E1%86%A8%E1%84%91%E1%85%A7%E1%86%BC%E1%84%80%E1%85%A1%20%E1%84%83%E1%85%A2%E1%84%87%E1%85%B5%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%205f9ac827ab0a4c5c8c781921bc159e34/Untitled%2021.png)
            
    - 스프링 빈 설정 : Annotation
        - Sterotype annotation 종류
            - 빈 자동등록에 사용할 수 있는 annotation
            - 빈 자동인식을 위한 annotation이 여러가지인 이유
                - 계층별로 빈의 특성이나 종류를 구분
                - AOP Pointcut 표현식을 사용하면 특정 annotation이 달린 클래스만 설정 가능.
                - 특정 계층의 빈에 부가기능을 부여
                
                ![Untitled](Spring%20%E1%84%80%E1%85%AA%E1%84%86%E1%85%A9%E1%86%A8%E1%84%91%E1%85%A7%E1%86%BC%E1%84%80%E1%85%A1%20%E1%84%83%E1%85%A2%E1%84%87%E1%85%B5%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%205f9ac827ab0a4c5c8c781921bc159e34/Untitled%2022.png)
                
    - 스프링 설정 : xml
        - XML 문서 이용
            - Application에서 사용할 Spring 자원들을 설정하는 파일
            - Spring Contatiner는 설정파일에 설정된 내용을 읽어 Application에서 필요한 기능들을 제공
            - Root tag는 <beans>
            - 파일명은 상관없다.
                
                ![Untitled](Spring%20%E1%84%80%E1%85%AA%E1%84%86%E1%85%A9%E1%86%A8%E1%84%91%E1%85%A7%E1%86%BC%E1%84%80%E1%85%A1%20%E1%84%83%E1%85%A2%E1%84%87%E1%85%B5%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%205f9ac827ab0a4c5c8c781921bc159e34/Untitled%2023.png)
                
        - 기본 설정 - 빈 객체 생성 및 주입
            - 주입 할 객체를 설정 파일에 설정
                - <bean> : 스프링 컨테이너가 관리할 Bean객체를 설정
            - 기본 속성
                - name : 주입 받을 곳에서 호출 할 이름 설정
                - id : 주입 받을 곳에서 호출 할 이름 설정(유일 값)
                - class : 주입 할 객체의 클래스
                - factory-method : Singleton 패턴으로 작성된 객체의 factory 메소드 호출
                
                ![Untitled](Spring%20%E1%84%80%E1%85%AA%E1%84%86%E1%85%A9%E1%86%A8%E1%84%91%E1%85%A7%E1%86%BC%E1%84%80%E1%85%A1%20%E1%84%83%E1%85%A2%E1%84%87%E1%85%B5%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%205f9ac827ab0a4c5c8c781921bc159e34/Untitled%2024.png)
                
        - 기본 설정 - 빈 객체 얻기
            - 설정 파일에 설정한 bean을 Container가 제공하는 주입기 역할의 api를 통해 주입 받는다.
                
                ![Untitled](Spring%20%E1%84%80%E1%85%AA%E1%84%86%E1%85%A9%E1%86%A8%E1%84%91%E1%85%A7%E1%86%BC%E1%84%80%E1%85%A1%20%E1%84%83%E1%85%A2%E1%84%87%E1%85%B5%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%205f9ac827ab0a4c5c8c781921bc159e34/Untitled%2025.png)
                
    - 스프링 빈 의존 관계설정 - XML
        - Constructor injection
            - 객체 또는 값을 생성자를 통해 주입 받는다
            - <constructor-arg> : <bean>의 하위태그를 이용하여 설정 하거나 또는 속성을 이용하여 설정
                - 설정 방법 : <ref>, <value>와 같은 하위태그를 이용하여 설정 하거나 또는 속성을 이용하여 설정.
                    1. 하위태그 이용
                        1. 객체 주입 시 : <ref bean = “bean name”/>
                        2. 문자열(String), primitive data주입 시 : <value>값</value>
                        
                        * type 속성 : 값은 기본적으로 String으로 처리 값의 타입을 명시해야 하는 경우 사용
                        
                        - ex) <value type=”int”>10</value>
                    2. 속성 이용
                        1. 객체 주입 시 : <constructor-arg ref = “baen name”/>
                        2. 문자열(String), primitive data 주입 시 : <constructor-arg value =”값”/>
                        
                        ![Untitled](Spring%20%E1%84%80%E1%85%AA%E1%84%86%E1%85%A9%E1%86%A8%E1%84%91%E1%85%A7%E1%86%BC%E1%84%80%E1%85%A1%20%E1%84%83%E1%85%A2%E1%84%87%E1%85%B5%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%205f9ac827ab0a4c5c8c781921bc159e34/Untitled%2026.png)
                        
        - Property injection
            - property를 통해 객체 또는 값을 주입 받는다 - setter method
                - 주의 : setter를 통해서는 하나의 값만 받을 수 있다.
            - <property> : <bean>의 하위태그로 설정한 bean 객체 또는 값을 property를 통해 주입하도록 설정.
                - 설정 방법 : <ref>, <value>와 같은 하위태그를 이용하여 설정 하거나 또는 속성을 이용하여 설정
                    1. 하위 태그 
                        1. 객체 주입 시 : <ref bean = “bean name”/>
                        2. 문자열(String), primitive data 주입 시 : <value>값</value>
                    2. 속성 이용 : name - 값을 주입할 property 이름(setter의 이름)
                        1. 객체 주입 시 : <property name=”propertyname” ref=”bean name”/>
                        2. 문자열(String), primitive data 주입 시 : <property name=”propertyname” value=”값”/>
                    3. xml namespace를 이용하여 설정
                        - <bean>태그의 스키마 설정에 namespace등록
                        - Bean 설정 시 <bean>태그의 속성으로 설정
                            - 기본데이터 주입 > p:propertyname=”value”
                            - bean 주입 > p:propertyname-ref=”bean_id”
                        
                        ![Untitled](Spring%20%E1%84%80%E1%85%AA%E1%84%86%E1%85%A9%E1%86%A8%E1%84%91%E1%85%A7%E1%86%BC%E1%84%80%E1%85%A1%20%E1%84%83%E1%85%A2%E1%84%87%E1%85%B5%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%205f9ac827ab0a4c5c8c781921bc159e34/Untitled%2027.png)
                        
                
                ![Untitled](Spring%20%E1%84%80%E1%85%AA%E1%84%86%E1%85%A9%E1%86%A8%E1%84%91%E1%85%A7%E1%86%BC%E1%84%80%E1%85%A1%20%E1%84%83%E1%85%A2%E1%84%87%E1%85%B5%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%205f9ac827ab0a4c5c8c781921bc159e34/Untitled%2028.png)
                
    - DI - Annotation
        - 스프링 빈 의존 관계설정 - annotation
            - Annotation : 멤버변수에 직접 정의 하는 경우 setter method를 만들지 않아도 됨.
                
                ![Untitled](Spring%20%E1%84%80%E1%85%AA%E1%84%86%E1%85%A9%E1%86%A8%E1%84%91%E1%85%A7%E1%86%BC%E1%84%80%E1%85%A1%20%E1%84%83%E1%85%A2%E1%84%87%E1%85%B5%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%205f9ac827ab0a4c5c8c781921bc159e34/Untitled%2029.png)
                
            - 특정 Bean의 기능 수행을 위해 다른 Bean을 참조해야 하는 경우 사용한다.
        - 기본설정 - 빈 객체 얻기
            - 설정한 component-scan에 의해 scanning에 의해 scanning 된 bean을 Container가 제공하는 주입기 역할의 api를 통해 주입 받는다.
            - bean 파일에는 @Component 설정
                
                ![Untitled](Spring%20%E1%84%80%E1%85%AA%E1%84%86%E1%85%A9%E1%86%A8%E1%84%91%E1%85%A7%E1%86%BC%E1%84%80%E1%85%A1%20%E1%84%83%E1%85%A2%E1%84%87%E1%85%B5%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%205f9ac827ab0a4c5c8c781921bc159e34/Untitled%2030.png)
                
                ![Untitled](Spring%20%E1%84%80%E1%85%AA%E1%84%86%E1%85%A9%E1%86%A8%E1%84%91%E1%85%A7%E1%86%BC%E1%84%80%E1%85%A1%20%E1%84%83%E1%85%A2%E1%84%87%E1%85%B5%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%205f9ac827ab0a4c5c8c781921bc159e34/Untitled%2031.png)
                
        - 필드에 @Autowired
            - 동일한 타입의 bean이 여러 개일 경우에는 @Qualifier(”name”)으로 식별한다.
                
                ![Untitled](Spring%20%E1%84%80%E1%85%AA%E1%84%86%E1%85%A9%E1%86%A8%E1%84%91%E1%85%A7%E1%86%BC%E1%84%80%E1%85%A1%20%E1%84%83%E1%85%A2%E1%84%87%E1%85%B5%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%205f9ac827ab0a4c5c8c781921bc159e34/Untitled%2032.png)
                
        - 생성자에 @Autowired
            - immutable:의존성이 필요한 filed를 final로 선언 가능
            - 순환 참조 방지
            - 생성자가 1개일 경우 @Autowired 생략 가능
            - 동일한 타입의 bean이 여러 개일 경우에는 @Qualifier(”name”)으로 식별한다.
                
                ![Untitled](Spring%20%E1%84%80%E1%85%AA%E1%84%86%E1%85%A9%E1%86%A8%E1%84%91%E1%85%A7%E1%86%BC%E1%84%80%E1%85%A1%20%E1%84%83%E1%85%A2%E1%84%87%E1%85%B5%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%205f9ac827ab0a4c5c8c781921bc159e34/Untitled%2033.png)
                
        - set method에 @Autowired
            - 동일한 타입의 bean이 여러 개일 경우에는 @Qualifier(”name”)으로 식별한다.
                
                ![Untitled](Spring%20%E1%84%80%E1%85%AA%E1%84%86%E1%85%A9%E1%86%A8%E1%84%91%E1%85%A7%E1%86%BC%E1%84%80%E1%85%A1%20%E1%84%83%E1%85%A2%E1%84%87%E1%85%B5%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%205f9ac827ab0a4c5c8c781921bc159e34/Untitled%2034.png)
                
    - 기타 설정
        - 빈 객체의 생성단위
            - BeanFactory를 통해 Bean을 요청 시 객체생성의 범위(단위)를 설정
            - <bean>의 scope 속성을 이용해 설정
            - scope의 값
                
                ![Untitled](Spring%20%E1%84%80%E1%85%AA%E1%84%86%E1%85%A9%E1%86%A8%E1%84%91%E1%85%A7%E1%86%BC%E1%84%80%E1%85%A1%20%E1%84%83%E1%85%A2%E1%84%87%E1%85%B5%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%205f9ac827ab0a4c5c8c781921bc159e34/Untitled%2035.png)
                
        - Factory Method로부터 빈(bean) 생성
            - getBean()으로 호출 시 private 생성자도 호출 하여 객체를 생성한다.
            - 그러므로 위의 상황에서 fatory method만 호출 해야 객체를 얻을 수 있는 것은 아니다.
                
                ![Untitled](Spring%20%E1%84%80%E1%85%AA%E1%84%86%E1%85%A9%E1%86%A8%E1%84%91%E1%85%A7%E1%86%BC%E1%84%80%E1%85%A1%20%E1%84%83%E1%85%A2%E1%84%87%E1%85%B5%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%205f9ac827ab0a4c5c8c781921bc159e34/Untitled%2036.png)
                
- AOP
    - 개념
        - 부가기능을 관점(Aspect)로 정의하여, 핵심기능에서 부가기능을 분리함으로써 핵심기능을 설계하고 구현할 때 객체지향적인 가치를 지킬 수 있도록 도와주는 개념.
            - 관점이란?
                
                ⇒ 부가 기능과 그 적용처를 정의하고 합쳐서 모듈로 만든 것.
                
    - 적용 예시
        - 간단한 메소드의 성능 검사
        - 트랜잭션 처리
        - 예외 반환
        - 아키텍처 검증
        - 기타 등등..
    - AOP 구조
        - 핵심 관심 사항 : BankingService, AccountService, CustomerService
        - 공통 관심 사항 : Security, Transaction, Other…
            
            ![Untitled](Spring%20%E1%84%80%E1%85%AA%E1%84%86%E1%85%A9%E1%86%A8%E1%84%91%E1%85%A7%E1%86%BC%E1%84%80%E1%85%A1%20%E1%84%83%E1%85%A2%E1%84%87%E1%85%B5%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%205f9ac827ab0a4c5c8c781921bc159e34/Untitled%2037.png)
            
    - Spring Aop 용어
        - Target
            - 핵심기능을 담고 있는 모듈로 target은 부가기능을 부여할 대상이 됨
        - Advice
            - 어느 시점(Ex : method의 수행 전/후, 예외 발생 후 등..)에 어떤 공통 관심 기능(Aspect)을 적용할지 정의 한 것.
            - Target에 제공할 부가기능을 담고 있는 모듈
        - JoinPoint
            - Aspect가 적용 될 수 있는 지점. (method, field)
            - 즉 target 객체가 구현한 인터페이스의 모든 method는 JoinPoint가 됨.
        - Pointcut
            - 공통 관심 사항이 적용될 JoinPoint
            - Advice를 적용할 target의 method를 선별하는 정규 표현식
            - Pointcut 표현식은 execution으로 시작하고 method의 Signature를 비교하는 방법을 주로 이용
            - 표현식
                
                ![Untitled](Spring%20%E1%84%80%E1%85%AA%E1%84%86%E1%85%A9%E1%86%A8%E1%84%91%E1%85%A7%E1%86%BC%E1%84%80%E1%85%A1%20%E1%84%83%E1%85%A2%E1%84%87%E1%85%B5%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%205f9ac827ab0a4c5c8c781921bc159e34/Untitled%2038.png)
                
                ![Untitled](Spring%20%E1%84%80%E1%85%AA%E1%84%86%E1%85%A9%E1%86%A8%E1%84%91%E1%85%A7%E1%86%BC%E1%84%80%E1%85%A1%20%E1%84%83%E1%85%A2%E1%84%87%E1%85%B5%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%205f9ac827ab0a4c5c8c781921bc159e34/Untitled%2039.png)
                
        - Aspect
            - 여러 객체에서 공통으로 적용되는 공통 관심 사항. (transaction, logging, security..)
            - AOP의 기본 모듈
            - Aspect = Advice + Pointcut
            - Aspect는 Singleton 형태의 객체로 존재
        - Advisor
            - Advisor = Advice + pointcut
            - Advisor는 Spring AOP에서만 사요외는 특별한 용어
        - Weaving
            - 어떤 Advice를 어떤 Pointcut(핵심사항)에 적용시킬 것인지에 대한 설정(Advisor)
            - 즉 Pointcut에 의해서 결정된 타겟의 JoinPoint에 부가기능(Advice)을 삽입하는 과정을 뜻함
            - Weaving은 AOP의 핵심기능(Target)의 코드에 영향을 주지 않으면서 필요한 부가기능(Advice)을 추가할 수 있도록 해주는 핵심적인 처리과정.
    - Spring AOP 특징
        1. Spring은 프록시(proxy) 기반 AOP를 지원
            - Spring은 Target 객체에 대한 Proxy를 만들어 제공
            - Target을 감싸는 Proxy는 실행시간(Runtime)에 생성
            - Proxy는 Advice를 Target 객체에 적용하면서 생성되는 객체
                
                ![Untitled](Spring%20%E1%84%80%E1%85%AA%E1%84%86%E1%85%A9%E1%86%A8%E1%84%91%E1%85%A7%E1%86%BC%E1%84%80%E1%85%A1%20%E1%84%83%E1%85%A2%E1%84%87%E1%85%B5%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%205f9ac827ab0a4c5c8c781921bc159e34/Untitled%2040.png)
                
        2. 프록시(Proxy)가 호출을 가로챈다(Intercept)
            - Proxy는 Target 객체에 대한 호출을 가로챈 다음 Advice의 부가기능 로직을 수행하고 난 후에 Target의 핵심 기능 로직을 호출한다. (전처리 Advice)
            - 또는 Target의 핵심 기능 로직 method를 호출한 후에 부가기능(Advice)을 수행하는 경우도 있다. (후처리 Advice)
                
                ![Untitled](Spring%20%E1%84%80%E1%85%AA%E1%84%86%E1%85%A9%E1%86%A8%E1%84%91%E1%85%A7%E1%86%BC%E1%84%80%E1%85%A1%20%E1%84%83%E1%85%A2%E1%84%87%E1%85%B5%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%205f9ac827ab0a4c5c8c781921bc159e34/Untitled%2041.png)
                
        3. Spring AOP는 method JoinPoint만 지원
            - Spring은 동적 Proxy를 기반으로 AOP를 구현하므로 method JoinPoint만 지원한다.
            - 즉, 핵심기능(Target)의 method가 호출되는 런타임 시점에만 부가기능(Advice)를 적용할 수 있다.
            - 반면 AspectJ 같은 고급 AOP framework를 사용하면 객체의 생성, 필드값의 조회와 조작, static method 호출 및 초기화 등의 다양한 작업에 부가기능을 적용할 수 있다.
    - Spring AOP 구현 방법
        - POJO Class를 이용한 AOP 구현
        - Spring API를 이용한 AOP 구현
        - Annotation을 이용한 AOP 구현
    - POJO기반 AOP 구현 - Adivce 종류
        - Before Advice
            - 대상 객체의 메소드가 실행되기 전에 실행됨
            - return type : 리턴 값을 갖더라도 실제 Advice의 적용과정에서 사용되지 않기 때문에 void을 쓴다.
            - argument : 없거나 대상객체 및 호출되는 메소드에 대한 정보 또는 파라미터에 대한 정보가 필요하다면 JoinPoint 객체를 전달
                
                ![Untitled](Spring%20%E1%84%80%E1%85%AA%E1%84%86%E1%85%A9%E1%86%A8%E1%84%91%E1%85%A7%E1%86%BC%E1%84%80%E1%85%A1%20%E1%84%83%E1%85%A2%E1%84%87%E1%85%B5%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%205f9ac827ab0a4c5c8c781921bc159e34/Untitled%2042.png)
                
        - Before Advice 실행 순서
            
            ![Untitled](Spring%20%E1%84%80%E1%85%AA%E1%84%86%E1%85%A9%E1%86%A8%E1%84%91%E1%85%A7%E1%86%BC%E1%84%80%E1%85%A1%20%E1%84%83%E1%85%A2%E1%84%87%E1%85%B5%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%205f9ac827ab0a4c5c8c781921bc159e34/Untitled%2043.png)
            
            1. 빈 객체를 사용하는 코드에서 스프링이 생성한 AOP 프록시의 메소드를 호출
            2. AOP 프록시는 <aop : before>에서 지정한 메소드를 호출
            3. AOP 프록시는 Aspect 기능 실행 후 실제 빈 객체의 메소드를 호출
        - After Returning Advice
            - 대상 객체의 method 실행이 정상적으로 끝난 뒤 실행 됨.
            - return type : void
            - argument :
                - 없거나 org.aspectj.lang.JoinPoint 객체를 받는다. JoinPoint는 항상 첫 argument로 사용
                - 대상 method에서 반환되는 특정 객체 타입의 값을 argument로 받을 수 있다.
                
                ![Untitled](Spring%20%E1%84%80%E1%85%AA%E1%84%86%E1%85%A9%E1%86%A8%E1%84%91%E1%85%A7%E1%86%BC%E1%84%80%E1%85%A1%20%E1%84%83%E1%85%A2%E1%84%87%E1%85%B5%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%205f9ac827ab0a4c5c8c781921bc159e34/Untitled%2044.png)
                
        - After Returning Advice 실행 순서.
            
            ![Untitled](Spring%20%E1%84%80%E1%85%AA%E1%84%86%E1%85%A9%E1%86%A8%E1%84%91%E1%85%A7%E1%86%BC%E1%84%80%E1%85%A1%20%E1%84%83%E1%85%A2%E1%84%87%E1%85%B5%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%205f9ac827ab0a4c5c8c781921bc159e34/Untitled%2045.png)
            
            1. 빈 객체를 사용하는 코드에서 스프링이 생성한 AOP 프록시의 메소드를 호출
            2. AOP 프록시는 실제 빈 객체의 메소드를 호출 (정상 실행).
            3. AOP 프록시는. <aop : after-returning>에서 지정한 메소드를 호출.
        - After Throwing Advice
            - 대상 객체의 method 실행 중 예외가 발생한 경우 실행 됨
            - return type : void
            - argument
                - 없거나 JoinPoint 객체를 받는다. JoinPoint는 항상 첫 argument로 사용
                - 대상 method에서 전달되는 예외 객체를 argument로 받을 수 있다.
                
                ![Untitled](Spring%20%E1%84%80%E1%85%AA%E1%84%86%E1%85%A9%E1%86%A8%E1%84%91%E1%85%A7%E1%86%BC%E1%84%80%E1%85%A1%20%E1%84%83%E1%85%A2%E1%84%87%E1%85%B5%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%205f9ac827ab0a4c5c8c781921bc159e34/Untitled%2046.png)
                
        - After Throwing Advice 실행 순서
            
            ![Untitled](Spring%20%E1%84%80%E1%85%AA%E1%84%86%E1%85%A9%E1%86%A8%E1%84%91%E1%85%A7%E1%86%BC%E1%84%80%E1%85%A1%20%E1%84%83%E1%85%A2%E1%84%87%E1%85%B5%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%205f9ac827ab0a4c5c8c781921bc159e34/Untitled%2047.png)
            
            1. 빈 객체를 사용하는 코드에서 스프링이 생성한 AOP 프록시의 메소드를 호출
            2. AOP 프록시는 실제 빈 객체의 메소드를 호출 (exception 발생)
            3. AOP 프록시는 <aop : after-throwing>에서 지정한 메소드를 호출.
        - After Advice
            - 대상 객체의 method가 정상적으로 실행 되었는지 아니면 exception을 발생 시켰는지의 여부와 상관없이 메소드 실행 종료 후 공통 기능 적용
            - return type : void
            - argument
                - 없거나 JoinPoint 객체를 받는다. JoinPoint는 항상 첫 argument로 사용
                
                ![Untitled](Spring%20%E1%84%80%E1%85%AA%E1%84%86%E1%85%A9%E1%86%A8%E1%84%91%E1%85%A7%E1%86%BC%E1%84%80%E1%85%A1%20%E1%84%83%E1%85%A2%E1%84%87%E1%85%B5%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%205f9ac827ab0a4c5c8c781921bc159e34/Untitled%2048.png)
                
        - After Advice 실행 순서
            
            ![Untitled](Spring%20%E1%84%80%E1%85%AA%E1%84%86%E1%85%A9%E1%86%A8%E1%84%91%E1%85%A7%E1%86%BC%E1%84%80%E1%85%A1%20%E1%84%83%E1%85%A2%E1%84%87%E1%85%B5%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%205f9ac827ab0a4c5c8c781921bc159e34/Untitled%2049.png)
            
            1. 빈 객체를 사용하는 코드에서 스프링이 생성한 AOP 프록시의 메소드를 호출
            2. AOP 프록시는 실제 빈 객체의 메소드를 호출 (정상 실행, exception 발생 : java의 finally와 같음)
            3. AOP 프록시는 <aop : after>에서 지정한 메소드를 호출
        - Around Advice
            - 위의 네가지 Advice를 다 구현 할 수 있는 Advice
            - return type : Object
            - argument
                - org.aspectj.lang.ProceedingJoinPoint를 반드시 첫 argument로 지정
                
                ![Untitled](Spring%20%E1%84%80%E1%85%AA%E1%84%86%E1%85%A9%E1%86%A8%E1%84%91%E1%85%A7%E1%86%BC%E1%84%80%E1%85%A1%20%E1%84%83%E1%85%A2%E1%84%87%E1%85%B5%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%205f9ac827ab0a4c5c8c781921bc159e34/Untitled%2050.png)
                
        - Around Advice 실행 순서
            
            ![Untitled](Spring%20%E1%84%80%E1%85%AA%E1%84%86%E1%85%A9%E1%86%A8%E1%84%91%E1%85%A7%E1%86%BC%E1%84%80%E1%85%A1%20%E1%84%83%E1%85%A2%E1%84%87%E1%85%B5%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%205f9ac827ab0a4c5c8c781921bc159e34/Untitled%2051.png)
            
            1. 빈 객체를 사용하는 코드에서 스프링이 생성한 AOP 프록시의 메소드를 호출
            2. AOP 프록시는 <aop : around>에서 지정한 메소드를 호출
            3. AOP 프록시는 실제 빈 객체의 메소드를 호출
            4. AOP 프록시는 <aop : around>에서 지정한 메소드를 호출
    - @aspect Annotation을 이용한 AOP 구현
        - @Aspect Annotation을 이용하여 Aspect Class에 직접 Advice 및 Pointcut등을 설정
        - 설정 파일에 <aop : aspectj - autoproxy/>를 반드시 추가
        - Aspect Class를 <bean>으로 등록
        - 어노테이션(Annotation)
            - @Aspect : Aspect Class 선언
            - @Before(”pointcut”)
            - @AfterReturning(pointcut=””,returning=””)
            - @AfterThrowing(pointcut=””,returning=””)
            - @After(”pointcut”)
            - @Around(”pointcut”)
        - Around를 제외한 나머지 method들은 첫 argument로 JoinPoint를 가질 수 있다.
        - Around method는 argument로 ProceedingJoinPoint를 가질 수 있다.
        
        ![Untitled](Spring%20%E1%84%80%E1%85%AA%E1%84%86%E1%85%A9%E1%86%A8%E1%84%91%E1%85%A7%E1%86%BC%E1%84%80%E1%85%A1%20%E1%84%83%E1%85%A2%E1%84%87%E1%85%B5%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%205f9ac827ab0a4c5c8c781921bc159e34/Untitled%2052.png)
        
- REST
    - HTTP URI를 통해 제어할 자원(Resource)를 명시하고, HTTP Method(GET, POST, PUT, DELETE)을 통해 해당 자원(Resource)를 제어하는 명령을 내리는 방식의 아키텍처
    
    ## REST 구성
    
    - 자원 ⇒ URI
    - 행위 (Verb) ⇒ HTTP Method
    - 표현 (Representation)
    - 잘 표현된 HTTP URI로 리소스를 정의하고 HTTP method로 리소스에 대한 행위를 정의한다. 리소스는 JSON, XML과 같은 여러 가지 언어로 표현할 수 있다.
    
    ## REST
    
    - 기존의 웹 접근 방식과 REST API 방식의 차이점
        
        ![Untitled](Spring%20%E1%84%80%E1%85%AA%E1%84%86%E1%85%A9%E1%86%A8%E1%84%91%E1%85%A7%E1%86%BC%E1%84%80%E1%85%A1%20%E1%84%83%E1%85%A2%E1%84%87%E1%85%B5%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%205f9ac827ab0a4c5c8c781921bc159e34/Untitled%2053.png)
        
        - 기존의 블로그등은 GET과 POST만으로 자원에 대한 CRUD를 처리하며, URI는 액션을 나타냈다.
        - REST로 변경할 경우 4가지 method를 모두 사용하여 CRUD를 처리하며, URI는 제어하려는 자원을 나타낸다.
    - 회원 목록 보기 예시
        
        ![Untitled](Spring%20%E1%84%80%E1%85%AA%E1%84%86%E1%85%A9%E1%86%A8%E1%84%91%E1%85%A7%E1%86%BC%E1%84%80%E1%85%A1%20%E1%84%83%E1%85%A2%E1%84%87%E1%85%B5%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%205f9ac827ab0a4c5c8c781921bc159e34/Untitled%2054.png)
        
    - 회원 등록 결과
        
        ![Untitled](Spring%20%E1%84%80%E1%85%AA%E1%84%86%E1%85%A9%E1%86%A8%E1%84%91%E1%85%A7%E1%86%BC%E1%84%80%E1%85%A1%20%E1%84%83%E1%85%A2%E1%84%87%E1%85%B5%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%205f9ac827ab0a4c5c8c781921bc159e34/Untitled%2055.png)
        
    - 회원 정보 등록
        
        ![Untitled](Spring%20%E1%84%80%E1%85%AA%E1%84%86%E1%85%A9%E1%86%A8%E1%84%91%E1%85%A7%E1%86%BC%E1%84%80%E1%85%A1%20%E1%84%83%E1%85%A2%E1%84%87%E1%85%B5%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%205f9ac827ab0a4c5c8c781921bc159e34/Untitled%2056.png)
        
    - 회원 정보 수정
        
        ![Untitled](Spring%20%E1%84%80%E1%85%AA%E1%84%86%E1%85%A9%E1%86%A8%E1%84%91%E1%85%A7%E1%86%BC%E1%84%80%E1%85%A1%20%E1%84%83%E1%85%A2%E1%84%87%E1%85%B5%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%205f9ac827ab0a4c5c8c781921bc159e34/Untitled%2057.png)
        
    - 회원 정보 삭제
        
        ![Untitled](Spring%20%E1%84%80%E1%85%AA%E1%84%86%E1%85%A9%E1%86%A8%E1%84%91%E1%85%A7%E1%86%BC%E1%84%80%E1%85%A1%20%E1%84%83%E1%85%A2%E1%84%87%E1%85%B5%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%205f9ac827ab0a4c5c8c781921bc159e34/Untitled%2058.png)
        
    
    ## REST 관련 Annotation
    
    ![Untitled](Spring%20%E1%84%80%E1%85%AA%E1%84%86%E1%85%A9%E1%86%A8%E1%84%91%E1%85%A7%E1%86%BC%E1%84%80%E1%85%A1%20%E1%84%83%E1%85%A2%E1%84%87%E1%85%B5%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%205f9ac827ab0a4c5c8c781921bc159e34/Untitled%2059.png)
    
- 인터셉터
    
    ## 개념
    
    - 서버에 들어온 Request객체를 컨트롤러의 핸들러(url에 매핑되어있는 매서드)로 도달하기 전에 낚아채서 개발자가 원하는 추가적인 작업을 한 후 핸들러로 보낼 수 있도록 해주는 것
    
    ## 특징
    
    ### 1. 전역 적인 작업 처리
    
    ### 2. 요청 처리 제어
    
    ### 3. **유연한 설정**
    
    ### 4. **다양한 용도에 활용 가능**
    
    ### 5. **다중 인터셉터 지원**
    
    ### 6. 공통 코드 사용으로 코드 재사용성 증가
    
    ## Method
    
    1. preHandel
        - Controller method가 실행되기 전 호출
        - false를 반환하며 request를 바로 종료
        - 전처리 작업이나 요청 정보를 가공하는 작업등
    2. postHandle
        - Controlle method 실행 직후 view 페이지가 렌더링 되기 전 호출
    3. afterCompletion
        - view 페이지가 렌더링 되고 난 후 호출
        - 예외가 발생하여도 실행
    
    ## 인터셉터와 필터의 차이
    
    1. 필터의 경우 웹 컨텍스트 안에서 실행되며, 인터셉터의 경우 스프링 컨텍스트 안에서 실행된다.
    2. 필터의 경우 HttpServlet request / response 객체를 조작할 수 있지만 인터셉터의 경우 불가능 하다.
- 스프링 부트
    
    ## 개념
    
    - 스프링으로 애플리케이션을 만들 때에 필요한 설정을 간편하게 처리해주는 별도의 프레임워크
    
    ## 특징
    
    1. project에 따라 자주 사용되는 library들이 미리 조합되어 있음(starter 의존성 제공)
    2. 복잡한 설정을 자동으로 처리
    3. 웹 서버에 의존적이지 않은 독립적인 Spring Application 구축 가능.
    4. Tomcat, Jetty와 같은 WAS 추가로 설치하지 않아도 개발 가능(내장 서버)
    5. WAS에 배포하지 않고도 실행할 수 있는 JAR파일로 WEB Application을 개발 할 수 있다.
    
    ## 어노테이션
    
    1. @Component
        - 개발자가 생성한 Class를 Spring의 Bean으로 등록할 때 사용하는 Annotation입니다.
    2. @ComponentScan
        - **@Component, @Service, @Repository, @Controller, @Configuration** 중 1개라도 등록된 클래스를 찾으면, **Context에 bean으로 등록**합니다.
        - **@ComponentScan Annotation이 있는 클래스의 하위 Bean을 등록될 클래스들을 스캔하여 Bean**으로 등록해 줍니다.
    3. @Bean
        - **@Bean** Annotation은 **개발자가 제어가 불가능한 외부 라이브러리와 같은 것들을 Bean으로 만들 때** 사용합니다.
    4. @Controller
        - Spring MVC에서 Controller클래스에 쓰인다.
    5. @RequestHeader
        - Request의 header값을 가져올 수 있으며, 해당 Annotation을 쓴 메소드의 파라미터에 사용합니다.
            
            ![Untitled](Spring%20%E1%84%80%E1%85%AA%E1%84%86%E1%85%A9%E1%86%A8%E1%84%91%E1%85%A7%E1%86%BC%E1%84%80%E1%85%A1%20%E1%84%83%E1%85%A2%E1%84%87%E1%85%B5%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%205f9ac827ab0a4c5c8c781921bc159e34/Untitled%2060.png)
            
    6. @RequestMapping
        - 호출하는 클라이언트의 정보를 가져다가 서버에 전달해주는 매핑
        - 쉽게 말하자면 요청이 왔을 때 어떤 컨트롤러가 호출이 되어야 하는지 알려주는 지표 같은 것
        - value는 요청받을 url을 설정
        - method는 어떤 요청으로 받을지 정의하게 된다. (GET, POST, PUT, DELETE 등)
        
        ![Untitled](Spring%20%E1%84%80%E1%85%AA%E1%84%86%E1%85%A9%E1%86%A8%E1%84%91%E1%85%A7%E1%86%BC%E1%84%80%E1%85%A1%20%E1%84%83%E1%85%A2%E1%84%87%E1%85%B5%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%205f9ac827ab0a4c5c8c781921bc159e34/Untitled%2061.png)
        
        ![Untitled](Spring%20%E1%84%80%E1%85%AA%E1%84%86%E1%85%A9%E1%86%A8%E1%84%91%E1%85%A7%E1%86%BC%E1%84%80%E1%85%A1%20%E1%84%83%E1%85%A2%E1%84%87%E1%85%B5%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%205f9ac827ab0a4c5c8c781921bc159e34/Untitled%2062.png)
        
    - GetMapping, PostMapping은 RequestMapping의 자식 클래스이다.
    1. @RequestParam
        - 사용자가 전달하는 값을 1:1로 매핑해주는 어노테이션이다.
        - 보통 파라미터를 통해 값을 전달할 때 자주 사용
        1. 파라미터 이름을 지정하고 받기
            
            ![Untitled](Spring%20%E1%84%80%E1%85%AA%E1%84%86%E1%85%A9%E1%86%A8%E1%84%91%E1%85%A7%E1%86%BC%E1%84%80%E1%85%A1%20%E1%84%83%E1%85%A2%E1%84%87%E1%85%B5%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%205f9ac827ab0a4c5c8c781921bc159e34/Untitled%2063.png)
            
            ![Untitled](Spring%20%E1%84%80%E1%85%AA%E1%84%86%E1%85%A9%E1%86%A8%E1%84%91%E1%85%A7%E1%86%BC%E1%84%80%E1%85%A1%20%E1%84%83%E1%85%A2%E1%84%87%E1%85%B5%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%205f9ac827ab0a4c5c8c781921bc159e34/Untitled%2064.png)
            
        2. 파라미터 이름을 생략하고 받기
            
            ![Untitled](Spring%20%E1%84%80%E1%85%AA%E1%84%86%E1%85%A9%E1%86%A8%E1%84%91%E1%85%A7%E1%86%BC%E1%84%80%E1%85%A1%20%E1%84%83%E1%85%A2%E1%84%87%E1%85%B5%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%205f9ac827ab0a4c5c8c781921bc159e34/Untitled%2065.png)
            
            ![Untitled](Spring%20%E1%84%80%E1%85%AA%E1%84%86%E1%85%A9%E1%86%A8%E1%84%91%E1%85%A7%E1%86%BC%E1%84%80%E1%85%A1%20%E1%84%83%E1%85%A2%E1%84%87%E1%85%B5%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%205f9ac827ab0a4c5c8c781921bc159e34/Untitled%2066.png)
            
        
        2. required
        
        ![Untitled](Spring%20%E1%84%80%E1%85%AA%E1%84%86%E1%85%A9%E1%86%A8%E1%84%91%E1%85%A7%E1%86%BC%E1%84%80%E1%85%A1%20%E1%84%83%E1%85%A2%E1%84%87%E1%85%B5%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%205f9ac827ab0a4c5c8c781921bc159e34/Untitled%2067.png)
        
        ![Untitled](Spring%20%E1%84%80%E1%85%AA%E1%84%86%E1%85%A9%E1%86%A8%E1%84%91%E1%85%A7%E1%86%BC%E1%84%80%E1%85%A1%20%E1%84%83%E1%85%A2%E1%84%87%E1%85%B5%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%205f9ac827ab0a4c5c8c781921bc159e34/Untitled%2068.png)
        
        - 해당 파라미터가 필수요소인지를 지정해준다.
        - required = true가 default 값이다.
        - required = false인데 값이 들어오지 않았다면 null이 들어온다.
        1. defaultValue
            
            ![Untitled](Spring%20%E1%84%80%E1%85%AA%E1%84%86%E1%85%A9%E1%86%A8%E1%84%91%E1%85%A7%E1%86%BC%E1%84%80%E1%85%A1%20%E1%84%83%E1%85%A2%E1%84%87%E1%85%B5%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%205f9ac827ab0a4c5c8c781921bc159e34/Untitled%2069.png)
            
            ![Untitled](Spring%20%E1%84%80%E1%85%AA%E1%84%86%E1%85%A9%E1%86%A8%E1%84%91%E1%85%A7%E1%86%BC%E1%84%80%E1%85%A1%20%E1%84%83%E1%85%A2%E1%84%87%E1%85%B5%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%205f9ac827ab0a4c5c8c781921bc159e34/Untitled%2070.png)
            
            - 해당 파라미터가 비어있을 때, default 값을 지정해준다.
        - RequestParam Map으로 조회
            
            ![Untitled](Spring%20%E1%84%80%E1%85%AA%E1%84%86%E1%85%A9%E1%86%A8%E1%84%91%E1%85%A7%E1%86%BC%E1%84%80%E1%85%A1%20%E1%84%83%E1%85%A2%E1%84%87%E1%85%B5%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%205f9ac827ab0a4c5c8c781921bc159e34/Untitled%2071.png)
            
            ![Untitled](Spring%20%E1%84%80%E1%85%AA%E1%84%86%E1%85%A9%E1%86%A8%E1%84%91%E1%85%A7%E1%86%BC%E1%84%80%E1%85%A1%20%E1%84%83%E1%85%A2%E1%84%87%E1%85%B5%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%205f9ac827ab0a4c5c8c781921bc159e34/Untitled%2072.png)
            
    2. @RequestBody
        - Body에 전달되는 데이터를 메소드의 인자와 매칭시켜, 데이터를 받아서 처리할 수 있는 Annotation으로 아래와 같이 사용
        - 클라이언트가 보내는 HTTP 요청 본문(JSON 및 XML 등)을 Java 오브젝트로 변환
            
            ![Untitled](Spring%20%E1%84%80%E1%85%AA%E1%84%86%E1%85%A9%E1%86%A8%E1%84%91%E1%85%A7%E1%86%BC%E1%84%80%E1%85%A1%20%E1%84%83%E1%85%A2%E1%84%87%E1%85%B5%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%205f9ac827ab0a4c5c8c781921bc159e34/Untitled%2073.png)
            
            ![Untitled](Spring%20%E1%84%80%E1%85%AA%E1%84%86%E1%85%A9%E1%86%A8%E1%84%91%E1%85%A7%E1%86%BC%E1%84%80%E1%85%A1%20%E1%84%83%E1%85%A2%E1%84%87%E1%85%B5%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%205f9ac827ab0a4c5c8c781921bc159e34/Untitled%2074.png)
            
        1. @ModelAttribute
            - 클라이언트가 전송하는 HTTP parameter, Body 내용을 Setter 함수를 통해 1:1로 객체에 데이터를 연결(바인딩) 합니다.
            - RequestBody와 다르게 HTTP Body 내용은 multipart/form-data 형해로 요구
            - @RequestBody가 json을 받는 것과 달리 @ModenAttribute 의 경우에는 json을 받아 처리할 수 없습니다.
        2. @Autowired
            - Bean을 주입 받기 위하여 사용
                - @Autowired
                - 생성자 (@AllArgsConstructor 사용)
                - setter
        3. @SpringBootApplication
            - @Configuration, @EnableAutoConfiguration, @ComponentScan 3가지를 하나의 애노테이션으로 합친 것 이다.
- MyBatis
    
    ## 개념
    
    - Java Object와 SQL문 사이의 자동 Mapping 기능을 지원 하는 ORM Framework
    
    ## 특징
    
    1. 쉬운 접근성과 코드의 간결함
        - 가장 간단한 persistence(**유재광**) framework
        - XML형태로 서술된 JDBC 코드라 생각해도 될 만큼 JDBC의 모든 기능을 MyBatis가 대부분 제공
        - 복잡한 JDBC 코드를 걷어내며 깔끔한 소스코드를 유지
        - 수동적인 parameter 설정과 Query결과에 대한 mapping 구문을 제거
    2. SQL문과 프로그래밍 코드의 분리
        - SQL에 변경이 있을 때마다 자바 코드를 수정하거나 컴파일 하지 않아도 됨
        - SQL 작성과 관리 또는 검토를 DBA와 같은 개발자가 아닌 다른 사람에게 맡길 수 있음
    3. 다양한 프로그래밍 언어로 구현가능.
    
    ## MyBatis와 MyBatis-Spring의 주요 Component
    
    ![Untitled](Spring%20%E1%84%80%E1%85%AA%E1%84%86%E1%85%A9%E1%86%A8%E1%84%91%E1%85%A7%E1%86%BC%E1%84%80%E1%85%A1%20%E1%84%83%E1%85%A2%E1%84%87%E1%85%B5%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%205f9ac827ab0a4c5c8c781921bc159e34/Untitled%2075.png)
    
    ![Untitled](Spring%20%E1%84%80%E1%85%AA%E1%84%86%E1%85%A9%E1%86%A8%E1%84%91%E1%85%A7%E1%86%BC%E1%84%80%E1%85%A1%20%E1%84%83%E1%85%A2%E1%84%87%E1%85%B5%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%205f9ac827ab0a4c5c8c781921bc159e34/Untitled%2076.png)
    
    ## mapper.xml에서 사용하는 태그 (동적쿼리 관련 포함)
    
    1. **mapper**: 이 태그는 매퍼 인터페이스와 연결되는 XML 문서의 루트 요소입니다. 일반적으로 **`<mapper namespace="com.example.MyMapper">`**와 같은 형태로 사용됩니다.
    2. **select**: 데이터베이스에서 데이터를 조회하는 데 사용됩니다. 주로 쿼리문이 포함됩니다. 예를 들어, 
    **`<select id="selectById" parameterType="int" resultType="com.example.User">
    SELECT * FROM users WHERE id = #{id}
    </select>`**
    와 같이 사용될 수 있습니다.
    3. **insert**: 데이터베이스에 새로운 데이터를 삽입하는 데 사용됩니다. 주로 삽입할 데이터와 관련된 매개변수가 포함됩니다.
    4. **update**: 데이터베이스에서 기존 데이터를 업데이트하는 데 사용됩니다. 주로 업데이트할 데이터와 관련된 매개변수가 포함됩니다.
    5. **delete**: 데이터베이스에서 데이터를 삭제하는 데 사용됩니다. 주로 삭제할 데이터와 관련된 매개변수가 포함됩니다.
    6. **resultMap**: 결과 집합의 열을 자바 객체의 프로퍼티에 매핑하는 데 사용됩니다.
    7. **parameterMap**: SQL 쿼리에 전달되는 매개변수를 정의하는 데 사용됩니다. 하지만 이제는 사용이 권장되지 않습니다. 대신 **`parameterType`**을 사용하는 것이 좋습니다.
    8. **if, choose, when, otherwise**: 동적 SQL 쿼리를 작성하는 데 사용됩니다. 예를 들어, **`if`** 태그를 사용하여 조건에 따라 쿼리 부분을 추가하거나 생략할 수 있습니다.
    9. **foreach**: 컬렉션을 반복하여 동적인 IN 절을 생성하는 데 사용됩니다. 주로 여러 값을 IN 연산자와 함께 사용하는 쿼리에서 유용합니다.
    10. **sql**: 재사용 가능한 SQL 조각을 정의하는 데 사용됩니다. 주로 공통적으로 사용되는 SQL 조각을 한 번 정의하고 여러 쿼리에서 재사용할 때 유용합니다.
- 환경 설정
    
    ## 빈등록 xml에 빈등록하는 형태 생성자 주입, setter 주입
    
    1. 등록하기
        
        ```xml
        <beans xmlns="http://www.springframework.org/schema/beans"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
        
        </beans>
        ```
        
        - 기본 형식인 bean은 이와 같은 모습
    2. spring binz 등록하기
        
        ```xml
        <beans xmlns="http://www.springframework.org/schema/beans"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
        
          <!-- Exam exam = new Exam();  -->
          <bean id="exam" class="spring.di.entity.Exam"/>
        
          <!-- ExamConsole console = new InlineExamConsole();  -->
          <bean id="console" class="spring.di.ui.InlineExamConsole"/>
        </beans>
        ```
        
    - bean 태그로 id 값과 class 값을 적어준다.
        - id는 xml파일 내부에서 참조변수명과 같이 사용
        - class는 생성할 객체의 클래스명을 적으면 됨
        - 정확하게 구분하기 위해 패키지명까지 적어주어야 함.
    1. 생성자로 의존성 주입하기
        
        ```xml
        <beans xmlns="http://www.springframework.org/schema/beans"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
        
          <!-- Exam exam = new Exam();  -->
          <bean id="exam" class="spring.di.entity.Exam">
            <!-- 오버로드 생성자를 통해 주입 -->
            <constructor-arg value="30"/>
            <constructor-arg value="40"/>
            <constructor-arg value="20"/>
            <constructor-arg value="20"/>
          </bean> 
        
          <!-- ExamConsole console = new InlinExamConsole();  -->
          <bean id="console" class="xml.spring.di.ui.InlineExamConsole">
            <!-- 오버로드 생성자를 통해 주입 -->
            <constructor-arg name="exam" ref="exam" />
          </bean>
        </beans>
        ```
        
        - bean 파일의 닫는 태그를 분리하여 내부에 constructor-arg 태그를 생성
        - constructor-arg는 오버로드 생성자의 매개변수를 지정한다.
        - constructor-arg 1개당 매개변수 1개를 의미한다.
        - constructor-arg 의 속성으로 name, index, value, ref 등이 있다.
            - name : 매개변수명으로 연결 (기존의 변수명과 동일해야함)
            - index : 매개변수의 순서, 앞에서 부터 0,1,2..
            - value : 일반 자료형이 들어갈 경우 value 속성으로 값을 입력 받는다.
            - ref : 참조 자료형을 받을 경우 ref 속성으로 값을 입력 받는다.
    2. setter로 주입하기
        
        ```xml
        <bean id="console" class="xml.spring.di.ui.ExamConsole">
        	<property name="exam" ref="exam"/>
        </bean>
        ```
        
        - property 태그는 setter 메서드와 동일하다.
        - 실제 객체에 해당하는 값을 받는 setter가 정의 되어 있어야 한다.
        - setter 메서드명이 setExam과 같은 형식이라면 앞에 set을 제외하고 앞의 문자를 소문자로 바꾼다.
        - 받는 타입은 생성자와 똑같이 기본 자료형일 경우 value, 참조형일 경우 ref로 받으면 된다.
    3. xml 불러오기
        
        ```jsx
        public class Test
        {
          public static void main(String[] args)
          {
            ApplicationContext context = new ClassPathXmlApplicationContext("spring/di/setting1.xml");
        
            // bean의 id 값으로 참조 (타입캐스팅 해주어야함)
            ExamConsole console = (ExamConsole) context.getBean("console");
        
            // 위의 방법 또는 클래스타입으로 받을 수 있다.
            ExamConsole console = context.getBean(ExamConsole.class);
            console.print();
          }
        }
        ```
        
    
    ## web.xml,root-context.xml,servlet-context.xml
    
    1. web.xml
        - 웹 어플리 케이션 서버(WAS)가 최초로 구동 될 때(톰캣이 최초로 구동이 될 때) 각종 설정을 정의 합니다. 이 때 파일 내에서 여러 xml파일을 인식 할 수 있도록 설정 되어 있으며, 설정을 위한 설정 파일 이다.
            
            ```xml
            <!-- The definition of the Root Spring Container shared by all Servlets and Filters -->
            <context-param>
            	<param-name>contextConfigLocation</param-name>
            	<param-value>/WEB-INF/spring/root-context.xml</param-value>
            </context-param>
            
            <!-- Creates the Spring Container shared by all Servlets and Filters -->
            <listener>
            	<listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
            </listener>
            
            <!-- Processes application requests -->
            <servlet>
            	<servlet-name>appServlet</servlet-name>
            	<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
            	<init-param>
            		<param-name>contextConfigLocation</param-name>
            		<param-value>/WEB-INF/spring/appServlet/servlet-context.xml</param-value>
            	</init-param>
            	<load-on-startup>1</load-on-startup>
            </servlet>
            ```
            
        - context-param 태그 내에 root-context로 모든 서블릿과 필터들이 공유함으로 root-context.xml을 정의
        - Listener 태그로 모든 서블릿과 필터들에 공유되는 스프링 컨테이너를 생성
        - 다시 말해 root-context에 정의되어 있는 것들을 모든 서블릿과 필터가 공유 할 수 있게 해줌
    2. servlet-context.xml
        - 웹 어플리케이션에서 클라이언트의 요청을 받기 위한 컨텍스트 설정이다
        - 요청과 관련된 객체를 정의함
        - url과 관련된 Controller나, 어노테이션, ViewResolver(컨트롤러에서 view 정보에 대해 설정하는 것), Interceptor, MultipartResolver 등의 설정을 해줍니다.
        
        ![Untitled](Spring%20%E1%84%80%E1%85%AA%E1%84%86%E1%85%A9%E1%86%A8%E1%84%91%E1%85%A7%E1%86%BC%E1%84%80%E1%85%A1%20%E1%84%83%E1%85%A2%E1%84%87%E1%85%B5%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%205f9ac827ab0a4c5c8c781921bc159e34/Untitled%2077.png)
        
    - servlet-context 파일을 보면 위처럼 주석을 볼수 있는다
    - 해석하면 “DipatcherServlet Context : 이 서블릿의 요청처리 인프라를 정의한다” 라고 해석
    - 이는 DispatcherServlet과 관련된 설정을 하는것임을 의미
    1. root-context.xml
        - servlet-context와는 반대로 view와 관련되지 않은 객체를 정의함
        - Service, Repository(DAO), DB등 비즈니스 로직과 관련된 설정을 함
            
            ![Untitled](Spring%20%E1%84%80%E1%85%AA%E1%84%86%E1%85%A9%E1%86%A8%E1%84%91%E1%85%A7%E1%86%BC%E1%84%80%E1%85%A1%20%E1%84%83%E1%85%A2%E1%84%87%E1%85%B5%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%205f9ac827ab0a4c5c8c781921bc159e34/Untitled%2078.png)
            
            - 위의 자료에서 다수의 서블릿을 가지게 되는 경우 다수의 servlet-context가 root-context의 bean정보들을 참조하는 구조가 될 수 있다.
    
    ## Spring boot에서의 환경설정 application properties
    
    - 라이브 배포 파일
        
        ```xml
        # Web ContextRootPath and PortNumber Settings
        #server.servlet.context-path=/board
        server.port=80
        
        # JSP Path (ViewResolver)
        spring.mvc.view.prefix=/WEB-INF/views/
        spring.mvc.view.suffix=.jsp
        
        # DataBase Settings : hikariCP : https://github.com/brettwooldridge/HikariCP
        spring.datasource.hikari.driver-class-name=com.mysql.cj.jdbc.Driver
        spring.datasource.hikari.jdbc-url=jdbc:mysql://localhost:3306/ssafyweb?serverTimezone=UTC&useUniCode=yes&characterEncoding=UTF-8
        spring.datasource.hikari.username=ssafy
        spring.datasource.hikari.password=ssafy
        spring.datasource.hikari.pool-name=hikari-pool
        
        # hikariCP property setting
        spring.datasource.hikari.maximum-pool-size=50
        spring.datasource.hikari.minimum-idle=50
        spring.datasource.hikari.connection-timeout=5000
        spring.datasource.hikari.connection-init-sql=SELECT 1
        spring.datasource.hikari.idle-timeout=600000
        spring.datasource.hikari.max-lifetime=1800000
        spring.datasource.hikari.auto-commit=true
        
        #spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
        #spring.datasource.url=jdbc:mysql://localhost:3306/ssafyweb?serverTimezone=UTC&useUniCode=yes&characterEncoding=UTF-8
        #spring.datasource.username=ssafy
        #spring.datasource.password=ssafy
        #spring.datasource.type=org.apache.commons.dbcp2.BasicDataSource
        
        #MyBatis Setting
        mybatis.type-aliases-package=com.ssafy.*.model
        mybatis.mapper-locations=mapper/**/*.xml
        
        # file upload path : window
        file.path=C:/board/upload/
        file.path.upload-images=C:/board/upload/imageUpload
        file.path.upload-files=C:/board/upload/fileUpload/
        #spring.servlet.multipart.location=C:/board/upload/
        
        # file upload path : mac
        #file.path=/Users/hissam/SSAFY/workspace/Framework/board6-springboot/upload
        #file.path.upload-images=/Users/hissam/SSAFY/workspace/Framework/board6-springboot/upload/image
        #file.path.upload-files=/Users/hissam/SSAFY/workspace/Framework/board6-springboot/upload/file
        #spring.servlet.multipart.location=C:/board/upload/
        
        #File Upload size Setting
        spring.servlet.multipart.max-file-size=25MB
        spring.servlet.multipart.max-request-size=25MB
        
        #log level Setting
        logging.level.root=info
        logging.level.com.ssafy=debug
        logging.level.com.zaxxer.hikari=debug
        ```
        
    - **application.properties 란?**
        - **Spring Boot 애플리케이션의 구성 속성을 설정하는 데 사용되는 파일입니다.**
        - 이 파일은 애플리케이션의 클래스 경로에 위치하며, 키-값 쌍의 형식으로 구성됩니다. 이 파일을 사용하면 애플리케이션의 동작을 구성할 수 있습니다.
        - 예를 들어, 데이터베이스 연결 정보, 로깅 설정, 서버 포트, 보안 구성 등을 지정할 수 있습니다.
- 서술형
    
    ## 1. REST서비스란?
    
    - data 처리만 한다 거나, 처리 후 반환될 data가 있다면 JSON이나 XML 형식으로 전달. View에 대해서는 신경 쓸 필요가 없다. ⇒ 이러한 이유로 Open API에서 많이 사용.
    - 기존 서비스는 : 요청에 대한 처리를 한 후 가공된 data를 이용하여 특정 플랫폼에 적합한 형태의 view로 만들어서 반환.
    
    ## 2.Spring MVC 요청 처리 과정
    
    ![Untitled](Spring%20%E1%84%80%E1%85%AA%E1%84%86%E1%85%A9%E1%86%A8%E1%84%91%E1%85%A7%E1%86%BC%E1%84%80%E1%85%A1%20%E1%84%83%E1%85%A2%E1%84%87%E1%85%B5%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%205f9ac827ab0a4c5c8c781921bc159e34/Untitled%2079.png)
    
    1. 클라이언트가 웹 브라우저에서 HTTP 요청을 전송
    2. DispatcherServlet 이 이 요청을 받아들임
    3. DispatcherServlet은 요청 처리를 위해 HandlerMapping에게 요청을 전달
    4. HandlerMapping은 요청 URL과 일치하는 컨트롤러(Controller)를 찾아서 DispatcherServlet에게 반환
    5. DispatcherServlet은 HandlerAdapter에게 Controller 호출을 위임
    6. HandlerAdapter는 Controller를 호출해서 Model 데이터와 View 이름을 반환
    7. DispatcherServlet 은 ViewResolver에게 view 이름을 전달하며 검색 요청
    8. ViewResolver는 View 이름을 실제 View 객체로 변환
    9. DispatcherServlet 은 View를 실행하여 결과를 클라이언트에게 반환