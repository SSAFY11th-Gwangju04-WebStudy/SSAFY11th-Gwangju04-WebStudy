

<annotiation-driven/>

**`<annotation-driven />`** 태그는 주로 **`servlet-context.xml`** 같은 Spring의 설정 파일에서 사용되며, Spring MVC 프로젝트에서 어노테이션 기반의 설정을 활성화하는 역할을 합니다. 이 태그를 사용함으로써, Spring은 **`@Controller`**, **`@RequestMapping`** 등의 어노테이션을 처리할 수 있게 됩니다. 즉, 개발자가 컨트롤러 클래스에서 HTTP 요청을 처리하는 메소드를 어노테이션으로 표시하면, Spring이 이를 인식하고 적절히 요청을 매핑할 수 있게 해줍니다.

**`<annotation-driven />`** 태그를 사용함으로써 다음과 같은 기능을 자동으로 설정할 수 있습니다:

1. **핸들러 매핑** (**`RequestMappingHandlerMapping`**): 요청 URL을 메소드에 매핑하는 역할을 합니다.
2. **핸들러 어댑터** (**`RequestMappingHandlerAdapter`**): 매핑된 메소드를 실행하는 역할을 합니다.
3. **메시지 컨버터 설정**: JSON이나 XML로 데이터를 자동 변환할 수 있게 해주는 **`HttpMessageConverter`**를 등록합니다.

	<**resources** mapping=*"/assets/**"* location=*"/resources/assets/"* />

**`/resources/assets/`** 위치는 **`webapp/resources/assets/`** 디렉토리를 가리키며, 이 디렉토리 안에 있는 파일들이 **`/assets/`** URL을 통해 접근할 수 있게 됩니다.


# 수업 내용

### SPRING MVC 동작흐름

1. 프로젝트 실행할려면 was 가 실행되어야한다.
2. was-web.xml 설정정보에 따라 was가 실행된다.
3. was에 의해서 project - web.xml 에 따라 읽는다.
4. 프로젝트의 설정은 root application context 와 servlet application context로 있다.
5. root application context는 cll 에 의해 관리된다.
6. root는 한번만 실행하고 serlvet에서 DI해서 자주 쓴다.

### 서블릿 애플리케이션 설정

dispatcherservelt이 관리하고 servlet-context.xml을 읽는다.

servlet -context.xml 

- viewResolver, resourses mapping , context component-scan > controller 읽기 ,
- resources : /png 하면 dispatcher에 의해서 핸들러매핑이 실행되기 떄문에 정적 리소스로 변경해주는 것이라고 생각하면된다.

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/cd4c5c0a-1563-4707-afa2-6b081ed69d45/8729c428-3039-4d96-ab81-fef07793804e/Untitled.png)

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/cd4c5c0a-1563-4707-afa2-6b081ed69d45/741a32a3-9098-4fd8-9409-805ebfc3c804/Untitled.png)

<init-param>

<param-name>throwsExceptionIfNoHandlerFound<param-name>

핸들러를 찾지 못하면 404 에러를 exception으로 던져주는 설정

이 param을 setting해주지않아도 기본값으로 true로 되어있다.

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/cd4c5c0a-1563-4707-afa2-6b081ed69d45/75baad54-d57d-41fd-a48d-8dadd78056f6/Untitled.png)

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/cd4c5c0a-1563-4707-afa2-6b081ed69d45/e881666a-1e2d-4caf-af0c-c04226bb427a/Untitled.png)

그럼 왜 에러가나면 에러페이지로 띄울 수 있을까?

## 에러발생시 에러페이지 띄우는방법

방법 1: controller안에 넣기

- 해당 controll에서 일어난 에러만 처리

방법 2

- @ controller advice 사용
- 주의할점 : service나 repository에서 나는 에러를 처리할 수 없음
- 예외는 servelt-context에 정의되어 있기 때문에 설정정보를 root-context에서 사용할 수 없기때문에 service,dao에서 사용할 수 없다.

방법3

- try catch해서 임의로 다 지정
- 모든 요청에 try catch 작성 반복된 코드가 많아짐 → 방법 2가 나오게 된 이유

# 비동기 통신으로 데이터 전송하는방법

- 페이지 이동없이 지금 화면에서 fetch를 통해 해당 jsp에 데이터 전송할때 쓴다.
- @ responseBody 사용, @PathVariable 사용
- @pathvariable

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/cd4c5c0a-1563-4707-afa2-6b081ed69d45/1f0fb613-3177-4759-8a9d-fa9e356f5ed1/Untitled.png)

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/cd4c5c0a-1563-4707-afa2-6b081ed69d45/dd5390b4-4d66-4561-9f60-48b158898116/Untitled.png)

dto가 없을때 한번에 받고 싶으면 map을 사용한다.

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/cd4c5c0a-1563-4707-afa2-6b081ed69d45/549d6f78-e37d-4d6b-866a-662d2a6d46f4/Untitled.png)

multipart 란?

- 하나의 request에 문자열과 file 형태를 보내는데 두개를 구분하기 위해서 사용
    
    ![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/cd4c5c0a-1563-4707-afa2-6b081ed69d45/2cad1a71-8f29-497d-9e34-02443bea0a63/Untitled.png)
    

# spring 에서 파일 업로드 하는방법

- jsp 설정 : input type = “file” , form enctype= “multipart-form” , multiple = “mutltiple”
- web.xml 설정 : 파일 용량 제한 설정
- servlet context 설정 :   multipartResolver 빈 등록

# 사용시 반드시 해야될것 3가지

- form attribute enctype=”multpart/form-data”
- 파일용량제한
- 빈 등록

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/cd4c5c0a-1563-4707-afa2-6b081ed69d45/ba28a3df-7758-4236-aafe-fa4192973afb/Untitled.png)

파일용량 제한 

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/cd4c5c0a-1563-4707-afa2-6b081ed69d45/69acbc12-5ca7-48b0-bf4d-ad8f250c0ecb/Untitled.png)

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/cd4c5c0a-1563-4707-afa2-6b081ed69d45/b0a07310-1db4-4300-822d-b1d2382160b1/Untitled.png)

multiple

여러개 파일을 올릴수 있다.

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/cd4c5c0a-1563-4707-afa2-6b081ed69d45/179a8e05-9571-47bd-aa42-c98031d206fc/Untitled.png)

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/cd4c5c0a-1563-4707-afa2-6b081ed69d45/74a18b59-e467-4da5-9950-7558e5bfd667/Untitled.png)

# file 다운로드

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/cd4c5c0a-1563-4707-afa2-6b081ed69d45/2eb4d5f1-2323-45b3-b368-5a603629f32f/Untitled.png)

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/cd4c5c0a-1563-4707-afa2-6b081ed69d45/26f3752e-abc0-4fa2-b816-ad7a7cdb8736/Untitled.png)

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/cd4c5c0a-1563-4707-afa2-6b081ed69d45/8a903393-c63f-45d2-a5b6-d0939637e246/Untitled.png)

# intercept

- Cotnroller 가 요청을 처리하기 전 / 후 처리
- 공통 코드 사용으로 코드 재사용성 증가
- 로깅,모니터링 정보 수집
- interceptor를 여러개 설정 가능 but 순서주의
- 사용 예
    - login session 검증
    - header 검증
    - jwt token 검증
- aop와 다른점
    - aop는 일반적으로 비web이고 intercept는 web임

- preHandle만 주로사용 :

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/cd4c5c0a-1563-4707-afa2-6b081ed69d45/2e4a77ad-33ed-4f15-97cc-c973ed728c95/Untitled.png)

pre는 등록순서 post 역순 , after 역순

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/cd4c5c0a-1563-4707-afa2-6b081ed69d45/800373d5-e64c-40a1-8d6f-55ddc9e7d1e9/Untitled.png)

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/cd4c5c0a-1563-4707-afa2-6b081ed69d45/79dafb98-9ee2-472b-97fd-724e071cd070/Untitled.png)

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/cd4c5c0a-1563-4707-afa2-6b081ed69d45/6b7bbcf1-0826-46d1-8399-e4937aa4dc41/Untitled.png)

# filter

- setCharcterset 사용
- 주로 사용하는곳
    - filter는  dispacter 서블릿보다 이전에 실행됨

서버에서 10.1 버전에서 setCharacter 자동으로되어있다. 하지만 이전버전에서는 dopost에서 적용해야되는데

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/cd4c5c0a-1563-4707-afa2-6b081ed69d45/ccc65ca5-74c7-4038-a90d-75449cd53e3a/Untitled.png)

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/cd4c5c0a-1563-4707-afa2-6b081ed69d45/d2eb0b19-83ce-412e-9238-a5ea3ce7a05b/Untitled.png)

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/cd4c5c0a-1563-4707-afa2-6b081ed69d45/40adad61-3735-42cd-887e-b9d56dc4d7b8/Untitled.png)

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/cd4c5c0a-1563-4707-afa2-6b081ed69d45/5ed47d9a-f0bd-4533-bb7c-c3870b6b6c58/Untitled.png)

# 오전

---

# 오후

---

# 실습

# 파일 업로드

### 설정 3가지

- form  설정
- 

```
        <form id="form-register" method="POST" enctype="multipart/form-data" action="">
        <div class="mb-3">
              <label for="img" class="form-label">이미지 : </label>
               <input
                type="file"
                class="form-control"
                id="img"
                name="img"
                placeholder="이미지"
                multiple="multiple"
              />
         
            </div>
```

- webapp_web.xml 파일에 multipart-config 설정추가

```
	<multipart-config>
			<max-file-size>52428800</max-file-size>	<!-- 파일 하나당 최대 파일 크기 -->
			<max-request-size>52428800</max-request-size><!-- 업로드 파일의 총 크기 -->
			<file-size-threshold>0</file-size-threshold><!-- 업로드하는 파일이 임시로 파일로 저장되지 
				않고 메모리에서 바로 스트림으로 전달되는 크기의 한계 1024 * 1024로 설정하면 1MB 이상인 경우에만 임시 파일로 저장. -->
			<!-- <location></location> --><!-- 임시저장 경로 -->
		</multipart-config>
```

- servlet-conext에 추가

```
	<!-- 6.x -->
	<beans:bean id="multipartResolver" class="org.springframework.web.multipart.support.StandardServletMultipartResolver"/>
	<!-- // fileUpload -->

	<!-- fileDownload -->
	<beans:bean id="fileDownLoadView" class="com.ssafy.board.view.FileDownLoadView"/>
	<!-- BeanNameViewResolver 설정. -->
	
	<beans:bean id="fileViewResolver" class="org.springframework.web.servlet.view.BeanNameViewResolver">
		<beans:property name="order" value="0" />
	</beans:bean> 
```

```
	@PostMapping("/regist")
	public String regist(@ModelAttribute Book book,
			@RequestParam(value = "img", required = false) MultipartFile[] files, HttpSession session,
			RedirectAttributes redirectAttributes) throws Exception {
		
```

# 파일 다운로드

```
	@GetMapping("/download")
	public ModelAndView downloadFile(@RequestParam("sfolder") String sfolder, @RequestParam("ofile") String ofile,
			@RequestParam("sfile") String sfile) {
		Map<String, Object> fileInfo = new HashMap<String, Object>();
		fileInfo.put("sfolder", sfolder);
		fileInfo.put("ofile", ofile);
		fileInfo.put("sfile", sfile);
		return new ModelAndView("fileDownLoadView", "downloadFile", fileInfo);
	}
	
```

### 파일다운로드 클래스 만들기

```
package com.ssafy.book.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class FileDownLoadView extends AbstractView {

	public FileDownLoadView() {
		setContentType("apllication/download; charset=UTF-8");
	}
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ServletContext ctx = getServletContext();
		String realPath = ctx.getRealPath("/upload");
		
		Map<String, Object> fileInfo = (Map<String, Object>) model.get("downloadFile"); // 전송받은 모델(파일 정보)
        
        String saveFolder = (String) fileInfo.get("sfolder");	// 파일 경로
        String originalFile = (String) fileInfo.get("ofile");	// 원본 파일명(화면에 표시될 파일 이름)
        String saveFile = (String) fileInfo.get("sfile");    	// 암호화된 파일명(실제 저장된 파일 이름)
        File file = new File(realPath + File.separator  + saveFolder, saveFile);
		
        response.setContentType(getContentType());
        response.setContentLength((int) file.length());
        
        String header = request.getHeader("User-Agent");
        boolean isIE = header.indexOf("MSIE") > -1 || header.indexOf("Trident") > -1;
        String fileName = null;
        // IE는 다르게 처리
        if (isIE) {
        	fileName = URLEncoder.encode(originalFile, "UTF-8").replaceAll("\\+", "%20");
        } else {
            fileName = new String(originalFile.getBytes("UTF-8"), "ISO-8859-1");
        }
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\";");
        response.setHeader("Content-Transfer-Encoding", "binary");
        OutputStream out = response.getOutputStream();
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            FileCopyUtils.copy(fis, out);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(fis != null) {
                try { 
                    fis.close(); 
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        out.flush();
    }
}
```

---

# 과제

---
