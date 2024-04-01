package hello.itemservice.domain.item;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
// @Getter
// @Setter	Data는 위험할 수 있으므로 잘 알고 사용하기
public class Item {
	private Long id;
	private String itemName;
	private Integer price;
	private Integer quantity;

	public Item() {
	}

	public Item(String itemName, Integer price, Integer quantity) {
		this.itemName = itemName;
		this.price = price;
		this.quantity = quantity;
	}
}
