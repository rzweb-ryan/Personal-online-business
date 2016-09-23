package eshop.user;

import eshop.utils.PageBean;
import eshop.utils.PageHibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by RZ on 6/7/16.
 */
public class UserDao extends HibernateDaoSupport{

    public void reg(User user) {
        this.getHibernateTemplate().save(user);
    }

    public User checkUsername(String username) {
        List<User> list =  this.getHibernateTemplate().find("from User where username = ?",username);
        if (list.size()==0) return null;
        return list.get(0);
    }

    public User findByUsernameAndPassword(User user) {
        List<User> list = this.getHibernateTemplate().find("from User where username = ? and password = ?",user.getUsername(),user.getPassword());
        if (list.size()==0) return null;
        return list.get(0);
    }

    public List<User> findAll() {
        return this.getHibernateTemplate().find("from User");
    }

    public int count() {
        List<Long> res = this.getHibernateTemplate().find("select count(*) from User");
        return res.get(0).intValue();
    }

    public List<User> findByPageBean(PageBean<User> pageBean) {
        int begin = (pageBean.getPage()-1)*pageBean.getPageCount();
        int limit = pageBean.getPageCount();
        return this.getHibernateTemplate().execute(new PageHibernateCallback<User>("from User",begin,limit));
    }

    public void delete(User user) {
        this.getHibernateTemplate().delete(user);
    }
}
