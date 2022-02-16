package com.example.FoodDelivery.Repositories;

import com.example.FoodDelivery.Models.Restaurants;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface RestRepositories extends CrudRepository<Restaurants,Integer>, QueryByExampleExecutor<Restaurants> {

}
