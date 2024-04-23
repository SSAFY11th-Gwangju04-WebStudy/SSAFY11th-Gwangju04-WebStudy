# 로그인 처리1 - 쿠키, 세션

**package 구조** 
도메인 과 web 을 분리! (web 은 domain 의존 의존관계를 단방향으로) domain 은 
**web 참조하면 안됨!**
 - hello.login
	 - domain 
		 - item
		 - member
		 - login 
	- web
		- item 
		- member 
		- login

**Optional**
java8 문법, 값이 null 로 반환해야 하는 경우 Optrional 에서 찾아서 사용함


## 로그인 처리 - 쿠키 사용
-> 쿠키에 시간정보 주지 않으면 세션쿠키(브라우저 종료시 사망)
```java
// 로그인 성공 처리 TODO  
// 쿠키 처리  
Cookie idCookie = new Cookie("memberId", String.valueOf(loginMember.getId()));  
response.addCookie(idCookie);  
return "redirect:/";
```

받기
```java
@GetMapping("/")  
public String homeLogin(@CookieValue(name = "memberId", required = false) Long memberId, Model model) {  
    if (memberId == null) {  
        return "home";  
    }  
    Member loginMember = memberRepository.findById(memberId);  
    if (loginMember == null) {  
        return "home";  
    }  
    model.addAttribute("member", loginMember);  
    return "members/loginHome";  
}  
  
@PostMapping("/logout")  
public String logout(HttpServletResponse response) {  
    expireCookie(response, "memberId");  
    return "redirect:/";  
}  
  
private static void expireCookie(HttpServletResponse response, String cookieName) {  
    Cookie cookie = new Cookie(cookieName, null);  
    cookie.setMaxAge(0);  
    response.addCookie(cookie);  
}
```


### 해당 방식의 문제점

1. 쿠키는 위변조 가능 (유저 to Server 이기 때문)
2. 쿠키에 보관된 정보는 유출이 가능하다 (개인정보, 민감한 정보 등)
3. 쿠키가 한번 유출되면 평생 사용이 가능하다...
*대안*
1. 쿠키에 중요한값 X, 임의의 토큰(랜덤값) 으로 매핑하여 사용
2. 토큰의 유지 시간 줄이고 임의의 값 넣어도 예상 불가능하도록 만들기
