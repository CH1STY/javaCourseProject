package PFrames;

import javax.swing.*;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import PDBConnect.*;
import PEntity.*;



public class PatientAdmit extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 1;
	private Font font,font2,font3;
	private JLabel pAdmitL,nameL,roomNoL,phoneL,addressL,imageL,isNameL,isphoneL,isRoomL,isAddressL;
	private JTextArea addressF;
	private JTextField nameF,roomNoF,phoneF;
	private	ImageIcon icon,logo;
	private	JButton AddPButton,BackButton;
	private Puser user;
	private JComboBox numberBox;
	public PatientAdmit(Puser user){
		
		super("PATIENT ADMISSION");
		this.setLayout(null);
		
		
		
		Border border = BorderFactory.createLineBorder(Color.decode("#989ca0"), 1);		
		
		this.user=user;
		font = new Font("Times New Roman", Font.BOLD, 40);
		font2 =new Font("Roboto",1,12);
		font3 =new Font("Roboto",0,11);
	
		logo = new ImageIcon(this.getClass().getResource("Slogan.jpg"));
        icon = new ImageIcon(this.getClass().getResource("icon.jpg"));
		
		this.setIconImage(icon.getImage());
		
		imageL = new JLabel("",logo,0);
		imageL.setBounds(20,125,463,262);
		this.add(imageL);
	
		
		
		
		//Label and TF
		
		pAdmitL = new JLabel("PATIENT FORM");
		pAdmitL.setFont(font);
		pAdmitL.setBounds(500,25,350,50);
        this.add(pAdmitL);
		
		nameL = new JLabel("Name");
		nameL.setFont(font2);
		nameL.setBounds(510,100,100,25);
		this.add(nameL);
		
		roomNoL = new JLabel("Room Number");
		roomNoL.setFont(font2);
		roomNoL.setBounds(510,160,100,25);
		this.add(roomNoL);
		
		
		addressL = new JLabel("Address");
		addressL.setFont(font2);
		addressL.setBounds(510,280,100,25);
		this.add(addressL);
		
		phoneL = new JLabel("Mobile Number");
		phoneL.setFont(font2);
		phoneL.setBounds(510,380,200,25);
		this.add(phoneL);
		//--Message Labels
		
		isNameL = new JLabel("Must Provide Name!!");
		isNameL.setBounds(712,132,150,20);
		isNameL.setFont(font3);
		isNameL.setForeground(Color.decode("#D43334"));
		isNameL.setVisible(false);
		this.add(isNameL);
		
		isRoomL = new JLabel("Provide Valid Room No!");
		isRoomL.setBounds(712,182,150,20);
		isRoomL.setFont(font3);
		isRoomL.setForeground(Color.decode("#D43334"));
		isRoomL.setVisible(false);
		this.add(isRoomL);
	
		
		isAddressL = new JLabel("Please Provide Address!");
		isAddressL.setBounds(712,322,150,20);
		isAddressL.setFont(font3);
		isAddressL.setForeground(Color.decode("#D43334"));
		isAddressL.setVisible(false);
		this.add(isAddressL);
		
		
		isphoneL = new JLabel("Provide Valid Number!");
		isphoneL.setBounds(715,410,150,20);
		isphoneL.setFont(font3);
		isphoneL.setForeground(Color.decode("#D43334"));
		isphoneL.setVisible(false);
		this.add(isphoneL);
		
		
		//TF--------
		
		nameF = new JTextField("");
		nameF.setBounds(510,125,200,30);
		this.add(nameF);
		
		roomNoF = new JTextField("");
		roomNoF.setBounds(510,185,200,30);
		this.add(roomNoF);
		
		
		addressF = new JTextArea("");
		addressF.setLineWrap(true);
		addressF.setBorder(border);
		addressF.setBounds(510,315,200,60);
		this.add(addressF);
		
		
		
		String forBox[]={"019","017","018","016","013","014"};
		numberBox = new JComboBox<String>(forBox);
		numberBox.setBounds (510,415,50,20);
		this.add(numberBox);
		
		phoneF = new JTextField("");
		phoneF.setBounds(565,415,145,20);
		this.add(phoneF);
		
		//---------
		
	
		//JBUTTONS
		
		AddPButton = new JButton("ADD PATIENT");
		AddPButton.setBackground(Color.decode("#47ab68"));
		AddPButton.setFont(font2);
		AddPButton.setBounds(510,485,200,40);
		this.add(AddPButton);
		AddPButton.addActionListener(this);
		
		
		BackButton= new JButton("CANCLE ADMISSION");
		BackButton.setFont(font2);
		BackButton.setBackground(Color.decode("#4385F3"));
		BackButton.setBounds(510,530,200,40);
		this.add(BackButton);
		BackButton.addActionListener(this);
	
	
		//Finish Up
		
		this.getContentPane().setBackground(Color.decode("#FDFEFF"));
		this.setVisible(true);
        this.setSize(950, 650);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
	}
	
	
	
	
	
	
	public void actionPerformed(ActionEvent e){
		
		boolean isName,isAddress,isNumber,isVNumber,isRoomNo;
		isVNumber= true;
		isRoomNo = true;
		int roomNo=0;
		String name = nameF.getText();
		
		String address = addressF.getText();
		String mNumber = (String)numberBox.getSelectedItem();
		mNumber = mNumber.concat(phoneF.getText());
		
		if(e.getActionCommand().equals("ADD PATIENT")){
			
			
			try {
			int i = Integer.parseInt(phoneF.getText());
		}catch(Exception ex){
			isVNumber = false;
		}
		
		try {
			roomNo = Integer.parseInt(roomNoF.getText());
		}catch(Exception ex){
			isRoomNo = false;
		}
		
			
			if(name.equals(""))
			{	
				isNameL.setVisible(true);
				isName= false;
			}else {
					isNameL.setVisible(false);
					isName=true;}
			
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
				
				isphoneL.setVisible(true);
				isNumber=false;
				
			}else
			{
				isphoneL.setVisible(false);
				isNumber=true;
			}

			
			if(roomNo>9999 || roomNo <1000){
				
				isRoomNo = false;
				isRoomL.setVisible(true);
			}else{
				isRoomNo = true;
				isRoomL.setVisible(false);
				
			}
			
			
			if(isName && isAddress && isNumber && isVNumber && isRoomNo){
				
				DbConnect dbo=new DbConnect();
				
				int pid=0;
				
				dbo.openConnection();
				
				String inserTPtSql ="Insert into patient(patientName,roomNo,patientPhone,patientAddress) values('"+name+"',"+roomNo+",'"+mNumber+"','"+address+"')";
				String sql2 = "Select pid from patient";
				
				try {
					
					dbo.st.executeUpdate(inserTPtSql);
					dbo.result = dbo.st.executeQuery(sql2);
					
					while(dbo.result.next()){
						
						pid = Integer.parseInt(dbo.result.getString(1));
						
					}
					
				}catch(Exception ex){
					System.out.println(ex.getMessage());
				}
				
				dbo.closeConnection();
				
				
				JOptionPane.showMessageDialog(this, "DONE\nNew PATIENT ID: "+pid+"\n", "PATIENT ADDED", JOptionPane.INFORMATION_MESSAGE);
				
				
				UserFrame fObj=new UserFrame(user);
				
				
				this.dispose();
				
				
				
				
			}
			
			
			
			
			
			
			
			
			
		}
		
		
		
		
		
		
		
		if(e.getActionCommand().equals("CANCLE ADMISSION")){
			
			UserFrame uF = new UserFrame(user);
			
			this.dispose();
			
			
		}
		
		
		
		
		
	}
}