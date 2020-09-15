package it.itresources.demo.acmex.repository;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import it.itresources.demo.acmex.entity.UserEntity;

@Repository
public class UserRepositoryImpl implements UserRepository {

	@Autowired
	private SessionFactory sessionFactory;

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserEntity> listAllUsers() {
		Criteria criteria = getSession().createCriteria(UserEntity.class);
		return (List<UserEntity>) criteria.list();
	}

	@Override
	public void saveOrUpdate(UserEntity user) {
		getSession().saveOrUpdate(user);
	}

	@Override
	public UserEntity findUserById(long id) {
		Criteria criteria = getSession().createCriteria(UserEntity.class);
		criteria.add(Restrictions.eq("id", id));
		return (UserEntity)criteria.uniqueResult();
	}

	@Override
	public void deleteUser(long id) {
		// TODO Auto-generated method stub

	}

}
