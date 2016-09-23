package eshop.order;

import eshop.user.User;
import eshop.utils.PageBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by RZ on 6/9/16.
 */
@Service
@Transactional
public class OrderService {
    @Resource
    private OrderDao orderDao;
    public int save(Order order) {
        return orderDao.save(order);
    }

    public Order getOrderByOid(Order order) {
        return orderDao.getOrderByOid(order);
    }

    public void update(Order currOrder) {
        orderDao.update(currOrder);
    }

    public List<Order> listOrderByUid(User currUser) {
        return orderDao.listOrderByUid(currUser);
    }

    public PageBean<Order> findByPageBean(Integer page, Integer state) {
        PageBean<Order> pageBean = new PageBean<>();
        pageBean.setPage(page);
        int pageCount = 10;
        int totalCount = orderDao.count(state);
        pageBean.setPageCount(pageCount);
        pageBean.setTotalCount(totalCount);
        pageBean.setTotalPage((int)Math.ceil(totalCount*1.0/pageCount));
        List<Order> pageContent = orderDao.findPageBean(pageBean,state);
        pageBean.setPageContent(pageContent);
        return pageBean;
    }
}
