package eshop.order;

import eshop.user.User;
import eshop.utils.PageBean;
import eshop.utils.PageHibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

/**
 * Created by RZ on 6/9/16.
 */
public class OrderDao extends HibernateDaoSupport{

    public int save(Order order) {
        return (int) this.getHibernateTemplate().save(order);
    }

    public Order getOrderByOid(Order order) {
        return this.getHibernateTemplate().get(Order.class,order.getOid());
    }

    public void update(Order currOrder) {
        this.getHibernateTemplate().update(currOrder);
    }

    public List<Order> listOrderByUid(User currUser) {
        List<Order> res = this.getHibernateTemplate().find("from Order o where o.user.uid=? order by ordertime desc",currUser.getUid());
        return res;
    }

    public int count(Integer state) {
        String hql="select count(*) from Order";
        List<Long> res;
        if(state!=null) {
            hql = hql + " where state = ?";
            res = this.getHibernateTemplate().find(hql, state);
        }else {
            res = this.getHibernateTemplate().find(hql);
        }
        return res.get(0).intValue();
    }

    public List<Order> findPageBean(PageBean<Order> pageBean, Integer state) {
        int begin = (pageBean.getPage()-1)*pageBean.getPageCount();
        int limit = pageBean.getPageCount();
        String hql = "from Order";
        if(state!=null) {
            hql = hql + " where state = ?";
            return this.getHibernateTemplate().execute(new PageHibernateCallback<Order>(hql+" order by ordertime desc", begin, limit, state));
        }
        return this.getHibernateTemplate().execute(new PageHibernateCallback<Order>(hql+" order by ordertime desc", begin, limit));
    }
}
