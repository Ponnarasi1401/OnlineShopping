<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,java.io.*,jakarta.servlet.RequestDispatcher,jakarta.servlet.http.*,jakarta.servlet.annotation.WebServlet,jakarta.servlet.ServletException,java.time.LocalDate,java.time.format.DateTimeFormatter"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Products</title>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Courgette&display=swap" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" integrity="sha512-iA9i9J8P1RpQyHz6Ckuk4RfP4jr4l3PPpxUlgfxIe7i3g2vntO68EA2XZv1Vw8Um/Op1JHbZ4L13oza8JvtzQ==" crossorigin="anonymous" referrerpolicy="no-referrer"/>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">

<style type="text/css">
	.footer
	{
		margin-left: 0px;
		height: 100%;
		width: 100%;
		position: relative;
	}
	.productDiv
	{
		margin-top: 10px;
		margin-left: 10px;
		height: 550px;
		width: 1330px;
		background-color: ghostwhite;
		position: absolute;
	}
	.imageDiv
	{
		position:absolute;
		margin-left: 70px;
		margin-top: 50px;
		background-color: aliceblue;
		height: 400px;
		width: 400px;
	}
	.header
	{
		height: 100px;
		width: 100%;
		position: absolute;
		background-color: #62186F;
	}
	.cartlist
	{
		height: 100%;
		width: 50%;
		position: absolute;
	}
	.amountdetail
	{
		margin-left: 750px;
		height: 100%;
		width: 40%;
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
	#amount
	{
		margin-top: 200px;
		margin-left: 150px;
		height: 300px;
/*		width: 300px;*/
		position: absolute;
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
	.product
	{
		border-radius: 5px;
		margin-top: 50px;
		margin-left: 40px;
		height: 200px;
		width: 200px;		
		position: absolute;
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
	
	<div class="footer">
	<% 
	String servlet_path=request.getServletPath();

	System.out.println("productpage...1");

	double sum=0,deliveryFee=0,productAmount=0;int size=150;
	
	if("WishList".equalsIgnoreCase(request.getParameter("view")))
	{
		if("Add To Cart".equalsIgnoreCase(request.getParameter("cart")))
        {
        	%>	
				<div id="popup" class="popup">
			  		
			  		<i class="fas fa-check-circle"></i>
			  		
			  		<p>Successfully Added To Cart</p>
					
				</div>

			<%
		}
		else if("X Remove".equalsIgnoreCase(request.getParameter("wishRemove")))
		{
			%>	
				<div id="popup" class="popup">
			  		
			  		<i class="fas fa-check-circle"></i>
			  		
			  		<p>Successfully Removed your Product</p>
					
				</div>

			<%
		}
		%>
		<div class="header">

			<div style="width:100px;height:30px;margin-top: 30px;margin-left: 50px;">

				<a href="HomePage.jsp" style="text-decoration: none;">
						
					<i class="fa fa-home" style="font-size:28px;color:white;margin-top: 0px;"></i>
					
					<p style="font-size:28px;color:white;font-family: 'Courgette', cursive;margin-left: 40px;margin-top: -30px;">ShoppyWorld</p>

				</a>

			</div>

		</div>
		
		<div class="cartlist">
		<%		
		
		ArrayList<String> wishlist=(ArrayList<String>)request.getAttribute("wishList");
		
		if(wishlist.size() == 0)
		{%>
			<div style="width:400px; height: 100px;box-shadow: 10px 20px 30px 0.3px gray;border-radius: 20px; color: gray;font-family: 'Courgette', cursive;font-size:18px;text-align: center;margin-top: 150px;margin-left: 400px;">

				<p>No Products Available in Wish List...!</p>
				
			</div>
		<%}
		else
		{
		for(int i=0;i<(wishlist.size()/10);i++) 
		{
			System.out.println("----Amount--------product.jsp-----"+ wishlist.get((i*10)+5));		
		%>
			<div class="cart" style="margin-top:<%= size%>px; margin-left: 300px;">
				
				<div class="product">

					<form action="productlist" method="post">

						<input type="hidden" name="product_id" value="<%= wishlist.get((i*10)+3)%>">

						<input type="hidden" name="gender" value="<%= wishlist.get((i*10)+8)%>">

						<input type="hidden" name="variety" value="<%= wishlist.get((i*10)+9)%>">						

						<button style="border: none;">

							<img id="myImage" src="<%= wishlist.get((i*10)+3)%>.jpeg" alt="Image" width="200px" height="200px">

						</button>

					</form>

				</div>

				<div class="details">
					
					<span>Product Name     :<%= wishlist.get((i*10)+0)%></span><br>
					
					<span>Product Id       :<%= wishlist.get((i*10)+3)%></span><br>
					
					<span>Color     	   :<%= wishlist.get((i*10)+1)%></span><br><br>

				<%  productAmount+=(Double.parseDouble(wishlist.get((i*10)+5)));

					deliveryFee+=(Float.parseFloat(wishlist.get((i*10)+6)));

					sum=productAmount+deliveryFee;
					
				%>
					
					<!-- <span style="width: 100px;height: 50px;border-radius: 5px;">Quantity :<%//= wishlist.get((i*10)+8)%></span> -->
					<br><br><br>
						
					<form action="productlist" method="post">

						<input type="hidden" name="product_id" value="<%= wishlist.get((i*10)+3)%>">

						<input type="hidden" name="view" value="<%= request.getParameter("view")%>">
								
						<input type="submit" name="wishRemove" value="X Remove" style="border:none; cursor: pointer;font-size: 16px; font-style: bold;">

					</form>

					<form action="productlist" method="post">

						<input type="hidden" name="path" value="<%= servlet_path%>">					

						<input type="hidden" name="product_id" value="<%= wishlist.get((i*10)+3)%>">

						<input type="hidden" name="variety" value="<%= wishlist.get((i*10)+9)%>">

						<input type="hidden" name="view" value="<%= request.getParameter("view")%>">

						<input type="submit" name="cart" value="Add To Cart" style="border:none; cursor: pointer;font-size: 16px; font-style: bold;background-color: #62186F;border-radius: 10px;color: white;font-family: 'Courgette', cursive;width: 150px;height: 40px;margin-left: 150px;margin-top: -50px;">
				
					</form>
					
				</div>

				<hr style="margin-top:300px">

				<% if(deliveryFee != 0)
				   {
				%>
					<span style="margin-left:450px;color: grey;">Delivery Fee ₹<%= deliveryFee%></span>
				
				<%}
				  else
				  {%>
					
					<span style="margin-left:450px;color: grey;">Free Delivery</span>
				
				<%}%>
			</div>
			
		</div>   									
		<%
		size+=400;
		}
	}
	}
	else if("MyOrders".equalsIgnoreCase(request.getParameter("view")) || "X Cancel Product".equalsIgnoreCase(request.getParameter("remove")))
	{
		if("NotCancelled".equalsIgnoreCase((String)request.getAttribute("notCancel")))
		{
			%>	
				<div id="popup" class="popup" style="background-color: red;">
			  		
			  		<p>Can't Cancelled your Product</p>
					
				</div>

			<%
		}
		if("X Cancel Product".equalsIgnoreCase(request.getParameter("remove")))
		{
			%>	
				<div id="popup" class="popup">
			  		
			  		<i class="fas fa-check-circle"></i>
			  		
			  		<p>Successfully Cancelled your Product</p>
					
				</div>

			<%
		}
		%>
		<div class="header">

			<div style="width:100px;height:30px;margin-top: 30px;margin-left: 50px;">

				<a href="HomePage.jsp" style="text-decoration: none;">
						
					<i class="fa fa-home" style="font-size:28px;color:white;margin-top: 0px;"></i>
					
					<p style="font-size:28px;color:white;font-family: 'Courgette', cursive;margin-left: 40px;margin-top: -30px;">ShoppyWorld</p>

				</a>

			</div>

		</div>
		<div class="cartlist">

			<%		
		
			ArrayList<String> productList=(ArrayList<String>)request.getAttribute("purchasedItems");
			
			if(productList.size() == 0)
			{%>
				<div style="width:400px; height: 100px;box-shadow: 10px 20px 30px 0.3px gray;border-radius: 20px; color: gray;font-family: 'Courgette', cursive;font-size:18px;text-align: center;margin-top: 150px;margin-left: 400px;">

					<p>No Products Available in Purchased List...!</p>
					
				</div>
			<%}
			else
			{
				for(int i=0;i<(productList.size()/12);i++) 
				{
					System.out.println("----Amount--------product.jsp-----"+ productList.get((i*12)+4));		
				%>
					<div class="cart" style="margin-top:<%= size%>px; margin-left: 300px;background-color: #FAE2FE;">

						<div class="product">

							<form action="productlist" method="post">

								<input type="hidden" name="product_id" value="<%= productList.get((i*12)+3)%>">

								<input type="hidden" name="gender" value="<%= productList.get((i*12)+8)%>">

								<input type="hidden" name="variety" value="<%= productList.get((i*12)+9)%>">

								<input type="hidden" name="path" value="<%= servlet_path%>">						

								<button style="border: none;">

									<img id="myImage" src="<%= productList.get((i*12)+3)%>.jpeg" alt="Image" width="200px" height="200px">

								</button>

							</form>

						</div>
						
						<div class="details">
					
							<span>Product Name     :<%= productList.get((i*12)+0)%></span><br>
							
							<span>Product Id       :<%= productList.get((i*12)+3)%></span><br>
							
							<span>Color     	   :<%= productList.get((i*12)+1)%></span><br>

							<span>Price            :<%= productList.get((i*12)+4)%></span><br>

							<!-- <span>Quantity         :<%//= productList.get((i*12)+12)%></span><br> -->

						<%
						
			        
			            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        				LocalDate date = LocalDate.parse(productList.get((i*12)+11), formatter);

        				System.out.println("---Product.jsp----"+productList.get((i*12)+8));

		                if("On Process".equalsIgnoreCase(productList.get((i*12)+8)))
		                {
						%>
						<div style="position: absolute;margin-top: 20px;">
							
							<span style="font-family: 'Courgette', cursive;">Delivered On &nbsp;<%= date%></span><br>
						
						</div>
						<%  
						}
						if("Delivered".equalsIgnoreCase(productList.get((i*12)+8)))
						{
						%>
						<div style="position: absolute;margin-top: 20px;">

							<span style="color:green;font-size: 18px;font-family: 'Courgette', cursive;margin-top: 30px;"><%= productList.get((i*12)+8)%></span><br>

						</div>

						<%  
						}
						if("Cancelled".equalsIgnoreCase(productList.get((i*12)+8)))
						{
						%>
						<div style="position: absolute;margin-top: 20px;">

							<span style="color:red;font-size: 18px;font-family: 'Courgette', cursive;margin-top: 30px;"><%= productList.get((i*12)+8)%></span><br>

						</div>
						<%  
						}

						if(! "Cancelled".equalsIgnoreCase(productList.get((i*12)+8)))
						{
						%>
								
							<form action="buy" method="post">

								<input type="hidden" name="product_id" value="<%= productList.get((i*12)+3)%>">
										
								<input type="submit" name="remove" value="X Cancel Product" style="border:none; cursor: pointer;font-size: 18px; font-style: bold;background-color: #62186F;border-radius: 10px;color: white;font-family: 'Courgette', cursive;width: 200px;height: 50px;margin-top: 60px;">

							</form>

						<%}%>
							
						</div>

					</div>

				<%
				size+= 400;
				}
			}%>
		</div>
			
	<%}

	else if("Cart".equalsIgnoreCase((String)request.getAttribute("name")))
	{
		if("X Remove".equalsIgnoreCase(request.getParameter("cartRemove")))
        {
		%>	
			<div id="popup" class="popup">
			  		
		  		<i class="fas fa-check-circle"></i>
			  		
		  		<p>Successfully Removed your Product</p>
					
			</div>

		<%
		}%>
		<div class="header">

			<div style="width:100px;height:30px;margin-top: 30px;margin-left: 50px;">

				<a href="HomePage.jsp" style="text-decoration: none;">
						
					<i class="fa fa-home" style="font-size:28px;color:white;margin-top: 0px;"></i>
					
					<p style="font-size:28px;color:white;font-family: 'Courgette', cursive;margin-left: 40px;margin-top: -30px;">ShoppyWorld</p>

				</a>

			</div>

		</div>
		
		<div class="cartlist">
		<%		
		
		ArrayList<String> cartList=(ArrayList<String>)request.getAttribute("CartList");
		
		if(cartList.size() == 0)
		{%>
			<div style="width:400px; height: 100px;box-shadow: 10px 20px 30px 0.3px gray;border-radius: 20px; color: gray;font-family: 'Courgette', cursive;font-size:18px;text-align: center;margin-top: 150px;margin-left: 400px;">

				<p>No Products Available in Cart List...!</p>
				
			</div>
		<%}
		else
		{
		for(int i=0;i<(cartList.size()/11);i++) 
		{
			System.out.println("----Amount--------product.jsp-----"+ cartList.get((i*11)+5));		
		%>
			<div class="cart" style="margin-top:<%= size%>px; margin-left: 100px;">

				<div>
					
					<form action="buy" method="post">

						<input type="hidden" name="product_id" value="<%= cartList.get((i*11)+3)%>">
						
						<input type="submit" name="editCart" value="+ Edit">

					</form>

				</div>
				
				<div class="product">

					<form action="productlist" method="post">

						<input type="hidden" name="product_id" value="<%= cartList.get((i*11)+3)%>">

						<input type="hidden" name="gender" value="<%= cartList.get((i*11)+9)%>">

						<input type="hidden" name="variety" value="<%= cartList.get((i*11)+10)%>">					

						<button style="border: none;">

							<img id="myImage" src="<%= cartList.get((i*11)+3)%>.jpeg" alt="Image" width="200px" height="200px">

						</button>

					</form>

				</div>

				<div class="details">
					
					<span>Product Name     :<%= cartList.get((i*11)+0)%></span><br>
					
					<span>Product Id       :<%= cartList.get((i*11)+3)%></span><br>
					
					<span>Color     	   :<%= cartList.get((i*11)+1)%></span><br><br>

				<%  productAmount+=((Double.parseDouble(cartList.get((i*11)+5)))*(Integer.parseInt(cartList.get((i*11)+8))));

					deliveryFee+=(Float.parseFloat(cartList.get((i*11)+6)));

					sum=productAmount+deliveryFee;
					
				%>

					<input type="hidden" name="product" value="<%= cartList.get((i*11)+3)%>">
					
					<span style="width: 100px;height: 50px;border-radius: 5px;">Quantity :<%= cartList.get((i*11)+8)%></span><br><br><br>
						
					<form action="cart" method="post">

						<input type="hidden" name="productId" value="<%= cartList.get((i*11)+3)%>">
								
						<input type="submit" name="cartRemove" value="X Remove" style="border:none; cursor: pointer;font-size: 16px; font-style: bold;">

					</form>
					
				</div>

				<hr style="margin-top:300px">

				<% if(deliveryFee != 0)
				   {
				%>
					<span style="margin-left:450px;color: grey;">Delivery Fee ₹<%= deliveryFee%></span>
				
				<%}
				  else
				  {%>
					
					<span style="margin-left:450px;color: grey;">Free Delivery</span>
				
				<%}%>
			</div>
			
		</div>   									
		<%
		size+=400;
		}%>

		<div class="amountdetail">

			<form action="buy" method="post">

				<div id="amount">

					<input type="hidden" name="productAmount" value="<%= productAmount%>">

					<input type="hidden" name="deliveryFee" value="<%= deliveryFee%>">

					<input type="hidden" name="fromCart" value="Cart_List">

					<span style="color: #62186F;font-family: 'Courgette', cursive;font-size: 24px;font-style: bold;">Price Details</span><br><br>
					
					<span>Total Product Price &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;+₹<%= productAmount%></span><br>

					<span>Additional Fees&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;+₹<%= deliveryFee%></span><br><hr>

					<span style="font-style: bold;">Order Total&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;₹<%= sum%></span><br><br><br>			

					<input type="submit" style="width:250px;height: 50px;border-radius: 10px;background-color: #62186F;color: white; font-size: 22px;font-family: 'Courgette', cursive;padding: 10px;font-style: bold;text-align: center;cursor: pointer; border: none;" value="Continue">

				</div>

			</form>
			
		</div>
	<%}}

	else
	{
		%><%@ include file="HeaderPage.jsp" %>

		<%
		if("Cart".equalsIgnoreCase((String)request.getAttribute("addedToCart")))
		{
		%>
			<div id="popup" class="popup">
			  		
		  		<i class="fas fa-check-circle"></i>
			  		
		  		<p>Successfully Added To Cart</p>
					
			</div>

		<%}%>

		<div class="productDiv">
		<%
			
		System.out.println("productpage...else");

		ArrayList<String> product=(ArrayList<String>)request.getAttribute("productList");
		
		String product_id=(String)session.getAttribute("product_id");
		
		System.out.println("productpage...2");

		for(int i=0;i<(product.size()/8);i++) 
		{
			System.out.println("productpage...for");

			if(product_id.equalsIgnoreCase(product.get((i*8)+6)))
			{
				System.out.println("productpage...if");
				%>

					<%if(Integer.parseInt(product.get((i*8)+5))<=0)
					{%>

						<div style="position:absolute;margin-left: 70px;margin-top: 50px;background-color: aliceblue;height: 400px;width: 400px;opacity: 0.2;">
				
							<img id="myImage" src="<%= product.get((i*8)+6)%>.jpeg" alt="Image" width="400px" height="400px">


							<h4 style="color:gray;">No Available Stock</h4>

						</div>

					<%}
					else
					{%>
				
						<div class="imageDiv">
				
							<img id="myImage" src="<%= product.get((i*8)+6)%>.jpeg" alt="Image" width="400px" height="400px">

						</div>
				
					<%}%>
			

			<div style="position:absolute;margin-left: 700px;margin-top: 50px;background-color: aliceblue;height: 300px;width: 400px;">

				<h2><span><%= product.get((i*8)+0)%></span><br><br><br></h2>

				<span>Product Color   :<%= product.get((i*8)+1)%></span><br>
				
				<span>Size            :<%= product.get((i*8)+2)%></span><br>
				
				<span>Price           :₹<%= product.get((i*8)+3)%></span><br>
				
				<span>Delivery Charge :₹<%= product.get((i*8)+4)%></span><br>
				
				<span>Product Id      :<%= product.get((i*8)+6).toUpperCase()%></span><br>

			</div>

			<div style="position:absolute;margin-left: 100px;margin-top: 480px;">

				<form action="cart" method="post">

					<input type="hidden" name="path" value="<%= servlet_path%>">					

					<input type="hidden" name="product_id" value="<%= product.get((i*8)+6)%>">

					<input type="hidden" name="variety" value="<%= request.getParameter("variety")%>">

					<!-- <input type="submit" value="Add to Cart"> -->

					<button type="submit" style="width:150px;height: 40px;border-radius: 5px;color: #62186F;background-color: #F6EAF8;font-size: 18px;border: 1px solid #62186F;">

						<i class="fa-solid fa-cart-shopping"></i> Add To Cart

					</button>
				
				</form>
			
			</div>

			<div style="position:absolute;margin-left: 300px;margin-top: 480px;">

				<form action="buy" method="post">

					<input type="hidden" name="productName" value="<%= product.get((i*8)+0)%>">

					<input type="hidden" name="productAmount" value="<%= product.get((i*8)+3)%>">

					<input type="hidden" name="deliveryFee" value="<%= product.get((i*8)+4)%>">

					<input type="hidden" name="product_id" value="<%= product.get((i*8)+6)%>">

					<input type="hidden" name="gender" value="<%= product.get((i*8)+7)%>">

					<input type="hidden" name="total_stock" value="<%= product.get((i*8)+5)%>">

					<input type="hidden" name="view" value="BuyNow">

					<input type="hidden" name="variety" value="<%= request.getParameter("variety")%>">
				
					<!-- <input type="submit" value="Buy Now"> -->
					<%if(Integer.parseInt(product.get((i*8)+5))<=0)
					{%>

						<button type="submit" style="width:150px;height: 40px;border-radius: 5px;background-color: #62186F;color: white;font-size: 18px;border: none;" disabled>
						
						Buy Now >>

						</button>

					<%}
					else
					{%>
				
					<button type="submit" style="width:150px;height: 40px;border-radius: 5px;background-color: #62186F;color: white;font-size: 18px;border: none;">
						
						Buy Now >>

					</button>
				
					<%}%>
				</form>

			</div>
	<%
			}					
		}

	%>
	</div>
	<%
}%></div>

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