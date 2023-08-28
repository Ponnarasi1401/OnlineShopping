<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,java.io.*,jakarta.servlet.RequestDispatcher,jakarta.servlet.http.*,jakarta.servlet.annotation.WebServlet,jakarta.servlet.ServletException"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link href="https://fonts.googleapis.com/css2?family=Courgette&display=swap" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>

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
        width: 1350px;
        height: 540px;
        position: absolute;
    }
    .account
    {
        margin-top: 100px;
        margin-left: 450px;
        height: 300px;
        width: 400px;
        position: absolute;
        border-radius: 10px;
        box-shadow: 10px 10px 30px 0.3px gray;
    }
    #choose
    {
        margin-top: 50px;
        margin-left: 50px;
        font-family: 'Courgette', cursive;
        font-size: 20px;
        color: #62186F;
    }
    #buttonInput
    {
        margin-top: 50px;
        margin-left: 120px;
        height: 40px;
        width: 150px;
        font-family: 'Courgette', cursive;
        font-size: 20px;
        background-color: #62186F;
        color: white;
        border: none;
        border-radius: 5px;
    }
    .accountDiv
    {
        margin-top: 70px;
        margin-left: 470px;
        height: 450px;
        width: 400px;
        position: absolute;
        border-radius: 10px;
        box-shadow: 10px 10px 30px 0.3px gray;
    }
    #inputTag
    {
        margin-left: 120px;
        margin-top: 30px;
        height: 30px;
        width: 160px;
        border-radius: 5px;
        border: none;
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

    <%
    if("OnlinePayment".equalsIgnoreCase(request.getParameter("payment")) || "OnlinePayment".equalsIgnoreCase((String)request.getAttribute("payment")))
    {%>
    
    <div class="footer" style="background-image: url('account.jpeg');background-repeat: no-repeat;box-sizing: border-box;
        background-size: cover;background-attachment: fixed;">
    
        <form action="pay" method="post">

            <div class="accountDiv">

                <h2 style="color: #737073;font-family: 'Courgette', cursive;text-align: center;">Account Details</h2>
                
                <input type="text" name="holderName" placeholder="Name" id="inputTag" required>

                <input type="text" name="cardNo" placeholder="Card No." id="inputTag" required>

                <input type="password" name="cvvNo" placeholder="CVV No." id="inputTag" required>

                <input type="text" name="expireDate" placeholder="Expire Date (dd-MM-yyyy)" id="inputTag" required>

                <input type="submit" name="order" value="Confirm Order" style="cursor: pointer;background-color: #737073;" id="buttonInput">

            </div>
        
        </form>

     </div>
    <%}

    if(request.getParameter("address_id")!=null)
    {
    %>
    <div class="footer">
    
    <form action="pay" method="post">
        
        <div class="account">

            <input type="hidden" name="cartAddress" value="<%= request.getParameter("cartAddress")%>">
            
            <input type="radio" name="payment" value="CashOnDelivery" id="choose" ><span id="choose">Cash On Delivery</span><br>

            <input type="radio" name="payment" value="OnlinePayment" id="choose"><span id="choose">Online Payment</span><br>

            <input type="submit" value="Continue" id="buttonInput" style="cursor:pointer;">

        </div>

    </form>

    </div>
    <%
    }
    
    %>
</body>
</html>