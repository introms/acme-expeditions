package it.itresources.demo.acmex.repository;

import java.util.List;

import it.itresources.demo.acmex.entity.UserEntity;

public interface UserRepository {
 
 public List<UserEntity> listAllUsers();
 
 public void saveOrUpdate(UserEntity user);
 
 public UserEntity findUserById(long id);
 
 public void deleteUser(long id);
 
}