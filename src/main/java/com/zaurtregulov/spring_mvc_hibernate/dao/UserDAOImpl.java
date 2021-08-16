package com.zaurtregulov.spring_mvc_hibernate.dao;

import com.zaurtregulov.spring_mvc_hibernate.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository()
public class UserDAOImpl implements UserDAO {  // Класс, который ответственен за работу с Базой данных


    @Autowired
    private SessionFactory sessionFactory;                                     // Внедряем зависимость от SessionFactory бина
    // чтобы DAO имел доступ к SessionFactory!

    @Override
    public List<User> getAllUsers() {

        Session session = sessionFactory.getCurrentSession();                     // Получаем сессию из SessionFactory!

//    List<User>  allUsers = session.createQuery("from User ",                    // 1 способ получить User-ов
//                    User.class).getResultList();

        Query<User> query = session.createQuery("from User  ", User.class);    // 2 способ получить User-ов
        List<User> allUsers =  query.getResultList();


        return allUsers;
    }

    @Override
    public void saveUser(User user) {

        Session session = sessionFactory.getCurrentSession();                // Получаем сессию

        session.saveOrUpdate(user);                               // Сохраняем user-а, либо изменяем, всё зависит от id.

    }

    @Override
    public User getUser(int id) {
        Session session = sessionFactory.getCurrentSession();

        User user = session.get(User.class, id);
        return user;
    }

    @Override
    public void deleteUser(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query<User> query = session.createQuery("delete from User " +
                "where id =:userId");
        query.setParameter("userId", id);
        query.executeUpdate();
    }
}
