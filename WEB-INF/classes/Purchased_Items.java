package Shopping;
// import PaymentDetails.*;
import DataBase.Utilities; 
import java.sql.ResultSet;
import java.io.*;
import java.util.*;

public class Purchased_Items
{
	public String productId;
	
	public Purchased_Items(){}

	public Purchased_Items(String productId)
	{
		this.productId=productId;
	}

	public boolean isDressAddedToPurchasedList(String productId,int currentUser,int address_id)
	{
		boolean isAdd=false;

		isAdd=new Utilities().isDressAddedToPurchasedList(productId,currentUser,address_id);

		return isAdd;
	}
	public boolean isAccessoriesAddedToPurchasedList(String productId,int currentUser,int address_id)
	{
		boolean isAdd=false;

		isAdd=new Utilities().isAccessoriesAddedToPurchasedList(productId,currentUser,address_id);

		return isAdd;
	}
	public boolean isFootWearAddedToPurchasedList(String productId,int currentUser,int address_id)
	{
		boolean isAdd=false;

		isAdd=new Utilities().isFootWearsAddedToPurchasedList(productId,currentUser,address_id);

		return isAdd;
	}
	public ArrayList<String> getPurchasedList(int currentUser)
	{
		ArrayList<String> list=new ArrayList<String>();

		ResultSet product=new Utilities().getPurchasedList(currentUser);
				
		try
		{								
			while(product.next()) 
			{
				list.add(product.getString("productname"));
				list.add(product.getString("productcolor"));
				list.add(product.getString("size"));
				list.add(product.getString("product_id"));
				list.add(""+product.getDouble("price"));
				list.add(""+product.getFloat("deliverycharge"));
				list.add(""+product.getInt("total_stock"));
				list.add(product.getString("gender"));
				list.add(product.getString("status"));
				list.add(product.getString("varieties"));
				list.add(""+product.getDate("purchased_date"));
				list.add(""+product.getDate("delivery_date"));
				// list.add(""+product.getInt("count"));
			}
		}
		catch(Exception e)
		{
			System.out.println(e+"Not Available");
		}

		return list;
	}
	public boolean isProductCancelled(String productId,int currentUser)
	{
		boolean isCancel=false;

		isCancel=new Utilities().isProductCancelled(productId,currentUser);

		return isCancel;	
	}  
}