package com.example.FoodDelivery.Controller;


import com.example.FoodDelivery.Models.MenuItem;
import com.example.FoodDelivery.Models.Menus;
import com.example.FoodDelivery.Models.Restaurants;
import com.example.FoodDelivery.Repositories.MenuItemRepository;
import com.example.FoodDelivery.Repositories.RestRepositories;
import com.example.FoodDelivery.Repositories.menuRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/menuItems")
public class MenuItemController {
    private final MenuItemRepository mir;
    private final menuRepository mr;
    private final RestRepositories restRepositories;

    public MenuItemController(MenuItemRepository mir, menuRepository mr, RestRepositories restRepositories) {
        this.mir = mir;
        this.mr = mr;
        this.restRepositories = restRepositories;
    }
    Logger logger = LogManager.getLogger(MenuItemController.class);

    @PostMapping("add_items")
    @ResponseBody
    public String addItem(@RequestBody Iterable<MenuItem> menuItem, @RequestParam Integer menu_id,@RequestParam String name){
        logger.info("items added in given menuItem list");
        Example<Restaurants> example = Example.of(new Restaurants(name));
        Iterable<Restaurants> restaurants = restRepositories.findAll(example);
        Integer menuID;
        for(Restaurants rest: restaurants){
            menuID = rest.getRest_id();
        }

        menuItem.forEach((element) -> {
            element.setMenu(mr.findById(menu_id).orElse(new Menus()));
        });
//        mit.setMenu(mr.findById(menu_id).orElse(new Menus()));
        mir.saveAll(menuItem);
//        mir.save(mit);
        return "Items added";
    }

    @DeleteMapping("delete_item")
    public String DeleteItem(@RequestParam Integer id){
        logger.info("deleting item of id " + id);
        mir.deleteById(id);

        return "item deleted";
    }

    @GetMapping("view_items")
    public Iterable<MenuItem> viewItems(){
        return mir.findAll();
    }

}
