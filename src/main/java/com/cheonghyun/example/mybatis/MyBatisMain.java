package com.cheonghyun.example.mybatis;

import com.cheonghyun.example.mybatis.dao.PersonDAO;
import com.cheonghyun.example.mybatis.mbatis.MyBatisConnectionFactory;
import com.cheonghyun.example.mybatis.vo.Person;

import java.util.List;

public class MyBatisMain {

    public static void main(String[] args) {
        PersonDAO personDAO = new PersonDAO(MyBatisConnectionFactory.getSqlSessionFactory());

        Person person = new Person();
        person.setName("Person 1");

        personDAO.insert(person);

        person.setName("Person 2");
        int id = personDAO.insert(person);

        personDAO.selectById(id);

        List<Person> persons = personDAO.selectAll();

        for (int i = 0; i < persons.size(); i++) {
            persons.get(i).setName(String.format("Person Name %d", i));
            personDAO.update(persons.get(i));
        }

        persons = personDAO.selectAll();
    }
}
