package com.spice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spice.dao.repository.ContactRepository;
import com.spice.dao.repository.entity.ContactEntity;

@RestController
public class ContactController {
	@Autowired
	private ContactRepository contactRepository;
    
    @RequestMapping(value="/contacts", method = RequestMethod.GET)
    public List<ContactEntity> getContact() throws Exception {
    	return contactRepository.findAll();
	}
    
	@RequestMapping(value="/contacts/{id}", method = RequestMethod.GET)
    public ContactEntity getContact(@PathVariable(value="id") Long id) {
		return contactRepository.findOne(id) ;
    }
    
	@RequestMapping(value="/contacts", method = RequestMethod.POST)
    public ContactEntity saveContact(@RequestBody ContactEntity contact) throws Exception {
		 return contactRepository.save(contact);
    }
    
	@RequestMapping(value="/contacts/{id}", method = RequestMethod.PUT)
    public ContactEntity updateContact(@PathVariable(value="id") Long id, @RequestBody ContactEntity contact) throws Exception {
		return contactRepository.save(contact);
    }
    
	@RequestMapping(value="/contacts/{id}", method = RequestMethod.DELETE)
    public void deleteContact(@PathVariable(value="id") Long id) {
		contactRepository.delete(id);
    }

}

