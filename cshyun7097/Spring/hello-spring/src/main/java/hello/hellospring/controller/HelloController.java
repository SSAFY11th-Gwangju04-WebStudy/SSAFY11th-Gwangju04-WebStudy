package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "Spring!!");
        return "hello";
    }

    /*
    - GET 방식으로 넘기면 파라미터를 통해 HTML에 데이터를 Attribute로 넘겨준다.
    - viewResolver를 통해 매핑하여 HTML을 생성하여 View를 보여준다.
    - Static(정적페이지)와 다르게 동적으로 화면을 변환 후 보여줌
     */
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    /*
    - ResponseBody -> Http에서 바디부에 이 데이터를 직접 넣어주겠다를 의미
    - API 방식
    - HTML과 다르게 그대로 화면에 보여준다.
     */
    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;
    }

    /*
    데이터를 클래스로 선언하여 JSON방식으로 넘겨준다 {key : value} 형식
    XML방식은 HTML형식으로 넘기고 매우 무겁기 때문에 최근에는 JSON방식으로 통일됨
     */
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
