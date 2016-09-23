package eshop.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import eshop.admin.AdminUser;
import eshop.admin.AdminUserAction;
import org.apache.struts2.ServletActionContext;

/**
 * Created by RZ on 6/14/16.
 */
public class AdminInterceptor extends MethodFilterInterceptor {
    @Override
    protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
        AdminUser admin = (AdminUser) ServletActionContext.getRequest().getSession().getAttribute("adminUser");
        if (admin!=null) {
           return actionInvocation.invoke();
        }else {
            ActionSupport action = (ActionSupport) actionInvocation.getAction();
            action.addActionError("请以管理员身份重新登录!");
            return "adminLogin";
        }
    }
}
