package com.example.FoodDelivery;

import com.example.FoodDelivery.Models.Menus;
//import com.example.FoodDelivery.Models.Restaurants;
//import com.example.FoodDelivery.Repositories.RestRepositories;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.LinkedList;

@SpringBootApplication
public class FoodDeliveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodDeliveryApplication.class, args);
	}

//	@Bean
//    public ApplicationRunner demo(RestRepositories cr) {
//        return args -> {
//            Restaurants r1 = new Restaurants(null,"Restaurant1", "Location1", null);
//            Menus menu = new Menus(null,  r1);
////            Menus menu = new Menus();
//            r1.setMenu(menu);
//            cr.save(r1);
//            cr.save(new Restaurants(null, "Restauant2", "Location2", null));
//        };
//    }

}
