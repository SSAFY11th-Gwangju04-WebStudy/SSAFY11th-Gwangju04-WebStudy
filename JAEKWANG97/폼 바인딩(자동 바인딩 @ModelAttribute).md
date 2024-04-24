### JSP에서 컨트롤러로 폼 데이터 전송 및 자동 바인딩 과정

1. **폼 구성 및 데이터 전송**: JSP 페이지에서 사용자에게 입력 받은 데이터는 `<form>` 태그를 사용하여 서버로 전송됩니다. 폼의 각 입력 필드의 `name` 속성은 `User` 클래스의 멤버 변수 이름과 일치해야 합니다. 이 일치성은 데이터가 서버에 전송될 때 `User` 객체의 해당 필드로 자동으로 매핑되는 기반을 제공합니다.

    ```jsp
    <form action="${root}/login" method="post">
        <div class="form-group mb-3">
            <label for="id">사용자 이름</label>
            <input type="text" class="form-control" id="id" name="id" required>
        </div>
        <div class="form-group mb-3">
            <label for="pass">비밀번호</label>
            <input type="password" class="form-control" id="pass" name="pass" required>
        </div>
        <div class="text-center">
            <button type="submit" class="btn btn-primary">로그인</button>
        </div>
    </form>
    ```

2. **컨트롤러에서의 자동 바인딩**: 스프링 MVC 컨트롤러는 `@ModelAttribute` 어노테이션을 사용하여 폼 데이터를 자동으로 `User` 객체에 바인딩합니다. 이 과정은 HTTP 요청으로부터 폼 데이터를 읽고, `User` 객체의 필드에 적절히 매핑하여 객체를 초기화합니다.

    ```java
    @PostMapping("/login")
    public String login(@ModelAttribute User user, HttpSession session, Model model) {
        System.out.println(user.getId());
        user = userService.login(user, session);
        
        if (user != null) {
            return "redirect:/";
        }
        model.addAttribute("fail", "로그인 실패: 아이디 또는 비밀번호를 확인해 주세요.");
        return "redirect:/index";  // URL을 깔끔하게 유지하기 위해 리다이렉트 사용
    }
    ```

3. **유저 클래스의 구조**: `User` 클래스는 `id`, `name`, `pass`라는 필드를 포함하고 있으며, 이 필드들은 JSP 폼의 `name` 속성과 동일해야 합니다. 이 구조는 폼 데이터의 서버측 객체로의 자동 바인딩을 가능하게 합니다.

    ```java
    public class User {
        private String id;
        private String name;
        private String pass;
        // getters and setters
    }
    ```

### 결론
`@ModelAttribute`를 사용하는 자동 바인딩 메커니즘은 폼 데이터를 서버측 모델 객체로 효율적이고 정확하게 전송할 수 있는 강력한 스프링 MVC의 기능입니다. 이 기능을 사용함으로써 개발자는 폼 데이터 처리를 위한 코드의 양을 크게 줄이고, 에러 발생 가능성을 최소화할 수 있습니다.
