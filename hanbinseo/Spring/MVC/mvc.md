# Spring MVC에 대한 공부를 기록하는 공간입니다

<details>
  <summary>Section 0, 1</summary>  
  
  - 웹 서버
    - HTTP 기반으로 동작
    - Client → Server : Request
    - Server → Client : Response
  - 서블릿
    - urlPatterns(url)로 정의한 url을 호출 시 서블릿 코드 실행
    - HTTP 요청 정보를 편리하게 사용할 수 있는 HttpServletRequest
    - HTTP 응답 정보를 편리하게 제공할 수 있는 HttpServletResponse
    - 서블릿 컨테이너
        - 서블릿 객체를 싱글톤으로 관리
        - 동시 요청을 위한 멀티 쓰레드 처리 지원
- 쓰레드
    - Application 코드를 하나하나 순차적으로 실행하는 것 → 쓰레드
    - 동시 처리가 필요하면 쓰레드 추가로 생성
    - 요청 마다 쓰레드 생성
        - 장점
            1. 동시 요청 처리 가능
            2. 리소스(CPU, 메모리)가 허용할 때 까지 처리 가능
            3. 하나의 쓰레드가 지연 되어도, 나머지 쓰레드는 정상 동작
        - 단점
            1. 생성 비용이 매우 비쌈 → 요청시마다 생성하면 응답 속도가 늦어짐
            2. 컨텍스트 스위칭 비용 발생
            3. 생성에 제한이 없어서 요청이 너무 많아질 시 서버가 죽을 수 있음
    - 쓰레드 풀
        - 특징
            - 필요한 쓰레드를 쓰레드 풀에 보관, 관리
            - 쓰레드 풀에 생성 가능한 쓰레드의 최대치를 관리
                - 톰캣은 최대 200개 기본 설정 (변경 가능)
        - 사용
            - 쓰레드 필요 시, 생성되어 있는 쓰레드를 풀에서 꺼내서 사용
            - 사용 종료 시 풀에 반납
            - 모두 사용중으로 풀에 쓰레드가 없을 시 기다리는 요청 거절하거나 특정 수만큼 대기하도록 설정 가능                
        - 장점
            - 쓰레드가 미리 생성되어 있으므로, 생성하고 종료하는 비용 절감, 응답 빠름
            - 생성 가능한 쓰레드의 최대치가 있으므로 요청 과다시 기존 요청은 안전하게 처리 가능
        - 실무 팁
            - WAS의 주요 튜닝 포인트 → 최대 쓰레드 수
                - 낮을 시 → 동시 요청이 많으면 서버 리소스는 여유롭지만 클라이언트는 금방 응답 지연
                - 높을 시 → 동시 요청이 많으면 CPU, 메모리 리소스 임계점 초과로 서버 다운
            - 장애 발생 시
                - 클라우드면 일단 서버부터 늘리고 이후에 튜닝
                - 아니면 열심히 튜닝
</details>

<details>
  <summary>Section 2</summary>
  
  > 인증
    ![section2](https://github.com/hanbinseo/SSAFY11th-Gwangju04-WebStudy/assets/79882952/2b27f4e9-a01e-4014-93b2-3ffb32559439)

  - HttpServletRequest
    - HTTP 요청 데이터
    - GET
    - POST HTML form
    - API 메시지 바디
      - 단순 텍스트
      - JSON
  - HttpServletResponse
    - HTTP 응답 데이터
      - 단순 텍스트, HTML
      - API JSON
</details>

<details>
  <summary>Section 3</summary>
  
  > 인증
    ![section3](https://github.com/hanbinseo/SSAFY11th-Gwangju04-WebStudy/assets/79882952/0e6ad273-a577-493b-8827-1a8c42ac2048)

  - 회원 관리 웹 애플리케이션 구현
  - MVC 패턴 적용
  
  - WEB-INF 안에 생성된 jsp는 주소창에 직접 입력해도 실행되지 않고 controller를 통해야만 접근이 가능함
</details>

<details>
  <summary>Section 4</summary>

  >인증
    ![image](https://github.com/hanbinseo/SSAFY11th-Gwangju04-WebStudy/assets/79882952/44e10d67-211e-4bcd-8ac4-c1bae0ff7192)

  - v1 : 프론트 컨트롤러를 도입
    - 기존 구조를 최대한 유지하면서 프론트 컨트롤러 도입
  - v2 : View 분류
    - 단순 반복되는 뷰 로직 분리
  - v3 : Model 추가
    - 서블릿 종속성 제거
    - 뷰 이름 중복 제거
  - v4 : 단순하고 실용적인 컨트롤러
    - v3와 거의 비슷
    - 구현 입장에서 ModelView를 직접 생성해서 반환하지 않도록 편리한 인터페이스 제공
  - v5 : 유연한 컨트롤러
    - 어댑터 도입
    - 어댑터를 추가해서 프레임워크를 유연하고 확장성 있게 설계

  > 최종 MVC 구조
    ![image](https://github.com/hanbinseo/SSAFY11th-Gwangju04-WebStudy/assets/79882952/908c495a-6b44-49c1-963a-f69233442dde)

</details>

<details>
  <summary>Section 5</summary>

  >인증
    ![image](https://github.com/hanbinseo/SSAFY11th-Gwangju04-WebStudy/assets/79882952/878521f1-6bfc-40a6-81f0-661e03685254)

  ### Spring mvc 동작 순서
   1. 핸들러 조회: 핸들러 매핑을 통해 요청 URL에 매핑된 핸들러(컨트롤러)를 조회한다.
   2. 핸들러 어댑터 조회: 핸들러를 실행할 수 있는 핸들러 어댑터를 조회한다.
   3. 핸들러 어댑터 실행: 핸들러 어댑터를 실행한다.
   4. 핸들러 실행: 핸들러 어댑터가 실제 핸들러를 실행한다.
   5. ModelAndView 반환: 핸들러 어댑터는 핸들러가 반환하는 정보를 ModelAndView로 변환해서 반환한다.
   6. viewResolver 호출: 뷰 리졸버를 찾고 실행한다.
     - JSP의 경우: InternalResourceViewResolver 가 자동 등록되고, 사용된다.
   7. View 반환: 뷰 리졸버는 뷰의 논리 이름을 물리 이름으로 바꾸고, 렌더링 역할을 담당하는 뷰 객체를 반환한다.
     - JSP의 경우 InternalResourceView(JstlView) 를 반환하는데, 내부에 forward() 로직이 있다.
   8. 뷰 렌더링: 뷰를 통해서 뷰를 렌더링 한다.
<hr></hr>

  ### Annotation
  - @Controller
    - 스프링이 자동으로 스프링 빈으로 등록한다.
      - 내부에 @Component 애노테이션이 있어서 컴포넌트 스캔의 대상이 됨
    - 스프링 MVC에서 애노테이션 기반 컨트롤러로 인식한다
  - @RequestParam 사용
    - @RequestParam("username") 은 request.getParameter("username")와 거의 같은 코드로 활용 가능
    - GET, POST 모두 지원
  - @RequestMapping @GetMapping, @PostMapping
    - @RequestMapping(value="path", method=Request.Method.GET) -> @GetMapping("path") 로 사용 가능
</details>

<details>
  <summary>Section 6</summary>

  >인증
  >![image](https://github.com/hanbinseo/SSAFY11th-Gwangju04-WebStudy/assets/79882952/a8363456-c827-4842-8218-7b0f09656089)

  - LOG 출력
    - @Slf4j
  - Mapping
    - 편리한 축약 Annotation
      - @GetMapping
      - @PostMapping
      - @PutMapping
      - @DeleteMapping
      - @PatchMapping
    - @PathVariable
  - Http Request
    - parameter
      - Query param, HTML Form
      - @RequestParam
      - @ModelAttribute
    - message
      - 단순 텍스트
      - JSON
  - Http Response
    - 정적 리소스, 뷰 템플릿
    - HTTP API, message body에 직접 입력

</details>

<details>
  <summary>Section 7</summary>

  >인증
> ![image](https://github.com/hanbinseo/SSAFY11th-Gwangju04-WebStudy/assets/79882952/93871c0d-cce0-475d-9976-dc4695243555)
>![image](https://github.com/hanbinseo/SSAFY11th-Gwangju04-WebStudy/assets/79882952/f747fde7-661f-4bf0-b2de-93925811512d)

- 상품 등록 웹페이지 기본 화면 구성
  - 상품 리스트
  - 등록하기
  - 상세보기
  - 수정하기

</details>

