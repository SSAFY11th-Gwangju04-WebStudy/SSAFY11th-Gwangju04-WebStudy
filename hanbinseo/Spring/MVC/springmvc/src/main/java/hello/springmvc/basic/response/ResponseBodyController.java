package hello.springmvc.basic.response;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import hello.springmvc.basic.HelloData;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
// @Controller
// @ResponseBody
@RestController
public class ResponseBodyController {

	@GetMapping("/responset-body-string-v1")
	public void responseBodyV1(HttpServletResponse response) throws IOException {
		response.getWriter().write("ok");
	}

	@GetMapping("/responset-body-string-v2")
	public ResponseEntity<String> responseBodyV2() throws IOException {
		return new ResponseEntity<>("ok", HttpStatus.OK);
	}

	// @ResponseBody
	@GetMapping("/responset-body-string-v3")
	public String responseBodyV3() {
		return "ok";
	}

	@GetMapping("/response-body-json-v1")
	public ResponseEntity<HelloData> responseBodyJsonV1() {
		HelloData helloData = new HelloData();
		helloData.setUsername("userA");
		helloData.setAge(20);

		return new ResponseEntity<>(helloData, HttpStatus.OK);
	}

	@ResponseStatus(HttpStatus.OK)
	// @ResponseBody
	@GetMapping("/response-body-json-v2")
	public HelloData responseBodyJsonV2() {
		HelloData helloData = new HelloData();
		helloData.setUsername("userA");
		helloData.setAge(20);

		return helloData;
	}
}
