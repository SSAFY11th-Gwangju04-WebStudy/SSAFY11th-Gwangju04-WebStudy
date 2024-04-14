package hello.itemservice.validation;

import java.util.Set;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.Test;

import hello.itemservice.domain.item.Item;

public class BeanValidationTest {

	@Test
	void beanValidation(){
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();

		Item item = new Item();

		item.setItemName("   ");
		item.setPrice(0);
		item.setQuantity(10000);

		Set<ConstraintViolation<Item>> violations = validator.validate(item);
		for (ConstraintViolation<Item> violation : violations) {
			System.out.println("violation=" + violation);
			System.out.println("violation.message=" + violation.getMessage());
		}
	}
}
