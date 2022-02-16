package com.example.FoodDelivery.Models;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;

import java.util.List;
import java.util.Optional;

@Entity
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@restaurants")
public class Menus {

    @Id
    @GeneratedValue
    private Integer id;
//    private String Restaurant_name;

    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<MenuItem> items;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "rest_id")
    private Restaurants restaurants;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<MenuItem> getItems() {
        return items;
    }
//
//    public String getRestaurant_name() {
//        return Restaurant_name;
//    }
//
//    public void setRestaurant_name(String restaurant_name) {
//        this.Restaurant_name = restaurant_name;
//    }





    public void setRestaurants(Restaurants restaurants){
        this.restaurants = restaurants;
    }

    public void setItems(List<MenuItem> items){
        this.items = items;
    }

    public Restaurants getRestaurant() {
        return restaurants;
    }

    public Menus() {
    }

    public Menus( Integer id, List<MenuItem> items) {
        this.id = id;
        if (items != null) {
            this.items = items;
            for (MenuItem item : items)
                item.setMenu(this);
        }
    }

    public Menus(Integer id,Restaurants restaurants) {
        this.id = id;
        this.restaurants = restaurants;
    }


}
