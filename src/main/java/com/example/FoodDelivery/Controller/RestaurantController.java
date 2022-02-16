package com.example.FoodDelivery.Controller;


import com.example.FoodDelivery.Models.Cart;
import com.example.FoodDelivery.Models.MenuItem;
import com.example.FoodDelivery.Models.Menus;
import com.example.FoodDelivery.Models.Restaurants;
import com.example.FoodDelivery.Repositories.MenuItemRepository;
import com.example.FoodDelivery.Repositories.RestRepositories;
import com.example.FoodDelivery.Repositories.menuRepository;
import com.example.FoodDelivery.global;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/restaurant")
public class RestaurantController {

    Logger logger = LogManager.getLogger(RestaurantController.class);

    private final RestRepositories restRepositories;
    private final MenuItemRepository menuItemRepository;

    @Autowired
    private final menuRepository mr;


    @Autowired
    public RestaurantController(menuRepository mr, RestRepositories restRepositories, MenuItemRepository menuItemRepository) {
        this.restRepositories = restRepositories;
        this.mr = mr;
        this.menuItemRepository = menuItemRepository;
    }

    @PostMapping("add_restaurant")
    @ResponseBody
    public String addMenu(@RequestParam String name, @RequestParam String city){
        logger.info("menu and restaurant added");
        Restaurants restaurants = new Restaurants();
        restaurants.setRest_name(name);
        restaurants.setCity(city);
        Menus menu = new Menus();
        menu.setRestaurants(restaurants);
        restaurants.setMenu(menu);
        mr.save(menu);
        restRepositories.save(restaurants);
        return "restaurant and menu added";
    }


    @GetMapping("view_restaurant")
    public ArrayList<List<String>> viewRestaurants(){
        Iterable<Restaurants> restList = restRepositories.findAll();
        ArrayList<List<String>> req_list = new ArrayList<>();
        for (Restaurants restaurants : restList) {
            String name = restaurants.getRest_name();
            String city = restaurants.getCity();
            List<String> rest = new ArrayList<>();
            rest.add(name);
            rest.add(city);
            req_list.add(rest);

        }

        return req_list;

    }





    @RequestMapping("/{id}")
    public Restaurants findRestaurantById(@PathVariable("id") Integer id) {
        return restRepositories.findById(id).orElse(new Restaurants());
    }
//
    @RequestMapping()
    public Iterable<Restaurants> getRestaurants() {
        logger.info("Fetch all: " + restRepositories.findAll());
        return restRepositories.findAll();
    }

    @GetMapping("/{name}")
    public Iterable<Restaurants> getRestaurantsByName(@PathVariable("name") String name){
        Example<Restaurants> example = Example.of(new Restaurants(name));
        return restRepositories.findAll(example);

//        restRepositories.findAll()
    }

    @GetMapping("/city")
    public Iterable<Restaurants> getRestaurantByCity(@RequestParam("city") String city){
        Example<Restaurants> example = Example.of(new Restaurants(null,city));
        return restRepositories.findAll(example);
    }

    @GetMapping("/view_restaurant/{name}")
    public ArrayList<List<String>> getMenuItem(@PathVariable("name") String name){
        Iterable<Restaurants> rest = getRestaurantsByName(name);
        Iterable<MenuItem> menuItems = new ArrayList<>();
        for (Restaurants restaurant: rest) {
            Menus menu = restaurant.getMenu();
            menuItems = menu.getItems();

        }

        ArrayList<List<String>> req_list = new ArrayList<>();
        for(MenuItem mit: menuItems){
            List<String> itemsList = new ArrayList<>();
            itemsList.add(mit.getItem_name());
            itemsList.add(Integer.toString(mit.getCost()));
            req_list.add(itemsList);

        }

        return req_list;

    }

    @PostMapping("/view_restaurant/{name}/{item_name}")
    public String add_to_cart(@PathVariable("name") String name, @PathVariable("item_name") String item_name){
        Iterable<Restaurants> rest = getRestaurantsByName(name);
        Integer id = 0;
        for(Restaurants restaurants:rest){
            id = restaurants.getRest_id();
        }
        Example<MenuItem> example = Example.of(new MenuItem(null,item_name,null));
        Iterable<MenuItem> menuItem = menuItemRepository.findAll(example);
        Integer cost = 0;
        String itemName = new String();
        for(MenuItem menuItem1:menuItem){
            cost = menuItem1.getCost();
            itemName = menuItem1.getItem_name();

        }
        Cart cart = Cart.getInstance();
        cart.setRest_id(id);
        global.cost = global.cost + cost;
        cart.setTotalCost(global.cost);
        List<String> list_item = cart.getMenuItem_name();
        list_item.add(itemName);
        cart.setMenuItem_name(list_item);
        String itemlist = cart.toString();
        return itemlist;

    }




}

//api
//list of rest,menuitems_id,price in cart table generates cart_id
//order api after buy button takes cart id mark order status and payment status,order_id.