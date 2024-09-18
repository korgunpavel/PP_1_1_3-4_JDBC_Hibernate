package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl extends Util implements UserDao {

    SessionFactory sessionFactory;

    public UserDaoHibernateImpl() {
        sessionFactory = getConfiguration();
        sessionFactory.openSession();
    }

    @Override
    public void createUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createNativeQuery("""
                                                CREATE TABLE IF NOT EXISTS users (
                                                    id int PRIMARY KEY AUTO_INCREMENT,
                                                    name varchar(100),
                                                    lastName varchar(100),
                                                    age TINYINT)
                            """)
                    .executeUpdate();

            session.getTransaction().commit();
        } catch (Exception e) {
            if (sessionFactory.isOpen()) {
                sessionFactory.openSession().getTransaction().rollback();
            }
            throw e;
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createNativeQuery("DROP TABLE IF EXISTS users")
                    .executeUpdate();

            session.getTransaction().commit();
        } catch (Exception e) {
            if (sessionFactory.isOpen()) {
                sessionFactory.openSession().getTransaction().rollback();
            }
            throw e;
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            session.persist(new User(name, lastName, age));
            session.getTransaction().commit();
        } catch (Exception e) {
            if (sessionFactory.isOpen()) {
                sessionFactory.openSession().getTransaction().rollback();
            }
            throw e;
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.remove(session.get(User.class, id));

            session.getTransaction().commit();
        } catch (Exception e) {
            if (sessionFactory.isOpen()) {
                sessionFactory.openSession().getTransaction().rollback();
            }
            throw e;
        }
    }

    @Override
    public List<User> getAllUsers() {

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            return session.createQuery("FROM User", User.class).getResultList();
        } catch (Exception e) {
            if (sessionFactory.isOpen()) {
                sessionFactory.openSession().getTransaction().rollback();
            }
            throw e;
        }
    }

    @Override
    public void cleanUsersTable() {

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            session.createNativeQuery("TRUNCATE TABLE users").executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            if (sessionFactory.isOpen()) {
                sessionFactory.openSession().getTransaction().rollback();
            }
            throw e;
        }
    }
}
