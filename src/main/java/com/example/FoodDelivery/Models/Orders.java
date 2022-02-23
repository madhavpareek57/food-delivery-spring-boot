package com.example.FoodDelivery.Models;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer cart_id;
    private Integer order_status;
    private Integer Payment_status;
    private Integer Total_cost;

    public Orders(Integer id, Integer cart_id, Integer order_status, Integer payment_status, Integer total_cost) {
        this.id = id;
        this.cart_id = cart_id;
        this.order_status = order_status;
        this.Payment_status = payment_status;
        Total_cost = total_cost;
    }

    public Integer getTotal_cost() {
        return Total_cost;
    }

    public void setTotal_cost(Integer total_cost) {
        Total_cost = total_cost;
    }

    public Orders(Integer cart_id, Integer order_status, Integer payment_status, Integer total_cost) {
        this.cart_id = cart_id;
        this.order_status = order_status;
        this.Payment_status = payment_status;
        Total_cost = total_cost;
    }

    public Orders() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCart_id() {
        return cart_id;
    }

    public void setCart_id(Integer cart_id) {
        this.cart_id = cart_id;
    }

    public Integer getOrder_status() {
        return order_status;
    }

    public void setOrder_status(Integer order_status) {
        this.order_status = order_status;
    }

    public Integer getPayment_status() {
        return Payment_status;
    }

    public void setPayment_status(Integer payment_status) {
        Payment_status = payment_status;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", cart_id=" + cart_id +
                ", order_status=" + order_status +
                ", Payment_status=" + Payment_status +
                ", Total_cost=" + Total_cost +
                '}';
    }
}
