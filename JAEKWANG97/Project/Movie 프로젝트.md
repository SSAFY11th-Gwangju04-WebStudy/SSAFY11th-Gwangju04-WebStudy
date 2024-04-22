참고자료 : https://a-develop.tistory.com/82

sts 에서 DynamicWebProject 생성 후 Maven Project로 설정 

>pom.xml 설정


```xml
<project xmlns="http://maven.apache.org/POM/4.0.0"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<groupId>com.ssafy</groupId>
	<artifactId>Movie</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<packaging>war</packaging>

	<properties>
		<java-version>17</java-version>
		<org.springframework-version>6.1.3</org.springframework-version>
		<org.aspectj-version>1.9.21</org.aspectj-version>
		<org.slf4j-version>2.0.12</org.slf4j-version>
		<log4j-version>2.22.1</log4j-version>
		<jakarta.servlet-version>6.0.0</jakarta.servlet-version>
		<jakarta.jsp-api-version>3.1.1</jakarta.jsp-api-version>
		<jakarta.jsp.jstl-version>3.0.1</jakarta.jsp.jstl-version>
		<commons-fileupload-version>1.5</commons-fileupload-version>
		<mysql-connector-version>8.3.0</mysql-connector-version>
		<mybatis-version>3.5.15</mybatis-version>
		<mybatis-spring-version>3.0.3</mybatis-spring-version>
		<commons-dbcp-version>2.11.0</commons-dbcp-version>
		<maven-compiler-plugin-version>3.8.1</maven-compiler-plugin-version>
		<maven-war-plugin-version>3.2.3</maven-war-plugin-version>
	</properties>

	<dependencies>
		<!-- Spring -->
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-context</artifactId>
			<version>${org.springframework-version}</version>
			<exclusions>
				<!-- Exclude Commons Logging in favor of SLF4j -->
				<exclusion>
					<groupId>commons-logging</groupId>
					<artifactId>commons-logging</artifactId>
				</exclusion>
			</exclusions>
		</dependency>
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-webmvc</artifactId>
			<version>${org.springframework-version}</version>
		</dependency>
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-jdbc</artifactId>
			<version>${org.springframework-version}</version>
		</dependency>

		<!-- AspectJ -->
		<dependency>
			<groupId>org.aspectj</groupId>
			<artifactId>aspectjrt</artifactId>
			<version>${org.aspectj-version}</version>
		</dependency>
		<dependency>
			<groupId>org.aspectj</groupId>
			<artifactId>aspectjweaver</artifactId>
			<version>${org.aspectj-version}</version>
		</dependency>

		<!-- Logging -->
		<dependency>
			<groupId>org.slf4j</groupId>
			<artifactId>slf4j-api</artifactId>
			<version>${org.slf4j-version}</version>
		</dependency>
		<dependency>
			<groupId>org.slf4j</groupId>
			<artifactId>jcl-over-slf4j</artifactId>
			<version>${org.slf4j-version}</version>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>org.slf4j</groupId>
			<artifactId>slf4j-log4j12</artifactId>
			<version>${org.slf4j-version}</version>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>org.apache.logging.log4j</groupId>
			<artifactId>log4j-core</artifactId>
			<version>${log4j-version}</version>
			<scope>runtime</scope>
			<exclusions>
				<exclusion>
					<groupId>javax.mail</groupId>
					<artifactId>mail</artifactId>
				</exclusion>
				<exclusion>
					<groupId>javax.jms</groupId>
					<artifactId>jms</artifactId>
				</exclusion>
				<exclusion>
					<groupId>com.sun.jdmk</groupId>
					<artifactId>jmxtools</artifactId>
				</exclusion>
				<exclusion>
					<groupId>com.sun.jmx</groupId>
					<artifactId>jmxri</artifactId>
				</exclusion>
			</exclusions>
		</dependency>

		<!-- Servlet API and JSP -->
		<dependency>
			<groupId>jakarta.servlet</groupId>
			<artifactId>jakarta.servlet-api</artifactId>
			<version>${jakarta.servlet-version}</version>
			<scope>provided</scope>
		</dependency>
		<dependency>
			<groupId>jakarta.servlet.jsp</groupId>
			<artifactId>jakarta.servlet.jsp-api</artifactId>
			<version>${jakarta.jsp-api-version}</version>
			<scope>provided</scope>
		</dependency>
		<dependency>
			<groupId>jakarta.servlet.jsp.jstl</groupId>
			<artifactId>jakarta.servlet.jsp.jstl-api</artifactId>
			<version>3.0.0</version> <!-- 제품 버전 -->
		</dependency>
		<dependency>
			<groupId>org.glassfish.web</groupId>
			<artifactId>jakarta.servlet.jsp.jstl</artifactId>
			<version>${jakarta.jsp.jstl-version}</version>
		</dependency>

		<!-- Data Handling -->
		<dependency>
			<groupId>commons-fileupload</groupId>
			<artifactId>commons-fileupload</artifactId>
			<version>${commons-fileupload-version}</version>
		</dependency>
		<dependency>
			<groupId>com.mysql</groupId>
			<artifactId>mysql-connector-j</artifactId>
			<version>${mysql-connector-version}</version>
		</dependency>
		<dependency>
			<groupId>org.mybatis</groupId>
			<artifactId>mybatis</artifactId>
			<version>${mybatis-version}</version>
		</dependency>
		<dependency>
			<groupId>org.mybatis</groupId>
			<artifactId>mybatis-spring</artifactId>
			<version>${mybatis-spring-version}</version>
		</dependency>

		<!-- https://mvnrepository.com/artifact/com.zaxxer/HikariCP -->
		<dependency>
			<groupId>com.zaxxer</groupId>
			<artifactId>HikariCP</artifactId>
			<version>5.0.1</version>
		</dependency>

	</dependencies>

	<build>
		<plugins>
			<plugin>
				<artifactId>maven-compiler-plugin</artifactId>
				<version>${maven-compiler-plugin-version}</version>
				<configuration>
					<release>${java-version}</release>
					<parameters>true</parameters>
				</configuration>
			</plugin>
			<plugin>
				<artifactId>maven-war-plugin</artifactId>
				<version>${maven-war-plugin-version}</version>
			</plugin>
		</plugins>
	</build>
</project>
```

1. **Spring Framework**
    
    - `spring-context`: 애플리케이션의 구성과 서비스를 관리합니다.
    - `spring-webmvc`: 웹 애플리케이션을 위한 MVC 프레임워크를 제공합니다.
    - `spring-jdbc`: JDBC와의 통합을 도와 데이터베이스 작업을 용이하게 합니다.
2. **AspectJ**
    
    - `aspectjrt` 및 `aspectjweaver`: AOP(Aspect-Oriented Programming) 지원을 위한 라이브러리입니다.
3. **Logging**
    
    - `slf4j-api`, `jcl-over-slf4j`, `slf4j-log4j12`: 로깅을 위한 SLF4J API와 Log4j의 통합을 제공합니다.
    - `log4j-core`: Log4j2의 핵심 구현체로 로깅 기능을 제공합니다.
4. **Jakarta Servlet API and JSP**
    
    - `jakarta.servlet-api`와 `jakarta.servlet.jsp-api`: 서블릿과 JSP를 위한 Jakarta API를 제공합니다.
    - `jakarta.servlet.jsp.jstl-api` 및 `jakarta.servlet.jsp.jstl`: JSTL (JavaServer Pages Standard Tag Library)을 사용하여 JSP 페이지의 관리를 간소화합니다.
5. **Data Handling**
    
    - `commons-fileupload`: 파일 업로드 기능을 제공합니다.
    - `mysql-connector-j`: MySQL 데이터베이스 연결을 위한 JDBC 드라이버입니다.
    - `mybatis` 및 `mybatis-spring`: MyBatis를 사용하여 SQL 매핑과 스프링 통합을 지원합니다.
    - `HikariCP`: 고성능 Java SQL 데이터베이스 커넥션 풀 라이브러리입니다.
6. **Apache Commons DBCP**
    
    - `commons-dbcp`: 데이터베이스 커넥션 풀을 관리합니다.

> 이후 java config를 통해서 설정광리
# RootConfig.java

```java
package com.ssafy.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@ComponentScan(basePackages = { "com.ssafy" })
@MapperScan(basePackages = {"com.ssafy.mapper"})
public class RootConfig {

	@Bean
	public DataSource dataSource() {
		HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setDriverClassName("com.mysql.cj.jdbc.Driver");
		hikariConfig.setJdbcUrl(
				"jdbc:mysql://localhost:3306/ssafyweb?serverTimezone=UTC&useUniCode=yes&characterEncoding=UTF-8");
		hikariConfig.setUsername("ssafy");
		hikariConfig.setPassword("ssafy");

		HikariDataSource dataSource = new HikariDataSource(hikariConfig);

		return dataSource;
	}

	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
	    SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
	    sqlSessionFactory.setDataSource(dataSource());
	    
	    // MyBatis 설정 추가
	    org.apache.ibatis.session.Configuration mybatisConfig = new org.apache.ibatis.session.Configuration();
	    mybatisConfig.setMapUnderscoreToCamelCase(true);  // 스네이크 케이스를 카멜 케이스로 매핑
	    sqlSessionFactory.setConfiguration(mybatisConfig);
	    
	    return (SqlSessionFactory) sqlSessionFactory.getObject();
	}


}
```

여기서는 HiKariConfig를 통해서 커넥션 풀을 관리했음. 
추가로 반환되는 값 자동 매핑을 위해서 
스네이크 케이스를 카멜 케이스로 매핑하는 옵션도 적용했음

# ServletConfig.java

```java
package com.ssafy.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.ssafy.interceptor.WriteInterceptor;

@EnableWebMvc
@ComponentScan(basePackages = { "com.ssafy.controller" })
public class ServletConfig implements WebMvcConfigurer {

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {

		InternalResourceViewResolver bean = new InternalResourceViewResolver();
		bean.setViewClass(JstlView.class);
		bean.setPrefix("/WEB-INF/views/");
		bean.setSuffix(".jsp");
		registry.viewResolver(bean);
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}
	
	@Autowired
    private WriteInterceptor writeInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
	    registry.addInterceptor(writeInterceptor)
	            .addPathPatterns("/board/write");  // 절대 경로로 변경
	}

}
```

1. **@EnableWebMvc**: 이 어노테이션은 Spring MVC를 활성화하고, Spring의 DispatcherServlet이 웹 요청을 처리할 때 필요한 빈 설정을 자동으로 구성합니다.
    
2. **@ComponentScan**: 지정된 패키지(`com.ssafy.controller`) 내에서 Spring의 컴포넌트, 서비스, 구성 등을 스캔하고 빈으로 등록합니다. 이 경우 컨트롤러 클래스를 자동으로 검색하여 스프링 애플리케이션 컨텍스트에 등록합니다.
    
3. **configureViewResolvers**: 뷰 리졸버를 설정합니다. `InternalResourceViewResolver`는 JSP 파일 경로를 관리하며, 각 뷰 이름 앞에는 `/WEB-INF/views/`를, 뒤에는 `.jsp`를 붙여 최종 경로를 결정합니다. `JstlView` 클래스를 사용하여 JSTL 태그가 포함된 뷰를 처리할 수 있습니다.
    
4. **addResourceHandlers**: 정적 리소스(예: CSS, JavaScript, 이미지 파일 등)의 서빙을 위해 리소스 핸들러를 추가합니다. `/resources/` 경로에 위치한 파일들을 웹에서 `/resources/**` URL 패턴으로 접근할 수 있게 설정합니다.
    
5. **addInterceptors**: 인터셉터를 등록합니다. `WriteInterceptor`는 특정 경로(`/board/write`)에 대한 요청을 가로채서 추가적인 처리를 할 수 있게 합니다. 인터셉터는 컨트롤러 메소드가 호출되기 전이나 후에 사용자 정의 작업을 수행할 수 있습니다

# WebConfig.java

```java
package com.ssafy.config;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import jakarta.servlet.Filter;

public class WebConfig extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { RootConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { ServletConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	@Override
	protected Filter[] getServletFilters() {
		CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
		encodingFilter.setEncoding("UTF-8");
		return new Filter[] { encodingFilter };
	}
}
```

### getRootConfigClasses()

- 이 메소드는 애플리케이션의 루트 컨텍스트를 위한 설정 클래스를 반환합니다. `RootConfig.class`를 포함하는 배열을 반환하며, 이 클래스는 일반적으로 서비스, 리포지토리, 데이터 소스 구성 등 전반적인 백엔드 구성을 관리합니다.

### getServletConfigClasses()

- 이 메소드는 `DispatcherServlet`의 웹 컨텍스트를 위한 설정 클래스를 반환합니다. `ServletConfig.class`를 포함하는 배열을 반환하며, 이 클래스는 컨트롤러, 뷰 리졸버, 핸들러 매핑 등과 같은 웹 관련 구성을 담당합니다.

### getServletMappings()

- 이 메소드는 `DispatcherServlet`이 매핑될 URL 패턴을 정의합니다. 여기서는 모든 요청(`"/"`)을 `DispatcherServlet`이 처리하도록 설정되어 있습니다. 이 설정으로 인해 애플리케이션의 모든 HTTP 요청이 Spring MVC 컨트롤러로 라우팅됩니다.

### getServletFilters()

- 이 메소드는 애플리케이션에 필요한 서블릿 필터를 구성합니다. `CharacterEncodingFilter`는 요청과 응답에 대한 캐릭터 인코딩을 "UTF-8"로 설정하여 문자 인코딩 문제를 방지합니다. 이 필터는 모든 요청과 응답에서 UTF-8 인코딩을 강제하여 문자열 데이터가 올바르게 처리되고 전송되도록 합니다.


> 이후 Mapper 와, Controller, Model, Interceptor 클래스 생성 

# Interceptor

```java
package com.ssafy.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


@Component
public class WriteInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		if (request.getSession().getAttribute("userInfo") == null) {
			response.sendRedirect("/Movie");
			return false;
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// 요청 처리 후 실행할 로직 (선택적)
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// 완료 후 정리 로직 (선택적)
	}

}

```

**[[#ServletConfig.java]]에서 설정한 인터셉터 클래스 정의한 URL에 접근 시 session에 userInfo가 없다면 /Movie로 리다이렉트**

# Controller
```java
package com.ssafy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.ssafy.mapper.BoardMapper;
import com.ssafy.mapper.MemberMapper;
import com.ssafy.model.Board;
import com.ssafy.model.Member;

import jakarta.servlet.http.HttpSession;

@Controller
public class MainController {
	private BoardMapper boardMapper;
	private MemberMapper memberMapper;

	@Autowired
	public MainController(BoardMapper boardMapper, MemberMapper memberMapper) {
		this.boardMapper = boardMapper;
		this.memberMapper = memberMapper;

	}

	@GetMapping("/")
	public String index(Model model) {
		List<Board> boards = boardMapper.getAllBoards();
		System.out.println(boards);
		model.addAttribute("boards", boards);
		return "index";
	}
	
	@GetMapping("/board/write")
	public String boardWrite(Model model, HttpSession session) {
		return "write";
	}

	@PostMapping("/user/login")
	public String login(HttpSession session, Model model, String userId, String password) {
		try {
			Member loggedUser = memberMapper.findUserById(userId);
			
			System.out.println(loggedUser);
			if (loggedUser != null) {
				session.setAttribute("userInfo", loggedUser);
				
				return "redirect:/";
			} else {
				System.out.println("검색된 유저가 없습니다!!!!!!!!!!!!!");
				model.addAttribute("error", "Invalid username or password");
				return "index";
			}
		} catch (Exception e) {
			model.addAttribute("error", "System error: " + e.getMessage());
			return "index";
		}
	}

	@GetMapping("/user/logout")
	public String logout(HttpSession session, Model model, String userId, String password) {
		session.removeAttribute("userInfo");
		return "redirect:/index";
	}
}
```

이후 다른 Class 작성
