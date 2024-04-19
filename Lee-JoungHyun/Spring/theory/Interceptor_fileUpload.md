# Interceptor/fileUpload


## 예외 처리!!

```java
@ControllerAdvice
public class ExceptionControllerAdvice {

	private final Logger logger = LoggerFactory.getLogger(ExceptionControllerAdvice.class);

	@ExceptionHandler(Exception.class)
	public String handleException(Exception ex, Model model) {
		logger.error("Exception 발생 : {}", ex.getMessage());
		model.addAttribute("msg", "처리중 에러 발생!!!");
		return "error/error";
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public String handle404(NoHandlerFoundException ex, Model model) {
		logger.error("404 발생 : {}", "404 page not found");
		model.addAttribute("msg", "해당 페이지를 찾을 수 없습니다!!!");
		return "error/error";
	}
}
```

**컨트롤러의 예외를 처리해주는 컨트롤러 후처리…?**

에러처리 방법 3가지! 

## FileUpload

**MultiPart**

Form 에 사용자가 입력하는 문자열 data와 업로드할 파일이 공존함

- 문자열인 경우 Content-type의 경우 application/x-www-form-urlencoded
- file 인 경우 Content-type이 image/jpeg… 형식

브라우저 입장에서 이를 구분하고 해석하는 방법이 Multipart 

반드시 `entype="multipart/form-data` 설정, method도 `POST` 만 가능!

1. 스프링 빈 등록 (id는 항상 `"mulitpartResolver”` )

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/60d66ae7-5f64-44bc-b4b0-f484626a8090/85f43831-4c93-4194-a028-0afeaeb6e9e9/Untitled.png)

```xml
<!-- servlet-context.xml -->
<beans:bean id="multipartResolver" class="org.springframework.web.multipart.support.StandardServletMultipartResolver"/>
```

1. 설정정보 등록

```xml
<!-- web.xml -->
<servlet>
	<multipart-config>
			<max-file-size>52428800</max-file-size><!-- 파일 하나당 최대 파일 크기 -->
			<max-request-size>52428800</max-request-size><!-- 업로드 파일의 총 크기 -->
			<file-size-threshold>0</file-size-threshold><!-- 업로드하는 파일이 임시로 파일로 저장되지 않고 메모리에서 바로 스트림으로 전달되는 크기의 한계 1024 * 1024로 설정하면 1MB 이상인 경우에만 임시 파일로 저장. -->
			<!-- <location></location> --><!-- 임시저장 경로 -->
	</multipart-config>
</servlet>
```

## Interceptor

- `Controller가` 요청을 처리하기 전/후 처리!
- 공통 코드 사용! (코드 재사용성 증가)
- 사용 예시
    - Login Session 검증
    - Header 검증
    - (JWT) token 검증
- AOP와의 차이점 (AOP는 일반적으로 Service, Dto, 등에서 사용된다!!)
    - 물론 컨트롤러에서도 사용은 할 수 있긴하다..
- 여러개 등록이 가능하다, but 순서에 주의해라!!!

### 사용 메서드

- boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
    - 주로 사용됨! (AOP는 request, response 가 없어서 입력데이터, 반환하기가 귀찮음…)
- void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView mav)
- void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler)

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/60d66ae7-5f64-44bc-b4b0-f484626a8090/a52ead51-623e-4c42-8ac8-e0fddf4ba43e/Untitled.png)

### 사용 예시

1. 구현

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/60d66ae7-5f64-44bc-b4b0-f484626a8090/0510fa3a-9b5c-46d5-8578-a665401b3744/Untitled.png)

1. 등록 (servlet-context.xml)

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/60d66ae7-5f64-44bc-b4b0-f484626a8090/34f4c9a2-21ef-469a-b1ec-1d500b25894c/Untitled.png)

### Interceptor 등록 및 호출 (순서)

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/60d66ae7-5f64-44bc-b4b0-f484626a8090/267264c3-3e7c-42af-a239-592f914a5538/Untitled.png)

## Filter 사용(Java)

```java
@Component // 모든 http 메소드 처리.
@WebFilter(urlPatterns = "/") // 특정 url만 처리.
@Order(1) // Filter가 두개 이상이 될 경우 실행 순서 결정.
public class FiaFilter1 implements Filter {
	
	private static final Logger log = LoggerFactory.getLogger(FiaFilter1.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		Filter.super.init(filterConfig);
		log.info((new Date()).toString() + " ==> Filter ::: FiaFilter1 Constructor");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		log.info((new Date()).toString() + " ==> Filter111 ::: doFilter pre(1)");
		chain.doFilter(request, response);
		log.info((new Date()).toString() + " ==> Filter111 ::: doFilter after(1)");
	}
	
	@Override
	public void destroy() {
		Filter.super.destroy();
	}

}
```

## 통신과정 정리

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/60d66ae7-5f64-44bc-b4b0-f484626a8090/276bf35d-b8c5-4a34-887b-de533ea56683/Untitled.png)

- Filter 는 Servlet 접근 전에 적용됨
- Interceptor 는 servlet 에서 핸들러 매핑 후에 컨트롤러 접근시 적용됨

## Interceptor, AOP, Filter

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/60d66ae7-5f64-44bc-b4b0-f484626a8090/c2a35ffc-88ff-43b2-86ed-5e0b1f9ebb7e/Untitled.png)
