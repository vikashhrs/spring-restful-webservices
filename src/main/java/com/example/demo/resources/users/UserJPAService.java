package com.example.demo.resources.users;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserJPAService {
	
	private static List<User> users =  new ArrayList<>();
	private static int usersCount = 5;
	
	static {
		users.add(new User(1, "User 1", new Date()));
		users.add(new User(2, "User 2", new Date()));
		users.add(new User(3, "User 3", new Date()));
		users.add(new User(4, "User 4", new Date()));
		users.add(new User(5, "User 5", new Date()));
	}
	
	public List<User> findAll(){
		return users;
	}
	
	public User save(User user) {
		if(user.getId() == null) {
			user.setId(++usersCount);
		}
		users.add(user);
		return user;
	}
	
	public User findOne(int id) {
		for(User user: users) {
			if(user.getId() == id)
				return user;
		}
		return null;
	}
	
	public User deleteOne(int id) {
		Iterator<User> iterator = users.iterator();
		while(iterator.hasNext()) {
			User user = iterator.next();
			if(user.getId() == id) {
				iterator.remove();
				return user;
			}
		}
		throw new UserNotFoundException("id-"+id);
	}
	
	
}
