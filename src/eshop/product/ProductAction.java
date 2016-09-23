package eshop.product;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;
import eshop.category.Category;
import eshop.category.CategoryService;
import eshop.categorysecond.CategorySecond;
import eshop.categorysecond.CategorySecondService;
import eshop.utils.PageBean;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by RZ on 6/8/16.
 */
@Controller("productAction")
@Scope("prototype")
public class ProductAction extends ActionSupport implements ModelDriven<Product> {
    /**
     * file upload 3 components
     */
    private File upload;
    private String uploadContentType;
    private String uploadFileName;

    public void setUpload(File upload) {
        this.upload = upload;
    }

    public void setUploadContentType(String uploadContentType) {
        this.uploadContentType = uploadContentType;
    }

    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    private Product product = new Product();

    @Override
    public Product getModel() {
        return product;
    }


    private Integer cid;

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getCid() {
        return cid;
    }

    private Integer csid;

    public Integer getCsid() {
        return csid;
    }

    public void setCsid(Integer csid) {
        this.csid = csid;
    }

    private Integer page;

    public void setPage(Integer page) {
        this.page = page;
    }

    @Resource
    private CategoryService categoryService;

    @Resource
    private ProductService productService;
    @Resource
    private CategorySecondService categorySecondService;

    /**
     * list category page content
     *
     * @return
     */
    public String show() {
        //find all category and categorySecond
        List<Category> categoryList = categoryService.findAll();

        //find all Content by page
        PageBean<Product> pageBean = productService.findPageBean(cid, page);
        ValueStack vs = ActionContext.getContext().getValueStack();
        vs.set("categoryList", categoryList);
        vs.set("pageBean", pageBean);
        return "showSuccess";
    }

    /**
     * product_show2
     */
    public String show2() {
        List<Category> categoryList = categoryService.findAll();
        PageBean<Product> pageBean = productService.findPageBean(csid, page);
        ValueStack vs = ActionContext.getContext().getValueStack();
        vs.set("categoryList", categoryList);
        vs.set("pageBean", pageBean);
        return "show2Success";
    }

    /**
     * product_desc
     */
    public String desc() {
        List<Category> categoryList = categoryService.findAll();
        ValueStack vs = ActionContext.getContext().getValueStack();
        vs.set("categoryList", categoryList);
        product = productService.findProductByPid(product);
        System.out.println(product);
        return "descSuccess";
    }

    /**
     * admin product list
     *
     * @return
     */
    public String adminList() {
        PageBean<Product> pageBean = productService.listByPageBean(page);
        List<Category> categoryList = categoryService.findAll();
        ValueStack vs = ActionContext.getContext().getValueStack();
        vs.set("categoryList", categoryList);
        vs.set("pageBean", pageBean);
        return "adminListSuccess";
    }

    /**
     * redirect add page
     */

    public String addPage() {
        List<CategorySecond> csList = categorySecondService.findAll();
        ActionContext.getContext().getValueStack().set("csList", csList);
        return "addPageSuccess";
    }

    /**
     * add
     */
    public String save() throws IOException {
        String path = ServletActionContext.getServletContext().getRealPath("/products");
        String filePath = path + "/" +csid+"/"+uploadFileName;
        File diskFile = new File(filePath);
        FileUtils.copyFile(upload,diskFile);
        CategorySecond cs = new CategorySecond();
        cs.setCsid(csid);
        product.setCategorySecond(cs);
        product.setImage("products/"+csid+"/"+uploadFileName);
        product.setPdate(new Date());
        productService.save(product);
        return "addSuccess";
    }

    public String delete() {
        product = productService.findProductByPid(product.getPid());
        String path = product.getImage();
        String realPath = ServletActionContext.getServletContext().getRealPath(path);
        /*
         delete file
         */
        productService.delete(product);
        new File(realPath).delete();
        return "deleteSuccess";
    }
}