package com.niit.daoimpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.niit.dao.FriendDAO;
import com.niit.model.Friend;

@EnableTransactionManagement
@Repository("friendDAO")
public class FriendDAOImpl implements FriendDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	
	public FriendDAOImpl(SessionFactory sessionFactory)
	{
	this.sessionFactory = sessionFactory;
	}
	
	/*private Integer getMaxId()
	{
		String hql= "select max(id) from Friend";
		Query query =sessionFactory.getCurrentSession().createQuery(hql);
		Integer maxId = (Integer) query.uniqueResult();
		return maxId;	
	}*/
	@Transactional
	public boolean save(Friend friend)
	{
	try {
		// Session session = sessionFactory.getCurrentSession();
	//friend.setId(getMaxId()+1);
		sessionFactory.getCurrentSession().save(friend);
		return true;
	}
	catch(Exception e)
	{
		e.printStackTrace();
		return false;
	}
	}


@Transactional
public boolean update(Friend friend)
{
try {

	sessionFactory.getCurrentSession().update(friend);
	return true;
}
catch(Exception e)
{
	e.printStackTrace();
	return false;
}
}



@Transactional
	public void delete(String userid,String friendid)
	{
		Friend FriendToDelete = new Friend();
		FriendToDelete.setUserid(userid);
		FriendToDelete.setFriendid(friendid);
			sessionFactory.getCurrentSession().delete(FriendToDelete);
		
	}


@Transactional
public List<Friend> getmyfriends(String userid)
{
	String hql = "from Friend where userid= "+" '" +userid+ "' and status='"+"A'";
	Query query =sessionFactory.getCurrentSession().createQuery(hql);
	@SuppressWarnings("unchecked")
	List<Friend> list = query.list();
	return list;
}

@Transactional
public List<Friend> getNewFriendrequest(String userid)
{
	String hql = "from Friend where userid= "+" '" +userid+ "' and status='"+"N'";
	Query query =sessionFactory.getCurrentSession().createQuery(hql);
	@SuppressWarnings("unchecked")
	List<Friend> list = query.list();
	return list;
}

@Transactional
public Friend get(String userid,String friendid)
{
	String hql = "from Friend where userid= '" + userid + "' and " + " friendid ='" + friendid + "'";
	Query query =sessionFactory.getCurrentSession().createQuery(hql);
	@SuppressWarnings("unchecked")
	List<Friend> list = (List<Friend>) query.list();

	if (list != null && !list.isEmpty()) {
		return list.get(0);
	}

	return null ;
}

@Transactional
public void setOnLine(String userid)
{
	String hql ="update Friend SET is_online='Y' where userid= "+" '" +userid+ "'";
	Query query =sessionFactory.getCurrentSession().createQuery(hql);
	query.executeUpdate();
}

@Transactional
public void setOffLine(String userid)
{
	String hql ="update Friend SET is_online='N' where userid= "+" '" +userid+ "'";
	Query query =sessionFactory.getCurrentSession().createQuery(hql);
	query.executeUpdate();
	
}
}