

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
