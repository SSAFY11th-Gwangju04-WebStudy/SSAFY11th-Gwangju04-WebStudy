# 6. 스프링 MVC 기본 패턴

- Jar 사용시 항상 내장 서버를 사용하고 webapp 경로 사용하지 않음
- War 사용시 내장서버도 가능하지만 외부 서버에 배포 목적으로 사용함

## 로깅 라이브러리
- SLF4J - http://www.slf4j.org  (인터페이스)
- Logback - http://logback.qos.ch (구현체)

@Slf4j 같은 역할
private final Logger log = LoggerFactory.getLogger(getClass());
@RestController 는 http메세지 바디에 String 넣어서 보내버림
1. trace
2. debug
3. info
4. warn
5. error
- 패키지와 하위 로그 레벨 설정 : logging.level.[패키지명]=trace
개발은 debug, 운영 서버는 info 까지!
성능도 sout보다 좋고 관리가 편하므로 해당 사용법을 익혀주자!
```java
log.trace(" trace log={}", name);  
log.debug(" debug log={}", name);  
log.info(" info log={}", name);  
log.warn(" warn log={}", name);  
log.error(" error log={}", name);
```

## 요청 매핑

둘다 허용
- 매핑: /hello-basic  
- URL 요청: /hello-basic , /hello-basic/

- PathVariable 변수명 같으면 ("userId") 생략 가능
```java
/**  
 * PathVariable 사용  
 * 변수명이 같으면 생략 가능  
 * @PathVariable("uerId") String userId -> @PathVariable userId  
 * /mapping/userA */
@GetMapping("/mapping/{userId}")  
public String mappingPath(@PathVariable String userId) {  
    log.info("mappingPath userId={}", userId;  
    return "PathVariable";  
}
```

- 에러 = build tool 을 gradle 로 변경해서 해결
Name for argument of type [java.lang.String] not specified, and parameter name information not available via reflection. Ensure that the compiler uses the '-parameters' flag.

HTTP요청 - 기본, 헤더 조회
https://docs.spring.io/spring-framework/reference/web/webmvc/mvc-controller/ann-methods/arguments.html
공식문서 에서 받을수 있는 매개변수 확인 가능

### HTTP 요청 파라미터 - 쿼리파라미터, HTML Form
cli to server 시 요청 전달방식은 주로 3가지
1. Get + 쿼리 파라미터
2. Post - HTML Form
3. HTTP message Body (Post, put, patch)

get 과 post 는 구분없이 조회 메서드로 조회할 수 있음

@RequestParam -> 변수명 맞추기 + 어노테이션 마저 생략이 가능함
- required 속성을 사용해서 매개변수를 받지 않아도 오류 발생을 방지할 수 있다. 
기본값은 true 이때 기본형 타입에 false 처리시 null을 넣을 수 없기 때문에 주의하자
- defaultValue = 기본값을 지정이 가능함 -> required 기능까지 사용함
- Map 을 사용해 받을 수 있다 -> 이때 key값이 1개인게 확실하지 않다면 MultivalutMap 을 사용해야 한다!

### HTTP 요청 파라미터 @ModelAttribute
@ModelAttribute HelloData helloData 로 객체를 파라미터로 받을 수 있다
이때 xxx -> setXxx 메서드를 호출해 값을 넣는다
ex) username -> setUsername() 호출
물론 @ModelAttribute 어노테이션도 생략이 가능하다
- String, int, Integer 같은 단순 타입 -> @RequestParam
- 나머지 객체 -> @ModelAttribute (argument resolver 지정시 처리 X 예약어)

### HTTP 요청 메시지 - 단순 텍스트 (messageBody)
- 매개변수에 HttpEntity<String[]> httpEntity 를 받을 수 있고 이를 반환 가능
(HTTPMessageConverter) 가 작동됨

```java
@ResponseBody  
@PostMapping("/request-body-string-v4")  
public String requestBodyStringV4(@RequestBody String messageBody) throws IOException {  
  
    log.info("messageBody={}", messageBody);  
    return "ok";  
}
```
- 요청 파라미터를 받는 경우: @RequestParam, @ModelAttribute
- HTTP 메세지 바디 조회: @RequestBody

### HTTP 요청 메세지 - JSON
해당 JSON 객체도 컨버터가 작동함, @RequestParam 생략시 @ModelAttribut 가 됨
- 요청시 contentType 이 json인지 확인해야 함!
```java
@ResponseBody  
@PostMapping("/request-body-json-v3")  
public String requestBodyJsonV3(@RequestBody HelloData helloData) throws IOException {  
  
    log.info("username={}, ag{}", helloData.getUsername(), helloData.getAge());  
    return "ok";  
}
@ResponseBody  
@PostMapping("/request-body-json-v4")  
public String requestBodyJsonV4(HttpEntity<HelloData> httpEntity) throws IOException {  
  
    HelloData data = httpEntity.getBody();  
    log.info("username={}, ag{}", data.getUsername(), data.getAge());  
    return "ok";  
}  
@ResponseBody  
@PostMapping("/request-body-json-v5")  
// -> 바뀐 Json 이 날라감 cli -> Json -> 객체 - 이 함수 -> 객체 -> Json -> clipublic HelloData requestBodyJsonV5(@RequestBody HelloData data) throws IOException {  
  
    log.info("username={}, ag{}", data.getUsername(), data.getAge());  
    return data; }
```

### 응답 - 정적 리소스, 뷰 템플릿
응답 데이터 만드는 방법 3가지!
1. 정적 리소스 (Html, css)
2. 뷰 템플릿 사용 (동적 html 제공)
3. Http 메시지 사용 (데이터 전달 - body에)
src/main/resources = 클래스 패스의 시작경로
- 정적 리소스 
	- /static , /public , /resources , /META-INF/resources
	- 시작 경로는 src/main/resources/static
- 뷰 템플릿
	- src/main/resources/templates
- String을 반환하는 경우 - View or HTTP 메시지  
	- @ResponseBody 가 없으면 response/hello 로 뷰 리졸버가 실행되어서 뷰를 찾고, 렌더링 한다. @ResponseBody 가 있으면 뷰 리졸버를 실행하지 않고, HTTP 메시지 바디에 직접 response/hello 라는 문자가 입력된다.
	- 여기서는 뷰의 논리 이름인 response/hello 를 반환하면 다음 경로의 뷰 템플릿이 렌더링 되는 것을 확인할 수 있다.  
	- 실행: templates/response/hello.html

- Void를 반환하는 경우  
	- @Controller 를 사용하고, HttpServletResponse , OutputStream(Writer) 같은 HTTP 메시지

	- 바디를 처리하는 파라미터가 없으면 요청 URL을 참고해서 논리 뷰 이름으로 사용 요청 URL: /response/hello  
	 - 실행: templates/response/hello.html

	 - 참고로 이 방식은 명시성이 너무 떨어지고 이렇게 딱 맞는 경우도 많이 없어서, 권장하지 않는다. (v2 정도가 적당한것 같다..)

- HTTP 메시지  
	- @ResponseBody , HttpEntity 를 사용하면, 뷰 템플릿을 사용하는 것이 아니라, HTTP 메시지 바디에 직접 응답 데이터를 출력할 수 있다.
```java
@RequestMapping("/response-view-v2")  
public String responseViewV2(Model model) {  
    model.addAttribute("data", "hello!");  
    return "/response/hello";  
}
```

### HTTP 응답 - messageBody에 직접
- @RestController: 해당 컨트롤러 전체에 @ResponseBody 적용됨
- @ResponseBody
```java
@ResponseBody  
@GetMapping("/response-body-string-v3")  
public String responseBodyV3() throws IOException {  
    return "ok";  
}

@ResponseStatus(HttpStatus.OK)  
@ResponseBody // 클래스 레벨에 선언 가능
@GetMapping("/response-body-json-v2")  
public HelloData responseBodyJsonV2() {  
  
    HelloData helloData = new HelloData();  
    helloData.setUsername("userA");  
    helloData.setAge(20);  
    return helloData;  
}
```

### HTTPMessageConverter
![]([https://i.ibb.co/K5ZLdD0/2024-03-30-10-51-40.png)
- 스프링 MVC가 HTTP 메시지 컨버터 적용하는 경우
	- HTTP 요청: @RequestBody, HttpEntity(RequestEntity)
	- HTTP 응답: @ResponseBody, HttpEntity(ResponseEntity)
- 스프링 부트 기본 메시지 컨버터 우선순위
0.  ByteArrayHttpMessageConverter: byte[] 데이터 처리
 1. StringHttpMessageConverter: String 문자 처리
 2. MappingJackson2HttpMessageConverter: application/json 처리

- HTTP 요청 데이터 읽기  
	1. HTTP 요청이 오고, 컨트롤러에서 @RequestBody , HttpEntity 파라미터를 사용한다. 메시지 컨버터가 메시지를 읽을 수 있는지 확인하기 위해 canRead() 를 호출한다.
	2. 대상 클래스 타입을 지원하는가.  예) @RequestBody 의 대상 클래스 ( byte[] , String , HelloData )
	3. HTTP 요청의 Content-Type 미디어 타입을 지원하는가. 예) text/plain , application/json , [*/*]
	4. canRead() 조건을 만족하면 read() 를 호출해서 객체 생성하고, 반환한다.

- HTTP 응답 데이터 생성  
	1. 컨트롤러에서 @ResponseBody , HttpEntity 로 값이 반환된다.  
	2. 메시지 컨버터가 메시지를 쓸 수 있는지 확인하기 위해 canWrite() 를 호출한다.
	3. 대상 클래스 타입을 지원하는가.  예) return의 대상 클래스 ( byte[] , String , HelloData )
	4. HTTP 요청의 Accept 미디어 타입을 지원하는가.(더 정확히는 @RequestMapping 의 produces ) 예) text/plain , application/json , */*
	5. canWrite() 조건을 만족하면 write() 를 호출해서 HTTP 응답 메시지 바디에 데이터를 생성한다.

### 요청 매핑 핸들러 어뎁터 구조
= RequestMapping Handler Adapter
1. 핸들러 (컨트롤러) 가 필요한 파라미터를 찾음
2. Argument Resolver 에게 해당 파라미터 요청 (30개 가량이 지정되있음)
3. 만들어진 파마리터로 핸들러 호출
https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#mvc-ann-arguments

![](https://i.ibb.co/34rT6n8/2024-03-31-8-44-18.png)

- Argument Resolver 가 HTTP Message Converter 를 찾아 처리할 수 있는것을 찾음
- 스프링은 Argument Resolver, 메세지 컨버터, Return Value Handler 모두 인터페이스로 구현되어 있다. 따라서 추가 기능이 필요하다면 확장성 있게 구현할 수 있다

