package com.example.practica.data;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

@Service
public class DataServiceImpl implements DataService {

    @Autowired
    DataService dataService;
    @Autowired
    DataJpaRepository dataJpaRepository;

    @Autowired
    DataRepository dataRepository;

    @Override
    public Person jdbcQueryObject(Long id) {

        return dataRepository.jdbcQueryObject(id);
    }

    @Override
    public Person jpaQueryObject(Long id) {

        return dataJpaRepository.getById(id);
    }
//
//    @Override
//    public Person jpaInsertObject(String name, String role) {
//        Person person = new Person();
//        person.setName(name);
//        person.setRole(role);
//
//        person=dataJpaRepository.save(person);
//        return person;
//    }
    @Override
    @Transactional
    public Person jpaInsertObject(String name, String role) {
        dataService.jpaInsertDummy();
        Person person = new Person();
        person.setName(name);
        person.setRole(role);

        person=dataJpaRepository.save(person);
        try{
            Thread.sleep(30*1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return person;
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void jpaInsertDummy(){
        Person person = new Person();
        person.setName("Dummy");
        person.setRole("dummy");

        dataJpaRepository.save(person);
    }

}
