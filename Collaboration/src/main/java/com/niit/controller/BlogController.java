package com.niit.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@PostMapping(value = "/createblog")
	public ResponseEntity<Blog> createBlog(@RequestBody Blog blog, HttpSession session) {
		
		String loggedInUserID = (String) session.getAttribute("loggedInUserID");
		blog.setUserid(loggedInUserID);
		blog.setBlogstatus('N');// A->Accepted,  R->Rejected
		
		
		if(blogDAO.saveblog(blog)){
			blog=new Blog();
			blog.setErrorCode("200");
			blog.setErrorMessage("Blog created");
			
		}else{
			blog.setErrorCode("400");
			blog.setErrorMessage("blog not created ok, try again....");

				}

		return new ResponseEntity<Blog>(blog, HttpStatus.OK);
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
			blog.setErrorMessage("No able to approve the blog");
			
		}return blog;
	}
}