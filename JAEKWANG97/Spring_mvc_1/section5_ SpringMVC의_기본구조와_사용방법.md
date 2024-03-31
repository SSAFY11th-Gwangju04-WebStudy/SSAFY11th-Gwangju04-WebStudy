[[섹션 5 스프링 MVC - 구조 이해]]
# 로깅
- 앞으로 sysout 대신 log.info 와 같은 로그를 찍어 디버깅 해야함
- SLF4J, Logback 라이브러리가 있음
```java
 log.trace("trace log={}", name);
 log.debug("debug log={}", name);
 log.info(" info log={}", name);
 log.warn(" warn log={}", name);
 log.error("error log={}", name);
```

### 매핑 정보

- @RestController
	- @Controller는 반환 값이 String  이면 뷰 이름으로 인식, 후 뷰를 찾고 렌더링 됨
	- @RestController는 반환 값을 바로 HTTP 메시지에 바디로 바로 입력함
### 테스트
- 로그가 출력되는 포멧을 확인함
- 로그 Level이 있어 따로 설정 가능(보고 싶은 단계부터 볼 수 있음)
- 보통 개발 서버는 debug, 운영 서버는 info 출력
- application.properties --> logging.level.root=info

# 요청 매핑

## MappingController 클스스

```
@RequestMapping("/hello-basic")
/hello-basic URL 호출이 오면 이 메서드가 실행되도록 매핑한다.
대부분의 속성을 배열[] 로 제공하므로 다중 설정이 가능하다. {"/hello-basic", "/hello-go"}
```


### URL 경로 매핑

- **경로와 메서드 매핑**
	- `@RequestMapping` 어노테이션을 사용하여 특정 경로에 대한 요청을 처리하는 메서드를 지정함.
	- 이 클래스에서는 `/hello-basic`, `/hello-go`, `/path-variable/{name}`, `/path-variable/{name}/{age}` 등의 경로에 대한 매핑을 보여줌.
- **경로 변수 사용**
	- `{name}` 및 `{age}`와 같이 경로 내에 변수를 포함시켜, 동적으로 변하는 경로 부분을 메서드 파라미터로 캡처할 수 있음
	- 이는 `@PathVariable` 어노테이션을 사용하여 구현됩니다
# HTTP 메서드
- `@RequestMapping`에 `method` 속성으로 HTTP 메서드를 지정하지 않으면 HTTP 메서드와 무관하게 호출됨. (get,post,patch,delete)


## HTTP 메서드 매핑 축약
```
  @GetMapping
  @PostMapping
  @PutMapping
  @DeleteMapping
  @PatchMapping
```

### PathVariable(경로 변수) 사용

- 위에서 살작 언급한 PathVariable을 통해서 URL 경로를 매핑할 수 있음
- 다중 사용 가능
```java
@GetMapping("/mapping/users/{userId}/orders/{orderId}")
public String mappingPath(@PathVariable String userId, @PathVariable Long
orderId) {
 log.info("mappingPath userId={}, orderId={}", userId, orderId);
 return "ok";
}
```

### 미디어 타입 조건 매핑 - HTTP 요청 Content-Type, consume

```
  Content-Type 헤더 기반 추가 매핑 Media Type
  consumes="application/json"
  consumes="!application/json"
  consumes="application/*"
  consumes="*\/*"
  MediaType.APPLICATION_JSON_VALUE
```

- `consumes="application/json"`: `Content-Type`이 정확히`application/json`인 요청만 처리
- `consumes="!application/json"`: `Content-Type`이 `application/json`이 아닌 요청을 처리
- `consumes="application/*"`: `Content-Type`이 `application/`으로 시작하는 모든 미디어 타입의 요청을 처리
- `consumes="*\/*"`: 모든 `Content-Type`의 요청을 처리. 하지만 이 경우는 일반적으로 `consumes` 속성을 생략한 것과 동일
- `MediaType.APPLICATION_JSON_VALUE`: `consumes` 속성에 직접 문자열을 사용하는 대신, `MediaType` 클래스의 상수를 사용할 수도 있음. 이는 코드의 가독성을 높이고 오타를 줄일 수 있는 방법.

### 미디어 타입 조건 매핑 - HTTP 요청 Accept, produce ```java

```
/**
 * Accept 헤더 기반 Media Type
 * produces = "text/html"
 * produces = "!text/html"
 * produces = "text/*"
 * produces = "*\/*"
 */
@PostMapping(value = "/mapping-produce", produces = "text/html")
```
### 내용 유형 및 HTTP 메서드 기반 매핑

- **내용 유형(`consumes` 및 `produces`) 매핑**: 요청의 `Content-Type`이나 응답의 `Accept` 헤더와 매치되는 조건을 기반으로 요청을 매핑합니다. JSON을 소비하거나 생성하는 메서드의 경우, 이를 명시적으로 지정할 수 있습니다.
- **HTTP 메서드 매핑**: `@RequestMapping`에 `method` 속성을 사용하여 특정 HTTP 메서드(예: GET, POST)에 대해서만 요청을 매핑합니다.


# HTTP 요청 - 기본, 헤더 조회

```java
@Slf4j
@RestController
public class RequestHeaderController {
 @RequestMapping("/headers")
 public String headers(HttpServletRequest request,
 HttpServletResponse response,
HttpMethod httpMethod, Locale locale,
 @RequestHeader MultiValueMap<String, String> 
headerMap,
 @RequestHeader("host") String host,
 @CookieValue(value = "myCookie", required = false) 
String cookie
 ) {
 log.info("request={}", request);
 log.info("response={}", response);
 log.info("httpMethod={}", httpMethod);
 log.info("locale={}", locale);
 log.info("headerMap={}", headerMap);
 log.info("header host={}", host);
 log.info("myCookie={}", cookie);
 return "ok";
 }
}
```

- `@Slf4j`: Lombok 라이브러리의 어노테이션으로, 클래스에 **로거(logger)를 자동으로 추가**합니다. 이를 통해 `log.info()`와 같은 로깅 메서드를 클래스 내에서 바로 사용할 수 있게 해줍니다.

- `@RestController`: 클래스가 RESTful 웹 서비스의 컨트롤러임을 나타내는 어노테이션입니다. `@Controller`와 `@ResponseBody`를 합친 것으로, 컨트롤러 메서드의 반환값을 HTTP 응답 바디에 직접 작성합니다.

- `@RequestMapping("/headers")`: 특정 HTTP 요청 경로(`/headers`)를 메서드에 매핑합니다. 이 메서드는 `/headers` 경로로 들어오는 요청을 처리합니다.

- `HttpServletRequest request`: 현재 HTTP 요청에 대한 정보를 담고 있는 객체입니다. 요청 URL, 헤더, 매개변수 등을 조회할 수 있습니다.

- `HttpServletResponse response`: 현재 HTTP 응답을 나타내는 객체입니다. 응답 상태 코드, 응답 헤더 등을 설정할 수 있습니다.

- `HttpMethod httpMethod`: 요청의 HTTP 메서드(예: GET, POST)를 나타냅니다. Spring MVC가 자동으로 현재 요청의 메서드 타입을 이 변수에 바인딩합니다.

- `Locale locale`: 요청의 로케일 정보입니다. 클라이언트의 지역 설정을 나타내며, 다국어 처리에 활용할 수 있습니다.

- `@RequestHeader MultiValueMap<String, String> headerMap`: 모든 요청 헤더를 `MultiValueMap`에 담아주는 어노테이션입니다. 헤더 이름과 값(복수 값 포함)을 조회할 수 있습니다.

- `@RequestHeader("host") String host`: 요청 헤더 중 "host" 헤더의 값을 문자열로 바인딩합니다. 예를 들어, 클라이언트가 요청한 호스트 이름을 얻을 수 있습니다.

- `@CookieValue(value = "myCookie", required = false) String cookie`: 요청에서 "myCookie"라는 이름의 쿠키 값을 문자열로 가져옵니다. `required = false`는 해당 쿠키가 없어도 에러가 발생하지 않고, `null`을 반환하게 합니다.

# HTTP 요청 파라미터 - 쿼리 파라미터, HTML Form

- HttpServletRequest 의 request.getParameter() 를 사용하면 다음 두가지 요청 파라미터를 조회가능
	- GET, 쿼리 파라미터 전송
	- http://localhost:8080/request-param?username=hello&age=20
	- POST, HTML Form 전송
```
POST /request-param ...
content-type: application/x-www-form-urlencoded
username=hello&age=20
```

### `requestParamV1`: 기본 Servlet 방식
[]
- **방식**: `HttpServletRequest`를 사용하여 직접 요청 파라미터를 조회합니다.
- **설명**: 가장 기본적인 방법으로, Spring 없이도 사용할 수 있습니다. 파라미터의 이름으로 `getParameter` 메서드를 호출하여 값을 얻습니다.
```java
public class RequestParamController {

    @RequestMapping("request-param-v1")

    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) th rows IOException {

        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username={}, age={}", username, age);
        response.getWriter().write("ok");

    }
```
### `requestParamV2`: `@RequestParam` 사용
- **방식**: `@RequestParam` 어노테이션을 사용하여 요청 파라미터를 메서드의 파라미터로 바인딩합니다.
- **설명**: 요청 파라미터 이름을 어노테이션의 값으로 지정해 해당 값을 메서드의 파라미터에 자동으로 바인딩합니다. 이 방법은 타입 변환을 자동으로 처리하고, 코드의 가독성을 높여줍니다.
```java
    @ResponseBody
    @RequestMapping("request-param-v2")

    public String requestParamV2(
            @RequestParam("username") String username,
            @RequestParam("age") int age) {
        log.info("username={}, age={}", username, age);
        return "ok";
    }
```
### `requestParamV3`: 필수 및 선택적 파라미터
- **방식**: `@RequestParam`의 `required` 속성을 사용합니다.
- **설명**: `username` 파라미터는 필수(`required = true`), `age` 파라미터는 선택적(`required = false`)으로 처리합니다. 선택적 파라미터는 값이 없을 때 `null`이나 기본 타입의 기본값(`int`의 경우 `0`)을 가집니다.
```java
    @ResponseBody
    @RequestMapping("request-param-v3")
    public String requestParamV3(
            @RequestParam(value = "username", required = true) String username,
            @RequestParam(value = "age", required = false) int age) {
        log.info("username={}, age={}", username, age);
        return "ok";

    }
```

### `requestParamV4`: 파라미터 이름 생략
- **방식**: `@RequestParam` 어노테이션 생략.
- **설명**: 메서드 파라미터 이름이 요청 파라미터 이름과 정확히 일치할 경우, `@RequestParam` 어노테이션을 생략할 수 있습니다. Spring MVC는 자동으로 요청 파라미터 값을 바인딩합니다.
```java

    @ResponseBody
    @RequestMapping("request-param-v4")
    public String requestParamV4(String username, int age) {
        log.info("username={}, age={}", username, age)
        return "ok";

    }
```
### `requestParamRequired`: 필수 파라미터
- **방식**: `@RequestParam`에서 `required`를 명시적으로 사용합니다.
- **설명**: `username`은 필수, `age`는 선택적으로 처리합니다. 이는 `requestParamV3`과 동일한 방식이지만, 이 메서드는 필수 파라미터에 초점을 맞춥니다.
```java
    @ResponseBody
    @RequestMapping("request-param-required")
    public String requestParamRequired(
            @RequestParam(value = "username", required = true) String username,
            @RequestParam(value = "age", required = false) int age) {
        log.info("username={}, age={}", username, age);
        return "ok";

    }
```
### `requestParamDefault`: 기본값 설정
- **방식**: `@RequestParam`의 `defaultValue` 속성을 사용합니다.
- **설명**: `username`과 `age` 파라미터에 기본값(`"guest"`, `"0"`)을 설정합니다. 요청에서 해당 파라미터가 누락된 경우, 지정된 기본값이 사용됩니다.
```java

    @ResponseBody
    @RequestMapping("request-param-default")
    public String requestParamDefault(
            @RequestParam(value = "username", required = true, defaultValue = "guest") String username,
            @RequestParam(value = "age", required = false, defaultValue = "0") int age) {
        log.info("username={}, age={}", username, age);
        return "ok";
    }

```
### `requestParamMap`: 파라미터 맵
- **방식**: `@RequestParam`과 `Map<String, String>`을 사용합니다.
- **설명**: 모든 요청 파라미터를 `Map`에 바인딩합니다. 특정 파라미터 이름을 미리 알지 못하거나, 여러 파라미터를 동시에 처리해야 할 때 유용합니다.
```java
    @ResponseBody
    @RequestMapping("request-param-map")
    public String requestParamMap(@RequestParam Map<String, String> paramMap) {
        log.info("username={}, age={}", paramMap.get("username"), paramMap.get("age"));
        return "ok";
    }
```


[[스프링 부트 3.2에서는 파라미터 이름 인식문제]]

# HTTP 요청 파라미터 - @ModelAttribute
- 객체를 주고 받아야하는 경우가 있음
```java
    @RequestMapping("/model-attribute-v1")

    public String modelAttributeV1(@ModelAttribute HelloData helloData) {

        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());

        return "ok";

    }

  

    @RequestMapping("/model-attribute-v2")

    public String modelAttributeV2(HelloData helloData) {

        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());

        return "ok";

    }
```

`@ModelAttribute` 어노테이션을 사용하면, 스프링 MVC가 HTTP 요청 파라미터를 자바 객체에 바인딩해줍니다. 이 과정에서 요청 파라미터의 이름과 객체의 필드 이름이 매칭되면, 해당 필드에 자동으로 값이 할당됩니다. 


1. HTTP 요청이 들어옵니다.
2. 스프링 MVC가 `@ModelAttribute` 어노테이션이 붙은 파라미터를 확인합니다.
3. 해당 클래스([HelloData](file:///c%3A/Spring-MVC1/springmvc/src/main/java/hello/springmvc/basic/requestmapping/MappingController.java#11%2C30-11%2C30))의 인스턴스를 생성합니다.
4. 요청 파라미터의 이름과 일치하는 [HelloData](file:///c%3A/Spring-MVC1/springmvc/src/main/java/hello/springmvc/basic/requestmapping/MappingController.java#11%2C30-11%2C30)의 필드를 찾습니다.
5. 요청 파라미터의 값을 해당 필드에 할당합니다.
6. 메서드 내부에서 이 객체를 사용할 수 있습니다.

`@ModelAttribute`는 생략할 수도 있는데, 이 경우 스프링 MVC는 여전히 같은 방식으로 작동합니다. 즉, 메서드 파라미터에 단순히 객체를 선언하는 것만으로도 ([modelAttributeV2](file:///c%3A/Spring-MVC1/springmvc/src/main/java/hello/springmvc/basic/requestmapping/MappingController.java#72%2C19-72%2C19) 메서드 참조) 스프링 MVC는 요청 파라미터를 해당 객체에 바인딩합니다. 

이러한 방식으로, 폼 데이터나 쿼리 파라미터 등을 자바 객체에 매핑하여, 컨트롤러 내에서 편리하게 사용할 수 있게 됩니다.


# HTTP 요청 메시지

```
HTTP message body에 데이터를 직접 담아서 요청
HTTP API에서 주로 사용, JSON, XML, TEXT
데이터 형식은 주로 JSON 사용
POST, PUT, PATCH
```


 - HTTP 메시지 바디를 통해 데이터가 직접 넘어오는 경우는 @RequestParam , @ModelAttribute 를 사용할 수 없다
## HTTP 요청 메시지 - 단순 텍스트

```java
 @PostMapping("/request-body-string-v1")

    public void requestBodyStringV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        log.info("messageBody={}", messageBody);
    }

  

    @PostMapping("/request-body-string-v2")
    public void requestBodyStringV2(@RequestBody String messageBody) {
        log.info("messageBody={}", messageBody);
    }
```
##  HTTP 요청 메시지 - JSON
 
 ```java
 @PostMapping("/request-body-json-v1")

    public void requestBodyJsonV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody={}", messageBody);
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
    }

  

    @PostMapping("/request-body-json-v2")
    public void requestBodyJsonV2(@RequestBody HelloData helloData) {
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
    }

  

    @PostMapping("/request-body-json-v3")
    public HelloData requestBodyJsonV3(@RequestBody HelloData helloData) {
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        return helloData;

    }
```




# HTTP 응답 - 정적 리소스, 뷰 템플릿
- 정적 리소스
	- 예) 웹 브라우저에 정적인 HTML, css, js를 제공할 때는, 정적 리소스를 사용한다.
- 뷰 템플릿 사용
	- 예) 웹 브라우저에 동적인 HTML을 제공할 때는 뷰 템플릿을 사용한다.
- HTTP 메시지 사용
	- HTTP API를 제공하는 경우에는 HTML이 아니라 데이터를 전달해야 하므로, HTTP 메시지 바디에 JSON 같은 형식으로 데이터를 실어 보낸다.

### HTTP 메시지 사용 예시

클라이언트가 서버에 사용자 정보를 JSON 형태로 요청하고, 서버는 해당 사용자 정보를 JSON 형태로 응답하는 REST API를 구현한다고 가정해봅시다. 클라이언트는 다음과 같은 요청을 보낼 수 있습니다:

```http
POST /api/users
Content-Type: application/json

{
  "username": "user1",
  "age": 30
}
```

서버는 이 요청을 처리하고, 사용자 정보가 성공적으로 생성되었음을 나타내는 JSON 응답을 반환합니다:

```http
HTTP/1.1 200 OK
Content-Type: application/json

{
  "message": "사용자 생성 완료",
  "userId": "12345"
}
```



# HTTP 응답 - HTTP API, 메시지 바디에 직접 입력

 - HTTP API를 제공하는 경우에는 HTML이 아니라 데이터를 전달해야 하므로, HTTP 메시지 바디에 JSON 같은 형식 으로 데이터를 실어 보낸다.

- **`responseBodyV1`**: `HttpServletResponse`를 사용해 HTTP 응답 바디에 직접 "ok" 메시지를 작성합니다. 서블릿을 직접 다루는 전통적인 방식입니다.
```java
    @GetMapping("/response-body-string-v1")
    public void responseBodyV1(HttpServletResponse response) throws IOException {
        response.getWriter().write("ok");
    }
```

- **`responseBodyV2`**: `ResponseEntity`를 통해 HTTP 응답 코드, 헤더, 바디를 설정할 수 있습니다. `HttpStatus.CREATED`를 설정하여 201 응답을 보낼 수도 있습니다.
```java
    @GetMapping("/response-body-string-v2")
    public ResponseEntity<String> responseBodyV2() {
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }
```

- **`responseBodyV3`**: `@ResponseBody` 어노테이션을 사용하면, 뷰 없이 HTTP 메시지 컨버터를 통해 응답 바디에 데이터를 직접 전송할 수 있습니다.
```java
    @ResponseBody
    @GetMapping("/response-body-string-v3")
    public String responseBodyV3() {
        return "ok";
    }
```

- **`responseBodyJsonV1`**: JSON 데이터를 포함한 `ResponseEntity`를 반환합니다. HTTP 메시지 컨버터를 통해 자동으로 JSON 형식으로 변환됩니다.
```java
    @GetMapping("/response-json-v1")
    public ResponseEntity<HelloData> responseJsonV1() {
        HelloData helloData = new HelloData();
        helloData.setUsername("userA");
        helloData.setAge(20);
        return new ResponseEntity<>(helloData, HttpStatus.OK);
    }
```

- **`responseBodyJsonV2`**: `@ResponseBody`를 사용하면 HTTP 응답 코드를 직접 설정하는 것이 어렵습니다. 하지만 `@ResponseStatus(HttpStatus.OK)` 어노테이션을 사용하면, 응답 코드도 설정할 수 있습니다. 동적으로 응답 코드를 변경하고 싶다면 `ResponseEntity`를 사용하세요.
```java
    @ResponseBody
    @GetMapping("/response-json-v2")
    public HelloData responseJsonV2() {
        HelloData helloData = new HelloData();
        helloData.setUsername("userA");
        helloData.setAge(20);
        return helloData;
    }
```


- **`@RestController`**: 이 어노테이션은 `@Controller`에 `@ResponseBody`가 적용된 효과를 제공합니다. 결과적으로, HTTP API를 만드는 데 적합한 컨트롤러가 됩니다.

# HTTP 메시지 컨버터

- 뷰 템플릿으로 HTML을 생성해서 응답하는 것이 아니라, HTTP API처럼 JSON 데이터를 HTTP 메시지 바디에서 직접 읽거나 쓰는 경우 HTTP 메시지 컨버터를 사용하면 편리하다

- HTTP 메시지 컨버터는 Spring MVC에서 HTTP 요청 및 응답을 Java 객체와 HTTP 메시지 간에 변환하는 역할을 합니다. 
- 즉, 클라이언트로부터 오는 요청을 애플리케이션에서 처리하기 쉬운 형태로 변환하거나, 애플리케이션에서 생성된 데이터를 클라이언트가 이해할 수 있는 HTTP 응답으로 변환하는 데 사용됩니다. 

### 주요 기능
- **요청 데이터 변환**: 클라이언트로부터 오는 HTTP 요청(예: JSON, XML)을 Java 객체로 변환합니다.
- **응답 데이터 변환**: 서버에서 생성된 Java 객체를 HTTP 응답(예: JSON, XML)으로 변환하여 클라이언트에 전송합니다.

### 동작 방식
1. **요청 시**: 클라이언트로부터 오는 HTTP 요청의 `Content-Type` 헤더를 기반으로 적절한 `HttpMessageConverter`를 선택하여 요청 바디를 Java 객체로 변환합니다.
2. **응답 시**: `@ResponseBody` 또는 `ResponseEntity`가 반환되는 메서드에서 반환 값의 타입과 클라이언트의 `Accept` 헤더를 기반으로 적절한 `HttpMessageConverter`를 선택하여 Java 객체를 HTTP 응답 바디로 변환합니다.

### 주요 구현체
Spring MVC는 다양한 유형의 데이터를 지원하기 위해 여러 `HttpMessageConverter` 구현체를 제공합니다. 몇 가지 예는 다음과 같습니다:
- `MappingJackson2HttpMessageConverter`: JSON 데이터 변환을 위해 사용됩니다. Jackson 2 라이브러리에 의존합니다.
- `MappingJackson2XmlHttpMessageConverter`: XML 데이터 변환을 위해 사용됩니다. Jackson XML 모듈에 의존합니다.
- `StringHttpMessageConverter`: 문자열 데이터 변환을 위해 사용됩니다.
- `ByteArrayHttpMessageConverter`: 바이트 배열 데이터 변환을 위해 사용됩니다. 이는 주로 이미지나 PDF 파일과 같은 바이너리 데이터를 처리할 때 사용됩니다.

### 커스텀 컨버터
필요에 따라 사용자 정의 `HttpMessageConverter`를 구현하여 Spring MVC에 등록할 수 있습니다. 이를 통해 특정 데이터 형식을 처리하거나 기존 처리 방식을 변경할 수 있습니다.

HTTP 메시지 컨버터는 RESTful API 개발에 있어서 핵심적인 컴포넌트 중 하나로, 데이터 포맷 변환을 자동화하여 애플리케이션 개발의 복잡성을 줄여줍니다.


## 흐름 파악

HTTP 메시지 컨버터의 흐름을 이해하기 위해서, Spring MVC에서 클라이언트의 요청부터 응답까지의 과정을 단계별로 살펴보겠습니다. 이 과정은 크게 요청 데이터의 변환과 응답 데이터의 변환으로 나뉩니다.

### 1. 클라이언트 요청 처리
1. **클라이언트 요청**: 클라이언트(예: 웹 브라우저, 모바일 앱)는 HTTP 요청을 서버로 전송합니다. 이 요청은 JSON, XML 등 다양한 형식의 데이터를 포함할 수 있습니다.
2. **컨트롤러 매핑**: Spring MVC는 요청 URL을 기반으로 적절한 컨트롤러 메서드를 찾습니다.
3. **데이터 변환**: 요청의 `Content-Type` 헤더를 확인하여 적절한 `HttpMessageConverter`를 선택합니다. 이 컨버터는 요청 바디의 데이터를 컨트롤러 메서드의 파라미터 타입에 맞는 Java 객체로 변환합니다.

### 2. 서버 응답 처리
1. **컨트롤러 로직 실행**: 컨트롤러 메서드가 실행되어 비즈니스 로직을 처리하고, 결과 데이터를 생성합니다.
2. **데이터 변환 및 응답**: 
    - 메서드에서 `@ResponseBody`가 사용된 경우, 또는 `ResponseEntity` 객체가 반환되는 경우, Spring MVC는 메서드의 반환 타입과 클라이언트가 요청한 `Accept` 헤더를 기반으로 적절한 `HttpMessageConverter`를 선택합니다.
    - 이 컨버터는 반환된 Java 객체를 클라이언트가 이해할 수 있는 형식(예: JSON, XML)으로 변환하여 응답 바디에 담습니다.
3. **응답 전송**: 변환된 데이터와 함께 HTTP 응답이 클라이언트로 전송됩니다.

### 예시
- 요청 예시: 클라이언트가 JSON 형식의 데이터를 포함한 POST 요청을 보냅니다.
    - `Content-Type: application/json`
    - 요청 바디: `{"username": "user1", "age": 30}`
- 응답 예시: 서버가 처리 결과를 JSON 형식으로 클라이언트에 전송합니다.
    - `Status: 200 OK`
    - `Content-Type: application/json`
    - 응답 바디: `{"message": "사용자 생성 완료", "userId": "123"}`

![[Pasted image 20240331081645.png]]

![[Pasted image 20240331081652.png]]
