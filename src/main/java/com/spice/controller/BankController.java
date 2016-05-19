package com.spice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spice.dao.repository.BankRepository;
import com.spice.dao.repository.entity.BankEntity;

@RestController
public class BankController {
	@Autowired
	private BankRepository bankRepository;
    
    @RequestMapping(value="/banks", method = RequestMethod.GET)
    public List<BankEntity> getBank() throws Exception {
    	return bankRepository.findAll();
	}
    
	@RequestMapping(value="/banks/{id}", method = RequestMethod.GET)
    public BankEntity getBank(@PathVariable(value="id") Long id) {
		return bankRepository.findOne(id) ;
    }
    
	@RequestMapping(value="/banks", method = RequestMethod.POST)
    public BankEntity saveBank(@RequestBody BankEntity contact) throws Exception {
		return bankRepository.save(contact);
    }
    
	@RequestMapping(value="/banks/{id}", method = RequestMethod.PUT)
    public BankEntity updateBank(@PathVariable(value="id") Long id, @RequestBody BankEntity contact) throws Exception {
		return bankRepository.save(contact);
    }

    
	@RequestMapping(value="/banks/{id}", method = RequestMethod.DELETE)
    public void deleteBank(@PathVariable(value="id") Long id) {
		bankRepository.delete(id);
    }

}

