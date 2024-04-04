# 스프링 통합, 폼


## 타임리프 스프링 통합
= 타임리프는 스프링이 없어도 되지만 스프링을 위한 기능을 제공함
- ${@myBean.doSomething()} 처럼 스프링 빈 호출 가능
- 메시지 국제화 기능의 편리한 통합
- 스프링의 검증, 오류 처리 통합
- 스프링 변환 서비스 (ConversionService) 통합
- 폼 컴포넌트 + 추가적인 속성 제공

타임리프 템플릿 엔진을 스프링 빈에 등록, 타임리프용 뷰 리졸버를 스프링 빈으로 등록해야 하지만 [스프링 부트] 는 이러한 부분 자동화 해줌!
`implementation 'org.springframework.boot:spring-boot-starter-thymeleaf'`


## 입력 폼 처리

-  `th:object` : 커맨드 객체를 지정한다.  
-  `*{...}` : 선택 변수 식이라고 한다. `th:object` 에서 선택한 객체에 접근한다. 
- `th:field`
	- HTML 태그의 `id` , `name` , `value` 속성을 자동으로 처리해준다.

빈 아이템 객체를 넘겨 주기! `model.addAttribute("item", new Item());`

```HTML
<form action="item.html" th:action th:object="${item}" method="post">  
    <div>        
	    <label for="itemName">상품명</label>  
        <input type="text" id="itemName"  th:field="*{itemName}" class="form-control" placeholder="이름을 입력하세요">  
    </div>    
    <div>        
	    <label for="price">가격</label>  
        <input type="text" id="price" th:field="*{price}" class="form-control" placeholder="가격을 입력하세요">  
    </div>    
    <div>        <
	    label for="quantity">수량</label>  
        <input type="text" id="quantity" th:field="*{quantity}" class="form-control" placeholder="수량을 입력하세요">  
    </div>
```
- 넘겨받은 item 객체를 th:object="${객체명}" 으로 받아 사용
- 객체 요소 접근을 th:field="${객체명.요소}" [= *{요소}] 로  접근
- name 과 value, id 속성들을 한번에 처리가 가능!
- 이후 폼 검증 부분에서 폼처리와 관련된 강력한 기능을 제공한다더라...

## 요구사항 추가!
= 타임리프를 이용해 폼에 체크박스, 라디오 버튼, 셀렉트 박스를 추가!

- 판매 여부
	- 판매 오픈 여부
	- 체크 박스로 선택할 수 있다. 
- 등록 지역
	- 서울, 부산, 제주
	- 체크 박스로 다중 선택할 수 있다. 
- 상품 종류
	- 도서, 식품, 기타
	- 라디오 버튼으로 하나만 선택할 수 있다. 
- 배송 방식
	- 빠른 배송  
	- 일반 배송  
	- 느린 배송  
	- 셀렉트 박스로 하나만 선택할 수 있다.


## 체크박스 (단일)
(타임리프 X 인 경우)
- 체크 박스 체크시 HTML Form 에서 open=on 값이 넘어감, 컨버터가 boolean 타입 true로 변경해줌
- 체크하지 않았을 경우는 null 이 나옴... (전송 자체가 안됨)
- 이는 수정시 서버에서 문제가 생길 수도 있다!! -> false 가 아니어서..

이를 해결하기 위해 스프링은 _ open 이라는 히든 속성 값을 사용하는 기믹을 사용함!
```HTML
<input type="checkbox" id="open" name="open" class="form-check-input"> 
<input type="hidden" name="_open" value="on"/> <!-- 히든 필드 추가! -->
```
해당 방식으로 사용하면 체크 안됬을 경우 false 값 반환됨!


## 체크박스 (단일2)
(타임리프 O)

```HTML
<input type="checkbox" id="open" th:field="*{open}" class="form-check-input">
```
히든 필드를 자동으로 만들어 줌..ㅋ


## 체크박스 (멀티)

```java
@ModelAttribute("regions")
```
- @ModelAttribute("key") 이 붙은 메서드의 반환값을 key-value 형태로 모델에 넣고
- 해당 클래스의 컨트롤러 model 에 자동으로 model.attribute(key) 가 됨!

! LinkedHashMap은 순서 보장!?


## 라디오 버튼

라디오 버튼은 여러 선택지 중에 하나를 선택할 때 사용할 수 있음!
enum 사용!
- 타임리프에서 enum에 직접 접근도 가능하긴 하다... (package 타고 드감)
	- `<div th:each="type :${T(hello.itemservice.domain.item.ItemType).values()}">`


## 셀렉트 박스

- 배송 방식
	- 빠른 배송  
	- 일반 배송  
	- 느린 배송  
	- 셀렉트 박스로 하나만 선택할 수 있다.
어.. 그냥 함


## 정리

암튼 스프링 부트 에서는 타임리프 설정을 자동으로 해줌 + 수정 가능함
@ModelAttribute 어노테이션 사용시 Model에 해당 함수의 반환값 넣어서 반환함!



