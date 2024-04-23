Mybatis-spring 
- mybatisconfig.xml 삭제
- dbinfo 파일 삭제
- daoImpl 삭제
- dao → mapper 변경 , @mapper 사용
- transaction 어노테이션으로 commit , rollback 구현


mybatis
mybatis-config.xml

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/cd4c5c0a-1563-4707-afa2-6b081ed69d45/a2b5da81-4058-416b-93d6-7883b8d0194e/Untitled.png)

Mapper.xml → 인터페이스로 바꾸기

- xml → 인터페이스 만들기
- 함수이름은 매개변수가 달라서 그대로 써도됨

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/cd4c5c0a-1563-4707-afa2-6b081ed69d45/6154f11b-7882-4d51-ae73-66c0e7211490/Untitled.png)

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/cd4c5c0a-1563-4707-afa2-6b081ed69d45/d0d1ec61-8143-4fed-b353-03833a40b279/Untitled.png)

공식 홈페이지

https://mybatis.org/spring/ko/getting-started.html

Mybatis 에서 순수 mybatis를 써서 따로 쓸때 

config와 mapper를 써야된다는 단점이있었는데

spring과 mybatis를 연결해주는 걸 제공해줘서 config mapper를 제거할 수 있다.

pom.xml

- mybatis - spring 사용시 필요한 라이브러리
- -지금은 순수 mybatis인듯

```
<com.mysql-version>8.2.0</com.mysql-version>
	<org.mybatis-version>3.5.15</org.mybatis-version>
	<org.mybatis-spring-version>3.0.3</org.mybatis-spring-version>
```

resource = mapper/resorces 처럼 앞에  경로가없으면 class path이다.

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/cd4c5c0a-1563-4707-afa2-6b081ed69d45/5af88cba-93fa-418c-bcbc-3db23400f5b2/Untitled.png)

sqlssesionTemplate

- 관리 객체?

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/cd4c5c0a-1563-4707-afa2-6b081ed69d45/41d5864d-a249-4ca8-8ec7-f869a794064b/Untitled.png)

# mapper Interface

- mapper.xml = dao
- 인터페이스에서 트랜잭션 처리하는부분을 제외한 나머지는 다똑같다.
- sql을 호출하는 프로그램을 type safe하게 기술하기 위해 mybatis 3.x부터 등장
- mapping 파일에 있는 sql을 java interface를 통해 호출할 수 있도록해줌
- mapper interface는 메서드 이름으로 기반해서 호출한다.

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/cd4c5c0a-1563-4707-afa2-6b081ed69d45/67555246-99fe-4533-bf2c-ee967cc9168a/Untitled.png)

MyBatis + spring 

- mybatis관련빈을 등록하여 my batis를 사용
- 트랜잭션 기능을 사용하면 손쉽게 트랙잭션 처리가능

mybatis 에서 스프링에서 설정하는걸로으로 바꿈

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/cd4c5c0a-1563-4707-afa2-6b081ed69d45/c6e4b29d-f3d5-4764-8471-e13352a126bd/Untitled.png)

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/cd4c5c0a-1563-4707-afa2-6b081ed69d45/b09a61e3-9f22-4ffb-b165-4486d8527c8a/Untitled.png)

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/cd4c5c0a-1563-4707-afa2-6b081ed69d45/ecb578dd-c24f-41a3-a520-70f1ca8b7a74/Untitled.png)

# mapper 빈등록

- mapperSannerConfigurer 설정 시 mapper 인 자동으로 검색하여 빈으로 등록
- basePackage로 패키지를  서정하면 해당패키지 하위의 모든 mapper 인터페이스가 자동으로 등록

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/cd4c5c0a-1563-4707-afa2-6b081ed69d45/5e73e8f3-ec59-469a-b090-afd3ca9af4b3/Untitled.png)

@ mapper 없이도 되긴하는데 명시적으로 해주자

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/cd4c5c0a-1563-4707-afa2-6b081ed69d45/7648b08e-bbc8-4255-bcfb-8890c65c3f33/Untitled.png)

Mapper

- mapper도 객체니 mybatis-spring scane해서 읽는다.
- dao → mapper로 바꾼다.
- serviceImpl 에 mapper주입
- board-xml에서 메서드 이름만 주의해서 바꾸기만하면된다.

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/cd4c5c0a-1563-4707-afa2-6b081ed69d45/ecb578dd-c24f-41a3-a520-70f1ca8b7a74/Untitled.png)

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/cd4c5c0a-1563-4707-afa2-6b081ed69d45/62900ab1-d7c7-4b1b-9a66-89cffa8be674/Untitled.png)

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/cd4c5c0a-1563-4707-afa2-6b081ed69d45/0b7f06ea-c376-48da-9f1c-8bf991af59e5/Untitled.png)

```
package com.ssafy.member.model.mapper;

import java.sql.SQLException;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.member.model.MemberDto;

@Mapper
public interface MemberMapper {

	int idCheck(String userId) throws SQLException;
	void joinMember(MemberDto memberDto) throws SQLException;
	MemberDto loginMember(Map<String, String> map) throws SQLException;
	
}

```

트랜잭션 관리

- trassactionmanger 라는 객체 생성 , 아이디바꾸면안됨
- property로 관리할 대상 지정

```
	
	<bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
		<property name="dataSource" ref="dataSource" />
	</bean>
	
	<!-- 트랜잭션 관리를 어노테이션으로 설정할거다? -->
	<tx:annotation-driven transaction-manager="transactionManager"/>
```

- insert가 되면 commit되고 실패하면  rollback 이 자동으로 실행된다.
