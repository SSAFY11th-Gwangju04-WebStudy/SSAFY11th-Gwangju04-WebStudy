# 4. MVC 프레임 워크 만들기

### FrontController 패턴 특징
- 프론트 컨트롤러(서블릿) 하나로 Cli의 모든 요청 받음
- 입구를 하나로 사용 + 공통처리가 가능함!
- 나머지 컨트롤러는 Servlet을 사용할 필요가 없다!
- Spring Web MVC 가 DispatcherServlet 으로 구현됨

## 프론트 컨트롤러
HTTP 요청시
1. URL 매핑 정보에서 Controller 조회
2. 해당 Controller 호출
3. 컨트롤러 에서 JSP forward
4. 응답 (html)

### v1 (Front Controller 도입)
FrontController 에 URL : Controller(Class) 매핑 시켜 놓음
= 구조적?
- 인터페이스로 객체를 받아 함수 호출 
- 구현체기 달라져서 같은 함수를 다른 기능으로 호출해 사용을 할 수 있다!!!
- 인터페이스 사용으로 일관성 있는 로직(호출) 이 가능함!

실제 개발시는 특수한 경우? 가 아니면 절대경로를 사용함

### V2 (View 분리)
Controller 에서 FrontController에 MyView를 반환
이를 FrontContrller 가 render() 호출
- 각각의 Controller 에서 직접 forward 안해도 됨
- 일관성 +

### V3 (Model 분리, request, response 처리?)
1. Parameter를 Map 형태로 넘기기 -> Controller가 Servlet 몰라도 사용 가능
2. request 객체 대신 별도의 Model 객체를 만들어 반환할 것!
3. 뷰 이름의 중복을 제거 (컨트롤러는 논리 이름 반환 (경로, 확장자 없애기))
= request대신 Model 사용 (model의 정보를 다 request에 attribute 로 넣음)
= viewResolver 가 논리 이름 -> 물리 이름 으로 변경 -> render() 호출
- 프론트 컨트롤러가 하는일이 많아진 만큼 Controller 가 편하게 사용 가능

### V4 단순하고 실용적인 컨트롤러
= 전에는 아키텍쳐를 이론적으로 좋게 구현됨
이번엔 개발자 입장에서 단순하게 사용할 수 있게 기능개선
- 컨트롤러가 ModelView 가 아닌 ViewName만 반환
- 받은 Map(Model) 에 값 세팅해주면 됨

### V5 유연한 컨트롤러 (v3, v4) - OCP
(adapter 패턴) = 다양한 종류(버전)의 컨트롤러를 사용함
핸들러 = Controller 의 이름 (더 넓은 범위로 이름 바꾸기)
Controller -- handler adapter -- handler 호출!
Front Controller가 직접 Controller 호출하는 방식에서
어탭터를 통해 Controller 호출하는 방식으로
1. 핸들러 매핑 정보 찾기
2. 핸들러 어댑터 목록 조회 (V3 처리할 수 있는지)
3. 핸들러 어댑터 호출
4. 뷰 리졸버 호출
5. 뷰 랜더 실행
	- 스프링도 이처럼 인터페이스 기반 -> 어노테이션 기반 으로 변경됨

### 정리
- 변경시는 큰 틀 변경 vs 세부사항 변경을 분리해서 진행해라!
- + 로는 애노테이션 사용해 컨트롤러를 더 편리하게 사용할 수 있다!
- 스프링 MVC 는 V5와 거의 유사하다
- 인터페이스와 구현체를 이용한 확장성이 좋다!

