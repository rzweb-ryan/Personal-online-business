package eshop.cart;

import eshop.product.Product;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by RZ on 6/9/16.
 */
@Component("cartItem")
@Scope("prototype")
public class CartItem {
    private Product product;
    private Integer count;
    private Double subTotal;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }


    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Double getSubTotal() {
        subTotal = count*product.getShop_price();
        return subTotal;
    }


}
