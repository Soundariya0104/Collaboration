'use strict';
app.controller('FriendController', ['UserService','$scope', 'FriendService','$location',
   '$rootScope',function(UserService,$scope, FriendService,$location,$routeParams,$rootScope) {
	console.log("inside FriendController")
          var self = this;
          self.Friend={friendid:'',username1:'',username2:'',friendstatus:''};
          self.friends=[];
          
          self.User = {
  				username : '',
  				password : '',
  				mobile : '',
  				address : '',
  				email : '',
  				is_online:'',
  				role : '',
  				errorMessage : '',
  				status : '',
  				firstname : '',
  				secondname : '',
          };
  			self.users = [];
  			self.friendsnotify = [];
  	          self.friendlist=[];
         self.addFriend=addfriend
         
         function addfriend(username)
         {

       	  console.log("inside addfriend controller")
        	 FriendService.addfriend(username)
                 .then(
                              function(d) {
                                   self.Friend = d;
                              	 $location.path('/viewalluser');
                              },
                               function(errResponse){
                                   console.error('Error while sending friend request');
                               }
                      );
         
        	 
         }
          
             
          self.getMyFriends = function(){
        	  console.log("Getting my friends")
              FriendService.getMyFriends()
                  .then(
                               function(d) {
                                    self.friends = d;
                                    console.log("Got the friends list")
                                     	/* $location.path('/view_friend');*/
                               },
                                function(errResponse){
                                    console.error('Error while fetching Friends');
                                }
                       );
          };
            
       
         self.updateFriendRequest = function(Friend, id){
              FriendService.updateFriendRequest(Friend, id)
                      .then(
                              self.fetchAllFriends, 
                              function(errResponse){
                                   console.error('Error while updating Friend.');
                              } 
                  );
          };
 
          
          self.notifications=function(){
        	  FriendService.notifications()
        	  .then( 
        			  function(d) {
                  self.friendsnotify = d;
                  console.log(d)
                  console.log("Got the notification list")
                   }
            
        			  
        			  );
          };
       
          self.acceptfriend=function(username){
        	  console.log("inside accept friend")
        	  FriendService.acceptfriend(username)
        	  .then( function(){
        		
                   	 $location.path('/viewallfriends');
             }
            
        			  
        			  );
          };

                  
          
         self.unfriend = function(username){
              FriendService.unfriend(username)
                      .then(        			  function(d) {

                    		  console.log('inside unfriend jolly')
                    			 $location.path('/viewallfriends')
                    			 self.fetchAllFriends},
                      
                              function(errResponse){
                                   console.error('Error while deleting Friend.');
                                 
} 
                     );
          };
          
          self.fetchAllUsers = function() {
				UserService.fetchAllUsers().then(function(d) {
					console.log('inside function, fetching Users');
					self.users = d;
					console.log(d)
				}, function(errResponse) {
					console.error('Error while fetching Users');
				});
			};
            
 
          self.fetchAllUsers();
          self.getMyFriends();
      self.notifications();
      
}]);