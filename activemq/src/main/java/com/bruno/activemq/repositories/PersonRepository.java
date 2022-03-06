package com.bruno.activemq.repositories;

import com.bruno.activemq.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

    public Person getByName(String name);

}