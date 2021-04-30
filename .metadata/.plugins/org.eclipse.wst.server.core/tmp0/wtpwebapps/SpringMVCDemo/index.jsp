
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>

<link rel="stylesheet" type="text/css" href="css/index-main.css">
</head>
<body>
	<div class="centre">

		<h1>Login</h1>



		<form  action="add" method="post">
			<div class="txt">
				<input name="t1" type="text" required> <span></span><label>User
					name</label>

			</div>
			<div class="txt">
				<input name="t2" type="text" required> <span></span><label>Password</label>
			</div>
			<input type="submit" value="Login">
		</form>
	</div>
</body>
</html>