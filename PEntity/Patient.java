package PEntity;

import PDBConnect.*;

public class Patient{
	
	private int pid;
	private String patientName;
	private int roomNo;
	private String patientPhone;
	private String patientAddress;
	private DbConnect dbo;
	
	public Patient(){
		
		dbo = new DbConnect();
	}
	public Patient(int pid,String patientName,int roomNo,String patientPhone,String patientAddress){
		
		this.pid = pid;
		this.patientName=patientName;
		this.roomNo=roomNo;
		this.patientPhone=patientPhone;
		this.patientAddress=patientAddress;		
		
	}
	
	
	
	public void setPatientName(String patientName){
		
		dbo.openConnection();
		String sql = "Update patient Set patientName ='"+patientName+"' where pid = "+this.pid+" ";
		try{
		dbo.st.executeUpdate(sql);
		}catch(Exception ex){}
		dbo.closeConnection();
		
		this.patientName = patientName;
		
		
	}
	
	public void setRoomNo(int roomNo){
		
		dbo.openConnection();
		String sql = "Update patient Set roomNo ="+roomNo+" where pid = "+this.pid+" ";
		try{
		dbo.st.executeUpdate(sql);
		}catch(Exception ex){}
		dbo.closeConnection();
		
		this.roomNo = roomNo;
		
		
	}
	
	
	public void setPatientPhone(String patientPhone){
		
		dbo.openConnection();
		String sql = "Update patient Set patientPhone ='"+patientPhone+"' where pid = "+this.pid+" ";
		try{
		dbo.st.executeUpdate(sql);
		}catch(Exception ex){}
		dbo.closeConnection();
		
		this.patientPhone = patientPhone;
		
		
	}
	
	public void setPatientAddress(String patientAddress){
		
		dbo.openConnection();
		String sql = "Update patient Set patientAddress ='"+patientAddress+"' where pid = "+this.pid+" ";
		try{
		dbo.st.executeUpdate(sql);
		}catch(Exception ex){}
		dbo.closeConnection();
		
		this.patientAddress = patientAddress;
		
		
	}
	
	
	public int getPid(){return this.pid;}
	public String getPatientName(){return this.patientName;}
	public int getPatientRoomNo(){return this.roomNo;}
	public String getPatientPhone(){return this.patientPhone;}
	public String getPatientAddress(){return this.patientAddress;}
	
	public Patient getPatient(int pid){
		
		
		String patientName="" ;
		int roomNo=0;
		String patientPhone="";
		String patientAddress=" ";
		dbo.openConnection();
		try{
							
							dbo.result= dbo.st.executeQuery("Select * from patient where pid ='"+pid+"'");
							if(dbo.result.next()){
								
								patientName = dbo.result.getString(2);
								roomNo = Integer.parseInt(dbo.result.getString(3));
								patientPhone = dbo.result.getString(4);
								patientAddress = dbo.result.getString(5);
							}
							
							
							
							
							
						
						}catch(Exception ex){
							
							System.out.print(ex.getMessage());
						}
		
		
		dbo.closeConnection();
		return new Patient(pid,patientName,roomNo,patientPhone,patientAddress);
		
	}
	
	
	
	
}