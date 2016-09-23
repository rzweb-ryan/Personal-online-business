package eshop.product;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import eshop.utils.PageBean;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by RZ on 6/8/16.
 */
@Service
@Transactional
public class ProductService {
    @Resource
    private ProductDao productDao;

    public List<Product> findHotList() {
        return productDao.findHotList();
    }

    public List<Product> findNewList() {
        return productDao.findNewList();

    }

    public  PageBean<Product> findPageBean(Integer cid, Integer page) {
        PageBean<Product> pageBean = new PageBean<>();
        pageBean.setPage(page);
        int pageCount = 16;
        int totalCount = productDao.countByCid(cid);
        pageBean.setPageCount(pageCount);
        pageBean.setTotalCount(totalCount);
        pageBean.setTotalPage((int)Math.ceil(totalCount*1.0/pageCount));
        List<Product> pageContent = productDao.findPageBean(cid,page,pageCount);
        pageBean.setPageContent(pageContent);
        return pageBean;
    }


    public Product findProductByPid(Product product) {
        return productDao.findByPid(product.getPid());
    }

    public Product findProductByPid(int pid) {
        return productDao.findByPid(pid);

    }

    public PageBean<Product> listByPageBean(Integer page) {
        PageBean<Product> pageBean = new PageBean<>();
        pageBean.setPage(page);
        int pageCount = 16;
        int totalCount = productDao.count();
        pageBean.setPageCount(pageCount);
        pageBean.setTotalCount(totalCount);
        pageBean.setTotalPage((int)Math.ceil(totalCount*1.0/pageCount));
        List<Product> pageContent = productDao.findPageBean(pageBean);
        pageBean.setPageContent(pageContent);
        return pageBean;
    }

    public void save(Product product) {
        productDao.save(product);
    }

    public void delete(Product product) {
        productDao.delete(product);
    }
}
