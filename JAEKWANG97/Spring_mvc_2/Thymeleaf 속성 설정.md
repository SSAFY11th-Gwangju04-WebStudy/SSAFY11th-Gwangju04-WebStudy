타임리프(Thymeleaf)에서 속성 설정은 HTML 태그의 속성을 동적으로 조작하고, 데이터 모델의 값에 따라 HTML 문서의 속성을 변경하는 데 사용됩니다. 타임리프는 다양한 속성 설정 관련 기능을 제공하여, 웹 페이지를 더욱 동적이고 반응적으로 만들 수 있게 합니다.

### 기본 속성 설정

타임리프에서는 `th:attr`를 사용해 HTML 요소의 속성을 설정할 수 있습니다. 이를 통해 태그의 속성 값을 표현식의 결과로 지정할 수 있습니다.

#### 예시: `th:attr`

```html
<img th:src="@{/images/logo.png}" th:alt="Logo" />
```

### 속성 추가

- `th:src`: 이미지 소스 URL을 동적으로 설정합니다.
- `th:href`: 링크의 `href` 속성을 동적으로 설정합니다.
- `th:value`: 입력 필드의 `value` 속성을 동적으로 설정합니다.
- `th:alt`: 이미지의 `alt` 텍스트를 동적으로 설정합니다.

#### 예시: `th:href`

```html
<a th:href="@{/login}">Login</a>
```

### 불리언 속성

불리언 속성은 해당 속성이 존재하면 `true`, 존재하지 않으면 `false`를 의미합니다. 타임리프는 `checked`, `disabled`, `selected` 같은 불리언 속성을 쉽게 조작할 수 있도록 합니다.

#### 예시: `th:checked`

```html
<input type="checkbox" th:checked="${user.active}" />
```

이 예제에서 `user.active`가 `true`이면 체크박스가 선택된 상태로 렌더링됩니다.

### 클래스와 스타일 조작

- `th:class`: CSS 클래스를 동적으로 추가합니다.
- `th:classappend`: 기존 클래스에 추가적으로 클래스를 더합니다.
- `th:style`: 인라인 스타일을 동적으로 설정합니다.

#### 예시: `th:class`와 `th:classappend`

```html
<div th:class="${condition ? 'class-true' : 'class-false'}">Some Content</div>
<div th:classappend="${additionalClass}">More Content</div>
```

### 데이터 속성

HTML5 데이터 속성(`data-*`)도 타임리프를 통해 동적으로 설정할 수 있습니다.

#### 예시: `data-*` 속성

```html
<div th:data-user-id="${user.id}" th:data-user-role="${user.role}">User Information</div>
```

타임리프의 속성 설정 기능을 활용하면, 서버 사이드에서 생성된 데이터를 바탕으로 클라이언트 사이드에서 사용될 HTML 요소의 속성을 효율적으로 조절할 수 있습니다. 이는 웹 애플리케이션 개발 시 유연성을 제공하며, 사용자 경험을 향상시키는 데 크게 기여합니다.

# 속성 추가


타임리프(Thymeleaf)에서 HTML 요소에 속성을 추가하는 방법은 몇 가지가 있으며, 사용 상황에 따라 적합한 방법을 선택할 수 있습니다. 주로 `th:attr`을 사용하며, 특정 속성을 직접 지정하는 방법도 있습니다. 여기서는 속성을 추가하는 기본적인 방법들을 살펴보겠습니다.

### `th:attr` 사용하기

`th:attr`를 사용하면 한 번에 여러 속성을 추가하거나 변경할 수 있습니다. 이는 동적으로 속성 값을 설정할 때 유용합니다.

#### 예시:

```html
<a th:attr="href=@{/home}, title=${homeTitle}, data-toggle=${toggle}">Home</a>
```

이 예시에서는 `href`, `title`, 그리고 `data-toggle` 속성을 동시에 설정합니다. `homeTitle`과 `toggle`은 모델에서 제공된 변수입니다.

### 직접적인 속성 추가

타임리프는 HTML 요소의 표준 속성을 직접적으로 조작할 수 있는 많은 속성을 제공합니다. 이 방법은 코드를 더 읽기 쉽고 간결하게 만들어 줍니다.

#### 예시:

- **`th:href`**: 링크의 `href` 속성을 설정합니다.

    ```html
    <a th:href="@{/contact}">Contact</a>
    ```

- **`th:src`**: 이미지의 `src` 속성을 설정합니다.

    ```html
    <img th:src="@{/images/logo.png}" alt="Logo">
    ```

- **`th:value`**: 입력 요소의 `value` 속성을 설정합니다.

    ```html
    <input type="text" th:value="${username}" />
    ```

- **`th:alt`**, **`th:title`**, 등: 각각 `alt`와 `title` 속성을 설정합니다.

### 불리언 속성 추가

`th:checked`, `th:selected`, `th:disabled` 같은 특수 속성을 사용하여, 불리언 값에 따라 체크박스, 라디오 버튼, 셀렉트 옵션 등이 선택되거나 비활성화 되도록 할 수 있습니다.

#### 예시:

```html
<input type="checkbox" th:checked="${user.active}" />
```

### 클래스와 스타일 동적 추가

`th:classappend`, `th:styleappend`를 사용하면 기존 클래스나 스타일에 추가적인 값들을 동적으로 더할 수 있습니다.

#### 예시:

```html
<div th:classappend="${isAdmin ? 'admin' : ''}">Admin Content</div>
```

타임리프를 사용하여 속성을 추가하는 방법은 HTML 요소를 동적으로 조작할 때 큰 유연성을 제공합니다. 이를 통해 서버 사이드 데이터에 기반한 사용자 인터페이스의 조건부 렌더링이 가능해지며, 사용자 경험을 향상시킬 수 있습니다.