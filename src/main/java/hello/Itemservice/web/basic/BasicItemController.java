package hello.Itemservice.web.basic;

import hello.Itemservice.domain.item.Item;
import hello.Itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {

    private final ItemRepository itemRepository;

    @GetMapping
    public String items(Model model){
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items",items);
        return "basic/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable Long itemId, Model model){
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item",item);
        return "basic/item";
    }

    @GetMapping("/add")
    public String addForm(){
        return "basic/addForm";
    }
    //@PostMapping("/add")
    public String addItem1(@ModelAttribute Item item, Model model){
        Item savedItem = itemRepository.save(item);
        model.addAttribute("item",item);
        return "/basic/item";
    }

    //@PostMapping("/add")
    public String addItem2(@ModelAttribute("item") Item item){
        Item savedItem = itemRepository.save(item);
        return "/basic/item";
    }

    //@PostMapping("/add")
    public String addItem3(@ModelAttribute Item item, Model model){
        Item savedItem = itemRepository.save(item);
       // model.addAttribute("item",item); // 실제 이 이름이 있는 것과 동일한다. 위의 Model은 ModelAttribute에서 자동 생성되므로 생략이 가능하다.
        return "/basic/item";
    }

    @PostMapping("/add")
    public String addItem4(Item item){
        Item savedItem = itemRepository.save(item);
        return "/basic/item";
    }

    @GetMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, Model model){
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item",item);
        return "basic/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String editSave(@PathVariable Long itemId, @ModelAttribute Item item, Model model){
        itemRepository.update(itemId,item);
        return "redirect:/basic/items/"+item.getId();
    }

    @PostConstruct
    public void init(){
        itemRepository.save(new Item("itemA",10000,10));
        itemRepository.save(new Item("itemB",20000,20));
        itemRepository.save(new Item("itemC",30000,30));
    }
}
