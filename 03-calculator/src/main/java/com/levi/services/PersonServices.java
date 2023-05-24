package com.levi.services;

import com.levi.model.Person;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;

//spring cuidará da injeção de dependência
@Service
public class PersonServices {

    private final AtomicLong counter = new AtomicLong(); //simular id DB

    public Person findById(String id) {
        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Mateus");
        person.setLastName("Levi");
        person.setAddress("Amazonas");
        person.setGender("Male");
        return person;
    }

}
