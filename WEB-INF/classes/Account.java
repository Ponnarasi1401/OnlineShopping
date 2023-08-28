package PaymentDetails;
import Shopping.*;
import UserDetails.*;
import DataBase.Utilities; 
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.ServletException;
import java.io.*;
import java.util.*;
import jakarta.servlet.RequestDispatcher;

public class Account extends HttpServlet 
{
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

        HttpSession session = request.getSession();

        PrintWriter out = response.getWriter();

        System.out.println("Address page...");

        Purchased_Items purchasedItem=new Purchased_Items();

        if("CashOnDelivery".equalsIgnoreCase(request.getParameter("payment")))
        {
            if("Cart_Address".equalsIgnoreCase(request.getParameter("cartAddress")))
            {
                ArrayList<String> cart=new CartList().CartList((int)session.getAttribute("userId"));

                for(int i=0;i<(cart.size()/11);i++)
                {
                    if( Integer.parseInt(cart.get((i*11)+8)) <= Integer.parseInt(cart.get((i*11)+7)) )
                    {
                        for(int j=0;j<Integer.parseInt(cart.get((i*11)+8));j++)
                        {
                            boolean isDressAdd = purchasedItem.isDressAddedToPurchasedList(cart.get((i*11)+3),(int)session.getAttribute("userId"),(int)session.getAttribute("address_id"));
                    
                            boolean isAccessoriesAdd = purchasedItem.isAccessoriesAddedToPurchasedList(cart.get((i*11)+3),(int)session.getAttribute("userId"),(int)session.getAttribute("address_id"));
                            
                            boolean isFootWearAdd = purchasedItem.isFootWearAddedToPurchasedList(cart.get((i*11)+3),(int)session.getAttribute("userId"),(int)session.getAttribute("address_id"));

                            if (isDressAdd || isAccessoriesAdd || isFootWearAdd) 
                            {

                                System.out.println("(*<>*)...Your Product is Sold...(*<>*)");
                        
                                new CartList((String)session.getAttribute("product_id")).removeProduct(cart.get((i*11)+3),(int)session.getAttribute("userId"));
                            
                            }
                        }
                    }
                    else
                    {
                        out.println("No Stock Available...");
                    }
                }
            }
            else
            {
                int count=(int)session.getAttribute("count");

                for (int i = 0; i < count; i++) 
                {
                    boolean isDressAdd = purchasedItem.isDressAddedToPurchasedList((String)session.getAttribute("product_id"),(int)session.getAttribute("userId"),(int)session.getAttribute("address_id"));
                    
                    boolean isAccessoriesAdd = purchasedItem.isAccessoriesAddedToPurchasedList((String)session.getAttribute("product_id"),(int)session.getAttribute("userId"),(int)session.getAttribute("address_id"));
                    
                    boolean isFootWearAdd = purchasedItem.isFootWearAddedToPurchasedList((String)session.getAttribute("product_id"),(int)session.getAttribute("userId"),(int)session.getAttribute("address_id"));

                    if (isDressAdd || isAccessoriesAdd || isFootWearAdd) 
                    {

                        System.out.println("(*<>*)...Your Product is Sold...(*<>*)");
                
                        new CartList((String)session.getAttribute("product_id")).removeProduct((String)session.getAttribute("product_id"),(int)session.getAttribute("userId"));
                    
                    } 
                    else
                    {
                        out.println("product not found...");
                    }
                }
            }
    
            request.setAttribute("purchasedItems",new Purchased_Items((String)session.getAttribute("product_id")).getPurchasedList((int)session.getAttribute("userId")));
                    
            request.setAttribute("Address",new UserUtilities((int)session.getAttribute("userId")).getDeliveryAddress((int)session.getAttribute("userId"),(int)session.getAttribute("address_id")));
 
            request.setAttribute("product_id",(String)session.getAttribute("product_id"));

            RequestDispatcher dispatcher = request.getRequestDispatcher("Summary.jsp");

            dispatcher.forward(request, response);

            return;
    
        }
    
        if("OnlinePayment".equalsIgnoreCase(request.getParameter("payment")))
        {
            RequestDispatcher dispatcher = request.getRequestDispatcher("Account.jsp");

            dispatcher.forward(request, response);

            return;
        }

        if("Confirm Order".equalsIgnoreCase(request.getParameter("order")))
        {

            CartList cartList=new CartList((String)session.getAttribute("product_id"));

            boolean isCorrectAccount=new UserUtilities().isCorrectUserAccount(request.getParameter("holderName"),Long.parseLong(request.getParameter("cardNo")),Integer.parseInt(request.getParameter("cvvNo")),request.getParameter("expireDate"));

            System.out.println(isCorrectAccount+"--Name---"+request.getParameter("holderName")+"-----c No-----"+Long.parseLong(request.getParameter("cardNo"))+"-----cv no-----"+Integer.parseInt(request.getParameter("cvvNo"))+"------date------"+request.getParameter("expireDate"));

            if(isCorrectAccount)
            {

                boolean isTransacted = new CartList((String)session.getAttribute("product_id")).isPurchased((double)session.getAttribute("total_price"),Long.parseLong(request.getParameter("cardNo")));
                     
                System.out.println(isTransacted+"----isTransacted-----");

                if (isTransacted) 
                {
                    if("Cart_Address".equalsIgnoreCase(request.getParameter("cartAddress")))
                    {
                        ArrayList<String> cart=new CartList().CartList((int)session.getAttribute("userId"));

                        for(int i=0;i<(cart.size()/11);i++)
                        {
                            if( Integer.parseInt(cart.get((i*11)+8)) <= Integer.parseInt(cart.get((i*11)+7)) )
                            {
                                for(int j=0;j<Integer.parseInt(cart.get((i*11)+8));j++)
                                {
                                    boolean isDressAdd = purchasedItem.isDressAddedToPurchasedList(cart.get((i*11)+3),(int)session.getAttribute("userId"),(int)session.getAttribute("address_id"));
                            
                                    boolean isAccessoriesAdd = purchasedItem.isAccessoriesAddedToPurchasedList(cart.get((i*11)+3),(int)session.getAttribute("userId"),(int)session.getAttribute("address_id"));
                                    
                                    boolean isFootWearAdd = purchasedItem.isFootWearAddedToPurchasedList(cart.get((i*11)+3),(int)session.getAttribute("userId"),(int)session.getAttribute("address_id"));

                                    if (isDressAdd || isAccessoriesAdd || isFootWearAdd) 
                                    {

                                        System.out.println("(*<>*)...Your Product is Sold...(*<>*)");
                                
                                        new CartList((String)session.getAttribute("product_id")).removeProduct(cart.get((i*11)+3),(int)session.getAttribute("userId"));
                                    
                                    }
                                }
                            }
                            else
                            {
                                out.println("No Stock Available...");
                            }
                        }
                    }
                    else
                    {
                        int count=(int)session.getAttribute("count");

                        for (int i = 0; i < count; i++) 
                        {
                            boolean isDressAdd = purchasedItem.isDressAddedToPurchasedList((String)session.getAttribute("product_id"), (int)session.getAttribute("userId"),(int)session.getAttribute("address_id"));
                           
                            boolean isAccessoriesAdd = purchasedItem.isAccessoriesAddedToPurchasedList((String)session.getAttribute("product_id"),(int)session.getAttribute("userId"),(int)session.getAttribute("address_id"));
                           
                            boolean isFootWearAdd = purchasedItem.isFootWearAddedToPurchasedList((String)session.getAttribute("product_id"),(int)session.getAttribute("userId"),(int)session.getAttribute("address_id"));
                                    
                            if (isDressAdd || isAccessoriesAdd || isFootWearAdd) 
                            {

                                System.out.println("(*<>*)...Your Product is Sold...(*<>*)");
                        
                                new CartList((String)session.getAttribute("product_id")).removeProduct((String)session.getAttribute("product_id"),(int)session.getAttribute("userId"));
                            
                            }
                        }                        
                    } 

                    request.setAttribute("purchasedItems",new Purchased_Items((String)session.getAttribute("product_id")).getPurchasedList((int)session.getAttribute("userId")));
                    
                    request.setAttribute("Address",new UserUtilities((int)session.getAttribute("userId")).getDeliveryAddress((int)session.getAttribute("userId"),(int)session.getAttribute("address_id")));
                    
                    request.setAttribute("product_id",(String)session.getAttribute("product_id"));

                    RequestDispatcher dispatcher = request.getRequestDispatcher("Summary.jsp");

                    dispatcher.forward(request, response);

                    return;
                }
                else
                {
         
                    System.out.println("Check enough Balance to your Account...");
         
                }

            }
            else
            {
                System.out.println(isCorrectAccount+" ---- else method");
             
                request.setAttribute("payment","OnlinePayment");

                RequestDispatcher dispatcher = request.getRequestDispatcher("Account.jsp");

                dispatcher.forward(request, response);

                return;
            }
        }
    }
}