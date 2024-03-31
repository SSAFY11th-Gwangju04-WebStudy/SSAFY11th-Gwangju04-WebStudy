# 7. 스프링 MVC - 웹페이지 만들기

## 요구사항 분석

#### 상품을 관리할 수 있는 서비스를 만들어보자.
- 상품 도메인 모델
	- 상품 ID 
	- 상품명 
	- 가격
	- 수량
- 상품 관리 기능
	- 상품 목록 
	- 상품 상세
	- 상품 등록 
	- 상품 수정

 
![](https://i.ibb.co/9Hbz23r/2024-03-31-10-04-52.png)


### 상품 도메인 개발
@Data 를 핵심 도메인에 사용하면 좀 위험하다 -> 이상하게 작동할 수도...
그럼 그냥 @Getter, @Setter 만 사용해라

실무에서는 멀티 쓰레드에서 동시 접속시 Map을 사용하면 안됨! -> ConcurrentHashMap<>(); 사용, Long 도 
중복보단 명확성!
테스트 생성 S + C + T

@RequiredArgsConstructor : final이 붙은 변수를 포함하는 생성자 만들어줌
tymeleaf 동작은 th가 대체하는 형식

### PRG Post/Redirect/Get 패턴


![](https://i.ibb.co/MGCb9GF/2024-03-31-12-36-00.png)


- 에러
 [Parameter 0 of constructor in hello.itemservice.domain.web.basic.BasicItemController required a bean of type 'hello.itemservice.domain.item.ItemRepository' that could not be found.]
 -> @Repository 를 안해서 빈을 찾지 못했음

```java
package hello.itemservice.domain.web.basic;  
  
import hello.itemservice.domain.item.Item;  
import hello.itemservice.domain.item.ItemRepository;  
import jakarta.annotation.PostConstruct;  
import lombok.RequiredArgsConstructor;  
import lombok.extern.slf4j.Slf4j;  
import org.springframework.stereotype.Controller;  
import org.springframework.ui.Model;  
import org.springframework.web.bind.annotation.*;  
import org.springframework.web.servlet.mvc.support.RedirectAttributes;  
  
import java.util.List;  
  
@Slf4j  
@Controller  
@RequestMapping("/basic/items")  
@RequiredArgsConstructor  
public class BasicItemController {  
    private final ItemRepository itemRepository;  
  
    @GetMapping  
    public String items(Model model) {  
        List<Item> items = itemRepository.findAll();  
        model.addAttribute("items", items);  
        return "basic/items";  
    }  
  
    @PostConstruct  
    public void init() {  
        itemRepository.save(new Item("itemA", 1000, 10));  
        itemRepository.save(new Item("itemB", 2000, 20));  
    }  
  
    @GetMapping("/{itemId}")  
    public String item(@PathVariable long itemId, Model model) {  
        Item item = itemRepository.findById(itemId);  
        model.addAttribute("item", item);  
        return "basic/item";  
    }  
  
    @GetMapping("/add")  
    public String add() {  
        return "/basic/addForm";  
    }  
  
    //@PostMapping("/add")  
    public String addItem(@RequestParam String itemName, int price, Integer quantity, Model model) {  
        log.debug("itemName={}, price={}, quantity={}", itemName, price, quantity);  
  
        Item item = new Item(itemName, price, quantity);  
        itemRepository.save(item);  
        model.addAttribute("item", item);  
        return "basic/item";  
    }  
  
    //@PostMapping("/add")  
    public String addItemV2(@ModelAttribute("item") Item item, Model model) {  
        itemRepository.save(item);  
        return "basic/item";  
    }  
  
    //@PostMapping("/add")  
    public String addItemV3(@ModelAttribute Item item, Model model) {  
        // Item -> item 으로 가져₩  
        itemRepository.save(item);  
        return "basic/item";  
    }  
  
    //@PostMapping("/add")  
    public String addItemV4(Item item, Model model) {  
        // Item -> item 으로 가져옴  
        itemRepository.save(item);  
        return "basic/item";  
    }  
  
    //@PostMapping("/add")  
    public String addItemV5(Item item, Model model) {  
        // Item -> item 으로 가져옴  
        itemRepository.save(item);  
        return "redirect:/basic/items/" + item.getId();  
    }  
  
    @PostMapping("/add")  
    public String addItemV6(Item item, RedirectAttributes redirectAttributes) {  
        // Item -> item 으로 가져옴  
        Item savedItem = itemRepository.save(item);  
        redirectAttributes.addAttribute("itemId", savedItem.getId());  
        redirectAttributes.addAttribute("status", true);  
        return "redirect:/basic/items/${itemId}";  
    }  
  
    @GetMapping("/{itemId}/edit")  
    public String editForm(@PathVariable long itemId, Model model) {  
        model.addAttribute(itemRepository.findById(itemId));  
        return "/basic/editForm";  
    }  
  
    @PostMapping("/{itemId}/edit")  
    public String edit(@ModelAttribute Item item, @PathVariable long itemId) {  
        itemRepository.update(itemId, item);  
        return "redirect:/basic/items/{itemId}";  
        //return "basic/item";  
    }  
  
}
