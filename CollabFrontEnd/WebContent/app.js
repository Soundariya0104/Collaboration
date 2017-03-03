var app = angular.module('myApp', ['ngRoute']);

app.config(function($routeProvider) {
  $routeProvider

  .when('/login', {
    templateUrl : 'login.html',
    controller  : 'UserController'
  })
  
});