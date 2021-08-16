package com.zaurtregulov.spring_mvc_hibernate.service;

import com.zaurtregulov.spring_mvc_hibernate.dao.UserDAO;
import com.zaurtregulov.spring_mvc_hibernate.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/*
Аннотация @Service отмечает класс, содержащий бизнес-логику. В иерархии компонентов приложения Service является соединительным
звеном между Controller-ом и DAO.
Для того чтобы пометить класс как Сервис, используем аннотацию @Service
 */
@Service
public class UserServiceImpl implements UserService{


    // Для того чтобы вызвать метод getAllUsers из класса UserDAOImpl, пропишем зависимость от DAO.
    @Autowired
    private UserDAO userDAO;



    @Override
    @Transactional
    public List<User> getAllUsers() {

        return userDAO.getAllUsers();       // В методе Service используем метод DAO
    }


    @Override
    @Transactional
    public void saveUser(User user) {
        userDAO.saveUser(user);
    }

    @Override
    @Transactional
    public User getUser(int id) {
        return userDAO.getUser(id);
    }

    @Override
    @Transactional
    public void deleteUser(int id) {

        userDAO.deleteUser(id);
    }
}
