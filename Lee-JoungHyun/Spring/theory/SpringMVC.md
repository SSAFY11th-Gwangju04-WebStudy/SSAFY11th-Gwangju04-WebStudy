# Spring MVC 프레임워크

![Untitled](https://github.com/Lee-JoungHyun/SSAFY11th-Gwangju04-WebStudy/assets/98592001/f8797e0c-69d8-41f8-840b-fc04a9031a43)

## Spring MVC

### Spring MVC의 특징

1. DI, AOP 뿐만 아니라 WEB 개발을 위한 MVC Framework 제공
2. Front Controller Pattern 을 Framework 차원에서 제공
3. Spring이 제공하는 Transaction 처리, DI, AOP 사용이 쉬움

### Spring MVC 구성요소

- Dispatcher Servlet(Front Controller)
    - 모든 크라이언트 요청 받음
    - Contoller에게 클라이언트 요청 전달, Controller가 리턴한 결과값을 View에 전달하여 알맞은 응답 생성 ( WAS 에서 관리)
- HandlerMapping
    - 클라이언트 요청 URL을 어떤 Controller가 처리할 지 지정
    - URL과 요청정보 기준으로 어떤 핸들러 객체(HandlerAdapter) 사용할지 결정
- Controller
    - 클라이언트 요청 처리(Model) 호출, 결과 반환 (DispatcherServlet) 에게
- ModelAndView
    - Controller가 처리한 데이터 및 화면 정보 보유한 객체
- ViewResolver
    - Controller가 반환한 이름 기반으로 View 결정
- View
    - Controller의 처리 결과 보여줄 응답 화면 생성

![Untitled (2)](https://github.com/Lee-JoungHyun/SSAFY11th-Gwangju04-WebStudy/assets/98592001/61d17e09-4253-4fca-bf82-6dcf14ef292d)

**Servlet-applicationContext — Root-applicationContext**

- 해당의 차이는 Servlet(Dispatcher Servlet) 이 알아야 하는 컴포넌트 인가? 아닌가 기준이라고 생각함
- Root-applicationContext 는 서블릿과 관계 없는 호출 당하는 입장 애들의 설정정보 (test 실행시 작동하는 애들?)

SpringMVC 는 Dispatcher Servlet 을 만들때 Servlet-applicationContent 를 참고해서 만든다! (빈 등록을 그렇게 하는듯)

스프링의 컨테이너는 2가지 종류! (servlet - root) 공간이 별도다!! (부트도 그런지는 모르겠지만..)

SpringMVC 설정정보는 pom.xml, servlet-applicationContext.xml, root-xxx.xml 이렇게 3개!

# 정리

1. WAS 를 띄우면 conf 안에 xml, propertie, pol등 설정정보 파일이 프로젝트를 가리킴!
2. 프로젝트의 설정 정보는 크게 2가지 (pom.xml, web.xml)
    1. pom.xml : Maven 프로젝트 사이클, 의존성 관리 
    2. web.xml 필터매핑, 세션설정, 오류처리, 보안설정, 리소스 매핑!
        1. RootApplicationContext : ContextLoaderListener (비즈니스 로직, 서비스, DAO 관리 + applicationContext 또는 Java Config 통새 설정
        2. ServletApplicationContext : DispatcherServlet 웹 계층과 관련된 빈 정의하고 관리

### Web.xml

```xml
<!-- RootApplicationContext 를 지정해 ContextLoaderListener 호출 -->
<context-param>
	<param-name>contextConfigLocation</param-name>
	<param-value>/WEB-INF/spring/root-context.xml</param-value>
</context-param>

<!-- 프로젝트 내부에 있는 모든 applicationContext.xml 이라는 파일 찾음 -->
<listener>
	<listener-class>org.springframework.web.context.ContextLoaderListener </listener-class>
</listener>

<!-- ServletApplicationContext 를 지정해 DispatcherServlet 생성 -->
<servlet>
	<servlet-name>appServlet</servlet-name>
	<servlet-class>org.springframework.web.servlet.DispatcherServlet
	</servlet-class>
	<init-param>
		<param-name>contextConfigLocation</param-name>
		<param-value>/WEB-INF/spring/appServlet/servlet-context.xml
		</param-value>
	</init-param>
	<load-on-startup>1</load-on-startup>
	<multipart-config>
		<max-file-size>209715200</max-file-size>
		<max-request-size>209715200</max-request-size>
		<file-size-threshold>0</file-size-threshold>
	</multipart-config>
</servlet>

<!-- appServlet 이라는 서블릿에 /(루트경로) 처리 매핑!! -->
<servlet-mapping>
	<servlet-name>appServlet</servlet-name>
	<url-pattern>/</url-pattern>
</servlet-mapping>

```

### ServletContext

```xml
<!-- 컴포넌트 스캔의 범위 지정, base-package="" 로 범위 지정 가능 -->
<context:component-scan />

<!-- 정적 리소스 스캔할 수 있게 매핑 /resources/~ 로 접근하면 /resources 로 이동시킴 -->
<resources mapping="/resources/**" location="/resources/" />

<!-- InternalResourceViewResolver 라는 내부 뷰리졸버(1개만 존재가능) 생성 (앞, 뒤에 붙이기) -->
<beans:bean
	class="org.springframework.web.servlet.view.InternalResourceViewResolver">
	<beans:property name="prefix" value="/WEB-INF/views/" />
	<beans:property name="suffix" value=".jsp" />
</beans:bean>

<!-- /regist 매핑에 대해 sessionInterceptor 로 빈등록되있는 인터셉터 매핑 -->
<interceptors>
	<interceptor>
		<mapping path="/regist" />
		<beans:ref bean="sessionInterceptor" />
	</interceptor>
</interceptors>

<!-- 멀티파트 리졸버 빈생성 -->
<beans:bean id="multipartResolver" class="org.springframework.web.multipart.support.StandardServletMultipartResolver"></beans:bean>
```

### root-context와 applicationContext 둘다 사용 이유

1. **설정의 분리 (Separation of Concerns)**:
    - root-context.xml: 주로 데이터베이스 연결, 트랜잭션 관리, 보안 설정 등과 같은 애플리케이션의 핵심적인 설정을 담당합니다. 이러한 설정은 애플리케이션 전체에서 공유되며, 여러 개의 서블릿 컨텍스트에서 공유할 수 있습니다.
    - applicationContext.xml: 주로 Spring MVC와 관련된 설정을 담당합니다. 뷰 리졸버, 핸들러 매핑, 인터셉터 등과 같이 웹 애플리케이션의 요청 처리와 관련된 설정을 포함합니다. 이러한 설정은 주로 각각의 서블릿 컨텍스트에서만 사용됩니다.
2. **구성의 재사용 (Reusability of Configuration)**:
    - root-context.xml에는 애플리케이션 전반에 걸쳐 사용되는 설정이 포함되므로, 다수의 서블릿 컨텍스트가 동일한 설정을 공유할 수 있습니다. 이렇게 함으로써 설정의 중복을 줄이고 코드의 재사용성을 높일 수 있습니다.
3. **테스트 용이성 (Ease of Testing)**:
    - 각각의 설정 파일이 서로 다른 역할을 수행하므로, 테스트를 진행할 때에도 해당 역할에 대한 설정만 포함된 파일을 사용하여 테스트를 수행할 수 있습니다. 이는 테스트의 격리성을 높이고 디버깅을 용이하게 합니다.
    

### 질문

1. /WEB-INF/spring/root-context.xml 과 src/main/resources/applicationContext 를 분리한 이유??
    - 이를 호출하기 위해 리스너를 설정한것이 맞는지??
2. 이미지를 보낼때 해당 경로로 받는데 이는 GET 방식 요청이 새로 들어와서 ServletContext 에서 매핑해준 resources 를 타고 다시 반환해주는 것인지? (요청 → 응답 → 요청 → 응답), 이러면 서버 사이드에서 랜더링 하고 클라이언트 사이드에서 다시 랜더링??

```xml
<td rowspan="5"><img id="cover" src="${root }/resources/upload/${book.img }"></td>
```
