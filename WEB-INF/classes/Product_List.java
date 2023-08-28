package Shopping;
import UserDetails.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.ServletException;
import java.io.*;
import java.util.*;
import jakarta.servlet.RequestDispatcher;

public class Product_List extends HttpServlet {
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

            PrintWriter out = response.getWriter();
                       
            HttpSession session = request.getSession();

            ProductList productList=new ProductList((String)session.getAttribute("product_id"));

            System.out.println("dresses---->1234");

            String path=request.getParameter("path");

            System.out.println(" --->path :"+path+"----variety----"+request.getParameter("variety")+"-----product_id------"+request.getParameter("product_id"));

            if("WishList".equalsIgnoreCase(request.getParameter("view")))
            {

                if(session.getAttribute("userId")==null)
                {
                    session.setAttribute("product_id",request.getParameter("product_id"));

                    RequestDispatcher dispatcher = request.getRequestDispatcher("LogIn.jsp");

                    dispatcher.forward(request, response);

                    return;
                }
                else
                {
                    if("/HomePage.jsp".equalsIgnoreCase(request.getParameter("path")) || "/Product_List.jsp".equalsIgnoreCase(request.getParameter("path")))
                    {

                        request.setAttribute("wishList",new UserUtilities((int)session.getAttribute("userId")).getProductFromWishList((int)session.getAttribute("userId")));
                    
                        RequestDispatcher dispatcher = request.getRequestDispatcher("Product.jsp");

                        dispatcher.forward(request, response);

                        return;
                    
                    }
                    else if("Add To Cart".equalsIgnoreCase(request.getParameter("cart")))
                    {
                        boolean isRemoved = new UserUtilities((int)session.getAttribute("userId")).removeProductFromWishList(request.getParameter("product_id"),(int)session.getAttribute("userId"));
                   
                        System.out.println("wish remove--->"+isRemoved+"   productId---->"+request.getParameter("product_id"));

                        if (isRemoved)
                        {
                            session.setAttribute("product_id",request.getParameter("product_id"));

                            System.out.println("You are Successfully removed your WishList...");

                            request.setAttribute("wishList",new UserUtilities((int)session.getAttribute("userId")).getProductFromWishList((int)session.getAttribute("userId")));                            

                            RequestDispatcher dispatcher = request.getRequestDispatcher("cart");

                            dispatcher.forward(request, response);

                            return;      
                        }
                        else
                        {
                            request.setAttribute("wishList",new UserUtilities((int)session.getAttribute("userId")).getProductFromWishList((int)session.getAttribute("userId")));

                            RequestDispatcher dispatcher = request.getRequestDispatcher("Product.jsp");

                            dispatcher.forward(request, response);

                            return;
                        }
                    }
                    else if("X Remove".equalsIgnoreCase(request.getParameter("wishRemove")))
                    {
                        boolean isRemoved = new UserUtilities((int)session.getAttribute("userId")).removeProductFromWishList(request.getParameter("product_id"),(int)session.getAttribute("userId"));
                   
                        System.out.println("wish remove--->"+isRemoved+"   productId---->"+request.getParameter("product_id"));

                        if (isRemoved)
                        {
                            System.out.println("You are Successfully removed your WishList...");

                            request.setAttribute("wishList",new UserUtilities((int)session.getAttribute("userId")).getProductFromWishList((int)session.getAttribute("userId")));

                            RequestDispatcher dispatcher = request.getRequestDispatcher("Product.jsp");

                            dispatcher.forward(request, response);

                            return;      
                        }
                        else
                        {
                            request.setAttribute("wishList",new UserUtilities((int)session.getAttribute("userId")).getProductFromWishList((int)session.getAttribute("userId")));

                            RequestDispatcher dispatcher = request.getRequestDispatcher("Product.jsp");

                            dispatcher.forward(request, response);

                            return;
                        }
                    }
                    else
                    {
                        boolean isAdded=new UserUtilities((int)session.getAttribute("userId")).isProductAddedToWishList(request.getParameter("product_id"),(int)session.getAttribute("userId"));

                        if(isAdded)
                        {
                            RequestDispatcher dispatcher = request.getRequestDispatcher("Product_List.jsp");

                            dispatcher.forward(request, response);

                            return;
                        }   
                        else
                        {
                            request.setAttribute("status","Not Added");

                            RequestDispatcher dispatcher = request.getRequestDispatcher("Product_List.jsp");

                            dispatcher.forward(request, response);

                            return;
                        }
                    }
                }

            }

            if("/Product.jsp".equalsIgnoreCase(request.getParameter("path")) || "/AdminPage.jsp".equalsIgnoreCase(request.getParameter("path")))
            {
                System.out.println("inside if...");

                if ("Dresses".equalsIgnoreCase(request.getParameter("variety"))) 
                {
                    if(((String)session.getAttribute("product_id")) !=null)
                    {
                
                        request.setAttribute("productList",productList.searchDressDetail((String)session.getAttribute("product_id")));    
                
                    }
                    else
                    {
                
                         request.setAttribute("productList",productList.searchDressDetail(request.getParameter("product_id")));
                
                    }
                }
                else if("Accessories".equalsIgnoreCase(request.getParameter("variety")))
                {
                    if(((String)session.getAttribute("product_id")) !=null)
                    {    
                
                        request.setAttribute("productList",productList.searchAccessoriesDetail((String)session.getAttribute("product_id")));
                
                    }
                    else
                    {
                
                        request.setAttribute("productList",productList.searchAccessoriesDetail(request.getParameter("product_id")));
                
                    }
                }
                else
                {
                    if(((String)session.getAttribute("product_id")) !=null)
                    {
                
                        request.setAttribute("productList",productList.searchFootWearsDetail((String)session.getAttribute("product_id")));    
                
                    }
                    else
                    {
                
                        request.setAttribute("productList",productList.searchFootWearsDetail(request.getParameter("product_id")));
                
                    }
                }
             
                System.out.println("inside if end...");

                request.setAttribute("addedToCart","Cart");
             
                RequestDispatcher dispatcher = request.getRequestDispatcher("Product.jsp");

                dispatcher.forward(request, response);

                return;
            }
            else
            {

                String product_id=request.getParameter("product_id");

                session.setAttribute("product_id",product_id);

                System.out.println("else check...");

                if(productList.isDressAvailable(request.getParameter("gender"),product_id))
                {   
                    System.out.println("else if method...");
                    
                    request.setAttribute("productList",productList.searchDressDetails(request.getParameter("gender"),product_id));

                    RequestDispatcher dispatcher = request.getRequestDispatcher("Product.jsp");

                    dispatcher.forward(request, response);

                    return ;
                } 
                else if(productList.isAccessoriesAvailable(request.getParameter("gender"),product_id))
                {   
                    System.out.println("else if method...");
                    
                    request.setAttribute("productList",productList.searchAccessoriesDetails(request.getParameter("gender"),product_id));

                    RequestDispatcher dispatcher = request.getRequestDispatcher("Product.jsp");

                    dispatcher.forward(request, response);

                    return ;
                }
                else if(productList.isFootWearsAvailable(request.getParameter("gender"),product_id))
                {   
                    System.out.println("else if method...");
                    
                    request.setAttribute("productList",productList.searchFootWearsDetails(request.getParameter("gender"),product_id));

                    RequestDispatcher dispatcher = request.getRequestDispatcher("Product.jsp");

                    dispatcher.forward(request, response);

                    return ;
                }
                else 
                {
                    out.println("Product Not Found");
                }
            }
    }
}