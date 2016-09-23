package eshop.categorysecond;

import eshop.utils.PageBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by RZ on 6/8/16.
 */
@Service
@Transactional
public class CategorySecondService {
    @Resource
    private CategorySecondDao categorySecondDao;
    public PageBean<CategorySecond> findAll(Integer page) {
        PageBean<CategorySecond> pageBean = new PageBean<>();
        pageBean.setPage(page);
        int pageCount = 10;
        pageBean.setPageCount(pageCount);
        int totalCount = categorySecondDao.getTotalCount();
        int totalPage = (int)Math.ceil(totalCount*1.0/pageCount);
        pageBean.setTotalCount(totalCount);
        pageBean.setTotalPage(totalPage);
        pageBean.setPageContent(categorySecondDao.findByPageBean(pageBean));
        return pageBean;
    }

    public CategorySecond get(CategorySecond categorySecond) {
        return categorySecondDao.get(categorySecond);
    }

    public void update(CategorySecond categorySecond) {
        categorySecondDao.update(categorySecond);

    }

    public void delete(CategorySecond categorySecond) {
        categorySecondDao.delete(categorySecond);
    }

    public void save(CategorySecond categorySecond) {
        categorySecondDao.save(categorySecond);

    }
    /**
     * public PageBean<CategorySecond> findAll
     */
    public List<CategorySecond> findAll() {
        return categorySecondDao.findAll();
    }
}
