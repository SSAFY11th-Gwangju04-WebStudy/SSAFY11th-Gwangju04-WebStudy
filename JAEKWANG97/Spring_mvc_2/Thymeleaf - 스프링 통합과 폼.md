[[../Spring MVC 2/Thymeleaf|Thymeleaf]]

th:object : 커맨드 객체를 저장함
`*{...}` : 선택 변수 식 이라고 함 th: object 에서 선택한 객체에 접근을 합니다.
`th:field` html 태그의 id,name, value 속성을 자동으로 처리해줍니다.

`th:object` 와 `th:field`는 Thymeleaf 템플릿 엔진에서 폼을 처리할 때 사용되는 속성입니다. 이들은 폼 데이터를 객체에 바인딩하고, 폼 필드를 객체의 속성과 연결하는 데 사용됩니다. 이를 통해 서버와 클라이언트 간의 데이터 교환을 용이하게 하며, 폼 데이터의 제출 및 검증 과정을 간소화합니다.

### th:object
`<form>` 태그에 사용되며, 이 속성을 통해 폼 데이터가 바인딩될 객체를 지정합니다. 이 객체는 보통 컨트롤러에서 모델에 추가된 객체이며, 폼의 데이터는 이 객체의 속성과 매핑됩니다.

예를 들어, `Person` 객체를 폼 데이터와 바인딩하려면 다음과 같이 사용할 수 있습니다:

```html
<form action="#" th:action="@{/submitPerson}" th:object="${person}" method="post">
    ...
</form>
```

여기서 `th:object="${person}"`은 폼 데이터를 `person` 객체에 바인딩하겠다는 것을 의미합니다.

### th:field
`th:field`는 `<input>`, `<select>`, `<textarea>` 등의 폼 요소에 사용되며, 해당 요소를 `th:object`에 지정된 객체의 특정 속성과 연결합니다. 이를 통해 폼 요소의 값이 해당 객체의 속성 값과 자동으로 매핑되며, 폼 제출 시 객체의 속성으로 데이터가 전달됩니다.

예를 들어, `Person` 객체에 `name` 속성이 있고, 이를 입력받기 위한 폼 필드를 만들고 싶다면 다음과 같이 사용할 수 있습니다:

```html
<input type="text" th:field="*{name}" />
```

여기서 `th:field="*{name}"`은 이 `<input>` 요소의 값이 `th:object`로 지정된 객체의 `name` 속성과 바인딩되어야 함을 의미합니다. `*{...}`는 선택 변수 식의 한 형태로, 현재 선택된 객체(`th:object`에 의해 지정된 객체)에 대한 속성이나 메소드에 접근할 때 사용됩니다.

`th:object`와 `th:field`를 사용함으로써, 폼 데이터의 처리를 보다 선언적이고 안전하게 만들 수 있으며, 폼 데이터와 도메인 모델 간의 바인딩을 간편하게 할 수 있습니다.

# 체크박스

단일 체크박스에서 open이라는 속성을 사용하였는데 사용자가 만약 체크를 안한 채 post 요청을 보내게 된다면, 서버는 open = null 이라는 값을 갖게 됩니다. 때문에 간단하 트릭을 활요합니다.

```html
<div>판매 여부</div>
<div>
 <div class="form-check">
 <input type="checkbox" id="open" name="open" class="form-check-input">
 <input type="hidden" name="_open" value="on"/> <!-- 히든 필드 추가 -->
 <label for="open" class="form-check-label">판매 오픈</label>
 </div>
</div>
```

이처럼 히든 필드를 추가한다면, 서버는 사용자가 별도의 체크를 안하였을 때 opne = false를 받을 수 있습니다. 

하지만 사용자가 체크를 할 경우 open값을 사용하고 히든 필드는 무시합니다.


# @ModelAttribute
```java
@ModelAttribute("regions")
public Map<String, String> regions() {
    Map<String, String> regions = new HashMap<>();
    regions.put("SEOUL", "서울");
    regions.put("BUSAN", "부산");
    regions.put("JEJU", "제주");
    return regions;
}
```

해당 어노테이션을 활용하면, Model 객체에 default값으로 넣어줄 수 있습니다. 이렇게 하는 까닭은 만약 해항 setting 을 메서드 안에서 하게 된다면 memory 적으로 비효율 이 발생하기 떄문입니다. 최적화를 생각한다면 static 에 선언하는 방법도 있습니다.



# 멀티 체크박스

```java
<div>
 <div>등록 지역</div>
 <div th:each="region : ${regions}" class="form-check form-check-inline">
 <input type="checkbox" th:field="*{regions}" th:value="${region.key}"
class="form-check-input">
 <label th:for="${#ids.prev('regions')}"
 th:text="${region.value}" class="form-check-label">서울</label>
 </div>
</div>
```

강의에서는 label의 th:for 값으로 # 접근자를 활용화여 ids.prev() 라는 함수를 활용합니다. 왜인지는 모르겠으나, 저의 컴퓨터에서는 실행이 되지 않았고 다른 방법으로 구현이  필요했습니다. 

```java
        <div th:each="region : ${regions}" class="form-check form-check-inline">
          <input
            type="checkbox"
            th:id="${'region-' + region.key}" <!-- ID 수정 -->
            th:field="*{regions}"
            th:value="${region.key}"
            class="form-check-input"
          />
          <label
            th:for="${'region-' + region.key}" <!-- for 속성 수정 -->
            th:text="${region.value}"
            class="form-check-label"
            >서울</label
          >
      </div>
```

떄문에 위와 같은 코드를 짜게 되었습니다. `'region'`을 한 까닥은 단일값에 유니크한 값을 주기위함입니다.

# Thymeleaf 자바 빈 규약을 따름

``` java
          <div
            th:each="type : ${itemTypes}"
            class="form-check form-check-inline"
          >
            <input
              type="radio"
              th:field="*{itemType}"
              th:value="${type.name()}"
              class="form-check-input"
            />
            <label
              th:for="${type.name()}"
              th:text="${type.description}"
              class="form-check-label"
            >
              BOOK
            </label>
```

해당 코드에서 type에서 description으로 접근하는 것을 볼 수 있습니다. 하지만 

```java
package hello.itemservice.domain.item;

public enum ItemType {
    BOOK("도서"), FOOD("음식"), ETC("기타");

    private final String description;

    ItemType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
```

private 로 선언 했기 떄문에 불가능하다고 생각했으나, 타임리프는 자바 빈 규약을 따르기 떄문에 위와같이 접근을 하였을 때는 getter를 통해서 접근한 다는 것을 알았습니다.