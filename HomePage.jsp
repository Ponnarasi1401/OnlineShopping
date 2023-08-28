<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import ="DataBase.Utilities,Shopping.*,java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<!-- <meta name="viewport" content="with=device-width,initial-scale=1.0" charset="UTF-8"> -->
<meta charset="UTF-8">
<title>Home Page</title>
<style type="text/css">

  	#div2
  	{
  		width: 100%;
		height: 80%;
		background-color: white;
		position: relative;
  	}
  	#productdiv
  	{
  		height: 1000px;
  		width: 1000px;
  		position: absolute;
  	}
  	#product
  	{
  		width: 900px;
  		height: 400px;
  		padding: 20px;
  		background-image: url("back.jpeg");
  		background-repeat: no-repeat;
  		box-sizing: border-box;
  		background-size: cover;
  		border-radius: 5px;
  		box-shadow: 1px 1px 10px rgba(0, 0, 0, 0.5);
  		box-sizing: border-box;
  		display: inline;
  		justify-content:space-around;
  		position: absolute;
  	}
  	#variety
  	{
  		position: absolute;
  		height: 100px;
  		width: 100px;
  		background-color: white;
  		transition: transform 0.3s;
  	}
  	#variety:hover
  	{
  		transform: scale(1.2);
  	}
  	.popup 
  	{
	  display: none;
	  position: fixed;
	  top: 5%;
	  left: 50%;
	  transform: translate(-50%, -50%);
	  padding: 10px;
	  text-align: center;
	  border: none;
	  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.3);
	  z-index: 9999;
	  border-radius: 40px;
	  background-color: #2BAC2E;
	  color: white;
	  font-family: 'Courgette', cursive;
	  height: 40px;
	  width: 320px;
	}

	.popup p 
	{
	  margin: 0;
	  margin-top: -35px;
	  margin-left: 10px;
	}
	.popup i 
	{
		margin-left: -270px;
		display: block;
		font-size:20px;
		color: white;
		margin-bottom: 10px;
	}
</style>

</head>
<body>
	<%@ include file="HeaderPage.jsp" %>	


			<%  
				if(session.getAttribute("userId") != null && "loginpage".equalsIgnoreCase(request.getParameter("action")))
		        {
		        	System.out.println(request.getParameter("action")+"---if condition----");	
			%>	
					<div id="popup" class="popup">
			  		
			  			<!-- <span class="close">&times;</span> -->
			  			<i class="fas fa-check-circle"></i>
			  		
			  			<p>You have successfully logged in!</p>
					
					</div>

			<%  }
			    else if(session.getAttribute("userId") != null && "singUp".equalsIgnoreCase(request.getParameter("action"))) 
				{%>

					<div id="popup" class="popup">
			  		
			  			<!-- <span class="close">&times;</span> -->
			  			<i class="fas fa-check-circle"></i>
			  		
			  			<p>You have successfully signed up!</p>
					
					</div>
					
			<%	}
				else if("LogOut".equalsIgnoreCase(request.getParameter("action"))) 
				{%>

					<div id="popup" class="popup">
			  		
			  			<!-- <span class="close">&times;</span> -->
			  			<i class="fas fa-check-circle"></i>
			  		
			  			<p>You have successfully logged out!</p>
					
					</div>

			<%	}
			%>			

	<div id="div2">	

		<div id="productdiv">

			<%
			int size=100;

			HashMap<Integer, String> genderList =new Utilities().genderList();
			
			for (Map.Entry<Integer, String> list : genderList.entrySet()) 
			{
				int box=20;
			%>
				<div id="product" style="margin-top:<%= size%>px;margin-left: 200px;")>
				
					<h2 style="text-align: center;font-family: 'Courgette', cursive;color: white;"><%= list.getValue()%> Store </h2>
				<%	

				HashMap<Integer, String> varietyList =new Product().shoppingVarieties();
		
				for (Map.Entry<Integer, String> variety : varietyList.entrySet()) 
				{
				%>
					<div id="variety" style="margin-left: <%= box%>px;margin-top: 30px;border-radius: 5px;">

						<form action="Product_List.jsp" method="post">
								
							<input type="hidden" name="variety" value="<%= variety.getValue()%>">

							<input type="hidden" name="gender" value="<%= list.getValue()%>">

							<button type="submit" value="<%= variety.getValue()%>" style="border: none;text-align: center;font-family: 'Courgette', cursive;border-radius: 5px;">
		    					
		    					<img src="<%= (list.getValue().toLowerCase())+"_"+(variety.getValue().toLowerCase())%>.jpeg" alt="Image" style="height: 200px;width:200px;border-radius: 200px;background-color: white;">

		    					<%= variety.getValue()%>
		 
		 					</button>

						</form>

					</div>
				<%
					box+=300;
				}

				size+=500;
				%>

				</div>

			<%}%>

		</div>

	</div>
	<div style="height: 500px;width: 100%;background-color: #541B5F;position: absolute;margin-top: 2100px;">

		<div style="background-color: #541B5F;position: absolute;height: 200px;width: 300px;margin-top: 100px;margin-left: 100px;">
			
			<h1 style="color:white;font-family: 'Courgette', cursive;">Welcome <br>
			 To <br>
			 Shoppy World</h1>
		
		</div>
		
		<div style="background-color: #541B5F;position: absolute;height: 200px;width: 300px;margin-top: 100px;margin-left: 800px;">
			
			<p style="color:white;font-family: 'Courgette', cursive;font-size: 18px;">Contact us</p>

			<p style="color:white;font-size: 16px;">Fashnear Technologies Private Limited,<br>
			   CIN: U74900KA2015PTC082263<br>
			   06-105-B, 06-102, (138 Wu) Vaishnavi Signature, No. 78/9, Outer Ring Road, Bellandur, Varthur Hobli, Bengaluru-560103, Karnataka, India<br>
			   E-mail address: query@shoppyworld.com<br>
	 		   © 2015-2023 ShoppyWorld.com</p>

		</div>

	</div>

	<script>
	
	  window.addEventListener('DOMContentLoaded', function () 
	  {
	    // var popup = document.getElementById('popup');
	    var popup = document.querySelector('.popup');

	    // close.addEventListener('click', function () {
	    //   popup.style.display = 'none';
	    // });

	    popup.style.display = 'block';

	    setTimeout(function () 
	    {

	        popup.style.display = 'none';

	    }, 2000);

	  });
	
	</script>
	
</body>
</html>