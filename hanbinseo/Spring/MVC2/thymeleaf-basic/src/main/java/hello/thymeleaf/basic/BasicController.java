package hello.thymeleaf.basic;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.Data;

@Controller
@RequestMapping("/basic")
public class BasicController {
	// 텍스트 출력 기본
	@GetMapping("text-basic")
	public String textBasic(Model model) {
		model.addAttribute("data", "Hello <b>Spring</b>");
		return "basic/text-basic";
	}

	// 텍스트 text, utext
	@GetMapping("text-unescaped")
	public String textUnescaped(Model model) {
		model.addAttribute("data", "Hello <b>Spring</b>");
		return "basic/text-unescaped";
	}

	// 변수 - SpringEL
	@GetMapping("/variable")
	public String variable(Model model) {
		User userA = new User("userA", 10);
		User userB = new User("userB", 20);

		List<User> list = new ArrayList<>();
		list.add(userA);
		list.add(userB);

		Map<String, User> map = new HashMap<>();
		map.put("userA", userA);
		map.put("userB", userB);

		model.addAttribute("user", userA);
		model.addAttribute("users", list);
		model.addAttribute("userMap", map);

		return "basic/variable";
	}

	// 기본 객체들
	@GetMapping("/basic-objects")
	public String basicObjects(
		Model model,
		HttpServletRequest request,
		HttpServletResponse response,
		HttpSession session) {
		session.setAttribute("sessionData", "Hello Session");
		model.addAttribute("request", request);
		model.addAttribute("response", response);
		model.addAttribute("servletContext", request.getServletContext());
		return "basic/basic-objects";
	}
	@Component("helloBean")
	static class HelloBean {
		public String hello(String data) {
			return "Hello " + data;
		}
	}

	// 유틸리티 객체와 날짜
	@GetMapping("/date")
	public String date(Model model){
		model.addAttribute("localDateTime", LocalDateTime.now());
		return "basic/date";
	}

	// URL 링크
	@GetMapping("link")
	public String link(Model model) {
		model.addAttribute("param1", "data1");
		model.addAttribute("param2", "data2");
		return "basic/link";
	}

	// 리터럴
	@GetMapping("/literal")
	public String literal(Model model) {
		model.addAttribute("data", "Spring!");
		return "basic/literal";
	}

	// 연산
	@GetMapping("/operation")
	public String operation(Model model) {
		model.addAttribute("nullData", null);
		model.addAttribute("data", "Spring!");
		return "basic/operation";
	}

	// 속성값 설정
	@GetMapping("/attribute")
	public String attribute(){
		return "basic/attribute";
	}

	@Data
	static class User {
		private String username;
		private int age;

		public User(String username, int age) {
			this.username = username;
			this.age = age;
		}
	}
}
