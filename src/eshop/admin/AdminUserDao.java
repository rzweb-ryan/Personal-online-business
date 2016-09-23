package eshop.admin;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

/**
 * Created by RZ on 6/13/16.
 */
public class AdminUserDao extends HibernateDaoSupport {
    public AdminUser login(AdminUser adminUser) {
        List<AdminUser> list = this.getHibernateTemplate().find("from AdminUser au where au.username=? and au.password=?",adminUser.getUsername(),adminUser.getPassword());
        if (list==null||list.size()==0) return null;
        return list.get(0);
    }
}
