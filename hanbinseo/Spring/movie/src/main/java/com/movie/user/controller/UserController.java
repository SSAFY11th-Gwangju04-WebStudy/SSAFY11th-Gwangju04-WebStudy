package com.movie.user.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.movie.user.model.UserDto;
import com.movie.user.model.service.UserService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/join")
	public String join() {
		return "user/join";
	}

	@GetMapping("/{userid}")
	@ResponseBody
	public String idCheck(@PathVariable("id") String id) throws Exception {
		int cnt = userService.idCheck(id);
		return cnt + "";
	}

	@PostMapping("/join")
	public String join(UserDto userDto, Model model) {
		try {
			userService.joinUser(userDto);
			return "redirect:/user/login";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "회원가입 문제 발생");
			return "error/error";
		}
	}

	@GetMapping("/login")
	public String login() {
		return "user/login";
	}

	@PostMapping("/login")
	public String login(@RequestParam Map<String, String> map,
			@RequestParam(name = "saveId", required = false) String saveId, Model model, HttpSession session,
			HttpServletResponse response) {
		try {
			UserDto userDto = userService.loginUser(map);
			if (userDto != null) {
				session.setAttribute("loginUser", userDto);

				Cookie cookie = new Cookie("userId", map.get("id"));
				cookie.setPath("/movie");

				if ("ok".equals(saveId)) {
					cookie.setMaxAge(60 * 60 * 24 * 365 * 40);
				} else {
					cookie.setMaxAge(0);
				}
				response.addCookie(cookie);
				return "redirect:/";
			} else {
				model.addAttribute("msg", "아이디 또는 비밀번호 확인 후 다시 로그인하세요!");
				return "user/login";
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "로그인 문제 발생");
			return "error/error";
		}
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
}
