package com.example.FoodDelivery.Controller;

import com.example.FoodDelivery.Models.Cart;
import com.example.FoodDelivery.Models.Orders;
import com.example.FoodDelivery.Repositories.OrdersRepository;
import com.example.FoodDelivery.global;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("order")
public class OrdersController {
    private final OrdersRepository ordersRepository;
    Logger logger = LogManager.getLogger(OrdersController.class);
    private final KafkaTemplate<String,String> kafkaTemplate;

    public OrdersController(OrdersRepository ordersRepository, KafkaTemplate<String, String> kafkaTemplate) {
        this.ordersRepository = ordersRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping("/place_order")
    public Iterable<Orders> place_order(){
        logger.info("Placing order");
        Orders orders = new Orders();
        orders.setCart_id(global.req_id);
        Cart cart = Cart.getInstance();
        orders.setTotal_cost(cart.getTotalCost());
        orders.setOrder_status(1);
        orders.setPayment_status(1);
        ordersRepository.save(orders);
        String ord = orders.toString();
        kafkaTemplate.send("kafka_topic",ord);

        return ordersRepository.findAll();

    }

    @GetMapping
    public Iterable<Orders> viewOrder(){
        return ordersRepository.findAll();
    }

    @PutMapping("/order_prepared")
    public Orders update_order(@RequestParam Integer id){
        return ordersRepository.save(ordersRepository.findById(id).map( target ->{
            target.setOrder_status(2);

            return target;
        }).orElse(new Orders()));
    }

    @PutMapping("/order_delivered")
    public void order_delivered(@RequestParam Integer id){

        Orders order =ordersRepository.save(ordersRepository.findById(id).map( target ->{
            target.setOrder_status(3);

            return target;
        }).orElse(new Orders()));

        String url = "http://localhost:8080/cart/delete";
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(url);



    }

    @DeleteMapping("/deleteAll")
    public void delete_order_table(){
        ordersRepository.deleteAll();
    }
}


