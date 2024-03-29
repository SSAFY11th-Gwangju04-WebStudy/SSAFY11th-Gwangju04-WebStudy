# 2. servlet

### Hello 서블릿
- 서블릿은 WAS 서버를 직접 설치, 클래스 파일로 빌드 -> 톰캣 서버 실행
- @WebServlet -> 서블릿 애노테이션 -> service 메서드 자동 호출
	- name: 서블릿 이름
	- urlPatterns: URL 매핑
- 옵션 넣기 (application.properties 에 'logging.level.org.apache.coyote.http11=debug' 추가
### Request, Response 사용법
- 헤더 정보 조회...
- 쿠키, content 정보, locale 등을 조회할 수 있음

### HTTP 요청 데이터 개요
1. GET - 쿼리 파라미터 (검색, 필터, 페이징) : 헤더에 쿼리파라미터
2. POST - form : 바디에 쿼리파라미터
	- 서버 입장에서 받아 쓸때는 별 의미 없음
	- content-type = [application/x-www-form-urlencoded]
3. HTTP message body (JSON, XML, TEXT 등) post, put, patch
	- messageBody를 인코딩, objectMapper 로 객체로 변환해 사용
	- 나중에는 request, response 대신 Json 객체를 바로 받을수 있음

### HttpServletResponse 기본 사용법
- 역할
	1. Http 응답코드 지정
	2. 해더 생성
	3. 바디 생성 
	4. 편의 기능 (Content-Type, 쿠키 관리, Redirect)

- 응답데이터
	1. 단순 텍스트 ()
	2. HTML 응답 .setContentType("text/html"); .setCharacterEncoding("utf-8"); 등을 사용후 writer로 쓰기
	3. JSON
- 정리
	- 표준 Http 스펙을 사용하기 편하게 제공하는 것이 HttpServletResponse, Request 
	- cli -> Server (Request) 요청 데이터
		1. GET 방식 .getParameter
		2. POST - form .getParameter
		3. Http body에 직접 전달 - 주로 JSON (post, put, patch)
	- Server -> cli (Response) 응답 데이터
		1. 단순 텍스트, HTML
		2. JSON 등 API
			- Content-Type 과 encoding만 잘 맞춰주기
