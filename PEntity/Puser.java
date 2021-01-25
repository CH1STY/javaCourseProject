package PEntity; 

import PDBConnect.*;

public class Puser{
	
	private int userId;
	private String userName;
	private String userPass;
	private String userAddress;
	private String userNumber;
	private int userType;
	
	public Puser(){}
	
	public Puser(int userId,String userName,String userPass,String userAddress,String userNumber,int userType){
		
		this.userId = userId;
		this.userName = userName;
		this.userPass = userPass;
		this.userAddress = userAddress;
		this.userNumber = userNumber;
		this.userType = userType;
		
		
		
	}
	
	
	
	//ONE PARA SET
	public void setUserName(String uName){
		DbConnect dbo = new DbConnect();
		dbo.openConnection();
		
		try{
		dbo.st.execute("Update puser SET username='"+uName+"' where uid= "+this.userId+"");
		dbo.st.executeUpdate("Update puser SET username='"+uName+"' where uid= "+this.userId+"");
		
		}catch(Exception ex){
			System.out.println(ex.getMessage());
			
			
		}
		
		dbo.closeConnection();
		this.userName = uName;
		
	
	
	}
	public void setUserPass(String userPass){
		DbConnect dbo = new DbConnect();
		dbo.openConnection();
		
			try{
			
			dbo.st.executeUpdate("Update puser SET password='"+userPass+"' where uid= "+this.userId+"");
		
		}catch(Exception ex){
			System.out.println(ex.getMessage());
			
			
		}
		
		dbo.closeConnection();
		this.userPass = userPass;}


	public void setUserAddress(String userAddress){
	
		DbConnect dbo = new DbConnect();
		dbo.openConnection();
		
		try{
		
		dbo.st.executeUpdate("Update puser SET address='"+userAddress+"' where uid= "+this.userId+"");
		
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
			dbo.closeConnection();
			this.userAddress = userAddress;
		}
	
	
	
	public void setUserNumber(String userNumber){
		DbConnect dbo = new DbConnect();
		dbo.openConnection();
		
		try{
		
		dbo.st.executeUpdate("Update puser SET mobile_number='"+userNumber+"' where uid= "+this.userId+"");
		
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		dbo.closeConnection();
		this.userNumber = userNumber;
		
		
		}
	public void setUserType(int userType){
		DbConnect dbo = new DbConnect();
		dbo.openConnection();
		try{
		
		dbo.st.executeUpdate("Update puser SET usertype='"+userType+"' where uid= "+this.userId+"");
		
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		dbo.closeConnection();
		this.userType = userType;}
	
	//DOUBLE PARA SET 
		public void setUserName(int userId,String uName){
		DbConnect dbo = new DbConnect();
		dbo.openConnection();
		
		try{
		
		dbo.st.executeUpdate("Update puser SET username='"+uName+"' where uid= "+userId+"");
		
		}catch(Exception ex){
			System.out.println(ex.getMessage());
			
			
		}
		
		dbo.closeConnection();
		
		
	
	
	}
	public void setUserPass(int userId,String userPass){
		DbConnect dbo = new DbConnect();
		dbo.openConnection();
		
			try{
			
			dbo.st.executeUpdate("Update puser SET password='"+userPass+"' where uid= "+userId+"");
		
		}catch(Exception ex){
			System.out.println(ex.getMessage());
			
			
		}
		
		dbo.closeConnection();
		}


	public void setUserAddress(int userId,String userAddress){
	
		DbConnect dbo = new DbConnect();
		dbo.openConnection();
		
		try{
		
		dbo.st.executeUpdate("Update puser SET address='"+userAddress+"' where uid= "+userId+"");
		
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
			dbo.closeConnection();
			
		}
	
	
	
	public void setUserNumber(int userId,String userNumber){
		DbConnect dbo = new DbConnect();
		dbo.openConnection();
		
		try{
		
		dbo.st.executeUpdate("Update puser SET mobile_number='"+userNumber+"' where uid= "+userId+"");
		
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		dbo.closeConnection();
		
		
		
		}
	public void setUserType(int userId,int userType){
		DbConnect dbo = new DbConnect();
		dbo.openConnection();
		try{
		
		dbo.st.executeUpdate("Update puser SET usertype='"+userType+"' where uid= "+userId+"");
		
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		dbo.closeConnection();
		}
	
	//-----
	
	public int getUserId(){ return userId;}
	public String getUserName(){ return userName;}
	public String getUserPass(){ return userPass;}
	public String getUserAddress(){ return userAddress;}
	public String getUserNumber(){ return userNumber;}
	public int getUserType(){return userType;}
	
	
	
	public void show(){
		System.out.println("ID: "+userId+userName+userAddress+userNumber+userType);
	}
	
	
}