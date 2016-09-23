package eshop.cart;

import com.opensymphony.xwork2.ActionSupport;
import eshop.product.Product;
import eshop.product.ProductService;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by RZ on 6/9/16.
 */
@Controller("cartAction")
@Scope("prototype")
public class CartAction extends ActionSupport{

    private Integer pid;

    public void setPid(int pid) {
        this.pid = pid;
    }

    private Cart cart;

    public Cart getCart() {
        return cart;
    }

    private int count;
    public void setCount(int count) {
        this.count = count;
    }
    @Resource
    private ProductService productService;

    /**
     * change item num
     */
    public String changeNum() {
        CartItem cartItem = new CartItem();
        cartItem.setProduct(productService.findProductByPid(pid));
        cartItem.setCount(count);
        cart = getCart(ServletActionContext.getRequest());
        cart.remove(pid);
        cart.addToCart(cartItem);
        return "show";
    }

    /**
     * delete cartItem from cart
     */
    public String delete() {
        cart = getCart(ServletActionContext.getRequest());
        cart.remove(pid);
        return "show";
    }

    /**
     * clear
     */
    public String clear(){
        cart = getCart(ServletActionContext.getRequest());
        cart.clear();
        return "show";
    }
    /**
     * add to cart
     * @return
     */
    public String show() {
        return add();
    }
    public String add() {
        cart = getCart(ServletActionContext.getRequest());
        if(pid==null) {
            return "show";
        }
        CartItem cartItem = new CartItem();
        cartItem.setProduct(productService.findProductByPid(pid));
        cartItem.setCount(count);
        cart.addToCart(cartItem);
        return "addSuccess";
    }

    /**
     * get cart from session
     * @return
     */
    public Cart getCart(HttpServletRequest httpServletRequest) {
        Cart cart = (Cart) httpServletRequest.getSession().getAttribute("cart");
        if(cart==null) {
            cart = new Cart();
            httpServletRequest.getSession().setAttribute("cart",cart);
        }
        return cart;
    }
}
