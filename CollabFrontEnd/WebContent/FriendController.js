'use strict';
 console.log("start of friend controller")
app.controller('FriendController', ['UserService','$scope', 'FriendService','$location',
   '$rootScope',function(UserService,$scope, FriendService,$location,$routeParams,$rootScope) {
	console.log("FriendController...")
          var self = this;
          self.Friend={id:'',userid:'',friendid:'',status:''};
          self.friends=[];
          
          self.Userdetails = {
  				userid : '',
  				username : '',
  				password : '',
  				contact : '',
  				address : '',
  				email : '',
  				is_online:'',
  				Role : '',
  				errorMessage : ''
  			};
  			self.users = [];
          
         self.sendFriendRequest=sendFriendRequest
         
         function sendFriendRequest(friendid)
         {

       	  console.log("->sendFriendRequest :"+friendid)
             FriendService.sendFriendRequest(friendid)
                 .then(
                              function(d) {
                                   self.Friend = d;
                                  alert("Friend request sent")
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
 
         self.deleteFriend = function(id){
              FriendService.deleteFriend(id)
                      .then(
                              self.fetchAllFriends, 
                              function(errResponse){
                                   console.error('Error while deleting Friend.');
                              } 
                  );
          };
          
          self.fetchAllUsers = function() {
				UserService.fetchAllUsers().then(function(d) {
					console.error('inside function, fetching Users');
					self.users = d;
				}, function(errResponse) {
					console.error('Error while fetching Users');
				});
			};
            
 
          self.fetchAllUsers();
          self.getMyFriends();
 
     
 
      }]);