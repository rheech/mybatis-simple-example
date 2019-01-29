package com.cheonghyun.example.mybatis.dao;

import com.cheonghyun.example.mybatis.vo.Person;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class PersonDAO {

    private SqlSessionFactory sqlSessionFactory = null;

    public PersonDAO(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public List<Person> selectAll() {
        List<Person> list = null;

        SqlSession session = sqlSessionFactory.openSession();

        try {
            list = session.selectList("Person.selectAll");
        } finally {
            session.close();
        }

        System.out.println(String.format("selectAll() --> %s", list));

        return list;
    }

    public Person selectById(int id) {
        Person person = null;
        SqlSession session = sqlSessionFactory.openSession();

        try {
            person = session.selectOne("Person.selectById", id);
        } finally {
            session.close();
        }

        System.out.println(String.format("selectById(%d) -> %s", id, person));

        return person;
    }

    public int insert(Person person) {
        int id = -1;
        SqlSession session = sqlSessionFactory.openSession();

        try {
            id = session.insert("Person.insert", person);
        } finally {
            session.commit();
            session.close();
        }

        System.out.println(String.format("insert(%s) --> %d", person, person.getId()));

        return id;
    }

    public void update(Person person) {
        int id = -1;

        SqlSession session = sqlSessionFactory.openSession();

        try {
            id = session.update("Person.update", person);
        } finally {
            session.commit();
            session.close();
        }

        System.out.println(String.format("update(%s) --> updated", person));
    }

    public void delete(int id) {
        SqlSession session = sqlSessionFactory.openSession();

        try {
            session.delete("Person.delete", id);
        } finally {
            session.commit();
        }

        System.out.println(String.format("delete(%d)", id));
    }
}
