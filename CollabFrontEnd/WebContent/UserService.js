console.log("start of user service")

app.factory('UserService', ['$http', '$q','$rootScope', '$cookieStore', function($http, $q,$rootScope,$cookieStore){
	
	
	var BASE_URL='http://localhost:8085/Collaboration'
		
    return {
           
             
            createUser: function(user){
            	    return $http.post(BASE_URL+'/register', user) 
                            .then(
                                    function(response){
                                        return response.data;
                                    }, 
                                    function(errResponse){
                                        console.error('Error while creating user');
                                        return $q.reject(errResponse);
                                    }
                            );
            },
             
             
        
        
            
            authenticate: function(user){ 	 
                return $http.post(BASE_URL+'/validate',user)
                        .then(
                                function(response){
                                    return response.data;   
                                }, function(errResponse){
                                    console.error('Error while authenticate user servioce');
                                    return $q.reject(errResponse);
                                }
                        );
        },
        

        fetchAllUsers: function() {
        	console.log("inside  fetchAllUsers service ")
                return $http.get(BASE_URL+'/getAllUser')
                        .then(
                                function(response){
                                    return response.data;
                                }, 
                               null
                        );
        }
        
         
    };
 
}]);