## 프로젝트 실행

- WAS 실행
    - conf - xml, prop-,  pol- … 파일 읽어들임.
    - xml은 tomcat이 읽는 것.
- server.xml에 실행할 프로젝트의 Context가 존재.
    - Context가 메모리에 올라감 (= 프로젝트가 메모리에 올라감).
- 각각의 프로젝트마다 web.xml을 가지고 있음.
    - web.xml은 프로젝트의 설명.
- web.xml은 크게 2가지로 나뉨.
    1. Root Application Context.
        - ContextLoaderListener 객체가 담당.
    2. Servlet Application Context.
        - DispatcherServlet 객체가 담당.
- web.xml을 읽는 순간 위 2가지가 로드됨.
    - 이때 Root Application Context가 먼저 로드가 됨.
    - Root Application Context(Service, Dao 등)는 한번만 만들면 된다. 만들어 놓으면 여러 DispatcherServlet이 사용 가능.
- Root Application Context는 Root-Context.xml을 읽어들임
    - 비 web ⇒ Service, Dao, DB, AOP등
- Servlet Application Context는 Servlet-Context.xml을 읽어들임
    - web ⇒ ViewResolver, resource 설정, Controller 설정, file upload, file download, interceptor
    - resource 설정
        
        ![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/609d46e8-84fd-4b9e-9a41-bd47c7d63d8e/6ec50f2e-5c48-4836-8f79-2067f8f2a148/Untitled.png)
        
        - {root}/assets 와 같은 요청이 들어올때, DispatcherServlet이 처리하게 하는 것이 아닌 정적 파일로 인식시키게 하는 것.
- 위의 과정이 끝나면 프로젝트가 서버에 올라간 것.
- 클라이언트가 요청을 보냄 {root}/board/
- DispatcherServlet이 요청을 받음 (/board/)
- HandlerMapping에게 어디로 가야하는지 확인
    - HandlerMapping은 어떤 Controller의 어떤 method로 가야하는지 알려줌
- DispatcherServlet은 목표 Controller 호출
- Controller는 목표지를 반환하거나 기능 수행을 위해 Service와 Dao를 거쳐 결과에 해당하는 Model과 화면을 보여주는 View라는 페이지를 가지고 반환
- ViewResolver가 View의 이름을 가지고 ~.jsp로 만들어줌 그리고 그것을 최종적으로 클라이언트에게 반환

## web.xml

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/609d46e8-84fd-4b9e-9a41-bd47c7d63d8e/9fe5c293-4c3f-40f9-aef3-7f8bc94b47fc/Untitled.png)

- line 8~16 - Root Application Context (ContextLoaderListener 담당)
- line 34~52 - Servlet Application Context (DispatcherServlet 담당)
- line 37~45 - DispatcherServlet이 생성될 때 파라미터를 지정해주는 부분. set property임.
- line 42~45 - 404라는 Error가 발생했을때, Exception으로 throw 해주는 설정. (DispatcherServlet이 해당 mapping을 찾지 못할 경우 throwExceptionIfNoHandlerFound를 throw 해준다.)
현재 spring 버전에서는 deprecated 되어 설정 안해도 기본 값으로 잡힘

## root-context.xml (비 Web)

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/609d46e8-84fd-4b9e-9a41-bd47c7d63d8e/4bbd61c7-0c28-408b-aa0e-29a06b4645a8/Untitled.png)

- line 15~20 - Database 연결
- line 13 - AOP위한 proxy 설정
- line 11 - Service, Dao scan (Controller를 제외한 나머지에 해당)

## servlet-context.xml (Web)

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/609d46e8-84fd-4b9e-9a41-bd47c7d63d8e/3364b6c8-7263-4a77-ad59-3368cf6141b8/Untitled.png)

- line 20 - resource 제외 (DispatcherServlet이 체크하는 것이 아닌 정적 파일로 인식)
- line 23 ~ 26 - ViewResolver 설정 (Controller에게 받은 view name에 prefix: 접두사, suffix: 접미사를 붙임)
- line 28 - Controller scan

## Exception 처리 방법

-

1. 직접적으로 try ~ catch 사용 
    - 매번 try ~ catch를 해야하는 번거로움이 발생한다.
    
    ```java
    	// MemberController의 일부
    	
    	@PostMapping("/join")
    	public String join(MemberDto memberDto, Model model) {
    		logger.debug("memberDto info : {}", memberDto);
    		try {
    			memberService.joinMember(memberDto);
    			return "redirect:/user/login";
    		} catch (Exception e) {
    			e.printStackTrace();
    			model.addAttribute("msg", "회원 가입 중 문제 발생!!!");
    			return "error/error";
    		}
    	}
    ```
    
2. 각 Controller에서 Exception 처리
    - Controller 하단에 ExceptionHandler 작성
    - 이 경우  선언한 Controller 안에서 Exception이 발생할 경우 처리를 해준다. 다른 Controller에는 해당 안됨.
    
    ```java
    	// BoardController의 일부
    	
    	@PostMapping("/modify")
    	public String modify(BoardDto boardDto, @RequestParam Map<String, String> map, RedirectAttributes redirectAttributes) throws Exception {
    		logger.debug("modify boardDto : {}", boardDto);
    		boardService.modifyArticle(boardDto);
    		redirectAttributes.addAttribute("pgno", map.get("pgno"));
    		redirectAttributes.addAttribute("key", map.get("key"));
    		redirectAttributes.addAttribute("word", map.get("word"));
    		return "redirect:/article/list";
    	}
    	
    	@GetMapping("/delete")
    	public String delete(@RequestParam("articleno") int articleNo, @RequestParam Map<String, String> map, RedirectAttributes redirectAttributes) throws Exception {
    		logger.debug("delete articleNo : {}", articleNo);
    		boardService.deleteArticle(articleNo);
    		redirectAttributes.addAttribute("pgno", map.get("pgno"));
    		redirectAttributes.addAttribute("key", map.get("key"));
    		redirectAttributes.addAttribute("word", map.get("word"));
    		return "redirect:/article/list";
    	}
    
    	// Exception 처리
    	@ExceptionHandler(Exception.class)
    	public String handleException(Exception ex, Model model) {
    		logger.error("Exception 발생 : {}", ex.getMessage());
    		model.addAttribute("msg", "처리중 에러 발생!!!");
    		return "error/error";
    	}
    	
    }
    ```
    
3. ExceptionControllerAdvice로 일괄 처리 (전역 ExceptionHandler)
    - Controller의 method가 Exception을 throw 하면 ExceptionControllerAdvice가 받는다.
    - Controller들에서 발생하는 Exception만 처리, Service나 Dao에서 발생하는 Exceptiond은 처리하지 못한다.
    
    ```java
    import org.slf4j.Logger;
    import org.slf4j.LoggerFactory;
    import org.springframework.http.HttpStatus;
    import org.springframework.ui.Model;
    import org.springframework.web.bind.annotation.ControllerAdvice;
    import org.springframework.web.bind.annotation.ExceptionHandler;
    import org.springframework.web.bind.annotation.ResponseStatus;
    import org.springframework.web.servlet.NoHandlerFoundException;
    
    @ControllerAdvice
    public class ExceptionControllerAdvice {
    
    	private final Logger logger = LoggerFactory.getLogger(ExceptionControllerAdvice.class);
    
    	// 모든 에러
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
    

## File Upload

> Multipart
> 
- form에 사용자가 입력하는 문자열 data(input type=”text”)와 업로드할 파일(input type=”file”)이 공존.
- 입력 문자열의 경우 Content-type의 경우 application/x-www-from-urlencoded이고 file의 경우 image/jpeg.. 형식.
- 하나의 request에 서버가 해석해야 하는 Content-type이 두가지가 되어 서버는 이를 구분해서 해석해야 하는 방법 필요.
- 이를 해결하는 방법이 multipart type
- form의 attribute에 반드시 enctype=”multipart/form-data”를 설정하고 method 또한 “POST”로만 가능
    
    ![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/609d46e8-84fd-4b9e-9a41-bd47c7d63d8e/cd27f460-c41e-49fb-bc97-46110cf07025/Untitled.png)
    

> File Upload
> 
- org.springframework.web.multipart.commons.CommonsMultipartResolver: 6.x 버전 이하
- org.springframework.web.multipart.support.StandardServletMultipartResolver 사용
    - Servlet 3.0 이후부터 사용 (javax naming issue)
    - servlet-context.xml: Spring Bean으로 등록 시 id는 반드시 “multipartResolver”로 등록해야 함.
        
        ![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/609d46e8-84fd-4b9e-9a41-bd47c7d63d8e/9b800d68-43f7-4715-9371-4dff7b78b247/Untitled.png)
        
    - web.xml: DispatcherServlet이 Servlet 3의 Multipart를 처리하도록 설정
        
        ![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/609d46e8-84fd-4b9e-9a41-bd47c7d63d8e/e5864c19-dc0c-47e1-8160-196dc4ac4599/Untitled.png)
        
        - multipart-config는 DispatcherServlet이 가지고 있는게 아님. tomcat이 가지고 있다. (
        https://tomcat.apache.org/tomcat-11.0-doc/servletapi/jakarta/servlet/annotation/MultipartConfig.html)
            
            ![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/609d46e8-84fd-4b9e-9a41-bd47c7d63d8e/8459b480-2fda-4a93-94d7-b3658025b008/Untitled.png)
            

> multipart-config 설정
> 

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/609d46e8-84fd-4b9e-9a41-bd47c7d63d8e/b0856783-10d3-4d73-b3ec-db6f65fa2c4a/Untitled.png)

## File Download

> File Download
> 
- view.jsp
    
    ![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/609d46e8-84fd-4b9e-9a41-bd47c7d63d8e/9a4ffba8-347c-4466-b92d-f9f36e4fba0f/Untitled.png)
    
    ![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/609d46e8-84fd-4b9e-9a41-bd47c7d63d8e/e0e08372-dae7-4922-8c47-6dbd8f993384/Untitled.png)
    
    ![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/609d46e8-84fd-4b9e-9a41-bd47c7d63d8e/95f70dfc-031d-4016-81b9-b76ec5d94cf1/Untitled.png)
    
- servlet-context.xml
    - view 이름과 동일한 이름을 갖는 bean 객체를 view로 사용.
    - 주로 파일 다운로드 기능을 위한 Custom View로 사용됨.
    - order property를 0으로 설정하여 기본 ViewResolver인 InternalResourceViewResolver보다 먼저 동작하도록 설정
    
    ![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/609d46e8-84fd-4b9e-9a41-bd47c7d63d8e/54253b02-c685-4258-9200-9f0df8b9f558/Untitled.png)
    
    - line 63~65 - bean의 이름을 view로 사용할것이다.
    - line 64 - 기존의 접두사, 접미사를 붙여 경로를 만들어주는 ViewResolver 말고 BeanNameViewResolver를 먼저 잡기 위한 order 설정

## Interceptor

- Controller가 요청을 처리하기 전/후 처리.
- 공통 코드 사용으로 코드 재사용성 증가.
- 로깅, 모니터링 정보 수집, 접근 제어 처리 등의 실제 Business Logic과는 분리되어 처리해야 하는 기능들을 넣고 싶을 때 유용함.
- interceptor를 여러 개 설정할 수 있다. (순서 주의!)
- AOP와 유사한 점이 많지만 사용 예는 다르다.
    - AOP는 비 web
    - Interceptor는 web (controller 기준)
- 사용 예
    - Login Session 검증
    - Header 검증
    - (JWT) token 검증 등등
    
    > HandlerInterceptor 제공 method (Controller 기준)
    > 
    
    ![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/609d46e8-84fd-4b9e-9a41-bd47c7d63d8e/923fea48-6f1a-402a-b1be-cafb349b9c50/Untitled.png)
    
    - Request, Response가 필요(session을 얻기 위함 )하기 때문에 AOP는 로그인 유효성 검사가 안된다.
    
    > method 실행 시점
    > 
    
    ![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/609d46e8-84fd-4b9e-9a41-bd47c7d63d8e/8161ee13-4fa7-481e-8459-e474e713da2a/Untitled.png)
    

## Interceptor 등록 및 호출

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/609d46e8-84fd-4b9e-9a41-bd47c7d63d8e/c5a4933d-298a-4bdb-878e-2b2aed0bd6db/Untitled.png)

- A, B, C 등록 순서대로 preHandle 호출
- post, after는 역순 호출

## HandlerInterceptor 인터페이스 구현

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/609d46e8-84fd-4b9e-9a41-bd47c7d63d8e/7d16d367-7d81-421e-a349-267f7dfab83e/Untitled.png)

## Interceptor 사용 예시 - session check

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/609d46e8-84fd-4b9e-9a41-bd47c7d63d8e/f7632b60-32d8-4eca-aaa7-d6119f86c0a3/Untitled.png)

- line 37~40 - interceptor를 걸 위치
- line 44 - interceptor를 걸어서 실행할 클래스, line 30에 해당

## Filter, Interceptor, AOP 실행 순서

1. 서버 실행시

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/609d46e8-84fd-4b9e-9a41-bd47c7d63d8e/577038be-4c08-4a04-9849-f14e9af64013/Untitled.png)

1. url 호출시

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/609d46e8-84fd-4b9e-9a41-bd47c7d63d8e/20d50b40-d9a9-4ddb-b9c0-ab2936fd6886/Untitled.png)
