# 3. 서블릿, JSP, MVC 패턴

### 회원 관리 웹 애플리케이션

- 테스트 만들기 단축키 C + S + T
- static import = O + E
- 실무에서 Map 사용시 ConcurrentHashMap, AtomicLong 사용을 고려해라

### Test 코드 만드는 법

```java
// 해당 어노테이션이 있으면 테스트 함수 호출 후마다 실행됨
@AfterEach  
void afterEach() {  
    memberRepository.clearStore();  
}
```
- given - when - then 형식!

-> servlet : 자바 내에 Html을 삽입함, 동적 불가능 + 비효율적
= JSP, Thymeleaf, Freemarker, Velocity 등의 템플릿 엔진이 나오게 되었음
 (내부에서 JSP -> servlet 형태로 변환됨 ㅇㅇ)

### MVC 패턴 - 개요
하나의 서블릿, JSP가 비즈니스 로직 + 뷰 + 렌더링 모두 처리함 따라서 
- 유지보수의 어려움
- 변경의 라이프 사이클이 달라 서로 영향을 미치지 않는데 하나의 코드로 관리하면 유지보수가 어려움
- 특화된 업무만 담당하는 것이 가장 효과적임

Model - View - Controller 패턴
- Controller : http 요청을 받아 파라미터 검증, 로직, 조회해 모델에 담기
	- 비즈니스 로직은 Service로 따로 담아 관리함
- Model: 뷰에 출력할 테이터를 담아둠, 화면 렌더링에 집중
- View: 모델에 있는 데이터를 화면그리는 일에 집중

- action에 "/" 로 시작하지 않으면 상대경로임!

Redirect vs Forward
실제 클라이언트에 응답이 나갔다가 redirect경로로 다시 요청이 들어옴 
서버 내부에서 일어나는 호출

전엔 몰랐는데 JSTL, el 도 사용함...
- build.gradle 에 jstl이 달라짐
```
//implementation 'javax.servlet:jstl'  
implementation group: 'org.glassfish.web', name: 'jakarta.servlet.jsp.jstl', version: '2.0.0'
```

### 한계
1. forword 중복
2. viewPath 중복
3. 사용하지 않는 코드 (response)
4. **공통처리** 가 어려움 (공통처리한 메서드를 호출해야함)

-> 이를 Front Controller 패턴으로 이런 문제를 깔끔하게 처리하자!!
(스프링 mvc 의 핵심이 프론트 컨트롤러!)
