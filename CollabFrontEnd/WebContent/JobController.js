console.log("start of job controller")

app.controller('JobController', ['$scope', 'JobService','$location','$rootScope','$cookieStore',
                                 function($scope, JobService,$location,$routeParams,$rootScope,$cookieStore) {
	console.log("inside jobController...")
          var self = this;
          self.job={jobname:'',jobdescription:'',username:''};
          self.jobs=[];
          self.viewjob=[];
          
         self.getJob = getjob

//-------------------------------------------------------------------------GET job ---------------------------------------------------------------------------------------
          function getjob(jobname){
        	  JobService.getjob(jobname)
                  .then(  
                		       function(d) {
                            	   console.log('inside getselected')
                                       console.log(d)
                            	        self.viewjob = d;
                               
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
          self.createjob = function(job){
              JobService.createjob(job)
                      .then(
                    		  
                    		  function(d) {
									
									self.jobModel = d;
									if (self.job.errorCode == "404")

									{
										alert(self.job.errorMessage)

										self.job.username = "";
										self.job.password = "";

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
                     self.job = angular.copy(self.jobs[i]);
                     break;
                  }
              }
          };
               
          self.remove = function(id){
              console.log('id to be deleted', id);
              if(self.job.id === id) {
                 self.reset();
              }
              self.deletejob(id);
          };
 
           
          self.reset = function(){
        	  self.job={jobname:'',jobdescription:'',username:'',jobdateTime:'',jobstatus:'',jobreason:''};
                
          };
 
      }]);