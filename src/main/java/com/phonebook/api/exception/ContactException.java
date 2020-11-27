package com.phonebook.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ContactException extends RuntimeException {

	public ContactException(String message) {
		super(message);
	}
}
