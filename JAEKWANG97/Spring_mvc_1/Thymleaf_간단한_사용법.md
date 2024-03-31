Thymeleaf를 사용한 스프링 MVC 웹 애플리케이션 개발에서 자주 사용되는 몇 가지 핵심 기능들에 대해 예시 코드와 함께 설명하겠습니다.

### 1. 변수 표현식 (`${...}`)

컨트롤러에서 모델에 데이터를 추가하고, Thymeleaf 템플릿에서 이 데이터를 표시합니다.

**컨트롤러 예시:**

```java
@Controller
public class MyController {

    @GetMapping("/greeting")
    public String greeting(Model model) {
        model.addAttribute("name", "World");
        return "greeting";
    }
}
```

**Thymeleaf 템플릿 예시 (`greeting.html`):**

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Greeting</title>
</head>
<body>
    <p th:text="'Hello, ' + ${name} + '!'">Hello, World!</p>
</body>
</html>
```

### 2. 조건부 표시 (`th:if`, `th:unless`)

특정 조건에 따라 HTML 요소를 표시하거나 숨깁니다.

**Thymeleaf 템플릿 예시:**

```html
<div th:if="${not #lists.isEmpty(posts)}">
    <p>Posts are available!</p>
</div>
<div th:unless="${not #lists.isEmpty(posts)}">
    <p>No posts available.</p>
</div>
```

### 3. 리스트 반복 (`th:each`)

리스트의 요소를 반복하여 표시합니다.

**Thymeleaf 템플릿 예시:**

```html
<ul>
    <li th:each="post : ${posts}" th:text="${post.title}">Post Title</li>
</ul>
```

### 4. 링크 URL 생성 (`@{...}`)

동적으로 URL을 생성합니다. 스프링 MVC와 통합되어 있어, URL에 파라미터를 추가하거나, 컨텍스트 경로를 자동으로 처리합니다.

**Thymeleaf 템플릿 예시:**

```html
<a th:href="@{/posts/view(id=${post.id})}">View Post</a>
```

### 5. 폼과 `th:field`

폼과 모델 객체를 바인딩하며, 폼을 제출할 때 사용자 입력을 컨트롤러로 전달합니다.

**Thymeleaf 폼 예시 (`postForm.html`):**

```html
<form th:action="@{/posts/save}" th:object="${post}" method="post">
    <input type="text" th:field="*{title}" />
    <textarea th:field="*{content}"></textarea>
    <button type="submit">Submit</button>
</form>
```

이 예시들은 Thymeleaf의 기본적인 사용 방법을 보여줍니다. Thymeleaf는 HTML 템플릿을 동적으로 렌더링하는 강력한 기능을 제공하며, 스프링과의 긴밀한 통합을 통해 개발자가 웹 애플리케이션을 효율적으로 구축할 수 있도록 지원합니다.
