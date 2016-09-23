package eshop.category;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by RZ on 6/8/16.
 */
@Controller("categoryAction")
@Scope("prototype")
public class CategoryAction extends ActionSupport implements ModelDriven<Category>{
    //model driven
    private Category category = new Category();
    @Override
    public Category getModel() {
        return category;
    }

    private List<Category> categoryList;

    public List<Category> getCategoryList() {
        return categoryList;
    }

    @Resource
    private CategoryService categoryService;
    /**
     * show all the Categories
     * @return
     */

    public String adminFindAll(){
        categoryList = categoryService.findAll();
        return "findAllSuccess";
    }

    /**
     * edit_page
     */

    public String edit() {
        category = categoryService.findByCid(category);
        return "editSuccess";
    }

    public String update() {
        categoryService.update(category);
        return  adminFindAll();
    }
    /**
     * category add
     */
    public String save() {
        categoryService.save(category);
        return "saveSuccess";
    }
    /**
     * delete
     */
    public String delete() {
        categoryService.delete(category);
        return "deleteSuccess";
    }
}
