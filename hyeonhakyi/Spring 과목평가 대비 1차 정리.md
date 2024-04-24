# Spring 과목평가 대비 정리
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
            
       
        - xml
            
          
        - 빈의 생성 범위 지정
            
            
            
    - 스프링 빈 설정
        - 스프링 빈 설정 메타정보
    
            
    - 스프링 빈 설정 : XML
        - XML 문서
            - XML문서 형태의 빈의 설정 메타 정보를 기술
            - 단순하며 사용하기 쉬움
            - <bean> 태그를 통해 세밀한 제어 가능
     
                
    - 스프링 빈 설정 : Annotation
        - Annotation
            - 어플리케이션의 규모가 커지고 빈의 개수가 많아질 경우 XML 파일을 관리하는 것이 번거러움
            - 빈으로 사용될 클래스에 특별한 annotation(@autowired)을 부여해 주면 자동으로 빈 등록 가능
            - “오브젝트 빈 스캐너”로 “빈 스캐닝”을 통해 자동 등록
    
                
            - Annotation으로 빈을 설정 할 경우 반드시 component-scan을 설정 해야 한다.
                
       
                
    - 스프링 빈 설정 : Java Configuration File
        - Annotation 기반 : @Configuration 적용
       
            
    - 스프링 빈 설정 : Annotation
        - Sterotype annotation 종류
            - 빈 자동등록에 사용할 수 있는 annotation
            - 빈 자동인식을 위한 annotation이 여러가지인 이유
                - 계층별로 빈의 특성이나 종류를 구분
                - AOP Pointcut 표현식을 사용하면 특정 annotation이 달린 클래스만 설정 가능.
                - 특정 계층의 빈에 부가기능을 부여
                
             
                
    - 스프링 설정 : xml
        - XML 문서 이용
            - Application에서 사용할 Spring 자원들을 설정하는 파일
            - Spring Contatiner는 설정파일에 설정된 내용을 읽어 Application에서 필요한 기능들을 제공
            - Root tag는 <beans>
            - 파일명은 상관없다.
                
            
                
        - 기본 설정 - 빈 객체 생성 및 주입
            - 주입 할 객체를 설정 파일에 설정
                - <bean> : 스프링 컨테이너가 관리할 Bean객체를 설정
            - 기본 속성
                - name : 주입 받을 곳에서 호출 할 이름 설정
                - id : 주입 받을 곳에서 호출 할 이름 설정(유일 값)
                - class : 주입 할 객체의 클래스
                - factory-method : Singleton 패턴으로 작성된 객체의 factory 메소드 호출
                
        
        - 기본 설정 - 빈 객체 얻기
            - 설정 파일에 설정한 bean을 Container가 제공하는 주입기 역할의 api를 통해 주입 받는다.
        
                
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
                        
              
          
                
    - DI - Annotation
        - 스프링 빈 의존 관계설정 - annotation
            - Annotation : 멤버변수에 직접 정의 하는 경우 setter method를 만들지 않아도 됨.
                
         
                
            - 특정 Bean의 기능 수행을 위해 다른 Bean을 참조해야 하는 경우 사용한다.
        - 기본설정 - 빈 객체 얻기
            - 설정한 component-scan에 의해 scanning에 의해 scanning 된 bean을 Container가 제공하는 주입기 역할의 api를 통해 주입 받는다.
            - bean 파일에는 @Component 설정
                
               
                
        - 필드에 @Autowired
            - 동일한 타입의 bean이 여러 개일 경우에는 @Qualifier(”name”)으로 식별한다.
                
              
        - 생성자에 @Autowired
            - immutable:의존성이 필요한 filed를 final로 선언 가능
            - 순환 참조 방지
            - 생성자가 1개일 경우 @Autowired 생략 가능
            - 동일한 타입의 bean이 여러 개일 경우에는 @Qualifier(”name”)으로 식별한다.
            
                
        - set method에 @Autowired
            - 동일한 타입의 bean이 여러 개일 경우에는 @Qualifier(”name”)으로 식별한다.
                
              
                
    - 기타 설정
        - 빈 객체의 생성단위
            - BeanFactory를 통해 Bean을 요청 시 객체생성의 범위(단위)를 설정
            - <bean>의 scope 속성을 이용해 설정
            - scope의 값
                
             
                
        - Factory Method로부터 빈(bean) 생성
            - getBean()으로 호출 시 private 생성자도 호출 하여 객체를 생성한다.
            - 그러므로 위의 상황에서 fatory method만 호출 해야 객체를 얻을 수 있는 것은 아니다.
                
             