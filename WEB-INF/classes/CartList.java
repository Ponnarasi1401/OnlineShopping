package Shopping;
import DataBase.Utilities; 
import java.sql.ResultSet;
import java.io.*;
import java.util.*;

public class CartList
{
	public String productId;

	public CartList(){}

	public CartList(String productId)
	{
		this.productId=productId;
	}

	public ArrayList<String> CartList(int currentUser)
	{
		ResultSet cart=new Utilities().CartList(currentUser);
		
		ArrayList<String> cartList=new ArrayList<String>();

		try
		{
			while(cart.next())
			{
		
				cartList.add(cart.getString("productname"));
				cartList.add(cart.getString("productcolor"));
				cartList.add(cart.getString("material_type"));
				cartList.add(cart.getString("product_id"));
				cartList.add(cart.getString("size"));
				cartList.add(""+cart.getDouble("price"));
				cartList.add(""+cart.getFloat("deliverycharge"));
				cartList.add(""+cart.getInt("total_stock"));
				cartList.add(""+cart.getInt("count"));
				cartList.add(cart.getString("gender"));
				cartList.add(cart.getString("varieties"));
		
			}
		
		}
		catch(Exception e)
		{
			System.out.println("Invalid Product");
		}
		return cartList;
	}

	public boolean removeProduct(String productId,int userId)
	{
		boolean removedProduct=false;

		removedProduct=new Utilities().removeProduct(productId,userId);

		return removedProduct;
	}
	public boolean isPurchased(double amount,long cardNo)
	{
		boolean isPurchase=false;
		
		 isPurchase=new Utilities().isPurchased(amount,cardNo); 
			
		return isPurchase;
	}
}