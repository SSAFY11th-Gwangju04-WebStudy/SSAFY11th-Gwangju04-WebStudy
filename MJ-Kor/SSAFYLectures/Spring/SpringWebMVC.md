## Spring Web MVC

> Model
> 
- 어플리케이션 상태의 캡슐화.
- 상태 쿼리에 대한 응답.
- 어플리케이션의 기능 표현.
- 변경을 view에 통지.

> View
> 
- model을 화면에 시각적으로 표현.
- model에게 업데이트 요청.
- 사용자의 입력을 controller에 전달.
- 컨트롤러가 view를 선택하도록 허용.

> Controller
> 
- 어플리케이션의 행위 정의.
- 사용자 액션을 model 업데이트와 mapping.
- 응답에 대한 view 선택.

---

- 어플리케이션의 확장을 위해 Model, View, Controller 세가지 영역으로 분리.
- 컴포넌트의 변경이 다른 영역 컴포넌트에 영향을 미치지 않음 (유지보수 용이).
- 컴포넌트 간의 결합성이 낮아 프로그램 수정이 용이 (확장성이 뛰어남).

> 장점
> 
- 화면과 비즈니스 로직을 분리해서 작업 가능.
- 영역별 개발로 인해 확장성이 뛰어남.
- 표준화된 코드를 사용하므로 공동작업이 용이하고 유지보수성이 좋음.

> 단점
> 
- 개발 과정이 복잡해 초기 개발 속도가 늦음.
- 초보자가 이해하고 개발하기에 다소 어려움.

## Model 2 요청 흐름

---

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/609d46e8-84fd-4b9e-9a41-bd47c7d63d8e/1994a625-d96d-4cd2-87ba-68af5df5ee85/Untitled.png)

- HttpServlet을 상속 받는다? ⇒ 해당 클래스에 종속적이다.
- 종속적이지 않은 POJO.
- Spring 안에서 내부적으로 request, response를 해준다.

## Spring MVC 특징

---

- Spring은 DI나 AOP 같은 기능 뿐 아니라 Servlet 기반의 WEB 개발을 위한 MVC Framework를 제공
- Spring MVC는 Model2 Architecture와 Front Controller Pattern을 Framework 차원에서 제공
    - 이전에 if 절로 url의 action 값을 구분하는 기능
- Spring MVC Framework는 Spring기반으로 하고 있기 때문에 Spring이 제공하는 Transaction 처리나 DI 및 AOP등을 손쉽게 사용

## Spring MVC 구성요소

---

> DispatcherServlet (Front Controller)
> 
- 모든 클라이언트의 요청을 전달받음.
- Controller에게 클라이언트의 요청을 전달하고, Controller가 리턴한 결과 값을 View에게 전달하여 알맞은 응답을 생성.
    - 정확하게는 Controller가 View의 이름을 받음

> HandlerMapping
> 
- Spring이 관리하는 Bean
- 클라이언트의 요청 URL을 어떤 Controller가 처리할지를 결정.
- URL과 요청 정보를 기준으로 어떤 핸들러 객체를 사용할지 결정하는 객체이며, DispatcherServlet은 하나 이상의 핸들러 매핑을 가질 수 있음.

> Controller
> 
- 클라이언트의 요청을 처리한 뒤, Model을 호출하고 그 결과를 DispatcherServlet에 알려준다.

> ModelAndView
> 
- request.setAttribute 와 같은 역할
- Controller가 처리한 데이터 및 화면에 대한 정보를 보유한 객체

> ViewResolver
> 
- Spring Bean
- Controller가 리턴한 view 이름을 기반으로 Controller의 처리 결과를 보여줄 view를 결정

> View
> 
- Controller의 처리결과를 보여줄 응답화면을 생성

## Spring MVC 요청 흐름

---

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/609d46e8-84fd-4b9e-9a41-bd47c7d63d8e/7aa58e4c-ac6a-402a-8543-b6b4f9387485/Untitled.png)

- ViewResolver는 /foler/view_name.jsp의 주소를 만들어줌

## Spring MVC 실행 순서

---

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/609d46e8-84fd-4b9e-9a41-bd47c7d63d8e/80626c39-d478-4805-b846-8386f2954c9d/Untitled.png)

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/609d46e8-84fd-4b9e-9a41-bd47c7d63d8e/34067ccf-1cbd-4be4-ba19-c216e7b0df87/Untitled.png)

## Spring MVC Project의 기본 구조

---

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/609d46e8-84fd-4b9e-9a41-bd47c7d63d8e/726069d1-00e0-4cd2-8581-84993bd06d1e/Untitled.png)

## Spring MVC 구현

---

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
    
    ![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/609d46e8-84fd-4b9e-9a41-bd47c7d63d8e/b80f2648-94f1-4dac-9704-de739c50b512/Untitled.png)
