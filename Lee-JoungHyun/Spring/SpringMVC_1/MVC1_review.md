# 스프링 MVC1 정리


### MVC 구조 (dispatherServlet)

```java
DispatcherServlet.doDispatch()

protected void doDispatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
	HttpServletRequest processedRequest = request;
	HandlerExecutionChain mappedHandler = null;
	ModelAndView mv = null;
	
	**// 1. 핸들러 조회**
	mappedHandler = getHandler(processedRequest);
	if (mappedHandler == null) { // 처리할 핸들러 없음
		noHandlerFound(processedRequest, response);
		return;
	}
		
	**// 2. 핸들러 어댑터 조회 - 핸들러를 처리할 수 있는 어댑터**
	HandlerAdapter ha = getHandlerAdapter(mappedHandler.getHandler());
	
	**// 3. 핸들러 어댑터 실행 -> 4. 핸들러 어댑터를 통해 핸들러 실행 -> 5. ModelAndView 반환**
	mv = ha.handle(processedRequest, response, mappedHandler.getHandler());
	processDispatchResult(processedRequest, response, mappedHandler, mv, dispatchException);
	
}

// 뷰 렌더링 호출 함수
private void processDispatchResult(HttpServletRequest request, HttpServletResponse response, HandlerExecutionChain mappedHandler, ModelAndViewmv, Exception exception) throws Exception {
	render(mv, request, response);
}

protected void render(ModelAndView mv, HttpServletRequest request, HttpServletResponse response) throws Exception {
	View view;
	String viewName = mv.getViewName();
	
	**// 6. 뷰 리졸버를 통해서 뷰 찾기, 7. View 반환**
	view = resolveViewName(viewName, mv.getModelInternal(), locale, request);
	
	**// 8. 뷰 렌더링**
	view.render(mv.getModelInternal(), request, response);
	
}
```

### 핸들러 매핑, 핸들러 어댑터 등록 우선순위

- 핸들러 매핑

<aside>
👉🏻  0 = RequestMappingHandlerMapping : 애노테이션 기반의 컨트롤러인 @RequestMapping에서 사용
 1 = BeanNameUrlHandlerMapping : 스프링 빈의 이름으로 핸들러를 찾는다.

</aside>

- 핸들러 어뎁터

<aside>
👉🏻 0 = RequestMappingHandlerAdapter : 애노테이션 기반의 컨트롤러인 @RequestMapping에서 사용
1 = HttpRequestHandlerAdapter : HttpRequestHandler 처리
2 = SimpleControllerHandlerAdapter : Controller 인터페이스(애노테이션X, 과거에 사용) 처리

</aside>

## @RequestMappingHandlerMapping 기반 애노테이션

- `@Controller`
    - 스프링이 자동으로 스프링 빈으로 등록한다. (내부에 @Component 애노테이션이 있어서 컴포넌트 스캔의 대상이 됨)
    - 스프링 MVC에서 애노테이션 기반 컨트롤러로 인식한다.
- `@RequestMapping`
    - 요청 정보를 매핑한다. 해당 URL이 호출되면 이 메서드가 호출된다.
    - 애노테이션을 기반 으로 동작하기 때문에, 메서드의 이름은 임의로 지으면 된다.
- `ModelAndView` : 모델과 뷰 정보를 담아서 반환하면 된다.

## 요청 매핑 @RequestMapping

- @RequestMapping(value = "/mapping-get-v1", method = RequestMethod.GET)
    - value: 경로 (/로 시작시 절대경로, 아니면 상대경로(현재 경로 기반)
    - method: get, post 등 방식으로 요청시 매핑
- 축약 매핑
    - @GetMapping
    - @PostMapping
    - @PutMapping
    - @DeleteMapping
    - @PatchMapping
- PathVaribale (경로 변수) 사용
    - 경로에 변수를 담아 전달하는 형식
    - @GetMapping(”/mapping/`{userId}`”)
    - 매개변수로 `@PathVariable[(”userId”)] String userId` 를 받아 사용 + 생략 가능!
- 추가 매핑 방법
    - 특정 파라미터로: params=”mode=debug”
    - 특정 헤더 조건으로: headers=”mode=debug”
    - 미디어 타입으로: consumes=”application/json”)
    

## HTTP 요청 파라미터 받는법!

- Client to Server Data
    1. Get + 쿼리 파라미터
    2. Post + Html Form
    3. HTTP message body (Json, xml, text)
- `@RequestParam`
    - [ @RequestParam[(”username”)] ] String username 형식으로 매개변수 받기
        - Spring 3.2 부터는 이름(”xxx”) 생략시 오류 나올수도 → 붙이기 or 컴파일시 -parameters 옵션 적용 or Gradle
    - 파라미터 생략 가능시 @RequestParam(required = false) + 기본값 defaultValue - “xxx”
    - 파라미터 Map으로 조회하기 Map or MultiVlaueMap
- `@ModelAttribute`
    - [@ModelAttribute] Xxx xxx 형식으로 객체 매개변수로 받음 ``
- `@RequestBody`
    - Http message 의 body 를 조회할 수 있음 (@RequestBody String messageBody)
    - Json 요청 시 처리법
        1. Xxx xxx = objectMapper.readValue(messageBody, Xxx.class); 로 사용 
        2. (@RequestBody Xxx xxx) 로 바로 받기 → 변수 xxx 는 setXxx, getXxx 를 호출해 세팅함!  
- 스프링의 파라미터 어노테이션 생략시 규칙
    1. String , int , Integer 같은 단순 타입 = @RequestParam
    2. 나머지 = @ModelAttribute (argument resolver 로 지정해둔 타입 외)

## HTTP 응답

- 종류
    1. 정적 리소스 (HTML, CSS, JS)
    2. 뷰 템플릿 사용 (동적 HTML)
    3. HTTP 메시지 (HTTP API 제공시 (Json)) 
- 매핑 메서드 반환 타입
    1. String: 뷰 리졸버가 실행되 뷰를 찾고 랜더링, @ResponseBody 있으면 바디에 직접입력
    2. void: @Controller 를 사용하고, HttpServletResponse , OutputStream(Writer) 같은 HTTP 메시지 바
    디를 처리하는 파라미터가 없으면 요청 URL을 참고해서 논리 뷰 이름으로 사용 (권장X)
- 뷰 템플릿을 호출하는 컨트롤러
    - 데이터를 보내는 경우
        - Model model 을 매개변수로 받음
        - model.addAttribute(”변수명”, object) 보냄
    - return “~” : 해당 경로 파일 뷰 리졸버가 찾아 실행
- HTTP 메세지 바디에 직접 입력하는 경우
    - @ResponseBody (메서드에) or @RestController (클래스에) 선언!
    - text인 경우 그냥 보내 (ResponseEntity<Type> 을 반환할 수 도 있지만 ㄱㅊ)
    - Json 인 경우 해당 객체 보내기 + json 배열의 경우 그냥 리스트로 반환

---

** 내일 메세지 컨버터 +a 정리하기 **

### 기타 애노테이션

- @RestController : returun 값을 HttpBody에 삽입에 반환 → Json 타입 반환시 주로 쓰임
- @Data : 롬복 (@Getter , @Setter , @ToString , @EqualsAndHashCode , @RequiredArgsConstructor)를 자동 적용
