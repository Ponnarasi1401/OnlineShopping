package DataBase;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.Date;
import java.util.Calendar;
import java.time.LocalDate;

public class Utilities
{
	public Utilities(){}

	public boolean signUp(String mailId)
	{
		PreparedStatement stmt=null;
		ResultSet correctUser=null;
		boolean user = false;
		try {
			DbConnection connect=DbConnection.getInstance();
       		Connection c=connect.getConnection();

			String getUser="SELECT mail_id FROM users WHERE mail_id =?";
			stmt=c.prepareStatement(getUser);
			stmt.setString(1,mailId.toLowerCase());
			correctUser=stmt.executeQuery();
			
			if (!correctUser.next()) {
				user=true;
			}
		}
		catch(Exception e) 
		{
			e.printStackTrace();
			System.out.println("Check signUp details");
		}
		finally
		{
			if(correctUser!=null)
			{
				try
				{
					correctUser.close();
				}
				catch(SQLException e)
				{
					System.out.println("Error message :"+e.getMessage());
				}
			}
			if(stmt!=null)
			{
				try
				{
					stmt.close();
				}
				catch(SQLException e)
				{
					System.out.println("Error message :"+e.getMessage());
				}
			}
		}
		return user;
	}
	public void insertSignUpDetails(String userName,String gender,String mailId,String phoneNo,String password)
	{
		PreparedStatement statement=null;
		ResultSet genderResult=null;
		try
		{
			DbConnection connect=DbConnection.getInstance();
			Connection c=connect.getConnection();
			String selectGender="SELECT gender_id FROM genderlist WHERE gender=?";
			statement= c.prepareStatement(selectGender);
			statement.setString(1, gender.toLowerCase());
			genderResult=statement.executeQuery();
								
			if(genderResult.next())
			{
				String insertUser="INSERT INTO users(user_name,gender_id,mail_id,password,phone_no) values(?,?,?,?,?)";
				statement= c.prepareStatement(insertUser);
				statement.setString(1, userName.toLowerCase());
				statement.setInt(2, genderResult.getInt("gender_id"));
				statement.setString(3, mailId);
				statement.setString(5, phoneNo);
				statement.setString(4, password.toLowerCase());
				statement.executeUpdate();
			}
			
		}
		catch(Exception e)
		{
			System.out.println("Something went wrong...!");
		}
		finally
		{
			if(genderResult!=null)
			{
				try
				{
					genderResult.close();
				}
				catch(SQLException e)
				{
					System.out.println("Error message :"+e.getMessage());
				}
			}
			if(statement!=null)
			{
				try
				{
					statement.close();
				}
				catch(SQLException e)
				{
					System.out.println("Error message :"+e.getMessage());
				}
			}
		}
	}
	
	public int getUserId(String userMailId) 
	{
		int currentUser=0;
		ResultSet userResult=null;
		PreparedStatement stmt=null;

		try 
		{
			DbConnection connect=DbConnection.getInstance();
			Connection c=connect.getConnection();
			String selectUser="SELECT user_id FROM users WHERE mail_id=?";
			stmt= c.prepareStatement(selectUser);
			stmt.setString(1, userMailId.toLowerCase());
			userResult=stmt.executeQuery();
		
			if(userResult.next())
			{
				currentUser = userResult.getInt("user_id");
			}
		}
		catch(Exception e) 
		{
			System.out.println("User Id Mismatch...");
		}
		finally
		{
			if(userResult!=null)
			{
				try
				{
					userResult.close();
				}
				catch(SQLException e)
				{
					System.out.println("Error message :"+e.getMessage());
				}
			}
			if(stmt!=null)
			{
				try
				{
					stmt.close();
				}
				catch(SQLException e)
				{
					System.out.println("Error message :"+e.getMessage());
				}
			}
		}
		return currentUser;	
	}
	public byte signIn(String userMailId, String userPassword) 
	{
		byte b = 0;
		ResultSet result=null,user=null;
		PreparedStatement stmt=null,state=null;
		try 
		{
			DbConnection connect=DbConnection.getInstance();
			Connection c=connect.getConnection();

			String selectUser="SELECT mail_id,password FROM users WHERE mail_id =? AND LOWER(password) =?";
			stmt=c.prepareStatement(selectUser);
			stmt.setString(1, userMailId.toLowerCase());
			stmt.setString(2, userPassword.toLowerCase());
			user=stmt.executeQuery();

			if(user.next())
			{
				String joinTable="SELECT admin.user_id FROM admin LEFT JOIN users ON users.mail_id =? AND LOWER(users.password)=? WHERE users.user_id=admin.user_id";
				state=c.prepareStatement(joinTable);
				state.setString(1, user.getString("mail_id"));
				state.setString(2, user.getString("password").toLowerCase());
				result=state.executeQuery();
				
				if(result.next()){ b=2;}
				else{ b=3;}
				
			}
			else{ b=1;}
		}
		catch(Exception e) 
		{
			e.printStackTrace();
			System.out.println("Check valid User...!");
		}
		finally
		{
			if(user!=null)
			{
				try
				{
					user.close();
				}
				catch(SQLException e)
				{
					System.out.println("Error message :"+e.getMessage());
				}
			}
			if(stmt!=null)
			{
				try
				{
					stmt.close();
				}
				catch(SQLException e)
				{
					System.out.println("Error message :"+e.getMessage());
				}
			}
			if(result!=null)
			{
				try
				{
					result.close();
				}
				catch(SQLException e)
				{
					System.out.println("Error message :"+e.getMessage());
				}
			}
			if(state!=null)
			{
				try
				{
					state.close();
				}
				catch(SQLException e)
				{
					System.out.println("Error message :"+e.getMessage());
				}
			}
		}
		return b;
	}
	public HashMap<Integer,String> genderList()
	{
		HashMap<Integer,String> genderList=new HashMap<Integer,String>();

		PreparedStatement stmt=null;
		ResultSet genderResult=null;
		try 
		{
			DbConnection connect=DbConnection.getInstance();
			Connection c=connect.getConnection();

			String selectGender="SELECT * FROM genderlist WHERE gender<>'Female' AND gender<>'Male' AND gender<>'Others'";
			stmt=c.prepareStatement(selectGender);
			genderResult=stmt.executeQuery();
			
			while(genderResult.next())
			{
				genderList.put(genderResult.getInt("gender_id"), genderResult.getString("gender"));
			}
		}catch(Exception e) {
			System.out.println("Invalid gender...");
		}
		finally
		{
			if(genderResult!=null)
			{
				try
				{
					genderResult.close();
				}
				catch(SQLException e)
				{
					System.out.println("Error message :"+e.getMessage());
				}
			}
			if(stmt!=null)
			{
				try
				{
					stmt.close();
				}
				catch(SQLException e)
				{
					System.out.println("Error message :"+e.getMessage());
				}
			}
		}
		return genderList;
	}	
	public ResultSet shoppingVarieties() 
	{
		PreparedStatement stmt=null;
		ResultSet varietyResult=null;
		try
		{	
			DbConnection connect=DbConnection.getInstance();
			Connection c=connect.getConnection();	

			String selectVariety="SELECT * FROM shopping_varieties";
			stmt=c.prepareStatement(selectVariety);
			varietyResult=stmt.executeQuery();
		}
		catch(Exception e)
		{
			System.out.println("Invalid Shopping Varieties...");
		}
		// finally
		// {
		// 	if(stmt!=null)
		// 	{
		// 		try
		// 		{
		// 			stmt.close();
		// 		}
		// 		catch(SQLException e)
		// 		{
		// 			System.out.println("Error message :"+e.getMessage());
		// 		}
		// 	}
		// }
		return varietyResult;
	}
	
	public ResultSet productType()
	{
	
		PreparedStatement stmt=null;
	
		ResultSet wearResult=null;
	
		try
		{
	
			DbConnection connect=DbConnection.getInstance();
			Connection c=connect.getConnection();

			String selectWear="SELECT * FROM product_type WHERE variety_id=1";
			stmt=c.prepareStatement(selectWear);
			wearResult=stmt.executeQuery();
			
		}
		catch(Exception e) 
		{
		
			System.out.println("Invalid Product Type...");
		
		}
		// finally
		// {
		// 	if(stmt!=null)
		// 	{
		// 		try
		// 		{
		// 			stmt.close();
		// 		}
		// 		catch(SQLException e)
		// 		{
		// 			System.out.println("Error message :"+e.getMessage());
		// 		}
		// 	}
		// }
	
		return wearResult;
	
	}

	public ResultSet productName(String gender,String userOpinion) 
	{
		PreparedStatement stmt=null;
		
		ResultSet product=null;
		
		try
		{
	
			DbConnection connect=DbConnection.getInstance();
			Connection c=connect.getConnection();
			String getproductName=null;

			if("Dresses".equalsIgnoreCase(userOpinion))
			{
				getproductName="SELECT product_name.productname,dresses.price,dresses.product_id,dresses.deliverycharge,dresses.total_stock FROM dresses LEFT JOIN product_name ON product_name.productname_id=dresses.productname_id LEFT JOIN genderlist ON genderlist.gender_id=dresses.gender_id LEFT JOIN shopping_varieties ON shopping_varieties.variety_id=product_name.variety_id WHERE LOWER(genderlist.gender)=? AND LOWER(shopping_varieties.varieties)='dresses';";
				
				stmt=c.prepareStatement(getproductName);
				stmt.setString(1, gender.toLowerCase());
			
				product=stmt.executeQuery();
			}
			if("Accessories".equalsIgnoreCase(userOpinion))
			{
				getproductName="SELECT product_name.productname,accessories.price,accessories.product_id,accessories.deliverycharge,accessories.total_stock FROM accessories LEFT JOIN product_name ON product_name.productname_id=accessories.productname_id LEFT JOIN genderlist ON genderlist.gender_id=accessories.gender_id LEFT JOIN shopping_varieties ON shopping_varieties.variety_id=product_name.variety_id WHERE LOWER(genderlist.gender)=? AND LOWER(shopping_varieties.varieties)='accessories';";
			
				stmt=c.prepareStatement(getproductName);
				stmt.setString(1, gender.toLowerCase());
			
				product=stmt.executeQuery();
			}
			if("FootWears".equalsIgnoreCase(userOpinion))
			{
				getproductName="SELECT product_name.productname,footwears.price,footwears.product_id,footwears.deliverycharge,footwears.total_stock FROM footwears LEFT JOIN product_name ON product_name.productname_id=footwears.productname_id LEFT JOIN genderlist ON genderlist.gender_id=footwears.gender_id LEFT JOIN shopping_varieties ON shopping_varieties.variety_id=product_name.variety_id WHERE LOWER(genderlist.gender)=? AND LOWER(shopping_varieties.varieties)='footwears';";
			
				stmt=c.prepareStatement(getproductName);
				stmt.setString(1, gender.toLowerCase());
			
				product=stmt.executeQuery();
			}

		}
		catch(Exception e)
		{
	
			e.printStackTrace();
			System.out.println(e+"Invalid ProductName...");
	
		}
		// finally
		// {
		// 	if(stmt!=null)
		// 	{
		// 		try
		// 		{
		// 			stmt.close();
		// 		}
		// 		catch(SQLException e)
		// 		{
		// 			System.out.println("Error message :"+e.getMessage());
		// 		}
		// 	}
		// }
	
		return product;
	
	}
	public ResultSet isDressList(String gender,String product_id)
	{
	
		ResultSet dress=null;
	
		PreparedStatement stmt=null;

		System.out.println(gender+" --->  ");

		try
		{
	
			DbConnection connect=DbConnection.getInstance();
			Connection c=connect.getConnection();

			String getDress="SELECT * FROM dresses LEFT JOIN genderlist ON genderlist.gender_id=dresses.gender_id LEFT JOIN product_name ON product_name.productname_id=dresses.productname_id LEFT JOIN product_type ON product_type.producttype_id=dresses.producttype_id LEFT JOIN product_color ON product_color.productcolor_id=dresses.productcolor_id LEFT JOIN size ON size.size_id=dresses.size_id WHERE LOWER(genderlist.gender)=? AND LOWER(dresses.product_id)=?";
			stmt=c.prepareStatement(getDress);
			stmt.setString(1, gender.toLowerCase());
			stmt.setString(2,product_id.toLowerCase());
			dress=stmt.executeQuery();
	
		}
		catch(Exception e) 
		{
			System.out.println("No Stock Available");
		}
		// finally
		// {
		// 	if(stmt!=null)
		// 	{
		// 		try
		// 		{
		// 			stmt.close();
		// 		}
		// 		catch(SQLException e)
		// 		{
		// 			System.out.println("Error message :"+e.getMessage());
		// 		}
		// 	}
		// }
		return dress;
	}
	public ResultSet isAccessoriesList(String gender,String product_id)
	{
		ResultSet accessories=null;

		PreparedStatement stmt=null;

		System.out.println(gender+" --->  ");

		try
		{
			DbConnection connect=DbConnection.getInstance();
			Connection c=connect.getConnection();

			String getAccessories="SELECT * FROM accessories LEFT JOIN genderlist ON genderlist.gender_id=accessories.gender_id LEFT JOIN product_name ON product_name.productname_id=accessories.productname_id LEFT JOIN product_type ON product_type.producttype_id=accessories.producttype_id LEFT JOIN product_color ON product_color.productcolor_id=accessories.productcolor_id LEFT JOIN size ON size.size_id=accessories.size_id WHERE LOWER(genderlist.gender)=? AND LOWER(accessories.product_id)=?";
			stmt=c.prepareStatement(getAccessories);
			stmt.setString(1, gender.toLowerCase());
			stmt.setString(2,product_id.toLowerCase());
			accessories=stmt.executeQuery();
		}
		catch(Exception e) 
		{
			System.out.println("No Stock Available");
		}
		// finally
		// {
		// 	if(stmt!=null)
		// 	{
		// 		try
		// 		{
		// 			stmt.close();
		// 		}
		// 		catch(SQLException e)
		// 		{
		// 			System.out.println("Error message :"+e.getMessage());
		// 		}
		// 	}
		// }
		return accessories;
	}
	public ResultSet isFootWearsList(String gender,String product_id)
	{
		ResultSet footwears=null;

		PreparedStatement stmt=null;

		System.out.println(gender+" --->  ");

		try
		{
			
			DbConnection connect=DbConnection.getInstance();
			Connection c=connect.getConnection();

			String getFootwears="SELECT * FROM footwears LEFT JOIN genderlist ON genderlist.gender_id=footwears.gender_id LEFT JOIN product_name ON product_name.productname_id=footwears.productname_id LEFT JOIN product_color ON product_color.productcolor_id=footwears.productcolor_id LEFT JOIN size ON size.size_id=footwears.size_id WHERE LOWER(genderlist.gender)=? AND LOWER(footwears.product_id)=?";
			stmt=c.prepareStatement(getFootwears);
			stmt.setString(1, gender.toLowerCase());
			stmt.setString(2,product_id.toLowerCase());
			footwears=stmt.executeQuery();
		
		}
		catch(Exception e) 
		{
		
			System.out.println("No Stock Available");
		
		}
		// finally
		// {
		// 	if(stmt!=null)
		// 	{
		// 		try
		// 		{
		// 			stmt.close();
		// 		}
		// 		catch(SQLException e)
		// 		{
		// 			System.out.println("Error message :"+e.getMessage());
		// 		}
		// 	}
		// }
		return footwears;
	}
	
	public boolean isAddedToCart(String productId, int currentUser)
	{
		
		boolean isAdd = false;
		
		PreparedStatement stmt=null;

		try 
		{
			
			DbConnection connect=DbConnection.getInstance();
			Connection c=connect.getConnection();

			System.out.println("Added to cart.......Utilities page...."+isAdd);

			String insertCart="INSERT INTO Cart_List(user_id,product_id,count) values(?,?,?)";
			stmt=c.prepareStatement(insertCart);
			stmt.setInt(1, currentUser);
			stmt.setString(2, productId);
			stmt.setInt(3, 1);
			stmt.executeUpdate();
	
			isAdd = true;
		
			System.out.println("Added to cart.......Utilities page..."+isAdd);
		
		}
		catch(Exception e) 
		{
		
			try 
			{
		
				DbConnection connect=DbConnection.getInstance();
				Connection c=connect.getConnection();

				String selectCart="SELECT * FROM Cart_List WHERE product_id=?";
				stmt=c.prepareStatement(selectCart);
				stmt.setString(1, productId.toLowerCase());
				ResultSet cart=stmt.executeQuery();
						
				if(cart.next())
				{
		
					String updateCart="UPDATE Cart_List SET count=? WHERE product_id=?";
					stmt=c.prepareStatement(updateCart);
					stmt.setInt(1,(cart.getInt("count")+1));
					stmt.setString(2, productId.toLowerCase());
					stmt.executeUpdate();
		
					isAdd = true;
		
				}
			}
			catch(Exception ex) 
			{
				
				System.out.println(ex+"Invalid product.......");
			
			}
		}
		System.out.println("-------is Add-----"+isAdd);

		return isAdd;
	
	}
	
	public ResultSet CartList(int currentUser)
	{	
		PreparedStatement stmt=null;
		
		ResultSet cart=null;								
		
		try 
		{

			DbConnection connect=DbConnection.getInstance();
       		Connection c=connect.getConnection();

			String selectCart="SELECT * FROM Cart_List LEFT JOIN dresses ON dresses.product_id=Cart_List.product_id LEFT JOIN accessories ON accessories.product_id=Cart_List.product_id LEFT JOIN footwears ON footwears.product_id=Cart_List.product_id LEFT JOIN product_type ON product_type.producttype_id=dresses.producttype_id OR product_type.producttype_id=accessories.producttype_id LEFT JOIN product_name ON product_name.productname_id=dresses.productname_id OR product_name.productname_id=accessories.productname_id OR product_name.productname_id=footwears.productname_id LEFT JOIN product_color ON product_color.productcolor_id=dresses.productcolor_id OR product_color.productcolor_id=accessories.productcolor_id OR product_color.productcolor_id=footwears.productcolor_id LEFT JOIN genderlist ON genderlist.gender_id=dresses.gender_id OR genderlist.gender_id=accessories.gender_id OR genderlist.gender_id=footwears.gender_id LEFT JOIN material_type ON material_type.material_id=dresses.material_id OR material_type.material_id=accessories.material_id OR material_type.material_id=footwears.material_id LEFT JOIN size ON size.size_id=dresses.size_id OR size.size_id=accessories.size_id OR size.size_id=footwears.size_id LEFT JOIN shopping_varieties ON shopping_varieties.variety_id=product_name.variety_id WHERE Cart_List.user_id=?";
			stmt=c.prepareStatement(selectCart);
			stmt.setInt(1, currentUser);
			cart=stmt.executeQuery();
		
		}
		catch(Exception e)
		{
	
			System.out.println("Not Available Product");
	
		}
	
		return cart;						
	
	}
	
	public ResultSet dressDetail(String productId)
	{
	
		ResultSet dress=null;
	
		PreparedStatement stmt=null;

		try
		{
	
			DbConnection connect=DbConnection.getInstance();
			Connection c=connect.getConnection();

			String getDress="SELECT * FROM dresses LEFT JOIN genderlist ON genderlist.gender_id=dresses.gender_id LEFT JOIN product_name ON product_name.productname_id=dresses.productname_id LEFT JOIN product_type ON product_type.producttype_id=dresses.producttype_id LEFT JOIN product_color ON product_color.productcolor_id=dresses.productcolor_id LEFT JOIN size ON size.size_id=dresses.size_id WHERE LOWER(dresses.product_id)=?";
			stmt=c.prepareStatement(getDress);
			stmt.setString(1, productId.toLowerCase());
			dress=stmt.executeQuery();
	
		}
		catch(Exception e) 
		{
	
			System.out.println("No Stock Available");
	
		}

		return dress;

	}

	public ResultSet accessoriesDetail(String productId)
	{
		ResultSet accessories=null;

		PreparedStatement stmt=null;

		try
		{
		
			DbConnection connect=DbConnection.getInstance();
			Connection c=connect.getConnection();

			String getAccessories="SELECT * FROM accessories LEFT JOIN genderlist ON genderlist.gender_id=accessories.gender_id LEFT JOIN product_name ON product_name.productname_id=accessories.productname_id LEFT JOIN product_type ON product_type.producttype_id=accessories.producttype_id LEFT JOIN product_color ON product_color.productcolor_id=accessories.productcolor_id LEFT JOIN size ON size.size_id=accessories.size_id WHERE LOWER(accessories.product_id)=?";
			stmt=c.prepareStatement(getAccessories);
			stmt.setString(1, productId.toLowerCase());
			accessories=stmt.executeQuery();
		
		}
		catch(Exception e) 
		{
		
			System.out.println("No Stock Available");
		
		}

		return accessories;
	}

	public ResultSet footwearsDetail(String productId)
	{
		ResultSet footwears=null;

		PreparedStatement stmt=null;

		try
		{
			DbConnection connect=DbConnection.getInstance();
			Connection c=connect.getConnection();

			String getFootwears="SELECT * FROM footwears LEFT JOIN genderlist ON genderlist.gender_id=footwears.gender_id LEFT JOIN product_name ON product_name.productname_id=footwears.productname_id LEFT JOIN product_color ON product_color.productcolor_id=footwears.productcolor_id LEFT JOIN size ON size.size_id=footwears.size_id WHERE LOWER(footwears.product_id)=?";
			stmt=c.prepareStatement(getFootwears);
			stmt.setString(1, productId.toLowerCase());
			
			footwears=stmt.executeQuery();
		}
		catch(Exception e) 
		{
			System.out.println("No Stock Available");
		}

		return footwears;
	}
	public boolean removeProduct(String productId,int currentUser)
	{
		boolean isRemoved=false;

		PreparedStatement stmt=null;
		
		ResultSet cart=null;

		try 
		{
			DbConnection connect=DbConnection.getInstance();
			Connection c=connect.getConnection();

			String getCart="SELECT * FROM Cart_List WHERE product_id=? AND user_id=?";
			stmt=c.prepareStatement(getCart);
			stmt.setString(1, productId);
			stmt.setInt(2, currentUser);
			cart=stmt.executeQuery();
			
			if(cart.next())
			{
				if(cart.getInt("count")>1)
				{
					String updateCart="UPDATE Cart_List SET count=? WHERE product_id=? AND user_id=?";
					stmt=c.prepareStatement(updateCart);
					stmt.setInt(1, (cart.getInt("count")-1));
					stmt.setString(2, productId.toLowerCase());
					stmt.setInt(3, currentUser);
					stmt.executeUpdate();

					isRemoved=true;
				}
				else if(cart.getInt("count")==1)
				{
					String deleteCart="DELETE FROM Cart_List WHERE count=? AND product_id=?";
					stmt=c.prepareStatement(deleteCart);
					stmt.setInt(1, cart.getInt("count"));
					stmt.setString(2, productId.toLowerCase());
					stmt.executeUpdate();

					isRemoved=true;
				}
			}
		}
		catch(Exception e) 
		{
			System.out.println("Product cannot removed...!");
		}
		
		return isRemoved;
	}

	public ResultSet getAddressDetail(int currentUser)
	{
		PreparedStatement stmt=null;

		ResultSet address=null;
		
		try 
		{
			DbConnection connect=DbConnection.getInstance();
			Connection c=connect.getConnection();
	
			String getAddress="SELECT * FROM address LEFT JOIN users ON users.user_id=address.user_id WHERE address.user_id=?";
			stmt=c.prepareStatement(getAddress);
			stmt.setInt(1, currentUser);
			address=stmt.executeQuery();

		}
		catch(Exception e) 
		{
			System.out.println(e+"Invalid UserAddress...!");
		}
		
		return address;
	}
	public ResultSet getAddressDetail(int currentUser,int address_id)
	{
		PreparedStatement stmt=null;

		ResultSet address=null;
		
		try 
		{
			DbConnection connect=DbConnection.getInstance();
			Connection c=connect.getConnection();
	
			String getAddress="SELECT * FROM address LEFT JOIN users ON users.user_id=address.user_id WHERE address.user_id=? AND address.address_id=?";
			stmt=c.prepareStatement(getAddress);
			stmt.setInt(1, currentUser);
			stmt.setInt(2, address_id);
			address=stmt.executeQuery();

		}
		catch(Exception e) 
		{
			System.out.println(e+"Invalid UserAddress...!");
		}
		
		return address;
	}

	public boolean isAvailableDressProduct(String productId,int count) 
	{
		PreparedStatement stmt=null;
		
		boolean isAvailable = false;		
		
		try 
		{
			DbConnection connect=DbConnection.getInstance();
			Connection c=connect.getConnection();

			String getDress="SELECT total_stock FROM dresses WHERE LOWER(product_id)=?";			
			stmt=c.prepareStatement(getDress);
			stmt.setString(1, productId.toLowerCase());
			
			ResultSet dress=stmt.executeQuery();
			
			if(dress.next())
			{
				if(dress.getInt("total_stock")>=count)
				{
					isAvailable = true;
			
					if(dress.getInt("total_stock")<=1)
					{
						String insertNote="INSERT INTO Notification(product_id) values(?)";		
						stmt=c.prepareStatement(insertNote);
						stmt.setString(1, productId.toLowerCase());
						stmt.executeUpdate();
					}
				}
			}
		}
		catch(Exception e) 
		{
			System.out.println(e+"Exception...");
		}
		
		return isAvailable;
	}

	public boolean isAvailableAccessoriesProduct(String productId,int count) 
	{
		PreparedStatement stmt=null;
		
		boolean isAvailable = false;		
		
		try 
		{
			DbConnection connect=DbConnection.getInstance();
			Connection c=connect.getConnection();

			String getAccessories="SELECT total_stock FROM accessories WHERE LOWER(product_id)=?";			
			stmt=c.prepareStatement(getAccessories);
			stmt.setString(1, productId.toLowerCase());
			
			ResultSet accessories=stmt.executeQuery();
			
			if(accessories.next())
			{
				if(accessories.getInt("total_stock")>=count)
				{
					isAvailable = true;
			
					if(accessories.getInt("total_stock")<=1)
					{
						String insertNote="INSERT INTO Notification(product_id) values(?)";		
						stmt=c.prepareStatement(insertNote);
						stmt.setString(1, productId.toLowerCase());
						stmt.executeUpdate();
					}
				}
			}
		}
		catch(Exception e) 
		{
			System.out.println(e+"Exception...");
		}
		
		return isAvailable;
	}
	public boolean isAvailableFootWearsProduct(String productId,int count) 
	{
		PreparedStatement stmt=null;
		
		boolean isAvailable = false;		
		
		try 
		{
			DbConnection connect=DbConnection.getInstance();
			Connection c=connect.getConnection();

			String getFootWear="SELECT total_stock FROM footwears WHERE LOWER(product_id)=?";			
			stmt=c.prepareStatement(getFootWear);
			stmt.setString(1, productId.toLowerCase());
			
			ResultSet footwears=stmt.executeQuery();
			
			if(footwears.next())
			{
				if(footwears.getInt("total_stock")>=count)
				{
					isAvailable = true;
			
					if(footwears.getInt("total_stock")<=1)
					{
						String insertNote="INSERT INTO Notification(product_id) values(?)";		
						stmt=c.prepareStatement(insertNote);
						stmt.setString(1, productId.toLowerCase());
						stmt.executeUpdate();
					}
				}
			}
		}
		catch(Exception e) 
		{
			System.out.println(e+"Exception...");
		}
		
		return isAvailable;
	}

	public void setDeliveryAddress(int currentUser,String phoneNo,String userAddress, String pincode, String city,String state)
	{
		PreparedStatement stmt=null;

		try 
		{
			DbConnection connect=DbConnection.getInstance();
			Connection c=connect.getConnection();
			
			String selectUser="SELECT user_id FROM users WHERE user_id=?";
			
			stmt=c.prepareStatement(selectUser);
			stmt.setInt(1, currentUser);
			ResultSet user=stmt.executeQuery();
					
			if(user.next())
			{
				String insertAddress="INSERT INTO Address(user_id,address,pincode,state,district) values(?,?,?,?,?)";
				stmt=c.prepareStatement(insertAddress);
				stmt.setInt(1, user.getInt("user_id"));
				stmt.setString(2, userAddress);
				stmt.setString(3, pincode);
				stmt.setString(4, state);
				stmt.setString(5, city);
				stmt.executeUpdate();
			}
		}
		catch(Exception e) 
		{
			System.out.println(e+"Exception...");
		}
	}
	
	public boolean isDressAddedToPurchasedList(String productId,int currentUser,int address_id)
	{
		boolean isAdd = false;

		PreparedStatement stmt=null;
		
		try 
		{
			DbConnection connect=DbConnection.getInstance();
			Connection c=connect.getConnection();

			Date currentDate = new Date();

			Calendar calendar = Calendar.getInstance();
			calendar.setTime(currentDate);
			calendar.add(Calendar.DAY_OF_YEAR, 4);
			Date dateAfter4Days = calendar.getTime();

			String selectDress="SELECT dresses.product_id,status.status_id,dresses.total_stock FROM dresses LEFT JOIN Status ON status='On Process' WHERE LOWER(product_id)=?";
			stmt=c.prepareStatement(selectDress);
			stmt.setString(1, productId.toLowerCase());
			ResultSet dress=stmt.executeQuery();
			
			if(dress.next())
			{
				String insertPurchasedList="INSERT INTO Purchased_Items(user_id,product_id,status_id,delivery_date,address_id) values(?,?,?,?,?)";
				stmt=c.prepareStatement(insertPurchasedList);
				stmt.setInt(1, currentUser);
				stmt.setString(2, productId.toLowerCase());
				stmt.setInt(3, dress.getInt("status_id"));
				stmt.setDate(4, new java.sql.Date(dateAfter4Days.getTime()));
				stmt.setInt(5,address_id);
				stmt.executeUpdate();
				
				String updateDress="UPDATE dresses SET total_stock=? WHERE product_id=?";
				stmt=c.prepareStatement(updateDress);
				stmt.setInt(1, (dress.getInt("total_stock")-1));
				stmt.setString(2, productId.toLowerCase());
				stmt.executeUpdate();
				
				isAdd=true;
			}
			dress.close();
		}
		catch(Exception e) 
		{
			System.out.println(e+"Not Added to PurchasedItems...!");
		}
		return isAdd;
	}
	public boolean isAccessoriesAddedToPurchasedList(String productId,int currentUser,int address_id)
	{
		boolean isAdd = false;

		PreparedStatement stmt=null;
		
		try 
		{
			DbConnection connect=DbConnection.getInstance();
			Connection c=connect.getConnection();

			Date currentDate = new Date();

			Calendar calendar = Calendar.getInstance();
			calendar.setTime(currentDate);
			calendar.add(Calendar.DAY_OF_YEAR, 4);
			Date dateAfter4Days = calendar.getTime();

			String selectAccessories="SELECT accessories.product_id,status.status_id,accessories.total_stock FROM accessories LEFT JOIN Status ON status='On Process' WHERE LOWER(product_id)=?";
			stmt=c.prepareStatement(selectAccessories);
			stmt.setString(1, productId.toLowerCase());
			ResultSet accessories=stmt.executeQuery();
			
			if(accessories.next())
			{
				String insertPurchasedList="INSERT INTO Purchased_Items(user_id,product_id,status_id,delivery_date,address_id) values(?,?,?,?,?)";
				stmt=c.prepareStatement(insertPurchasedList);
				stmt.setInt(1, currentUser);
				stmt.setString(2, productId.toLowerCase());
				stmt.setInt(3, accessories.getInt("status_id"));
				stmt.setDate(4, new java.sql.Date(dateAfter4Days.getTime()));
				stmt.setInt(5,address_id);
				stmt.executeUpdate();
				
				String updateAccessories="UPDATE accessories SET total_stock=? WHERE product_id=?";
				stmt=c.prepareStatement(updateAccessories);
				stmt.setInt(1, (accessories.getInt("total_stock")-1));
				stmt.setString(2, productId.toLowerCase());
				stmt.executeUpdate();
				
				isAdd=true;
			}
			
			accessories.close();
		}
		catch(Exception e) 
		{
			System.out.println(e+"Not Added to PurchasedItems...!");
		}
		
		return isAdd;
	}
	public boolean isFootWearsAddedToPurchasedList(String productId,int currentUser,int address_id)
	{
		boolean isAdd = false;

		PreparedStatement stmt=null;
		
		try 
		{
			DbConnection connect=DbConnection.getInstance();
			Connection c=connect.getConnection();

			Date currentDate = new Date();

			Calendar calendar = Calendar.getInstance();
			calendar.setTime(currentDate);
			calendar.add(Calendar.DAY_OF_YEAR, 4);
			Date dateAfter4Days = calendar.getTime();

			String selectFootWears="SELECT footwears.product_id,status.status_id,footwears.total_stock FROM footwears LEFT JOIN Status ON status='On Process' WHERE LOWER(product_id)=?";
			stmt=c.prepareStatement(selectFootWears);
			stmt.setString(1, productId.toLowerCase());
			ResultSet footwears=stmt.executeQuery();
			
			if(footwears.next())
			{
				String insertPurchasedList="INSERT INTO Purchased_Items(user_id,product_id,status_id,delivery_date,address_id) values(?,?,?,?,?)";
				stmt=c.prepareStatement(insertPurchasedList);
				stmt.setInt(1, currentUser);
				stmt.setString(2, productId.toLowerCase());
				stmt.setInt(3, footwears.getInt("status_id"));
				stmt.setDate(4, new java.sql.Date(dateAfter4Days.getTime()));
				stmt.setInt(5,address_id);
				stmt.executeUpdate();
				
				String updateFootWears="UPDATE footwears SET total_stock=? WHERE product_id=?";
				stmt=c.prepareStatement(updateFootWears);
				stmt.setInt(1, (footwears.getInt("total_stock")-1));
				stmt.setString(2, productId.toLowerCase());
				stmt.executeUpdate();
				
				isAdd=true;
			}
			
			footwears.close();
		}
		catch(Exception e) 
		{
			System.out.println(e+"Not Added to PurchasedItems...!");
		}
		
		return isAdd;
	}
	public ResultSet getPurchasedList(int currentUser)
	{
		ResultSet product=null,list=null;

		PreparedStatement stmt=null;

		System.out.println(currentUser);

		try
		{
			DbConnection connect=DbConnection.getInstance();
			Connection c=connect.getConnection();

			String getList="SELECT * FROM Purchased_Items WHERE user_id=? AND status_id=?";
			stmt=c.prepareStatement(getList);
			stmt.setInt(1, currentUser);
			stmt.setInt(2,3);
			list=stmt.executeQuery();

			while(list.next())
			{
				System.out.println("---inside while------");

				LocalDate currentDate = LocalDate.now();
                LocalDate endDate = list.getDate("delivery_date").toLocalDate();

                long daysDifference = endDate.toEpochDay() - currentDate.toEpochDay();

                System.out.println("-------difference-------   "+daysDifference);

                if(daysDifference<=0)
                {
                	System.out.println("-----inside if utilitiees---");

					String insertList="UPDATE Purchased_Items SET status_id=? WHERE user_id=? AND status_id=?";
					stmt=c.prepareStatement(insertList);
					stmt.setInt(1, 1);
					stmt.setInt(2, currentUser);
					stmt.setInt(3,3);
					stmt.executeUpdate();
				}
			}

			String getProduct="SELECT * FROM Purchased_Items LEFT JOIN users ON users.user_id=purchased_items.user_id LEFT JOIN dresses ON dresses.product_id=Purchased_Items.product_id LEFT JOIN accessories ON accessories.product_id=Purchased_Items.product_id LEFT JOIN footwears ON footwears.product_id=Purchased_Items.product_id LEFT JOIN product_name ON dresses.productname_id=product_name.productname_id OR accessories.productname_id=product_name.productname_id OR footwears.productname_id=product_name.productname_id LEFT JOIN product_color ON dresses.productcolor_id=product_color.productcolor_id OR accessories.productcolor_id=product_color.productcolor_id OR footwears.productcolor_id=product_color.productcolor_id LEFT JOIN size ON dresses.size_id=size.size_id OR accessories.size_id=size.size_id OR footwears.size_id=size.size_id LEFT JOIN genderlist ON genderlist.gender_id=dresses.gender_id OR genderlist.gender_id=accessories.gender_id OR genderlist.gender_id=footwears.gender_id LEFT JOIN Status ON Purchased_Items.status_id=Status.status_id LEFT JOIN shopping_varieties ON shopping_varieties.variety_id=product_name.variety_id LEFT JOIN Address ON Address.address_id=Purchased_Items.address_id WHERE Purchased_Items.user_id=?";
			stmt=c.prepareStatement(getProduct);
			stmt.setInt(1, currentUser);
			
			product=stmt.executeQuery();

			System.out.println("end list........");
		}
		catch(Exception e) 
		{
		
			System.out.println(e+"No Stock Available");
		
		}
		
		return product;
	}
	public ResultSet getAddressId()
	{
		ResultSet address=null;

		PreparedStatement stmt=null;

		try
		{
			DbConnection connect=DbConnection.getInstance();
			Connection c=connect.getConnection();

			String getAddress="SELECT MAX(address_id) FROM Address;";
			stmt=c.prepareStatement(getAddress);
			address=stmt.executeQuery();

		}
		catch(Exception e)
		{
			System.out.println(e+"Not Valid User");
		}
		return address;
	}
	public boolean isProductCancelled(String productId,int currentUser)
	{
		boolean isCancel=false;

		ResultSet product=null,dress=null,access=null,foot=null;

		PreparedStatement stmt=null;

		try
		{
			DbConnection connect=DbConnection.getInstance();
			Connection c=connect.getConnection();

			String getProduct="SELECT * FROM Purchased_Items LEFT JOIN dresses ON dresses.product_id=? LEFT JOIN accessories ON accessories.product_id=? LEFT JOIN footwears ON footwears.product_id=? LEFT JOIN product_name ON dresses.productname_id=product_name.productname_id OR accessories.productname_id=product_name.productname_id OR footwears.productname_id=product_name.productname_id LEFT JOIN product_color ON dresses.productcolor_id=product_color.productcolor_id OR accessories.productcolor_id=product_color.productcolor_id OR footwears.productcolor_id=product_color.productcolor_id LEFT JOIN size ON dresses.size_id=size.size_id OR accessories.size_id=size.size_id OR footwears.size_id=size.size_id LEFT JOIN Status ON Purchased_Items.status_id=Status.status_id WHERE Purchased_Items.product_id=? AND Purchased_Items.user_id=?";
			stmt=c.prepareStatement(getProduct);
			stmt.setString(1, productId);
			stmt.setString(2, productId);
			stmt.setString(3, productId);
			stmt.setString(4, productId);
			stmt.setInt(5,currentUser);
			product=stmt.executeQuery();
			
			if(product.next())
			{
				System.out.println("----Utilities-------Cancel product------inside if------");

				String updateDress="UPDATE Purchased_Items SET status_id=? WHERE Purchased_Items.product_id=? AND Purchased_Items.user_id=?";
				stmt=c.prepareStatement(updateDress);
				stmt.setInt(1, 2);
				stmt.setString(2, productId);
				stmt.setInt(3,currentUser);
				stmt.executeUpdate();
				isCancel=true;
			}

			System.out.println("----Utilities-------Cancel product-------"+isCancel);

			if(isCancel)
			{
				String getdressUpdate="SELECT * FROM dresses WHERE product_id=?";
				stmt=c.prepareStatement(getdressUpdate);
				stmt.setString(1, productId);
				dress=stmt.executeQuery();
																
				String getaccessUpdate="SELECT * FROM accessories WHERE product_id=?";
				stmt=c.prepareStatement(getaccessUpdate);
				stmt.setString(1, productId);
				access=stmt.executeQuery();
																
				String getfootUpdate="SELECT * FROM footwears WHERE product_id=?";
				stmt=c.prepareStatement(getfootUpdate);
				stmt.setString(1, productId);
				foot=stmt.executeQuery();
																
				if(dress.next())
				{
					String updateDress="UPDATE dresses SET total_stock=? WHERE product_id=?";
					stmt=c.prepareStatement(updateDress);
					stmt.setInt(1, (dress.getInt("total_stock")+1));
					stmt.setString(2, productId);
					stmt.executeUpdate();
					isCancel=true;
				}
				
				if(access.next())
				{
					String updateAccess="UPDATE accessories SET total_stock=? WHERE product_id=?";
					stmt=c.prepareStatement(updateAccess);
					stmt.setInt(1, (access.getInt("total_stock")+1));
					stmt.setString(2, productId);
					stmt.executeUpdate();
					isCancel=true;
				}
				
				if(foot.next())
				{
					String updateFoot="UPDATE footwears SET total_stock=? WHERE product_id=?";
					stmt=c.prepareStatement(updateFoot);
					stmt.setInt(1, (foot.getInt("total_stock")+1));
					stmt.setString(2, productId);
					stmt.executeUpdate();
					isCancel=true;
				}
			}
		}
		catch(Exception e) 
		{
		
			System.out.println("No Stock Available");
		
		}
		
		return isCancel;
	}
	public boolean isCountAddedToProductList(String product_Id,int count)
	{
		boolean isAdd=false;

		PreparedStatement stmt=null;

		try
		{
			DbConnection connect=DbConnection.getInstance();
			Connection c=connect.getConnection();

			String getNotify="SELECT * FROM Notification WHERE Notification.product_id=?";
			stmt=c.prepareStatement(getNotify);
			stmt.setString(1, product_Id);
			ResultSet notification=stmt.executeQuery();
														
			if (notification.next()) 
			{	
				String getDress="SELECT product_id FROM dresses WHERE dresses.product_id=?";
				stmt=c.prepareStatement(getDress);
				stmt.setString(1, notification.getString("product_id"));
				ResultSet dress=stmt.executeQuery();
															
				if(dress.next()) 
				{
					String dressUpdate="UPDATE dresses SET total_stock=? WHERE product_id=?";
					stmt=c.prepareStatement(dressUpdate);
					stmt.setInt(1, count);
					stmt.setString(2, product_Id);
					stmt.executeUpdate();
				}
															
				String getAccess="SELECT product_id FROM accessories WHERE accessories.product_id=?";
				stmt=c.prepareStatement(getAccess);
				stmt.setString(1, notification.getString("product_id"));
				ResultSet access=stmt.executeQuery();
															
				if(access.next()) 
				{
					String accessUpdate="UPDATE accessories SET total_stock=? WHERE product_id=";
					stmt=c.prepareStatement(accessUpdate);
					stmt.setInt(1, count);
					stmt.setString(2, product_Id);
					stmt.executeUpdate();
				}
															
				String getFoot="SELECT product_id FROM footwears WHERE footwears.product_id=?";
				stmt=c.prepareStatement(getFoot);
				stmt.setString(1, notification.getString("product_id"));
				ResultSet foot=stmt.executeQuery();
															
				if(foot.next()) 
				{
					String footUpdate="UPDATE footwears SET total_stock=? WHERE product_id=?";
					stmt=c.prepareStatement(footUpdate);
					stmt.setInt(1, count);
					stmt.setString(2, product_Id);
					stmt.executeUpdate();
				}

				String deleteNotify="DELETE FROM Notification WHERE product_id=?";
				stmt=c.prepareStatement(deleteNotify);
				stmt.setString(1, product_Id);
				stmt.executeUpdate();

				isAdd=true;
			}

		}
		catch(Exception e) 
		{
		
			System.out.println("No Stock Available");
		
		}

		return isAdd;
	}
	public boolean isProductAdded(String variety,String gender,String productId,String productName,String productColor,String materialType,String productType,String size,double price,float deliveryCharge,int totalStock)
	{
		boolean isAdded=false;

		PreparedStatement stmt=null;
		
		System.out.println("---Product Add----- Utility page----");
		try
		{
			DbConnection connect=DbConnection.getInstance();
			Connection c=connect.getConnection();
			
			String getProductId="SELECT * FROM dresses LEFT JOIN accessories ON accessories.product_id=? LEFT JOIN footwears ON footwears.product_id=? WHERE dresses.product_id=? OR accessories.product_id=? OR footwears.product_id=?";
			stmt=c.prepareStatement(getProductId);
			stmt.setString(1, productId);
			stmt.setString(2, productId);
			stmt.setString(3, productId);
			stmt.setString(4, productId);
			stmt.setString(5, productId);
			ResultSet productID=stmt.executeQuery();
		
			System.out.println(productID.next());
			
			if(!productID.next()) 
			{
				String getVariety="SELECT variety_id FROM shopping_varieties WHERE LOWER(shopping_varieties.varieties)=?";
				stmt=c.prepareStatement(getVariety);
				stmt.setString(1, variety.toLowerCase());
				ResultSet vary=stmt.executeQuery();
				
				if(vary.next()) 
				{
					String getGender="SELECT gender_id FROM genderlist WHERE LOWER(genderlist.gender)=?";
					stmt=c.prepareStatement(getGender);
					stmt.setString(1, gender.toLowerCase());
					ResultSet gen=stmt.executeQuery();
					
					ResultSet ptype=null;
					
					if( ! ("FootWears".equalsIgnoreCase(variety)))
					{	
						String getProductType="SELECT producttype_id FROM product_type WHERE LOWER(producttype)=? AND variety_id=?";
						stmt=c.prepareStatement(getProductType);
						stmt.setString(1, productType.toLowerCase());
						stmt.setInt(2, vary.getInt("variety_id"));
						ptype=stmt.executeQuery();

						if( ! ptype.next())
						{
							String insertType="INSERT INTO product_type(producttype) values(?)";
							stmt=c.prepareStatement(insertType);
							stmt.setString(1, productType);
							stmt.executeUpdate();
								
							String getType="SELECT producttype_id FROM product_type WHERE LOWER(producttype)=? AND variety_id=?";
							stmt=c.prepareStatement(getType);
							stmt.setString(1, productType.toLowerCase());
							stmt.setInt(2, vary.getInt("variety_id"));
							ptype=stmt.executeQuery();
						}
						
					}
					
					String getProductName="SELECT productname_id FROM product_name WHERE LOWER(productname)=? AND variety_id=?";
					stmt=c.prepareStatement(getProductName);
					stmt.setString(1, productName.toLowerCase());
					stmt.setInt(2, vary.getInt("variety_id"));
					ResultSet name=stmt.executeQuery();
					
					if(!name.next())
					{
						String insertName="INSERT INTO product_name(productname,variety_id,producttype_id) values(?,?,?)";
						stmt=c.prepareStatement(insertName);
						stmt.setString(1, productName);
						stmt.setInt(2,vary.getInt("variety_id"));
						stmt.setInt(3,ptype.getInt("producttype_id"));
						stmt.executeUpdate();
						
						String getName="SELECT productname_id FROM product_name WHERE LOWER(productname)=? AND variety_id=? AND producttype_id=?";
						stmt=c.prepareStatement(getName);
						stmt.setString(1, productName.toLowerCase());
						stmt.setInt(2, vary.getInt("variety_id"));
						stmt.setInt(3,ptype.getInt("producttype_id"));
						name=stmt.executeQuery();
					}
					
					String getProductColor="SELECT productcolor_id FROM product_color WHERE LOWER(productcolor)=?";
					stmt=c.prepareStatement(getProductColor);
					stmt.setString(1, productColor.toLowerCase());
					ResultSet color=stmt.executeQuery();
					
					if(!color.next())
					{
						String insertColor="INSERT INTO product_color(productcolor) values(?)";
						stmt=c.prepareStatement(insertColor);
						stmt.setString(1, productColor);
						stmt.executeUpdate();
						
						String getColor="SELECT productcolor_id FROM product_color WHERE LOWER(productcolor)=?";
						stmt=c.prepareStatement(getColor);
						stmt.setString(1, productColor.toLowerCase());
						color=stmt.executeQuery();
					}

					String getProductType="SELECT material_id FROM material_type WHERE LOWER(material_type)=? AND variety_id=?";
					stmt=c.prepareStatement(getProductType);
					stmt.setString(1, productType.toLowerCase());
					stmt.setInt(2, vary.getInt("variety_id"));
					ResultSet type=stmt.executeQuery();
					
					if(!type.next())
					{
						String insertType="INSERT INTO material_type(material_type,variety_id) values(?,?)";
						stmt=c.prepareStatement(insertType);
						stmt.setString(1, materialType);
						stmt.setInt(2,vary.getInt("variety_id"));
						stmt.executeUpdate();
						
						String getType="SELECT material_id FROM material_type WHERE LOWER(material_type)=? AND variety_id=?";
						stmt=c.prepareStatement(getType);
						stmt.setString(1, materialType.toLowerCase());
						stmt.setInt(2, vary.getInt("variety_id"));
						type=stmt.executeQuery();
					}
					
					String getProductSize="SELECT size_id FROM size WHERE LOWER(size)=?";
					stmt=c.prepareStatement(getProductSize);
					stmt.setString(1, size.toLowerCase());
					ResultSet productSize=stmt.executeQuery();
					
					if(!productSize.next())
					{
						String insertSize="INSERT INTO size(size) values(?)";
						stmt=c.prepareStatement(insertSize);
						stmt.setString(1, size);
						stmt.executeUpdate();
						
						String getSize="SELECT size_id FROM size WHERE LOWER(size)=?";
						stmt=c.prepareStatement(getSize);
						stmt.setString(1, size.toLowerCase());
						productSize=stmt.executeQuery();
					}
					
					if(gen.next() && ptype.next() && name.next() && color.next() && type.next() && color.next() ) 
					{
						String insertStock="INSERT INTO "+vary.getString("varieties")+"(product_id,gender_id,producttype_id,productname_id,productcolor_id,material_id,size_id,price,deliverycharge,total_stock) values(?,?,?,?,?,?,?,?,?,?)";
						stmt=c.prepareStatement(insertStock);
						stmt.setString(1, productId);
						stmt.setInt(2, gen.getInt("gender_id"));
						stmt.setInt(3, ptype.getInt("producttype_id"));
						stmt.setInt(4, name.getInt("productname_id"));
						stmt.setInt(5, color.getInt("productcolor_id"));
						stmt.setInt(6, type.getInt("material_id"));
						stmt.setInt(7, productSize.getInt("size_id"));
						stmt.setDouble(8, price);
						stmt.setFloat(9, deliveryCharge);
						stmt.setInt(10, totalStock);
						stmt.executeUpdate();

						isAdded=true;

						System.out.println("     <<<<<<<<<<<<<<<<<<Product is Added Successfully>>>>>>>>>>>>>>>>>>");
					}
					else
					{
						PreparedStatement state=null;
						
						String insertStock="INSERT INTO "+vary.getString("varieties")+"(product_id,gender_id,productname_id,productcolor_id,material_id,size_id,price,deliverycharge,total_stock) values(?,?,?,?,?,?,?,?,?)";
						state=c.prepareStatement(insertStock);
						state.setString(1, productId);
						state.setInt(2, gen.getInt("gender_id"));
						state.setInt(3, name.getInt("productname_id"));
						state.setInt(4, color.getInt("productcolor_id"));
						state.setInt(5, type.getInt("material_id"));
						state.setInt(6, productSize.getInt("size_id"));
						stmt.setDouble(8, price);
						stmt.setFloat(9, deliveryCharge);
						stmt.setInt(10, totalStock);
						state.executeUpdate();

						isAdded=true;

						System.out.println("     <<<<<<<<<<<<<<<<<<Product is Added Successfully>>>>>>>>>>>>>>>>>>");
					}
				}
			}
			else
			{
				
				System.out.println("Product is Already Exists...");
			
			}
		}
		catch(Exception e) 
		{
			System.out.println(e+"Product is Already Exists...");
		}

		return isAdded;
	}
	public boolean isAmountAddedToProductList(String productId,double amount,float deliveryCharge)
	{
		boolean isAdded=false;

		PreparedStatement stmt=null;
		
		try
		{
			DbConnection connect=DbConnection.getInstance();
			Connection c=connect.getConnection();

			String updateDress="UPDATE dresses SET price=?,deliverycharge=? WHERE LOWER(product_id)=?";
			stmt=c.prepareStatement(updateDress);
			stmt.setDouble(1, amount);
			stmt.setFloat(2,deliveryCharge);
			stmt.setString(3, productId.toLowerCase());
			stmt.executeUpdate();
												
			String updateAccess="UPDATE accessories SET price=?,deliverycharge=? WHERE LOWER(product_id)=?";
			stmt=c.prepareStatement(updateAccess);
			stmt.setDouble(1, amount);
			stmt.setFloat(2,deliveryCharge);
			stmt.setString(3, productId.toLowerCase());
			stmt.executeUpdate();
												
			String updateFoot="UPDATE footwears SET price=?,deliverycharge=? WHERE LOWER(product_id)=?";
			stmt=c.prepareStatement(updateFoot);
			stmt.setDouble(1, amount);
			stmt.setFloat(2,deliveryCharge);
			stmt.setString(3, productId.toLowerCase());
			stmt.executeUpdate();
			
			isAdded=true;

			System.out.println("<<<<<<<<<<<<<<<<<<<<<<---Amount will be Added Successfully--->>>>>>>>>>>>>>>>>>>>>>");
		
		}
		catch(Exception e) 
		{
		
			System.out.println("Update valid amount...");
		
		}

		return isAdded;
	}
	public boolean isProductAddedToWishList(String productId,int currentUser)
	{
		boolean isAdded=false;

		PreparedStatement stmt=null;
		
		try 
		{
			DbConnection connect=DbConnection.getInstance();
			Connection c=connect.getConnection();

			String getAccount="INSERT INTO wish_list(user_id,product_id) values(?,?)";
			stmt=c.prepareStatement(getAccount);
			stmt.setInt(1,currentUser);
			stmt.setString(2,productId.toLowerCase());
			stmt.executeUpdate();
			
			isAdded=true;
		}
		catch(Exception e) 
		{
			System.out.println(e+"Not Valid Account...!");
		}

		return isAdded;
	}
	public ResultSet getProductFromWishList(int currentUser)
	{
		ResultSet list=null;

		PreparedStatement stmt=null;
		
		try 
		{
			DbConnection connect=DbConnection.getInstance();
			Connection c=connect.getConnection();

			String selectList="SELECT * FROM wish_list LEFT JOIN dresses ON dresses.product_id=wish_list.product_id LEFT JOIN accessories ON accessories.product_id=wish_list.product_id LEFT JOIN footwears ON footwears.product_id=wish_list.product_id LEFT JOIN product_type ON product_type.producttype_id=dresses.producttype_id OR product_type.producttype_id=accessories.producttype_id LEFT JOIN product_name ON product_name.productname_id=dresses.productname_id OR product_name.productname_id=accessories.productname_id OR product_name.productname_id=footwears.productname_id LEFT JOIN product_color ON product_color.productcolor_id=dresses.productcolor_id OR product_color.productcolor_id=accessories.productcolor_id OR product_color.productcolor_id=footwears.productcolor_id LEFT JOIN genderlist ON genderlist.gender_id=dresses.gender_id OR genderlist.gender_id=accessories.gender_id OR genderlist.gender_id=footwears.gender_id LEFT JOIN material_type ON material_type.material_id=dresses.material_id OR material_type.material_id=accessories.material_id OR material_type.material_id=footwears.material_id LEFT JOIN size ON size.size_id=dresses.size_id OR size.size_id=accessories.size_id OR size.size_id=footwears.size_id LEFT JOIN shopping_varieties ON shopping_varieties.variety_id=product_name.variety_id WHERE wish_list.user_id=?";
			stmt=c.prepareStatement(selectList);
			stmt.setInt(1, currentUser);
			list=stmt.executeQuery();
			
		}
		catch(Exception e) 
		{
			System.out.println(e+"Not Valid List...!");
		}

		return list;
	}
	public boolean removeProductFromWishList(String productId,int currentUser)
	{
		boolean isRemoved=false;

		ResultSet list=null;

		PreparedStatement stmt=null;
		
		try 
		{
			DbConnection connect=DbConnection.getInstance();
			Connection c=connect.getConnection();

			String getAccount="DELETE FROM wish_list WHERE user_id=? AND LOWER(product_id)=?";
			stmt=c.prepareStatement(getAccount);
			stmt.setInt(1,currentUser);
			stmt.setString(2,productId.toLowerCase());
			stmt.executeUpdate();

			isRemoved=true;
		}
		catch(Exception e) 
		{
			System.out.println(e+"Not Valid List...!");
		}

		return isRemoved;
	}
	public boolean isCorrectUserAccount(String holderName,long cardNo,int cvvNo,String expireDate)
	{
		boolean isAdd = false;

		PreparedStatement stmt=null;
		
		try 
		{
			DbConnection connect=DbConnection.getInstance();
			Connection c=connect.getConnection();

			String getAccount="SELECT * FROM account WHERE card_no=? AND LOWER(account_holdername)=? AND cvv_no=? AND expirydate=?";
			stmt=c.prepareStatement(getAccount);
			stmt.setLong(1, cardNo);
			stmt.setString(2,holderName.toLowerCase());
			stmt.setInt(3,cvvNo);
			stmt.setString(4,expireDate);

			ResultSet account=stmt.executeQuery();
			
			if(account.next())
			{
				isAdd=true;
			}

		}
		catch(Exception e) 
		{
			System.out.println(e+"Not Valid Account...!");
		}

		return isAdd;		
	}
	public int getNotificationCount()
	{
		int value = 0;

		PreparedStatement stmt=null;
		
		try 
		{
			DbConnection connect=DbConnection.getInstance();
			Connection c=connect.getConnection();

			String getSize="SELECT COUNT(*) FROM Notification";
			stmt=c.prepareStatement(getSize);
			ResultSet size=stmt.executeQuery();

			if(size.next())
			{
				value=size.getInt("count");
			}
		}
		catch(Exception e) 
		{
			System.out.println(e+"Not Valid Account...!");
		}

		System.out.println("---------Utility page-------"+value);

		return value;
	}
	public ResultSet getNotificationList()
	{
		PreparedStatement stmt=null;
		
		ResultSet notify=null;

		try 
		{
			DbConnection connect=DbConnection.getInstance();
			Connection c=connect.getConnection();

			String getNotification="SELECT * FROM Notification LEFT JOIN dresses ON dresses.product_id=Notification.product_id LEFT JOIN accessories ON accessories.product_id=Notification.product_id LEFT JOIN footwears ON footwears.product_id=Notification.product_id LEFT JOIN product_type ON product_type.producttype_id=dresses.producttype_id OR product_type.producttype_id=accessories.producttype_id LEFT JOIN product_name ON product_name.productname_id=dresses.productname_id OR product_name.productname_id=accessories.productname_id OR product_name.productname_id=footwears.productname_id LEFT JOIN product_color ON product_color.productcolor_id=dresses.productcolor_id OR product_color.productcolor_id=accessories.productcolor_id OR product_color.productcolor_id=footwears.productcolor_id LEFT JOIN genderlist ON genderlist.gender_id=dresses.gender_id OR genderlist.gender_id=accessories.gender_id OR genderlist.gender_id=footwears.gender_id LEFT JOIN material_type ON material_type.material_id=dresses.material_id OR material_type.material_id=accessories.material_id OR material_type.material_id=footwears.material_id LEFT JOIN size ON size.size_id=dresses.size_id OR size.size_id=accessories.size_id OR size.size_id=footwears.size_id LEFT JOIN shopping_varieties ON shopping_varieties.variety_id=product_name.variety_id WHERE Notification.product_id=dresses.product_id OR Notification.product_id=accessories.product_id OR Notification.product_id=footwears.product_id";
			stmt=c.prepareStatement(getNotification);
			notify=stmt.executeQuery();
		}
		catch(Exception e) 
		{
			System.out.println(e+"Not Valid Account...!");
		}
		
		return notify;
	}
	public boolean isPurchased(double amount,long cardNo)
	{
		boolean isPurchase=false;

		PreparedStatement stmt=null;

		try 
		{
			DbConnection connect=DbConnection.getInstance();
			Connection c=connect.getConnection();

			String getAccount="SELECT * FROM account WHERE card_no=?";
			stmt=c.prepareStatement(getAccount);
			stmt.setLong(1,cardNo);
			ResultSet account=stmt.executeQuery();
			
			if(account.next()) 
			{

				if(amount <= account.getDouble("balance"))
				{
					// LocalDateTime now = LocalDateTime.now(); 
					// DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");  
					// String formatDateTime = now.format(format); 
		        
					// transaction=new Transaction(cardNo,"Transacted",formatDateTime,amount);
				
					if(account.getLong("card_no") == cardNo) 
					{

						String updateAccount="UPDATE account SET balance=? WHERE card_no=?";
						stmt=c.prepareStatement(updateAccount);
						stmt.setDouble(1, (account.getDouble("balance")-amount));
						stmt.setLong(2, cardNo);
						stmt.executeUpdate();

						isPurchase=true;
					}
					
				}
			}
		}
		catch(Exception e) 
		{
			System.out.println(e+"Not sufficient amount in your account......!");
		}

		return isPurchase;
	}
}