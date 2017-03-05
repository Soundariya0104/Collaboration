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
		    controller  : 'UserController'
		  })
	  
});