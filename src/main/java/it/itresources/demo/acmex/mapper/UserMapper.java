package it.itresources.demo.acmex.mapper;

import java.util.ArrayList;
import java.util.List;

import it.itresources.demo.acmex.entity.UserEntity;
import it.itresources.demo.acmex.model.User;

public class UserMapper {
	
	public static UserEntity modelToEntity(User user) {
		UserEntity entity = new UserEntity(user.getId(), user.getName(), user.getSurname(), user.getEmail(), "");
		return entity;
	}
	
	public static User entityToModel(UserEntity entity) {
		User model = new User(entity.getId(), entity.getName(), entity.getSurname(), entity.getEmail());
		return model;
	}

	
	public static List<User> entitiesToModels(List<UserEntity> entities) {
		List<User> users = new ArrayList<>();
		
		for (UserEntity entity : entities) {
			users.add(UserMapper.entityToModel(entity));
		}
		
		return users;
	}
	
	public static List<UserEntity> modelsToEntity(List<User> models) {
		List<UserEntity> entities = new ArrayList<>();
		
		for (User model : models) {
			entities.add(UserMapper.modelToEntity(model));
		}
		
		return entities;
	}
}
