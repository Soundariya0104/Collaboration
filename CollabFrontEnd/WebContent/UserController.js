app.controller(	'UserController', [	'$scope', 'UserService','$location','$rootScope','$cookieStore','$http',
						function($scope, UserService, $location, $rootScope,$cookieStore,
								$http) {
							console.log("UserController...")
							var self = this;
							this.user = {
									username : '',
									firstname : '', 
									secondname : '', 
									password : '',	
									mobile : '',
									address : '', 
									email : '',
									is_online : '',	
									role : '',
									errorCode : '',	
									errorMessage : '' , 
									imageUrl:''
							};
							
							this.users = []; //json array
							
							self.createUser = function(user) {
						console.log('inside register')
								UserService
										.createUser(user)
										.then(
												function(d)
												{
													alert("Thank you for registration")
												},
												function(errResponse) {
													console
													alert("not registered")
													
												});
							};							
						

							self.authenticate = function(user) {
								console.log("authenticate...")
								UserService
										.authenticate(user)
										.then(

												function(d) {
													
													self.user = d;
													if (self.user.errorCode == "404")

													{
														alert(self.user.errorMessage)

														self.user.username = "";
														self.user.password = "";

													} else { 
														$rootScope.currentUser = self.user
														$cookieStore.currentUser=self.user
														console.log(self.user);
	                                                 	
														 $location.path('/');
													}

												},
												function(errResponse) {
													
												});
							};

							self.logouts = function() {
								console.log("inside logout")
UserService.logout().then(
		function(d){
//								$rootScope.currentUser = {};
								 $rootScope.currentUser = undefined
								$cookieStore.remove('currentUser');
								//UserService.logout()
								//$location.path('/login');
								 $location.path('/');
									
							});

							};

							
							self.login = function() {
								{	self.authenticate(self.user);
								}

							};

							self.register = function() {
								{
									self.createUser(self.user);
								}
							};

							self.logout = function() {
								{
									self.logouts();
								}
							};


						

						
						} ]);

