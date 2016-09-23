package eshop.categorysecond;

import eshop.utils.PageBean;
import eshop.utils.PageHibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

/**
 * Created by RZ on 6/8/16.
 */
public class CategorySecondDao extends HibernateDaoSupport {
    public List<CategorySecond> findAll() {
        return this.getHibernateTemplate().find("from CategorySecond");
    }

    public List<CategorySecond> findByPageBean(PageBean<CategorySecond> pageBean) {
        int pageCount = pageBean.getPageCount();
        int page = pageBean.getPage();
        int begin = (page-1)*pageCount;
        return this.getHibernateTemplate().execute(new PageHibernateCallback<CategorySecond>("from CategorySecond",begin,pageCount));
    }

    public int getTotalCount() {
        List<Long> res = this.getHibernateTemplate().find("select count(*) from CategorySecond");
        return res.get(0).intValue();
    }

    public CategorySecond get(CategorySecond categorySecond) {
        return this.getHibernateTemplate().get(CategorySecond.class,categorySecond.getCsid());
    }

    public void update(CategorySecond categorySecond) {
        this.getHibernateTemplate().update(categorySecond);
    }

    public void delete(CategorySecond categorySecond) {
        this.getHibernateTemplate().delete(categorySecond);
    }

    public void save(CategorySecond categorySecond) {
        this.getHibernateTemplate().save(categorySecond);

    }
}
