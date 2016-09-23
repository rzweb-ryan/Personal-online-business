package eshop.user;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by RZ on 6/7/16.
 */
@Controller("userAction")
@Scope("prototype")
public class UserAction extends ActionSupport implements ModelDriven<User> {
    private String checkCode;

    public void setCheckCode(String checkCode) {
        this.checkCode = checkCode;
    }

    private User user = new User();
    @Override
    public User getModel() {
        return user;
    }
    //userService
    @Resource
    private UserService userService;

    public String regPage(){
        return "regPageSuccess";
    }

    public String loginPage() {
        return "loginPageSuccess";
    }

    /**
     * user_reg
     */
    @InputConfig(resultName = "regInput")
    public String reg() {
        String checkCode1 = (String) ServletActionContext.getRequest().getSession().getAttribute("checkCode");
        if (!checkCode1.equalsIgnoreCase(checkCode)) {
            this.addActionError("验证码错误,请重试!");
            return "regInput";
        }
        userService.reg(user);
        this.addActionMessage("register success!");
        return "regSuccess";
    }

    /**
     * user_checkUsername
     */
    public String checkUsername() {
        User existUser = userService.checkUsername(user.getUsername());
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=utf-8");

        try {
            if (existUser!=null) {
                response.getWriter().print("<font color='red'>username exists</font>");
            }else {
                response.getWriter().print("<font color='green'>username doesn't exist</font>");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return NONE;
    }
    /**
     * user_login
     */
    @InputConfig(resultName = "loginInput")
    public String login() {
        String checkCode1 = (String) ServletActionContext.getRequest().getSession().getAttribute("checkCode");
        if (!checkCode1.equalsIgnoreCase(checkCode)) {
            this.addActionError("验证码错误,请重试!");
            return "loginInput";
        }
        User existUser = userService.findByUsernameAndPassword(user);
        if (existUser==null) {
            this.addActionError("username/password don't match!");
            return "loginInput";
        }
        ServletActionContext.getRequest().getSession().setAttribute("existUser",existUser);
        return "loginSuccess";
    }
    /**
     * user_logout
     */
    public String logout() {
        ServletActionContext.getRequest().getSession().invalidate();
        return "logoutSuccess";
    }
}
