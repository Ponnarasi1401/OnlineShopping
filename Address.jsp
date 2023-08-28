<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,java.io.*,jakarta.servlet.RequestDispatcher,jakarta.servlet.http.*,jakarta.servlet.annotation.WebServlet,jakarta.servlet.ServletException,java.time.LocalDate,java.time.format.DateTimeFormatter"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link href="https://fonts.googleapis.com/css2?family=Courgette&display=swap" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" integrity="sha384-7C5JISoI9O1RWRgIhV4A4/JpXnMWrFk4v5u5f5+4Wm0Ej2odq4HV2tqF5X6Isx4s" crossorigin="anonymous">


<style type="text/css">
    
    .header
    {
        margin-top: -30px;
        height: 100px;
        width: 100%;
        position: relative;
        background-color: #62186F;
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
      background-color: red;
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
    #amount
    {
        margin-top: 100px;
        margin-left: 900px;
        height: 300px;
/*      width: 300px;*/
        position: absolute;
    }
    .buynow
    {
        font-family: 'Courgette', cursive;
        height: 300px;
        width: 500px;
        margin-left: 200px;
        margin-top: 100px;
        border-radius: 5px;
        box-shadow: 10px 10px 30px 0.3px gray;
        position: absolute;
    }
    .product_list
    {
        font-family: 'Courgette', cursive;
        height: 250px;
        width: 400px;
        margin-left: 200px;
        margin-top: 50px;
        border-radius: 5px;
        box-shadow: 10px 10px 30px 0.3px gray;
        position: absolute;
    }
    .footer
    {
        height: 80%;
        width: 100%;
    }
    
    .add
    {
        font-family: 'Courgette', cursive;
        height: 520px;
        width: 500px;
        margin-left: 400px;
        margin-top: 20px;
        border-radius: 5px;
        box-shadow: 10px 10px 30px 0.3px gray;
        position: absolute;
    }
    .address
    {
        height: 80%;
        width: 700px;
        position: absolute;
    }
    .addressDetail
    {
        margin-left: 200px;
        width:400px; 
        height: 200px; 
        position:absolute;
        background-color: #F6EAF8;
        border-radius: 5px;
        box-shadow: 10px 5px 3px 1px gray;
    }
    .addAddress
    {
        position: absolute;
        margin-top: 75px;
        margin-left: 425px;
        border-radius: 5px;
    }
    #d1
    {
        height: 40px;
        width: 250px;
        margin-top: 10px;
        margin-left: 125px;
        border-radius: 5px;
        border: none;
        background-color: lightgray;
    }
</style>
</head>
<body>

    <div class="header">

        <div style="width:100px;height:30px;margin-top: 30px;margin-left: 50px;">

            <!-- <a href=""> -->
                
                <!-- <i class="fa-regular fa-circle-arrow-left"></i> -->
                
            <!-- </a> -->

            <a href="HomePage.jsp" style="text-decoration: none;">
                        
                <i class="fa fa-home" style="font-size:28px;color:white;margin-top: 30px;"></i>
                    
                <p style="font-size:28px;color:white;font-family: 'Courgette', cursive;margin-left: 40px;margin-top: -30px;">ShoppyWorld</p>

            </a>

        </div>

    </div>

    <div class="footer">
        
    <%
    String path=request.getServletPath();

    if("Review".equalsIgnoreCase(request.getParameter("reviewPage")) || "Review".equalsIgnoreCase((String)request.getAttribute("reviewPage")))
    {
        System.out.println("inside if"+request.getParameter("reviewPage") +request.getParameter("productName"));
    %>
        <form action="Account.jsp" method="post">
        
            <div class="product_list">

                <button style="border: none;margin-top: 60px;margin-left: 50px;">

                   <img id="myImage" src="<%= request.getParameter("product_id")%>.jpeg" alt="Image" width="100px" height="100px">

                </button>

                <h3 style="margin-top:-100px;margin-left: 250px;"><b><%= request.getParameter("productName")%></b></h3>

                <input type="hidden" name="productName" value="<%= request.getParameter("productName")%>">

                <input type="hidden" name="product_id" value="<%= request.getParameter("product_id")%>">

                <input type="hidden" name="productAmount" value="<%= request.getParameter("productAmount")%>">

                <input type="hidden" name="deliveryFee" value="<%= request.getParameter("deliveryFee")%>">

                <input type="hidden" name="path" value="<%= path%>">

                <input type="hidden" name="gender" value="<%= request.getParameter("gender")%>">

                <input type="hidden" name="variety" value="<%= request.getParameter("variety")%>">

                <p style="font-size:16px;color:#62186F;margin-top:0px;margin-left:250px;">₹<%= request.getParameter("productAmount")%></p>

                <p style="font-size:16px;color:#62186F;margin-top:0px;margin-left: 250px">Quantity<p>&nbsp;&nbsp;&nbsp;<span style="margin-top:-250px;margin-left:250px"><%= request.getParameter("count")%></span>
        
            </div>    

        <div style="background-color: white;">

        <%
            ArrayList<String> deliveryAddress=(ArrayList<String>)request.getAttribute("Address");

            for (int i=0; i<(deliveryAddress.size()/7);i++) 
            {
                
            %>
            <div class="addressDetail" style="margin-top: 340px;">
                
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
            
        <div>

        <div id="amount">

            <%
                System.out.println(request.getParameter("productAmount")+"......Amount......"+request.getParameter("deliveryFee"));
                        
                double productAmount=(Double.parseDouble(request.getParameter("productAmount")))*(Integer.parseInt(request.getParameter("count")));
                
                double deliveryFee=Double.parseDouble(request.getParameter("deliveryFee"));

            %>

                <span style="color: #62186F;font-family: 'Courgette', cursive;font-size: 24px;font-style: bold;">Price Details</span><br><br>
                        
                <span>Total Product Price &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;+₹ <%= productAmount%></span><br>

                <span>Additional Fees &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;+₹<%= deliveryFee%></span><br><hr>

                <span style="font-style: bold;">Order Total &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;₹<%= (productAmount+deliveryFee)%></span><br><br><br>            

                <input type="submit" style="width:250px;height: 50px;border-radius: 10px;background-color: #62186F;color: white; font-size: 22px;font-family: 'Courgette', cursive;padding: 10px;font-style: bold;text-align: center;cursor: pointer; border: none;" value="Continue ---->">

        </div>

        </div>   

        </form>    

    <%
    }

    else if("BuyNow".equalsIgnoreCase(request.getParameter("view")) || "BuyNow".equalsIgnoreCase((String)request.getAttribute("view")))
    {
        if("BuyNow".equalsIgnoreCase((String)request.getAttribute("view")))
        {
            %>  
                <div id="popup" class="popup">
                    
                   <i class="fa-solid fa-exclamation"></i>
                    
                    <p>Invalid Number</p>
                    
                </div>

            <%
        }
        %>
        <form action="address" method="post">

            <div class="buynow">

                <button style="border: none;margin-top: 80px;margin-left: 50px;">

                   <img id="myImage" src="<%= request.getParameter("product_id")%>.jpeg" alt="Image" width="100px" height="100px">

                </button>

                <h3 style="margin-top:-100px;margin-left: 250px;"><b><%= request.getParameter("productName")%></b></h3>

                <input type="hidden" name="productName" value="<%= request.getParameter("productName")%>">

                <input type="hidden" name="product_id" value="<%= request.getParameter("product_id")%>">

                <input type="hidden" name="productAmount" value="<%= request.getParameter("productAmount")%>">

                <input type="hidden" name="deliveryFee" value="<%= request.getParameter("deliveryFee")%>">

                <input type="hidden" name="path" value="<%= path%>">

                <input type="hidden" name="gender" value="<%= request.getParameter("gender")%>">

                <input type="hidden" name="variety" value="<%= request.getParameter("variety")%>">

                <p style="font-size:16px;color:#62186F;margin-top:0px;margin-left:250px;">₹<%= request.getParameter("productAmount")%></p>
    
                <!-- <p style="font-size:16px;color:#62186F;margin-top:0px;margin-left: 250px;">Quantity<p> -->

                <input type="number" name="stock" placeholder="Quantity" style="margin-top: -170px;margin-left: 250px;height: 30px; width:100px; border-radius:4px;font-family: 'Courgette', cursive;border: none;">
                
        
            </div>

            <div>

            <div id="amount">

            <%
                System.out.println(request.getParameter("productAmount")+"......Amount......"+request.getParameter("deliveryFee"));
                        
                double productAmount=(Double.parseDouble(request.getParameter("productAmount")));
                
                double deliveryFee=Double.parseDouble(request.getParameter("deliveryFee"));

            %>

                <span style="color: #62186F;font-family: 'Courgette', cursive;font-size: 24px;font-style: bold;">Price Details</span><br><br>
                        
                <span>Total Product Price &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;+₹ <%= productAmount%></span><br>

                <span>Additional Fees &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;+₹<%= deliveryFee%></span><br><hr>

                <span style="font-style: bold;">Order Total &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;₹<%= (productAmount+deliveryFee)%></span><br><br><br>            

                <input type="submit" style="width:250px;height: 50px;border-radius: 10px;background-color: #62186F;color: white; font-size: 22px;font-family: 'Courgette', cursive;padding: 10px;font-style: bold;text-align: center;cursor: pointer; border: none;" value="Select Address">

            </div>
        
            </div>
        </form>
        <%
    } 
    else
    {
        ArrayList<String> deliveryAddress=(ArrayList<String>)request.getAttribute("Address");

        if(deliveryAddress==null || "NewAddress".equalsIgnoreCase(request.getParameter("newAddress")))
        {
    %>
        <div class="add">
       
            <form action="address" method="post">

                <input type="hidden" name="productName" value="<%= request.getParameter("productName")%>">

                <input type="hidden" name="product_id" value="<%= request.getParameter("product_id")%>">

                <input type="hidden" name="productAmount" value="<%= request.getParameter("productAmount")%>">

                <input type="hidden" name="deliveryFee" value="<%= request.getParameter("deliveryFee")%>">

                <input type="hidden" name="path" value="<%= path%>">

                <input type="hidden" name="gender" value="<%= request.getParameter("gender")%>">

                <input type="hidden" name="variety" value="<%= request.getParameter("variety")%>">

                <input type="hidden" name="newAddress" value="NewAddress">
        
                <h3 style="color: #62186F; font-size: 22px;font-family: 'Courgette', cursive;text-align: center;">Contact Details</h3>

                <input type="text" name="customerName" placeholder="Name" id="d1" required ><br>

                <input type="text" name="contactNo" placeholder="Contact Number" id="d1" required><br>

                <h3 style="color: #62186F; font-size: 22px;font-family: 'Courgette', cursive;text-align: center;">Address</h3>

                <input type="text" name="useraddress" placeholder="House No./Area" id="d1" required><br>

                <input type="text" name="pincode" placeholder="Pincode" id="d1" required><br>

                <input type="text" name="city" placeholder="City" id="d1" required>

                <input type="text" name="state" placeholder="State" id="d1" required><br>

                <input type="submit" value="Save Address & Continue" required style="width:200px;height:40px;border:none;border-radius: 5px;color: white;background-color: #62186F; font-size: 16px;font-family: 'Courgette', cursive;margin-left: 150px;margin-top: 10px;cursor: pointer;">

            </form>

        </div>
    <%

    }
    else
    {
    %>    
        <div class="address">

            <div class="addAddress">

                <form action="Address.jsp" action="get">

                    <input type="hidden" name="count" value="<%= request.getParameter("stock")%>">

                    <input type="hidden" name="productName" value="<%= request.getParameter("productName")%>">

                    <input type="hidden" name="product_id" value="<%= request.getParameter("product_id")%>">

                    <input type="hidden" name="productAmount" value="<%= request.getParameter("productAmount")%>">

                    <input type="hidden" name="deliveryFee" value="<%= request.getParameter("deliveryFee")%>">

                    <input type="hidden" name="path" value="<%= path%>">

                    <input type="hidden" name="gender" value="<%= request.getParameter("gender")%>">

                    <input type="hidden" name="variety" value="<%= request.getParameter("variety")%>">
                    
                    <button type="submit" style="border: none;color: #62186F;background-color: #F6EAF8;font-size: 16px;height: 40px;width: 170px;border-radius: 5px;cursor: pointer;">

                        <input type="hidden" name="newAddress" value="NewAddress">
                        
                        <span><b>+ Add Address</b></span>

                    </button>
                
                </form>

            </div>

            <form action="address" method="post">

                <input type="hidden" name="count" value="<%= request.getParameter("stock")%>">

                <input type="hidden" name="productName" value="<%= request.getParameter("productName")%>">

                <input type="hidden" name="product_id" value="<%= request.getParameter("product_id")%>">

                <input type="hidden" name="productAmount" value="<%= request.getParameter("productAmount")%>">

                <input type="hidden" name="deliveryFee" value="<%= request.getParameter("deliveryFee")%>">

                <input type="hidden" name="path" value="<%= path%>">

                <input type="hidden" name="gender" value="<%= request.getParameter("gender")%>">

                <input type="hidden" name="variety" value="<%= request.getParameter("variety")%>">

        <%
            int address=1,size=100;

            for (int i=0; i<(deliveryAddress.size()/7);i++) 
            {
                
            %>
            <div class="addressDetail" style="margin-top: <%= size%>px;">
                    
                <input type="radio" name="address" value="<%= address%>" required>
                
                <input type="hidden" name="address_id" value="<%= deliveryAddress.get((i*7)+0)%>">

                <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%= deliveryAddress.get((i*7)+1)%></span><br>
                <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%= deliveryAddress.get((i*7)+2)%></span><br>
                <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%= deliveryAddress.get((i*7)+3)%></span><br>
                <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%= deliveryAddress.get((i*7)+4)%></span><br>
                <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%= deliveryAddress.get((i*7)+5)%></span><br>
                <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%= deliveryAddress.get((i*7)+6)%></span>
                
            </div>
            <%    
                address++;
                size+=300;
            }
        %>            
            
        </div>
        <div>

            <div id="amount">

            <%
                System.out.println(request.getParameter("productAmount")+"......Amount......"+request.getParameter("deliveryFee"));
                 
                double productAmount=0,deliveryFee=0;
                         
                if("Cart_List".equalsIgnoreCase(request.getParameter("fromCart")))
                {
                    productAmount=(Double.parseDouble(request.getParameter("productAmount")));
                    
                    deliveryFee=Double.parseDouble(request.getParameter("deliveryFee"));%>

                    <input type="hidden" name="cartAddress" value="Cart_Address">

                    <span style="color: #62186F;font-family: 'Courgette', cursive;font-size: 24px;font-style: bold;">Price Details</span><br><br>
                            
                    <span>Total Product Price &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;+₹ <%= productAmount%></span><br>

                    <span>Additional Fees &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;+₹<%= deliveryFee%></span><br><hr>

                    <span style="font-style: bold;">Order Total &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;₹<%= (productAmount+deliveryFee)%></span><br><br><br>            

                    <input type="submit" style="width:250px;height: 50px;border-radius: 10px;background-color: #62186F;color: white; font-size: 22px;font-family: 'Courgette', cursive;padding: 10px;font-style: bold;text-align: center;cursor: pointer; border: none;" value="Continue">
                <%}
                else
                {
                    productAmount=(Double.parseDouble(request.getParameter("productAmount")))*(Integer.parseInt(request.getParameter("stock")));
                    
                    deliveryFee=Double.parseDouble(request.getParameter("deliveryFee"));%>
                    
                    <input type="hidden" name="reviewPage" value="Review">          

                    <span style="color: #62186F;font-family: 'Courgette', cursive;font-size: 24px;font-style: bold;">Price Details</span><br><br>
                            
                    <span>Total Product Price &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;+₹ <%= productAmount%></span><br>

                    <span>Additional Fees &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;+₹<%= deliveryFee%></span><br><hr>

                    <span style="font-style: bold;">Order Total &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;₹<%= (productAmount+deliveryFee)%></span><br><br><br>            

                    <input type="submit" style="width:250px;height: 50px;border-radius: 10px;background-color: #62186F;color: white; font-size: 22px;font-family: 'Courgette', cursive;padding: 10px;font-style: bold;text-align: center;cursor: pointer; border: none;" value="Continue">              
            <%    }%>

            </div>
        
        </div>
        </form>
    <%
    }   
}
    %>     

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