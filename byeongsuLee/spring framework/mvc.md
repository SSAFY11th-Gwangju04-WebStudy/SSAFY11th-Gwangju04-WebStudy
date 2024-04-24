# 수업 요약

<aside>
💡 우리는 spring 레거시 사용중

spring mvc 환경설정하는법
1. web-xml
2. spring/root-context.xml
3. spring/appServlet-

알아야하는 부분
- root , servlet context

실습하면서 몰랐던 부분
- 이미지저장
- redirect는 / 를 붙이는이유?
- forward 방식과 redirect차이

</aside>

# 질문

/book/index 로 시작을 바꿀수 있나요?

- server 설정시 바꿀 수 있는데 모든 프로젝트도 똑같이 바뀜 → 그냥 index.jsp로 시작하자

모든 객체를 빈으로 만드나요?

- 아니다.
- dto 같은 경우 빈으로 등록하지 않고 컨테이너가 관리하지않는다.
- 

webInf - webxml을 어떻게 읽나요?

- tomcat- context에 webinfwebxml을 읽으라고 적혀있음

context란?
- application의 

왜 service , dao 등 root 먼저 만들고 servlet을 만드나요?

- 서비스에서 dao 가 빈으로등록되어있는것처럼 먼저해줘야된답니다.

왼쪽은 servlet context

오른쪽 root.context

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/cd4c5c0a-1563-4707-afa2-6b081ed69d45/f01d73b3-e7bf-4758-bcc1-551395fa8d2f/Untitled.png)

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/cd4c5c0a-1563-4707-afa2-6b081ed69d45/a0bfe1da-c940-48c5-b7f1-7b1c5c8e9a35/Untitled.png)

---

# 오늘의 계획 및 해야할 일

- [ ]  
- [ ]  
- [ ]  
- [ ]  
- [ ]  
- [ ]  

[ACTION 🔥](https://www.notion.so/ACTION-b7e67b32e8044b5dbfdc7e84f137e79e?pvs=21) 

# 

---

# 모르는 내용

---

# 질문

---

# 목차

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/cd4c5c0a-1563-4707-afa2-6b081ed69d45/9af96cc5-485f-40e8-b73f-d7ae9feb3bd9/Untitled.png)

---

# 수업 내용

# MVC 패턴

- Model
    - 어플리케이션 상태의 캡슐화
    - 상태쿼리에 대한 응답
    - 어플리케이션의 기능 표현
    - 변경을 view 통지
- view
    - 모델을 화면에 시각적으로 표현
    - 모델에게 업데이트 요청
    - 사용자의 입력을 컨트롤러에 전달
    - 컨트롤러가 view를 선택하도록 허용
- controller
    - 어플리케이션의 행우 정의
    - 사용자 액션을 모델 업데이트와 mapping
    - 응답에 대한 view 선택

MVC 패턴 특징

- 컴포넌트의 변경이 다른 영역 컴포넌트에 영향을 미치지 않음(유지보수 용이
- 컴포넌트 간의 결합성이 낮아 프로그램 수정이 용이 (확정성이 뛰어남)
- 장점
    - 화면과 비지니스 로직을 분리해서 작업 가능
    - 영역별 개발로 인하여 확장성이 뛰어남
    - 표준화된 코드를 사용하므로 공동작업이 용이하고 유지보수성이 좋음
- 단점
    - 초기 개발속도 늦다.
    - 초보가 이해하고 개발하기에 어렵다.

MVC 흐름

- spring 으로 변환 시 어떤 점이 바뀔까?
    - contorller
        - 종속적인 extend httpservlet 상속 받을 필요없다.
        - reqeust, response, cookie 사라진다.
        - 
    - model
        - 로직은 변경되지않는다.

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/cd4c5c0a-1563-4707-afa2-6b081ed69d45/a4852efd-aa3f-4be7-8716-3a93b402e6ee/Untitled.png)

---

# Spring MVC

- Spring mvc 특징
    - DI, AOP 같은 기능 뿐만아니라 , Servlet 기반의 web개발을 위한 MVC Framework를 제공
    - spring mvc는 mdel2 architecture와 front controoler pattern을 framework 차원에서 제공
    - srping mvc frameworkd는 spring을 기반으로 하고 있기 때문에 spring이 제공하는 transaction 처리나 di 및 aop등을 손쉽게 사용.
    - 

FrontController

- 크게 2가지로 나눠진다.
    - action 구분
    - method 처리
        - 따로 클래스 파서 join,~~등등
        - url를 통해 각각의 메서드에 맞는거를 실행

# spring MVC 구성요소

---

Dispatcher servlet

- Tomcat이 만든다. 개발자가 만드는 게아니다.
- web.xml, @webservlcet() 로 만들었는데 @web이건 못한다. 우리가 만드는게 아니라서
- 역할 : frontcontroller이고 요청을 전달받음

HandlerMapping

- 비교 : 예전에는 if action 으로 메서드 구분했었음
- 역할  : 클라이언트 요청 url을 어떤 controller가 처리할지를 결정
- 어떤 핸들러 객체 사용할지 결정하는 객체

Model And view

- request.set 으로 data 저장해서 view 의 이름을 받아서 view resolver에 줌
- 요청 결과 model , view 이름

# spring mvc 요청흐름

viewResolver

- 설정을 통해 지정된 폴더와 view로 갈 수  있음

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/cd4c5c0a-1563-4707-afa2-6b081ed69d45/d941f92e-e4b2-4036-89ae-9614e8b0bdc3/Untitled.png)

spring mvc 구현

- 방법1 : xml 로 구현
- 방법 2: javaConfig - dispatcherservlet 등록 및 spring 설정 파일등록해야함
- web  , 비 web이 있다.
- web = jsp,controller , 비web= service,dao,db
    - spring 설정파일
    - root 라 web을 가져와 쓸수있따?
    - service,dao,db  등 rootcontext.xml

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/cd4c5c0a-1563-4707-afa2-6b081ed69d45/2e96dcb5-ae2b-4b44-8779-5f0a66f94459/Untitled.png)

# srping mvc project 폴더 위치

- web.xml : src-main-webapp-web-inf
- views : sr

spring mvc 프로젝스 생성 방법

- new dynamic project
- com.ssafy , web.xml create

 

# xml 생성

context 의 설정을 servlet 아래쪽에서는쓸수있지만

반대로는 안됨

context , listenere : root  application 

```
	<context-param>
		<param-name>contextConfigLocation</param-name>
		<param-value>/WEB-INF/spring/root-context.xml</param-value>
	</context-param>
	
	<listener>
		<listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
	</listener>
	
	
	<servlet>
		<servlet-name>appServlet</servlet-name>
		<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
		<init-param>
			<param-name>contextConfigLocation</param-name>
			HM 이나 VR를 설정을 DISPACTHERSERVLET에서 지정을해주야함
			<param-value>/WEB-INF/spring/appServlet/servlet-context.xml</param-value>
		</init-param>
		//APPSERVELT를 초기화
		<load-on-startup>1</load-on-startup>
	</servlet>
	
	//이 서블릿이 언제 실행이되냐?
	<servlet-mapping>
		<servlet-name>appServlet</servlet-name>
		<url-pattern>/</url-pattern>
	</servlet-mapping>
```

context : 프로젝트를 메모리에 올린다.

아래 코드해석

param - name 은 set이 생략되어있어서 name은 쩌런식으로 써야된다는게 지정이 되어있고

value : 경로를 설정함

```
	<context-param>
		<param-name>contextConfigLocation</param-name>
		<param-value>/WEB-INF/spring/root-context.xml</param-value>
	</context-param>
```

로딩이 되는순간 context를 알려줌

```
	<listener>
		<listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
	</listener>
```

view resolver

- views/index.jsp 붙여서 알려줌

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/cd4c5c0a-1563-4707-afa2-6b081ed69d45/ce72129a-ae13-4935-95d0-005703b39a29/Untitled.png)

	*@GetMapping*("/")

@ requestMapping

언제실행하는지 지정

기본적으로 dispactcherserlvet은 forward 방식으로 이동

```
package com.ssafy.hello.controller;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ssafy.hello.model.HelloDto;

@Controller
public class HelloController {

	private static final Logger logger = LoggerFactory.getLogger(HelloController.class);
	//@GetMapping("/")
	@RequestMapping(value = "/",method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("name", "이병수");
		mav.setViewName("index");
		return mav;
		
	}

}

```

```
	<context:component-scan base-package="com.ssafy.hello.controller" />
```

spring mvc : xml vs javaconfig

java할떄 무조건webapllication~~ 를 상속받아야함

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/cd4c5c0a-1563-4707-afa2-6b081ed69d45/ada806ba-6428-4762-a489-667cd4eac23a/Untitled.png)

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/cd4c5c0a-1563-4707-afa2-6b081ed69d45/987e4eba-df7f-4753-91a9-3235ef60cbcb/Untitled.png)

@controller

- request, session 그냥 쓸수있다.

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/cd4c5c0a-1563-4707-afa2-6b081ed69d45/ed21e48d-c685-476b-807e-78e0792abab8/Untitled.png)

```
	@PostMapping("/parameter")
	public ModelAndView parameter(@RequestParam("userName") String userName, @RequestParam("userAge") int age , @RequestParam("fruit") String [] fruit) {
		//data 가 없을고 페이지 이동할떄 
		//String 은 view의 name이 됨
		System.out.println(userName + " " + age +" " +fruit);
		ModelAndView mav = new ModelAndView();
		mav.addObject("userInfo", userName+"("+age+")님안녕하세요.");
		mav.addObject("fruit", fruit);
		mav.setViewName("param/result");
		return mav;
	}
```

## 매개변수로 dto를 지정해주면 알아서 requestParm이 된다.

- 조건 : html의 name과 setName 이 같아야지만 가능

```
	@PostMapping("/paramdto")
	public ModelAndView postMethodName(HelloDto dto) {
		//TODO: 예전에 dto를 다 담아서 처리했는데 바로 dto를 받을 수 있음
		ModelAndView mav = new ModelAndView();
		mav.addObject("result", dto);

		mav.setViewName("param/result_dto");
		return mav;
	}
```

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/cd4c5c0a-1563-4707-afa2-6b081ed69d45/b3bbf2f9-711e-43c9-9307-1d0207f3a557/Untitled.png)

# view

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/cd4c5c0a-1563-4707-afa2-6b081ed69d45/2219fc0e-5439-42d2-8f46-9025a6e28e53/Untitled.png)

이 방법을 제일 많이 씀

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/cd4c5c0a-1563-4707-afa2-6b081ed69d45/15fa94d7-6641-46e4-9d81-4ef612e8532b/Untitled.png)

# spring web application의 동작원리

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/cd4c5c0a-1563-4707-afa2-6b081ed69d45/43d6c14f-d698-4033-b971-2f7bbf0bde57/Untitled.png)

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/cd4c5c0a-1563-4707-afa2-6b081ed69d45/6543643c-8790-49e9-9b59-1f667e73d2ed/Untitled.png)

# 다올쌤 수업

spring 

spring에서 servlet은 dispatcherservlet1개만있음

tomcat에서 이걸관리하는데  web.xml에서 dispatcher를 만듬

- 구성요소
    - controller = sub-controller가 된다.
    - servlet = disatcherserlvelt이됨
- 동작순서

context load → contex 만들기 → dispatcherservlet 만들어짐→ 요청 시 dispatcher가 요청을 가져가고 HandlerMapping

- request.getParam 안쓰고 가져올수있는이유
    - dispatcher가 Map행태로 다 넣어줌
    - 그래서 키로 가져올수있음

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/cd4c5c0a-1563-4707-afa2-6b081ed69d45/8002711a-56eb-4882-8872-78f8a08a635e/Untitled.png)

# 오후

---

---

# 실습

---

# 과제

---
