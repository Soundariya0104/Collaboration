package com.niit.dao;

import java.util.List;

import com.niit.model.Blog;
import com.niit.model.Comment;



public interface BlogDAO {
	public Blog getBlog(String blogname);
			public List<Blog> getAllBlog();
	public List<Blog> getAllblogs(String username);
	public boolean saveblog(Blog blog);
	public boolean update(Blog blog);
	public void deleteblog(String blogname);
	public void addcomment(Comment comment);
	public List<Comment> getcomments(String blogname);

		
		

}
