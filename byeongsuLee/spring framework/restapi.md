@ pathvariable

```
	@GetMapping("/{userid}")
	@ResponseBody
	public String idCheck(@PathVariable("userid") String userId) throws Exception {
		logger.debug("idCheck userid : {}", userId);
		int cnt = memberService.idCheck(userId);
		return cnt + "";
	}
```

# open Api

- open api 는 외부데이터(공공 데이터,기상청, 우체국) 를 외부응용프로그램에서 사용할수 있는 것

# Rest Api(표현 상태 전송)

- open ai와 함꺠 거론되는 기술이 rest이며 대부분의 open api는 rest방식으로 지원
- url qeury요청시 크게 3가지 status , header , responsebody 가 전달된다.
- 전달 약속안에 의중을 표현
- Http urll를 통해 자원(resource) 명시 , http method(get,post,put,delete)을 통해 자원을 제어하는 명령 내리는 방식의 아키텍처
- 하나의 URI는 하나의 고유한 리소스를 대표하도록 설계된다는 개념에 전송방식을 결합해서 원하는 작업을 지정한다.
- 모든 PUT 일부 PATCH

## 기존 SERVICE와 REST service 차이점

- 기존 서비스 : 서비스 처리 후 플랫폼에 맞는 view 전달
- rest service : 서비스 처리 후 json/xml 형식의 data만 전달

# Rest

- 자원을 표현할때 collection 과 document 사용
    - http://www.ssafy.com/sports/baseball/palyers/31
- 가장 큰 단점은 어떻게 사용하라는지 표준이 없음
    - 하이폰은 사용가능하지만 언더바는 사용하지않는다
    - 대문자 사용은 하지않는다.(대소문자 구분)
    - uri 마지막에 슬래스 / 를 사용하지 않는다.

jackson library

- 우리가 만드는 list,dto 객체를 json 으로 만들어 준다.
- json
- xml

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/cd4c5c0a-1563-4707-afa2-6b081ed69d45/7c260724-2467-41e7-ad06-2816aaad35ff/Untitled.png)

# Rest Annotation

@RestController 

- controller가 rest 방식을 처리하기 위한 것임을 명시.

@ responseBody 

- jsp 같은 뷰로 전달되는 것이 아니라 데이터 자체를 전달

@PathVariable 

- URL 경로에 있는 값을 파라미터로 추출

@CrossOrigin 

- Ajax의 크로스 도메인 문제를 해결

@RequestBody

- JSON 데이터를 원하는 타입으로 바인딩

/assets/** 

- assets/ 뒤에 모든것

```
<resources mapping="/assets/**" location="/resources/assets/" />
```

css 적용 안될때

- ctrl + f5

회원목록 crud 비동기 처리

- crud시 list.html 그대로
- 동작방식
    - 회원목록 클릭 → list.html 로딩 → controller → service → controller → json → list.html

*@ResponseBody*

- 데이터를 전송하겠다.
- javckson-databind가 있어서 json으로 넘어간다.

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/cd4c5c0a-1563-4707-afa2-6b081ed69d45/18743096-10e6-43f0-9f41-aace17dc8e66/Untitled.png)

```
@ResponseBody
	@RequestMapping(value = "/user", method = RequestMethod.GET, headers = { "Content-type=application/json" })
	public List<MemberDto> userList() throws Exception {
		List<MemberDto> list = memberService.listMember(null);
		logger.debug("회원목록 : {}", list);
		return list;
//        return memberService.listMember();
	}
```

@ RestController

- 이걸 붙이면 모든 return 값이 json으로 된다.
- = @ controller + @*ResponseBody* 붙인거랑 똑같다.

@RequestBody

- 클라이언트 쪽에서의 json 데이터를 member dto로 맵핑해줌

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/cd4c5c0a-1563-4707-afa2-6b081ed69d45/c674eb98-b74a-46f0-bab5-dc67c22977fe/Untitled.png)

## url qeury요청시 크게 3가지 status , header , responsebody

```
@GetMapping(value = "/user")
	// 와이드 카드하는경우에 경우마다 list,null,string이기때문이다.
	public ResponseEntity<?> userList() {
		logger.debug("userList call");
		try {
			List<MemberDto> list = memberService.listMember(null);
			if(list != null && !list.isEmpty()) {
				//리스트가 있으면 list와 상태값 넘기기
				return new ResponseEntity<List<MemberDto>>(list, HttpStatus.OK);
//				return new ResponseEntity<List<MemberDto>>(HttpStatus.NOT_FOUND);
			} else {
				// 없으면 값은 없고 상태값만 주기
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			//에러발생시 string 넘기기 , 상태
			return exceptionHandling(e);
		}
		
	}
```

# 오전
