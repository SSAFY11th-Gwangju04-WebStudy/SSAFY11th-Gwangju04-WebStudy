# 메세지, 국제화


## 메시지, 국제화 소개


### 메시지

화면에 있는 문구를 한번에 모두 바꾸기는 어려움 (HTML문서에 하드코딩 되있기 때문)
이를 message.properteis 라는 메시지 관리용 파일 만들고, html 에는 이를 불러와 사용!
```
item=상품
item.id=상품 ID
item.itemName=상품명
item.price=가격
item.quantity=수량
```

`<label for="itemName" th:text="#{item.itemName}"></label>`


### 국제화

메시지 에서 설정한 파일을 각 나라별로 관리하면 서비스 국제화가 가능하다!
접근 인식법은 `accept-language` 헤더 값을 사용 or 사용자 선택해 쿠키로 처리!


스프링은 이러한 메시지, 국제화 기능을 모두 제공하고 타임리프 에서 이를 편리하게 통합 제공함!


## 스프링 메시지 소스


### 설정

스프링이 제공하는 MessageSource(인터페이스) 를 스프링 빈으로 등록하면 됨!
`ResourceBundleMessageSource`(구현체) 를 스프링 빈으로 등록

스프링 부트의 경우 application.properties 에 
` spring.messages.basename=messages[,config.i18n.messages]`

resorces/ 경로에 message.properties 파일 생성!



### 사용

`ms.getMessage("hello", null, null)` 
- **code**: `hello`  
- **args**: `null`  
- **locale**: `null`


- 타임리프의 메시지 표현식 `#{...}`
	- ` <div th:text="#{label.item}"></h2>`
