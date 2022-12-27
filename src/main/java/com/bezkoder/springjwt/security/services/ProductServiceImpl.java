package com.bezkoder.springjwt.security.services;

import com.bezkoder.springjwt.models.Product;
import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.repository.ProductRepository;
import com.bezkoder.springjwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl {


    @Autowired
    private ProductRepository productDao;



    @Autowired
    private UserRepository userDao;



    public Product saveProduct(String userID, Product product) {
        //getting and setting the useremail
        User user = userDao.findByEmail(userID);
        product.setUser(user);

        // saving the property
        return productDao.save(product);
    }


    public Iterable<Product> getProducts() {
        // TODO Auto-generated method stub
        return productDao.findAll();
    }


    public Product getProduct(Long productID) {
        // TODO Auto-generated method stub
        return productDao.findById(productID).get();
    }


    public Iterable<Product> search(String str) {
        // TODO Auto-generated method stub
        return productDao.findByNameContaining(str);
    }

}
