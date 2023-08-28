<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import ="DataBase.Utilities,Shopping.*,java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">

<style type="text/css">

	.largeDiv
	{
		height: 80%;
		width: 100%;
		background-color: white;
		position: relative;
	}
	.divDress
	{
		margin-left: 70px;
		margin-top: 50px;
		height: 2000px;
		width: 1200px;
		position: absolute;
		background-color: ghostwhite;
		box-sizing: border-box;
		display: flex;
		align-items: stretch;
	}

	.miniDiv
	{
		position: absolute;
		border-radius: 5px;
		transition: transform 0.3s;
	}
	.miniDiv:hover
	{
		transform: scale(1.2);
	}
</style>

</head>
<body>	

	<%@ include file="HeaderPage.jsp" %>
	
	<div class="largeDiv">

		<% String gen_der= request.getParameter("gender");%>

	<div class="divDress">
	<%
		System.out.println("Dresses jsp page..."+request.getParameter("gender")+"---->"+request.getParameter("variety"));
		
		ArrayList<String> productName=new Product().productName(gen_der,request.getParameter("variety"));

		int left=50,margin_top=10;		

		System.out.println(productName.size());

		for (int j=0;j<(productName.size()/5);j++) 
		{
	%>
			
		<div class="miniDiv" style="height:350px;width: 250px;margin-left: <%= left%>px;margin-top: <%= margin_top%>px;">
			
			<form action="productlist" method="post">

				<button style="border: none;">

					<%if(Integer.parseInt(productName.get((j*5)+3))<=0)
					{
					%>			
						<img src="<%= productName.get((j*5)+2)%>.jpeg" alt="Image" width="200px" height="250px" style="opacity: 0.2;">
					<%}
					else
					{%>
						<img src="<%= productName.get((j*5)+2)%>.jpeg" alt="Image" width="200px" height="250px">
					<%}%>

					<input type="hidden" name="gender" value="<%= gen_der%>">

					<input type="hidden" name="variety" value="<%= request.getParameter("variety")%>">

					<input type="hidden" name="product_id" value="<%= productName.get((j*5)+2)%>">
					
					<p><u><%= productName.get((j*5)+2).toUpperCase()%><br>
					<%= productName.get((j*5)+0)%><br>
					₹<%= Double.parseDouble(productName.get((j*5)+1))%></u></p>

				</button>		
		
			</form>

			<%
			if("WishList".equalsIgnoreCase(request.getParameter("view")) && (productName.get((j*5)+2)).equalsIgnoreCase(request.getParameter("product_id")))
			{
			%>

				<div style="position: absolute;margin-top: -60px;height: 5px;width: 5px;margin-left: 170px;background-color: ghostwhite;">
								
					<i class="fas fa-heart" style="size:5px;color:red;"></i>
			
				</div>

			<%}
			else
			{%>

				<div style="position: absolute;margin-top: -60px;height: 5px;width: 5px;margin-left: 170px;background-color: ghostwhite;">

				<form action="productlist" method="post">

					<button style="border:none;size: 5px;background-color: ghostwhite;">

						<input type="hidden" name="product_id" value="<%= productName.get((j*5)+2)%>">

						<input type="hidden" name="gender" value="<%= gen_der%>">

						<input type="hidden" name="variety" value="<%= request.getParameter("variety")%>">

						<input type="hidden" name="view" value="WishList">
								
						<i class="fa-regular fa-heart" style="background-color: ghostwhite;"></i>

					</button>

				</form>
			
			</div>
			<%}%>

		</div>
	<%
		left+=300;

		if((j+1)%4==0)
		{
			margin_top+=400;
			left=50;
		}
	}
	%>
	</div>
	</div>							
</body>
</html>