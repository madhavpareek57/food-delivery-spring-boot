package com.example.FoodDelivery.Controller;

import com.example.FoodDelivery.Models.Cart;
import com.example.FoodDelivery.Models.MenuItem;
import com.example.FoodDelivery.Repositories.MenuItemRepository;
import com.example.FoodDelivery.global;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("cart")
public class CartController {

    private final MenuItemRepository menuItemRepository;

    public CartController(MenuItemRepository menuItemRepository) {
        this.menuItemRepository = menuItemRepository;
    }

    @GetMapping("/view")
    public String view(){
        Cart cart = Cart.getInstance();
        return cart.toString();
    }

    @DeleteMapping("/delete")
    public void deleteAll(){
        Cart cart = Cart.getInstance();
        cart.setTotalCost(0);
        global.cost = 0;
        cart.setRest_id(null);
        cart.setMenuItem_name(new ArrayList<>());
        global.req_id = global.req_id + 1;
        cart.setId(global.req_id);

    }

    @DeleteMapping("/deleteItem")
    public String deleteItem(@RequestParam String item_name){
        Example<MenuItem> example = Example.of(new MenuItem(null,item_name,null));
        Iterable<MenuItem> menuItem = menuItemRepository.findAll(example);
        Integer cost = 0;
        for(MenuItem menuItem1:menuItem){
            cost = menuItem1.getCost();
        }
        Cart cart = Cart.getInstance();
        List<String> itemlist = cart.getMenuItem_name();
        if (itemlist != null) {
            itemlist.remove(item_name);
            cart.setTotalCost(cart.getTotalCost() - cost);
        }
        else{
            return cart.toString();
        }
        if(itemlist == null){
            cart.setRest_id(null);
            global.req_id = global.req_id + 1;
            cart.setId(global.req_id);
        }

        return cart.toString();
    }



}
