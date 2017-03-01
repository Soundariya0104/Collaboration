package com.niit.daoimpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.niit.dao.BlogDAO;
import com.niit.model.Blog;


@EnableTransactionManagement
@Repository("blogDAO")
public class BlogDAOImpl implements BlogDAO{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public BlogDAOImpl(SessionFactory sessionFactory){
		this.sessionFactory=sessionFactory;
	}

	
	//------------------------------------------------------GET BLOG---------------------------------------------------------------
	public Blog getBlog(int blogid){
		return (Blog) sessionFactory.getCurrentSession().get(Blog.class, blogid);
	
	}
	
	
	//------------------------------------------------------GET ALL BLOG---------------------------------------------------------------
	public List<Blog> getAllBlog(){
		Query query = sessionFactory.getCurrentSession().createQuery("from BlogModel");
	return query.list();
	}
	
	//------------------------------------------------------GET all BLOG of by user---------------------------------------------------------------
	public List<Blog> getAllblogs(String username){
		String hql="from Blog where username='"+username+"'";
	Query query = sessionFactory.getCurrentSession().createQuery(hql);
return query.list();
	}
	
	//------------------------------------------------------SAVE BLOG---------------------------------------------------------------
	public boolean saveblog(Blog blog){
		try{
			sessionFactory.getCurrentSession().save(blog);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	//------------------------------------------------------update BLOG---------------------------------------------------------------
	public boolean update(Blog blog){
		return false;
	}
}
