package com.spring.project.Dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.project.entity.User;


@Repository
public class UserDao {

  @Autowired
  private SessionFactory factory;

	public void savePerson(User user) {
    getSession().save(user);
	}

	public List<User> getPersons() {
		return getSession().createCriteria(User.class).list();
	}

	public void deletePerson(int id) {
    Session session= getSession();
    session.delete(session.get(User.class, id));
	}

	public void updatePerson(int id) {
    Session session= getSession();
    session.update(session.get(User.class, id));
	}

	public User getPerson(int id) {
    User user= null;
		try {
      user =  (User) getSession().get(User.class, id);
    } catch (Exception e) {
      System.out.println("no user found");
    }
    return user;
	}

  private Session getSession() {
		Session session = factory.getCurrentSession();
		if(session == null) {
			session= factory.openSession();
		} 
		return session;
	}
	
}
