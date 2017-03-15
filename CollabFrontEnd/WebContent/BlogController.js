app.controller('BlogController', ['$scope', 'BlogService','$location','$rootScope',function($scope, BlogService,$location,$routeParams,$rootScope) {
	console.log("inside BlogController...")
          var self = this;
          self.blog={blogname:'',blogdescription:'',username:'',blogdateTime:'',blogstatus:'',blogreason:''};
          self.blogs=[];
          self.blogs1=[];
          
          
         self.getBlog = getblog

//-------------------------------------------------------------------------GET BLOG ---------------------------------------------------------------------------------------
          function getblog(blogname){
        	  BlogService.getblog(blogname)
                  .then(  
                		       function(d) {
                            	   console.log('inside getselected')
                                   
                            	   self.blogs1 = d;
                            	   console.log(d)
                            	   
                                     $location.path('/viewblog'); 
                               },
                                function(errResponse){
                                    console.error('Error while get Blogs controller');
                                }
                       );
          };
          
//---------------------------------------------------------------------------ALL BLOG LIST-------------------------------------------------------------------------------------        
          self.fetchAllBlogs = function(){
              BlogService.fetchAllBlogs()
                  .then(
                               function(d) {
                                    self.blogs = d;
                                 console.log('inside fetch all blog')
                           
                               },
                                function(errResponse){
                                    console.error('Error while fetching Blogs');
                                }
                       );
          };
  //--------------------------------------------------------------------------ADD BLOG--------------------------------------------------------------------------------------          
          self.createBlog = function(blog){
              BlogService.createBlog(blog)
                      .then(
                    		  
                    		  function(d) {
									
									self.blog = d;
									if (self.blog.errorCode == "404")

									{
										alert(self.blog.errorMessage)

										self.blog.username = "";
										self.blog.password = "";

									} else { 
												
												self.fetchAllUsers(); 
													$location.path('/addblog');
												

									}}

                  );
          };
//--------------------------------------------------------------------------UPDATE BLOG--------------------------------------------------------------------------------------          
         self.updateBlog = function(blog, id){
              BlogService.updateBlog(blog, id)
                      .then(
                              self.fetchAllBlogs, 
                              function(errResponse){
                                   console.error('Error while updating Blog.');
                              } 
                  );
          };

//--------------------------------------------------------------------------ACCEPT BLOG--------------------------------------------------------------------------------------          
          self.accept = function(id) {
				console.log("accept...")
				JobService
						.accept(id)
						.then(
								function(d) {
									self.job = d;
									self.fetchAllBlogs
									$location.path("/manage_jobs")
									alert(self.job.errorMessage)
									
								},
								
								function(errResponse) {
									console
											.error('Error while accepting the blog.');
								});
			};  
//--------------------------------------------------------------------------REJECT BLOG--------------------------------------------------------------------------------------          
			self.reject = function( id) {
				console.log("reject...")
				var reason = prompt("Please enter the reason");
				JobService
						.reject(id,reason)
						.then(
								function(d) {
									self.job = d;
									self.fetchAllBlogs
									$location.path("/manage_jobs")
									alert(self.job.errorMessage)
									
								},
								function(errResponse) {
									console
											.error('Error while updating User.');
								});
			};

			
			
			
			
			
			
			
			

     self.fetchAllBlogs();
 
          self.addblog = function() {
           
                  self.createBlog(self.blog);
              	self.reset();
				
            
          };
    
               
          self.edit = function(id){
              console.log('id to be edited', id);
              for(var i = 0; i < self.blogs.length; i++){
                  if(self.blogs[i].id === id) {
                     self.blog = angular.copy(self.blogs[i]);
                     break;
                  }
              }
          };
               
          self.remove = function(id){
              console.log('id to be deleted', id);
              if(self.blog.id === id) {
                 self.reset();
              }
              self.deleteBlog(id);
          };
 
           
          self.reset = function(){
        	   self.blog={id:'',title:'',description:'',BlogID:'',dateTime:'',status:'',reason:'',errorMessage : ''};

          };
 
      }]);