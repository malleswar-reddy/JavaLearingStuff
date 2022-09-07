package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Person;
import com.example.demo.entity.PhoneNumber;
import com.example.demo.repository.IPersonRepository;
import com.example.demo.repository.IPhoneNumberRepository;

import javax.swing.text.html.Option;

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
		List<String> msgs=new ArrayList<String>();
		if(phoneSet.size()>0){
			for(PhoneNumber ph:phoneSet){
				msgs.add("PhoneNumbers are mapped  Person saved with"+phoneRepo.save(ph)+"Id value");
			}
		}
		return msgs.toString();
	}

	@Override
	public Iterable<Person> loadDataUsingParent() {
		Iterable<Person> it=personRepo.findAll();
		return it;
	}

	@Override
	public Iterable<PhoneNumber> loadDataUsingChild() {
		return phoneRepo.findAll();
	}

	@Override
	public String deletePersonAndHisPhoneNumbeByPid(int pid) {
		Optional<Person> opt=personRepo.findById(pid);
		if(opt.isEmpty()){
			return "Person not found";
		}
		else{
			Person per=opt.get();
			personRepo.delete(per);
			return pid+"Person and his Puhone Number deleted";

		}
	}

	@Override
	public String deleteAllPhoneNumbersOrfaPerson(int pid) {
		Optional<Person> opt=personRepo.findById(pid);
		if (opt.isEmpty()){
			return "Person not found";
		}
		else{
			Person per=opt.get();
			Set<PhoneNumber> phones=per.getPhones();
			int count=phones.size();
			phoneRepo.deleteAll(phones);
			per.setPhones(null);
			personRepo.save(per);
			return count+"No.of Phone Numbers are deleted for person"+pid;
		}
	}

	@Override
	public String removeAllPhoneNumbersOfaPersonByPid(int pid) {
		Optional<Person> opt=personRepo.findById(pid);
		if (opt.isEmpty()){
			return "Person not found";
		}
		else {
			int count= phoneRepo.deleteAllPhoneNumbersOfaPerson(pid);
			return count+"No.of phone numbers are deleted";
		}
	}

	@Override
	public List<Object[]> getDataUsingPersonByJoins() {
		return personRepo.fetchDataUsingPersonByJoins();
	}


//	public String saveDataUsingPhoneNumber(Set<PhoneNumber> phoneSet) {
//	   int count=phoneRepo.saveAll(phoneSet).size();
//	   return count+"no.of PhoneNumber obj mapped to Person obj are saved";
//	}

}
