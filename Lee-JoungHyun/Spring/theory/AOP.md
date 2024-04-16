# AOP (Aspect Oriented Programming)

- 핵심 관심 사항과 공통(부가) 관심 사항
    - 핵심 관심 사항(core concern)과 공통 관심 사항(cross-cutting concern)
    - 기존 OOP에서 공통 관심사항을 여러 모듈에 적용하는데 한계가 존재
    - 이를 해결하기 위해 등장
    - 문제를 해결하기 위한 2가지 사항들을 기준으로 프로그래밍 함으로 써 공통모듈을 손쉽게 적용할 수 있게 함
- 적용의 예시
    - 간단한 메서드 성능 검사
    - 트랜잭션 처리
    - 예외 반환
    - 아키텍처 검증
    - 기타 (멀티쓰레드, 데드락, 로깅, 인증, 권한 등)
 
** 이는 Proxy Pattern 으로 구성됨 **


## Spring AOP 용어

- Target: 핵심 기능 담고있는 모듈, target은 부가기능을 부여할 대상
- Advice: 어느 시점에 어떤 공통 관심 기능을 적용할건지 정의한 것
- JoinPoint: Aspct가 적둉될 수 있는 지점
- Pointcut: 공통 관심사항이 적용될 JoinPoint - Advice를 적용할 target의 method 선별하는 정규 표현식
- Aspect: 여러 객체에서 공통으로 적용되는 공통 관심 사항 (Advice + Pointcut). 싱글톤 객체
- Advisor: (Advice + Pointcut), 스프링에서만 사용되는 특별 용어
- Weaving: 어떤 Advice를 어떤 Pointcut에 적용시킬 것에 대한 설정(Advisor) Pointcut에 의해 결정된 타겟의 JoinPoint에 부가기능(Advice)을 삽입하는 과정

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/60d66ae7-5f64-44bc-b4b0-f484626a8090/aef01eb6-0e4b-41d6-a5f6-64935a1223b3/Untitled.png)


## Spring AOP 구현 방법

- POJO Class를 이용한 AOP 구현 = XML 사용

```xml
<!-- AOP 설정 -->
	<bean id="boardContentEncodeAdvice" class="com.ssafy.aop.step02.before.BoardContentEncodeAdvice"></bean>
	
	<!--  com.ssafy.board package안에 있는 Dao로 끝나는 모든 클래스의 모든 메소드에 적용 -->
	<aop:config>
		<aop:aspect id="beforeAspect" ref="boardContentEncodeAdvice">
			<aop:pointcut id="publicMethod" expression="execution(public * com.ssafy.board..*Dao.*(..))"/>
			<aop:before method="encode" pointcut-ref="publicMethod"/>
		</aop:aspect>
	</aop:config>
```

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/60d66ae7-5f64-44bc-b4b0-f484626a8090/1ce48fa3-c312-4d7e-a559-189ed6057ee1/Untitled.png)

```java
ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
Person audience = (Person) context.getBean("audience", Person.class);
```

- JDK 다이내믹 프록시를 사용하는 경우 AoP의 target class 는 항상 인터페이스를 구현하고 있어야 하며 캐스팅시 그 인터페이스로 캐스팅 해야 한다??
- 이때 AoP를 사용하면 해당 객체 베이스인 Proxy 객체를 생성하는데 이는 같은 해당 클래스로 캐스팅이 되지 않음 따라서 인터페이스로 캐스팅 해라 바보야
- @Aspect 어노테이션 쓸꺼면 @Component 도 같이 붙여라


### AOP 어노테이션으로 구현

```java
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MovieAspect {
	
	@Before("execution(public * com.ssafy.ws.Audience.doSomething(..))")
	public void before() {
		System.out.println("영화관을 갑니다.");
	}
	
	@AfterReturning("execution(public * com.ssafy.ws.Audience.doSomething(..))")
	public void afterReturn()  {
		System.out.println("영화가 끝나고 나옵니다.");
	}
	
	@AfterThrowing("execution(public * com.ssafy.ws.Audience.doSomething(..))")
	public void afterThrow() {
		System.out.println("전화를 받습니다.");
	}
	
	@After("execution(public * com.ssafy.ws.Audience.doSomething(..))")
	public void after() {
		System.out.println("집에 갑니다.");
	}
}
```


## JDK Dynamic Proxy vs CGLib

- JDK Dynamic Proxy
    - JDK Dynamic Proxy 는 동적 프록시 기술로 런타임에 생성됨
    - 이는 리플랙션 (Java API) 을 사용
    - 이는 인터페이스 기반으로 프록시를 동적으로 만들기 때문에 인터페이스가 필수이다!!
- CGLib
    - 바이트 코드를 조작해 동적으로 클래스 생성, 구현 클래스만으로 동적 프록시 생성 가능
    - Spring Boot 에서는 Default
    - AOP에서 CGLib 사용법
        - ApplicationContext.xml에서 <aop:aspectj-autoproxy `proxy-target-class="true"` />
        - 어노테이션으로 `@EnableAspectJAutoProxy(proxyTargetClass = true)`
