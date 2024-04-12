package hello.itemservice.validation;

import static org.assertj.core.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.validation.DefaultMessageCodesResolver;
import org.springframework.validation.FieldError;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.validation.ObjectError;

public class MessageCodesResolverTest {
	MessageCodesResolver codesResolver = new DefaultMessageCodesResolver();

	@Test
	void messageCodesResolverObject() {
		String[] messageCodes = codesResolver.resolveMessageCodes("required", "item");
		for (String messageCode : messageCodes) {
			System.out.println("messageCode = " + messageCode);
		}

		assertThat(messageCodes).containsExactly("required.item", "required");
	}

	@Test
	void messageCodesResolverField() {
		String[] messageCodes = codesResolver.resolveMessageCodes("required", "item", "itemName", String.class);
		for (String messageCode : messageCodes) {
			System.out.println("messageCode = " + messageCode);
		}
		// bindingResult.rejectValue("itemName", "required");
		// new FieldError("item", "itemName", null, false, messageCodes, null, null );
		assertThat(messageCodes).containsExactly(
			"required.item.itemName",
					"required.itemName",
					"required.java.lang.String",
					"required");
	}
}
