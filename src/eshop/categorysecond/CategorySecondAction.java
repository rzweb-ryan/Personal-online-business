package eshop.categorysecond;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import eshop.category.Category;
import eshop.category.CategoryService;
import eshop.utils.PageBean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by RZ on 6/8/16.
 */
@Controller("categorySecondAction")
@Scope("prototype")
public class CategorySecondAction extends ActionSupport implements ModelDriven<CategorySecond>{
    private CategorySecond categorySecond = new CategorySecond();
    @Override
    public CategorySecond getModel() {
        return categorySecond;
    }

    private Integer cid;

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    private Integer page;

    public void setPage(Integer page) {
        this.page = page;
    }

    @Resource
    private CategorySecondService categorySecondService;
    @Resource
    private CategoryService categoryService;
    public String adminFindAll() {
        PageBean<CategorySecond> pageBean = categorySecondService.findAll(page);
        ActionContext.getContext().getValueStack().set("pageBean",pageBean);
        return "findAllSuccess";
    }

    /**
     *
     * @return edit page redirect
     */
    public String edit(){
        categorySecond = categorySecondService.get(categorySecond);
        List<Category> list = categoryService.findAll();
        ActionContext.getContext().getValueStack().set("categoryList",list);
        return "editSuccess";
    }
    public String update(){
        Category category = categoryService.getByCid(cid);
        categorySecond.setCategory(category);
        categorySecondService.update(categorySecond);
        return "updateSuccess";
    }
    public String delete(){
        categorySecondService.delete(categorySecond);
        return "deleteSuccess";
    }

    /**
     * add page redirect
     * @return
     */
    public String addPage() {
        List<Category> list = categoryService.findAll();
        ActionContext.getContext().getValueStack().set("categoryList",list);
        return "addPageSuccess";
    }
    public String  save() {
        Category category = categoryService.getByCid(cid);
        categorySecond.setCategory(category);
        categorySecondService.save(categorySecond);
        return "saveSuccess";
    }

}
