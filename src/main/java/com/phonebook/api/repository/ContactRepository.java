package com.phonebook.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.phonebook.api.domain.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

	@Query("select c from Contact c where c.user.id = ?1")
	Iterable<Contact> findContactsByUserId(Long userId);

}
