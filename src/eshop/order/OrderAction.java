package eshop.order;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import eshop.cart.Cart;
import eshop.cart.CartItem;
import eshop.user.User;
import eshop.utils.PageBean;
import eshop.utils.PaymentUtil;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by RZ on 6/9/16.
 */
@Controller("orderAction")
@Scope("prototype")
public class OrderAction extends ActionSupport{


    private Integer state;

    public void setState(Integer state) {
        this.state = state;
    }
    public Integer getState() {
        return state;
    }
    private Integer page;

    public void setPage(Integer page) {
        this.page = page;
    }

    private Order order;

    private int oid;

    public void setOid(int oid) {
        this.oid = oid;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    private String pd_FrpId;

    public void setPd_FrpId(String pd_FrpId) {
        this.pd_FrpId = pd_FrpId;
    }

    public Order getOrder() {
        return order;
    }

    @Resource
    private OrderService orderService;

    /**
     * place order
     */
    public String place() {
        User user = (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
        if(user==null) return "login";
        Cart cart = getCart(ServletActionContext.getRequest());
        order = new Order();
        order.setOrderTime(new Date());
        order.setUser(user);
        order.setState(1);//1:placed 2:paid 3:sent 4:delivered
        order.setTotal(cart.getTotal());

        //set orderItems
        for (CartItem cartItem : cart.getCartItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setCount(cartItem.getCount());
            orderItem.setSubTotal(cartItem.getSubTotal());
            order.getOrderItem().add(orderItem);
        }
        //save cascade
        int oid = orderService.save(order);
        cart.clear();
        return  "placeSuccess";
    }

    /**
     * show order
     */
    public String showOrder() {
        order = new Order();
        order.setOid(oid);
        order = orderService.getOrderByOid(order);
        return  "placeSuccess";
    }
    /**
     * get cart from session
     */
    public Cart getCart(HttpServletRequest httpServletRequest) {
        Cart cart = (Cart) httpServletRequest.getSession().getAttribute("cart");
        if(cart==null) {
            cart = new Cart();
            httpServletRequest.getSession().setAttribute("cart",cart);
        }
        return cart;
    }

    /**
     * order pay action
     */
    public String payOrder() throws IOException {
        order.setOid(oid);
        Order currOrder = orderService.getOrderByOid(order);
        currOrder.setAddr(order.getAddr());
        currOrder.setName(order.getName());
        currOrder.setPhone(order.getPhone());
        orderService.update(currOrder);

        //***************pay money
/**
 * p1_MerId=10001126856
 keyValue=69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl
 responseURL=http://localhost:8080/order_callBack.action
 */
        String keyValue="69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl";
        String p0_Cmd = "buy";
        String p1_MerId = "10001126856";
        String p2_Order = ""+order.getOid();
        String p3_Amt = "0.01";
        String p4_Cur = "CNY";
        String p5_Pid = "eshop";
        String p6_Pcat = "";
        String p7_Pdesc = "";
        String p8_Url = "http://localhost:8080/order_callBack.action";      //接收地址
        String p9_SAF = "0";
        String pa_MP = "";
        String  pr_NeedResponse = "1";
        String  hmac = PaymentUtil.buildHmac(p0_Cmd,p1_MerId,p2_Order,p3_Amt,p4_Cur,
                p5_Pid,p6_Pcat,p7_Pdesc,p8_Url,p9_SAF,pa_MP,pd_FrpId,pr_NeedResponse,keyValue);
        StringBuilder requestURL = new StringBuilder("https://www.yeepay.com/app-merchant-proxy/node?");
        requestURL.append("p0_Cmd=").append(p0_Cmd).append("&");
        requestURL.append("p1_MerId=").append(p1_MerId).append("&");
        requestURL.append("p2_Order=").append(p2_Order).append("&");
        requestURL.append("p3_Amt=").append(p3_Amt).append("&");
        requestURL.append("p4_Cur=").append(p4_Cur).append("&");
        requestURL.append("p5_Pid=").append(p5_Pid).append("&");
        requestURL.append("p6_Pcat=").append(p6_Pcat).append("&");
        requestURL.append("p7_Pdesc=").append(p7_Pdesc).append("&");
        requestURL.append("p8_Url=").append(p8_Url).append("&");
        requestURL.append("p9_SAF=").append(p9_SAF).append("&");
        requestURL.append("pa_MP=").append(pa_MP).append("&");
        requestURL.append("pd_FrpId=").append(pd_FrpId).append("&");
        requestURL.append("pr_NeedResponse=").append(pr_NeedResponse).append("&");
        requestURL.append("pr_NeedResponse=").append(pr_NeedResponse).append("&");
        requestURL.append("hmac=").append(hmac);
        ServletActionContext.getResponse().sendRedirect(requestURL.toString());
        return NONE;
    }

    /**
     * call back
     */
    public String callBack() {
        Order currOrder = new Order();
        currOrder.setOid(oid);
        currOrder = orderService.getOrderByOid(currOrder);
        currOrder.setState(2);
        orderService.update(currOrder);
        this.addActionMessage("恭喜您付款成功!您的商品即将发货!");
        return "callBackSuccess";
    }

    /**
     * list orders
     */
    public String list() {
        User currUser = (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
        if(currUser==null) {
            return LOGIN;
        }
        List<Order> orderList = orderService.listOrderByUid(currUser);
        ActionContext.getContext().getValueStack().set("orderList",orderList);
        return "listSuccess";
    }

    public String updateState() {
        order = new Order();
        order.setOid(oid);
        order = orderService.getOrderByOid(order);
        order.setState(4);
        orderService.update(order);
        return "updateStateSuccess";
    }

    /**
     * admin order part
     */
    public String adminFindAll() {
        PageBean<Order> pageBean = orderService.findByPageBean(page,state);
        ActionContext.getContext().getValueStack().set("pageBean",pageBean);
        return "adminFindAllSuccess";
    }

    public String adminUpdateState() {
        order = new Order();
        order.setOid(oid);
        order = orderService.getOrderByOid(order);
        order.setState(3);
        orderService.update(order);
        return "adminUpdateStateSuccess";
    }
}
