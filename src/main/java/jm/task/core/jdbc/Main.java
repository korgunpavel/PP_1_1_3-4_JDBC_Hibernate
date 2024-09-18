package jm.task.core.jdbc;


import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

public class Main {

    public static void main(String[] args) {

        UserService userDao = new UserServiceImpl();

        userDao.createUsersTable();

        userDao.saveUser("name1","lastname1", (byte) 20);
        userDao.saveUser("name2","lastname2", (byte) 34);
        userDao.saveUser("name3","lastname3", (byte) 42);
        userDao.saveUser("name4","lastname4", (byte) 18);

        System.out.println(userDao.getAllUsers());
        userDao.cleanUsersTable();
        userDao.dropUsersTable();

    }

}
