<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">


<title>SixtyPlus</title>

<link rel="stylesheet" type="text/css"
	href="/webjars/bootstrap/css/bootstrap.min.css" />
<script type="text/javascript" src="/webjars/jquery/jquery.min.js"></script>
<script type="text/javascript"
	src="/webjars/bootstrap/js/bootstrap.min.js"></script>



<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>

	<div class="container" ng-app="app" ng-controller="home as home">

		<form class="form-signin" role="form" action="/login" method="post"
			modelAttribute="user">
			<div class="container" ng-show="!home.authenticated">
			<h2 class="form-signin-heading">Please sign in</h2>
				<a href="/login/facebook" class="btn btn-info" role="button">Facebook</a>
				<a href="/login/google" class="btn btn-info" role="button">Google</a>

				<label for="username" class="sr-only">UserName</label> <input
					type="text" id="username" name="username" class="form-control"
					placeholder="User Name" required autofocus> <label
					for="passwordHash" class="sr-only">Password</label> <input
					type="password" id="passwordHash" name="passwordHash"
					class="form-control" placeholder="Password" required>
				<div class="checkbox">
					<label> <input type="checkbox" name="remember-me"
						value="remember-me"> Remember me
					</label>
				</div>
				<button class="btn btn-lg btn-primary btn-block" type="submit">Sign
					in</button>
				<div class="container">
					Don't have an account? <a href="/user/create">SignUp</a>
				</div>
				<div class="container text-danger" ng-show="home.error">There
					was an error (bad credentials).</div>
			</div>
		</form>
		<div class="container" ng-show="home.authenticated">
			Logged in as: <span ng-bind="home.user"></span>
			<div>
				<button ng-click="home.logout()" class="btn btn-primary">Logout</button>
			</div>
		</div>
	</div>
	<!-- /container -->

</body>
<script type="text/javascript" src="/webjars/angularjs/angular.min.js"></script>
<script type="text/javascript">
	angular.module("app", []).controller(
			"home",
			 function($http, $location) {
				var self = this;

				 $http.get("/user").success(function(data) {
					self.data = data;
					self.user = data.userAuthentication.details.displayName;
					if (self.user == "" || self.user == null)
						self.user = data.userAuthentication.details.name;
					self.authenticated = true;
					console.log(data);
				}).error(function() {
					self.user = "N/A";
					self.authenticated = false;
				}); 
				
				if ($location.absUrl().indexOf("error=true") >= 0
						|| $location.absUrl().indexOf("error") >= 0) {
					self.authenticated = false;
					self.error = true;
				}
				;

				self.logout = function() {
					$http.post('/logout', {}).success(function() {
						self.authenticated = false;
						$location.path("/");
					}).error(function(data) {
						console.log("Logout failed")
						self.authenticated = false;
					});
				};
			});
</script>
</html>
