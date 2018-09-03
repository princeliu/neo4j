package com.semptian.neo4j.service.serviceImpl;

import com.semptian.neo4j.entity.Person;
import com.semptian.neo4j.repository.PersonRepository;
import com.semptian.neo4j.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @Author Princeliu
 * @Date 2018/8/4 21:21
 * @Description
 */
@Service
public class PersonServiceImpl implements PersonService {
    @Autowired
    PersonRepository personRepository;
    @Override
    public List<Person> findByName(String name, int depth) {
        List<Person> personList = personRepository.findByName(name, depth);
        return personList;
    }

    @Override
    public Person save(Person person) {
        return personRepository.save(person);
    }

    @Override
    public Optional<Person> findById(long id) {
        return personRepository.findById(id);
    }
}
