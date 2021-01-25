package PFrames;

import javax.swing.*;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import PDBConnect.*;
import PEntity.*;


public class RegistrationFrame extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1;
	
	private Font font,font2,font3;
	
	private JLabel imgLabel,nameL,passL,cPassL,addressL,numberL,signUpL,imageL,isNameL,isAddressL,isPassL,isCpassL,isNumberL;
	private JTextField nameF,numberF;
	private JTextArea addressF;
	private JPasswordField passF,cPassF;
	private JCheckBox agree;
	private JComboBox<String> numberBox;
	private JButton back,signUpB;
	public 	ImageIcon logo,icon;
	
	
	public RegistrationFrame(){
		
		super("LSPC Registration");
		this.setLayout(null);
		
		Border border = BorderFactory.createLineBorder(Color.decode("#989ca0"), 1);
		font = new Font("Times New Roman", Font.BOLD, 40);
		font2 =new Font("Roboto",1,12);
		font3 =new Font("Roboto",0,11);
		logo = new ImageIcon(this.getClass().getResource("Slogan.jpg"));
        icon = new ImageIcon(this.getClass().getResource("icon.jpg"));
		
		this.setIconImage(icon.getImage());
		
		imageL = new JLabel("",logo,0);
		imageL.setBounds(20,125,463,262);
		this.add(imageL);
		
		signUpL = new JLabel("Sign Up");
		signUpL.setFont(font);
		signUpL.setBounds(500,25,350,50);
        this.add(this.signUpL);
		
		nameL = new JLabel("Name");
		nameL.setFont(font2);
		nameL.setBounds(510,100,100,25);
		this.add(nameL);
		
		passL = new JLabel("Password");
		passL.setFont(font2);
		passL.setBounds(510,160,100,25);
		this.add(passL);
		
		
		cPassL = new JLabel("Confirm Password");
		cPassL.setFont(font2);
		cPassL.setBounds(510,220,200,25);
		this.add(cPassL);
		
		addressL = new JLabel("Address");
		addressL.setFont(font2);
		addressL.setBounds(510,280,100,25);
		this.add(addressL);
		
		numberL = new JLabel("Mobile Number");
		numberL.setFont(font2);
		numberL.setBounds(510,380,200,25);
		this.add(numberL);
		//--Message Labels
		
		isNameL = new JLabel("Must Provide Name!!");
		isNameL.setBounds(712,132,150,20);
		isNameL.setFont(font3);
		isNameL.setForeground(Color.decode("#D43334"));
		isNameL.setVisible(false);
		this.add(isNameL);
		
		isPassL = new JLabel("Must Provide Password!");
		isPassL.setBounds(712,182,150,20);
		isPassL.setFont(font3);
		isPassL.setForeground(Color.decode("#D43334"));
		isPassL.setVisible(false);
		this.add(isPassL);
		
		isCpassL = new JLabel("Passwords Doesn't Match!");
		isCpassL.setBounds(712,242,150,20);
		isCpassL.setFont(font3);
		isCpassL.setForeground(Color.decode("#D43334"));
		isCpassL.setVisible(false);
		this.add(isCpassL);
		
		isAddressL = new JLabel("Please Provide Address!");
		isAddressL.setBounds(712,322,150,20);
		isAddressL.setFont(font3);
		isAddressL.setForeground(Color.decode("#D43334"));
		isAddressL.setVisible(false);
		this.add(isAddressL);
		
		
		isNumberL = new JLabel("Provide Valid Number!");
		isNumberL.setBounds(715,410,150,20);
		isNumberL.setFont(font3);
		isNumberL.setForeground(Color.decode("#D43334"));
		isNumberL.setVisible(false);
		this.add(isNumberL);
		
		
		//TF--------
		
		nameF = new JTextField("");
		nameF.setBounds(510,125,200,30);
		this.add(nameF);
		
		passF = new JPasswordField("");
		passF.setBounds(510,185,200,30);
		this.add(passF);
		
		cPassF = new JPasswordField("");
		cPassF.setBounds(510,245,200,30);
		this.add(cPassF);
		
		
		addressF = new JTextArea("");
		addressF.setLineWrap(true);
		addressF.setBorder(border);
		addressF.setBounds(510,315,200,60);
		this.add(addressF);
		
		
		
		String forBox[]={"019","017","018","016","013","014"};
		numberBox = new JComboBox<String>(forBox);
		numberBox.setBounds (510,415,50,20);
		this.add(numberBox);
		
		numberF = new JTextField("");
		numberF.setBounds(565,415,145,20);
		this.add(numberF);
		//AGREE CHECK BOX
		agree = new JCheckBox("I AGREE WITH ALL TERMS OF LSPC");
		agree.setFont(font2);
		agree.setBackground(Color.decode("#FDFEFF"));
		agree.setBounds(510,445,250,15);	
		this.add(agree);
		
		agree.addActionListener(this);
		
		//BUTTONS
			
		back = new JButton("BACK TO LOGIN");
		back.setFont(font2);
		back.setBackground(Color.decode("#4385F3"));
		back.setBounds(510,530,200,40);
		this.add(back);
		back.addActionListener(this);
		
		
		signUpB = new JButton("CREATE NEW ACCOUNT");
		signUpB.setBackground(Color.decode("#FFFFFF"));
		signUpB.setEnabled(false);
		signUpB.setFont(font2);
		signUpB.setBounds(510,485,200,40);
		this.add(signUpB);
		signUpB.addActionListener(this);
		
		//FINISH UP
		this.getContentPane().setBackground(Color.decode("#FDFEFF"));
		this.setVisible(true);
        this.setSize(950, 650);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		
	}
	
	public void actionPerformed(ActionEvent e){
		
		boolean isName,isPass,isCpass,isAddress,isNumber,isVNumber;
		isVNumber= true;
		
		String name = nameF.getText();
		String pass = new String(passF.getPassword());
		String cPass =new String(cPassF.getPassword());
		String address = addressF.getText();
		String mNumber = (String)numberBox.getSelectedItem();
		mNumber = mNumber.concat(numberF.getText());
		
		
		try {
			int i = Integer.parseInt(numberF.getText());
		}catch(Exception ex){
			isVNumber = false;
		}
		
		if(agree.isSelected()){
			signUpB.setEnabled(true);
			signUpB.setBackground(Color.decode("#47ab68"));
			//System.out.println(mNumber);
		}
		else {
			signUpB.setBackground(Color.decode("#FFFFFF"));
			signUpB.setEnabled(false);
			
		}
		
		
		if(e.getActionCommand().equals("CREATE NEW ACCOUNT")){
			
			if(name.equals(""))
			{	
				isNameL.setVisible(true);
				isName= false;
			}else {
					isNameL.setVisible(false);
					isName=true;}
			
			if(pass.equals(""))
			{
				isPassL.setVisible(true);
				isPass = false;
			}else
			{
				isPassL.setVisible(false);
				isPass=true;
			}
			
			if(!cPass.equals(pass)){
				isCpassL.setVisible(true);
				isCpass = false;
			}else{
				isCpassL.setVisible(false);
				isCpass=true;
			}
			
			if(address.equals("")){
				isAddressL.setVisible(true);
				isAddress = false;
				
			}
			else
			{
				isAddressL.setVisible(false);
				isAddress=true;
			}
			
			if(mNumber.length()!=11 || !isVNumber){
				
				isNumberL.setVisible(true);
				isNumber=false;
				
			}else
			{
				isNumberL.setVisible(false);
				isNumber=true;
			}

			if(isName && isPass && isCpass && isAddress && isNumber && isVNumber){
				DbConnect dbo = new DbConnect();
				dbo.openConnection();
				
				
				String sql = ("Insert Into puser(username,password,address,mobile_number) Values('"+name+"','"+pass+"','"+address+"','"+mNumber+"')");
				try{
				
				dbo.st.executeUpdate(sql);
				
				}catch (Exception ex){
					System.out.println(ex.getMessage());
				}
				int userId=0;
				int userType=0;
				
				sql = ("Select uid,userType from puser where username = '"+name+"' AND password = '"+pass+"' AND address = '"+address+"' AND mobile_number = '"+mNumber+"' ");
				try {
					
					dbo.result = dbo.st.executeQuery(sql);
					
					while(dbo.result.next()){
						userId = Integer.parseInt(dbo.result.getString(1));
						userType = Integer.parseInt(dbo.result.getString(2));
					}
					
					 
						
					//System.out.println("ID"+userId+userType);	
					
				}catch(Exception ex){
					System.out.println(ex.getMessage());
				}
				
				dbo.closeConnection();
				Puser userObj = new Puser(userId,name,pass,address,mNumber,userType);
				UserFrame uFrameObj= new UserFrame(userObj);
				JOptionPane.showMessageDialog(null,"USER CREATED USER ID: "+userId);
				
				this.dispose();
			}

			
			
			
			
		}
		
		
		
		
		if(e.getActionCommand().equals("BACK TO LOGIN")){
			Login obj = new Login();
			
			this.dispose();
			
		}
		
		
	}
	
	
}
