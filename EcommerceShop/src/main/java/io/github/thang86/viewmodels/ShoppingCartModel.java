package io.github.thang86.viewmodels;


import io.github.thang86.entities.Order;
import io.github.thang86.entities.User;
import io.github.thang86.enums.OrderStatus;
import io.github.thang86.enums.Role;
import io.github.thang86.service.OrderService;
import io.github.thang86.service.StoreService;
import io.github.thang86.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
*  ShoppingCartModel.java
* 
*  Version 1.0
*
*  Copyright
*
*  Modification Logs:
*  DATE		     AUTHOR		 DESCRIPTION
*  -------------------------------------
*  2018-12-13    ThangTX     Create
*/

@Component
public class ShoppingCartModel {
    @Autowired
    OrderService orderService;

    @Autowired
    UserService userService;

	@Autowired
    StoreService storeService;

    public HashMap<String, Object> create(Long id) {
        HashMap<String, Object> model = new HashMap<>();

        Collection<Order> orders = orderService.getOrders(id, OrderStatus.UNPROCESSED);

        Map<Long, String> perOrderMsgs = new HashMap<>();
        Map<Long, Double> perOrderTotal = new HashMap<>();

        Collection<String> msgs = new ArrayList<>();

        Double total = 0.0;

        for(Order order: orders){
            Float currentOrderTotal = order.getQuantity() * order.getStoreProduct().getPrice();
			String msg = "";
            if(order.getQuantity() > 1) {
                currentOrderTotal -= currentOrderTotal * 0.1f;
                msg = "10% Discount for buying more than 2 items!";
            }

	        perOrderMsgs.put(order.getId(), msg);
	        perOrderTotal.put(order.getId(), Double.valueOf(currentOrderTotal));

	        total += currentOrderTotal;
        }

        User user = userService.getUserById(id).get();

        if(user.getRoles().contains(Role.STORE_OWNER)
		        && storeService.getAllAcceptedUserStores(id).size() > 0) {
            total -= total * 0.1f;
            msgs.add("Total checkout 10% Discount for being a store owner!");
        }

        Integer oldOrdersCount = orderService.getOrderCountByUser(id, OrderStatus.DELIVERED)
		                            + orderService.getOrderCountByUser(id, OrderStatus.PROCESSED);


        if(oldOrdersCount == 0 ) {
            total -= total * 0.05f;
            msgs.add("Total checkout 5% Discount for your first order!");
        }

        model.put("orders",orders);
        model.put("msgs",msgs);
        model.put("perOrderMsgs",perOrderMsgs);
        model.put("perOrderTotal",perOrderTotal);
        model.put("total",total);

        return model;
    }

}
