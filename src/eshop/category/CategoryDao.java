package eshop.category;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

/**
 * Created by RZ on 6/8/16.
 */
public class CategoryDao extends HibernateDaoSupport{
    public List<Category> findAll() {
        return this.getHibernateTemplate().find("from Category");
    }

    public Category findByCid(Category category) {
        return this.getHibernateTemplate().get(Category.class,category.getCid());
    }

    public void update(Category category) {
        this.getHibernateTemplate().update(category);
    }

    public void save(Category category) {
        this.getHibernateTemplate().save(category);
    }

    public void delete(Category category) {
        this.getHibernateTemplate().delete(category);
    }

    public Category getByCid(Integer cid) {
        return this.getHibernateTemplate().get(Category.class,cid);
    }

}
