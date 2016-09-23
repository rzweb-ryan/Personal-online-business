package eshop.admin;

import eshop.user.User;
import eshop.user.UserDao;
import eshop.user.UserService;
import eshop.utils.PageBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by RZ on 6/13/16.
 */
@Service("adminUserService")
@Transactional
public class AdminUserService {
    @Resource
    private AdminUserDao adminUserDao;
    @Resource
    private UserDao userDao;
    public AdminUser login(AdminUser adminUser) {
        return adminUserDao.login(adminUser);
    }


    public PageBean<User> findByPageBean(Integer page) {
        PageBean<User> pageBean = new PageBean<>();
        pageBean.setPage(page);
        int totalCount = userDao.count();
        int pageCount = 20;
        int totalPage = (int)Math.ceil(totalCount*0.1/pageCount);
        pageBean.setTotalPage(totalPage);
        pageBean.setPageCount(pageCount);
        pageBean.setTotalCount(totalCount);
        pageBean.setPageContent(userDao.findByPageBean(pageBean));
        return pageBean;
    }

    public void deleteUser(User user) {
        userDao.delete(user);
    }
}
