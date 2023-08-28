package UserDetails;
import Shopping.*;
import DataBase.Utilities; 
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.ServletException;
import java.io.*;
import java.util.*;
import jakarta.servlet.RequestDispatcher;

public class Address extends HttpServlet {
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession();

        PrintWriter out = response.getWriter();

        System.out.println("Address page...");

        System.out.println(session.getAttribute("total_stock") +"..."+request.getParameter("stock"));

        if(session.getAttribute("userId")==null)
        {
            session.setAttribute("product_id",request.getParameter("product_id"));

            RequestDispatcher dispatcher = request.getRequestDispatcher("LogIn.jsp");

            dispatcher.forward(request, response);

            return;
        }
        else
        {
            int currentUser=(int)session.getAttribute("userId");

            System.out.println("User_id-----Addresspageserv--->"+currentUser+"----Review---->"+request.getParameter("reviewPage")+"-----address_id----->"+request.getParameter("address_id")+"------Address----->"+request.getParameter("useraddress"));
            
            if("Review".equalsIgnoreCase(request.getParameter("reviewPage")))
            {
                double amount=(((Double.parseDouble(request.getParameter("productAmount")))*(Integer.parseInt(request.getParameter("count"))))+(Double.parseDouble(request.getParameter("deliveryFee"))));

                System.out.println(amount);
                
                session.setAttribute("total_price",amount);

                session.setAttribute("address_id",Integer.parseInt(request.getParameter("address_id")));

                session.setAttribute("count",Integer.parseInt(request.getParameter("count")));

                request.setAttribute("Address",new UserUtilities(currentUser).getDeliveryAddress(currentUser,Integer.parseInt(request.getParameter("address_id"))));

                RequestDispatcher dispatcher = request.getRequestDispatcher("Address.jsp");

                dispatcher.forward(request, response);

                return ;
            }
            else if("NewAddress".equalsIgnoreCase(request.getParameter("newAddress")))
            {

                new UserUtilities().setDeliveryAddress(currentUser,request.getParameter("contactNo"),request.getParameter("useraddress"),request.getParameter("pincode"),request.getParameter("city"),request.getParameter("state"));
       
                request.setAttribute("reviewPage","Review");

                int address_id=new UserUtilities((int)session.getAttribute("userId")).getAddressId((int)session.getAttribute("userId"));

                System.out.println(address_id);

                request.setAttribute("Address",new UserUtilities(currentUser).getDeliveryAddress(currentUser,address_id));

                RequestDispatcher dispatcher = request.getRequestDispatcher("Address.jsp");

                dispatcher.forward(request, response);

                return ;
            }
            else if("Cart_Address".equalsIgnoreCase(request.getParameter("cartAddress")))
            {

                double amount=Double.parseDouble(request.getParameter("productAmount"))+Double.parseDouble(request.getParameter("deliveryFee"));

                session.setAttribute("address_id",Integer.parseInt(request.getParameter("address_id"))); 
               
                session.setAttribute("total_price",amount);

                RequestDispatcher dispatcher = request.getRequestDispatcher("Account.jsp");

                dispatcher.forward(request, response);

                return ;
            
            }
            else
            {
                if(request.getParameter("stock") != null)
                {
                    if((Integer.parseInt(request.getParameter("stock")))<=((int)session.getAttribute("total_stock")) && (Integer.parseInt(request.getParameter("stock")))>=0)
                    {
                        request.setAttribute("Address",new UserUtilities(currentUser).getDeliveryAddress(currentUser));

                        RequestDispatcher dispatcher = request.getRequestDispatcher("Address.jsp");

                        dispatcher.forward(request, response);

                        return ;
                    }
                    else
                    {
                        request.setAttribute("view","BuyNow");

                        request.setAttribute("Address",new UserUtilities(currentUser).getDeliveryAddress(currentUser));

                        RequestDispatcher dispatcher = request.getRequestDispatcher("Address.jsp");

                        dispatcher.forward(request, response);

                        return ;
                    }
                }
                else
                {
                    request.setAttribute("Address",new UserUtilities(currentUser).getDeliveryAddress(currentUser));

                    RequestDispatcher dispatcher = request.getRequestDispatcher("Address.jsp");

                    dispatcher.forward(request, response);

                    return ;
                }
            }
        }
    }
}