package eshop.category;

import eshop.categorysecond.CategorySecond;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by RZ on 6/8/16.
 */
public class Category {
    private Integer cid;
    private String cname;
    private Set<CategorySecond> categorySecond = new HashSet<CategorySecond>();

    public Set<CategorySecond> getCategorySecond() {
        return categorySecond;
    }

    public void setCategorySecond(Set<CategorySecond> categorySecond) {
        this.categorySecond = categorySecond;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }
}
