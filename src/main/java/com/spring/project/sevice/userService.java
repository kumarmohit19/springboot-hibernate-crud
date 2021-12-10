package com.spring.project.sevice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.project.Dao.UserDao;
import com.spring.project.entity.User;

@Service
@Transactional
public class userService {

  @Autowired
	private UserDao userDAO;

	public void savePerson(User user) {
    userDAO.savePerson(user);
	}

	public List<User> getPersons() {
		return userDAO.getPersons();
	}

	public void deletePerson(int id) {
    userDAO.deletePerson(id);
	}

	public void updatePerson(int id) {
    userDAO.updatePerson(id);
	}

	public User getPerson(int id) {
		return userDAO.getPerson(id);
	}


}
