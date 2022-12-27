package com.bezkoder.springjwt.controllers;

import java.util.List;

import com.bezkoder.springjwt.models.Product;
import com.bezkoder.springjwt.security.services.ProductServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class ProductController {

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductServiceImpl productService;

    @PostMapping(value = "/products/{userID}")
    public ResponseEntity<Product> saveProduct(@PathVariable String userID,
                                               @RequestBody Product Product) {
    	log.info("hi");
        return ResponseEntity.ok().body(productService.saveProduct(userID, Product));
    }

    @GetMapping("/products")
    public ResponseEntity<Iterable<Product>> getproducts() {
    	log.info("hi");
        return ResponseEntity.ok().body(productService.getProducts());
    }

	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    @GetMapping("/products/{ProductID}")
    public ResponseEntity<Product> getProduct(@PathVariable Long ProductID) {
        return ResponseEntity.ok().body(productService.getProduct(ProductID));
    }

    @GetMapping("/searchproducts")
    public ResponseEntity<Iterable<Product>> search(@RequestParam("str") String str) {
        return ResponseEntity.ok().body(productService.search(str));
    }

}
