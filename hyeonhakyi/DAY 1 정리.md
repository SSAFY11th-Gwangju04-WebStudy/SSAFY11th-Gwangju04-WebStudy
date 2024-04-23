# DAY 1

- Spring? & 개발환경
    - framework
        - 프로그래밍에서 특정 운영 체제를 위한 응용 프로그램 표준 구조를 구현하는 클래스와 라이브러리 모임이다.
    - SpringFramework의 구조
        - Spring 삼각형
            - Enterprise Application 개발 시 복잡함을 해결하는 Spring의 핵심
                1. POJO (Plain Old Java Object) 
                    - 특정 환경이나 기술에 종속적이지 않은 객체 지향 원리에 충실한 자바 객체
                    - 테스트하기 용이하며, 객체 지향 설계를 자유롭게 적용할 수 있다.
                2. PSA (Portable Service Abstraction)
                    - 환경과 세부기술의 변경과 관계없이 일관된 방식으로 기술에 접근할 수 있게 해 주는 설계 원칙
                    - 트랜잭션 추상화, OXM 추상화, 데이터 액세스의 Exception 변환 기능…등 기술적인 복잡함은 추상화를 통해 Low Level의 기술 구현 부분과 기술을 사용하는 인터페이스로 분리.
                    - 예를 들어 데이터베이스에 관계없이 동일하게 적용 할 수 있는 트랜잭션 처리방식
                3. IoC/DI
                    - DI는 유연하게 확장 가능한 객체를 만들어 두고 객체 간의 의존관계는 외부에서 다이나믹하게 설정.
                4. AOP
                    - 관심사의 분리를 통해서 소프트웨어의 모듈성을 향상
                    - 공통 모듈을 여러 코드에 쉽게 적용 가능
                    
          
                    
    - SpringFramework의 특징
        - 경량컨테이너
            - 스프링은 자바 객체를 담고 있는 컨테이너이다.
            - 스프링 컨테이너는 이들 자바 객체의 생성과 소멸과 같은 라이프 사이클을 관리.
            - 언제든지 스프링 컨테이너로부터 필요한 객체를 가져와 사용할 수 있다.
        - DI(Dependency Injection - 의존성 지원) 패턴 지원
            - 스프링은 설정 파일이나, 어노테이션을 통해서 객체 간의 의존 관계를 설정할 수 있다.
            - 따라서, 객체는 의존하고 있는 객체를 직접 생성하거나 검색할 필요가 없다.
        - AOP(Aspect Orlented Programmin - 관점 지향 프로그래밍) 지원
            - AOP는 문제를 바라보는 관점을 기준으로 프로그래밍하는 기법이다.
            - 이는 문제를 해결하기 위한 핵심 관심 사항과 전체에 적용되는 공통 관심 사항을 기준으로 프로그래밍 함으로써 공통 모듈을 여러 코드에 쉽게 적용할 수 있도록 한다.
            - 스프링은 자체적으로 프록시 기반의 AOP를 지원하므로 트랜잭션이나, 로깅, 보안과 같이 여러 모듈에서 공통으로 필요로 하지만 실제 모듈의 핵심이 아닌 기능들을 분리하여 각 모듈에 적용이 가능하다.
        - POJO(Plain Old Java Object) 지원
            - 특정 인터페이스를 구현하거나 또는 클래스를 상속하지 않는 일반 자바 객체 지원
            - 스프링 컨테이너에 저장되는 자바 객체는 특정한 인터페이스를 구현 하거나, 클래스 상속 없이도 사용이 가능하다.
            - 일반적인 자바 객체를 칭하기 위한 별칭 개념이다.
        - IoC(Inversion of Control - 제어의 반전)
            - IoC는 스프링이 갖고 있는 핵심적인 기능이다.
            - 자바의 객체 생성 및 의존 관계에 있어 모든 제어권은 개발자에게 있었다.
            - Servlet과 EJB가 나타나면서 기존의 제어권이 Servlet Container 및 EJB Container에게 넘어가게 됐다.
            - 단, 모든 객체의 제어권이 넘어간 것은 아니고 Servlet, EJB에 대한 제어권을 제외한 나머지 객체 제어권은 개발자들이 담당하고 있다.
            - 스프링에서도 객체에 대한 생성과 생명 주기를 관리할 수 있는 기능을 제공하고 있는데 이런 이유로 [Spring Container] 또는 [IoC Contianer] 라고 부르기도 한다.
        - 스프링은 트랜잭션 처리를 위한 일관된 방법을 제공
            - JDBC, JTA 또는 컨테이너가 제공하는 트랜잭션을 사용하든, 설정 파일을 통해 트랜잭션 관련정보를 입력하기 때문에 트랜잭션 구현에 상관 없이 동일한 코드를 여러 환경에서 사용이 가능하다.
        - 스프링은 영속성과 관련된 다양한 API를 지원
            - 스프링은 JDBC를 비롯하여 iBatis,myBatis,Hibernate,JAP등 DB처리를 위해 널리 사용되는 라이브러리와 연동을 지원하고 있다.
        - 스프링은 다양한 API에 대한 연동을 지원
            - 스프링은 JMS, 메일, 스케쥴링 등 엔터프라이즈 어플리케이션 개발에 필요한 다양한 API를 설정 파일과 어노테이션을 통해서 손쉽게 사용할 수 있도록 지원하고 있다.
- IoC & Container
    - IoC(Inversion of Control, 제어의 역행)
        - Ioc/DI
        - 객체지향 언어에서 Object간의 연결 관계를 런타임에 결정
        - 객체 간의 관계가 느슨하게 연결됨(loose coupling)
        - IoC의 구현 방법 중 하나가 DI(Dependency Injection)
    - IoC 유형
        

    - Container란?
        - 객체의 생성, 사용, 소멸에 해당하는 라이프 사이클을 담당
        - 라이프 사이클을 기본으로 애플리케이션 사용에 필요한 주요 기능을 제공
    - Container 기능
        - 라이프 사이클 관리
        - Dependency 객체 제공
        - Thread 관리
        - 기타 애플리케이션 실행에 필요한 환경
    - Container 필요성
        - 비즈니스 로직 외에 부가적인 기능들에 대해서는 독립적으로 관리되도록 하기 위함.
        - 서비스 look up 이나 Configuration에 대한 일관성을 갖기 위함
        - 서비스 객체를 사용하기 위해 각각 Factory 또는 Singleton 패턴을 직접 구현하지 않아도 됨
    - IoC Container
        - 오브젝트의 생성과 관계설정, 사용, 제거 등의 작업을 애플리케이션 코드 대신 독립된 컨테이너가 담당.
        - 컨테이너가 코드 대신 오브젝트에 대한 제어권을 갖고 있어 IoC라고 부름.
        - 이런 이유로, 스프링 컨테이너를 IoC컨테이너라고 부리기도 함.
        - 스프링에서 IoC를 담당하는 컨테이너에는 BeanFactory, ApplicationContext가 있음
    - Spring DI Container
        - Spring DI Container가 관리하는 객체를 Bean(빈)이라 하고,이 빈들의 생명주기를 관리하는 의미로 빈팩토리라 한다.
        - Bean Factory에 여러 가지 컨테이너 기능을 추가하여 ApplicationContext라 한다.

            
        - 빈 팩토리
            - Bean을 등록, 생성, 조회, 반환 관리
            - 일반적으로 BeanFactory보다는 이를 확장한 ApplicationContext를 사용.
            - getBean() method가 정의되어 있음
        - ApplicationContext
            - Bean을 등록, 생성, 조회, 반환 관리 기능은 BeanFactory와 같음
            - Spring의 각종 부가 서비스를 추가로 제공
            - Spring이 제공하는 ApplicationContext 구현클래스는 여러가지 종류가 있음.
    - IoC 개념
        - 객체간 강한 결합
            - 클래스 호출 방식
            - 클래스내에 선언과 구현이 모두 되어 있기 때문에 다양한 형태로 변화가 불가능
            - Class의 세부사항이 변경되면 다른 Class도 변경 해야 함 (의존관계 높음)
     
        - Ex
            - HelloMessageKor 또는 HelloMessageEng의 객체를 HelloMain에서 직접 생성하여 사용
            - HelloMessageKor가 HelloMessageEng로 교체되거나 내부 코드가 변경되면 HelloMain까지 수정해야 할 가능성이 있음.
                

                
        - 객체 간의 강한 결합을 다형성을 통해 결합도를 낮춤
            - 인터페이스 호출 방식
            - HelloMessage interface를 이용하여 HelloMessageKor 또는 HelloMessageEng 클래스와 HelloMain 클래스간의 Business Logic에 관련된 직접적인 의존성을 제거
            - 구현 클래스 교체가 용이하여 다양한 형태로 변화가능. (다형성)
            - 하지만 인터페이스 교체 시 호출 클래스도 수정해야함. (객체 생성관계 의존성은 여전히 존재)
 
        - 객체 간의 강한 결합을 Factory를 통해 결합도를 낮춤
            - 팩토리 호출 방식
            - 팩토리 방식은 팩토리가 구현 클래스를 생성하므로 클래스는 팩토리를 호출
            - 인터페이스 변경 시 팩토리만 수정하면 됨. 호출 클래스에는 영향을 미치지 않음
            - 하지만 클래스에 팩토리를 호출하는 소스가 들어가야함. 그것 자체가 팩토리에 의존함을 의미한다.
                
 
        - 객체 간의 강한 결합을 Assembler를 통해 결합도를 낮춤
            - IoC호출 방식
            - 팩토리 패턴의 장점을 더하여 어떠한 것에도 의존하지 않는 형태가 됨.
            - 실행시점에 클래스 간의 관계가 형성이 됨.
     
                
        - Spring DI 용어 정리
            - 빈(Bean)
                - 스프링이 IoC방식으로 관리하는 오브젝트를 말한다.
                - 스프링이 직접 그 생성과 제어를 담당하는 오브젝트만을 Bean이라고 부른다.
                - POJO(Plain Old Java Object)로 정의
            - 빈 팩토리(BeanFactory)
                - 스프링이 IoC를 담당하는 핵심 컨테이너
                - Bean을 등록, 생성, 조회, 반환하는 기능을 담당.
                - 일반적으로 BeanFactory를 바로 사용하지 않고 이를 확장한 ApplicationContext를 이용한다.
            - 애플리케이션 컨텍스트(ApplicationContext)
                - BeanFactory를 확장한 IoC컨테이너이다.
                - Bean을 등록하고 관리하는 기본적인 기능은 BeanFactory와 동일하다.
                - 스프링이 제공하는 각종 부가 서비스를 추가로 제공한다.
                - BeanFactory라고 부를 때는 주로 빈의 생성과 제어의 관점에서 이야기하는 것이고, 애플리케이션 컨텍스트라고 할 때는 스프링이 제공하는 애플리케이션 지원 기능을 모두 포함해서 이야기하는 것이라고 보면 된다.
                 
- 의존성주입 (Dependency Injection)
    - Dependency Injection
        - 객체 간의 의존관계를 자신이 아닌 외부의 조립기가 수행
        - 제어의 역행(inversion of Control,IoC)이라는 의미로 사용
        - DI를 통해 시스템에 있는 각 객체를 조정하는 외부 개체가 객체들에게 생성시에 의존관계를 주어짐
        - 느슨한 결합(loose coupling)의 주요강점
            - 객체는 인터페이스에 의한 의존 관계만을 알고 있으며, 이 의존 관계는 구현 클래스에 대한 차이를 모르는 채 서로 다른 구현으로 대체가 가능.
    - 빈 생성범위
        - 싱글톤 빈(Singleton Bean)
            - 스프링 빈은 기본적으로 ‘singleton’으로 만들어짐
            - 그러므로, 컨테이너가 제공하는 모든 빈의 인스턴스는 항상 동일함
            - 컨테이너가 항상 새로운 인스턴스를 반환하게 만들고 싶을 경우 scope를 ‘prototype’으로 설정해야 함.
                
        
                
    - 스프링 빈 설정 : XML
        - XML 문서
            - XML문서 형태로 빈의 설정 메타 정보를 기술
            - 단순하며 사용하기 쉬움
            - <bean>태그를 통해 세밀한 제어 가능
                
     
                
    - 스프링 빈 설정 : Annotation
        - Annotation
            - 어플리케이션의 규모가 커지고 빈의 개수가 많아질 경우 XML 파일을 관리하는 것이 번거로움
            - 빈으로 사용될 클래스에 특별한 annotation (@autowired)을 부여해 주면 자동으로 빈 등록 가능
            - “오브젝트 빈 스캐너”로 “빈 스캐닝”을 통해 자동 등록
                - 빈 스캐너는 기본적으로 클래스 이름을 빈의 아이디로 사용 (클래스 이름의 첫 글자만 소문자로 바꾼 것)
                
   
                
            - Annotation으로 빈을 설정 할 경우 반드시 component-scan을 설정 해야 한다.

                
            - Stereotype annotation 종류
                - 빈 자동등록에 사용할 수 있는 annotation
                - 빈 자동인식을 위한 annotation이 여러가지인 이유
                    - 계층별로 빈의 특성이나 종류를 구분
                    - AOP Poincut표현식을 사용하면 특정 annotation이 달린 클래스만 설정 가능
                    - 특정 계층의 빈에 부가기능을 부여
   
- DI - XML
    - Spring 설정 : xml
        - 기본설정 - 빈 객체 생성 및 주입
            - 주입 할 객체를 설정파일에 설정
                - <bean> : 스프링 컨테이너가 관리할 Bean객체를 설정
            - 기본 속성
                - name : 주입 받을 곳에서 호출 할 이름 설정
                - id : 주입 받을 곳에서 호출 할 이름 설정(유일 값)
                - class : 주입 할 객체의 클래스
                - factory - method : Singleton 패턴으로 작성된 객체의 factory 메소드 호출
                
         
        - 기본 설정 - 빈 객체 얻기
            - 설정 파일에 설정한 bean을 Container가 제공하는 주입기 역할의 api를 통해 주입 받는다.
                
          
        - Constructor injection
            - 객체 또는 값을 생성자를 통해 주입 받는다
            - <constructor-arg> : <bean>의 하위태그로 설정한 bean 객체 또는 값을 생성자를 통해 주입하도록 설정
            - 설정 방법 : <ref> , <value>와 같은 하위태그를 이용하여 설정 하거나 또는 속성을 이용하여 설정
                1. 하위태그 이용
                    1. 객체 주입 시 : <ref bean = “bean name”/>
                    2. 문자열(String), primitive data 주입 시 : <value>값</value>
                        
                        *type 속성 : 값은 기본적으로 String으로 처리 값의 타입을 명시해야 하는 경우 사용 ex) <value type = “int”> 10 </value>
                        
                2. 속성 이용
                    1. 객체 주입 시 : <constructor-arg ref = “bean name”/>
                    2. 문자열(String), primitive data 주입 시 : <constructor-arg value=”값”/>
           
        - Property injection
            - 하위태그 이용
                - <ref bean=”bean name”/> - 객체 주입 시
                - <value>값</value> - 문자열(String),primitive data  주입 시
            - 속성 이용
                - <property name = “property name” ref = “bean name”/>
                - <property name = “property name” value = “값”/>
     
- DI - Annotation
    - Annotation : 멤버변수에 직접 정의 하는 경우 setter method를 만들지 않아도 됨
        

        
    - 특정 Bean의 기능 수행을 위해 다른 Bean을 참조해야 하는 경우 사용한다.
        - @Autowired
            - Spring Framework에서 지원하는 Dependency정의 용도의 Annotation으로, Spring Framework에 종속적이긴 하지만 정밀한 Dependency Injection이 필요한 경우에 유용하다.
    - 기본 설정 - 빈 객체 얻기
        - 설정한 component-scan에 의해 scanning 된 bean을 Container가 제공하는 주입기 역할의 api를 통해 주입 받는다.
        - bean 파일에는 @Component 설정
            
            
    - 필드에 @Autowired
        - 동일한 타입의 bean이 여러 개일 경우에는 @Qualifier(”name”)으로 식별한다.
            
    
    - 생성자에 @Autowired
        - immutable : 의존성이 필요한 field를 final로 선언 가능
        - 순환 참조 방지
        - 생성자가 1개일 경우 @Autowired 생략 가능
        - 동일한 타입의 bean이 여러 개일 경우에는 @Qualifier(”name”)으로 식별한다.
    
    - set method에 @Autowired
        
   