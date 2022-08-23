package com.example.demo.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.demo.entity.Person;
import com.example.demo.entity.PhoneNumber;

@Component
public class AssociationMappingTestRunner implements CommandLineRunner {
	@Autowired
	private IPersonMgmtService service;

	@Override
	public void run(String... args) throws Exception {
	  Person per=new Person();
	  per.setPname("Srinu");
	  per.setPaddr("VNK");
	  
	  PhoneNumber ph1=new PhoneNumber();
	  ph1.setNumber_type("Home");
	  ph1.setPhone_number(9989428279L);
	  ph1.setProvider("Jio");
	  
	  PhoneNumber ph2=new PhoneNumber();
	  ph2.setNumber_type("Office");
	  ph1.setPhone_number(8142827919L);
	  ph1.setProvider("VI");
	  ph1.setPerson(per);
	  ph2.setPerson(per);
	  Set<PhoneNumber> phonesSet=Set.of(ph1,ph2);
	  per.setPhones(phonesSet);
	  
	  try {
		  System.out.println(service.saveDataUsingPerson(per));
	  }
	  catch(Exception e){
		  e.printStackTrace();
		  
	  }

	}

}
