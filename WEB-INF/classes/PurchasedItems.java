package Shopping;
import DataBase.Utilities; 
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.ServletException;
import java.io.*;
import java.util.*;
import jakarta.servlet.RequestDispatcher;

public class PurchasedItems extends HttpServlet {
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession();

        PrintWriter out = response.getWriter();

        System.out.println("purchase page...." +request.getParameter("view")+".........Amount........."+request.getParameter("productAmount")+"***"+request.getParameter("deliveryFee"));
        
        System.out.println(request.getParameter("total_stock") +"...."+session.getAttribute("total_stock") +"..."+request.getParameter("stock"));


        if(request.getParameter("total_stock") != null)
        {
            session.setAttribute("total_stock",Integer.parseInt(request.getParameter("total_stock")));
        }

        if(session.getAttribute("userId")==null)
        {
            session.setAttribute("product_id",request.getParameter("product_id"));

            RequestDispatcher dispatcher = request.getRequestDispatcher("LogIn.jsp");

            dispatcher.forward(request, response);

            return;
        }                                       
        else
        {
            if("MyOrders".equalsIgnoreCase(request.getParameter("view")))
            {

                request.setAttribute("purchasedItems",new Purchased_Items((String)session.getAttribute("product_id")).getPurchasedList((int)session.getAttribute("userId")));
        
                RequestDispatcher dispatcher = request.getRequestDispatcher("Product.jsp");

                dispatcher.forward(request, response);

                return;

            }
            if("X Cancel Product".equalsIgnoreCase(request.getParameter("remove")))
            {

                boolean isCancelled = new Purchased_Items((String)session.getAttribute("product_id")).isProductCancelled(request.getParameter("product_id"),(int)session.getAttribute("userId"));

                System.out.println("------purchaseditems page------"+isCancelled+"----------"+request.getParameter("product_id"));

                if(isCancelled)
                {
                    request.setAttribute("purchasedItems",new Purchased_Items((String)session.getAttribute("product_id")).getPurchasedList((int)session.getAttribute("userId")));
            
                    RequestDispatcher dispatcher = request.getRequestDispatcher("Product.jsp");

                    dispatcher.forward(request, response);

                    return;
                }
                else
                {
                    request.setAttribute("notCancel","NotCancelled");

                    request.setAttribute("purchasedItems",new Purchased_Items((String)session.getAttribute("product_id")).getPurchasedList((int)session.getAttribute("userId")));
            
                    RequestDispatcher dispatcher = request.getRequestDispatcher("Product.jsp");

                    dispatcher.forward(request, response);

                    return;
                }
            }
            if("BuyNow".equalsIgnoreCase(request.getParameter("view")))
            {

                RequestDispatcher dispatcher = request.getRequestDispatcher("Address.jsp");

                dispatcher.forward(request, response);

                return;
            
            }
            else
            {
                System.out.println("-----from cart--------"+request.getParameter("fromCart"));

                if("Cart_List".equalsIgnoreCase(request.getParameter("fromCart")))
                {
                    ArrayList<String> cart=new CartList().CartList((int)session.getAttribute("userId"));

                    for(int i=0;i<(cart.size()/11);i++) 
                    {
                        if(Integer.parseInt(cart.get((i*11)+8))<=Integer.parseInt(cart.get((i*11)+7)))
                        {
                            boolean isAvailableDress = new ProductList((String)session.getAttribute("product_id")).isAvailableDressProduct(cart.get((i*11)+3), Integer.parseInt(cart.get((i*11)+8)));
                           
                            // System.out.println("Available dress...."+isAvailableDress+" ---Count...."+Integer.parseInt(request.getParameter("count"))+"  product_id...."+request.getParameter("product_id"));

                            boolean isAvailableAccessories =new ProductList((String)session.getAttribute("product_id")).isAvailableAccessoriesProduct(cart.get((i*11)+3), Integer.parseInt(cart.get((i*11)+8)));
                           
                            boolean isAvailableFootWear =new ProductList((String)session.getAttribute("product_id")).isAvailableFootWearsProduct(cart.get((i*11)+3), Integer.parseInt(cart.get((i*11)+8)));

                            if (isAvailableDress || isAvailableAccessories || isAvailableFootWear) 
                            {
                                request.setAttribute("stock",Integer.parseInt(cart.get((i*11)+8)));
                       
                                RequestDispatcher dispatcher = request.getRequestDispatcher("address");

                                dispatcher.forward(request, response);

                                return;
                       
                            }
                            else
                            {
                                request.setAttribute("name","Cart");
                           
                                request.setAttribute("CartList",cart);

                                RequestDispatcher dispatcher = request.getRequestDispatcher("Product.jsp");

                                dispatcher.forward(request, response);

                                return;   
                            }
                        }
                        else
                        {
                            request.setAttribute("name","Cart");
                           
                            request.setAttribute("CartList",cart);

                            RequestDispatcher dispatcher = request.getRequestDispatcher("Product.jsp");

                            dispatcher.forward(request, response);

                            return; 
                        }
                    }
                }
                else
                {
                    session.setAttribute("product_id",request.getParameter("product_id"));

                    boolean isAvailableDress = new ProductList((String)session.getAttribute("product_id")).isAvailableDressProduct(request.getParameter("product_id"), Integer.parseInt(request.getParameter("count")));
                   
                    System.out.println("Available dress...."+isAvailableDress+" ---Count...."+Integer.parseInt(request.getParameter("count"))+"  product_id...."+request.getParameter("product_id"));

                    boolean isAvailableAccessories =new ProductList((String)session.getAttribute("product_id")).isAvailableAccessoriesProduct(request.getParameter("product_id"), Integer.parseInt(request.getParameter("count")));
                   
                    boolean isAvailableFootWear =new ProductList((String)session.getAttribute("product_id")).isAvailableFootWearsProduct(request.getParameter("product_id"), Integer.parseInt(request.getParameter("count")));

                    if (isAvailableDress || isAvailableAccessories || isAvailableFootWear) 
                    {
                        RequestDispatcher dispatcher = request.getRequestDispatcher("address");

                        dispatcher.forward(request, response);

                        return;
                    }
                    else
                    {
                        RequestDispatcher dispatcher = request.getRequestDispatcher(request.getParameter("variety").toLowerCase());

                        dispatcher.forward(request, response);

                        return;   
                    }
                }
            }
        }
    }
}