package com.niit.daoimpl;

import java.util.List;



import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.niit.dao.UserDAO;
import com.niit.model.User;


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
	@Transactional
	public List<User> list(){
		String hql="from User";
		return sessionFactory.getCurrentSession().createQuery(hql).list();
		
	}
	
	//-----------------------------------------------------login------------------------------------------------------------------------------------------------------------		
	@SuppressWarnings({ "unchecked", "deprecation" })
	@Transactional
	public User validate(String username, String password) {
		System.out.println("hii");
		
	String hql="from User where id='"+username+"'and password='"+password+"'";
	System.out.println("hii1");
	
	Query<User> query=sessionFactory.getCurrentSession().createQuery(hql);
	System.out.println("hii2");
	return (User) query.uniqueResult();
	}

	
	
	//-----------------------------------------------------registration------------------------------------------------------------------------------------------------------------	
	@Transactional
	public boolean save(User user) {
	try{
		sessionFactory.getCurrentSession().save(user);
		return true;
	}catch(Exception e){
		
	}
		return false;
	}
}
