package com.example.FoodDelivery.Repositories;

import com.example.FoodDelivery.Models.Menus;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface menuRepository extends CrudRepository<Menus,Integer> {
//    List<Menus> findByRestaurant_Id(Integer id);
//
//    void deleteByRestaurant_Id(Integer id);
}
