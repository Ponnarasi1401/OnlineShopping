package Shopping;
import DataBase.Utilities; 
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.ServletException;
import java.io.*;
import java.util.*;
import jakarta.servlet.RequestDispatcher;

public class Cart_List extends HttpServlet {
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        System.out.println("CartList......");

        HttpSession session = request.getSession();

        PrintWriter out = response.getWriter();

        System.out.println("View... --->"+request.getParameter("view")+"   Path-------->"+request.getParameter("path"));

        if(session.getAttribute("userId")==null)
        {           
            RequestDispatcher dispatcher = request.getRequestDispatcher("LogIn.jsp");

            dispatcher.forward(request, response);

            return;
        }
        else
        {
            int user=(int)session.getAttribute("userId");
            
            String productId=(String)session.getAttribute("product_id");

            System.out.println(request.getParameter("view")+" product_id--->"+productId+"userId--->"+user+"----variety---"+request.getParameter("variety"));

            if("ViewCart".equalsIgnoreCase(request.getParameter("view")))
            {
                System.out.println("viewcart condition");

                session.setAttribute("product_id",request.getParameter("product_id"));

                request.setAttribute("name","Cart");

                request.setAttribute("CartList",new CartList(productId).CartList(user));

                RequestDispatcher dispatcher = request.getRequestDispatcher("Product.jsp");

                dispatcher.forward(request, response);

                return;
            }

            if("X Remove".equalsIgnoreCase(request.getParameter("cartRemove")))
            {
                // System.out.println("Value---> "+"Remove".equalsIgnoreCase((String)request.getAttribute("cartRemove"))+"---------->"+new CartList().removeProduct(productId,user));

                boolean isRemoved = new CartList().removeProduct(request.getParameter("productId"),user);
                   
                System.out.println("cart remove--->"+isRemoved+"   productId---->"+request.getParameter("productId"));

                if (isRemoved)
                {
                    System.out.println("You are Successfully removed your CartList...");

                    request.setAttribute("name","Cart");

                    request.setAttribute("CartList",new CartList().CartList(user));

                    RequestDispatcher dispatcher = request.getRequestDispatcher("Product.jsp");

                    dispatcher.forward(request, response);

                    return;      
                } 
                else 
                {
                    System.out.println("You didn't remove your CartList...Try again...!");

                    request.setAttribute("name","Cart");

                    request.setAttribute("CartList",new CartList().CartList(user));

                    RequestDispatcher dispatcher = request.getRequestDispatcher("Product.jsp");

                    dispatcher.forward(request, response);

                    return;
                }
            }

            if("/Product.jsp".equalsIgnoreCase(request.getParameter("path")))
            {

                if(new Product().isAddedToCart(productId,user))
                {
                    System.out.println("Added to cart.............."+request.getParameter("path"));

                    if("Wish List".equalsIgnoreCase(request.getParameter("view")))
                    {
                        RequestDispatcher dispatcher = request.getRequestDispatcher("Product.jsp");
                    
                        dispatcher.forward(request, response);

                        return;
                    }
                    else
                    {    
                    
                        RequestDispatcher dispatcher = request.getRequestDispatcher("productlist");
                    
                        dispatcher.forward(request, response);

                        return;
                    }
                   
                }
                else
                {
             
                    out.println("Product Mismatch...!");
             
                }
            }
        }
    }
}                   