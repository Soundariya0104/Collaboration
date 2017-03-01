package com.niit.dao;

import java.util.List;

import com.niit.model.Blog;



public interface BlogDAO {
	public Blog getBlog(int blogid);
	public List<Blog> getAllBlog();
	public List<Blog> getAllblogs(String username);
	public boolean saveblog(Blog blog);
	public boolean update(Blog blog);

}