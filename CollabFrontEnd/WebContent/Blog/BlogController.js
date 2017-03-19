app.controller('BlogController', ['$scope', 'BlogService','$location','$rootScope','$cookieStore',
            function($scope, BlogService,$location,$routeParams,$rootScope,$cookieStore) {
	console.log("inside BlogController...")
          var self = this;
          self.blog={blogname:'',blogdescription:'',username:'',blogdateTime:'',blogstatus:'',blogreason:''};
        self.comment={comments:'',username:'',commentid:''}
          self.blogs=[];
        self.comment=[];
          self.userblog=[];
          self.editblog=[];
           
          self.getBlog = getblog
//-------------------------------------------------------------------------GET BLOG ---------------------------------------------------------------------------------------
          function getblog(blogname){
        	  BlogService.getblog(blogname)
                  .then(  
                		       function(d) {
                            	   console.log('inside getselected')
                                  console.log(d.blogname)
                                  
                            	   self.getcomments(d.blogname)
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
												
												self.fetchAllBlogs(); 
										self.reset();
													$location.path('/listblog');
												

									}}

                  );
          };
//--------------------------------------------------------------------------UPDATE BLOG--------------------------------------------------------------------------------------          
         self.editBlog = function(blogname){
        	 console.log('inside editblog')
              BlogService.editBlog(blogname)
                      .then( function(d) {
                    	  console.log('inside edit'+d)
                    	  console.log(d)
                    	  self.editblog=d;
                    	  $location.path("/addblog")
							
						 },
                              self.fetchAllBlogs, 
                              function(errResponse){
                                   console.error('Error while updating Blog.');
                              } 
                  );
          };
          
          
//--------------------------------------------------------------------------DELETE BLOG--------------------------------------------------------------------------------------          
          self.deleteBlog = function(blogname){
              BlogService.deleteBlog(blogname)
                      .then( function(d) {
                    	  $location.path("/userblog")
							
							 },
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
			
			
			
//--------------------------------------------------------------------------USER BLOG--------------------------------------------------------------------------------------          			
			  self.userblogs = function() {
					BlogService
							.userblogs()
							.then(
									function(d) {
										self.userblog = d;
										console.log('getting userblog my boy')
										console.log(d)
										
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

			
			
			
			self.addcomment = function(comment) {
				console.log('this is  '+comment.comments)
				BlogService
						.addcomment(comment)
						.then(
								function(d) {
							//self.getcomments(commment.blogname)
								//	$route.reload();
								},
								function(errResponse) {
									console
											.error('Error while adding comment.');
								});
			};

			
			
			self.getcomment = function(blogname) {
				console.log('inside getcomments')
				BlogService
						.getcomments(blogname)
						.then(
								
								function(d) {
									console.log('hiii amd inside function of getcomment')
									self.comment = d;
									console.log(d)
									$location.path("/comments")
									
								},
								function(errResponse) {
									console
											.error('Error while adding comment.');
								});
			};

			
			
			

     self.fetchAllBlogs();
     self.userblogs();
     
          self.addblog = function() {
           
                  self.createBlog(self.blog);
              	self.reset();
				
            
          };
          

          self.getcomments = function() {
           
                  self.getcomment('sample');
              	self.reset();
				
            
          }; 
          self.addcomments = function() {
              
              self.addcomment(self.comment);
          	//self.reset();
			
        
      };
    
               
          
           
          self.reset = function(){
        	  self.blog={blogname:'',blogdescription:'',username:'',blogdateTime:'',blogstatus:'',blogreason:''};
                // $scope.myForm.$setPristine(); 
          };
 
      }]);