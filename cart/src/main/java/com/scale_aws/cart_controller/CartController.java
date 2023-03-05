package com.scale_aws.cart_controller;

import com.scale_aws.cart_model.Item;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import software.amazon.awssdk.enhanced.dynamodb.*;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;

import java.util.*;

@RestController
@EnableWebMvc
public class CartController {
    private static final Logger logger = LogManager.getLogger(CartController.class);

    private static final DynamoDbEnhancedClient dbClient = DynamoDbEnhancedClient.create();

    @RequestMapping(path="/getCart", method= RequestMethod.POST)
    public List<Item> getCart(@RequestBody String userId) {
        logger.info("Serving /getCart");
        List<Item> cart = new ArrayList<>();

        try {
            DynamoDbTable<Item> table = dbClient.table(System.getenv("DYNAMO_TABLE"), TableSchema.fromBean(Item.class));
            String pk = String.format("cart#%s", userId);

            QueryConditional query = QueryConditional.keyEqualTo(Key.builder().partitionValue(pk).build());

            cart = new ArrayList(Collections.singleton(table.query(r -> r.queryConditional(query)).items().iterator()));
        } catch (DynamoDbException e) {
            logger.info("Exception while communicating with DynamoDB, " + e);
        } catch (Exception e) {
            logger.info("Generic Exception while retrieving from Dynamo, " + e);
        }

        return cart;
    }

    @RequestMapping(path="/addToCart", method = RequestMethod.POST)
    public Item addItem(@RequestBody Item newItem) {
        logger.info("Serving /addToCart, Incoming Data: " + newItem.toString());
        if (newItem.getProductId() == null || newItem.getQuantity() == 0) {
            return null;
        }

        Item cartItem = newItem;
//        cartItem.setPk();

        return newItem;
    }

    @RequestMapping(path="/removeFromCart", method = RequestMethod.POST)
    public Item removeItem(@RequestBody Item toRemove) {
        logger.info("Service /removeFromCart, Incoming Data: " + toRemove.toString());
        return new Item();
    }
}
