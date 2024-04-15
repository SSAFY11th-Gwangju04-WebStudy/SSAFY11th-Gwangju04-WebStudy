package hello.itemservice.web.validation.form;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import lombok.Data;

@Data
public class ItemSaveForm {
	@NotBlank
	private String itemName;

	@NotNull
	@Range(min = 1000, max = 1000000)
	private Integer price;

	@NotNull
	@Max(value = 9999)
	private Integer quantity;
}
