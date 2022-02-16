package com.example.FoodDelivery.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.jetbrains.annotations.NotNull;

@Entity
public class MenuItem {

    private String item_name;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer item_id;
    private Integer cost;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL,optional = false)
    @JoinColumn(name = "menu_id")
    private Menus menu;


    public MenuItem() {
    }

    public MenuItem(@JsonProperty("id") Integer item_id, @JsonProperty("name") String item_name, @JsonProperty("price") Integer cost){
        this.item_id = item_id;
        this.cost = cost;
        this.item_name = item_name;

    }

    @JsonBackReference
    public Menus getMenu() {
        return menu;
    }

    public void setMenu(Menus menu){
        this.menu = menu;
    }


    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public Integer getItem_id() {
        return item_id;
    }

    public void setItem_id(Integer item_id) {
        this.item_id = item_id;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }
}
