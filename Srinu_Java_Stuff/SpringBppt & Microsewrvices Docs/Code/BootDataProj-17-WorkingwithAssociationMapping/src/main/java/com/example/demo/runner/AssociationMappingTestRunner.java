package com.example.demo.runner;

import com.example.demo.entity.Person;
import com.example.demo.entity.PhoneNumber;
import com.example.demo.repository.IPhoneNumberRepository;
import com.example.demo.service.IPersonMgmtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
@Component
public class AssociationMappingTestRunner implements CommandLineRunner {

    @Autowired
    private IPersonMgmtService service;

    @Autowired
    private IPhoneNumberRepository iPhoneNumberRepository;


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
        ph2.setPhone_number(8142827919L);
        ph2.setProvider("VI");
        ph1.setPerson(per);
        ph2.setPerson(per);
        Set<PhoneNumber> phonesSet=Set.of(ph1,ph2);
        per.setPhones(phonesSet);

        try{
            System.out.println(service.saveDataUsingPerson(per));
        }
        catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("Done");
    }
}
