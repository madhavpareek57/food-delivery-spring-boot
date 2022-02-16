package com.example.FoodDelivery.Repositories;

import com.example.FoodDelivery.Models.Orders;
import org.springframework.data.repository.CrudRepository;

public interface OrdersRepository extends CrudRepository<Orders,Integer> {


}
