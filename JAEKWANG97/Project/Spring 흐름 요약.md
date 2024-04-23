기본 setting

### 1. Spring 기본 설정

- 기존 XML 설정 파일(`web.xml`, `servlet-context.xml`, `root-context.xml`)을 제거하고, Java 기반의 설정으로 전환합니다.
- `pom.xml`에 Maven 설정을 추가하여 `web.xml`이 없어도 오류가 발생하지 않도록 설정합니다.

### 2. Java Configuration 파일 생성

- **RootConfig.java**: 전역 서비스 및 구성 요소의 설정을 담당합니다. `@Configuration` 어노테이션을 사용하여 Spring 컨테이너에 Bean을 등록합니다.
- **WebConfig.java**: `AbstractAnnotationConfigDispatcherServletInitializer`를 상속받아 웹 애플리케이션을 위한 기본 경로 및 설정을 정의합니다.
- **ServletConfig.java**: 웹 MVC 설정을 담당합니다. `@EnableWebMvc`를 사용하여 Spring MVC를 활성화하고, 컨트롤러 스캔, 뷰 리졸버 설정, 정적 리소스 핸들러 등을 구성합니다.

### 3. 데이터베이스 연동

- **JDBC 연결 설정**: 필요한 JDBC 라이브러리를 `pom.xml`에 추가하고, Oracle이나 MySQL과 같은 데이터베이스 드라이버 설정을 합니다.
- **커넥션 풀 설정**: HikariCP를 사용하여 `DataSource` 설정을 추가합니다.

### 4. MyBatis와 Spring 연동

- [[#pom.xml]]에 MyBatis 라이브러리를 추가하고, `SqlSessionFactory`를 설정하여 SQL 세션을 생성할 수 있도록 합니다.
- [[#Mapper]] 인터페이스 및 XML 매퍼 설정을 통해 SQL 쿼리를 관리하고 실행합니다.

### 5. 추가 기능 구현

- **UTF-8 필터 설정**: `WebConfig.java`에 문자 인코딩 필터를 추가하여 요청과 응답에 UTF-8 인코딩을 사용합니다.
- **파일 업로드 설정**: `CommonsMultipartResolver`를 설정하여 파일 업로드 기능을 구현합니다.
# 전체적인 흐름 정리

1. **애플리케이션 시작**:
   - 서블릿 컨테이너(예: Tomcat)가 실행되면서 ==웹 애플리케이션을 초기화==

2. **초기화 클래스 로드(`WebConfig`)**:
   - `WebConfig` 클래스가 `AbstractAnnotationConfigDispatcherServletInitializer`를 확장하여 웹 애플리케이션의 구성을 시작합니다.
   - 서블릿 매핑(`getServletMappings`)과 필터 설정(`getServletFilters`)을 포함한 초기화 설정이 로드됩니다.

3. **루트 컨텍스트 생성(`RootConfig`)**:
   - `WebConfig`에 정의된 `getRootConfigClasses` 메소드를 통해 `RootConfig`가 로드되며, 루트 애플리케이션 컨텍스트가 생성됩니다.
   - 루트 컨텍스트는 애플리케이션 전반에 걸쳐 공유되어야 하는 빈들(서비스, 리포지토리, 데이터 소스 등)을 등록합니다.

4. **서블릿 컨텍스트 생성(`ServletConfig`)**:
   - `WebConfig`에 정의된 `getServletConfigClasses` 메소드를 통해 `ServletConfig`가 로드되며, 서블릿 애플리케이션 컨텍스트가 생성됩니다.
   - 서블릿 컨텍스트는 주로 웹 계층의 컴포넌트(컨트롤러, 뷰 리졸버, 인터셉터 등)를 등록합니다.

5. **요청 처리**:
   - 사용자의 HTTP 요청이 들어오면 `DispatcherServlet`이 해당 요청을 받습니다.
   - `DispatcherServlet`은 요청을 적절한 컨트롤러로 라우팅합니다.
   - 컨트롤러는 비즈니스 로직을 처리하고 결과 데이터와 뷰 이름을 반환합니다.

6. **뷰 렌더링**:
   - 반환된 뷰 이름은 `ViewResolver`에 의해 실제 뷰(예: JSP 파일)로 해석됩니다.
   - 뷰는 모델 데이터를 사용하여 최종적인 HTML 컨텐츠를 생성합니다.

7. **응답 반환**:
   - 생성된 HTML 컨텐츠가 사용자의 웹 브라우저로 응답으로 반환됩니다.
   - 인터셉터가 있다면, 요청 전후로 추가적인 처리를 할 수 있습니다.

8. **애플리케이션 종료**:
   - 웹 애플리케이션이 종료될 때, `ContextLoaderListener`는 루트 애플리케이션 컨텍스트를 정리합니다.


# pom.xml

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

# Mapper

```java
package com.ssafy.mapper;

import com.ssafy.model.Member;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Insert;

import java.util.List;

public interface MemberMapper {
    @Select("SELECT user_id, user_name, user_password, email_id, email_domain, join_date FROM members")
    List<Member> findAll();

    @Insert("INSERT INTO members (user_id, user_name, user_password, email_id, email_domain, join_date) VALUES (#{userId}, #{userName}, #{password}, #{emailId}, #{emailDomain}, #{joinDate})")
    int insert(Member member);

    @Select("SELECT * FROM members WHERE user_id = #{userId}")
    Member findUserById(@Param("userId") String userId);
}

```


