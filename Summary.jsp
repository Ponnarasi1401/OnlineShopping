<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,java.io.*,jakarta.servlet.RequestDispatcher,jakarta.servlet.http.*,jakarta.servlet.annotation.WebServlet,jakarta.servlet.ServletException,java.time.LocalDate,java.time.format.DateTimeFormatter"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link href="https://fonts.googleapis.com/css2?family=Courgette&display=swap" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" integrity="sha512-iA9i9J8P1RpQyHz6Ckuk4RfP4jr4l3PPpxUlgfxIe7i3g2vntO68EA2XZv1Vw8Um/Op1JHbZ4L13oza8JvtzQ==" crossorigin="anonymous" referrerpolicy="no-referrer"/>

<link href="https://fonts.googleapis.com/css2?family=Courgette&display=swap" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<style type="text/css">
    .header
    {
        margin-top: -30px;
        height: 100px;
        width: 100%;
        position: relative;
        background-color: #62186F;
    }
    .footer
    {
        height: 80%;
        width: 100%;
    }
   
    .addressDetail
    {
        margin-left: 200px;
        width:400px; 
        height: 200px; 
        position:absolute;
        background-color: whitesmoke;
        border-radius: 5px;
        box-shadow: 10px 5px 3px 1px gray;
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
    .details
    {
        margin-top: 70px;
        margin-left: 300px;
        height: 300px;
        width: 300px;
        position: absolute;
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

    <div class="header">

        <div style="width:100px;height:30px;margin-top: 30px;margin-left: 50px;">

            <a href="HomePage.jsp" style="text-decoration: none;">
                        
                <i class="fa fa-home" style="font-size:28px;color:white;margin-top: 30px;"></i>
                    
                <p style="font-size:28px;color:white;font-family: 'Courgette', cursive;margin-left: 40px;margin-top: -30px;">ShoppyWorld</p>

            </a>

        </div>

    </div>1
    
<div class="footer">

    <%
    if("Confirm Order".equalsIgnoreCase(request.getParameter("order")) || "CashOnDelivery".equalsIgnoreCase(request.getParameter("payment")))
    {%>

        <div id="popup" class="popup">
                    
            <i class="fas fa-check-circle"></i>
                    
            <p>Your order has been confirmed</p>
                    
        </div>

    <%}
%>
<div style="margin-top:250px;">
<%
    ArrayList<String> productList=(ArrayList<String>)request.getAttribute("purchasedItems");

    for(int i=0;i<(productList.size()/12);i++) 
    {
        if((productList.get((i*12)+3)).equalsIgnoreCase((String)request.getAttribute("product_id")))
        {
        %>
    
        <div class="details">

            <button style="border: none;">

                <img id="myImage" src="<%= productList.get((i*12)+3)%>.jpeg" alt="Image" width="200px" height="200px">

            </button>
                        
            <span>Product Name     :<%= productList.get((i*12)+0)%></span><br>
                                
            <span>Product Id       :<%= productList.get((i*12)+3)%></span><br>
                                
            <span>Color            :<%= productList.get((i*12)+1)%></span><br>

            <span>Price            :<%= productList.get((i*12)+4)%></span><br>

            <!-- <span>Quantity         :<%//= productList.get((i*12)+12)%></span><br> -->

        <%
                            
                        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate date = LocalDate.parse(productList.get((i*12)+11), formatter);

        System.out.println("---Summary.jsp----"+productList.get((i*12)+8));

        if("On Process".equalsIgnoreCase(productList.get((i*12)+8)))
        {
            %>
            <div style="position: absolute;margin-top: 20px;">
                
                <span style="font-family: 'Courgette', cursive;">Delivered On &nbsp;<%= date%></span><br>
            
            </div>
            <%  
        }
        }
    }
        %>
    </div>

</div>
    <div style="background-color: white;position: absolute;">

        <%
            ArrayList<String> deliveryAddress=(ArrayList<String>)request.getAttribute("Address");

            for (int i=0; i<(deliveryAddress.size()/7);i++) 
            {
                
            %>
            <div class="addressDetail" style="margin-top: 50px;background-color: whitesmoke;">
                
                <input type="hidden" name="address_id" value="<%= deliveryAddress.get((i*7)+0)%>">

                <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%= deliveryAddress.get((i*7)+1)%></span><br>
                <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%= deliveryAddress.get((i*7)+2)%></span><br>
                <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%= deliveryAddress.get((i*7)+3)%></span><br>
                <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%= deliveryAddress.get((i*7)+4)%></span><br>
                <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%= deliveryAddress.get((i*7)+5)%></span><br>
                <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%= deliveryAddress.get((i*7)+6)%></span>
                
            </div>
            <%    
            }
        %>    

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