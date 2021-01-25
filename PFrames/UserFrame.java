package PFrames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import PDBConnect.*;
import PEntity.*;


public class UserFrame extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1;
	private Font font1,font,font2,font3;
	private JLabel logoL,userLabel,patientIdL,headerLabel,userIdL,wrongPIDL,wrongUIDL;
	private JTextField userIdF,patientId;
	private JButton logOut;
	private ImageIcon logo,icon;
	private JButton vPtInfo,addNewPt,releasePt,addMed,sellMed,addDoc,gAdminP,setting,delUserB;
	private Puser user;
	private DbConnect dbo;
	private Patient pObj;
	public UserFrame(Puser user){	
		super("User Home");
		
		this.user=user;
		
		
		
		
		
		logo = new ImageIcon(this.getClass().getResource("logo.jpg"));
        icon = new ImageIcon(this.getClass().getResource("icon.jpg"));
		
		
		
		this.setIconImage(icon.getImage());
		this.setLayout(null);
		font1 = new Font("Times New Roman", Font.BOLD, 35);
		font = new Font("Times New Roman", Font.ITALIC, 15);
		font2 =new Font("Roboto",0,15);
		font3 =new Font("Roboto",0,11);
		
		
		headerLabel = new JLabel("LSPC PHARMA");
		headerLabel.setFont(font1);
		headerLabel.setForeground(Color.decode("#45EB7D"));
		headerLabel.setBounds(400,60,350,35);
		this.add(headerLabel);
		
		logoL = new JLabel("",logo,0);
		logoL.setBounds(250,20,150,150);
		this.add(logoL);
		
		wrongPIDL = new JLabel("Invalid Patient ID!");
		wrongPIDL.setFont(font2);
		wrongPIDL.setForeground(Color.RED);
		wrongPIDL.setBounds(220,220,150,20);
		wrongPIDL.setVisible(false);
		this.add(wrongPIDL);
		
		patientIdL = new JLabel("ENTER PATIENT ID: ");
		
		patientIdL.setFont(font2);
		patientIdL.setBounds(20,200,150,20);
		this.add(patientIdL);
		
		wrongUIDL = new JLabel("Invalid User ID!");
		wrongUIDL.setFont(font2);
		wrongUIDL.setForeground(Color.RED);
		wrongUIDL.setBounds(550,333,150,20);
		wrongUIDL.setVisible(false);
		this.add(wrongUIDL);
		
		userIdL = new JLabel("ENTER USER ID: ");
		userIdL.setFont(font2);
		userIdL.setBounds(680,300,200,20);
		this.add(userIdL);
		
		userLabel = new JLabel("Logged in As: "+user.getUserName()+" User ID:"+user.getUserId());
		userLabel.setBounds(620,480,400,20);
		userLabel.setFont(font);
		this.add(userLabel);
		
		//TF
		
		patientId = new JTextField("");
		patientId.setBounds(20,220,200,20);
		this.add(patientId);
		
		userIdF = new JTextField("");
		userIdF.setBounds(680,330,200,20);
		this.add(userIdF);
		
		//Button
		
		sellMed = new JButton("Sell Medicine");
		sellMed.setBounds(20,250,200,40);
		sellMed.setFont(font2);
		sellMed.setBackground(Color.decode("#47CEC0"));
		this.add(sellMed);
		sellMed.addActionListener(this);
		
		vPtInfo = new JButton("View Patient Details");
		vPtInfo.setBounds(20,300,200,40);
		vPtInfo.setFont(font2);
		vPtInfo.setBackground(Color.decode("#47CEC0"));
		this.add(vPtInfo);
		vPtInfo.addActionListener(this);
		
		
		addNewPt = new JButton("Admit Patient");
		addNewPt.setBounds(20,350,200,40);
		addNewPt.setFont(font2);
		addNewPt.setBackground(Color.decode("#47CEC0"));
		this.add(addNewPt);
		addNewPt.addActionListener(this);
		
		
		releasePt = new JButton("Release Patient");
		releasePt.setBounds(20,400,200,40);
		releasePt.setFont(font2);
		releasePt.setBackground(Color.decode("#47CEC0"));
		this.add(releasePt);
		releasePt.addActionListener(this);
		
		
		addMed = new JButton("ADD NEW MEDICINE");
		addMed.setBounds(680,200,200,40);
		addMed.setFont(font2);
		addMed.setBackground(Color.decode("#47CEC0"));
		this.add(addMed);
		addMed.addActionListener(this);
		
		
		
		addDoc = new JButton("ADD NEW DOCTOR");
		addDoc.setBounds(680,250,200,40);
		addDoc.setFont(font2);
		addDoc.setBackground(Color.decode("#47CEC0"));
		this.add(addDoc);
		addDoc.addActionListener(this);
		
		
		
		
		
		gAdminP = new JButton("Give Admin Privilege");
		gAdminP.setBounds(680,360,200,40);
		gAdminP.setFont(font2);
		gAdminP.setBackground(Color.decode("#47CEC0"));
		this.add(gAdminP);
		gAdminP.addActionListener(this);
		
		setting = new JButton("SETTING");
		setting.setBounds(320,550,200,50);
		setting.setFont(font2);
		setting.setBackground(Color.decode("#C4CBD3"));
		this.add(setting);
		setting.addActionListener(this);
		
		delUserB = new JButton("REMOVE USER");
		delUserB.setBounds(680,410,200,50);
		delUserB.setBackground(Color.decode("#9E1201"));
		delUserB.setFont(font2);
		this.add(delUserB);
		delUserB.addActionListener(this);
		
		
		
		
		logOut = new JButton("LOG OUT");
		logOut.setBackground(Color.decode("#8F949D"));
		logOut.setBounds(710,520,200,50);
		this.add(logOut);
		logOut.addActionListener(this);
		
		
		//CHECK USER TYPE
		if(user.getUserType()==0){
			disbleThings();
		}
		
		//Finish Up
		
		
		this.getContentPane().setBackground(Color.decode("#FDFEFF"));
		this.setVisible(true);
        this.setSize(950, 650);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		
	}
	
	public void actionPerformed(ActionEvent e){
		
		int ptId=0;
		int uId=0;
		if(e.getActionCommand().equals("Sell Medicine")){
			boolean ptExist=false;
			wrongPIDL.setVisible(false);
			try{
			 ptId = 	Integer.parseInt(patientId.getText());
				
			 ptExist = checkIfPatientExist(ptId);
			}catch(Exception ex){
				ptExist=false;
			}
			
			if(ptExist){
			pObj = new Patient();
			pObj = pObj.getPatient(ptId);
			SellFrame sObj = new SellFrame(user,pObj);
			this.dispose();
			}
			else{
				wrongPIDL.setVisible(true);
				
			}
			
			
			
		}
		
		if(e.getActionCommand().equals("View Patient Details")){
			
			 boolean ptExist=false;
			wrongPIDL.setVisible(false);
			try{
			 ptId = 	Integer.parseInt(patientId.getText());
				
			 ptExist = checkIfPatientExist(ptId);
			}catch(Exception ex){
				ptExist=false;
			}
			
			if(ptExist){
			pObj = new Patient();
			pObj = pObj.getPatient(ptId);
			String msgP = "PATIENT ID:" + pObj.getPid() +"\nPATIENT NAME: "+pObj.getPatientName()+"\nROOM NO: "+pObj.getPatientRoomNo()+"\nMOBILE NUMBER: "+pObj.getPatientPhone()+"\nPATIENT ADDRESS: "+pObj.getPatientAddress(); 
			JOptionPane.showMessageDialog(this,msgP,"PATIENT INFORMATION BOX",JOptionPane.INFORMATION_MESSAGE);
			
			}
			else{
				wrongPIDL.setVisible(true);
				
			}
			
			
			
			
			
		}
		
		
		if(e.getActionCommand().equals("Admit Patient")){
			
			PatientAdmit ptAF = new PatientAdmit(user);
			
			this.dispose();
			
			
			
		}
		
		if(e.getActionCommand().equals("Release Patient")){
			
			boolean ptExist=false;
			wrongPIDL.setVisible(false);
			try{
			 ptId = 	Integer.parseInt(patientId.getText());
				
			 ptExist = checkIfPatientExist(ptId);
			}catch(Exception ex){
				ptExist=false;
			}
			
			if(ptExist){
			pObj = new Patient();
			pObj = pObj.getPatient(ptId);
			
			String dsql = "DELETE FROM patient WHERE pid ="+ptId+" ";
			
			try{
				
			dbo.st.executeUpdate(dsql);	
				
			}catch(Exception ex){System.out.println(ex.getMessage());}
			
			
			JOptionPane.showMessageDialog(this,"PATIENT RELEASED\nPATIENT ID: "+ptId+"","PATIENT RELEASED",JOptionPane.INFORMATION_MESSAGE);
			
			
			
			}
			else{
				wrongPIDL.setVisible(true);
				
			}
			
			
			
			
		
			
			
			
			
		}
		
		
		if(e.getActionCommand().equals("SETTING")){
				
				SettingF newS = new SettingF(user);
				this.dispose();
				
				
				
				
			}
			
		if(e.getActionCommand().equals("ADD NEW MEDICINE")){
				
				AddMed newAm = new AddMed(user);
				this.dispose();
				
				
				
				
			}	
			
		
		if(e.getActionCommand().equals("ADD NEW DOCTOR")){
				
				AddDoc newDoc = new AddDoc(user);
				this.dispose();
				
				
				
				
			}	
			
		if(e.getActionCommand().equals("Give Admin Privilege")){
			
			
			boolean utExist=false;
			wrongUIDL.setVisible(false);
			
			
			try{
			 
			 uId = 	Integer.parseInt(userIdF.getText());
				
			 utExist = checkIfUserExist(uId);
			}catch(Exception ex){
				utExist=false;
			}
			
			
			if(uId!=this.user.getUserId()){
			
			if(utExist){
			
			
			String dsql = "Update puser SET userType = 1 WHERE uid ="+uId+" ";
			
			try{
				
			dbo.st.executeUpdate(dsql);	
				
			}catch(Exception ex){System.out.println(ex.getMessage());}
			
			
			JOptionPane.showMessageDialog(this,"Privilege Given\nTO USER ID: "+uId+"","PRIVILEGE SUCCESS",JOptionPane.INFORMATION_MESSAGE);
			
			
			
			}
			else{
				wrongUIDL.setVisible(true);
				
			}
			}else{JOptionPane.showMessageDialog(this,"CANT MODIFY CURRENT USER","ERROR",JOptionPane.ERROR_MESSAGE); }
			

			
			
			
			
			
		}	
		

		if(e.getActionCommand().equals("REMOVE USER")){
			
			
			boolean utExist=false;
			wrongUIDL.setVisible(false);
			
			
			try{
			 
			 uId = 	Integer.parseInt(userIdF.getText());
				
			 utExist = checkIfUserExist(uId);
			}catch(Exception ex){
				utExist=false;
			}
			
			
			if(uId!=this.user.getUserId()){
			
			if(utExist){
			
			System.out.println("I am Here");
			String dsql = "DELETE  From puser WHERE uid ="+uId+" ";
			
			try{
				
			dbo.st.executeUpdate(dsql);	
				
			}catch(Exception ex){System.out.println(ex.getMessage());}
			
			
			JOptionPane.showMessageDialog(this,"USER DELETED\nDELETED USER ID: "+uId+"","DELETION SUCCESS",JOptionPane.INFORMATION_MESSAGE);
			
			
			
			}
			else{
				wrongUIDL.setVisible(true);
				
			}
			}else{JOptionPane.showMessageDialog(this,"CANT DELETE CURRENT USER","ERROR",JOptionPane.ERROR_MESSAGE); }
			

			
			
			
			
			
		}	

		
			
			
		
		if(e.getActionCommand().equals("LOG OUT")){
			
			this.dispose();
			
			Login lObj=new Login();
			
		}		
		
		
		
		
		
	}
	
public boolean checkIfUserExist(int userId){
	
	boolean isExist=false;
	String sql = "Select uid from puser where uid="+userId+"";
	dbo = new DbConnect();
	dbo.openConnection();
	try{
		dbo.result = dbo.st.executeQuery(sql);
		while(dbo.result.next()){
			isExist = true;
		}
		
	}catch(Exception ex){}
		
	return isExist;	
	
	
}	
	
public void disbleThings(){
	
	
	this.addMed.setBackground(Color.decode("#FFFFFF"));
	this.addMed.setEnabled(false);
	this.gAdminP.setBackground(Color.decode("#FFFFFF"));
	this.gAdminP.setEnabled(false);
	this.delUserB.setBackground(Color.decode("#FFFFFF"));
	this.delUserB.setEnabled(false);
	this.addDoc.setBackground(Color.decode("#FFFFFF"));
	this.addDoc.setEnabled(false);
	this.userIdL.setVisible(false);
	this.userIdF.setVisible(false);
	
	
	
}

	
public boolean checkIfPatientExist(int patientId){
	boolean isExist=false;
	String sql = "Select pid from patient where pid="+patientId+"";
	dbo = new DbConnect();
	dbo.openConnection();
	try{
		dbo.result = dbo.st.executeQuery(sql);
		while(dbo.result.next()){
			isExist = true;
		}
		
	}catch(Exception ex){}
		
	return isExist;	
}	
	
	
}