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
//        Person per=new Person();
//        per.setPname("Srinu");
//        per.setPaddr("VNK");
//
//        PhoneNumber ph1=new PhoneNumber();
//        ph1.setNumber_type("Home");
//        ph1.setPhone_number(9989428279L);
//        ph1.setProvider("Jio");
//        PhoneNumber ph2=new PhoneNumber();
//        ph2.setNumber_type("Office");
//        ph2.setPhone_number(8142827919L);
//        ph2.setProvider("VI");
//        ph1.setPerson(per);
//        ph2.setPerson(per);
//        Set<PhoneNumber> phonesSet=Set.of(ph1,ph2);
//        per.setPhones(phonesSet);
//
//        try{
//            System.out.println(service.saveDataUsingPerson(per));
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//        Person per=new Person();
//        per.setPname("Ravi Teja");
//        per.setPaddr("NRT");
//        PhoneNumber ph1=new PhoneNumber();
//        ph1.setNumber_type("Home");
//        ph1.setPhone_number(9345637892L);
//        ph1.setProvider("Airtel");
//        PhoneNumber ph2=new PhoneNumber();
//        ph2.setNumber_type("Office");
//        ph2.setPhone_number(6302756296L);
//        ph2.setProvider("BSNL");
//        ph1.setPerson(per);
//        ph2.setPerson(per);
//        Set<PhoneNumber> phonesSet=new HashSet<PhoneNumber>();
//        phonesSet.add(ph1);
//        phonesSet.add(ph2);
//        per.setPhones(phonesSet);
//
//        try{
//            System.out.println(service.saveDataUsingPhoneNumber(phonesSet));
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//        Person per=new Person();
//        per.setPname("Ashok");
//        per.setPaddr("GNT");
//        PhoneNumber ph1=new PhoneNumber();
//        ph1.setNumber_type("Home");
//        ph1.setPhone_number(924875972L);
//        ph1.setProvider("Idea");
//        PhoneNumber ph2=new PhoneNumber();
//        ph2.setNumber_type("Office");
//        ph2.setPhone_number(62864933475L);
//        ph2.setProvider("Docomo");
//        ph1.setPerson(per);
//        ph2.setPerson(per);
//        Set<PhoneNumber> phonesSet=new HashSet<PhoneNumber>();
//        phonesSet.add(ph1);
//        phonesSet.add(ph2);
//        per.setPhones(phonesSet);
//
//        try{
//            System.out.println(service.saveDataUsingPhoneNumber(phonesSet));
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//        service.loadDataUsingParent().forEach(per->{
//            System.out.println("pernt::"+per);
//            Set<PhoneNumber> childs=per.getPhones();
//            childs.forEach(ph->{
//                System.out.println("Child::"+ph);
//            });
//        });
//        service.loadDataUsingChild().forEach(ph->{
//            System.out.println("Child::"+ph);
//            Person person=ph.getPerson();
//            System.out.println("Parent::"+person);
//            System.out.println("---------");
//        });
        //System.out.println(service.deletePersonAndHisPhoneNumbeByPid(1021));
        //System.out.println(service.deleteAllPhoneNumbersOrfaPerson(1001));
        System.out.println(service.removeAllPhoneNumbersOfaPersonByPid(1000));
    }
}
