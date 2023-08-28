<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<!-- <meta name="viewport" content="with=device-width,initial-scale=1.0"> -->
<meta charset="UTF-8">
<title>Log-In</title>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Courgette&display=swap" rel="stylesheet">
<script src="https://kit.fontawesome.com/b3d3e8f117.js" crossorigin="anonymous"></script>

<style type="text/css">

body
{
	font-family: 'Courgette', cursive;
}
.container
{
	margin-top: 110px;
	margin-left: 350px;
	padding: 10px;
	height: 450px;
	width: 600px;
	background-color: white;
	border: none;
	position: relative;
	border-radius: 10px;
	box-shadow: 10px 10px 30px 0.3px gray;
	
}
.box1{
	padding: 10px;
	height:400px;
	width: 400px;
	margin-left: 70px;
	background-color: white;
	position: relative;	
}
#b1{

	margin-top: -20px;
	margin-left: 60px;
	border: none;
	background-color: lightgray;
	height: 40px;
	width: 300px;
	text-align: left;
	padding: 10px;
	color: gray;
	cursor: auto;
	border-radius: 5px;
}
#b2{

	margin-top: 20px;
	margin-left: 60px;
	border: none;
	background-color: lightgray;
	height: 40px;
	width: 300px;
	text-align: left;
	padding: 10px;
	color: gray;
	cursor: auto;
	border-radius: 5px;
}
#p5{
	margin-left: 145px;
	margin-top: 30px;
	color: gray;
	font-size: 15px;
	text-align: center;
}

#b3{
	margin-left: 130px;
	margin-top: 30px;
	height: 40px;
	width: 150px;
	color: white;
	border-radius: 50px;
	border: none;
	background-color: #62186F;
	font-family: 'Courgette', cursive;
	cursor: pointer;
}
#p1{
	font-family: 'Courgette', cursive;
	margin-left: 150px ;
	color: #62186F;
	font-size: 50px;
}
</style>
</head>
<body>

<div class="container">	
	
	<form action="log" method="post">
	
		<div class="box1">

			<h1 id="p1">Login</h1>

			<% 
			   String path=request.getParameter("path");

			   String cart=request.getParameter("view");

			   String variety=request.getParameter("variety");

			   System.out.println(path +"<---LogIn Page--->"+cart+"   variety---->"+variety +" product_id--->"+request.getParameter("product_id")+"..............Amount................."+request.getParameter("productAmount")+"----"+request.getParameter("deliveryFee"));	
			%>

			<input type="hidden" name="productName" value="<%= request.getParameter("productName")%>">

			<input type="hidden" name="productAmount" value="<%= request.getParameter("productAmount")%>">

			<input type="hidden" name="gender" value="<%= request.getParameter("gender")%>">

			<input type="hidden" name="deliveryFee" value="<%= request.getParameter("deliveryFee")%>">

			<input type="hidden" name="product_id" value="<%= request.getParameter("product_id")%>">

			<input type="hidden" name="path" value=<%= path%>>

			<input type="hidden" name="view" value=<%= cart%>>

			<input type="hidden" name="variety" value=<%= variety%>>

			<input type="hidden" name="action" value="loginpage">
			
			<input type="text" name="mailId" placeholder="E-Mail" id="b1" required><br><br>
			
			<input type="password" name="password" placeholder="Password" id="b2" required><br><br>
			
				<a href="SignUp.jsp" id="p5">Create an Account</a>

			<input type="submit" value="Continue" id="b3">

		</div>

	</form>

</div>

</body>
</html>