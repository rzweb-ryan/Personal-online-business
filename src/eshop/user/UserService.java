package eshop.user;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by RZ on 6/7/16.
 */
@Service
@Transactional
public class UserService {
    @Resource
    private UserDao userDao;

    /**
     * regMethod
     * @param user
     */
    public void reg(User user) {
        userDao.reg(user);
    }

    public User checkUsername(String username) {
        return userDao.checkUsername(username);
    }

    public User findByUsernameAndPassword(User user) {
        return userDao.findByUsernameAndPassword(user);
    }
}
