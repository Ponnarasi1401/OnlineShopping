package UserDetails;
import Shopping.*;
import DataBase.Utilities; 
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.ServletException;
import java.io.*;
import java.util.UUID;
import jakarta.servlet.RequestDispatcher;

public class Admin extends HttpServlet {
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

        HttpSession session = request.getSession();

        PrintWriter out = response.getWriter();

        if(session.getAttribute("userId")==null)
        {
            RequestDispatcher dispatcher = request.getRequestDispatcher("LogIn.jsp");

            dispatcher.forward(request, response);

            return;
        }
        else
        {
            int count=new UserUtilities((int)session.getAttribute("userId")).getNotificationCount();

            session.setAttribute("count",count);

            System.out.println("-----Admin page-------"+count+"---------"+request.getParameter("view"));

            if("Change Amount".equalsIgnoreCase(request.getParameter("changeAmount")))
            {

                boolean isAdded=new ProductList(request.getParameter("product_id")).isAmountAddedToProductList(request.getParameter("product_id"),Double.parseDouble(request.getParameter("amount")),Float.parseFloat(request.getParameter("deliveryCharge")));

                System.out.println("-------Admin page.....-------"+isAdded +"------product_id--------"+request.getParameter("product_id")+"-------amount------"+request.getParameter("amount"));

                if(isAdded)
                {
                    request.setAttribute("action","Change Amount");

                    RequestDispatcher dispatcher = request.getRequestDispatcher("AdminPage.jsp");

                    dispatcher.forward(request, response);

                    return;
                }
                else
                {   

                    RequestDispatcher dispatcher = request.getRequestDispatcher("AdminPage.jsp");

                    dispatcher.forward(request, response);

                    return;
                }       

            }

            if("Add".equalsIgnoreCase(request.getParameter("adminAction")) || "Add".equalsIgnoreCase(request.getParameter("addQuantity")))
            {

                boolean isAdded=new ProductList(request.getParameter("product_id")).isCountAddedToProductList(request.getParameter("product_id"),Integer.parseInt(request.getParameter("quantity")));

                System.out.println("-------Admin page.....-------"+isAdded +"------product_id--------"+request.getParameter("product_id")+"-------count------"+request.getParameter("quantity"));

                if(isAdded)
                {
                    if("Add".equalsIgnoreCase(request.getParameter("adminAction")))
                    {
                    
                        request.setAttribute("notification_list",new UserUtilities((int)session.getAttribute("userId")).getNotificationList());

                    }

                    RequestDispatcher dispatcher = request.getRequestDispatcher("AdminPage.jsp");

                    dispatcher.forward(request, response);

                    return;
                }
                else
                {
                    if("Add".equalsIgnoreCase(request.getParameter("adminAction")))
                    {
                     
                        request.setAttribute("action","+ Add Product");
                    
                    }
                    else
                    {
                    
                         request.setAttribute("action","Add Stock");   
                    
                    }

                    RequestDispatcher dispatcher = request.getRequestDispatcher("AdminPage.jsp");

                    dispatcher.forward(request, response);

                    return;
                }

            }
            if("Add Product".equalsIgnoreCase(request.getParameter("action")))
            {
                System.out.println("-----------Add Product----------"+request.getParameter("variety")+request.getParameter("gender")+request.getParameter("product_id")+request.getParameter("product_name")+request.getParameter("product_color")+request.getParameter("material_type")+request.getParameter("product_type")+request.getParameter("size")+Double.parseDouble(request.getParameter("price"))+Float.parseFloat(request.getParameter("delivery_charge"))+Integer.parseInt(request.getParameter("total_stock")));

                boolean isAdded=new ProductList().isProductAdded(request.getParameter("variety"),request.getParameter("gender"),request.getParameter("product_id"),request.getParameter("product_name"),request.getParameter("product_color"),request.getParameter("material_type"),request.getParameter("product_type"),request.getParameter("size"),Double.parseDouble(request.getParameter("price")),Float.parseFloat(request.getParameter("delivery_charge")),Integer.parseInt(request.getParameter("total_stock")));

                if(isAdded)
                {

                    RequestDispatcher dispatcher = request.getRequestDispatcher("AdminPage.jsp");

                    dispatcher.forward(request, response);

                    return;
                }
                else
                {
                    request.setAttribute("action","NewProduct");

                    RequestDispatcher dispatcher = request.getRequestDispatcher("AdminPage.jsp");

                    dispatcher.forward(request, response);

                    return;
                }
            }
            if("Notification".equalsIgnoreCase(request.getParameter("view")))
            {
                request.setAttribute("notification_list",new UserUtilities((int)session.getAttribute("userId")).getNotificationList());

                RequestDispatcher dispatcher = request.getRequestDispatcher("AdminPage.jsp");

                dispatcher.forward(request, response);

                return;
            }
            else
            {
                RequestDispatcher dispatcher = request.getRequestDispatcher("AdminPage.jsp");

                dispatcher.forward(request, response);

                return;   
            }

        }

    }

}