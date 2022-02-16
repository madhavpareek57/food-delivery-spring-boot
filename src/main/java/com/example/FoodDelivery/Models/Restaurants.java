package com.example.FoodDelivery.Models;


import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;

import java.util.List;


@Entity
public class Restaurants {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer rest_id;

    private String rest_name;
    private String city;

    @OneToOne(mappedBy = "restaurants", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Menus menu;



    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getRest_id() {
        return rest_id;
    }

    public void setRest_id(Integer rest_id) {
        this.rest_id = rest_id;
    }

    public String getRest_name() {
        return rest_name;
    }

    public void setRest_name(String rest_name) {
        this.rest_name = rest_name;
    }

    public Menus getMenu() {
        return menu;
    }

    public void setMenu(Menus menu) {
        this.menu = menu;
    }

    public Restaurants() {
    }

    public Restaurants(String rest_name,String city) {
        this.rest_name = rest_name;
        this.city = city;
    }

    public Restaurants(String rest_name) {
        this.rest_name = rest_name;
    }


    public Restaurants(Long rest_id, String rest_name, String city, Menus menus) {
        this.rest_name = rest_name;
        this.city = city;
        this.menu = menus;
//        if (menus != null ) {
//            this.menu = menus;
//            for (Menus menu : menus)
//                menu.setRestaurants(this);
//        }
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + rest_id +
                ", name='" + rest_name + '\'' +
                ", location='" + city + '\'' +
                ", menus=" + menu +
                '}';
    }
}
