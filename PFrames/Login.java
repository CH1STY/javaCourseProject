package PFrames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import PDBConnect.*;
import PEntity.*;


public class Login extends JFrame implements ActionListener
{
	private static final long serialVersionUID = 1;
    private Font font,font2,font3;
    private JLabel loginL;
    private JLabel imageLabel,useridL,passL,useridMsg,passMsg,creditL;
    private JTextField useridF;
    private JPasswordField passF;
    private JButton loginB;
    private JButton registrationB;
    private ImageIcon logo,icon;
	private DbConnect dbo;
	
    public Login() {
        super("PHARMACY LOGIN");
        font = new Font("Arial", Font.BOLD, 50);
		
		font2 =new Font("Roboto",0,11);
		font3 = new Font("Roboto",1,11);
		logo = new ImageIcon(this.getClass().getResource("Slogan.jpg"));
        icon = new ImageIcon(this.getClass().getResource("icon.jpg"));
		
		this.setIconImage(icon.getImage());
		
		this.setLayout(null);
        
		
		
		creditL = new JLabel ("CREATED BY -CHISTY-  FARDIT LAMIA PROKITY");
		creditL.setFont(new Font("ROBOTO",Font.ITALIC,9));
		creditL.setBounds(720,470,200,25);
		this.add(creditL);
		
		
		
		imageLabel = new JLabel("",logo,0);
		imageLabel.setBounds(20,100,463,262);
		this.add(imageLabel);
		
		loginL = new JLabel("WELCOME!");
		loginL.setFont(font);
		loginL.setBounds(455,80,350,50);
        this.add(this.loginL);
		
		
		useridL = new JLabel("User Id");
		useridL.setFont(font2);
		useridL.setBounds(470,190,60,25);
        this.add(this.useridL);
		

		passL = new JLabel("Password");
		passL.setFont(font2);
		passL.setBounds(460,240,60,25);
        this.add(this.passL);
        
		useridMsg = new JLabel ("");
		useridMsg.setForeground(Color.decode("#8a0303"));
		useridMsg.setBounds (680,190,150,25);
		this.add(useridMsg);
		
		passMsg = new JLabel ("");
		passMsg.setForeground(Color.decode("#8a0303"));
		passMsg.setBounds (680,240,150,25);
		this.add(passMsg);
		
        useridF = new JTextField();
		useridF.setFont(font3);
		useridF.setForeground(Color.decode("#3700B3"));
		useridF.setBounds(520, 180, 150, 40);
        this.add(this.useridF);
		
		
		
		
        passF = new JPasswordField();
		passF.setBounds(520, 230, 150, 40);
        this.add(this.passF);
		
		
        loginB = new JButton("LOGIN");
		loginB.setFont(font2);
		loginB.setBounds(520, 280, 150, 40);
        loginB.setBackground(Color.decode("#14adf5"));
        this.add(this.loginB);
        this.getContentPane().setBackground(Color.decode("#FDFEFF"));
		
		registrationB = new JButton("SIGN UP");
		registrationB.setFont(font2);
		registrationB.setBounds(520, 330, 150, 40);
        registrationB.setBackground(Color.decode("#CD4C30"));
        this.add(this.registrationB);
		loginB.addActionListener(this);
		registrationB.addActionListener(this);
        
		
        this.setVisible(true);
        this.setSize(950, 550);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		
		dbo = this.setDB();
		
    }
	
	public void actionPerformed(ActionEvent e)
	{
		
		if(e.getActionCommand().equals("LOGIN")){
			boolean uFound=false;
			boolean pFound=false;
			String userid,password;
			userid = useridF.getText();
			password = new String(passF.getPassword());
			
			if(userid.equals("")){
				uFound = false;
				useridMsg.setVisible(true);
				useridMsg.setText("Please Enter A User ID!!");
				
				
			}
			else{
				
				
				
				try{
					
				dbo.result= dbo.st.executeQuery("Select uid from puser where uid ='"+userid+"'");	
					
				while(dbo.result.next())
				{
					if (dbo.result.getString(1).equals(userid)){
						uFound = true ;
						break;
					}
					
				}
				
				if(uFound){
					//System.out.println ("User Found");
					useridMsg.setVisible(false);
					
				}
				else
				{
					useridMsg.setText("User ID NOT FOUND!!");
					// System.out.println("User Not Found");
				}
				}
				catch(Exception ex){
					
					System.out.println(ex.getMessage());
				}
			
			}
		
			
			if(password.equals("")){
				
				passMsg.setVisible(true);
				passMsg.setText("Please Enter PassWord!!");
			}
			else
			{
			if(uFound){
				try{
				dbo.result= dbo.st.executeQuery("Select password from puser where uid ='"+userid+"'");
				
				
				
				while ( dbo.result.next()){
					if (dbo.result.getString(1).equals(password)){
						pFound = true;
						
						break;
					}
				}	
				}catch(Exception ex){
					System.out.println(ex.getMessage());
					
				}
			
				if (pFound){
						passMsg.setVisible(false);
						// System.out.println("Pass and User Valid");
						int userId = Integer.parseInt(userid);
						String userName="";
						String userAddress="";
						String userNumber="";
						int userType=0;
						
						try{
							
							dbo.result= dbo.st.executeQuery("Select * from puser where uid ='"+userid+"'");
							if(dbo.result.next()){
								
								userName = dbo.result.getString(2);
								userAddress = dbo.result.getString(4);
								userNumber = dbo.result.getString(5);
								userType = dbo.result.getInt(6);
							}
							
							Puser userObj = new Puser(userId,userName,password,userAddress,userNumber,userType);
								
							UserFrame uFrameObj= new UserFrame(userObj);
							
							
							dbo.closeConnection();
							this.dispose();
							
						
						}catch(Exception ex){
							
							
						}
						
						
						
						
				}else{
				passMsg.setVisible(true);
				passMsg.setText("Wrong Password");
				
				}

			}	
			else{
				
				passMsg.setVisible(true);
				passMsg.setText("Enter Valid User ID First");	

				
				
			}
				
			}
			
			
		}
		
		
		if(e.getActionCommand().equals("SIGN UP")){
			
			dbo.closeConnection();
			RegistrationFrame obj = new RegistrationFrame();
			
			this.dispose();			
		}
		
		
	}
	
	public DbConnect setDB(){
		
		DbConnect dbo = new DbConnect();
		
		
		
		
		dbo.openConnection();
		try{
		
			
		dbo.st.execute("CREATE TABLE IF NOT EXISTS pUser( uid bigint NOT NULL AUTO_INCREMENT UNIQUE, username varchar(255) NOT NULL,password varchar(50) NOT NULL  , address varchar(2000), mobile_number varchar (11), usertype int(1) DEFAULT 0,PRIMARY KEY(uid) )");
		
		
		dbo.st.executeUpdate("Insert INTO pUser(uid,username,password,address,mobile_number,usertype) Values(1111,\"Admin\",\"admin\",\"KHILGAON\",\"01911111111\",1)");
		
		}
		catch(Exception e){
			//System.out.println(e.getMessage());
			
		}
		
		
		String pTableSql= "Create table IF NOT EXISTS patient(pid int(20) NOT NULL AUTO_INCREMENT , patientName varchar(20),roomNo int(4),patientPhone varchar(25),patientAddress varchar(50),primary key(pid))";
		String mTableSql= "Create table IF NOT EXISTS medicine(mid int(20) NOT NULL AUTO_INCREMENT,medicineName varchar(30),medicinePrice int(5),stock int(100) ,primary key(mid))";
		String dTableSql= "Create table IF NOT EXISTS doctor(did int(10) NOT NULL AUTO_INCREMENT,salary int(20),doctorName varchar(20),primary key(did))";
		String fileTableSql = "Create Table IF NOT EXISTS fileTable(fileId int not null AUTO_INCREMENT, fileType varchar(10),primary key(fileId))";
		String createDoctorSql = "Insert Into doctor(did,doctorName) values(404,'NO DOCTOR')";
		try{
		dbo.st.execute(pTableSql);
		dbo.st.execute(mTableSql);
		dbo.st.execute(dTableSql);
		dbo.st.execute(fileTableSql);
		dbo.st.executeUpdate(createDoctorSql);
		}catch (Exception ex){
			//System.out.println(ex.getMessage());
		}
		
		
		
		
		return dbo;
		
		
		
	}
	

	
	
	
}