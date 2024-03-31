# 과목 평가 정리

---

## URL 매핑 방법

- web.xml
    - 각 servlet으로 이동할 수 있는 url을 매핑할 수 있다
    - <servlet>과 <servlet-mapping>이 한 쌍
        - <servlet>과 <servlet-mapping>의 <servlet-name>이 일치해야 한다.
        - <servlet-mapping>의 <url-pattern>에 매핑할 url을 입력하면 된다.
        - <url-pattern>을 여러 개 두어서 여러 개를 매칭할 수 있다.
            
            ![Untitled](%E1%84%80%E1%85%AA%E1%84%86%E1%85%A9%E1%86%A8%20%E1%84%91%E1%85%A7%E1%86%BC%E1%84%80%E1%85%A1%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%20bae25f97168f40cbbdd5412b6790b964/Untitled.png)
            
- @WebServlet
    - servlet 파일 내부에 어노테이션을 등록하여 url을 매핑할 수 있다.
    - 한개의 URL을 설정하려면 @WebServlet(”url”) 형식으로 작성하면 된다.
    - 여러개의 URL을 설정하려면 아래와 같이 작성하면 된다.
        
        ```java
        @WebServlet(
        				name = "ServeltName",
        				urlPatterns = {"/url1", "/url2"},
        				loadOnStartup = 1
        				)
        ```
        

## Servlet

- 특징
- http servlet life cycle
- init 메서드, service 메서드가 왜 있나?
- 관련 객체 및 메서드들
    - http session
    - servlet context 등
    - 객체들이 가지고 있는 getParameter, getAttribute 이런 메서드들

- 특징
    - 웹 페이지를 동적으로 생성하는 서버측 프로그램이며, 자바 클래스의 일종이다.
    - Java code 안에 HTML을 포함하고 있다.
    - 외부 요청마다 프로세스보다 가벼운 스레드로써 응답하므로 보다 가볍다.
- Servlet Life-Cycle
    - request → Constructor → init() → service() → doGet(), doPost() → destroy()
    - Servlet class는 main method가 없다.
        - 객체의 생성부터 사용의 주체가 사용자가 아닌 Servlet Container에게 있다.
    - Client가 요청을 하게 되면 Servlet Container는 Servlet 객체를 한번만 생성 및 초기화 하며, 요청에 대한 처리를 반복하게 된다.
        - 코드가 수정되면 다시 호출된다.
        - 요청이 올때마다 Servlet을 만드는 것이 아닌 최초 요청시 한번만 만들고 재사용
    - Servlet 객체가 필요 없게 되면 제거하는 일까지 Container가 담당하게 된다.
- Servlet Life-Cycle method
    1. init() : servlet이 메모리에 로드될 때 한 번 호출
    2. doGet() : GET 방식으로 data 전송 시 호출
    3. doPost() : POST 방식으로 data 전송 시 호출
    4. service() : 모든 요청은 service()를 통해서 doXXX() 메소드로 이동
    5. destroy() : servlet이 메모리에서 해제되면 호출
- Service() ?
    - public과 protected service가 존재
    - public은 client의 요청을 받아 protected service에게 전달해준다.
    - protected service는 public이 보낸 것을 받고, servlet에 정의된 doXXX 함수로 요청을 전달한다.
    - [https://jakarta.ee/specifications/platform/9/apidocs/](https://jakarta.ee/specifications/platform/9/apidocs/)
- Servlet 관련 객체들 및 메서드
    1. HttpServletMapping
    2. HttpServletRequest
        1. getContextPath()
        2. getCookies()
        3. getSession()
        4. getParameter() - String
        5. getParameterValues(String name) - String[]
        6. getRequestDispatcher()
        7. getAttribute()
        8. setAttribute()
    3. HttpServletResponse
        1. addCookie(Cookie cookie)
        2. sendRedirect(String location)
    4. HttpSession
        1. getAttribute(String name)
        2. setAttribute(String name, Object value)
    5. HttpServlet
        1. doGet
        2. doPost
        3. doPut
        4. doDelete
        5. doHead
        6. service
        7. doTrace
    

## JSP

- 주요 내장 객체 및 메서드
- 스크립틀릿(<%, <%= 등) 별 기능 및 사용법

- JSP 지시어
    - page Directive
        - 컨테이너에게 현재 JSP 페이지를 어떻게 처리할 것인가에 대한 정보를 제공한다.
            - <%@ page attr1=”val” attr2=”val2” … %>
    - include Directive
        - 특정 jsp file을 페이지에 포함
            - <%@ include file=”jsp 경로” %>
    - tag library Directive
        - JSTL 또는 사용자에 의해서 만든 커스텀 태그를 이용할 때 사용
            - <%@ tablib prefix=”c” uri=”http://java.sum.com/jsp/jstl/core” %>
- 기본 객체
    - request
        - HTML 폼 요소의 선택 값 등 사용자 입력 정보를 읽어올 때
    - response
        - 사용자 요청에 대한 응답을 처리하기 위해 사용
    - pageContext
        - 각종 기본 객체를 얻거나 forward 및 include 기능을 활용할 때 사용
    - session
        - 클라이언트에 대한 세션 정보를 처리하기 위해 사용
    - application
        - 웹 서버의 애플리케이션 처리와 관련된 정보를 레퍼런스하기 위해 사용
    - out
        - 사용자에게 전달하기 위한 output 스트림을 처리할 때 사용
    - config
        - 현재 JSP에 대한 설정 정보를 포함하는 객체
    - page
        - 현재 JSP 페이지에 대한 참조 변수에 해당됨
- 기본 객체의 영역
    - page
        - 하나의 page에 관련된 영역
    - request
        - 하나의 요청에 관련된 영역
        - request가 저장한 속성은 그 요청에 대한 응답이 완료되면 사라진다
    - session
        - 하나의 웹 브라우저와 관련된 영역
        - 같은 웹브라우저 내에서 요청되는 페이지들은 같은 session을 공유
    - application
        - 하나의 웹 어플리케이션과 관련된 영역
        - 같은 웹 어플리케이션에서 요청되는 페이지들은 같은 application 객체를 공유
- 기본 객체의 method
    - setAttribute(String name, Object value) : 문자열 name 이름으로 Object형 데이터를 저장
    - getAttribute(String name) : 문자열 name에 해당하는 속성 값이 있다면 Object형으로 가져옴, 없으면 null return
    - getAttributeNames() : 현재 객체에 저장된 속성들의 이름들을 Enumeration 형태로 가져온다.
    - removeAttribute(String name) : 문자열 name에 해당하는 속성을 삭제한다.
- Scripting Element
    - 선언문 : 멤버 변수 선언이나 메소드를 선언하는 영역
        - <%! 멤버변수와 method 작성 %>
    - 스크립트릿 : client 요청 시 매번 호출 영역으로, Servlet으로 변환 시 service() method에 해당되는 영역
        - <% java code %>
    - 표현식 : 데이터를 브라우저에 출력할 때 사용
        - <%= 문자열 %>
        - 문자열 뒤에 세미콜론은 작성하지 않는다
    - 주석 : 코드 상에서 부가 설명을 작성
        - <%-- 주석 code --%>

## Cookie

- 개념 및 특징
- 생성, 사용과 관련된 객체 및 메서드들

- 개념
    - 서버에서 사용자의 컴퓨터에 저장하는 정보 파일 (String)
    - 사용자가 별도의 요청을 하지 않아도 브라우저는 request시 Header에 넣어 자동으로 서버에 전송
    - key와 value로 구성되고 String 형태로 이루어져 있다.
    - Browser마다 저장되는 쿠키는 다르다.\
- 사용 목적
    - 세션 관리
    - 개인화
    - 트래킹
- Cookie의 구성 요소
    - 이름 : 쿠키를 구별하기 위해 사용됨
    - 값 : 쿠키의 이름과 매핑되는 값
    - 유효기간 : 쿠키의 유효기간 (setMaxAge)
    - 도메인 : 쿠키를 전송할 도메인 (어느 도메인에서만 사용 할 것인가?)
    - 경로 : 쿠키를 전송할 요청 경로 (특정 도메인의 페이지)
- Cookie의 동작 순서
    1. client가 페이지 요청
    2. WAS는 Cookie 생성
    3. HTTP Header에 Cookie 넣어 응답
    4. Broswer는 Cookie를 client의 컴퓨터에 저장하고,  다시 요청을 보낼 때 header에 Cookie를 태워 전송
    5. Browser가 종료되어도 Cookie의 수명이 남아 있다면 저장
    6. 동일 사이트 재 방문시 client의 PC에 해당 Cookie가 있는 경우, 요청 페이지와 함께 Cookie를 전송
- Cookie의 특징
    - 이름, 값, 만료일, 경로 정보로 구성되어 있다.
    - 브라우저 별로 50 ~ 180개의 쿠키를 저장할 수 있다.
    - 하나의 도메인 당 약 20개의 쿠키를 가질 수 있다.
    - 하나의 쿠키는 4KB까지 저장 가능하다.
    - 오로지 String만 저장 가능!!!!
- Cookie 주요 메서드
    - new Cookie(String name, String value);
    - cookie.setValue(String value)
    - String value = cookie.getValue()
    - cookie.setDomain(String domain)
    - String domain = cookie.getDomain()
    - cookie.setPath(String path)
    - String path = cookie.getPath()
    - cookie.setMaxAge(int expiry)
    - int expiry = cookie.getMaxAge()
    - cookie.setMaxAge(0) → 쿠키 삭제
    - response.addCookie(cookie)
    - Cookie cookies[] = request.getCookies()

## Session

- 개념 및 특징
- 생성, 사용과 관련된 객체 및 메서드들

- 개념
    - 방문자가 웹 서버에 접속해 있는 상태를 하나의 단위로 보고 그것을 세션이라 한다.
    - WAS의 memory에 Object 형태로 저장
    - memory가 허용하는 용량까지 제한없이 저장 가능
    - 휘발성
- 동작 순서
    1. client가 페이지를 요청
    2. 서버는 request-header 필드인 Cookie를 확인하여 클라이언트가 해당 session-id를 보냈는지 확인
    3. session-id가 존재하지 않는다면, 서버는 session-id를 생성해 client에게 돌려준다.
    4. 서버에서 client로 돌려준 session-id를 Cookie를 사용해 서버에 저장한다.
    쿠키 이름 : JSESSIONID
    5. client는 재접속 시, 이 Cookie를 사용하여 session-id를 서버에 전달한다.
- session의 특징
    - 웹 서버에 웹 컨테이너의 상태를 유지하기 위한 정보를 저장한다.
    - 웹 서버에 저장되는 쿠키(=세션 쿠키)
    - 브라우저를 닫거나, 서버에서 세션을 삭제했을 때만 삭제가 되므로, 쿠키보다 비교적 보안이 좋다.
    - 저장 데이터에 제한이 없다.
    - 각 클라이언트 고유 session id를 부여한다.
    - session id로 client를 구분하여 각 client 요구에 맞는 서비스를 제공한다.

## JSTL

- 사용 설정
- 사용 core 라이브러리 중에서 주요 태그들(c: set, c: if 등)

- JSTL Tag
    - <%@ taglib prefix=”prefix” uri=”uri” %>
    - prifix
        - c : 변수 지원, 흐름제어, URL 처리 ⇒ http://java.sun.com/jsp/jstl/core
        - x : XML 코어, 흐름제어, XML 변환 ⇒ http://java.sun.com/jsp/jstl/xml
        - fmt : 지역, 메시지 형식, 숫자 및 날짜 형식 ⇒ http://java.sun.com/jsp/jstl/fmt
        - sql : SQL ⇒ http://java.sun.com/jsp/jstl/sql
        - 없음 : Collection, String 처리 ⇒ http://java.sun.com/jsp/jstl/functions
- c prifix의 주요 태그
    1. set : jsp page에서 사용할 변수 설정
        - <c : set value=”value” var = “varName” [scope = “{page|request|session|applicationj}” />
        - value의 속성 값이나 액션의 Body content로 값을 설정
        - 특정 객체의 프로퍼티에 값을 할당할 때는 target 속성에 객체를 설정하고 property에 프로퍼티명 설정
    2. if : 조건에 따른 코드 실행
        - <c : if test=”표현식” var = “var”> </c : if>
        - var은 표현식의 평가 결과인 Boolean 값을 담을 변수를 나타낸다.
    3. choose, when, otherwise : if ~ else if ~ else
        - <c : choose> 
              <c : when test=”표현식”>
              </c : when>
              <c : otherwise>
              </c : otherwise>
        </c : choose>
    4. forEach : array나 collection의 각 항목을 처리할 때 사용
        - <c : for Each var=”var” items=”${vars}”> </c : forEach>
        - var에는 값, items는 컬렉션이 지정
    5. catch : Exception 처리에 사용
        - <c : catch var=”ex”> </c : catch>
        - var에는 발생한 예외가 담김

## EL

- JSP 구문 EL로 변환
- JSP 페이지에서 EL 적용 결과 예측

- 개념
    - JSP에서 <%=%>를 대체할 수 있다.
    - 도트 연산자 왼쪽은 반드시 [java.util.Map](http://java.util.Map) 또는 Java Bean(Dto) 객체여야 한다.
    - 도트 연산자 오른쪽은 반드시 맵의 키이거나 Bean 프로퍼티여야 한다.
- [ ] 연산자
    - EL에는 Dot 표기법 외에 [ ] 연산자를 사용하여 객체의 값에 접근할 수 있다.
    - [ ]안의 값이 문자열일 경우 맵의 키가 될 수 있다
    - 주의할 점은 객체가 배열과 리스트인 경우 문자로된 인덱스 값은 숫자로 변경되어 처리된다.
- EL 사용
    - pageContext를 제외한 모든 EL 내장 객체는 Map이다.
    - 그러므로 key와 value의 값을 저장하고 있다.
    - 기본 문법
        - ${expr}
- 객체 접근
    - request.setAttribute(”userinfo”, “김민주”);
        1. ${requestScope.userinfo}
        2. ${pageContexst.request.userinfo}
        3. ${userinfo}
    - url?name=김민주&fruit=수박
        1. ${param.name}
        2. ${paramValues.fruit[0]}
    - ${cookie.id.value}
        - Cookie가 null 이라면 null return
        - null이 아니라면 id 검사후 null 이라면 null return
        - null이 아니라면 value 값 검사
        - null이라도 null을 출력하지 않는다, 빈 문자열 출력
    - request.getAttribute(”users”).getSize()
        - ${users.sizes()}
        - EL에서는 getter 에서 get을 빼고 대문자를 소문자로 바꿔 사용한다.
- EL Operator (모르는 것만)
    - eq (==)
    - ne (≠)
    - lt (<)
    - gt(>)
    - le(≤)
    - ge(≥)
    - empty (타당성 검사, 비었니?라고 생각하자)

## 클라이언트 요청 방식

    - get, post

- GET
    - 특징
        - 전송되는 데이터가 URL 뒤에 Query String으로 전달.
        - 입력 값이 적은 경우나 데이터가 노출이 되도 문제가 없을 경우에 사용
    - 장점
        - 간단한 데이터를 빠르게 전송
        - form tag 뿐만 아니라 직접 URL에 입력하여 전송 가능
    - 단점
        - 데이터 양에 제한(2KB)

- POST
    - 특징
        - URL과 별도로 전송
        - BODY에 입력 스트림으로 데이터 전달
    - 장점
        - 데이터의 제한이 없다.
        - 최소한의 보안 유지 효과를 볼 수 있다.
    - 단점
        - 전달 데이터의 양이 같은 경우 GET 방식보다 느리다.

## Servlet에서 처리 방법

    - Service안에 doget, dopost가 존재

## 에러 응답 코드들

- 200 : OK
- 302 : Found
- 400 : Bad Request(잘못된 문법)
- 401 : Unauthorized(비인증)
- 403 : Forbidden(권한이 없음, 서버가 클라이언트가 누군지는 알고 있음)
- 404 : Not Found(서버가 요청받은 리소스를 찾을 수 없음)
- 408 : Request Timeout(요청 시간 초과)
- 500 : Internal Server Error(서버가 처리 방법을 모름)
- 501 : Not Implemented(서버에서 지원되지 않음)
- 502 : Bad Gateway(서버가 요청을 처리하는데 필요한 응답을 얻기 위해 게이트 웨이로 작업하는 동안 잘못된 응답을 수신했음을 의미)
- 503 : Service Unavailable(서버가 요청을 처리할 준비가 되지 않음)
- 504 : Gateway Timeout(적시에 응답을 받을 수 없을때)
- 505 : HTTP Version Not Supported(요청에 사용된 HTTP 버전은 서버에서 지원되지 않는다)

## 페이지 이동 방식의 구분 및 설명(서술형)

- forward(request, response)
    - 사용 방법
        - RequestDispatcher dispatcher = request.getRequestDispatcher(path);
        - dispatcher.forward(request, response);
    - 이동 범위
        - 동일 서버(project) 내 경로
    - location bar
        - 기존 URL 유지 (실제 이동되는 주소 확인 불가)
    - 객체
        - 기존의 request와 response가 그대로 전달
    - 속도
        - 비교적 빠름
    - 데이터 유지
        - request의 setAttribute(name, value)를 통해 전달

- sendRedirect(location)
    - 사용 방법
        - response.sendRedirect(location);
    - 이동 범위
        - 동일 서버 포함 타 URL 가능
    - location bar
        - 이동하는 page로 변경
    - 객체
        - 기존의 request와 response는 소멸되고 새로운 request와 response가 생성
    - 속도
        - forward()에 비해 느림
    - 데이터 유지
        - request로는 data 저장 불가능
        - session이나 cookie를 이용
        

## MVC 패턴의 동작흐름(서술형)

- Model1
    - view와 logic을 JSP 하나에서 처리하는 구조 (view + controller)
    - client로 부터 요청이 들어오게 되면 JSP는 java beans나 별도의 service class를 이용하여 작업을 처리하고, 결과를 client에 출력한다.
- Model2
    - 모든 처리를 JSP 페이지에서 하는 것이 아니라, client 요청에 대한 처리는 servlet이, logic 처리는 javaclass(Service, Dao,,,), client에게 출력하는 response page를 JSP가 담당한다.
    - model2 구조는 MVC(Model-View-Controller) pattern을 웹 개발에 도입한 구조, 완전히 같은 형태를 보인다.
    - 구조
        - Model - Service, Dao, Dto : Logic(Business & DB Logic)을 처리하는 모든 것
        - View -JSP : 모든 화면 처리를 담당, Logic 처리를 위한 java code는 사라지고 결과 출력을 위한 code만 존재한다.
        - Controller - Servlet : 요청 분석, logic 처리 위한 model 단 호출, data를 request, session에 저장하고 redirect 또는 forward 방식으로 jsp page를 이용하여 출력한다.
    - 동작 흐름
        1. client가 요청을 보냄, 요청을 보내는 화면단은 View인 jsp가 담당한다.
        2. Controller인 Servlet은 요청을 분석하여 Logic 처리를 위한 Service를 호출한다.
        3. Service는 이에 맞는 Dao 메서드를 호출하고 Dao는 DB에 접근하여 필요한 데이터로 결과를 만들어 낸다. 이때 controller에서 넘어온 data를 이용할 수도 있다.
        4. Dao는 Service에게 Service는 다시 controller에게 결과를 return하고, jsp로 요청 결과를 client에게 출력한다.