package com.example.FoodDelivery.Repositories;

import com.example.FoodDelivery.Models.MenuItem;
import com.example.FoodDelivery.Models.Restaurants;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.List;

public interface MenuItemRepository extends CrudRepository<MenuItem,Integer>, QueryByExampleExecutor<MenuItem> {

//    List<MenuItem>  findByMenu_Id(Integer id);
}
