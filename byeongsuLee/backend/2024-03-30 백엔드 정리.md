# 백엔드 과목평가

- 1번 페이지이동 과 관련된 객체 및 메소드 , url 매핑방법
    - 페이징 이동 방법
        - <a href =”/member?aciton=mvmain>
        - response.sendRedircet(reqeust.getContextPath()+path);
        - RequestDispatcher dispatcher = request.getRequestDispatcher(path);
        dispatcher.forward(request, response);
        - location.href = "<%=root%>/member?action=mvjoin";
        - form action=”root/member”
    - url 매핑
        - web.xml  : </url-pattern>/book</url-pattern>
        - @WebServlet(”/book”)
    

- 서블릿
    - 정의 : 자바를 사용하여 웹 페이지를 동적으로 생성하는 서버 측 프로그램
    - 특징
        - request에 대해 동적으로 작동
        - HttpServlet 상속 받아 원하는 메서드만 구현 가능
        - 자바 코드 안에 html 코드 포함
        - 자바로 구현되어 다양한 플랫폼에서 동작가능
        - 외부 요청마다 가벼운 스레드로 응답하므로 보다 가볍다.
    - 동작 흐름
        1. 사용자 요청 처리 및 데이터 받기
        2. 비지니스 로직 처리
        3. 응답 페이지  생성 및 전달
    - http 서블릿 life사이클
        - 객체의 생성과 사용의 주체는 서블릿 컨테이너다
        - 생성자  한번 , 초기화 init() 한번 , service(doget,dopost) 요청시마다 반복, destory (한번) 필요없어지면 제거
    - 관련 객체 및 메서드
        - **HttpServletRequest의 주요 메서드**
            - **getParameter(String name): 요청 파라미터 값을 가져옵니다.**
            - **.getSession(): 클라이언트의 세션을 가져옵니다.**
        - **HttpServletResponse의 주요 메서드**
            - **setContentType(String type): 응답의 콘텐츠 타입을 설정합니다.**
            - **getWriter(): 응답을 위한 PrintWriter 객체를 가져옵니다.**
            - **sendRedirect(String location): 클라이언트를 다른 위치로 리다이렉트합니다.**
        
        - 제네릭 서블릿
            - 추상클래스로 일반적인 서블릿기본 동작을 구현하고 서브클래스에서 이를 확장하여 구체적인 기능을 추가 할 수 있다.
            - 제네릭 서블릿을  상속 받아 새로운 서블릿을 만들면 , 서블릿의 메서드들을 오버라이드 하여 개별적인 요청 처리 로직을 구현할 수 있다.
        - httpSession
            - 클라이언트와 서버간의 상태를 유지하기 위한 객체로, 클라이언트의 세션 정보를 저장하고 관리한다.
            - **HttpSession 객체는 사용자의 세션에 대한 데이터를 저장하고 필요한 경우에 이를 검색할 수 있는 메서드를 제공합니다**
            - 주요 메서드
                - **setAttribute(String name, Object value): 세션에 속성을 설정합니다.**
                - **getAttribute(String name): 세션에서 속성 값을 가져옵니다.removeAttribute(String name): 세션에서 속성을 제거합니다.**
                - **invalidate(): 세션을 무효화합니다.**
        
    - Jsp
        - 정의  : html안에 java코드를 넣는 것
        - 장점 : servlet-html안의 java데이터를 입력의 불편함을 개선
        - 단점 : 코드 작성 그래도 불편함, 유지보수 힘듬
        - 동작방식 : 데이터 받기 → 로직처리 db → 응답 page 보내주기
        - 표현방식
            - 선언문 - <%! > 전역변수나 함수 선언
            - <% > : 요청관련부분 , request,response 데이터 표현
            - 표현문  : <%= > 데이터 표현
        - page 이동 2가지
            - forwad(request,response) :  jsp 데이터 들고 page이동 , reqeust사용 및Attribute로 데이터 저장 및 꺼내오기
            - sendRedirect(”갈곳”)
                - response 사용
        
        - jsp 작성시 주의할 점
            - /  : 절대경로 =context root를 말한다 .
            - /안쓰면 상대경로를 의미한다. = 자기 기준
            - 예시) main-src-webapp에있는 a,b 페이지가 있을때 a에서  herf=”b.jsp” 하면 실행됨

3 jsp

- 주요 내장객체 및 메서드
- 스크립트릿 별 기능 별 사용법
    - 주석,~~자바코드,request 가 적용범위?
    - 스크립트 릿
        - clinet 요청시 매번 호출 영역으로 servlet 변환시 , 서비스 method에 해당되는 영역
        - <%  자바코드  %>
    - 선언문
        - <%! 자바코드 %>
    - 주석
        - 선언된 변수는 전역변수로 됨
        - <%-- 주석 할 code --%>
    - 표현식
        - <%=문자열 %>   = out.print(name);
    
    page
    
    - setAttribute한 값은 현재 페이지까지만 쓸수 있다.
    
    requst 
    
    - 다음페이지 까지만 생존
    - 요청하고 나서 다시 못씀
    
    session
    
    - session true 인 곳이면 어디서든 쓸 수 있다.
    - but 특정시간동안만이다.
    - servers - web.xml - session timeout에서 시간 설정할 수 있다.
    
    application 
    
    - 어디서든 쓸 수 있다.
    - 단점 : but , application에 너무많이 담아두면 메모리 문제가 발생함
    - 사용 : project에서 사용하는 공통값들을 담을 떄 사용

cookie sesion 개념및 특징

- 쿠키세션에 생성 ,사용 관련된 메서드들
    - **쿠키 생성: new Cookie(String name, String value)**
    - **쿠키 응답 헤더에 추가: response.addCookie(Cookie cookie)**
    - 쿠기 가져오기   Cookie[] cookies = request.getCookies();
    - **세션 생성: request.getSession()**
    - **세션 속성 설정: session.setAttribute(String name, Object value)**
    - **세션 속성 가져오기: session.getAttribute(String name)**

1. jstl 부분
- jstl 사용 설정
    - maven jstl + jstl 구현  or  jar파일 다운받기
    - `<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>`
- 코어 라이브러리에서 주요태그들
    - <c:if test="${condition}"<!-- 조건이 참일 때 수행할 내용 --></c:if>
    - <c:forEach var="item" items="${collection}"><!-- 반복 처리할 내용, ${item}을 통해 현재 요소에 접근 --></c:forEach>
    - `<c:set var="variableName" value="${expression}" />`

1. el
- jsp 부분 el로 변환하기
    - `String name = (String) request.getAttribute("name")` = ${name}
    - <c:if test="${not empty name}">
    <p>Hello, ${name}</p>
    </c:if>

7

client 요청방식  : get, post

서블릿에서 처리 방법 : 요청들어왔을때 doget , dopost      httpservlet에서 service  두개 구분하는 메서드

1. **doGet 및 doPost 메서드 오버라이딩:클라이언트의 요청이 GET 방식일 때는 `doGet` 메서드를, POST 방식일 때는 `doPost` 메서드를 오버라이딩하여 해당 요청을 처리합니다.**

```java
@Override
protected void doGet(HttpServletRequest request, HttpServletResponse response) {
// GET 방식 요청 처리// 필요한 로직을 구현
}

@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response) {
// POST 방식 요청 처리// 필요한 로직을 구현
}
```

1. **service 메서드 사용:만약 모든 HTTP 메서드에 대해 동일한 로직을 수행해야 한다면 `service` 메서드를 오버라이딩하여 모든 요청을 처리할 수 있습니다.**

```java
@Override
protected void service(HttpServletRequest request, HttpServletResponse response) {
    if (request.getMethod().equals("GET")) {
// GET 방식 요청 처리// 필요한 로직을 구현
    } else if (request.getMethod().equals("POST")) {
// POST 방식 요청 처리// 필요한 로직을 구현
    }
}
```

- 에러응답코드 - 404 , 500 , 403
    - 404  -  페이지가 존재하지않음
    - 403 - 페이지 접근 금지
    - 500  - 서버내부 오류

서술형 

페이지 이동 방식의 구분 방식 및 설명

서버측

- forward : 주소 표시줄의 url 이 변경하지않고 데이터를 포함해서 이동,새로고침시 다시실행
- redirect :  주소 변경되고 데이터를 포함하지않고 이동,새로고침시 다시실행 x

mvc패턴의 동작흐름

- 사용자 요청 : 클라이언트가 서버로 요청보냄
- 컨트롤러  servlet:  클라이언트의 요청처리를 위한 model 단 호출 및 return 받은 결과를 request session 등에 저장하고 redirect와 forward 방식으로 page 이동
- model : 비지니스 로직을 처리하고 데이터를 가져와서 가공하여 컨트롤러 전달
- view : 컨트롤러는 모델로부터 받은 데이터를 뷰에 전달하여 사용자에게 보여준다.
- 모든 처리를 jsp 하는것이 아니라 client 요청처리 = sevlet, logic 처리는 java class , client 출력하는 response page = jsp 담당하는 구조
