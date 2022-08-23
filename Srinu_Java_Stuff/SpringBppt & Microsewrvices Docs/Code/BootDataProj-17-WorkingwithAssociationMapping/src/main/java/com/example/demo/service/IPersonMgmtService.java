package com.example.demo.service;

import java.util.Set;

import com.example.demo.entity.Person;
import com.example.demo.entity.PhoneNumber;

public interface IPersonMgmtService {
	public String saveDataUsingPerson(Person per);
	public String saveDataUsingPhoneNumber(Set<PhoneNumber> phoneSet);

}
