console.log("start of blog service")

app.factory('BlogService', ['$http', '$q','$rootScope','$cookieStore' ,
                            function($http, $q,$rootScope,$cookieStore){
	
	console.log("inside BlogService...")
	
	var BASE_URL='http://localhost:8085/Collaboration'
    return {
         
            fetchAllBlogs: function() {
                    return $http.get(BASE_URL+'/fetchallblogs')
                            .then(
                                    function(response){
                                        return response.data;
                                    }, 
                                    function(errResponse){
                                        console.error('Error while fetching Blogs');
                                        return $q.reject(errResponse);
                                    }
                            );
            },
             
            createBlog: function(blog){
                    return $http.post(BASE_URL+'/createblog', blog)
                            .then(
                                    function(response){
                                        return response.data;
                                    }, 
                                    function(errResponse){
                                        console.error('Error while creating blog');
                                        return $q.reject(errResponse);
                                    }
                            );
            },
             
            editBlog: function(blogname){
                    return $http.get(BASE_URL+'/editblog/'+blogname)
                            .then(
                            		 function(response){
                                     	$rootScope.editblog = response.data
                                         return response.data;
                                     }, 
                                     function(errResponse){
                                         console.error('Error while edit blog');
                                         return $q.reject(errResponse);
                                     }
                             );
             },
            
            accept: function(id) {
            	console.log("calling approve ")
                    return $http.get(BASE_URL+'/accept/'+id)
                            .then(
                                    function(response){
                                        return response.data;
                                    }, 
                                    function(errResponse){
                                        console.error('Error while accept blog');
                                        return $q.reject(errResponse);
                                    }
                            );
            },
            
            reject: function(id, reason) {
            	console.log("calling reject ")
                    return $http.get(BASE_URL+'/reject/'+id+'/'+reason)
                            .then(
                                    function(response){
                                        return response.data;
                                    }, 
                                    function(errResponse){
                                        console.error('Error while rejecting blog');
                                        return $q.reject(errResponse);
                                    }
                            );
            },
             
            deleteBlog: function(blogname){
                    return $http.get(BASE_URL+'/deleteblog/'+blogname)
                            .then(
                                    function(response){
                                        return response.data;
                                    }, 
                                    function(errResponse){
                                        console.error('Error while deleting blog');
                                        return $q.reject(errResponse);
                                    }
                            );
            },
            
            getblog: function(blogname){
                return $http.get  (BASE_URL+'/getblogbyname/'+blogname)
                        .then(
                                function(response){
                                	$rootScope.selectedBlog = response.data
                                    return response.data;0
                                }, 
                                function(errResponse){
                                    console.error('Error while getting blog');
                                    return $q.reject(errResponse);
                                }
                        );
        },
        
        
        addcomment: function(comment){
         console.log('inside addcomemnt blogservices boy'+comment.comments)
            return $http.post(BASE_URL+'/addcomment',comment)
                    .then(
                            function(response){
                                return response.data;
                            }, 
                            function(errResponse){
                                console.error('Error while getting blog');
                                return $q.reject(errResponse);
                            }
                    );
    }, 
    getcomments: function(blogname){
    	console.log('inside getcomments service')
		
        return $http.get  (BASE_URL+'/getcomments/'+blogname)
                .then(
                        function(response){
                        	$rootScope.comment = response.data
                            return response.data;
                        }, 
                        function(errResponse){
                            console.error('Error while getting blog');
                            return $q.reject(errResponse);
                        }
                );},
        userblogs: function(){
            return $http.get(BASE_URL+'/userblog/')
                    .then(
                            function(response){
                            	$rootScope.userblog = response.data
                                return response.data;
                            }, 
                            function(errResponse){
                                console.error('Error while getting blog');
                                return $q.reject(errResponse);
                            }
                    );
    }
         
    };
 
}]);