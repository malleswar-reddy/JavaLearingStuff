package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.PhoneNumber;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

@Transactional
public interface IPhoneNumberRepository extends JpaRepository<PhoneNumber, Integer> {

    @Query(value="delete from phone_numbers_otm where person_id=:id",nativeQuery = true)
    @Modifying
    public int deleteAllPhoneNumbersOfaPerson(int id);

}
