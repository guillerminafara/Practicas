package com.example.practica.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.capgemini.pruebasspring.config.BeanMapper;
@RestController
@RequestMapping(path = "/data")
public class DataController {
    @Autowired
    BeanMapper beanMapper;

    @Autowired
    DataService dataService;

    @RequestMapping(path = "/jdbcQueryObject/{id}", method = RequestMethod.GET)
    public PersonDTO jdbcQueryObject(@PathVariable Long id){
        return beanMapper.map(dataService.jdbcQueryObject(id), PersonDTO.class;
    }
    @RequestMapping(path = "/jpaQueryObject/{id}", method = RequestMethod.GET)
    public PersonDTO jpaQueryObject(@PathVariable Long id){
        return beanMapper.map(dataService.jdbcQueryObject(id), PersonDTO.class;
    }

    @RequestMapping(path = "/jpaInsertObject/{name}/{role}", method = RequestMethod.PUT)
    public PersonDTO jpaInsertObject(@PathVariable String name, @PathVariable String role ){
        return beanMapper.map(dataService.jpaInsertObject(name, role), PersonDTO.class;
    }

}
