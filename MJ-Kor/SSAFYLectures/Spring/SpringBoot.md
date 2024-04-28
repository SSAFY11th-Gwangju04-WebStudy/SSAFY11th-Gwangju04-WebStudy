## Spring Boot에서 자동으로 생성되는 것들

- 필수적인 요소들 (Dispatcher Servlet, component scan 등)

## Spring Boot의 특징

- Spring의 경우 개발하려면 사전에 많은 작업(library 추가, dependency 설정 등)을 해야 했다.

## Spring의 장점

1. project에 따라 자주 사용되는 library들이 미리 조합되어 있음
2. 복잡한 설정을 자동으로 처리
3. 웹 서버에 의존적이지 않은 독립적인 Spring Application 구축 가능
4. Tomcat, Jetty와 같은 WAS를 추가로 설치하지 않아도 개발 가능(내장 서버)
    - 내장 서버가 존재하므로 여러 개 서버를 띄울 수 있음
5. WAS에 배포하지 않고도 실행할 수 있는 JAR 파일로 Web Application을 개발 가능
    - Spring은 Tomcat에 띄워야 하므로 WAR 파일로 ~
    - Spring Boot는 내장 Tomcat이 존재하므로 JAR 파일로 가능

## SpringBoot Project 생성 구조 및 주요 구성 폴더/파일

| 프로젝트 주요 파일 | 설명 |
| --- | --- |
| src/main/java | java source directory |
| HelloSpringBootApplication.java | application을 시작할 수 있는 main method가 존재하는 스프링 구성 메인 클래스 |
| static | css, js, img등의 정적 resource directory |
| templates | SpringBoot에서 사용가능한 여러가지 ViewTemplate(Thymeleaf, Velocity 등)의 위치 |
| application.properties | application 및 스프링의 설정 등에서 사용할 여러 가지 property를 정의한 file |
| src/main | jsp등의 resource directory |

- war 배포 다시 듣기
    - source 체크 → java 파일까지 모두 export → 모든 권한을 다 넘기는 것
    - source 체크x → class 파일만 → 유지보수하려면 개발자 불러야 함
    - 배포할 때, 디버깅용 로그들은 싹 다 없애자
        - Log level을 올리고 sysout을 모두 지워야 한다
        - e.printStackTrace() 다 지울 것 → 배포한 서버에 log가 쌓임
        - 배포 서버 log에는 에러가 아닌 실행 결과, 과정을 남길 것
    
    - maven pom.xml도 상속이 된다
    - @SpringBootAnnotation
        - @EnableAutoConfiguration
        - @ComponentScan
        
- main method 있는 이유? → 내장 tomcat이 돌아가기 위함임

## Spring Boot 복습

- Dependency
    1. devtool - reload 기능
    2. springweb - web 구축, spring mvc이면서 RESTful을 포함하고 있다.
    3. mysql - DB
    4. mybatis - 기능 구현하고 db 연결하고 세팅하는게 오류 잡기 편함

- 전체적인 실행 흐름을 보려면?
    - legacy는 web.xml
    - boot는 application.properties

- configuration과 xml (root-context, servlet-context 등) 을 매칭해보자
    - WebMVCConfiguration
        - interceptor 설정
        - cors 설정
        - resource 설정
        - MainController 설정

- @Value
    - ${}안의 변수들을 application.properties에서 찾는다.

## application.properties 분석

![Untitled](https://github.com/MJ-Kor/SSAFY11th-Gwangju04-WebStudy/blob/main/MJ-Kor/SSAFYLectures/Spring/imgs/spring_boot.png)

- line 12~16 - hikari Connection pool에 DB를 설정 <=> legacy에서 JNDI로 ConnectionPool에 접근하는 부분(META-INF → context.xml)

| 위치 | Spring Boot | Spring Legacy |
| --- | --- | --- |
| line 7~8 | ViewResolver 경로 설정 | root-context.xml의 ViewResolver bean |
| line 12~25 | hikari Connection pool에 DB를 설정 및 속성 설정 | JNDI로 ConnectionPool에 접근하기 위해 META-INF 하위 context.xml의 Resource 태그에 DB를 설정 및 속성 설정 |
| line 54~56 | log 설정 (root는 info만, com.ssafy는 debug 요소) |  |
| line 34~35 | Mybatis 연결 | root-context.xml의 sqlSessionFactorybean |

## Configuration 파일


| 위치(board6) | Spring Boot | Spring Legacy |
| --- | --- | --- |
| com.ssafy.config.DataBaseConfiguration | Datasource 객체 생성 | root-context.xml의 bean 객체 |
| com.ssafy.config.WebMvcConfigurer | Interceptor 설정 | servlet-context의 interceptor bean 객체 |
| com.ssafy.config.WebMvcConfigurer | MainController 기능 | MainController |

## ‘Lombok’ dependency의 Slf4j


- @Slf4j annotation만으로 log를 출력해주는 역할을 한다.
- log.[debug, info, trace, warning, warn, error] 로 사용가능하다.
