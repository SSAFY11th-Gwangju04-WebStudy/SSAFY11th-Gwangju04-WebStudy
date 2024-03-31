

웹 애플리케이션 개발 시, 폼 제출 같은 POST 요청 처리 후 사용자의 브라우저에 새로운 페이지를 로드하도록 하는 것이 일반적인 패턴 중 하나입니다. 이 과정에서 "POST/Redirect/GET" 패턴을 사용하여, 폼 제출(POST) 후에는 다른 페이지로 리다이렉션(GET)을 하게 만드는 것이 좋습니다. 이렇게 하면 사용자가 브라우저의 새로고침 버튼을 눌렀을 때 폼이 중복으로 제출되는 것을 방지할 수 있습니다.

#### 리다이렉션 수행 방법

스프링 MVC에서 컨트롤러 메서드는 문자열을 반환함으로써 뷰 이름을 지정합니다. 이 문자열 앞에 `redirect:`를 붙이면, 스프링 MVC는 HTTP 리다이렉션을 수행해야 함을 인식하고, 지정된 URL로 사용자를 리다이렉션합니다.

```java
@PostMapping("/posts")
public String handlePostSubmission(Post post) {
    // post 처리 로직 (저장 등)
    return "redirect:/posts/list"; // 사용자를 게시글 목록 페이지로 리다이렉션
}
```

#### `RedirectAttributes`를 사용한 메시지 전달

`RedirectAttributes` 객체는 리다이렉션 시 URL에 파라미터를 추가하는 방식이 아닌, 세션을 통해 데이터를 전달할 수 있게 해 줍니다. 이를 사용하면 리다이렉션 후의 페이지에서 사용자에게 성공 메시지나 에러 메시지를 보여줄 수 있습니다.

```java
@PostMapping("/posts")
public String handlePostSubmission(Post post, RedirectAttributes redirectAttributes) {
    // post 처리 로직
    redirectAttributes.addFlashAttribute("message", "게시글이 성공적으로 등록되었습니다.");
    return "redirect:/posts/list";
}
```

- `addFlashAttribute` 메서드를 사용하면, 리다이렉션된 페이지에서 한 번 사용할 수 있는 속성을 추가할 수 있습니다. 이 메서드로 추가된 속성은 URL에는 나타나지 않으며, 리다이렉션 후 첫 번째 요청에서만 사용할 수 있습니다.

#### 리다이렉션된 페이지에서 메시지 사용하기

리다이렉션된 페이지(예: 게시글 목록 페이지)에서는 `addFlashAttribute`를 통해 추가된 속성을 사용할 수 있습니다. Thymeleaf 템플릿에서는 다음과 같이 사용할 수 있습니다:

```html
<!-- posts/list.html -->
<div th:if="${message}">
    <p th:text="${message}"></p>
</div>
```

이 방식을 사용하면, 폼 제출 결과와 같은 중요한 사용자 피드백을 안전하게 전달하면서도, URL을 깔끔하게 유지할 수 있습니다. 사용자 경험을 향상시키는 데 도움이 됩니다.
