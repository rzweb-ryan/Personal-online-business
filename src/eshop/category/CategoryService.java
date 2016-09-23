package eshop.category;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by RZ on 6/8/16.
 */
@Service
@Transactional
public class CategoryService {
    @Resource
    private CategoryDao categoryDao;
    //find all categories
    public List<Category> findAll() {
        return categoryDao.findAll();
    }

    public Category findByCid(Category category) {
        return categoryDao.findByCid(category);
    }

    public void update(Category category) {
        categoryDao.update(category);
    }

    public void save(Category category) {
        categoryDao.save(category);
    }

    public void delete(Category category) {
        categoryDao.delete(category);
    }

    public Category getByCid(Integer cid) {
        return categoryDao.getByCid(cid);
    }
}
