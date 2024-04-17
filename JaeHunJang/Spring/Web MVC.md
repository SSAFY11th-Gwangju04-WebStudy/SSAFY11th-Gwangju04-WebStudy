# Spring Web MVC

### Controller
1. DispatcherServlet을 통해 접근할 Controller class를 @Controller로 선언  
2. @RequestMapping 을 통해 url mapping  
3. @GetMapping, @PostMapping로 http method 별 mapping 처리 가능  
4. class 내 method 별로 mapping 처리 가능  
5. Controller Class를 servlet-context.xml에 ```<bean>```으로 등록

### View
prefix + 논리뷰 + suffix 로 설정
```xml
<beans:bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
		<beans:property name="prefix" value="/WEB-INF/views/" />
		<beans:property name="suffix" value=".jsp" />
</beans:bean>
```
ModelAndView 의 ```setViewName("논리뷰이름")``` 으로 뷰 지정  
혹은 String으로 view이름 리턴 ```return "논리뷰이름"```

### Model
View에 전달하는 데이터  
Map, Model, ModelMap