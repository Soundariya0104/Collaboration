'use strict';
 console.log("start of friend service")
app.factory('FriendService', ['$http', '$q','$rootScope', function($http, $q,$rootScope){
	
	console.log("FriendService...")
	
	var BASE_URL='http://localhost:8085/Collaboration'
    return {
         
		getMyFriends: function() {
                    return $http.get(BASE_URL+'/friendslist')
                            .then(
                                    function(response){
                                        return response.data;
                                    }, 
                                   null
                            );
            },
             
            addfriend: function(username){
                    return $http.get(BASE_URL+'/addfriend/'+username)
                            .then(
                                    function(response){
                                        return response.data;
                                    }, 
                                    function(errResponse){
                                        console.error('Error while creating friend');
                                        return $q.reject(errResponse);
                                    }
                            );
            },
             
            notifications: function(){
                    return $http.get(BASE_URL+'/notifications')
                            .then(
                                    function(response){
                                        return response.data;
                                    }, 
                                    function(errResponse){
                                        console.error('Error while getting notification');
                                        return $q.reject(errResponse);
                                    }
                            );
            },

            
            removefriend: function(username){
            return $http.get(BASE_URL+'/unfriend/'+username)
                    .then(+
                            function(response){
                                return response.data;
                            }, 
                            function(errResponse){
                                console.error('Error while deleting friend');
                                return $q.reject(errResponse);
                            }
                    );
    },
           
            
    unfriend: function(username){
    	return $http.get(BASE_URL+'/unfriend/'+username)
                .then(
                        function(response){
                            return response.data;
                        }, 
                        function(errResponse){
                            console.error('Error while getting notification');
                            return $q.reject(errResponse);
                        }
                );
},
   
            
            acceptfriend: function(username){
            	console.log("inside acceptfriend services")
            	console.log("hii"+username)
                return $http.get(BASE_URL+'/acceptfriend/'+username)
                        .then(
                                function(response){
                                    return response.data;
                                }, 
                                function(errResponse){
                                    console.error('Error while getting notification');
                                    return $q.reject(errResponse);
                                }
                        );
        },
         
            
           
         
    };
 
}]);