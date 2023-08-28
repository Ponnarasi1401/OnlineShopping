package Shopping;
import DataBase.Utilities; 
import java.sql.ResultSet;
import java.io.*;
import java.util.*;

public class ProductList
{
	public String product_id;

	public ProductList(String product_id)
	{
		this.product_id=product_id;
	}
	
	public ProductList(){}

	public boolean isDressAvailable(String gender,String product_id)
	{
		boolean search=false;

		System.out.println("else  check method");
		
		// System.out.println(gender+"----------"+productName.split("--->")[0]);

		ResultSet dress=new Utilities().isDressList(gender,product_id);
		
		try
		{
			if(dress.next())
			{
				search=true;
			}
		}
		catch(Exception e)
		{
			System.out.println("Not Available...");
		}
		return search;
	}
	public ArrayList<String> searchDressDetails(String gender,String product_id)
	{
		ArrayList<String> d=new ArrayList<String>();

		ResultSet dress=new Utilities().isDressList(gender,product_id);
		try
		{
			while(dress.next())
			{
				d.add(dress.getString("productname"));
				d.add(dress.getString("productcolor"));
				d.add(dress.getString("size"));
				d.add(""+dress.getDouble("price"));
				d.add(""+dress.getFloat("deliverycharge"));
				d.add(""+dress.getInt("total_stock"));
				d.add(dress.getString("product_id"));
				d.add(dress.getString("gender"));
			}
		}
		catch(Exception e)
		{
			System.out.println("Not Available...");
		}
		return d;
	}

	public ArrayList<String> searchDressDetail(String productId)
	{
		ArrayList<String> d=new ArrayList<String>();

		ResultSet dress=new Utilities().dressDetail(productId);

		System.out.println("if method");
		
		try
		{
			while(dress.next())
			{
				d.add(dress.getString("productname"));
				d.add(dress.getString("productcolor"));
				d.add(dress.getString("size"));
				d.add(""+dress.getDouble("price"));
				d.add(""+dress.getFloat("deliverycharge"));
				d.add(""+dress.getInt("total_stock"));
				d.add(dress.getString("product_id"));
				d.add(dress.getString("gender"));
			}
		}
		catch(Exception e)
		{
			System.out.println("Not Available...");
		}
		
		System.out.println("if method end");
		
		return d;
	}
	public boolean isAvailableDressProduct(String productId,int count)
	{
		boolean isAvailable=false;

		isAvailable=new  Utilities().isAvailableDressProduct(productId,count);

		return isAvailable;
	}
	public boolean isAccessoriesAvailable(String gender,String product_id)
	{
		boolean search=false;

		System.out.println("else  check method");
		
		// System.out.println(gender+"----------"+productName.split("--->")[0]);

		ResultSet dress=new Utilities().isAccessoriesList(gender,product_id);
		
		try
		{
			if(dress.next())
			{
				search=true;
			}
		}
		catch(Exception e)
		{
			System.out.println("Not Available...");
		}
		return search;
	}
	public ArrayList<String> searchAccessoriesDetails(String gender,String product_id)
	{
		ArrayList<String> d=new ArrayList<String>();

		ResultSet accessories=new Utilities().isAccessoriesList(gender,product_id);
		try
		{
			while(accessories.next())
			{
				d.add(accessories.getString("productname"));
				d.add(accessories.getString("productcolor"));
				d.add(accessories.getString("size"));
				d.add(""+accessories.getDouble("price"));
				d.add(""+accessories.getFloat("deliverycharge"));
				d.add(""+accessories.getInt("total_stock"));
				d.add(accessories.getString("product_id"));
				d.add(accessories.getString("gender"));
			}
		}
		catch(Exception e)
		{
			System.out.println("Not Available...");
		}
		return d;
	}

	public ArrayList<String> searchAccessoriesDetail(String productId)
	{
		ArrayList<String> d=new ArrayList<String>();

		ResultSet accessories=new Utilities().accessoriesDetail(productId);

		System.out.println("if method");
		
		try
		{
			while(accessories.next())
			{
				d.add(accessories.getString("productname"));
				d.add(accessories.getString("productcolor"));
				d.add(accessories.getString("size"));
				d.add(""+accessories.getDouble("price"));
				d.add(""+accessories.getFloat("deliverycharge"));
				d.add(""+accessories.getInt("total_stock"));
				d.add(accessories.getString("product_id"));
				d.add(accessories.getString("gender"));
			}
		}
		catch(Exception e)
		{
			System.out.println("Not Available...");
		}
		
		System.out.println("if method end");
		
		return d;
	}
	public boolean isAvailableAccessoriesProduct(String productId,int count)
	{
		boolean isAvailable=false;

		isAvailable=new  Utilities().isAvailableAccessoriesProduct(productId,count);

		return isAvailable;
	}
	public boolean isFootWearsAvailable(String gender,String product_id)
	{
		boolean search=false;

		System.out.println("else  check method");
		
		// System.out.println(gender+"----------"+productName.split("--->")[0]);

		ResultSet footWear=new Utilities().isFootWearsList(gender,product_id);
		
		try
		{
			if(footWear.next())
			{
				search=true;
			}
		}
		catch(Exception e)
		{
			System.out.println("Not Available...");
		}

		return search;
	}
	public ArrayList<String> searchFootWearsDetails(String gender,String product_id)
	{
		ArrayList<String> d=new ArrayList<String>();

		ResultSet footwears=new Utilities().isFootWearsList(gender,product_id);
		try
		{
			while(footwears.next())
			{
				d.add(footwears.getString("productname"));
				d.add(footwears.getString("productcolor"));
				d.add(footwears.getString("size"));
				d.add(""+footwears.getDouble("price"));
				d.add(""+footwears.getFloat("deliverycharge"));
				d.add(""+footwears.getInt("total_stock"));
				d.add(footwears.getString("product_id"));
				d.add(footwears.getString("gender"));
			}
		}
		catch(Exception e)
		{
			System.out.println("Not Available...");
		}

		return d;
	}

	public ArrayList<String> searchFootWearsDetail(String productId)
	{
		ArrayList<String> d=new ArrayList<String>();

		ResultSet footwears=new Utilities().footwearsDetail(productId);

		System.out.println("if method");
		
		try
		{
			while(footwears.next())
			{
				d.add(footwears.getString("productname"));
				d.add(footwears.getString("productcolor"));
				d.add(footwears.getString("size"));
				d.add(""+footwears.getDouble("price"));
				d.add(""+footwears.getFloat("deliverycharge"));
				d.add(""+footwears.getInt("total_stock"));
				d.add(footwears.getString("product_id"));
				d.add(footwears.getString("gender"));
			}
		}
		catch(Exception e)
		{
			System.out.println("Not Available...");
		}
		
		System.out.println("if method end");
		
		return d;
	}
	public boolean isAvailableFootWearsProduct(String productId,int count)
	{
		boolean isAvailable=false;

		isAvailable=new  Utilities().isAvailableFootWearsProduct(productId,count);

		return isAvailable;
	}
	public boolean isCountAddedToProductList(String productId,int count)
	{
		boolean isAdded=false;

		isAdded=new  Utilities().isCountAddedToProductList(productId,count);

		return isAdded;
	}
	public boolean isAmountAddedToProductList(String productId,double amount,float deliveryCharge)
	{
		boolean isAdded=false;

		isAdded=new  Utilities().isAmountAddedToProductList(productId,amount,deliveryCharge);

		return isAdded;
	}
	public boolean isProductAdded(String variety,String gender,String productId,String productName,String productColor,String materialType,String productType,String size,double price,float deliveryCharge,int totalStock)
	{
		System.out.println("------ProductList.java------"+variety+gender+productId+productName+productColor+materialType+productType+size+price+deliveryCharge+totalStock);

		boolean isAdded=false;

		isAdded=new  Utilities().isProductAdded(variety,gender,productId,productName,productColor,materialType,productType,size,price,deliveryCharge,totalStock);

		return isAdded;
	}
}