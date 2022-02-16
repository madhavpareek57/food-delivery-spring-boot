package com.example.FoodDelivery.Models;


import com.example.FoodDelivery.global;
import jakarta.persistence.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class Cart {
    private Integer id;
    private Integer rest_id;
    private List<String> menuItem_name = new ArrayList<>();
    private Integer totalCost;

    private static Cart instance = null;

    public static Cart getInstance(){
        if(instance == null){
            instance = new Cart();
            instance.setId(global.req_id);
        }
        return instance;
    }

    public Cart(Integer rest_id, List<String> menuItem_name, Integer totalCost) {
        this.rest_id = rest_id;
        this.menuItem_name = menuItem_name;
        this.totalCost = totalCost;
    }

    public Cart() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRest_id() {
        return rest_id;
    }

    public void setRest_id(Integer rest_id) {
        this.rest_id = rest_id;
    }

    public List<String> getMenuItem_name() {
        return menuItem_name;
    }

    public void setMenuItem_name(List<String> menuItem_name) {
        this.menuItem_name = menuItem_name;
    }

    public Integer getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Integer totalCost) {
        this.totalCost = totalCost;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", rest_id=" + rest_id +
                ", menuItem_name=" + menuItem_name +
                ", totalCost=" + totalCost +
                '}';
    }
}
