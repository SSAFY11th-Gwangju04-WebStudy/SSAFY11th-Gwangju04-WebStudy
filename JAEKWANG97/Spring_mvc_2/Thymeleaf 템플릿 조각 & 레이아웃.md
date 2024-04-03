타임리프(Thymeleaf)는 템플릿 조각과 레이아웃을 통해 코드 중복을 줄이고, 템플릿의 재사용성을 높이는 기능을 제공합니다. 이를 통해 웹 애플리케이션의 다양한 페이지에서 공통적으로 사용되는 헤더, 푸터, 네비게이션 바 등을 쉽게 관리할 수 있습니다.

### 템플릿 조각 (Fragment)

템플릿 조각은 재사용 가능한 템플릿의 일부를 정의하고, 다른 템플릿에서 이를 포함시키는 방법입니다. 예를 들어, 웹 사이트의 헤더를 조각으로 만들 수 있습니다.

**조각 정의 예시:**

`header.html`

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
  <head>
    <!-- Head 태그 내용 -->
  </head>
  <body>
    <div th:fragment="header">
      <!-- 헤더 내용 -->
      <h1>Welcome to My Website</h1>
    </div>
  </body>
</html>
```

**조각 사용 예시:**

`index.html`

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
  <head>
    <!-- Head 태그 내용 -->
  </head>
  <body>
    <div th:replace="header.html :: header"></div>
    <!-- 페이지 본문 -->
  </body>
</html>
```

### 템플릿 레이아웃

타임리프와 함께 `thymeleaf-layout-dialect` 라이브러리를 사용하면, 더 진보된 레이아웃 관리를 할 수 있습니다. 이를 통해 전체 HTML 페이지를 베이스 레이아웃으로 사용하고, 해당 레이아웃을 상속받아 다른 페이지를 만들 수 있습니다.

**베이스 레이아웃 정의 예시:**

`layout.html`

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
  <head>
    <title>Layout Page</title>
    <!-- 공통 스타일시트 -->
  </head>
  <body>
    <header th:fragment="layout-header">
      <!-- 공통 헤더 -->
    </header>
    <nav th:fragment="layout-nav">
      <!-- 공통 네비게이션 바 -->
    </nav>
    <div th:fragment="layout-content">
      <!-- 콘텐츠가 들어갈 부분 -->
    </div>
    <footer th:fragment="layout-footer">
      <!-- 공통 푸터 -->
    </footer>
  </body>
</html>
```

**베이스 레이아웃 사용 예시:**

`about.html`

```html
<!DOCTYPE html>
<html
  layout:decorate="~{layout.html}"
  xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout"
  xmlns:th="http://www.thymeleaf.org"
>
  <head>
    <title>About Page</title>
  </head>
  <body>
    <div layout:fragment="layout-content">
      <!-- About 페이지 콘텐츠 -->
      <p>This is the about page.</p>
    </div>
  </body>
</html>
```

이 방법을 사용하면, `layout.html`을 베이스로 하여 각 페이지의 고유 콘텐츠를 `layout-content` 부분에 삽입할 수 있습니다. 이렇게 하면 헤더, 푸터, 네비게이션 바 등을 각각의 페이지마다 반복해서 작성할 필요 없이, 공통 레이아웃을 유지하면서 각 페이지의 특정 내용만 변경할 수 있습니다.

`thymeleaf-layout-dialect`를 사용하기 위해서는 해당 라이브러리를 프로젝트의 의존성에 추가해야 합니다. Maven이나 Gradle을 사용하는 경우, `pom.xml`이나 `build.gradle` 파일에 라이브러리를 추가하여 사용할 수 있습니다.
