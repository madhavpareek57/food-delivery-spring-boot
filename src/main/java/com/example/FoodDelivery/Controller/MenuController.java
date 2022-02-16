package com.example.FoodDelivery.Controller;


import com.example.FoodDelivery.Models.Menus;
import com.example.FoodDelivery.Repositories.MenuItemRepository;
import com.example.FoodDelivery.Repositories.menuRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "menu")
public class MenuController {
    @Autowired
    private final menuRepository mr;

    @Autowired
    private final MenuItemRepository mir;

    Logger logger = LogManager.getLogger(MenuController.this);

    public MenuController(menuRepository mr, MenuItemRepository mir) {
        this.mr = mr;
        this.mir = mir;
    }

    @GetMapping("view_menu")
    public Iterable<Menus> viewMenu(){
        logger.info("returning all menus");
        return mr.findAll();
    }

}
