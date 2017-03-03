app.factory('UserService', ['$http', '$q','$rootScope', function($http, $q,$rootScope){
	
	
	var BASE_URL='http://localhost:8080/collabrationBackend'
		
    return {
           
             
            createUser: function(user){
            	    return $http.post(BASE_URL+'/user/', user) //1
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
          	 
                return $http.get(BASE_URL+'/validate',user)
                        .then(
                                function(response){
                                    return response.data;   //user json object
                                }, function(errResponse){
                                    console.error('Error while authenticate user servioce');
                                    return $q.reject(errResponse);
                                }
                        );
        }
         
    };
 
}]);