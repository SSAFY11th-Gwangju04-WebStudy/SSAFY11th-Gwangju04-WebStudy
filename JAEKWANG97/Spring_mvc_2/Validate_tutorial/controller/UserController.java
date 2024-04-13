package Validation.controller;

import Validation.model.User;
import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {
    private final List<User> userList = new ArrayList<>(); // 사용자 정보를 저장하는 리스트

    @GetMapping("/")
    public String index() {
        return "index"; // 메인 페이지의 Thymeleaf 템플릿 반환
    }

    @PostMapping("/create")
    public String createUser(@Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return "userForm"; // 폼 검증 실패 시, userForm으로 돌아가기
        }
        userList.add(user); // 폼 검증 성공 시, 사용자 리스트에 추가
        return "redirect:/users"; // 사용자 리스트 페이지로 리디렉션
    }

    @GetMapping("/form")
    public String showUserForm(Model model) {
        model.addAttribute("user", new User()); // 폼 바인딩을 위해 빈 User 객체를 모델에 추가
        return "userForm"; // userForm.html 뷰 반환
    }
}