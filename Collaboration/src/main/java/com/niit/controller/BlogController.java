package com.niit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.niit.dao.BlogDAO;
import com.niit.model.Blog;



@RestController
public class BlogController {

	@Autowired
	private Blog blog;
	@Autowired
	private BlogDAO blogDAO;
	
	@GetMapping("/getallBlog")
	public List<Blog> getallBlog(){
		return blogDAO.getAllBlog();
	}
	
	@PutMapping("/approveblog/{blogID}")
	public Blog approveblog(@PathVariable("blogid")int blogid){
		blog=blogDAO.getBlog(blogid);
		blog.setBlogstatus('A');
		
		if(blogDAO.update(blog)){
			blog.setErrorCode("200");
			blog.setErrorMessage("SuccessFully approved");
		}else{
			blog.setErrorCode("404");
			blog.setErrorMessage("Not able to approve the blog");
			
		}return blog;
	}
}
