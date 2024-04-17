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


## Form 전송 객체 분리

실무에서는 `groups` 를 잘 사용하지 않는데, 그 이유는 다른곳에 있다!
전달받을떼 Item 그대로 받지 않고 ItemSaveForm 라는 전용 객체에 받아 Item 생성함

**폼 데이터 전달에 Item 도메인 객체 사용**  
`HTML Form -> Item -> Controller -> Item -> Repository`
- 장점: Item 도메인 객체를 컨트롤러, 리포지토리 까지 직접 전달해서 중간에 Item을 만드는 과정이 없어서 간단하다.  
- 단점: 간단한 경우에만 적용할 수 있다. 수정시 검증이 중복될 수 있고, groups를 사용해야 한다.

**폼 데이터 전달을 위한 별도의 객체 사용**  
`HTML Form -> ItemSaveForm -> Controller -> Item 생성 -> Repository`
- 장점: 전송하는 폼 데이터가 복잡해도 거기에 맞춘 별도의 폼 객체를 사용해서 데이터를 전달 받을 수 있다. 보통 등록과, 수정용으로 별도의 폼 객체를 만들기 때문에 검증이 중복되지 않는다.  
- 단점: 폼 데이터를 기반으로 컨트롤러에서 Item 객체를 생성하는 변환 과정이 추가된다.

**수정과 등록의 경우 완전 다른 데이터가 들어옴(필요한 데이터가 다름...)**
예를 들어 주민번호는 수정 못함 ㅇㅇ 
데이터만 가지고는 완전한 Item을 만들기 힘듬(DB에서 추가정보가 필요할 수도) -> 가볍지 않으면 따라서 이를 완전히 분리하기 때문에 groups는 잘 사용하진 않는다!
**어설프게 통합하면 지옥의 유지보수가 될수 있으니 조심해라** 
차라리 그냥 어설프게 해도 분리하는게...

-> 분리해서 새로운 객체로 받는건 다 좋은데 ModelAttribute("원본 클래스명") 을 잊지마라
안그러면 프론트에서 가져오는 객체의 이름이 바뀐다!!


## Bean Validation - Http 메시지 컨버터

`@Valid`, `@Validated` 는 HttpMessageConverter(@RequestBody) 에도 적용 가능!

**검증 결과 3가지**
1. 성공
2. 객체생성은 성공했지만 조건에 안맞음
3. 객체에 넣지도 못함
	- 세팅이 안되는 경우 예외처리는 따로 해야함, 405 에러 발생

**에러 처리 Json 도 다른 스펙에 맞춰 반환하자**

**@ModelAttribute vs @RequestBody**

- ModelAttribute 는 각각 필드단위로 세팅하기 때문에 필드 타입이 맞지 않는 오류가 발생해도 나머지 필드를 정상적으로 처리할 수 있다
	- 기본 생성자 호출로 만들고 setXxx 로 세팅하기 때문
- RequestBody의 경우 메세지 컨버터의 작동이 성공해서 해당 객체를 만들어야 @Valid, @Validated 가 적용되므로 객체 변환 안되면 처리 불가능한 오류가 발생한다!
  	- 해당 오류 처리의 경우는 나중애 배운다네여...
 
