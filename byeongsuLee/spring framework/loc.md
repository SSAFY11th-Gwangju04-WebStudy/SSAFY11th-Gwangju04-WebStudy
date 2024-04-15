# 수업 정리

1교시

<aside>
💡 1교시 
IOC  inversion of control 
- 제어의 역전,커넥션풀처럼 미리만들어서 사용해서 쓰는것을  loc라고한다.
- Spring framework의 등장배경 : EJB의 배우기 어렵고 무겁다.

</aside>

2교시

<aside>
💡 bean : 객체
application factory 객체생성
DI
- 결합도가 높은 코드에서 값이 변경될떄 바꿔야될게 너무많음

결합도 줄이기
1. new 객체생성 : 결합도가 높다.
2. 다형성 : 객체 생성할 시 바꿔줘야함
3. factory 사용: 다형성-객체이름 바꿔야되는점 해결

container 
- factory로 bean 객체를 생성하고 lifecycle을 관리
- 개발자는  xml,annotiaion , 자바 confing 한가지 방법으로 관리해야할 bean을 알려줘야한다.

maven 버전관리
- context 버전이 바뀌면 해당 관련된 maven 파일 다바꿔야되서
- context{} 사용

Di xml(생성자)
- default 생성자를 기준으로 객체를 생성한다.
- 따라서 기본생성자가 아닌 다른걸로 하면 에러나서 bean 등록과 매개변수를 bean에 등록해야한다.

</aside>

DI xml

- 이방법은 bean안에 객체를 주입하는방법인거같다.
- 다른방법으로는 setter 주입이다.

```
bean을 보아라 
생성자랑 매개변수를 가진 생성자가 있으면 무엇이 실행될까?
-답은 생성자다. 즉 생성자를 지우면 오류가 발생되서 어떤 메서드를 실행할 지 
지정을 해줘야한다. 그게 바로 constructor-arg ref="messageDto"> </constructor-arg>

// 바뀐부분
	<bean id="kor" class="com.ssafy.hello.di4.xml.HelloMessageKor">
		<property name="messageDto" ref="messageDto"></property>	
	</bean>
	
	여기서 name:class messagDto를 의미하고 ref은 아래를 의미한다.

//기존
	<bean id ="messageDto" class="com.ssafy.hello.di4.xml.MessageDto"></bean>
	
	
```

DI xml setter이용하는법

- property 할려면 default 생성자가 있어야된다.
- setter

```
<bean id = "boardService" class ="com.ssafy.board.model.service.BoardServiceImpl">
<property name="boardDao" ref="boardDao"></property>
</bean>
```

Di 생성자 주입

```

	<bean id="boardController" class ="com.ssafy.board.controller.BoardController">
		 <constructor-arg ref="boardService"></constructor-arg>
	</bean>
	<bean id = "boardService" class ="com.ssafy.board.model.service.BoardServiceImpl"></bean>
```

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/cd4c5c0a-1563-4707-afa2-6b081ed69d45/d8a19411-f2fb-4aed-b01a-b395f1053481/Untitled.png)

Di annotaiton

- @component(value =”kor”),@scope(value=”singleton”);
- @repository : dao같이 db와 관련된 것들은 repository
