app.controller(	'UserController', [	'$scope', 'UserService', '$location','$rootScope',						'$http',
						function($scope, UserService, $location, $rootScope,
								$http) {
							console.log("UserController...")
							var self = this;
							this.user = {username : '',name : '', password : '',	mobile : '',
								address : '', email : '',isOnline : '',	role : '',
								errorCode : '',	errorMessage : '' , imageUrl:''
							};
							
							this.users = []; //json array
							
							 $scope.orderByMe = function(x) {
							        $scope.myOrderBy = x;
							    }
					

//							 this.fetchAllUsers = function() {
//								console.log("fetchAllUsers...")
//								UserService
//										.fetchAllUsers()
//										.then(
//												function(d) {
//													self.users = d;
//												},
//												function(errResponse) {
//													console
//															.error('Error while fetching Users');
//												});
//							};
							
							//self.fatchAllUsers();

							self.createUser = function(user) {
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
							self.createblog = function(user) {
								UserService
										.createblog(user)
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

													} else { //valid credentials
																
//																self.fetchAllUsers(); 
																alert(self.user.errorMessage)
														
//														$rootScope.currentUser = self.user
//                                                     	$cookieStore.put('currentUser', self.user);
//														
//														$http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.currentUser; 
////														$location.path('/chat_forum');

													}

												},
												function(errResponse) {

													console
															.error('Error while authenticate Users');
												});
							};

							self.logout = function() {
								console.log("logout")
								$rootScope.currentUser = {};
								$cookieStore.remove('currentUser');
								UserService.logout()
								$location.path('/');

							}

						

							
							self.login = function() {
								{	self.authenticate(self.user);
								}

							};

							self.register = function() {
								{
									self.createUser(self.user);
								}
								self.reset();
							};


							self.adddblog = function() {
								{
									self.createblog(self.user);
								}
								self.reset();
							};

						
						} ]);

