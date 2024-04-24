## 기존 jsp → vue.js 변경 시 문제점

jsp → vue.js 변경시 model, session  등 자바의 객체 사용 불가  = 데이터 전송 x

js = javascript 라서 session을 사용할 수 없다.

## 해결방법

- token를 사용해서 session 로그인 처리를 할 것입니다.
- json, xml 사용하여 데이터 전송 및 view

# Spring boot

- 사용이유
    - 설정 등 자동으로 되는게 많음 - 우리가 생각해야되는건 안됨
    - 설정 자동 o : component scan, dispatcherservlet , resources, driven
    - 설정 자동 x : intercept,file,viewresolver ,datasource
- 특징
    - spring 이용 개발 시 사전 library , dependency 설정 및 여러가지 구성 및 설정파일 너무 복잡했다.
    - maven 대신 → 자주사용되는 library 미리 조합 되어있음 (starter 의존성 제공) → 복잡한 설정을 자동으로 처리
    - 기존 spring과 tomcat은 내장되어있지않다.
        - tomcat에 war파일을 줘야한다.
    - spring Boot 내부에 tomcat이 내장되어있다.
        - SB 를 실행하면 서버가 실행된다.
        - 따라서 MAIN이 존재하고 JAR파일로 WEB APPLICATION을 개발 할 수 있다.

## spring boot project 생성

- 방법 1 : spring io 사이트 - spring intialize 주소 :https://start.spring.io/
- 방법 2 : eclipse - new spring project
- dependency 추가 - devtool,spring web, mysql,mybatis

devtool

- 애플리케이션 개발 시 자동으로 설정 변경을 로드하고, 애플리케이션을 재시작할 때 시간을 절약할 수 있도록 여러 기능을 제공한다.
- 기능
    1. **자동 재시작(Automatic Restart)**: 클래스패스에 있는 파일이 변경될 때마다 애플리케이션을 자동으로 재시작합니다. 이 기능은 개발 중 변경 사항을 빠르게 보고 테스트할 수 있게 해줍니다.
    2. **라이브 리로드(Live Reload)**: 웹 브라우저 플러그인과 연동하여 HTML, CSS, JavaScript 파일이 변경되었을 때 웹 페이지를 자동으로 새로고침합니다. 이를 통해 웹 개발을 보다 신속하게 진행할 수 있습니다.
    3. **기본 프로퍼티(Default Properties)**: 개발 중에 사용하기에 적합한 여러 기본 설정을 제공합니다. 예를 들어, 템플릿 캐시 비활성화, H2 데이터베이스 콘솔 활성화 등이 있습니다.
    4. **조건부 설정(Conditional Configuration)**: 개발 중에만 적용되는 조건부 설정을 할 수 있습니다. 예를 들어, **`application-dev.properties`** 파일을 만들어 개발 환경에서만 사용되는 특정 설정을 관리할 수 있습니다.
    5. **원격 애플리케이션(Remote Application)**: 원격 서버에서 실행 중인 애플리케이션에 대해 DevTools 기능을 사용할 수 있습니다. 이를 위해 약간의 설정이 필요합니다.

war 

- 웹 압축파일임
- jar =자바 압축파일임

tip)  개발 시 주의할점

- logger debug - 많은 에러 찍지말기
- sysout 그거 다지우기 , e.trace 머시기 지우기

## spring boot project static 폴더

- css , img,list.html 넣기

## 내장톰캣 .jsp 실행 방법

- jsp → servlet으로 바꿔주는게 없다.
- 설정하는 방법 : pom-xml

## jsp 실행시 static으로 되는 이유

- 해결법
    - src/main/resouces/static 에 정적 파일들(css,img,index.html) 옮기기
    - src/main - webapp -WEB-INF/ views 에 .jsp 파일 두기
    - [application.properties](http://application.properties) - prefix, suffix 추가
    
    ```
    spring.mvc.view.prefix=/WEB-INF/views/
    spring.mvc.view.suffix=.jsp
    ```
    

# component 스캔 되는 이유

- @ springBootApplication안에 component 스캔이 포함되어 있다.
- component 범위 기준
    - @ springbootApplication 이 어노테이션이 명시된 클래스 부터 모든 하위 페이지가 적용범위
- componet 범위 설정방법
    1. @ springBootApplication 선언을 상위 패키지에 놓기
    2. @componentScan(basePackages = “com.ssafy”)추가

```
package com.ssafy.hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.ssafy")
public class HelloSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloSpringBootApplication.class, args);
	}

}

```
