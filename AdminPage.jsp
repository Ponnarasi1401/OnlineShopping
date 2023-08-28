<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,java.io.*,jakarta.servlet.RequestDispatcher,jakarta.servlet.http.*,jakarta.servlet.annotation.WebServlet,jakarta.servlet.ServletException,DataBase.Utilities,Shopping.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title></title>

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Courgette&display=swap" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<!-- <link rel="stylesheet" href="https://fontawesome.com/icons/bell?f=classic&s=solid"> -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" integrity="sha512-iA9i9J8P1RpQyHz6Ckuk4RfP4jr4l3PPpxUlgfxIe7i3g2vntO68EA2XZv1Vw8Um/Op1JHbZ4L13oza8JvtzQ==" crossorigin="anonymous" referrerpolicy="no-referrer"/>

<style type="text/css">
	
	.header
	{
		margin-top: -30px;
		height: 110px;
		width: 100%;
		position: relative;
		background-color: #62186F;
	}
	.footer
	{
		margin-left: 0px;
		height: 100%;
		width: 100%;
		position: relative;
	}
	.cart
	{
		background-color: whitesmoke;
		border-radius: 5px;
		box-shadow: 10px 10px 20px 0.3px gray;
		height: 350px;
		width: 700px;
		position: absolute;
	}
	.productList
	{
		border-radius: 5px;
		margin-top: 50px;
		margin-left: 40px;
		height: 200px;
		width: 200px;		
		position: absolute;
	}
	.details
	{
		margin-top: 70px;
		margin-left: 300px;
		height: 300px;
		width: 300px;
		position: absolute;
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
	#div4
	{
		margin-top: 0px;
		display: flex;
 		padding: 5px;
 		margin-left: 1100px;
 		box-sizing: border-box;
 		justify-content: space-between;
		position: absolute;
	}
	#d3
  	{
  		font-family: 'Courgette', cursive;
  		margin-left:70px;
  		margin-top: 30px;
  	    background-color: whitesmoke;
	    color: #62186F;
	    padding: 10px;
	    cursor: auto;
	    border: none;
  		height: 20px;
	    width: 200px;
	    border-bottom-left-radius: 5px;
	    border-top-left-radius: 5px;
  	}
  	#d4
  	{
  		font-family: 'Courgette', cursive;
  		margin-left: 290px;
  		margin-top: -100px;
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
	.notification-container 
	{
	  position: relative; 
	  display: inline-block; 
	}

	.notification-container i 
	{
	  font-size: 28px; 
	  color: white;
	}

	.notification-count 
	{
	  position: absolute; 
	  top: -8px; 
	  right: -8px;
	  background-color: #ff0000; 
	  color: #ffffff; 
	  padding: 4px 8px; 
	  border-radius: 50%; 
	}
	.list
	{
		width:200px;
		height: 200px;
		border-radius: 200px;
		position: absolute;
		background-color: #FAE2FE;
		margin-top: 150px;
		box-shadow: 10px 20px 30px 0.3px gray;
	}
	#action
	{
		border: none;
		font-size: 16px;
		background-color: #FAE2FE;
		height: 50px;
		font-weight: bold;
		margin-top: 60px;
		margin-left: 20px;
		cursor: pointer;
		width: 150px;
		color: #62186F;
		font-family: 'Courgette', cursive;
	}
	.addProduct
	{
		height: 300px;
		width: 400px;
		position: absolute;
		background-color: #FAE2FE;
		margin-top: 100px;
		margin-left: 450px;
		border-radius: 10px;
		box-shadow: 10px 20px 20px 0.3px gray;
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
  	}
  	.newProduct
  	{
  		height: 350px;
  		width: 500px;
  		margin-top: 50px;
  		margin-left: 350px;
		border-radius: 10px;
		background-color: #FAE2FE;
		padding: 2%;
		box-shadow: 10px 20px 20px 0.3px gray;
  		position: absolute;
  	}
  	#n1
  	{
  		height: 30px;
  		width: 200px;
  		margin-top: 10px;
		margin-left: 20px;
  		background-color: #FAF5FB;
  		color: gray;
  		border-radius: 5px;
  		border: none;
  	}
  	.bigDiv
	{
		height: 80%;
		width: 100%;
		background-color: white;
		position: relative;
	}
	.dressDiv
	{
		margin-left: 10px;
		margin-top: 50px;
		height: 2000px;
		width: 1320px;
		position: absolute;
		background-color: whitesmoke;
		box-sizing: border-box;
		display: flex;
		align-items: stretch;
	}

	.smallDiv
	{
		position: absolute;
		border-radius: 5px;
		transition: transform 0.3s;
	}
	.smallDiv:hover
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
    .close 
    {
      position: absolute;
      top: 10px;
      right: 10px;
      cursor: pointer;
    }
</style>

</head>

<body>
	
	<% String servlet_path=request.getServletPath();

	%>

	<div class="header">

		<div style="width:100px;height:30px;margin-top: 30px;margin-left: 50px;">

			<form action="admin" method="post">

				<button type="submit" style="text-decoration: none;background-color: #62186F;border: none;cursor: pointer;">
							
					<i class="fa fa-home" style="font-size:28px;color:white;margin-top: 30px;margin-left: -170px;"></i>
						
					<p style="font-size:28px;color:white;font-family: 'Courgette', cursive;margin-left: 40px;margin-top: -30px;">ShoppyWorld</p>

				</button>

			</form>

		</div>

		<!-- <div id="div3">
		
			<form action="productList" method="post" style="width:350px;margin-left:100px">
							
				<input type="text" name="search" placeholder="Search here" id="d3"><input type="submit" name="action"value="Search" id="d4">
				
			</form>
			
		</div> -->

		<div id="div4">

			<form action="admin" method="post">

				<input type="hidden" name="view" value="notification">
			
				<%if((int)session.getAttribute("count")==0)
				{
				%>
				<button type="submit" style="background-color: #62186F;border: none;height: 50px;width: 70px;cursor: pointer;">
				
					<div class="notification-container">
  						
  						<i class="fas fa-bell"></i>
					
					</div>

				</button>
				<%
				}
				else
				{%>
				<button type="submit" style="background-color: #62186F;border: none;height: 50px;width: 70px;cursor: pointer;">
				
					<div class="notification-container">
  						
  						<i class="fas fa-bell"></i>
  						
  						<span class="notification-count"><%= (int)session.getAttribute("count")%></span>
					
					</div>

				</button>
				<%
				}%>

			</form>

			<form action="log" method="post">
				
				<%if(session.getAttribute("userId") != null)
				{%>

					<input type="submit" name="action" value="LogOut" style="font-size:20px;background-color:#62186F;color: white;border: none;font-family: 'Courgette', cursive;margin-left: 20px;margin-top: 10px;cursor: pointer;">

				<%}%>

			</form>

		</div>

	</div>

	<div class="footer">

		<%
		if("Add Stock".equalsIgnoreCase(request.getParameter("action")) || "Change Amount".equalsIgnoreCase(request.getParameter("action")) || "Add Stock".equalsIgnoreCase((String)request.getAttribute("action")) || "Change Amount".equalsIgnoreCase((String)request.getAttribute("action")))
		{
		%>
			<div class="cart" style="margin-top:100px;margin-left:250px;background-color: whitesmoke;padding: 20px;">
				
				<form action="admin" method="post">
					
					<input type="hidden" name="gender" value="<%= request.getParameter("gender")%>">

					<input type="hidden" name="product_id" value="<%= request.getParameter("product_id")%>">

					<input type="hidden" name="variety" value="<%= request.getParameter("variety")%>">

					<%
					if("Add Stock".equalsIgnoreCase(request.getParameter("action")) || "Add Stock".equalsIgnoreCase((String)request.getAttribute("action")))
					{
					%>

						<p>Enter the Stock you want to Add :</p>

						<input type="number" name="quantity" placeholder="Type here...">

						<input type="submit" name="addQuantity" value="Add" style="margin-left:150px;height:40px;width:200px;background-color: #62186F;border-radius: 10px;color: white;font-family: 'Courgette', cursive;font-size: 18px;border: none;">

					<%}
					else
					{
					%>

						<p>Enter the Amount you want to Add :</p>

						<input type="number" name="amount" placeholder="Type here...">

						<input type="number" name="deliveryCharge" placeholder="Type here...">

						<input type="submit" name="changeAmount" value="Change Amount" style="margin-left:150px;height:40px;width:200px;background-color: #62186F;border-radius: 10px;color: white;font-family: 'Courgette', cursive;font-size: 18px;border: none;">

					<%}%>

				</form>

			</div>
		<%
		}
		else if("Increase StockAction".equalsIgnoreCase(request.getParameter("action")) || "Manage AmountAction".equalsIgnoreCase(request.getParameter("action")) || "Add".equalsIgnoreCase(request.getParameter("addQuantity")) || "Change Amount".equalsIgnoreCase(request.getParameter("changeAmount")))
		{
			if("Add".equalsIgnoreCase(request.getParameter("addQuantity")))
			{
		%>
				<div id="popup" class="popup">
                    
            		<i class="fas fa-check-circle"></i>
                    
            		<p>Quantity has been Added Successfully</p>
                    
        		</div>

    		<%}
    		if("Change Amount".equalsIgnoreCase(request.getParameter("changeAmount")))
			{
    		%>
    			<div id="popup" class="popup">
                    
        		    <i class="fas fa-check-circle"></i>
                    
            		<p>Amount has been Changed Successfully</p>
                    
    		    </div>

    		<%}%>
			<div class="bigDiv">

			<% String gen_der= request.getParameter("gender");%>

			<div class="dressDiv">
			
			<%
				System.out.println("Dresses jsp page..."+request.getParameter("gender")+"---->"+request.getParameter("variety"));
				
				ArrayList<String> varietyList =new Product().productName(gen_der,request.getParameter("variety"));

				int value=70,top=10;		

				for (int i=0;i<(varietyList.size()/5);i++) 
				{
					
			%>
				<div class="smallDiv" style="height:350px;width: 250px;margin-left: <%= value%>px;margin-top: <%= top%>px;flex-grow: 1">
					
					<form action="AdminPage.jsp" method="post">

						<button style="border: none;">
										
							<img src="<%= varietyList.get((i*5)+2)%>.jpeg" alt="Image" width="200px" height="230px">

							<input type="hidden" name="gender" value="<%= gen_der%>">

							<input type="hidden" name="variety" value="<%= request.getParameter("variety")%>">

							<input type="hidden" name="product_id" value="<%= varietyList.get((i*5)+2)%>">							

							<%
							if("Increase StockAction".equalsIgnoreCase(request.getParameter("action")))
							{
							%>
								
								<p><%= varietyList.get((i*5)+2).toUpperCase()%><br>
								Total Stock :<%= Integer.parseInt(varietyList.get((i*5)+3))%></p>
								
								<input type="submit" name="action" value="Add Stock" style="border:none;background-color: #62186F;color: white; font-size: 18px;font-family: 'Courgette', cursive;border-radius: 5px;">
							
							<%}
							else
							{
							%>
								
								<p><%= varietyList.get((i*5)+2).toUpperCase()%><br>
								Price :₹<%= Double.parseDouble(varietyList.get((i*5)+1))%><br>
								Delivery Charge :₹<%= Float.parseFloat(varietyList.get((i*5)+4))%></p>
								
								<input type="submit" name="action" value="Change Amount" style="border:none;background-color: #62186F;color: white; font-size: 18px;font-family: 'Courgette', cursive;border-radius: 5px;">
							
							<%}%>
						</button>
				
					</form>

				</div>
		<%
			value+=300;

			if((i+1)%4==0)
			{
				top+=400;
				value=70;
			}
		}
		%>
		</div>

	</div>	

		<%
		}
		else if("+ Add Product".equalsIgnoreCase(request.getParameter("action")) || "+ Add Product".equalsIgnoreCase((String)request.getAttribute("action")))
		{
		%>
			<div class="addProduct">
					
				<form action="admin" method="post">
					
					<div style="text-align: center;font-size: 14px;font-family: 'Courgette', cursive;color: #62186F;margin-top: 50px;margin-left: 0px;">Enter the count you want to add :</div>

					<input type="hidden" name="product_id" value="<%= request.getParameter("product_id")%>">

					<input type="hidden" name="view" value="Notification">

					<input type="number" name="quantity" placeholder="Type here..." style="margin-top: 20px;margin-left: 150px;height: 30px; width:100px; border-radius:4px;font-family: 'Courgette', cursive;border: none;" required>

					<input type="submit" name="adminAction" style="width:150px;height: 40px;border-radius: 10px;background-color: #62186F;color: white; font-size: 18px;font-family: 'Courgette', cursive;padding: 10px;font-style: bold;text-align: center;cursor: pointer; border: none;margin-left: 120px;margin-top: 50px;" value="Add">

				</form>

			</div>

		<%}
		 else if( "NewProduct".equalsIgnoreCase(request.getParameter("action")) || "NewProduct".equalsIgnoreCase((String)request.getAttribute("action")))
		 {
		  %>
		  	<div class="newProduct">
		  	 	
		  	 	<form action="admin" method="post">

		  	 		<input type="hidden" name="gender" value="<%= request.getParameter("gender")%>">

		  	 		<input type="hidden" name="variety" value="<%= request.getParameter("variety")%>">

		  	 		<input type="text" name="product_id" placeholder="Product Id" id="n1" required>&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="product_name" placeholder="Product Name" id="n1" required><br><br>

		  	 		<input type="text" name="product_color" placeholder="Product Color" id="n1" required>&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="material_type" placeholder="Material Type" id="n1" required><br><br>

		  	 		<%
		  	 		if(!("FootWears".equalsIgnoreCase(request.getParameter("variety"))))
		  	 		{
		  	 		%>
		  	 			<input type="text" name="product_type" placeholder="Product Type" id="n1" style="margin-left: 150px;" required><br><br>

		  	 		<%}%>

		  	 		<input type="text" name="size" placeholder="Size" id="n1" required>&nbsp;&nbsp;&nbsp;&nbsp;<input type="number" name="price" placeholder="Price" id="n1" required><br><br>

		  	 		<input type="number" name="delivery_charge" placeholder="Delivery Charge" id="n1" required>&nbsp;&nbsp;&nbsp;&nbsp;<input type="number" name="total_stock" placeholder="Total Stock" id="n1" required><br><br><br>

		  	 		<input type="submit" name="action" value="Add Product" style="margin-left:150px;height:40px;width:200px;background-color: #62186F;border-radius: 10px;color: white;font-family: 'Courgette', cursive;font-size: 18px;border: none;">

		  	 	</form>

		  	</div>

		<%}
		 else if("Add New Product".equalsIgnoreCase(request.getParameter("action")) || "Add Product".equalsIgnoreCase(request.getParameter("action")))
		 {
		 %>
		 	<div id="productdiv">

			<%
			int size=100;

			HashMap<Integer, String> genderList =new Utilities().genderList();
			
			for (Map.Entry<Integer, String> list : genderList.entrySet()) 
			{
				int box=20;
			%>
				<div id="product" style="margin-top:<%= size%>px;margin-left: 100px;")>
				
					<h2 style="text-align: center;font-family: 'Courgette', cursive;color: white;"><%= list.getValue()%> Store </h2>
				
				<%	

				HashMap<Integer, String> varietyList =new Product().shoppingVarieties();
		
				for (Map.Entry<Integer, String> variety : varietyList.entrySet()) 
				{
				%>
					<div id="variety" style="margin-left: <%= box%>px;margin-top: 30px;">

						<form action="admin" method="post">
								
							<input type="hidden" name="variety" value="<%= variety.getValue()%>">

							<input type="hidden" name="gender" value="<%= list.getValue()%>">

							<input type="hidden" name="action" value="NewProduct">

							<button type="submit" value="<%= variety.getValue()%>" style="border: none;text-align: center;font-family: 'Courgette', cursive;">
		    					
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

		<%}
		  else if ("Increase Stock".equalsIgnoreCase(request.getParameter("action")) || "Manage Amount".equalsIgnoreCase(request.getParameter("action"))) 
		  {
		  %>
		  <div id="productdiv">

			<%
			int size=100;

			HashMap<Integer, String> genderList =new Utilities().genderList();
			
			for (Map.Entry<Integer, String> list : genderList.entrySet()) 
			{
				int box=20;
			%>
				<div id="product" style="margin-top:<%= size%>px;margin-left: 100px;")>
				
					<h2 style="text-align: center;font-family: 'Courgette', cursive;color: white;"><%= list.getValue()%> Store </h2>
				
				<%	

				HashMap<Integer, String> varietyList =new Product().shoppingVarieties();
		
				for (Map.Entry<Integer, String> variety : varietyList.entrySet()) 
				{
				%>
					<div id="variety" style="margin-left: <%= box%>px;margin-top: 30px;">

						<form action="AdminPage.jsp">
								
							<input type="hidden" name="variety" value="<%= variety.getValue()%>">

							<input type="hidden" name="gender" value="<%= list.getValue()%>">

							<input type="hidden" name="action" value="<%= request.getParameter("action")%>Action">

							<button type="submit" value="<%= variety.getValue()%>" style="border: none;text-align: center;font-family: 'Courgette', cursive;">
		    					
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

		<%}
		  else if("Notification".equalsIgnoreCase(request.getParameter("view")))
		  {
			 int size=100;

    		if("Add".equalsIgnoreCase(request.getParameter("adminAction")))
			{
    		%>
    			<div id="popup" class="popup">
                    
        		    <i class="fas fa-check-circle"></i>
                    
            		<p>Product has been Added Successfully</p>
                    
    		    </div>

    		<%}%>

		<div class="cartlist">

			<%		
		
			ArrayList<String> productList=(ArrayList<String>)request.getAttribute("notification_list");
			
			if(productList.size() == 0)
			{%>
				<div style="width:400px; height: 100px;box-shadow: 10px 20px 30px 0.3px gray;border-radius: 20px; color: gray;font-family: 'Courgette', cursive;font-size:18px;text-align: center;margin-top: 150px;margin-left: 400px;">

					<p>No Notification Here...!</p>
					
				</div>
			<%}
			else
			{
				for(int i=0;i<(productList.size()/8);i++) 
				{
					System.out.println("----------admin page.jsp-----"+ productList.get((i*8)+3));		
				%>
					<div class="cart" style="margin-top:<%= size%>px; margin-left: 300px;background-color: #FAE2FE;">

						<div class="productList">

							<form action="productlist" method="post">

								<input type="hidden" name="product_id" value="<%= productList.get((i*8)+3)%>">

								<input type="hidden" name="variety" value="<%= productList.get((i*8)+7)%>">

								<input type="hidden" name="path" value="<%= servlet_path%>">						

								<button style="border: none;">

									<img id="myImage" src="<%= productList.get((i*8)+3)%>.jpeg" alt="Image" width="200px" height="200px">

								</button>

							</form>

						</div>
						<div class="details">

							<span>Total Stock      :<%= productList.get((i*8)+0)%></span><br>
					
							<span>Product Name     :<%= productList.get((i*8)+1)%></span><br>
														
							<span>Color     	   :<%= productList.get((i*8)+2)%></span><br>

							<span>Product Id       :<%= productList.get((i*8)+3)%></span><br>

							<span>Size       	   :<%= productList.get((i*8)+4)%></span><br>

							<span>Price            :<%= productList.get((i*8)+5)%></span><br>

							<span>Delivery Charge  :<%= productList.get((i*8)+6)%></span><br><br>
	

							<form action="admin" method="post">

								<input type="hidden" name="product_id" value="<%= productList.get((i*8)+3)%>">
										
								<input type="submit" name="action" value="+ Add Product" style="border:none; cursor: pointer;font-size: 18px; font-style: bold;background-color: #62186F;border-radius: 10px;color: white;font-family: 'Courgette', cursive;width: 200px;height: 50px;">

							</form>
							
						</div>

					</div>

				<%
				size+= 400;
				}
			}%>
		</div>
		<%}
		  else
		  {%>

			<div style="margin-left: 250px;" class="list">
				
				<form action="admin" method="post">

					<input type="submit" name="action" value="Add New Product" id="action">

				</form>

			</div>

			<div style="margin-left: 550px;" class="list">
				
				<form action="admin" method="post">

					<input type="submit" name="action" value="Increase Stock" id="action">

				</form>

			</div>

			<div style="margin-left: 850px;" class="list">
				
				<form action="admin" method="post">

					<input type="submit" name="action" value="Manage Amount" id="action">

				</form>

			</div>

		<%}%>
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