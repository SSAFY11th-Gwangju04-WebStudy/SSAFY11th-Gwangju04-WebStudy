# 수업 요약

<aside>
💡 1교시
DI xml constructor 써야되는 이유
- final 사용 가능 
- autowired 생략가능
- 순환 참조를 막을 수 있다.

</aside>

2교시

<aside>
💡 aop( aspect orient programing)
- 핵심과 부가적인 기능을 분리해서 기존 oop에서 공통 관심사항을 여러 모듈에서 적용하는데 있어 중복된 코드를 양상 하는 한계가 존재함

-AOP 주요 개념

- Aspect: 여러 부분에 흩어져 있는 기능을 모듈화 한 것 (예: 로깅, 보안)
- Target: Aspect가 적용되는 대상 (예: 클래스, 메소드)
- Advice: Aspect의 실질적인 기능에 대한 구현체
- Join point: 프로그램 실행 중 Advice가 Target에 적용되는 지점
- Pointcut: 어떤 join point에 어떤 Advice가 실행되는지 결정하는 표현식
- Weaving: Aspect를 프로그램 코드에 적용하는 과정

-aop 적용방법

1. <aop:aspect>: Aspect를 정의하는 태그입니다. ref 속성으로 Aspect의 빈 이름을 지정합니다.
2. <aop:pointcut>: Pointcut을 정의하는 태그입니다. id 속성으로 Pointcut의 이름을 지정하고, expression 속성으로 Pointcut 표현식을 지정합니다.
3. <aop:before>, <aop:after>, <aop:around>, ... : Advice를 정의하는 태그입니다. method 속성으로 Advice 메소드를 지정하고, pointcut 혹은 pointcut-ref 속성으로 적용할 Pointcut을 지정합니다.

</aside>

@anotation aop 실습

```jsx
package com.ssafy.ws;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MovieAspect {
		//코드작성
	
	@Pointcut("execution(public * com.ssafy.ws.Audience.*(..))")
	public void pointcut() {
		
	}
	
	@Before("pointcut()")
	public void before(JoinPoint joinpoint) {
		System.out.println("[before] " +"영화관을 갑니다." );
	}
	
	@After("pointcut()")
	public void after(JoinPoint joinpont) {
		System.out.println("[after] " + "집을 갑니다.");
	}

	
	@AfterReturning(value="pointcut()",returning ="result")
	public void afterReturning(JoinPoint joinPoint, Object result) {
		System.out.println("[AfterReturning] " +"영화가 끝나고 나옵니다.");
	}
	
	@AfterThrowing(value = "pointcut()",throwing="ex")
	public void afterThrowing(JoinPoint joinPoint , Throwable ex) throws Throwable{
		System.out.println("[AfterThrowing] " + "전화를 받습니다.");
	}
	
//	@Around("poincut()")
//	void around(ProceedingJoinPoint joinpoint) throws Throwable{
//		System.out.println("[Around] +++ " + joinpoint.getSignature().toShortString() + " +++");
//		// Object[] args = joinPoint.getArgs(); // 메소드의 입력인자를 가져올 수 있다.
//		Object obj = joinpoint.proceed();
//		System.out.println("[Around] --- " + joinpoint.getSignature().toShortString() + " ---");
//		System.out.println("...");
//	}
	
	
	
}

```

배운점

- **`Audience`** 클래스를 직접 사용하여 빈을 요청했을 때 문제가 발생한 것은 스프링이 기대하는 빈 이름 또는 타입이 정확히 일치하지 않았기 때문일 수 있습니다. 또한, 스프링 설정(**`AppConfig.class`**)에서 빈의 등록 방식에 따라 빈 검색 동작이 달라질 수도 있습니다.
- 그리고 audience라고 지정을안해주면 클래스이름에서 앞에 대문자를 소문자된 클래스명이 자동으로 빈의 이름이 된다.

```jsx
package com.ssafy.ws;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MovieTest {
	public static void main(String[] args) {
		//코드작성
		
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		Person audience=	context.getBean("audience", Person.class);
		
		//이게 에러가 났다. 이유는? 
//Audience audience = context.getBean(Audience.class);
		try {
			audience.doSomething();
		} catch (CallException e) {
			// TODO Auto-generated catch block
		
		}
	        

	}

}

```

