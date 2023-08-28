<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import ="DataBase.Utilities,Shopping.*,java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<!-- <meta name="viewport" content="with=device-width,initial-scale=1.0" > -->
<meta charset="UTF-8">
<title></title>

<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Courgette&display=swap" rel="stylesheet">

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="https://getbootstrap.com/docs/5.3/assets/css/docs.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" integrity="sha512-xgafO0dbZQx0ryCtNG3WbUZj3t1pv69kaN2HjTvuI/Kx92lQNLeE8Ujx1c2D7SMdRW7Nbxuj8FUDzMqhsS3kPQ==" crossorigin="anonymous" referrerpolicy="no-referrer" />


<style type="text/css">
	
	/*header 
	{
	    position: fixed;
	    top: 0;
	    left: 0;
	    width: 100%;
	    background-color: #62186F;
	}*/
	#d1
	{
		margin-left: 500px;
	    background-color: white;
	    justify-content:space-around;
	    color: #145363;
	    padding: 10px 20px;
	    border: none;
	    cursor: pointer;
	    display: inline; 
	    width: 80px;
	    height: 40px;
	    text-align: center;
	    border-radius: 5px;
  	}
	#div1
	{
		position: -webkit-sticky;
  		position: sticky;
		padding: 25px;
		justify-content:space-evenly;
		width: 100%;
		height: 150px;
		box-sizing: border-box;
		background-color: #62186F;
		position: relative;
	}
	#div3
	{
		margin-top: -30px;
		display: flex;
 		padding: 5px;
 		margin-left: 220px;
 		box-sizing: border-box;
 		justify-content: space-between;
		position: absolute;
	}
	#d3
  	{
  		font-family: 'Courgette', cursive;
  		margin-left:50px;
  		margin-top: 30px;
  	    background-color: whitesmoke;
	    color: #62186F;
	    padding: 10px;
	    cursor: auto;
	    border: none;
  		height: 40px;
	    width: 200px;
	    border-bottom-left-radius: 5px;
	    border-top-left-radius: 5px;
  	}
  	#d4
  	{
  		font-family: 'Courgette', cursive;
  		margin-left: 0px;
  		margin-top: 15px;
  		background-color:#62186F;
	  	color: white;
	  	font-style: bold;
  		height: 40px;
  		width: 60px;
	  	cursor: pointer;
	  	border: 0.5px solid whitesmoke;
	  	border-bottom-right-radius: 5px;
	    border-top-right-radius: 5px;
  	}
 	#div4
 	{
 		display: flex;
 		padding: 5px;
 		box-sizing: border-box;
 		justify-content: space-between;
 		margin-left: 1000px;
 		margin-top:0px;
 		height: 70px;
  		width: 250px;
  		position: absolute;
  		background-color:  #62186F;
 	}
  	#mini
	{
		margin-left: 0px;
		width: 200px;
		font-family: 'Courgette', cursive;
	    background-color: white;
	    color: #62186F;
	    font-style: bold;
	    font-size: 20px;
	    padding: 5px;
	    border: none;
	    cursor: pointer; 
	    text-align: center;
  	}

  	#div5
  	{
  		display: inline;
  		margin-top: -15px;
  		background-color: #62186F;
  		box-sizing: border-box;
  		width: 1200px;
		height: 50px;
		justify-content:space-around;
		position: absolute;
  	}
  	#d2
	{
		margin-left: 10px;
        background-color: #62186F;
        font-family: 'Courgette', cursive;
        font-size: 16px;
        font-style: bold;
	    color: white;
	    padding: 20px;
	    border: none;
	    cursor: pointer;
	    justify-content:space-around;
	    width: 100px;
	    height: 30px;
	    box-shadow: 5px 2px 2px #62186F;
	    border:none;
  	}
  	#mini:hover
  	{
  		background-color: #62186F;
  		color: white;
  	}
	
</style>
</head>
<body>
	<header>
		<%String path=request.getServletPath();%>

		<div id="div1">

			<div style="width:100px;height:30px;margin-top:-30px;margin-left: 20px;">

				<a href="HomePage.jsp" style="text-decoration: none;">
						
					<i class="fa fa-home" style="font-size:28px;color:white;margin-top: 40px;"></i>
					<p style="font-size:28px;color:white;font-family: 'Courgette', cursive;margin-left: 40px;margin-top: -35px;">ShoppyWorld</p>

				</a>

			</div>
		
			<div id="div3">
		
				<form action="product" method="post" style="width:350px">
							
					<input type="text" name="search" placeholder="Search here" id="d3"><input type="submit" name="action"value="Search" id="d4">
				
				</form>
			
			</div>
			
			<!-- <div style="position: absolute;margin-left: 850px;margin-top: 10px;">
				
				<form action="admin" method="post">

					<input type="hidden" name="view" value="Supplier">
					
					<input type="submit" value="Become a Supplier" style="background-color: #62186F;border: none;font-family: 'Courgette', cursive;color: white;">

				</form>

			</div> -->

			<div id="div4">

				<span>

					<form action="productlist" method="post">

						<button style="color:white;background-color: #62186F;border: none;">

								<i class="fas fa-heart" style="font-size:28px;color:white;margin-left:10px"></i><br>

								<input type="hidden" name="path" value=<%= path%>>

								<input type="submit" name="view" value="WishList" style="color:white;background-color: #62186F;border: none;font-family: 'Courgette', cursive;margin-left: 10px;">
						
						</button>

					</form>

				</span>	

				<span>
				
					<form action="cart" method="post">

						<button style="color:white;background-color: #62186F;border: none;">

							<i class="fa fa-shopping-cart" style="font-size:28px;color:white;margin-left:30px"></i><br>

							<input type="hidden" name="path" value=<%= path%>>

							<input type="hidden" name="view" value="ViewCart">

							<input type="submit" value="Cart" style="color:white;background-color: #62186F;border: none;font-family: 'Courgette', cursive;margin-left: 30px;">
					
						</button>
					</form>

				</span>	

				<%
				if(session.getAttribute("userId")==null)
				{
				%>		
				<span>

				<div class="dropdown">
				  
				  <a class="btn btn-secondary dropdown-toggle" type="button" data-bs-toggle="dropdown" data-bs-trigger="hover focus" aria-expanded="false" style="background-color: #62186F;border: none;margin-left: 10px;">
				  			
				  		<i class="fas fa-user-alt" style="font-size:28px;color:white;background-color: #62186F;margin-left: 0px;margin-top: -5px;"><br><p style="font-size:16px;margin-top:5px;font-family: 'Courgette', cursive;">Profile</p></i>

				  </a>

				  <ul class="dropdown-menu">

 					<li>

						<form action="SignUp.jsp" method="post">
						
							<input type="submit" value="SignUp" id="mini" class="dropdown-item">
						
						</form> 	
 					
 					</li>

					<li>
							
						<form action="LogIn.jsp" method="post">

							<input type="hidden" name="path" value=<%= path%>>

							<input type="submit" value="LogIn" id="mini" class="dropdown-item">
						
						</form>

					</li>

					<li>
					
						<form action="buy" method="post">
							
								<input type="submit" name="view" value="MyOrders" id="mini" class="dropdown-item">
							
						</form>

					</li>
				 
				 </ul>
				
				</div>
				</span>
				<%}
				else{%>

				<span>

				<div class="dropdown">
				  
				  <a class="btn btn-secondary dropdown-toggle" type="button" data-bs-toggle="dropdown" data-bs-trigger="hover focus" aria-expanded="false" style="background-color: #62186F;border: none;margin-left: 10px;">
				  			
				  		<i class="fas fa-user-check" style="font-size:28px;color:white;background-color: #62186F;margin-left: 0px;margin-top: -5px;"><br><p style="font-size:16px;margin-top:5px;font-family: 'Courgette', cursive;">Profile</p></i>

				  </a>

				  <ul class="dropdown-menu">

					<li>
							
						<form action="log" method="post">

							<input type="submit" name="action" value="LogOut" id="mini" class="dropdown-item">
						
						</form>

					</li>

					<li>
					
						<form action="buy" method="post">
							
							<input type="submit" name="view" value="MyOrders" id="mini" class="dropdown-item">
							
						</form>

					</li>
				 
				 </ul>
				
				</div>
				</span>
				<%}%>

			</div>
			
			<hr style="margin-top: 65px;border-width: 1px;color: white;">	

			<div id="div5">

				<form action="Varieties.jsp" >
				
					<%
					HashMap<Integer, String> gender =new Utilities().genderList();
					
					for (Map.Entry<Integer, String> list : gender.entrySet()) 
					{
					%>
						<input type="submit" name="gender" value="<%= list.getValue()%>" id="d2">
					<%
					}

					HashMap<Integer, String> product_List =new Product().productType();
					
					for(Map.Entry<Integer, String> list : product_List.entrySet()) 
					{
					%>	
						<input type="submit" name="dress" value="<%= list.getValue()%>" id="d2" style="margin-left: 20px; width: 120px; text-align: center;">
					
					<%}%>						
				
					<input type="submit" name="accessory" value="Accessories" id="d2" style="margin-left: 20px; width: 120px; text-align: center;">

					<input type="submit" name="footwear" value="FootWears" id="d2" style="margin-left: 20px; width: 120px; text-align: center;">

				</form>
			
			</div>
		
		</div>
	
	</header>

</body>
</html>