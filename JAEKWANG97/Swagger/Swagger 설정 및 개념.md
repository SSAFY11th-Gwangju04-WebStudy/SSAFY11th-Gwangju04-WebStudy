### Swagger를 이용한 REST API 문서화
- 프로젝트 개발 시 일반적으로 FrontEnd 개발자와 BackEnd 개발자가 분리
- FrontEnd 개발자의 경우 화면과 로직에 집중을 하고 BackEnd 개발자가 만든 문서 API를 보며 데이터 처리를 하게 된다.
- 이때 개발 상황의 변화에 따른 API의 추가 또는 변경할 때 마다 문서에 적용하는 불편함 발생
- 이 문제를 해결하기 위해 Swagger를 사용
- Controller에 정의 되어 있는 모든 URL을 바로 확인할 수 있음

### Swagger의 주요 구성 요소
- Swagger Editor
	- 웹 기반 에디터로, 개발자가 Swagger 스펙을 사용하여 API를 설계할 수 있게 도와줌
	- 실시간으로 입력하는 동안 오류를 검사하고, 수정 사항을 시각적으로 보여줌
- Swagger UI
	- Swagger로 작성된 API 스펙을 이용하여 API문서를 자동으로 생성함
	- 이 UI는 사용자가 API의 메소드를 직접 탐색하고 테스트할 수 있는 기능을 제공
- Swagger Codegen
	- 다양한 프로그래밍 언어와 서버 프레임워크용 클라이언트 라이브러리와 서버 스텁을 자동으로 생성할 수 있는 강력한 코드 생성 도구

### Swagger의 작동 방식
- Swagger는 API를 설명하기 위해 사용하는 YAML 또는 JSONB 파일인 Swagger Specification을 중심으로 작동함
-  API가 지원하는 모든 엔드포인트, 파라미터, 예상 응답 등을 정의함
- Swagger 도구들은 이 스펙 파일을 사용하여 문서화를 자동화하고, 클라이언트 라이브러리 및 서버 구현을 생성함


### Swagger 설정  추가

```java
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private static final String API_NAME = "Study API";
    private static final String API_VERSION = "0.0.1";
    private static final String API_DESCRIPTION = "Study API 명세서";

    @Bean
    public Docket api() {
        Parameter parameterBuilder = new ParameterBuilder()
            .name(HttpHeaders.AUTHORIZATION)
            .description("Access Tocken")
            .modelRef(new ModelRef("string"))
            .parameterType("header")
            .required(false)
            .build();

        List<Parameter> globalParamters = new ArrayList<>();
        globalParamters.add(parameterBuilder);

        return new Docket(DocumentationType.SWAGGER_2)
                .globalOperationParameters(globalParamters)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.kjh.study.api"))
                .paths(PathSelectors.any())
                .build();
    }

    public ApiInfo apiInfo() {
	return new ApiInfoBuilder()
		.title(API_NAME)
		.version(API_VERSION)
		.description(API_DESCRIPTION)
		.build();
    }
}
```

**ApiInfo apiInfo()**

API의 이름은 무엇이며 현재 버전은 어떻게 되는지 해당 API에 대한 정보를 적어주면된다.

**ParameterBuilder**

API를 테스트할때 모든 API에 전역 파라미터를 설정한다.  
해당소스는 모든 API 테스트시 header에 'Autorization' 이라는 값을 추가하도록 했다.

**RequestHandlerSelectors.basePackage(String packageName)**

Swagger를 적용할 클래스의 package 명을 적어준다.

**PathSelectors.any()**

해당 package 하위에 있는 모든 url에 적용시킨다. /test/**로시작되는 부분만 적용해주고싶다면  
PathSelectors.ant(String pattern) 메서드에 url parttern을 추가해주면된다.
