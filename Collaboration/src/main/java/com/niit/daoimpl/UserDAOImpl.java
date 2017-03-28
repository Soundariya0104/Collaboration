package com.niit.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.niit.dao.UserDAO;
import com.niit.model.User;

@SuppressWarnings("deprecation")
@EnableTransactionManagement
@Repository("userDAO")
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	public UserDAOImpl(SessionFactory sessionFactory){
		this.sessionFactory=sessionFactory;
	}

	//-----------------------------------------------------getby id------------------------------------------------------------------------------------------------------------	
	@Transactional
	public User get(String username) {
return sessionFactory.getCurrentSession().get(User.class, username);
	}

	//-----------------------------------------------------getlist------------------------------------------------------------------------------------------------------------		
	@SuppressWarnings("unchecked")
	@Transactional
	public List<User> list(){
		String hql="from User";
		return sessionFactory.getCurrentSession().createQuery(hql).list();
		
	}
	
	@SuppressWarnings("rawtypes")
	@Transactional
	public User validate(String username, String password) {
		String hql = "from User where username = '" + username + "' and password = '" + password +"'";
		Query query =  sessionFactory.getCurrentSession().createQuery(hql);
		return (User) query.uniqueResult();

		
	}
	
	

	
	//-----------------------------------------------------registration------------------------------------------------------------------------------------------------------------	
	@Transactional
	public boolean save(User user) {
	try{
		sessionFactory.getCurrentSession().save(user);
		return true;
	}catch(Exception e){
		System.out.println("error has been catched");
		return false;
	}
		
	}
	
	@Transactional
	public boolean saveonline(User user) {
	try{
		user.setIs_online('y');
		sessionFactory.getCurrentSession().saveOrUpdate(user);
		return true;
	}catch(Exception e){
		
	}
		return false;
	}
	@Transactional
	public boolean offline(String username) {
	try{
		String hql = "from User where username = '" + username + "'";
		Query query =  sessionFactory.getCurrentSession().createQuery(hql);
		User user=(User) query.uniqueResult();

	System.out.println("inside logout");
	user.setUsername(username);	
	user.setIs_online('N');
		sessionFactory.getCurrentSession().saveOrUpdate(user);
		return true;
	}catch(Exception e){
		
	}
		return false;
	}
}