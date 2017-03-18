'use strict'
var app = angular.module('myApp', [ 'ngRoute','ngCookies']);
app.config(function($routeProvider) {
	$routeProvider

  .when('/login', {
    templateUrl : 'login.html',
    controller  : 'UserController'
  })
  
  .when('/register', {
	    templateUrl : 'register.html',
	    controller  : 'UserController'
	  })
	  .when('/addblog', {
		    templateUrl : 'addblog.html',
		    controller  : 'BlogController'
		  })
	  .when('/viewblog', {
		    templateUrl : 'viewblog.html',
		    controller  : 'BlogController'
		  })
		    .when('/userblogs', {
		    templateUrl : 'userblogs.html',
		    controller  : 'BlogController'
		  })
		    .when('/listblog', {
		    templateUrl : 'listblog.html',
		    controller  : 'BlogController'
		  })
		  
		   .when('/addjob', {
		    templateUrl : 'addjob.html',
		    controller  : 'JobController'
		  })
	  .when('/viewjob', {
		    templateUrl : 'viewjob.html',
		    controller  : 'JobController'
		  })
		  .when('/listjob', {
		    templateUrl : 'listjob.html',
		    controller  : 'JobController'
		  })
		
		    .when('/viewalluser', {
		    templateUrl : 'viewalluser.html',
		    controller  : 'FriendController'
		  })
	  .when('/viewfriends', {
		    templateUrl : 'viewfriends.html',
		    controller  : 'FriendController'
		  })

.when('/viewnotifications', {
	    templateUrl : 'viewnotifications.html',
	    controller  : 'FriendController'
	  })
	  .when('/chat', {
	    templateUrl : 'chat.html',
	    controller  : 'ChatController'
	  })
});


app.run( function ($rootScope, $location, $http) {

	 $rootScope.$on('$locationChangeStart', function (event, next, current) {
		 console.log("$locationChangeStart")
		   
		 var userPages = ['/addblog','/listblog','/viewjob','/addjob','/viewalluser','/viewblog','/userblog','/viewfriend', '/searchfriend','/chat']
		 var adminPages = ["/post_job","/manage_users"]
		 
		 var currentPage = $location.path()
		  console.log("currentpage ="+ currentPage)
		   
		 console.log($.inArray(currentPage, userPages))
		 var isUserPage = $.inArray(currentPage, userPages) != -1;
		 var isAdminPage = $.inArray(currentPage, adminPages) != -1;
		 
		 var isLoggedIn = $rootScope.currentUser;
		 console.log("isLoggedIn:" +isLoggedIn)
	     console.log("isUserPage:" +isUserPage)
	     console.log("isAdminPage:" +isAdminPage)
	        
	        if(!isLoggedIn)
	        	{
	        	
	        	 if (isUserPage || isAdminPage) {
		        	  console.log("Navigating to login page:")
		        	  alert("You need to loggin to do this operation")

						            $location.path('/login');
		                }
	        	}
	        
			 else //logged in
	        	{
	        	
				 var role = $rootScope.currentUser.role;
				 
				 if(isAdminPage && role!='ROLE_ADMIN' )
					 {
					 
					  alert("You can not do this operation as you are logged as : " + role )
					   $location.path('/login');
					 
					 }
				     
	        	
	        	}
	        
	 }
	       );
	 
	 
//     $rootScope.currentUser = $cookieStore.get('currentUser') || {};
//     if ($rootScope.currentUser) {
//         $http.defaults.headers.common['Authorization'] = 'Basic' + $rootScope.currentUser; 
//     }

});


 
    
    
