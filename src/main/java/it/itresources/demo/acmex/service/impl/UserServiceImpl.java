package it.itresources.demo.acmex.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import it.itresources.demo.acmex.entity.UserEntity;
import it.itresources.demo.acmex.mapper.UserMapper;
import it.itresources.demo.acmex.model.User;
import it.itresources.demo.acmex.repository.UserRepository;
import it.itresources.demo.acmex.service.UserService;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	private static final AtomicLong counter = new AtomicLong();
	private static List<User> users;

	static {
		users = new ArrayList<User>();
		users.add(new User(counter.incrementAndGet(), "Frank", "Zappa", "fzappa@fake.com"));
		users.add(new User(counter.incrementAndGet(), "Frank", "Sinatra", "fsinatra@fake.com"));
		users.add(new User(counter.incrementAndGet(), "Warren", "Cuccurullo", "wcuccu@fake.com"));
		users.add(new User(counter.incrementAndGet(), "Tom", "Waits", "tommasoaspetta@fake.com"));
	}
	
	private final UserRepository userRepository;
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		Assert.notNull(userRepository, "UserRepository cannot be null!");
		this.userRepository = userRepository;
	}

	public List<User> findAllUsers() {
		// return users;
		System.out.println("UserService FIND ALL");
		List<UserEntity> entities = userRepository.listAllUsers();
		
		List<User> users = UserMapper.entitiesToModels(entities);
		
		System.out.println("found " + users.size() + " users");
		return users;
	}

	public User findById(long id) {
		UserEntity entity = this.userRepository.findUserById(id);
		/*User user = new User(entity.getId(), entity.getName(), entity.getSurname(), entity.getEmail());
		return user;*/
		User user = UserMapper.entityToModel(entity);
		return user;
	}

	public User findByName(String name) {
		for (User user : users) {
			if (user.getName().equalsIgnoreCase(name)) {
				return user;
			}
		}
		return null;
	}
	
	private String genTmpPassword(User user) {
		return "CICCIONE " + user.getName();
	}

	public void saveUser(User user) {
		//user.setId(counter.incrementAndGet());
		// UserEntity entity = new UserEntity(null, user.getName(), user.getSurname(), user.getEmail(), "segretissima");
		UserEntity entity = UserMapper.modelToEntity(user);
		entity.setPassword(genTmpPassword(user));
		userRepository.saveOrUpdate(entity);
	}

	public void updateUser(User user) {
		int index = users.indexOf(user);
		users.set(index, user);
	}

	public void deleteUserById(long id) {

		for (Iterator<User> iterator = users.iterator(); iterator.hasNext();) {
			User user = iterator.next();
			if (user.getId() == id) {
				iterator.remove();
			}
		}
	}

	public void deleteAllUsers() {
		users.clear();
	}

	public boolean isUserExist(User user) {
		return findByName(user.getName()) != null;
	}

}
