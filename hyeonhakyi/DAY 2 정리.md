# DAY 2

- 관점 지향 프로그래밍(AOP)
    - 핵심 관심 사항(core concern)과 공통 관심 사항(cross-cutting concern)
    - 기존 OOP에서는 공통관심사항을 여러 모듈에서 적용하는데 있어 중복된 코드를 양상 하는 한계가 존재함
    - 이를 해결하기 위해 AOP가 등장
    - Aspect Oriented Programming은 문제를 해결하기 위한 핵심 관심 사항과 전체에 적용되는 공통 관심 사항을 기준으로 프로그래밍함으로써 공통 모듈을 손쉽게 적용할 수 있게 함
    - AOP(Aspect Oriented Programming) 개요
        - AOP는 application에서의 관심사의 분리(기능의 분리) 즉, 핵심적인 기능에서 부가적인 기능을 분리한다.
        - 분리한 부가기능을 어스펙트(Aspect)라는 독특한 모듈 형태로 만들어서 설계하고 개발하는 방법.
        - OOP를 적용하여도 핵심기능에서 부가기능을 쉽게 분리된 모듈로 작성하기 어려운 문제점을 AOP가 해결
        - AOP는 부가기능을 어스팩트(Aspect)로 정의하여, 핵심기능에서 부가기능을 분리함으로써 핵심기능을 설계하고 구현할 때 객체지향적인 가치를 지킬 수 있도록 도와주는 개념
    - AOP 적용 예
        - 간단한 메소드의 성능 검사
        - 트랜잭션 처리
        - 예외 반환
        - 아키텍처 검증
    - AOP 구조
        - 핵심 관심 사항 : BankingService. AccountService. CustomerService
        - 공통 관심 사항 : Security, Transaction,Other..
        
    
- Spring AOP
    - Spring AOP 용어
        - Target
            - 핵심기능을 담고 있는 모듈로 target은 부가기능을 부여할 대상이 됨.
        - Advice
            - 어느 시점(Ex : method의 수행 전/후, 예외 발생 후 등)에 어떤 공통 관심 기능(Aspect)을 적용할지 정의 한 것. Target에 제공할 부가기능을 담고 있는 모듈
        - JoinPoint
            - Aspect가 적용 될 수 있는 지점.(method,field)
            - 즉 target 객체가 구현한 인터페이스의 모든 method는 JoinPoint가 됨
        - Poincut
            - 공통 관심 사항이 적용될 JoinPoint
            - Advice를 적용할 target의 method를 선별하고 정규 표현식
            - Pointcut 표현식은 execution으로 시작하고 method의 Signature를 비교하는 방법을 주로 이용
        - Aspect
            - 여러 객체에서 공통으로 적용되는 공통 관심 사항. (transaction, logging, security)
            - AOP의 기본 모듈
            - Aspect = Adivice + Pointcut
            - Aspect = Singleton 형태의 객체로 존재.
        - Advisor
            - Advisor = Advice + pointcut
            - Advisor는 Spring AOP에서만 사용되는 특별한 용어.
        - Weaving
            - 어떤 Advice를 어떤 Pointcut(핵심사항)에 적용시킬 것인지에 대한 설정(Advisor)
            - 즉 Pointcut에 의해서 결정된 타겟의 JoinPoint에 부가기능(Advice)을 삽입하는 과정을 뜻함.
            - Weaving은 AOP의 핵심기능(Target)의 코드에 영향을 주지 않으면서 필요한 부가기능(Advice)을 추가할 수 있도록 해 주는 핵심적인 처리 과정.
    - Pointcut 표현식
        

        
    - AOP 비교
        

        
    - Spring AOP 특징 - 1
        - Spring은 프록시(Proxy) 기반 AOP를 지원
            - Spring은 Target 객체에 대한 Proxy를 만들어 제공
            - Target을 감싸는 Proxy는 실행시간(Runtime)에 생성.
            - Proxy는 Advice를 Target객체에 적용하면서 생성되는 객체

                
    - Spring AOP 특징 - 2
        - 프록시(Proxy)가 호출을 가로챈다. (Intercept)
            - Proxy는 Target 객체에 대한 호출을 가로챈 다음 Advice의 부가기능 로직을 수행하고 난 후에 Target의 핵심 기능 로직을 호출한다.(전처리 Advice)
            - 또는 Target의 핵심 기능 로직 method를 호출한 후에 부가기능(Advice)을 수행하는 경우도 있다. (후처리 Advice)
            
           
    - Spring AOP 특징 - 3
        - Spring AOP는 method JoinPoint만 지원.
            - Spring은 동적 Proxy를 기반으로 AOP를 구현하므로 method JoinPoint만 지원한다.
            - 즉, 핵심기능(Target)의 method가 호출되는 런타임 시점에만 부가기능(Advice)를 적용할 수 있다.
            - 반면 AspectJ 같은 고급 AOP framework를 사용하면 객체의 생성, 필드값의 조회와 조작, static method 호출 및 초기화 등의 다양한 작업에 부가기능을 적용할 수 있다.
- Spring AOP 구현
    - Spring AOP 구현 방법
        - POJO Class를 이용한 AOP 구현
        - Spring API를 이용한 AOP 구현
        - Annotation을 이용한 AOP 구현
        - XML Schema 확장 기법을 통해 설정 파일을 작성.
        - POJO기반 Advice Class 작성
    - 설정 파일 - 1
        - XML Schema를 이용한 AOP 설정
            

            
    - 설정 파일 - 2
        

        
    - 설절 파일 - 3
        - AOP 설정 태그
            

            
        - Advice 설정 태그

            
    - 설정 파일 <aop : aspect> - 4
        - 한 개의 Aspect(공통 관심 기능)을 설정
        - ref 속성을 통해 공통 기능을 가지고 있는 bean을 연결
        - id는 이 태그의 식별자 설정
        - 자식 태그로 <aop : pointcut> advice관련 태그가 올 수 있다.
            

            
    - 설정 파일 <aop : pointcut> - 5
        - Poincut(공통 기능이 적용될 곳)을 지정하는 태그
        - <aop:config>나 <aop:aspect>의 자식 태그
            - <aop:config>전역적으로 사용
            - <aop:aspect> 내부에서 사용
        - AspectJ 표현식을 통해 pointcut지정
        - 속성
            - id : 식별자로 advice 태그에서 사용됨
            - expression : pointcut 지정
                

                
    - POJO기반 Adivice Class 작성
        - Advice 작성
            - 설정 파일의 advice 관련 태그에 맞게 작성한다.
            - <bean>으로 등록 하며 <aop:aspect>의 ref 속성으로 참조한다.
            - 공통 기능 메소드 : advice 관련 태그들의 method 속성의 값이 method의 이름이 된다.
        - Advice 정의 관련 태그
            - pointcut : 직접 pointcut을 설정. 호출 할 method의 패전 지정.
            - pointcut-ref : <aop : pointcut> 태그의 id명을 넣어 pointcut 지정
            - method : Aspect bean에서 호출할 method명 지정.
        - Advice Class
            - class명이나 method명에 대한 제한은 없다.
            - method 구문은 호출되는 시점에 따라 달라 질 수 있다.
            - method의 이름은 advice 태그(<aop : before/>)에서 method 속성의 값이 method명이 된다.
                
 
                
    - POJO기반 AOP 구현 - Advice 종류 - 1
        - Before Advice
            - 대상 객체의 메소드가 실행되기 전에 실행됨
            - return type : 리턴 값을 갖더라도 실제 Advice의 적용과정에서 사용되지 않기 때문에 void을 쓴다.
            - argument : 없거나 대상객체 및 호출되는 메소드에 대한 정보 또는 파라미터에 대한 정보가 필요하다면 JoinPoint 객체를 전달
                

                
        - Before Advice 실행 순서
            1. 빈 객체를 사용하는 코드에서 스프링이 생성한 AOP 프록시의 메소드를 호출
            2. AOP 프록시는 <aop:before>에서 지정한 메소드를 호출
            3. AOP 프록시는 Aspect 기능 실행 후 실제 빈 객체의 메소드를 호출
                

    - POJO기반 AOP 구현 Advice 종류 - 2
        - After Returning Advice
            - 대상 객체의 method 실행이 정상적으로 끝난 뒤 실행됨.
            - return type : void
            - argument : 없거나 org.aspectj.lang.JoinPoint 객체를 받는다. JoinPoint는 항상 첫 argument로 사용
            대상 method에서 반환되는 특정 객체 타입의 값을 argument로 받을 수 있다.

        - After Returning Advice 실행 순서
            1. 빈 객체를 사용하는 코드에서 스프링이 생성한 AOP 프록시의 메소드를 호출
            2. AOP 프록시는 실제 빈 객체의 메소드를 호출(정상 실행)
            3. AOP 프록시는 <aop:after-returning>에서 지정한 메소드를 호출>
    
    - POJO기반 AOP 구현 Advice 종류 - 3
        - After Throwing Advice
            - 대상 객체의 method 실행 중 예외가 발생한 경우 실행됨
            - return type : void
            - argument : 없거나 JoinPoint 객체를 받는다. JoinPoint는 항상 첫 argument로 사용.
            - 대상 method에서 전달되는 예외 객체를 argument로 받을 수 있다.
          
                
        - After Throwing Advice 실행 순서
            1. 빈 객체를 사용하는 코드에서 스프링이 생성한 AOP 프록시의 메소드를 호출
            2. AOP 프록시는 실제 빈 객체의 메소드를 호출 (exception 발생)
            3. AOP 프록시는 <aop:after-throwing>에서 지정한 메소드를 호출
    - POJO기반 AOP 구현 Advice 종류 - 4
        - After Advice
            - 대상 객체의 method가 정상적으로 실행 되었는지 아니면 exception을 발생 시켰는지의 여부와 상관없이 메소드 실행 종료 후 공통 기능 적용
            - return type : void
            - argument : 없거나 JoinPoint 객체를 받는다. JoinPoint는 항상 첫 argument로 사용
        
                
        - After Advice 실행 순서
            1. 빈 객체를 사용하는 코드에서 스프링이 생성한 AOP 프록시의 메소드를 호출.
            2. AOP 프록시는 실제 빈 객체의 메소드를 호출(정상 실행,exception 발생 : java의 finally와 같음)
            3. AOP 프록시는 <aop:after>에서 지정한 메소드를 호출
       
                
    - POJO기반 AOP 구현 Advice 종류 - 5
        - Around Advice
            - 위의 네가지 Advice를 다 구현 할 수 있는 Advice
            - return type : Object
            - argument : org.aspectj.lang.ProceedingJoinPoint를 반드시 첫 argument로 지정
            
  
            
        - Around Advice 실행 순서
            1. 빈 객체를 사용하는 코드에서 스프링이 생성한 AOP 프록시의 메소드를 호출
            2. AOP 프록시는 <aop:around>에서 지정한 메소드를 호출
            3. AOP 프록시는 실제 빈 객체의 메소드를 호출
            4. AOP 프록시는 <aop:around>에서 지정한 메소드를 호출
                
      
                
    - JoinPoint Class 구성 요소
        - 대상 객체에 대한 정보를 가지고 있는 객체로 Spring Container로 부터 받는다.
        - org.aspectj.lang패키지에 있다.
        - 반드시 Aspect method의 첫 argument로 와야 한다.

            
    - @aspect Annotation을 이용한 AOP 구현
        - @Aspect Annotation을 이용하여 Aspect Class에 직접 Advice 및 Pointcut등을 설정.
        - 설정 파일에 <aop:aspectj-autoproxy>를 반드시 추가.
        - Aspect Class를 <bean>으로 등록
        - 어노테이션
            - @Aspect : Aspect Class 선언
            - @Before(”pointcut”)
            - @AfterReturning(pointcut=””,returning=””)
            - @AfterThrowing(pointcut=””,throwing=””)
            - @After(”pointcut”)
            - @Around(”pointcut”)
        - Around를 제외한 나머지 method들은 첫 argument로 JoinPoint를 가질 수 있다.
        - Around method는 argument로 ProceedingJoinPoint를 가질 수 있다.
