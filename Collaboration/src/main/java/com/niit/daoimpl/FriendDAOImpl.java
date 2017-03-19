package com.niit.daoimpl;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.niit.dao.FriendDAO;
import com.niit.model.Friend;

@EnableTransactionManagement
@Repository("friendDAO")
public class FriendDAOImpl implements FriendDAO{
	
	@Autowired
	private SessionFactory sessionFactory;

	
	@Transactional
	public void addfriend(Friend friend){
		System.out.println("hii am inside daoimpl");
		sessionFactory.getCurrentSession().save(friend);
}
	@Transactional
	public List<Friend> notifications(String username){

		System.out.println("am inside notification backend daoimpl");
String hql="from Friend where username2= '"+username+"'and friendstatus='w'";
	List<Friend> list= sessionFactory.openSession().createQuery(hql).list();
	return list;
	}
	
	@Transactional
	public void acceptfriend(Friend friend){
		System.out.println("am inside acceptfriend daoimpl");
		Session session=sessionFactory.openSession();
		String hql="from Friend where username1= '"+friend.getUsername1()+"'and username2='"+friend.getUsername2()+"'";
		friend= (Friend) session.createQuery(hql).uniqueResult();
		friend.setFriendstatus('f');
		sessionFactory.getCurrentSession().saveOrUpdate(friend);
				
				
		hql="from Friend where username1= '"+friend.getUsername2()+"'and username2='"+friend.getUsername1()+"'";
		if( session.createQuery(hql).uniqueResult()==null){
			Friend friend1= new Friend();
			friend1.setUsername1(friend.getUsername2());
			friend1.setUsername2(friend.getUsername1());
			friend1.setFriendstatus('f');
			sessionFactory.getCurrentSession().save(friend1);
			
		}else{
			friend.setFriendstatus('f');
			sessionFactory.getCurrentSession().saveOrUpdate(friend);
					
		}
				
				session.close();
	
		}

	@Transactional
	public List<Friend> friendslist(String username){
		Session session=sessionFactory.openSession();
		String hql="from Friend where username1= '"+username+"'and friendstatus='f'";
		List<Friend> list= sessionFactory.openSession().createQuery(hql).list();

		return list;
	}

	@Transactional
	public void unfriend(String username1, String username2){
	Session session=sessionFactory.openSession();
	String hql="from Friend where username1= '"+username1+"'and username2='"+username2+"'";
	Friend friend= new Friend();
	friend=(Friend) session.createQuery(hql).uniqueResult();
	sessionFactory.getCurrentSession().delete(friend);

	 hql="from Friend where username1= '"+username2+"'and username2='"+username1+"'";
	friend=(Friend) session.createQuery(hql).uniqueResult();
	sessionFactory.getCurrentSession().delete(friend);
	
	
	}
	
}
