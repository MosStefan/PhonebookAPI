package com.phonebook.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.phonebook.api.domain.Contact;
import com.phonebook.api.services.ContactService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class ContactController {

	@Autowired
	ContactService contactService;

	@GetMapping("contacts/{userId}")
	public Iterable<Contact> findContactsByUser(@PathVariable Long userId) {
		return contactService.findContactsByUser(userId);
	}

	@GetMapping("contact/{contactId}")
	public Contact findContactById(@PathVariable Long contactId) {
		return contactService.findContactById(contactId);
	}

	@PostMapping("contacts/{userId}")
	public ResponseEntity<?> addOrEditContact(@Valid @RequestBody Contact contact, @PathVariable Long userId) {

		Contact newContact = contactService.saveOrUpdateContact(contact, userId);

		return new ResponseEntity<Contact>(newContact, HttpStatus.CREATED);
	}

	@DeleteMapping("contacts/{contactId}")
	public ResponseEntity<?> deleteContactById(@PathVariable Long contactId) {

		contactService.deleteContactById(contactId);

		return new ResponseEntity<String>("Contact with ID " + contactId + " is deleted", HttpStatus.OK);
	}

}
