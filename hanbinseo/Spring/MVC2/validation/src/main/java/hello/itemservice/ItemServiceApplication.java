package hello.itemservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.validation.Validator;

import hello.itemservice.web.validation.ItemValidator;

@SpringBootApplication
public class ItemServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ItemServiceApplication.class, args);
	}

	// @Override
	// public Validator getValidator(){
	// 	return new ItemValidator();
	// }
}
