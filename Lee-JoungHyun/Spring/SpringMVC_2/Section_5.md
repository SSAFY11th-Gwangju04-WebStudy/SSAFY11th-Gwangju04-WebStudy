# 검증2 - Bean Validation


## Bean Validation 소개

검증은 대부분 일반적인 상황! (x ~ y, 빈 값이 아님, 최대 9999)
이를 어노테이션 하나도 처리하기 위한 방법!!!

**BeanValidation** 
특정한 구현체가 아니라 기술 표준! 구현체는 하이버네이트 Validator (ORM 관계 X)

## Bean Validation 시작

Item 객체의 set 메서드에 어노테이션 달아서 제한하기!
```java
@Data  
public class Item {  
  
    private Long id;  
  
    @NotBlank(message= "공백X")
    private String itemName;  
  
    @NotNull  
    @Range(min = 1000, max = 1000000) // hivernate 에만 존재  
    private Integer price;  
  
    @NotNull  
    @Max(9999)  
    private Integer quantity;  
  
    public Item() {  
    }  
}
```

이러면 @Validator 어노테이션을 달아주면 FieldError, ObjectError 생성해서 BindResult에 담아줌 (이때 전에 했던 글로벌 validator 지워야함!)
```java
public String addItem(@Validated @ModelAttribute Item item, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) { ~ }
```

@Validated vs @Valid
- @Validated 는 스프링 검증 전용 애노테이션, +groups 기능 포함
- @Valid 는 javax (자바표준) + 사용하려면 build.gradle에 의존관계 추가 필요함

### 검증 순서!

1. @ModelAttribute 어노테이션으로 각각에 필드에 타입 변환 시도!
	1. 성공하면 다음
	2. 실패하면 typeMisMatch 로 FieldError 추가
2. Validator 적용
**즉 바인딩에 성공한 필드만 Bean Validation 적용됨


## Bean Validation - 에러 코드

오류코드가 `bindingResult` 에 애노테이션 기반으로 등록됨

**@NotBlank** 
- NotBlank.item.itemName 
- NotBlank.itemName 
- NotBlank.java.lang.String 
- NotBlank

**@Range** 
- Range.item.price
- Range.price 
- Range.java.lang.Integer 
- Range

이를 errors.properties 에서 순서대로 찾아 사용, 
못찾을 경우 massage속성의 값 넣기
없을 경우 기본 값이 나감


## 오브젝트 오류

**Field 에러가 아닌 ObjectError 처리법
`@ScriptAssert(lang = "javascript", 
	`script = "_this.price * _this.quantity >= 10000")`

실무에서는 이러한 검증이 상당히 복잡하다...

그런 경우 그냥 java code 로 사용하자...


## Bean Validation 한계

**수정시 요구사항 변경**
- 등록시에는 `quantity` 수량을 최대 9999까지 등록할 수 있지만 **수정시에는 수량을 무제한으로 변경**할 수 있다.  
- 등록시에는 `id` 에 값이 없어도 되지만, **수정시에는 id 값이 필수**이다.

아직 이를 따로 해결할 방법은 없다... (등록, 수정 둘다 공통 BeanValidation 으로 들어가서)

## Bean Validation - groups

**동일한 모델 객체 등록, 수정 시 다른 BeanValidation 적용법
1. BeanValidation 의 groups 기능 사용!
2. Item을 직접 사용하지 않고 폼 전송을 위한 별도 모델 객체 사용 (ItemSaveForm, ItemUpdateForm)

**BeanValidation group 기능 사용
1. 그룹별 Interface 생성 (SaveCheck, UpdateCheck)
2. Item의 어노테이션에 groups 추가 `@NotNull(groups = {SaveCheck.class, UpdateCheck.class})`
3. 검증할 @Validated 어노테이션에 value 추가 `@Validated(SaveCheck.class)`
	- 이때 @Valid 에는 value 가 없다 `@Validated(value = SaveCheck.class)`
groups를 사용해서 다른 검증을 할수 있게 되었지만 코드가 매우 복잡해짐
실무에서는 주로 등록폼 객체와 수정폼 객체를 분리해서 사용한다!!

