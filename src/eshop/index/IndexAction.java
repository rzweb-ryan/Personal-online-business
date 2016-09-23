package eshop.index;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import eshop.category.Category;
import eshop.category.CategoryService;
import eshop.product.Product;
import eshop.product.ProductService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by RZ on 6/7/16.
 */
@Controller(value = "indexAction")
@Scope("prototype")
public class IndexAction extends ActionSupport {
    private List<Product> hotList;
    public List<Product> getHotList() {
        return hotList;
    }

    private List<Product> newList;
    public List<Product> getNewList() {
        return newList;
    }



    /**
     * index page
     */
    @Resource
    private CategoryService categoryService;
    @Resource
    private ProductService productService;
    public String execute() throws Exception {
         //find all categories
        List<Category> categoryList = categoryService.findAll();

        //find hot products
        hotList = productService.findHotList();

        //find new products
        newList = productService.findNewList();

        ActionContext.getContext().getSession().put("categoryList",categoryList);
        return "indexSuccess";
    }
}
