# 4. 검증1 - Validation


## 검증 요구사항


**요구사항: 검증 로직 추가** 

- 타입 검증
	- 가격, 수량에 문자가 들어가면 검증 오류 처리 
- 필드 검증
	- 상품명: 필수, 공백X  
	- 가격: 1000원 이상, 1백만원 이하 
	- 수량: 최대 9999
- 특정 필드의 범위를 넘어서는 검증  
	- 가격 * 수량의 합은 10,000원 이상

-> 현재 오류 발생시는 오류화면으로 바로 이동해버림...
-> 오류 발생시 고객의 입력을 유지하며 오류 발생을 자세히 알려주어야 함!

** 컨트롤러의 중요한 역활중 하나가 HTTP 요청이 정상인지 검증하는 것 **

**참고: 클라이언트 검증, 서버 검증**  
- 클라이언트 검증(JS)은 조작할 수 있으므로 보안에 취약하다.  
- 서버만으로 검증하면, 즉각적인 고객 사용성이 부족해진다.  
- 둘을 적절히 섞어서 사용하되, 최종적으로 서버 검증은 필수  
- API 방식을 사용하면 API 스펙을 잘 정의해서 검증 오류를 API 응답 결과에 잘 남겨주어야 함


## 검증 직접 처리


![[스크린샷 2024-04-09 오전 1.20.59.png]]

- 실패 시 결과 창? 에 검증된 오류 결과 + 입력한 정보를 전달해야 함!(Model에 담아)


## 검증 직접 처리 - 개발


- 검증에서 오류 메세지가 하나라도 있으면 오류 메시지를 출력하기 위해 `model` 에 `errors` 를 담고, 입력 폼이 있는 뷰 템플릿(현재) 로 다시 보낸다!

```java
@PostMapping("/add")  
public String addItem(@ModelAttribute Item item, RedirectAttributes redirectAttributes, Model model) {  
  
    // 검증 오류 결과를 보관  
    Map<String, String> errors = new HashMap<>();  
  
    // 검증 로직  
    if (!StringUtils.hasText(item.getItemName())) {  
        errors.put("itemName", "상품 이름은 필수입니다.");  
    }  
    if (item.getPrice() == null || item.getPrice() < 1000 || item.getPrice() > 1000000) {  
        errors.put("price", "가격은 1,000 ~ 1,000,000 까지 허용");  
    }  
    if (item.getQuantity() == null || item.getQuantity() > 9999) {  
        errors.put("quantity", "수량은 최대 9,999 까지 하용합니다");  
    }  
  
    // 특정 필드 검증이 아닌 복합 룰 검증  
    if (item.getPrice() != null && item.getQuantity() != null) {  
        int result = item.getPrice() * item.getQuantity();  
        if (result < 10000) {  
            errors.put("globalError", "가격 * 수량은 10,000원 이상이어야 합니다, 현재 값: " + result);  
        }  
    }  
  
    // 검증에 실패하면 다시 입력 폼으로  
    if (!errors.isEmpty()) {  
        log.info("errors = {}", errors);  
        model.addAttribute(errors);  
        return "validation/v1/addForm";  
    }  
  
  
    Item savedItem = itemRepository.save(item);  
    redirectAttributes.addAttribute("itemId", savedItem.getId());  
    redirectAttributes.addAttribute("status", true);  
    return "redirect:/validation/v1/items/{itemId}";  
}
```

- global 에러 처리
```Html
<div th:if="${errors?.containsKey('globalError')}">  
    <p class="field-error" th:text="${errors['globalError']}">전체 오류 메시지</p>  
</div>
```

- xxx?. 은 xxx 가 Null 일때 NullPointerExcepiton 이 아닌 null 반환 문법 (el)

- 처리된 것
	- 오류 확인 가능
	- 입력 데이터 유지
- 문제점
	- 중복 처리가 많음
	- 타입 처리가 아직 안되있음 (400 예외) Item 객체를 못받음
	- 결국 고객이 입력한 정보를 별도로 저장해야 함...


## BindingResult1


- BindingResult 위치는 @ModelAttribute 바로 뒤!
- 필드오류
```Java
if (!StringUtils.hasText(item.getItemName())) {  
    bindingResult.addError(new FieldError("item", "itemName", "상품 이름은 필수입니다."));  
}
```
- 글로벌 오류
```java
if (result < 10000) {  
    bindingResult.addError(new ObjectError("item", "가격 * 수량은 10,000원 이상이어야 합니다, 현재 값: " + result));  
}
```

- Thymeleaf 에서 받기
```HTML
<div th:if="${#fields.hasGlobalErrors()}">  
    <p class="field-error" th:each="err: ${#fields.globalErrors()}" th:text="${err}">전체 오류 메시지</p>  
</div>

<div>  
    <label for="itemName" th:text="#{label.item.itemName}">상품명</label>  
    <input type="text" id="itemName" th:field="*{itemName}"  
           th:errorclass="field-error" class="form-control" placeholder="이름을 입력하세요">  
           
</div>  
<div class="field-error" th:errors="*{itemName}">  
    상품명 오류  
</div>

```


## BindingResult2


- **예) @ModelAttribute에 바인딩 시 타입 오류가 발생하면?**
	- `BindingResult` 가 없으면 400 오류가 발생하면서 컨트롤러가 호출되지 않고, 오류 페이지로 이동한다. 
	- `BindingResult` 가 있으면 오류 정보( `FieldError` )를 `BindingResult` 에 담아서 컨트롤러를 정상 호출한다.

따라서 직접 처리하지 않으면 이상한 오류정보가 출력된다!

- BindingResult 작동방법 3가지
	1. `@ModelAttribute` 의 객체에 타입 오류 등으로 바인딩이 실패하는 경우 스프링이 `FieldError` 생성해서 `BindingResult` 에 넣어준다.
	2. 개발자가 직접 넣어준다.  
	3. `Validator` 사용 이것은 뒤에서 설명


## FieldError, ObjectError


- FieldError 의 2가지 생성자
	- `public FieldError(String objectName, String field, String defaultMessage);`
	 - `public FieldError(String objectName, String field, @Nullable Object rejectedValue, boolean bindingFailure, @Nullable String[] codes, @Nullable Object[] arguments, @Nullable String defaultMessage)`
		 - 파라미터 목록  
			- `objectName` : 오류가 발생한 객체 이름  
			- `field` : 오류 필드  
			- `rejectedValue` : 사용자가 입력한 값(거절된 값)  
			- `bindingFailure` : 타입 오류 같은 바인딩 실패인지, 검증 실패인지 구분 값 `codes` : 메시지 코드  
			- `arguments` : 메시지에서 사용하는 인자  
			- `defaultMessage` : 기본 오류 메시지

+ Tymeleaf 에서는 그냥 `th:field` 가 처리해준다!


## 오류 코드와 메세지 처리 1

앞 2번째 생성자의 codes, arguments 를 

-> errors.properties 로 처리
전에 messages 를 스프링이 인식했던 이유가`spring.messages.basename=messages`  디폴트이기 때문, 따라서 errors.properties 를 사용하려면 이것도 여기에 등록시킨다
`spring.messages.basename=messages, errors`

```java
bindingResult.addError(new FieldError("item", "price", item.getPrice(), false, new String[]{"range.item.price"}, new Object[]{1_000, 1_00_000}, null));

bindingResult.addError(new ObjectError("item", new String[]{"totalPriceMin"}, new Object[]{10000, result}, null));
```

처음엔 등록된 properties 에서 String[] 배열의 값을 순서대로 찾음,
- 찾으면 다음 Object 배열에서 값 넣기
- 없으면 default value 처리 


## 오류 코드와 메세지 처리2

BindingResult 는 검증해야 할 객체인 target 바로 다음에 오므로 이미 알고있음
```java
bindingResult.rejectValue("price", "range", new Object[]{1_000, 1_000_000}, null);

bindingResult.reject("totalPriceMin", new Object[]{1000, result}, null);
```

이렇게 줄일 수 있음 + 규칙이 있음
rejectValue 는 결국 Field Error 를 생성해서 넣어주긴 함
```java
 void rejectValue(@Nullable String field, String errorCode,
         @Nullable Object[] errorArgs, @Nullable String defaultMessage);
```

*여기서 errorCode 는 messageResolver 를 위한 오류 코드*
errorArgs 는 치환 값


## 오류코드와 메세지 처리 3

- 오류코드 만드는 법
	1. 단순하게 - 범용성이 있음
	2. 세밀하게 
좋은 방법은 범용성으로 사용하다 세밀하게 작성해야 하는 경우 단계를 두는 것!

객체명 + 필드명 을 조합한 메시지가 있는지 먼저 확인한 후 없으면 좀더 범용적인 메시지 선택하게 함 `MessageCodeResolver` 가


## 오류 코드와 메시지 처리 4

```Java
String[] messageCodes = codesResolver.resolveMessageCodes("required", "item");  
for (String messageCode : messageCodes) {  
    System.out.println("messageCode = " + messageCode);  
}
```

```
messageCode = required.item // 우선순위 더 높음!
messageCode = required
// = new String[]{"required.item", "required"} 와 같은 역할
```

- 객체 오류
```
객체 오류의 경우 다음 순서로 2가지 생성 

1.: code + "." + object name 
2.: code

예) 오류 코드: required, object name: item

1.: required.item
2.: required

```

- 필드 오류
```
필드 오류의 경우 다음 순서로 4가지 메시지 코드 생성 

1.: code + "." + object name + "." + field 
2.: code + "." + field  
3.: code + "." + field type  
4.: code

예) 오류 코드: typeMismatch, object name "user", field "age", field type: int 

1. "typeMismatch.user.age"  
2. "typeMismatch.age"  
3. "typeMismatch.int"
```


## 오류 코드와 메시지 처리 5


### 오류 처리 관리 전략!

**핵심은 구체적인것에서! 덜 구체적인 것으로!!**

**정리**  
1. `rejectValue()` 호출
2. `MessageCodesResolver` 를 사용해서 검증 오류 코드로 메시지 코드들을 생성
3. `new FieldError()` 를 생성하면서 메시지 코드들을 보관
4. `th:erros` 에서 메시지 코드들로 메시지를 순서대로 메시지에서 찾고, 노출

## 오류 코드와 메세지 처리 6

**스프링이 직접 만든 오류 메시지 처리**

- 오류는 2가지
	1. 개발자가 직접 설정한 오류 코드 `rejectValue()` 를 직접 호출 
	2. 스프링이 직접 검증 오류에 추가한 경우(주로 타입 정보가 맞지 않음)


## Validator 분리 1

복잡한 검증 코드를 별도의 class 로 처리!
ItemValidator 를 스프링 빈으로 주입받아 직접 호출함


## Validator 분리 2

```Java
@InitBinder  
public void init(WebDataBinder dataBinder) {  
    dataBinder.addValidators(itemValidator);  
}
// + (@Validated) 어노테이션 추가로 사용!

public String addItemV6(@Validated @ModelAttribute Item item, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) { ~ }
```

@Valiated 적용시 해당 객체에 대해 검증기가 작동하며 BindingResult에 등록
검증기가 여러개일 경우는 supports(Item.class) 가 호출되어 매핑

여러 컨트롤러에 매핑시키려면(글로벌 설정) Main 에 설정함 ㅇㅇ 근데 잘 안쓰임
