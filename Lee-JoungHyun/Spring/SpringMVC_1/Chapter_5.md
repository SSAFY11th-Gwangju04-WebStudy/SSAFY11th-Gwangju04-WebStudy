# 5. 스프링 MVC 패턴 구조 이해

### 직접 만든 프레임 워크와 비교
FrontController -> DispatcherServlet 
handlerMappingMap -> HandlerMapping 
MyHandlerAdapter -> HandlerAdapter 
ModelView -> ModelAndView 
viewResolver -> ViewResolver
MyView -> View

- DispatcherServlet
	- 스프링 MVC의 핵심
	- 앞의 프론트 컨트롤러
	- 스프링 부트의 내장 톰켓 서버에서 생성, 모든 경로 매핑 ("/")

 
#### 동작순서

1. 핸들러 조회: URL에 매핑된 핸들러(컨트롤러) 조회
2. 핸들러 어댑터 조회: 핸들러 실행할 수 있는 어댑터 조회
3. 핸들러 어댑터 실행
4. 핸들러 실행
5. ModelAndView 반환: 핸들러의 반환값을 변환해서 반환해줌
6. viewResolver 호출: 뷰 리졸버 찾아서 실행
7. view 반환: 뷰 리졸버(물리 이름으로 변경) 뷰 객체 반환
8. 뷰 랜더링
#### 정리

- 코드 분량도 많고 복잡해 내부 구조를 모두 파악하기는 쉽지 않다
- 기능 확장이나 나만의 컨트롤러는 만들 필요는 없다
- 문제 발생시 해결, 확장 포인트가 필요할 때 사용할 수 있게만 하자!

### 핸들러 매핑, 핸들러 어댑터
= 컨트롤러 호출시 필요한 2가지!!
1. HandlerMapping (핸들러 매핑)
	- 스프링 빈의 이름으로 핸들러를 찾을 수 있어야 함 (우선순위)
		0. RequestMappingHandlerMapping (@RequestMapping)
		1. BeanNameURLHandlerMapping (스프링 빈의 이름)
1.  HandlerAdapter (핸들러 어댑터)
	- 핸들러를 실행할 수 있는 핸들러 어댑터가 필요함
		0. RequestMappingHandlerAdapter (@Request)
		1. HttpRequestHandlerAdapter
		2. SimpleControllerHandlerAdapter (Controller 인터페이스)

### 뷰 리졸버
InternalResourceViewResolver 에 등록된 정보를 가져오려 하는데 이때
application.properties 에 
spring.mvc.view.prefix=/Xxx/
spring.mvc.view.suffix=.jsp
를 추가해 실제 경로를 찾는다!
1. BeanNameViewResolver : 빈 이름으로 뷰 찾기
2. InternalResourceViewResolve : JSP 처리할 수 있는 뷰 반환

### 스프링 MVC 시작하기
= 에노태이션 기반으로 동작 @RequestMapping
- @Controller: 스프링 빈에 등록 (컴포넌트 스캔의 대상  - @Component)
- @RequestMapping: 요청 정보 URL 매핑
- ModelAndView: 모델과 뷰 정보 담아 반환

### 스프링 MVC 통합
- class 단위의 @RequestMapping (공통 url)
- 메서드 단위 @RequestMapping (공통 + /추가)

### 스프링 MVC - 실용적인 방식
- 다형성을 이용해 호출 함수에 매개변수를 @RequestParam("username") String username, 형식으로 받고 Model도 받아 model에 값 넣고 파일 논리 이름 (String) 반환
- POST, GET 세팅 = @RequestMapping(value = "/new-form", method = RequestMethod.GET) -> GetMapping("/new-form")
- method 를 바꿔주면 해당 전송방식만 받을 수 있음!
