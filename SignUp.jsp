<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sign-Up</title>
</head>
<body>
	
	<form action="signup" method="post">
		
		<input type="hidden" name="action" value="signUp">

		<label for="username">Enter Your Name :</label>
		<input type="text" name="username" required><br><br>
		
		<label for="gender">Select the Gender :</label><br>
		<input type="radio" name="gender" value="Female" required>
		<label for="fm">Female</label>
		
		<input type="radio" name="gender" value="Male" required>
	    <label for="m">Male</label>

		<input type="radio" name="gender" value="Others" required>
		<label for="o">Others</label><br><br>

		<label for="phoneNo">Enter Your Phone No. :</label>
		<input type="number" name="phoneNo" required><br><br>
		
		<label for="mailId">Enter Your Mail-Id :</label>
		<input type="text" name="mailId" required><br><br>
		
		<label for="password">Create Password :</label>
		<input type="password" name="password" required><br><br>
		
		<input type="submit" value="SignUp">
		
		<a href="LogIn.jsp">Already a user?</a>

	</form>
</body>
</html>