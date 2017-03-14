app.controller('JobController', ['$scope', 'JobService','$location','$rootScope',function($scope, JobService,$location,$routeParams,$rootScope) {
	console.log("inside jobController...")
          var self = this;
          self.jobModel={jobname:'',jobdescription:'',username:''};
          self.jobs=[];
            
          
         self.getJob = getjob

//-------------------------------------------------------------------------GET job ---------------------------------------------------------------------------------------
          function getjob(jobname){
        	  JobService.getjob(jobname)
                  .then(  
                		       function(d) {
                            	   console.log('inside getselected')
                                   
                            	  	   console.log(d)
                            	   
                                     $location.path('/viewjob'); 
                               },
                                function(errResponse){
                                    console.error('Error while get jobs controller');
                                }
                       );
          };
          
//---------------------------------------------------------------------------ALL job LIST-------------------------------------------------------------------------------------        
          self.fetchAlljobs = function(){
              JobService.fetchAlljobs()
                  .then(
                               function(d) {
                                    self.jobs = d;
                                 console.log('inside fetch all job')
                           
                               },
                                function(errResponse){
                                    console.error('Error while fetching jobs');
                                }
                       );
          };
  //--------------------------------------------------------------------------ADD job--------------------------------------------------------------------------------------          
          self.createjob = function(jobModel){
              JobService.createjob(jobModel)
                      .then(
                    		  
                    		  function(d) {
									
									self.jobModel = d;
									if (self.jobModel.errorCode == "404")

									{
										alert(self.jobModel.errorMessage)

										self.jobModel.username = "";
										self.jobModel.password = "";

									} else { 
												
												self.fetchAlljobs(); 
										self.reset();
													$location.path('/addjob');
												

									}}

                  );
          };
//--------------------------------------------------------------------------UPDATE job--------------------------------------------------------------------------------------          
         self.updatejob = function(job, id){
              JobService.updatejob(job, id)
                      .then(
                              self.fetchAlljobs, 
                              function(errResponse){
                                   console.error('Error while updating job.');
                              } 
                  );
          };
			
			

     self.fetchAlljobs();
 
          self.addjob = function() {
           
                  self.createjob(self.jobModel);
              	self.reset();
				
            
          };
    
               
          self.edit = function(id){
              console.log('id to be edited', id);
              for(var i = 0; i < self.jobs.length; i++){
                  if(self.jobs[i].id === id) {
                     self.jobModel = angular.copy(self.jobs[i]);
                     break;
                  }
              }
          };
               
          self.remove = function(id){
              console.log('id to be deleted', id);
              if(self.jobModel.id === id) {//clean form if the job to be deleted is shown there.
                 self.reset();
              }
              self.deletejob(id);
          };
 
           
          self.reset = function(){
        	  self.jobModel={jobname:'',jobdescription:'',username:'',jobdateTime:'',jobstatus:'',jobreason:''};
                // $scope.myForm.$setPristine(); 
          };
 
      }]);