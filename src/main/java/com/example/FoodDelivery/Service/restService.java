//package com.example.FoodDelivery.Service;
//
//import com.example.FoodDelivery.Models.Restaurants;
//import com.example.FoodDelivery.Repositories.RestRepositories;
//import org.springframework.stereotype.Service;
//
//@Service
//public class restService {
//    public void add_restaurant(RestRepositories restRepositories,String rest_name,String city,Integer id){
//        Restaurants n = new Restaurants();
//        n.setRest_name(rest_name);
//        n.setCity(city);
//        n.setRest_id(id);
//        restRepositories.save(n);
//    }
//
//    public void del_restaurant(RestRepositories restRepositories,Integer id){
//        restRepositories.deleteById(id);
//    }
//
//    public Iterable<Restaurants> show(RestRepositories restRepositories){
//        return restRepositories.findAll();
//    }
//}
