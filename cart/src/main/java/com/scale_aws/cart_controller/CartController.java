package com.scale_aws.cart_controller;

import com.scale_aws.cart_model.Item;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.ArrayList;
import java.util.List;

@RestController
@EnableWebMvc
public class CartController {

    @RequestMapping(path="/getCart", method= RequestMethod.GET)
    public List<Item> getCart() {
        List<Item> cart = new ArrayList<>();

        return cart;
    }

    @RequestMapping(path="/addToCart", method = RequestMethod.POST)
    public Item addItem(@RequestBody Item newItem) {
        if (newItem.getItemId() == null || newItem.getQuantity() == 0) {
            return null;
        }

        Item cartItem = newItem;
        cartItem.setPk();

        return newItem;
    }

    @RequestMapping(path="/removeFromCart", method = RequestMethod.POST)
    public Item removeItem(@RequestBody Item toRemove) {

        return new Item();
    }
}
