package Shopping;
import DataBase.Utilities; 
import java.sql.ResultSet;
import java.io.*;
import java.util.*;

public class Product
{
	public Product(){}

	public HashMap<Integer,String> shoppingVarieties() 
	{
		HashMap<Integer,String> shoppingList=new HashMap<Integer,String>();

		ResultSet varietyResult= new Utilities().shoppingVarieties(); 
		
		try
		{
			while(varietyResult.next())
			{
				shoppingList.put(varietyResult.getInt("variety_id"),varietyResult.getString("varieties"));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
			System.out.println(e+"Invalid data...@");
		}
		return shoppingList;
	}

	public HashMap<Integer,String> productType()
	{
		HashMap<Integer,String> productType=new HashMap<Integer,String>();

		ResultSet wearResult= new Utilities().productType();
		try
		{
			while(wearResult.next())
			{
				productType.put(wearResult.getInt("producttype_id"), wearResult.getString("producttype"));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();

			System.out.println("Invalid data...!");
		}
		return productType;
	}

	public ArrayList<String> productName(String gender,String userOpinion) 
	{
		ArrayList<String> productName=new ArrayList<String>();

		ResultSet product= new Utilities().productName(gender, userOpinion);

		try
		{
			while(product.next())
			{
				productName.add(product.getString("productname"));
				productName.add(""+product.getDouble("price"));
				productName.add(product.getString("product_id"));
				productName.add(""+product.getInt("total_stock"));
				productName.add(""+product.getFloat("deliverycharge"));

				// System.out.println("------Product page------"+product.getString("productname")+"---"+product.getDouble("price")+"---"+product.getString("product_id")+"---"+product.getInt("total_stock")+"---"+product.getFloat("deliverycharge"));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();

			System.out.println("Invalid data...");
		}

		return productName;
	}
	public boolean isAddedToCart(String productId, int currentUser)
	{
		boolean isAdd=false;

		if(new Utilities().isAddedToCart(productId,currentUser))
		{
			isAdd=true;
		}
		else
		{
			System.out.println("Invalid Product");
		}	
		
		return isAdd;
	}
}