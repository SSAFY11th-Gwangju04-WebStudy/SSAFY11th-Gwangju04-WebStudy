## Spring Web MVC

> Model
- 어플리케이션 상태의 캡슐화.
- 상태 쿼리에 대한 응답.
- 어플리케이션의 기능 표현.
- 변경을 view에 통지.

> View
- model을 화면에 시각적으로 표현.
- model에게 업데이트 요청.
- 사용자의 입력을 controller에 전달.
- 컨트롤러가 view를 선택하도록 허용.

> Controller
- 어플리케이션의 행위 정의.
- 사용자 액션을 model 업데이트와 mapping.
- 응답에 대한 view 선택.

---

- 어플리케이션의 확장을 위해 Model, View, Controller 세가지 영역으로 분리.
- 컴포넌트의 변경이 다른 영역 컴포넌트에 영향을 미치지 않음 (유지보수 용이).
- 컴포넌트 간의 결합성이 낮아 프로그램 수정이 용이 (확장성이 뛰어남).

> 장점
- 화면과 비즈니스 로직을 분리해서 작업 가능.
- 영역별 개발로 인해 확장성이 뛰어남.
- 표준화된 코드를 사용하므로 공동작업이 용이하고 유지보수성이 좋음.

> 단점
- 개발 과정이 복잡해 초기 개발 속도가 늦음.
- 초보자가 이해하고 개발하기에 다소 어려움.

## Model 2 요청 흐름

![Untitled](https://github.com/MJ-Kor/SSAFY11th-Gwangju04-WebStudy/blob/main/MJ-Kor/SSAFYLectures/Spring/imgs/webmvc1.png)

- HttpServlet을 상속 받는다? ⇒ 해당 클래스에 종속적이다.
- 종속적이지 않은 POJO.
- Spring 안에서 내부적으로 request, response를 해준다.

## Spring MVC 특징

- Spring은 DI나 AOP 같은 기능 뿐 아니라 Servlet 기반의 WEB 개발을 위한 MVC Framework를 제공
- Spring MVC는 Model2 Architecture와 Front Controller Pattern을 Framework 차원에서 제공
    - 이전에 if 절로 url의 action 값을 구분하는 기능
- Spring MVC Framework는 Spring기반으로 하고 있기 때문에 Spring이 제공하는 Transaction 처리나 DI 및 AOP등을 손쉽게 사용

## Spring MVC 구성요소

> DispatcherServlet (Front Controller)
- 모든 클라이언트의 요청을 전달받음.
- Controller에게 클라이언트의 요청을 전달하고, Controller가 리턴한 결과 값을 View에게 전달하여 알맞은 응답을 생성.
    - 정확하게는 Controller가 View의 이름을 받음

> HandlerMapping
- Spring이 관리하는 Bean
- 클라이언트의 요청 URL을 어떤 Controller가 처리할지를 결정.
- URL과 요청 정보를 기준으로 어떤 핸들러 객체를 사용할지 결정하는 객체이며, DispatcherServlet은 하나 이상의 핸들러 매핑을 가질 수 있음.

> Controller
- 클라이언트의 요청을 처리한 뒤, Model을 호출하고 그 결과를 DispatcherServlet에 알려준다.

> ModelAndView
- request.setAttribute 와 같은 역할
- Controller가 처리한 데이터 및 화면에 대한 정보를 보유한 객체

> ViewResolver
- Spring Bean
- Controller가 리턴한 view 이름을 기반으로 Controller의 처리 결과를 보여줄 view를 결정

> View
- Controller의 처리결과를 보여줄 응답화면을 생성

## Spring MVC 요청 흐름

![Untitled](https://github.com/MJ-Kor/SSAFY11th-Gwangju04-WebStudy/blob/main/MJ-Kor/SSAFYLectures/Spring/imgs/webmvc2.png)

- ViewResolver는 /foler/view_name.jsp의 주소를 만들어줌

## Spring MVC 실행 순서

![Untitled](https://github.com/MJ-Kor/SSAFY11th-Gwangju04-WebStudy/blob/main/MJ-Kor/SSAFYLectures/Spring/imgs/webmvc3.png)

![Untitled](https://github.com/MJ-Kor/SSAFY11th-Gwangju04-WebStudy/blob/main/MJ-Kor/SSAFYLectures/Spring/imgs/webmvc4.png)

## Spring MVC Project의 기본 구조

![Untitled](https://github.com/MJ-Kor/SSAFY11th-Gwangju04-WebStudy/blob/main/MJ-Kor/SSAFYLectures/Spring/imgs/webmvc5.png)

## Spring MVC 구현

- **Spring MVC를 이용한 Application 구현 Step**
    1. web.xml (or JavaConfig)에 DispatcherServlet 등록 및 Spring 설정 파일 등록
        - Web - Servlet Application Context
        - 비 Web - Root Application Context (Spring 설정 파일)
        - Web에서 비 Web은 사용 가능
        - 비 Web에서 Web은 사용 불가능
    2. 설정 파일에 HandlerMapping 설정
    3. Controller 구현 및 Context 설정 파일(servlet-context.xml, root-context.xml)에 등록
    4. Controller와 JSP의 연결을 위해 View Resolver 설정
        - View Resolver는 servlet-context
    5. JSP 코드 작성
- Controller 작성
    - 좋은 디자인은 Controller가 많은 일을 하지 않고 Service에 처리를 위임
 
## Controller

> @Controller와 @RequestMapping 선언

- method 단위의 mapping 가능
- DefaultAnnotationHandlerMapping에 의해 수행
- Controll Class는 Client의 요청을 처리

> Controller Class를 <bean>에 등록 - beans:bean

```java
<beans:bean class="com.ssafy.board.controller.BoardController">
		<beans:constructor-arg ref="boardService"></beans:constructor-arg>
</beans:bean>
```

> Controll Class 자동 스캔 - context:component-scan

```java
<context:component-scan base-package="com.ssafy.*.controller"/>
```

- base-package에 설정된 package내의 class중 @Controller annotation이 적용된 클래스는 자동 스캔대상이 된다.

> @RequestMapping 선언
- 요청 URL mapping 정보를 설정, 클래스타입과 메소드에 설정 가능
- 같은 URL 요청에 대해 HTTP method(GET, POST,…)에 따라 서로 다른 메소드를 mapping 할 수 있다. (같은 URL이지만 GET으로 부르냐 POST로 부르냐에 따라 method가 달라진다.  약간 메소드 오버로딩 느낌)
- View에서의 @[Get, Post, Put, Delete]Mappping과는 다르게 @[Get, Post, Put, Delete]Mappping( )에서 ( )안의 값은 요청에 대한 목적지를 의미

> Controller method의 parameter type

| Parameter Type | 설명 |
| --- | --- |
| HttpServletRequest, HttpServletResponse, HttpSession | 필요시 Servlet API 사용 가능, 주로 세션과 쿠키가 필요한 로그인 기능 구현 때 사용 |
| Java.util.Locale | 현재 요청에 대한 Locale |
| InputStream, Reader | 요청 컨텐츠에 직접 접근할 때 사용 |
| OutputStream, Writer | 응답 컨텐츠를 생성할 때 사용 |
| @PathVariable annotation 적용 파라미터 | URI 템플릿 변수에 접근할 때 사용 |
| @RequestParam annotation 적용 파라미터 | HTTP 요청 파라미터를 매핑 |
| @RequestHeader annotation 적용 파라미터 | HTTP 요청 헤더를 매핑 |
| @CookieValue annotation 적용 파라미터  | HTTP 쿠키 매핑 |
| @RequestBody annotation 적용 파라미터 | HTTP 요청의 body 내용에 접근할 때 사용 |
| Map, Model, ModelMap | view에 전달할 model data를 설정할 때 사용 |
| Dto | HTTP 요청 parameter를 저장한 객체, 기본적으로 클래스 이름을 모델명으로 사용. @ModelAttribute annotation 설정으로 모델명을 설정할 수 있음 |
| Errors, BindingResult | HTTP 요청 파라미터를 커맨드 객체에 저장한 결과, 커맨드 객체를 위한 파라미터 바로 다음에 위치 |
| SessionStatus | 폼 처리를 완료했음을 처리하기 위해 사용. @SessionAttributes annotation을 명시한 session 속성을 제거하도록 이벤트를 발생시킴 |

> @RequestBody parameter type
- HTTP 요청 Body가 그대로 객체에 전달
- 해당 annotation이 붙은 parameter가 있으면 해당 미디어 타입을 확인한 후 처리 가능한 변환기가 자동으로 객체로 변환시켜 줌
- 주로 @ResponseBody와 함께 사용됨 → 비동기처리

> Servlet API 사용
- HttpSession의 생성을 직접 제어해야 하는 경우
- Controller가 Cookie를 생성해야 하는 경우

```java
@GetMapping("/servletapi")
public String getMethodName(HttpServletRequest request, HttpServletResponse response) {
		return "test";
}
```

> Controller Class에서 method의 return type 종류

| Return Type | 설명 |
| --- | --- |
| ModelAndView | model 정보 및 view를 정보를 담고 있는 객체 |
| Model | view에 전달할 객체 정보를 담고 있는 Model을 반환한다. 이때 view 이름은 요청 URL로부터 결정된다. |
| Map | View에 전달 할 객체 정보를 담고 있는 Map, 이때 view 이름은 요청 URL로부터 결정된다. |
| String  | view의 이름을 반환한다. |
| View | view 객체를 직접 리턴, 해당 View 객체를 이용하여 view를 생성한다. |
| void | method가 ServletResponse나 HttpServletResponse 타입의 parameter를 갖는 경우 method가 직접 응답을 처리한다고 가정한다. 그렇지 않을 경우 요청 URL로부터 결정된 View를 보여준다. |
| @ResponseBody Annotation 적용 | method에서 @ResponseBody annotation이 적용된 경우, 리턴 객체를 HTTP 응답으로 전송한다. HttpMessageConverter를 이용해서 객체를 HTTP 응답 스트림으로 변환한다. |

## View

> View 지정
- Controller에서는 처리 결과를 보여줄 View 이름이나 객체를 리턴하고, DispatcherServlet은 View 이름이나 View 객체를 이용하여 view를 생성
- 명시적 지정
- 자동 지정

> ViewResolver: 논리적 view와 실제 JSP 파일 mapping (servlet-context.xml)

```java
<beans:bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
		<beans:property name="prefix" value="/WEB-INF/views/"/>
		<beans:property name="suffix" value=".jsp"/>
</beans:bean>
```

> 명시적 지정
- ModelAndView와 String 리턴 타입

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/609d46e8-84fd-4b9e-9a41-bd47c7d63d8e/d20fb2d8-7bed-4856-8e6f-01b570ac20db/Untitled.png)

> 자동 지정
- RequestToViewNameTranslator를 이용하여 URL로부터 view 이름을 결정
- 자동 지정 유형
    - return type이 Model이나 Map인 경우
    - return type이 void이면서 ServletResponse나 HttpServletResponse 타입의 parameter가 없는 경우
    
    ![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/609d46e8-84fd-4b9e-9a41-bd47c7d63d8e/eb6dd72d-8528-4100-a576-faa0f1de77b6/Untitled.png)
    

> redirect view
- View 이름에 “redirect:” 접두어를 붙이면, 지정한 페이지로 redirect 됨
- RedirectAttributes를 이용하여 Parameter 전달 가능
    - redirect:/article/list?pgno=1&key=&word=

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/609d46e8-84fd-4b9e-9a41-bd47c7d63d8e/3b4f289b-b080-4eb9-af2d-1002f5889ade/Untitled.png)

## Model

> View에 전달하는 데이터
1. @RequestMapping annotation이 적용된 metghod의 Map, Model, ModelMap
2. @RequestMapping method가 return 하는 ModelAndView
3. @ModelAttribute annotation이 적용된 method가 return 한 객체

> Map, Model, ModelMap을 통한 설정
- method의 argument로 받는 방식

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/609d46e8-84fd-4b9e-9a41-bd47c7d63d8e/b2bc5ced-4d00-4b86-9bfe-f62754d28002/Untitled.png)

> Model 생성
- addAttribute(String name, Object value);
- addAttribute(Object value);
- addAllAttributes(Collection<?> values);
- addAllAttributes(Map<String, ?> attributes);
- mergeAttributes(Map<String, ?> attributes);
- boolean containsAttribute(String name);

> ModelAndView를 통한 Model 설정
- Controller에서 처리결과를 보여줄 view와 view에 전달할 값(model)을 저장하는 용도로 사용
- setViewName(String viewname);
- addObject(String name, Object value);

```java
@GetMapping("/home4")
public ModelAndView() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("msg", "안녕하세요");
		mav.setViewName("home");
		return mav;
}
```
