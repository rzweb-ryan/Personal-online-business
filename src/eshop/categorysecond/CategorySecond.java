package eshop.categorysecond;

import eshop.category.Category;
import eshop.product.Product;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by RZ on 6/8/16.
 */
public class CategorySecond {
    private Integer csid;
    private String csname;

    private Category category;

    private Set<Product> products = new HashSet<Product>();

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Integer getCsid() {
        return csid;
    }

    public void setCsid(Integer csid) {
        this.csid = csid;
    }

    public String getCsname() {
        return csname;
    }

    public void setCsname(String csname) {
        this.csname = csname;
    }
}
