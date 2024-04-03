
타임리프(Thymeleaf)에서 조건식은 템플릿 내에서 조건부 로직을 구현하는 데 사용됩니다. 조건식을 사용하면 특정 조건에 따라 다른 콘텐츠를 표시하거나, HTML 요소의 속성을 동적으로 변경할 수 있습니다. 조건식의 핵심은 `th:if`, `th:unless`, 그리고 `th:switch`/`th:case` 지시어를 활용하는 것입니다.

### `th:if`와 `th:unless`

- **`th:if`**: 지정된 조건이 참(True)일 때, 요소를 표시합니다.
- **`th:unless`**: 지정된 조건이 거짓(False)일 때, 요소를 표시합니다.

#### 예시:

```html
<div th:if="${condition}">
    이 부분은 condition이 참일 때만 표시됩니다.
</div>
<div th:unless="${condition}">
    이 부분은 condition이 거짓일 때만 표시됩니다.
</div>
```

### `th:switch`와 `th:case`

`th:switch`와 `th:case`를 사용하여 다중 조건을 처리할 수 있습니다. 이는 다른 프로그래밍 언어의 `switch` 문과 유사합니다.

#### 예시:

```html
<div th:switch="${user.role}">
    <p th:case="'admin'">사용자는 관리자입니다.</p>
    <p th:case="'user'">사용자는 일반 사용자입니다.</p>
    <p th:case="*">알 수 없는 사용자 역할입니다.</p>
</div>
```

### 삼항 조건 연산자

삼항 조건 연산자를 사용하여 간결한 조건부 표현을 만들 수 있습니다. 이는 `(condition) ? (value if true) : (value if false)` 형식을 따릅니다.

#### 예시:

```html
<p th:text="${condition ? '조건이 참입니다.' : '조건이 거짓입니다.'}">
    조건에 따른 텍스트가 여기에 표시됩니다.
</p>
```

타임리프의 조건식을 사용함으로써, 템플릿 기반의 웹 어플리케이션 개발에서 더 동적이고 반응적인 사용자 인터페이스를 구현할 수 있습니다. 조건에 따라 보여줄 콘텐츠를 세밀하게 제어할 수 있어, 사용자 경험을 풍부하게 만드는 데 기여합니다.