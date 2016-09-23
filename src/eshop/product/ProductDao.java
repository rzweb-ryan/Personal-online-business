package eshop.product;

import eshop.utils.PageBean;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import eshop.utils.PageHibernateCallback;

import java.util.List;

/**
 * Created by RZ on 6/8/16.
 */
public class ProductDao extends HibernateDaoSupport{
    public List<Product> findHotList() {
        return this.getHibernateTemplate().execute(new PageHibernateCallback<Product>("from Product where is_hot=?",0,10,1));
    }

    public List<Product> findNewList() {
        return this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(
                "from Product order by pdate desc",0,10
        ));
    }

    public int countByCid(Integer cid) {
        List<Long> res = this.getHibernateTemplate().find("select count(*) from Product p join p.categorySecond cs join cs.category c where c.cid=?",cid);
        return res.get(0).intValue();
    }

    public List<Product> findPageBean(Integer cid, Integer page, int pageCount) {
        String hql = "select p from Product p join p.categorySecond cs join cs.category c where c.cid=?";
        return this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql,(page-1)*page,pageCount,cid));
    }

    public int countByCsid(Integer csid) {
        List<Long> list =this.getHibernateTemplate().find("select count(*) from Product p join p.categorySecond cs where cs.csid=?", csid);
        return list.get(0).intValue();
    }

    public List<Product> findPageBean2(Integer csid, Integer page, int pageCount) {
        String hql = "select p from Product p join p.categorySecond cs where cs.csid=?";
        return this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql,(page-1)*page,pageCount,csid));
    }

    public Product findByPid(Integer pid) {
        Product product = this.getHibernateTemplate().get(Product.class,pid);
        return product;
    }

    public int count() {
        List<Long> res = this.getHibernateTemplate().find("select count(*) from Product");
        return res.get(0).intValue();
    }

    public List<Product> findPageBean(PageBean<Product> pageBean) {
        return this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(
                "from Product",(pageBean.getPage()-1)*pageBean.getPageCount(),pageBean.getPageCount()
        ));
    }

    public void save(Product product) {
        this.getHibernateTemplate().save(product);
    }

    public void delete(Product product) {
        this.getHibernateTemplate().delete(product);
    }
}
