package com.spice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spice.dao.repository.ProductRepository;
import com.spice.dao.repository.entity.ProductEntity;

@RestController
public class ProductController {
	@Autowired
	private ProductRepository productRepository;
    
    @RequestMapping(value="/products", method = RequestMethod.GET)
    public List<ProductEntity> getProduct() throws Exception {
    	return productRepository.findAll();
	}
    
	@RequestMapping(value="/products/{id}", method = RequestMethod.GET)
    public ProductEntity getProduct(@PathVariable(value="id") Long id) {
		return productRepository.findOne(id) ;
    }
    
	@RequestMapping(value="/products", method = RequestMethod.POST)
    public ProductEntity saveProduct(@RequestBody ProductEntity contact) throws Exception {
		return productRepository.save(contact);
    }
    
	@RequestMapping(value="/products/{id}", method = RequestMethod.PUT)
    public ProductEntity updateProduct(@PathVariable(value="id") Long id, @RequestBody ProductEntity contact) throws Exception {
		return productRepository.save(contact);
    }

    
	@RequestMapping(value="/products/{id}", method = RequestMethod.DELETE)
    public void deleteProduct(@PathVariable(value="id") Long id) {
		productRepository.delete(id);
    }

}

