## board.xml의 data 정보에 오타가 난다면

- board3_ mybatis ⇒ DB에 접근하는 순간에 에러가 발생
    - SqlMapConfig
- board4_ spring-mybatis ⇒ 서버를 구동할 수 없음
    - root-context.xml에서 mapper를 로딩할 수 없기 때문에
    
    ```markdown
    org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'boardServiceImpl' defined in file [C:\minju\framework\SSAFY_spring_class\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\board4-mybatis-spring\WEB-INF\classes\com\ssafy\board\model\service\BoardServiceImpl.class]: Unsatisfied dependency expressed through constructor parameter 0: Error creating bean with name 'boardMapper' defined in file [C:\minju\framework\SSAFY_spring_class\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\board4-mybatis-spring\WEB-INF\classes\com\ssafy\board\model\mapper\BoardMapper.class]: Unsatisfied dependency expressed through bean property 'sqlSessionFactory': Error creating bean with name 'sqlSessionFactoryBean' defined in ServletContext resource [/WEB-INF/spring/root-context.xml]: Failed to parse mapping resource: 'file [C:\minju\framework\SSAFY_spring_class\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\board4-mybatis-spring\WEB-INF\classes\mapper\board.xml]'
    	at org.springframework.beans.factory.support.ConstructorResolver.createArgumentArray(ConstructorResolver.java:798)
    	at org.springframework.beans.factory.support.ConstructorResolver.autowireConstructor(ConstructorResolver.java:237)
    	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.autowireConstructor(AbstractAutowireCapableBeanFactory.java:1354)
    	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBeanInstance(AbstractAutowireCapableBeanFactory.java:1191)
    	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:561)
    	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:521)
    	at org.springframework.beans.factory.support.AbstractBeanFactory.lambda$doGetBean$0(AbstractBeanFactory.java:325)
    	at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:234)
    	at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:323)
    	at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:199)
    	at org.springframework.beans.factory.support.DefaultListableBeanFactory.preInstantiateSingletons(DefaultListableBeanFactory.java:975)
    	at org.springframework.context.support.AbstractApplicationContext.finishBeanFactoryInitialization(AbstractApplicationContext.java:959)
    	at org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:624)
    	at org.springframework.web.context.ContextLoader.configureAndRefreshWebApplicationContext(ContextLoader.java:394)
    	at org.springframework.web.context.ContextLoader.initWebApplicationContext(ContextLoader.java:274)
    	at org.springframework.web.context.ContextLoaderListener.contextInitialized(ContextLoaderListener.java:102)
    	at org.apache.catalina.core.StandardContext.listenerStart(StandardContext.java:4438)
    	at org.apache.catalina.core.StandardContext.startInternal(StandardContext.java:4876)
    	at org.apache.catalina.util.LifecycleBase.start(LifecycleBase.java:171)
    	at org.apache.catalina.core.ContainerBase$StartChild.call(ContainerBase.java:1332)
    	at org.apache.catalina.core.ContainerBase$StartChild.call(ContainerBase.java:1322)
    	at java.base/java.util.concurrent.FutureTask.run(FutureTask.java:264)
    	at org.apache.tomcat.util.threads.InlineExecutorService.execute(InlineExecutorService.java:75)
    	at java.base/java.util.concurrent.AbstractExecutorService.submit(AbstractExecutorService.java:145)
    	at org.apache.catalina.core.ContainerBase.startInternal(ContainerBase.java:866)
    	at org.apache.catalina.core.StandardHost.startInternal(StandardHost.java:845)
    	at org.apache.catalina.util.LifecycleBase.start(LifecycleBase.java:171)
    	at org.apache.catalina.core.ContainerBase$StartChild.call(ContainerBase.java:1332)
    	at org.apache.catalina.core.ContainerBase$StartChild.call(ContainerBase.java:1322)
    	at java.base/java.util.concurrent.FutureTask.run(FutureTask.java:264)
    	at org.apache.tomcat.util.threads.InlineExecutorService.execute(InlineExecutorService.java:75)
    	at java.base/java.util.concurrent.AbstractExecutorService.submit(AbstractExecutorService.java:145)
    	at org.apache.catalina.core.ContainerBase.startInternal(ContainerBase.java:866)
    	at org.apache.catalina.core.StandardEngine.startInternal(StandardEngine.java:240)
    	at org.apache.catalina.util.LifecycleBase.start(LifecycleBase.java:171)
    	at org.apache.catalina.core.StandardService.startInternal(StandardService.java:433)
    	at org.apache.catalina.util.LifecycleBase.start(LifecycleBase.java:171)
    	at org.apache.catalina.core.StandardServer.startInternal(StandardServer.java:917)
    	at org.apache.catalina.util.LifecycleBase.start(LifecycleBase.java:171)
    	at org.apache.catalina.startup.Catalina.start(Catalina.java:795)
    	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
    	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
    	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
    	at java.base/java.lang.reflect.Method.invoke(Method.java:568)
    	at org.apache.catalina.startup.Bootstrap.start(Bootstrap.java:347)
    	at org.apache.catalina.startup.Bootstrap.main(Bootstrap.java:478)
    Caused by: org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'boardMapper' defined in file [C:\minju\framework\SSAFY_spring_class\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\board4-mybatis-spring\WEB-INF\classes\com\ssafy\board\model\mapper\BoardMapper.class]: Unsatisfied dependency expressed through bean property 'sqlSessionFactory': Error creating bean with name 'sqlSessionFactoryBean' defined in ServletContext resource [/WEB-INF/spring/root-context.xml]: Failed to parse mapping resource: 'file [C:\minju\framework\SSAFY_spring_class\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\board4-mybatis-spring\WEB-INF\classes\mapper\board.xml]'
    	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.autowireByType(AbstractAutowireCapableBeanFactory.java:1515)
    	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.populateBean(AbstractAutowireCapableBeanFactory.java:1409)
    	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:598)
    	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:521)
    	at org.springframework.beans.factory.support.AbstractBeanFactory.lambda$doGetBean$0(AbstractBeanFactory.java:325)
    	at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:234)
    	at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:323)
    	at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:199)
    	at org.springframework.beans.factory.config.DependencyDescriptor.resolveCandidate(DependencyDescriptor.java:254)
    	at org.springframework.beans.factory.support.DefaultListableBeanFactory.doResolveDependency(DefaultListableBeanFactory.java:1443)
    	at org.springframework.beans.factory.support.DefaultListableBeanFactory.resolveDependency(DefaultListableBeanFactory.java:1353)
    	at org.springframework.beans.factory.support.ConstructorResolver.resolveAutowiredArgument(ConstructorResolver.java:907)
    	at org.springframework.beans.factory.support.ConstructorResolver.createArgumentArray(ConstructorResolver.java:785)
    	... 45 more
    Caused by: org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'sqlSessionFactoryBean' defined in ServletContext resource [/WEB-INF/spring/root-context.xml]: Failed to parse mapping resource: 'file [C:\minju\framework\SSAFY_spring_class\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\board4-mybatis-spring\WEB-INF\classes\mapper\board.xml]'
    	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.initializeBean(AbstractAutowireCapableBeanFactory.java:1773)
    	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:599)
    	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:521)
    	at org.springframework.beans.factory.support.AbstractBeanFactory.lambda$doGetBean$0(AbstractBeanFactory.java:325)
    	at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:234)
    	at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:323)
    	at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:199)
    	at org.springframework.beans.factory.config.DependencyDescriptor.resolveCandidate(DependencyDescriptor.java:254)
    	at org.springframework.beans.factory.support.DefaultListableBeanFactory.doResolveDependency(DefaultListableBeanFactory.java:1443)
    	at org.springframework.beans.factory.support.DefaultListableBeanFactory.resolveDependency(DefaultListableBeanFactory.java:1353)
    	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.autowireByType(AbstractAutowireCapableBeanFactory.java:1500)
    	... 57 more
    Caused by: java.io.IOException: Failed to parse mapping resource: 'file [C:\minju\framework\SSAFY_spring_class\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\board4-mybatis-spring\WEB-INF\classes\mapper\board.xml]'
    	at org.mybatis.spring.SqlSessionFactoryBean.buildSqlSessionFactory(SqlSessionFactoryBean.java:700)
    	at org.mybatis.spring.SqlSessionFactoryBean.afterPropertiesSet(SqlSessionFactoryBean.java:577)
    	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.invokeInitMethods(AbstractAutowireCapableBeanFactory.java:1820)
    	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.initializeBean(AbstractAutowireCapableBeanFactory.java:1769)
    	... 67 more
    Caused by: org.apache.ibatis.builder.BuilderException: Error parsing Mapper XML. The XML location is 'file [C:\minju\framework\SSAFY_spring_class\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\board4-mybatis-spring\WEB-INF\classes\mapper\board.xml]'. Cause: org.apache.ibatis.builder.BuilderException: Error resolving class. Cause: org.apache.ibatis.type.TypeException: Could not resolve type alias 'boardto'.  Cause: java.lang.ClassNotFoundException: Cannot find class: boardto
    	at org.apache.ibatis.builder.xml.XMLMapperBuilder.configurationElement(XMLMapperBuilder.java:127)
    	at org.apache.ibatis.builder.xml.XMLMapperBuilder.parse(XMLMapperBuilder.java:100)
    	at org.mybatis.spring.SqlSessionFactoryBean.buildSqlSessionFactory(SqlSessionFactoryBean.java:698)
    	... 70 more
    Caused by: org.apache.ibatis.builder.BuilderException: Error resolving class. Cause: org.apache.ibatis.type.TypeException: Could not resolve type alias 'boardto'.  Cause: java.lang.ClassNotFoundException: Cannot find class: boardto
    	at org.apache.ibatis.builder.BaseBuilder.resolveClass(BaseBuilder.java:103)
    	at org.apache.ibatis.builder.xml.XMLMapperBuilder.resultMapElement(XMLMapperBuilder.java:269)
    	at org.apache.ibatis.builder.xml.XMLMapperBuilder.resultMapElement(XMLMapperBuilder.java:261)
    	at org.apache.ibatis.builder.xml.XMLMapperBuilder.resultMapElements(XMLMapperBuilder.java:253)
    	at org.apache.ibatis.builder.xml.XMLMapperBuilder.configurationElement(XMLMapperBuilder.java:123)
    	... 72 more
    Caused by: org.apache.ibatis.type.TypeException: Could not resolve type alias 'boardto'.  Cause: java.lang.ClassNotFoundException: Cannot find class: boardto
    	at org.apache.ibatis.type.TypeAliasRegistry.resolveAlias(TypeAliasRegistry.java:128)
    	at org.apache.ibatis.builder.BaseBuilder.resolveAlias(BaseBuilder.java:132)
    	at org.apache.ibatis.builder.BaseBuilder.resolveClass(BaseBuilder.java:101)
    	... 76 more
    Caused by: java.lang.ClassNotFoundException: Cannot find class: boardto
    	at org.apache.ibatis.io.ClassLoaderWrapper.classForName(ClassLoaderWrapper.java:226)
    	at org.apache.ibatis.io.ClassLoaderWrapper.classForName(ClassLoaderWrapper.java:103)
    	at org.apache.ibatis.io.Resources.classForName(Resources.java:322)
    	at org.apache.ibatis.type.TypeAliasRegistry.resolveAlias(TypeAliasRegistry.java:124)
    ```
    

## Open API

- 프로그래밍에서 사용할 수 있는 개방되어 있는 상태의 interface
- naver, kakao등 포털 서비스 사이트나 통계청, 기상청, 우체국 등과 같은 관공서, 데이터 포털이 가지고 있는 데이터를 외부 응용 프로그램에서 사용할 수 있도록 Open API를 제공하고 있다.
- Open API와 함께 거론되는 기술이 REST이며, 대부분의 Open API는 REST 방식으로 지원

## REST(Representational State Transfer)

- 하나의 URI는 하나의 고유한 리소스를 대표하도록 설계된다는 개념에 전송방식을 결합해서 원하는 작업을 지정
    - URI + (GET / POST / PUT / DELETE)
- 웹의 장점을 최대한 활용할 수 있는 아키텍처로써 REST를 발표
- HTTP URI를 통해 제어할 자원을 명시하고, HTTP Method(GET, POST, PUT, DELETE)를 통해 해당 자원을 제어하는 명령을 내리는 방식의 아키텍처

## REST 구성

1. 자원 - URI
2. 행위 - HTTP Method
3. 표현

⇒ 잘  표현된 HTTP URI로 리소스를 정의하고 HTTP method로 리소스에 대한 행위를 정의한다. 리소스는 JSON, XML과 같은 여러가지 언어로 표현할 수 있다.

## 기존 Service와 REST Service

- 기존: 요청에 대한 처리를 한 후 가공된 data를 이용하여 특정 플랫폼에 적합한 형태의 View로 만들어서 반환
- REST Service: data 처리만 한다거나, 처리 후 반환될 data가 있다면 JSON이나 XML 형식으로 전달. View에 대해서는 신경 쓸 필요가 없다. >> 이러한 이유로 Open API에서 많이 사용

## REST

- 기존의 전송방식과는 달리 서버는 요청으로 받은 리소스에 대해 순수한 데이터를 전송한다.
- 기존의 GET/POST 외에 PUT, DELETE 방식을 사용하여 리소스에 대한 CRUD를 처리할 수 있다.
- HTTP URI를 통해 제어할 자원을 명시하고, HTTP METHOD(GET/POST/PUT/DELETE)를 통해 해당 자원을 제어하는 명령을 내리는 방식의 아키텍처
- 자원을 표현할 때 Collection과 Document를 사용
- 가장 큰 단점은 딱 정해진 표준이 없어 ‘다들 이렇게 쓰더라’ 정도의 암묵적인 표준만 정해져 있다.
    - 하이픈(-)은 사용 가능하지만 언더바(_)는 사용하지 않는다. (가독성)
    - 특별한 경우를 제외하고 대문자 사용은 하지 않는다. (대소문자 구분을 하기 때문)
    - URI 마지막에 /를 사용하지 않는다.
    - /로 계층 관계를 나타낸다.
    - 확장자가 포함된 파일 이름을 직접 포함시키지 않는다.
    - URI는 명사를 사용한다.
    

## 기존의 웹 접근 방식과 REST API 방식의 차이점

![Untitled](https://github.com/MJ-Kor/SSAFY11th-Gwangju04-WebStudy/blob/main/MJ-Kor/SSAFYLectures/Spring/imgs/rest1.png)

- 기존의 블로그등은 GET과 POST만으로 자원에 대한 CRUD를 처리하며, URI는 액션을 나타냄
- REST로 변경할 경우 4가지 method를 모두 사용하여 CRUD를 처리하며, URI는 제어하려는 자원을 나타냄

## REST 관련 Annotation

![Untitled](https://github.com/MJ-Kor/SSAFY11th-Gwangju04-WebStudy/blob/main/MJ-Kor/SSAFYLectures/Spring/imgs/rest2.png)

## REST를 왜 사용?

- 웹 상에서 데이터를 전송하고 처리하기 위함

## REST, RESTFUL, REST API 의 차이

- REST - HTTP URI를 통해 제어할 자원을 명시하고, HTTP METHOD(GET/POST/PUT/DELETE)를 통해 해당 자원을 제어하는 명령을 내리는 방식을 모아둔 인터페이스
- RESTful - REST 규칙을 잘 준수한 애들
    - 만약 POST로 자원을 지우는 기능을 구현했다? → 이건 RESTful하지 못함
- REST API - REST 서비스를 구현해놓은 것

## REST 구성 요소

1. URI - 자원 구분
2. METHOD -  자원에 대한 행위 구분
    1. GET: 자원 조회
    2. POST: 자원 생성
    3. PUT: 자원 수정 
    4. PATCH: 자원 수정
    5. DELETE: 자원 삭제
3. 응답 자원은 XML, JSON, RSS 형태의 포맷으로 표현

## REST 장점

- client와 server의 분리 개발을 할 수가 있다.
- 별도의 서버 구축이 필요없다.

## Client와 Server의 분리 개발을 하면?

- Front server가 생길 수 있음
- Session이 가장 큰 문제
