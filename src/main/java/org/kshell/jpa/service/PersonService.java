package org.kshell.jpa.service;

import org.kshell.jpa.entity.Person;
import org.kshell.jpa.repository.PersonRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author kshell
 * @version 1.0
 * create by 2017-11-10
 * describe:
 */
@Service
public class PersonService {
    @Resource
    private PersonRepository personRepository;

    @Transactional
    public void init() {
        List<Person> personList = new ArrayList<Person>();

        for (int i = 'a'; i <= 'z'; i++) {
            Person person = new Person();
            String name = (char) i + "" + (char) i + (char) i;
            String emal = name + "@kshell.org";

            person.setLastName(name.toUpperCase());
            person.setEmail(emal.toLowerCase());
            person.setBirth(new Date());
            personList.add(person);
        }
        personRepository.save(personList);
    }
}
