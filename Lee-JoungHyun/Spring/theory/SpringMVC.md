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
