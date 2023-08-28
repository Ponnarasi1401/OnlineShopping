package UserDetails;
import DataBase.Utilities; 
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.ServletException;
import java.io.*;
import java.util.UUID;
import jakarta.servlet.RequestDispatcher;

public class Users extends HttpServlet {
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
    	Utilities utility=new Utilities();
    	
    	PrintWriter out = response.getWriter();
    	
    	HttpSession session = request.getSession();
     	
     	String path=request.getParameter("path");

    	System.out.println("userserv...  "+path);

     	if("loginpage".equalsIgnoreCase(request.getParameter("action")))
     	{
	        String userMailId = request.getParameter("mailId");
	        
	        String password = request.getParameter("password");
	        
	        byte validUser=utility.signIn(userMailId,password);
	       
	        if(validUser==3) 
	        {  
	            session.setAttribute("userId", utility.getUserId(userMailId));
	        
	            String sessionId=UUID.randomUUID().toString();
	        
	            Cookie sessionCookie = new Cookie("sessionId", sessionId);
	            
	            response.addCookie(sessionCookie);

	            if("WishList".equalsIgnoreCase(request.getParameter("view")))
	            {

            		RequestDispatcher dispatcher = request.getRequestDispatcher("productlist");

	           		dispatcher.forward(request, response);

	           		return;

	           	}
	          	
	          	if("/HomePage.jsp".equalsIgnoreCase(path))
	          	{
		     		String cart=request.getParameter("view");

   				  	System.out.println("User -->"+cart);
	          	
	          		if("ViewCart".equalsIgnoreCase(cart))
	          		{
	          			RequestDispatcher dispatcher = request.getRequestDispatcher("cart");

            			dispatcher.forward(request, response);

            			return;
	          		}

	            	RequestDispatcher dispatcher = request.getRequestDispatcher("HomePage.jsp");

            		dispatcher.forward(request, response);

	            	return;
	            	
	            }
	            else
	            {
	            	System.out.println("UserServlet---------->"+path);

	            	if("BuyNow".equalsIgnoreCase(request.getParameter("view"))|| "MyOrders".equalsIgnoreCase(request.getParameter("view")))
	            	{
	            		RequestDispatcher dispatcher = request.getRequestDispatcher("buy");

	            		dispatcher.forward(request, response);

	            		return;
	            	}
	            	else
	            	{
		            	RequestDispatcher dispatcher = request.getRequestDispatcher("cart");

	            		dispatcher.forward(request, response);

	            		return;
            		}
	            }
	        }
	        else if(validUser==2)
	        {
	            session.setAttribute("userId", utility.getUserId(userMailId));
	        
	            String sessionId=UUID.randomUUID().toString();
	        
	            Cookie sessionCookie = new Cookie("sessionId", sessionId);
	            
	            response.addCookie(sessionCookie);
	     
	            RequestDispatcher dispatcher = request.getRequestDispatcher("admin");

	            dispatcher.forward(request, response);

	       		return;
	        } 
	        else 
	        {
	        	out.println("...You didn't SignUp...");

	            response.sendRedirect("LogIn.jsp");

	            return;
	        }
    	}
    	
    	if("LogOut".equalsIgnoreCase(request.getParameter("action")))
    	{
    		if (session != null) 
    		{
				session.invalidate();

            	System.out.println("log out...");

            	RequestDispatcher dispatcher = request.getRequestDispatcher("HomePage.jsp");

            	dispatcher.forward(request, response);

	           	return;
			}
    	}

    	if("signUp".equalsIgnoreCase(request.getParameter("action")))
    	{
    		boolean isValid=utility.signUp(request.getParameter("mailId"));
    		
    		if(isValid)
    		{
    			utility.insertSignUpDetails(request.getParameter("username"),request.getParameter("gender"),request.getParameter("mailId"),request.getParameter("phoneNo"),request.getParameter("password"));

    			RequestDispatcher dispatcher = request.getRequestDispatcher("HomePage.jsp");

            	dispatcher.forward(request, response);

	            return;
    		}
    		else
    		{	
    			response.sendRedirect("LogIn.jsp");

    			return;
    		}
    	}
    }
}


