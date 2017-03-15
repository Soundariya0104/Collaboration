package com.niit.daoimpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

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
	@Transactional
	public Blog getBlog(String blogname){
		return (Blog) sessionFactory.getCurrentSession().get(Blog.class, blogname);
	
	}
	
	
	//------------------------------------------------------GET ALL BLOG---------------------------------------------------------------
	@SuppressWarnings({ "deprecation", "unchecked" })
	@Transactional
	public List<Blog> getAllBlog(){
		Query<Blog> query = sessionFactory.getCurrentSession().createQuery("from Blog");
	return query.list();
	}
	
	//------------------------------------------------------GET all BLOG of by user---------------------------------------------------------------
	@Transactional
	public List<Blog> getAllblogs(String username){
		String hql="from Blog where username='"+username+"'";
	Query<Blog> query = sessionFactory.getCurrentSession().createQuery(hql);
return query.list();
	}
	
	//------------------------------------------------------SAVE BLOG---------------------------------------------------------------
	@Transactional
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
	@Transactional
	public boolean update(Blog blog){
		return false;
	}
}
