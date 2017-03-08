app.controller('BlogController', ['$scope', 'BlogService','$location','$rootScope',function($scope, BlogService,$location,$routeParams,$rootScope) {
	console.log("inside BlogController...")
          var self = this;
          self.blogModel={blogname:'',blogdescription:'',username:'',dateTime:'',status:'',blogreason:''};
          self.blogs=[];
          
          
         self.getSelectedBlog = getBlog
          
          function getBlog(id){
        	  BlogService.getBlog(id)
                  .then(
                               function(d) {
                                     //self.blog = d;
                                     $location.path('/view_blog'); 
                               },
                                function(errResponse){
                                    console.error('Error while fetching Blogs');
                                }
                       );
          };
          
        
          self.fetchAllBlogs = function(){
              BlogService.fetchAllBlogs()
                  .then(
                               function(d) {
                                    self.blogs = d;
                               },
                                function(errResponse){
                                    console.error('Error while fetching Blogs');
                                }
                       );
          };
            
          self.createBlog = function(blogModel){
              BlogService.createBlog(blogModel)
                      .then(
                    		  
                    		  function(d) {
									
									self.user = d;
									if (self.user.errorCode == "404")

									{
										alert(self.user.errorMessage)

										self.user.username = "";
										self.user.password = "";

									} else { //valid credentials
												
//												self.fetchAllUsers(); 
												alert(self.user.errorMessage)

									}}

								
                    		  
//                    		  
//                      self.fetchAllBlogs, 
//                              function(errResponse){
//                                   console.error('Error while creating Blog.');
//                              } 
                  );
          };
 
         self.updateBlog = function(blog, id){
              BlogService.updateBlog(blog, id)
                      .then(
                              self.fetchAllBlogs, 
                              function(errResponse){
                                   console.error('Error while updating Blog.');
                              } 
                  );
          };
          
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

 
               //calling the method
          //when it will be execute?
          self.fetchAllBlogs();
 
          self.addblog = function() {
           
                  self.createBlog(self.blogModel);
            
          };
               
          self.edit = function(id){
              console.log('id to be edited', id);
              for(var i = 0; i < self.blogs.length; i++){
                  if(self.blogs[i].id === id) {
                     self.blogModel = angular.copy(self.blogs[i]);
                     break;
                  }
              }
          };
               
          self.remove = function(id){
              console.log('id to be deleted', id);
              if(self.blogModel.id === id) {//clean form if the blog to be deleted is shown there.
                 self.reset();
              }
              self.deleteBlog(id);
          };
 
           
          self.reset = function(){
        	   self.blog={id:'',title:'',description:'',BlogID:'',dateTime:'',status:'',reason:'',errorMessage : ''};
               $scope.myForm.$setPristine(); //reset Form
          };
 
      }]);