package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Person;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IPersonRepository extends JpaRepository<Person, Integer> {

    @Query("select p.pid,p.pname,p.paddr,ph.regno,ph.number_type,ph.phone_number,ph.provider from Person p inner join p.phones ph")
    public List<Object[]> fetchDataUsingPersonByJoins();

}
