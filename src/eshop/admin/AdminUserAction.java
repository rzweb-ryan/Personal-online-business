package eshop.admin;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import eshop.user.User;
import eshop.user.UserService;
import eshop.utils.PageBean;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by RZ on 6/13/16.
 */
@Controller("adminUserAction")
@Scope("prototype")
public class AdminUserAction extends ActionSupport implements ModelDriven<AdminUser> {
    private AdminUser adminUser = new AdminUser();
    private Integer page;
    private Integer uid;

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    @Override
    public AdminUser getModel() {
        return adminUser;
    }

    @Resource
    private AdminUserService adminUserService;

    public String login() {
        AdminUser existAdminUser = adminUserService.login(adminUser);
        if (existAdminUser == null) {
            this.addActionError("用户名/密码错误!请重试!");
            return INPUT;
        }
        ServletActionContext.getRequest().getSession().setAttribute("adminUser", existAdminUser);
        return "loginSuccess";
    }

    /**
     * adminFindAll
     */
    public String adminFindAll() {
        PageBean<User> pageBean = adminUserService.findByPageBean(page);
        ActionContext.getContext().getValueStack().set("pageBean",pageBean);
        return "adminFindAllSuccess";
    }

    public String delete() {
        User user = new User();
        user.setUid(uid);
        adminUserService.deleteUser(user);
        return "deleteSuccess";
    }
}
