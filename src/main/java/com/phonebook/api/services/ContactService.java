package com.phonebook.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phonebook.api.domain.Contact;
import com.phonebook.api.domain.User;
import com.phonebook.api.exception.ContactException;
import com.phonebook.api.repository.ContactRepository;
import com.phonebook.api.repository.UserRepository;

@Service
public class ContactService {

	@Autowired
	ContactRepository contactRepository;

	@Autowired
	UserRepository userRepository;

	public Iterable<Contact> findContactsByUser(Long userId) {
		return contactRepository.findContactsByUserId(userId);
	}

	public Contact findContactById(Long contactId) {
		return contactRepository.findById(contactId).get();
	}

	public Contact saveOrUpdateContact(Contact contact, Long userId) {

		try {

			User user = userRepository.findById(userId).get();
			contact.setUser(user);

			return contactRepository.save(contact);

		} catch (Exception ex) {
			throw new ContactException("User not exist");
		}
	}

	public void deleteContactById(Long contactId) {

		Contact contact = contactRepository.findById(contactId).get();
		if (contact == null) {
			throw new ContactException("Contact not exist");
		}
		contactRepository.delete(contact);
	}
}
