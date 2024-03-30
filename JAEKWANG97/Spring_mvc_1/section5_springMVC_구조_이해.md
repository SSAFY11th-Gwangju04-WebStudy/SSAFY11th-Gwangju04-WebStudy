## 스프링 MVC 전체 구조

- 스프링 MVC는 프론트 컨트롤러 패턴을 기반으로 구성됨. 
- 이 패턴에서, 모든 요청은 중앙 집중화된 단일 컨트롤러(DispatcherServlet)를 통과함
- DispatcherServlet은 요청을 핸들러(컨트롤러)에게 전달하고, 응답을 뷰로 렌더링하는 역할을 합니다.

### DispatcherServlet 구조

- **DispatcherServlet**은 SpringMVC의 핵심 요소로, HttpServlet을 상속받아 서블릿으로 동작함.
- 스프링 부트는 DispatcherServlet을 자동으로 등록하고, 모든 경로`(urlPatterns="/")`에 대해 매핑함

- **요청 흐름**
  1. 서블릿이 호출되면, HttpServlet의 `service()` 메소드가 실행됨.
  2. `DispatcherServlet`의 `doDispatch()` 메소드가 호출되어 실제 요청 처리 흐름을 시작함.

### 핵심 요소 (중요)

1. **핸들러 매핑(Handler Mapping)**: 요청 URL에 매칭되는 핸들러(컨트롤러)를 찾음.
2. **핸들러 어댑터(Handler Adapter)**: 핸들러를 실행할 수 있게 함.
3. **뷰 리졸버(View Resolver)**: 뷰 이름(문자열)을 실제 뷰 객체로 변환함.
4. **뷰(View)**: 최종적으로 클라이언트에게 HTML 등의 형태로 응답을 렌더링함.

### 핸들러 매핑과 핸들러 어댑터

- 스프링은 다양한 타입의 핸들러를 지원하기 위해 핸들러 매핑과 핸들러 어댑터를 제공함

### 뷰 리졸버

- **뷰 리졸버**는 컨트롤러가 반환한 뷰 이름을 기반으로 실제 뷰 객체를 찾는 역할을 합니다. 예를 들어, JSP의 경우 `InternalResourceViewResolver`가 사용됨.

## 스프링 MVC 시작하기

스프링 MVC를 시작하기 위해서는 애노테이션 기반의 컨트롤러를 이해하는 것이 중요.
`@Controller` 애노테이션을 사용해 클래스를 컨트롤러로 선언하고, `@RequestMapping` 애노테이션으로 요청 경로를 매핑할 수 있음.

## 스프링 MVC 컨트롤러 통합

- 스프링 MVC에서는 `@RequestMapping` 애노테이션을 메서드 레벨뿐만 아니라 클래스 레벨에서도 사용할 수 있음. uri를 조합할 수 있다는 것임. 이를 통해 관련된 요청들을 하나의 컨트롤러로 통합하여 관리할 수 있음음

## 스프링 MVC 실용적인 방식

스프링 MVC는 개발자가 보다 편리하게 웹 애플리케이션을 개발할 수 있도록 다양한 편의 기능을 제공함. 너무 좋음.  이러한 편의 기능은 주로 애노테이션 기반의 컨트롤러를 사용에 있음

### SpringMemberControllerV3 - 실용적 예예시

```java
package hello.servlet.web.springmvc.v3;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/springmvc/v3/members")
public class SpringMemberControllerV3 {
    private MemberRepository memberRepository = MemberRepository.getInstance();

    @GetMapping("/new-form")
    public String newForm() {
        return "new-form";
    }

    @PostMapping("/save")
    public String save(@RequestParam("username") String username, @RequestParam("age") int age, Model model) {
        Member member = new Member(username, age);
        memberRepository.save(member);

        model.addAttribute("member", member);
        return "save-result";
    }

    @GetMapping
    public String members(Model model) {
        List<Member> members = memberRepository.findAll();
        model.addAttribute("members", members);
        return "members";
    }
}
```

- 이 컨트롤러는 `@Controller` 애노테이션을 사용해 스프링 MVC에서 애노테이션 기반 컨트롤러로 작동함.
- `@RequestMapping` 애노테이션은 클래스 레벨에서 사용되어 `/springmvc/v3/members`라는 기본 경로를 설정하고, 메소드 레벨에서는 `@GetMapping` 또는 `@PostMapping`을 사용하여 HTTP 메소드와 경로를 세부적으로 매핑함.
- 파라미터로 어노테이션을 활용해서 변수 선언을 바로 해줄 수 있음. 타입 변환도 자동 --> 이게 너무 편하다.

#### 주요 특징:

- **Model 파라미터**: `Model`을 메소드의 파라미터로 사용하여 뷰에 데이터를 전달 가능
- **ViewName 직접 반환**: 메소드에서 뷰의 이름을 직접 반환할 수 있음. 이는 `ModelAndView` 객체를 생성하고 설정하는 대신, 뷰 이름을 문자열로 반환하여 더 간단하게 뷰를 지정할 수 있게 하는 것임
- **@RequestParam 사용**: HTTP 요청 파라미터를 메소드의 파라미터로 직접 매핑할 수 있음. 이를 통해 `request.getParameter`를 사용하지 않고도 요청 파라미터를 메소드에서 직접 사용할 수 있음.
- **@GetMapping, @PostMapping**: `@RequestMapping`에 비해 더 명시적으로 HTTP 메소드를 지정할 수 있음. 가독성 유지보수성 굿
