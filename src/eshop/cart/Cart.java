package eshop.cart;

import eshop.user.User;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by RZ on 6/9/16.
 */
@Component("cart")
@Scope("prototype")
public class Cart {
    private Map<Integer,CartItem> cart;
    private Double total = 0d;



    public Double getTotal() {
        return total;
    }


    public Collection<CartItem> getCartItems() {
        return cart.values();
    }

    public void addToCart(CartItem cartItem) {
        if(cart==null) {
            cart = new HashMap<>();
        }
        int pid = cartItem.getProduct().getPid();
        if(cart.containsKey(pid)) {
            CartItem orgItem = cart.get(pid);
            int count = orgItem.getCount();
            orgItem.setCount(count+cartItem.getCount());
        } else {
            cart.put(pid,cartItem);
        }
        total += cartItem.getSubTotal();
    }

    public void remove(int pid) {
        CartItem cartItem = cart.remove(pid);
        total -=cartItem.getSubTotal();
    }

    public void clear() {
        cart.clear();
        total = 0d;
    }
}
