## AOP(Aspect Oriented Programming)

> AOP 개요

- 핵심 관심 사항(core concern)과 공통 관심 사항(cross-cutting concern)
- 기존 OOP에서는 공통 관심 사항을 여러 모듈에서 적용하는데 있어 중복된 코드를 양상하는 한계가 존재함
- 이를 해결하기 위해 AOP 등장
- AOP는 문제를 해결하기 위한 핵심 관심 사항과 전체에 적용되는 공통 관심 사항을 기준으로 프로그래밍함으로써 공통 모듈을 손쉽게 적용할 수 있게 함.
- AOP는 application에서의 관심사의 분리(기능의 분리) 즉, 핵심적인 기능에서 부가적인 기능을 분리한다.
- 분리한 부가기능을 Aspect라는 모듈 형태로 만들어서 설계하고 개발하는 방법.
- OOP를 적용하여도 핵심기능에서 부가기능을 쉽게 분리된 모듈로 작성하기 어려운 문제점을 AOP가 해결.
- AOP는 부가기능을 Aspect로 정의하여, 핵심 기능에서 부가기능을 분리함으로써 핵심기능을 설계하고, 구현할 때 객체지향적인 가치를 지킬 수 있도록 도와주는 개념.

> AOP의 적용 예

1. 간단한 메소드의 성능 검사
    - DB에 대량 데이터를 넣고 빼는 등의 배치 작업에 대하여 시간을 측정해보고 쿼리를 개선하는 작업은 매우 의미 있다.
    - 이때 매번 해당 메소드의 처음과 끝에 System.currenTimeMilllis();를 사용하거나, Spring이 제공하는 StopWatch코드를 사용하기는 매우 번거롭다.
    - 이런 경우 해당 작업을 하는 코드를 밖에서 설정하고 해당 부분을 사용하는 것이 편리하다.
2. 트랜잭션 처리
    - 트랜잭션의 경우 비즈니스 로직의 전후에 설정된다.
    - 하지만 매번 사용하는 트랜잭션(try~catch)의 코드는 번거롭고, 소스를 더욱 복잡하게 보여준다.
3. 예외 반환
    - 스프링에는 DataAccessException이라는 매우 잘 정의되어 있는 예외 계층 구조가 있다.
    - 하지만 구조가 별로 안좋은 예외들이 발생 했을 때, 그걸 잡아서 잘 정의 되어있는 예외 계층 구조로 변환해서 다시 던지는 Aspect는 제 3의 프레임워크를 사용할 때, 본인의 프레임워크나 애플리케이션에서 별도의 예외 계층 구조로 변환하고 싶을 때 유용하다.
4. 아키텍처 검증
5. 기타
    - Hibernate와 JDBC를 같이 사용할 경우, DB 동기화 문제 해결
    - 멀티쓰레드 Safety 관련하여 작업해야 하는 경우, 메소드들에 일괄적으로 락을 설정하는 Aspect
    - Dead Lock 등으로 인한 PessimisticLockingFailureException등의 예외를 만났을 때 재시도하는 Aspect
    - 로깅, 인증, 권한 등 …

> AOP 구조

- 핵심 관심 사항 : BankingService, AccountService, CustomerService
- 공통 관심 사항 : Security, Transaction, Other …

![Untitled](https://github.com/MJ-Kor/SSAFY11th-Gwangju04-WebStudy/blob/main/MJ-Kor/SSAFYLectures/Spring/imgs/aop1.png)

- AOP를 적용하면 새로 만들어진 객체에 공통 관심 사항이 알아서 적용된다. (이런 느낌)

> Spring AOP - 용어

- Target
    - 핵심기능을 담고 있는 모듈, target은 부가기능을 부여할 대상이 됨.
- Advice
    - 어느 시점(Ex: method의 수행 전/후, 예외 발생 후 등…)에 어떤 공통 관심 기능(Aspect)을 적용할지 정의 한 것.
        1. around - 감싸는 것
        2. before - method 수행 전
        3. after returning - method 정상 수행 후
        4. after throwing - method 오류 발생 후
        5. after - 정상이든 오류든 상관 없이
    - Target에 제공할 부가기능을 담고 있는 모듈.
- JoinPoint (Target과 Aspect가 만나는 지점)
    - Aspect가 적용 될 수 있는 지점.(method, field) - spring은 method에만 적용 가능
    - 즉 target 객체가 구현한 인터페이스의 모든 method는 JoinPoint가 됨.
- Pointcut
    - 공통 관심 사항이 적용될 JoinPoint
    - Advice를 적용할 target의 method를 선별하는 정규 표현식
    - Pointcut 표현식은 execution으로 시작하고, method의 Signature를 비교하는 방법을 주로 이용
- Aspect (Spring AOP를 이용하여 공통 관심 사항을 특정 부분을 적용할 때 설정해야 하는 것)
    - 여러 객체에서 공통으로 적용되는 공통 관심 사항
    - AOP 기본 모듈
    - Aspect = Advice + Pointcut
    - Aspect는 Singleton 형태의 객체로 존재
    - bean의 형태
- Advisor
    - Advisor = Advice + Pointcut
    - Spring에서만 사용되는 특별한 용어
- Weaving (Aspect에 대한 전체적인 행동 단위)
    - 어떤 Advice를 어떤 Pointcut에 적용시킬 것인지에 대한 설정
    - Pointcut에 의해 결정된 target의 joinPoint에 부가기능(Advice)를 삽입하는 과정
    - Weaving은 AOP의 핵심기능(Target)의 코드에 영향을 주지 않으면서 Advice를 추가할 수 있도록 해주는 핵심 처리 과정

> Pointcut 표현식

![Untitled](https://github.com/MJ-Kor/SSAFY11th-Gwangju04-WebStudy/blob/main/MJ-Kor/SSAFYLectures/Spring/imgs/aop2.png)

- execution(public * *(..))
    - 첫번째 * 위치 - return type
    - 두번째 * 위치 - package.class.method(argument)
    - 따라서 해당 정규식은 public이면서 return type이 모든 것이고, 모든 패키지의 모든 클래스의 모든 메서드에서 실행
    - ( ) 안의 .. - 인자 값이 없어도 되고 많아도 됨
    - 패키지에서의 .. - 바로 밑 패키지가 아닌 모든 하위 패키지
    - 종합적으로 public 메소드를 실행하라는 것

> AOP 비교

![Untitled](https://github.com/MJ-Kor/SSAFY11th-Gwangju04-WebStudy/blob/main/MJ-Kor/SSAFYLectures/Spring/imgs/aop3.png)

- 

> Spring AOP 특징

- Proxy 기반 AOP 지원
    - Spring은 Target 객체에 대한 Proxy를 만들어 제공
    - Target을 감싸는 Proxy는 실행시간(Runtime)에 생성
    - Proxy는 Advice를 Target 객체에 적용하면서 생성되는 객체
    
    ![Untitled](https://github.com/MJ-Kor/SSAFY11th-Gwangju04-WebStudy/blob/main/MJ-Kor/SSAFYLectures/Spring/imgs/aop4.png)
    

- Proxy가 호출을 가로챈다
    - Proxy는 Target 객체에 대한 호출을 가로챈 다음 Advice의 부가기능 로직을 수행하고 난 후에 Target의 핵심 기능 로직을 호출한다. (전처리 Advice)
    - 또는 Target의 핵심 기능 로직 method를 호출한 후에 부가기능(Advice)를 수행하는 경우도 있다. (후처리 Advice)
    
    ![Untitled](https://github.com/MJ-Kor/SSAFY11th-Gwangju04-WebStudy/blob/main/MJ-Kor/SSAFYLectures/Spring/imgs/aop5.png)
    

- Spring AOP는 method JoinPoint만 지원
    - Spring은 동적 Proxy를 기반으로 AOP를 구현하므로 method JoinPoint만 지원한다.
    - 즉, 핵심기능(Target)의 method가 호출되는 런타임 시점에만 부가기능(Advice)를 적용할 수 있다.
    - 반면 AspectJ 같은 고급 AOP framework를 사용하면 객체의 생성, 필드값의 조회와 조작, static method 호출 및 초기화 등의 다양한 작업에 부가기능을 적용할 수 있다.
