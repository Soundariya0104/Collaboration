'use strict'
var app = angular.module('myApp', ['ngRoute']);

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
	  .when('/listblog', {
		    templateUrl : 'listblog.html',
		    controller  : 'BlogController'
		  })
		  .when('/viewblog', {
		    templateUrl : 'viewblog.html',
		    controller  : 'BlogController'
		  })
  
		  .when('/userblog', {
		    templateUrl : 'userblog.html',
		    controller  : 'BlogController'
		  })

		   .when('/addjob', {
		    templateUrl : 'addjob.html',
		    controller  : 'JobController'
		  })
	  .when('/viewbjob', {
		    templateUrl : 'viewjob.html',
		    controller  : 'JobController'
		  })
		    .when('/viewalluser', {
		    templateUrl : 'viewalluser.html',
		    controller  : 'FriendController'
		  })
	  .when('/viewfriend', {
		    templateUrl : 'viewfriend.html',
		    controller  : 'FriendController'
		  })
});


app.run( function ($rootScope, $location, $http) {

	 $rootScope.$on('$locationChangeStart', function (event, next, current) {
		 console.log("$locationChangeStart")
		   
		 var userPages = ['/addblog','/listblog','/listblog','/addjob','/view_friend', '/viewFriendRequest','/chat']
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
	        
			 else 
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
	 
});