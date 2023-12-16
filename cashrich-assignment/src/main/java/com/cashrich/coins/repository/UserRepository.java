package com.cashrich.coins.repository;

import com.cashrich.coins.entity.User;
import com.cashrich.coins.entity.UserHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

@Repository
@Transactional
public class UserRepository {

    private final EntityManager entityManager;
    @Autowired
    public UserRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public User findById(Long id) {
        User user = entityManager.find(User.class, id);
        return user;
    }

    public User findByUsername(String userName) {
        try{
            TypedQuery<User> userTypedQuery = entityManager.createQuery("SELECT u FROM User u where user_name = :userName", User.class);
            userTypedQuery.setParameter("userName", userName);
            User user = userTypedQuery.getSingleResult();
            return user;
        } catch (NoResultException noResultException) {
            return null;
        }
    }

    public User save(User user) {
        entityManager.persist(user);
        return user;
    }

    public void saveUserHistory(User user) {
        UserHistory userHistory = new UserHistory(user);
        entityManager.persist(userHistory);
    }
}
