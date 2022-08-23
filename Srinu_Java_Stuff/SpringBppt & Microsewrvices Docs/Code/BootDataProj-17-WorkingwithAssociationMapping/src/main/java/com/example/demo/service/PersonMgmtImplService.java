package com.example.demo.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Person;
import com.example.demo.entity.PhoneNumber;
import com.example.demo.repository.IPersonRepository;
import com.example.demo.repository.IPhoneNumberRepository;

@Service("PersonService")
public class PersonMgmtImplService implements IPersonMgmtService {
	@Autowired
	private IPersonRepository personRepo;
	@Autowired
	private IPhoneNumberRepository phoneRepo;
	@Override

	public String saveDataUsingPerson(Person per) {
	    return "Person and his PhoneNumber saved with"+personRepo.save(per)+"id value";
	}
	@Override

	public String saveDataUsingPhoneNumber(Set<PhoneNumber> phoneSet) {
	   int count=phoneRepo.saveAll(phoneSet).size();
	   return count+"no.of PhoneNumber obj mapped to Rerson obj are saved";
	}

}
