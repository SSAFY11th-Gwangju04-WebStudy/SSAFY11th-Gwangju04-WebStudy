### REST (Representational State Transfer)
<hr>
- REST는 하나의 URI는 하나의 고유한 리소스를 대표하도록 설계된다는 개념에 전송 방식을 결합해서 원하는 작업을 지정함
  `URI + GET/POST/PUT/DELETE`
- 웹의 장점을 최대한 활용할 수 있는 아키텍쳐로써 REST를 발표
- HTTP URI를 통해 제어할 자원을 명시, HTTP Method를 통해 해당 자원을 제어하는 명령을 내리는 방식의 아키텍쳐

### REST 구성
<hr>
- 자원 (Resource) : URI
	- 잘 표현된 HTTP URI로 리소스를 정의
- 행위 (Verb) : HTTP Method
	- HTTP Method로 리소스에 대한 행위를 정의
- 표현 (Representations)
	- 리소스는 JSON, XML과 같은 여러 가지 언어로 표현 가능

### 기존 Service와 REST Service
<hr>
- 기존 Service
	- 요청에 대한 처리를 한 후 가공된 data를 이용하여 특정 플랫폼에 적합한 형태의 View로 만들어서 반환
- REST Service
	- data 처리만 하거나, 처리 후 반환될 data가 있다면 JSON이나 XML 형식으로 전달
	- View에 대해서 신경 쓸 필요가 없음 -> Open API에서 많이 사용
![[Pasted image 20240423210253.png]]

### @RestController와 @Controller의 차이
<hr>
- @Controller
	- 주로 뷰를 반환하는 컨트롤러를 나타내는 어노테이션
	- 메서드에서 반환되는 값은 뷰의 이름으로 해석됨
- @RestController
	- RESTful 웹 서비스를 개발할 때 사용되는 어노테이션
	- @Controller와 @ResponseBody가 합쳐진 형태
	- 메서드에서 반환되는 객체가 자동으로 JSON이나 XML 형식으로 변환되어 HTTP 응답 본문에 포함됨

### @RestController
<hr>
- @ResponseBody
	- 메서드에 적용되어 반환되는 값이 HTTP 응답 본문에 직접 쓰여지도록 함
- @RequestBody
	- HTTP 요청 본문에 포함된 데이터를 자바 객체로 변환하여 메서드 파라미터로 받을 때 사용
- @PathVariable
	- URL 경로에 포함된 변수 값을 메서드 파라미터로 받을 때 사용
- @RequestParam
	- HTTP 요청 파라미터를 메서드 파라미터로 받을 때 사용