# 프론트 파일만 가져와 백엔드 구현

```java
package Mvc1myItemService.controller;

import Mvc1myItemService.dto.Item;
import Mvc1myItemService.repo.itemRepository;
import jakarta.servlet.annotation.WebServlet;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@WebServlet("/basic/items")
@Controller
@RequiredArgsConstructor
public class mainController {

    private final itemRepository repository;

    @GetMapping
    public String view(Model model) {
        List<Item> list = repository.findAll();
        model.addAttribute("items", list);
        return "basic/items";
    }

    @GetMapping("/{itemId}")
    public String detail(@PathVariable long itemId, Model model) {
        Item item = repository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/item";
    }

    @GetMapping("/add")
    public String add() {
        return "basic/addFrom";
    }

    @PostMapping("/add")
    public String insert(@ModelAttribute Item item, Model model) {
        // Item -> item 으로 가져₩
        repository.save(item);
        return "basic/item";
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable long itemId, Model model) {
        model.addAttribute(repository.findById(itemId));
        return "/basic/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@ModelAttribute Item item, @PathVariable long itemId) {
        repository.update(itemId, item);
        return "redirect:/basic/items/{itemId}";
    }

}
```

! 내일 어노태이션으로 get, post 받는 것 정리하기
