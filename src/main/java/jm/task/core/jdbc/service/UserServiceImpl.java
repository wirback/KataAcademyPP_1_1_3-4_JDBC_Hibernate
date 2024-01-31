package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
//    private final UserDaoJDBCImpl userDaoJDBCImpl = new UserDaoJDBCImpl();
    private final UserDaoHibernateImpl userDaoHibernateImpl = new UserDaoHibernateImpl();

    public void createUsersTable() {
//        userDaoJDBCImpl.createUsersTable();
        userDaoHibernateImpl.createUsersTable();
    }

    public void dropUsersTable() {
//        userDaoJDBCImpl.dropUsersTable();
        userDaoHibernateImpl.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
//        userDaoJDBCImpl.saveUser(name, lastName, age);
        userDaoHibernateImpl.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
//        userDaoJDBCImpl.removeUserById(id);
        userDaoHibernateImpl.removeUserById(id);
    }

    public List<User> getAllUsers() {
//        return userDaoJDBCImpl.getAllUsers();
        return userDaoHibernateImpl.getAllUsers();
    }

    public void cleanUsersTable() {
//        userDaoJDBCImpl.cleanUsersTable();
        userDaoHibernateImpl.cleanUsersTable();
    }
}
