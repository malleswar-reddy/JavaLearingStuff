package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Person;

public interface IPersonRepository extends JpaRepository<Person, Integer> {

}
