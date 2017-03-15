app.factory('JobService', ['$http', '$q','$rootScope', function($http, $q,$rootScope){
	
	console.log("inside jobService...")
	
	var BASE_URL='http://localhost:8085/Collaboration'
    return {
         
            fetchAlljobs: function() {
                    return $http.get(BASE_URL+'/fetchallJobs')
                            .then(
                                    function(response){
                                        return response.data;
                                    }, 
                                    function(errResponse){
                                        console.error('Error while fetching jobs');
                                        return $q.reject(errResponse);
                                    }
                            );
            },
             
            createjob: function(job){
                    return $http.post(BASE_URL+'/createJob', job)
                            .then(
                                    function(response){
                                        return response.data;
                                    }, 
                                    function(errResponse){
                                        console.error('Error while creating job');
                                        return $q.reject(errResponse);
                                    }
                            );
            },
             
            updatejob: function(job, id){
                    return $http.put(BASE_URL+'/job/'+id, job)
                            .then(
                                    function(response){
                                        return response.data;
                                    }, 
                                    function(errResponse){
                                        console.error('Error while updating job');
                                        return $q.reject(errResponse);
                                    }
                            );
            },
            
         
             
            deletejob: function(id){
                    return $httpdelete(BASE_URL+'/job/'+id)
                            .then(
                                    function(response){
                                        return response.data;
                                    }, 
                                    function(errResponse){
                                        console.error('Error while deleting job');
                                        return $q.reject(errResponse);
                                    }
                            );
            },
            
            getjob: function(jobname){
                return $http.get  (BASE_URL+'/getJobbyname/'+jobname)
                        .then(
                                function(response){
                                	$rootScope.selectedjob = response.data
                                    return response.data;
                                }, 
                                function(errResponse){
                                    console.error('Error while getting job');
                                    return $q.reject(errResponse);
                                }
                        );
        }
         
    };
 
}]);