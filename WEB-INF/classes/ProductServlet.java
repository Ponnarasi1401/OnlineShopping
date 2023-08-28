package Shopping;
import DataBase.Utilities; 
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.ServletException;
import java.io.*;
import java.util.*;

public class ProductServlet extends HttpServlet {
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        PrintWriter out = response.getWriter();
        
        if (request.getParameter("action").equalsIgnoreCase("Search")) 
        {
            String product=request.getParameter("search");

            response.sendRedirect("HomePage.jsp");                
        }
        
    }
}