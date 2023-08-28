package UserDetails;
import Shopping.*;
import DataBase.Utilities; 
import java.sql.ResultSet;
import java.io.*;
import java.util.*;

public class UserUtilities
{
	public int user;

	public UserUtilities(int user)
	{
		this.user=user;
	}

	public UserUtilities(){}

	public ArrayList<String> getDeliveryAddress(int currentUser)
	{
		ArrayList<String> deliveryAddress=new ArrayList<String>();

		ResultSet address=new Utilities().getAddressDetail(currentUser);
		
		try
		{
			while(address.next())
			{
				deliveryAddress.add(""+address.getInt("address_id"));
				deliveryAddress.add(address.getString("user_name"));
				deliveryAddress.add(address.getString("address"));
				deliveryAddress.add(address.getString("pincode"));
				deliveryAddress.add(address.getString("district"));
				deliveryAddress.add(address.getString("state"));
				deliveryAddress.add(address.getString("phone_no"));		
			}
		}
		catch(Exception e) 
		{
			System.out.println(e+"Exception...");
		}

		return deliveryAddress;
	}
	public ArrayList<String> getDeliveryAddress(int currentUser,int address_id)
	{
		ArrayList<String> deliveryAddress=new ArrayList<String>();

		ResultSet address=new Utilities().getAddressDetail(currentUser,address_id);
		
		try
		{
			while(address.next())
			{
				deliveryAddress.add(""+address.getInt("address_id"));
				deliveryAddress.add(address.getString("user_name"));
				deliveryAddress.add(address.getString("address"));
				deliveryAddress.add(address.getString("pincode"));
				deliveryAddress.add(address.getString("district"));
				deliveryAddress.add(address.getString("state"));
				deliveryAddress.add(address.getString("phone_no"));		
			}
		}
		catch(Exception e) 
		{
			System.out.println(e+"Exception...");
		}

		return deliveryAddress;
	}
	public void setDeliveryAddress(int currentUser,String phoneNo,String address, String pincode, String city,String state)
	{
	
		new Utilities().setDeliveryAddress(currentUser,phoneNo,address,pincode,city,state);
	
	}
	public boolean isCorrectUserAccount(String holderName,long cardNo,int cvvNo,String expireDate)
	{
	
		boolean isCorrectUser=false;

		isCorrectUser=new Utilities().isCorrectUserAccount(holderName,cardNo,cvvNo,expireDate);

		System.out.println(isCorrectUser+"----UserPage----");

		return isCorrectUser;
	
	}
	public int getNotificationCount()
	{
		int count=new Utilities().getNotificationCount();

		return count;
	}
	public ArrayList<String> getNotificationList()
	{
		ArrayList<String> notification=new ArrayList<String>();

		ResultSet notify=new Utilities().getNotificationList();

		try
		{
			while(notify.next())
			{
				notification.add(""+notify.getInt("total_stock"));
				notification.add(notify.getString("productname"));
				notification.add(notify.getString("productcolor"));
				notification.add(notify.getString("product_id"));
				notification.add(notify.getString("size"));
				notification.add(""+notify.getDouble("price"));
				notification.add(""+notify.getFloat("deliverycharge"));
				notification.add(notify.getString("varieties"));

			}
		}
		catch(Exception e) 
		{
			System.out.println(e+"Exception...");
		}

		return notification;
	}
	public boolean isProductAddedToWishList(String productId,int currentUser)
	{
		boolean isAdded=false;

		isAdded=new Utilities().isProductAddedToWishList(productId,currentUser);

		return isAdded;
	}
	public ArrayList<String> getProductFromWishList(int currentUser)
	{
		ArrayList<String> wishList=new ArrayList<String>();

		ResultSet list=new Utilities().getProductFromWishList(currentUser);
		
		try
		{
			while(list.next())
			{
			
				wishList.add(list.getString("productname"));
				wishList.add(list.getString("productcolor"));
				wishList.add(list.getString("material_type"));
				wishList.add(list.getString("product_id"));
				wishList.add(list.getString("size"));
				wishList.add(""+list.getDouble("price"));
				wishList.add(""+list.getFloat("deliverycharge"));
				wishList.add(""+list.getInt("total_stock"));
				wishList.add(list.getString("gender"));
				wishList.add(list.getString("varieties"));

				System.out.println("-----product_id------"+list.getString("product_id"));

			}
		}
		catch(Exception e) 
		{
			System.out.println(e+"Exception...");
		}

		return wishList;
	}
	public boolean removeProductFromWishList(String productId,int currentUser)
	{
		boolean isRemoved=false;

		isRemoved=new Utilities().removeProductFromWishList(productId,currentUser);

		return isRemoved;
	}
	public int getAddressId(int currentUser)
	{
		int address_id=0;

		ResultSet address=new Utilities().getAddressId();

		try
		{
			if(address.next())
			{
				address_id=address.getInt("max");
			}
		}
		catch(Exception e)
		{
			System.out.println(e+"Not valid User");
		}
		
		return address_id;
	}
}