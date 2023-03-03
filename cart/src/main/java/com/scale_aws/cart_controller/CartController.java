package com.scale_aws.cart_controller;

import com.scale_aws.cart_model.Item;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@EnableWebMvc
public class CartController {
    private static final Logger logger = LogManager.getLogger(CartController.class);
    private static final DynamoDbEnhancedAsyncClient dbClient = DynamoDbEnhancedAsyncClient.builder()
            .dynamoDbClient()

    @RequestMapping(path="/getCart", method= RequestMethod.POST)
    public List<Item> getCart() {
        logger.info("Serving /getCart");
        List<Item> cart = new ArrayList<>();

        HashMap<String, AttributeValue> keyToGet = new HashMap<String, AttributeValue>();

//        keyToGet.put();

        try {
            GetItemRequest request = GetItemRequest.builder()
                    .key(keyToGet)
                    .tableName(System.getenv("DYNAMO_TABLE"))
                    .build();

        } catch (DynamoDbException e) {
            logger.info("Exception while communicating with DynamoDB, " + e.toString());
        } catch (Exception e) {
            logger.info("Generic Exception while retrieving from Dynamo, " + e.toString())
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
