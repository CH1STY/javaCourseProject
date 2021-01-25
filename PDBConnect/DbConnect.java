package PDBConnect;

import java.sql.*;


public class DbConnect {
 	
private final String url = "jdbc:mysql:///";
private final String uName = "root";
private final String pass = "";
private final String drName = "com.mysql.jdbc.Driver";
		public 	Connection con;
		public	Statement st;
		public	ResultSet result;
	
	public DbConnect(){
		
	}
	
	public void openConnection()
	{
		try {
			
			
			
			Class.forName(drName).newInstance();
			con = DriverManager.getConnection(url,uName,pass);
			st = con.createStatement();
			st.execute("CREATE DATABASE IF NOT EXISTS projectdb");	
			con = DriverManager.getConnection(url.concat("projectdb"),uName,pass);
			st = con.createStatement();
			
			//System.out.println("Connection Set");
			
			
		}catch(Exception e){
			
			System.out.println(e.getMessage());
			
		}

	}
	
	
	public void closeConnection()
	{
		try
		{
			if(con!=null){con.close();}
			if(st!=null){st.close();}
			if(result!=null){result.close();}
		}
		catch(Exception e){}
		
		
		//System.out.println("Connection Closed");
	}
	
	
}

