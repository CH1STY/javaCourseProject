package PFrames;

import javax.swing.*;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import PDBConnect.*;
import PEntity.*;


public class AddDoc extends JFrame implements ActionListener{
	
	private JLabel heading,nameL,salaryL;
	private JTextField nameF,salaryF;
	private Font font,font2;
	private ImageIcon icon;
	private JButton BackButton,AddButton;
	private Puser user;
	private DbConnect dbo;
	
	
	
	public AddDoc(Puser user){
	super("DOCTOR ADDING PAGE");
	this.setLayout(null);
	this.user = user;
	dbo = new DbConnect();
	font = new Font("Times New Roman", 1, 32);
	font2 =new Font("Roboto",0,15);
	icon = new ImageIcon(this.getClass().getResource("icon.jpg"));
	this.setIconImage(icon.getImage());
	
	//JLABEL
	
	
	heading = new JLabel("DOCTOR ADDING FORM");
	heading.setBounds(15,20,430,50);
	heading.setForeground(Color.decode("#008081"));
	heading.setFont(font);
	this.add(heading);
	
	nameL = new JLabel ("DOCTOR NAME ");
	nameL.setFont(font2);
	nameL.setBounds (15,125,130,25);
	this.add(nameL);
	
	salaryL = new JLabel ("DOCTORS SALARY");
	salaryL.setFont(font2);
	salaryL.setBounds (15,205,130,25);
	this.add(salaryL);
	
	
	
	//TF
	
		nameF = new JTextField("");
		nameF.setBounds(150,125,200,25);
		this.add(nameF);
		
		salaryF = new JTextField("");
		salaryF.setBounds(150,205,200,25);
		this.add(salaryF);
	
		
		
	
	
	//JButton
	
	AddButton = new JButton("ADD");
	AddButton.setBackground(Color.decode("#47ab68"));
	AddButton.setFont(font2);
	AddButton.setBounds(145,450,140,50);
	this.add(AddButton);
	AddButton.addActionListener(this);
	
	
	BackButton= new JButton("BACK");
	BackButton.setBackground(Color.decode("#4385F3"));
	BackButton.setFont(font2);
	BackButton.setBounds(295,450,100,50);
	this.add(BackButton);
	BackButton.addActionListener(this);
	
	
	//FINISH
		
	this.setVisible(true);
    this.setSize(475, 550);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setLocationRelativeTo(null);
		
		
	}
	
	public void actionPerformed(ActionEvent e){
		
	if(e.getActionCommand().equals("ADD")){
		boolean isValid;
		isValid=true;
		
		
		String name="" ;
		int salary = 0;
		
		
		name = nameF.getText();
		try{
			salary = Integer.parseInt(salaryF.getText());
			
				
			}catch (Exception ex){
				isValid = false;
			}
			
		if(!isValid || name.equals("")  ||  salaryF.getText().equals("") ){
			
			JOptionPane.showMessageDialog(this,"INVALID REQUEST!", "ERROR!!", JOptionPane.ERROR_MESSAGE);

		
		}
		else
		{
			String sql = "Insert into doctor(doctorName,salary) Values('"+name+"',"+salary+") ";
			String getId = "Select did from doctor";
			dbo.openConnection();
			try{
				
				dbo.st.executeUpdate(sql);
				dbo.result = dbo.st.executeQuery(getId);
				
				while(dbo.result.next()){getId = dbo.result.getString(1);}
				
				
			}catch(Exception ex){
			
			
			
			}			
			JOptionPane.showMessageDialog(this,"Doctor Added\nNew Doctor ID:  "+getId+" ", "ADDING SUCCESSFUL", JOptionPane.INFORMATION_MESSAGE);
			
			dbo.closeConnection();
			
			nameF.setText("");
			salaryF.setText("");
		
			
		}
			
			
		
		
		
		
		
	}	
		
	if(e.getActionCommand().equals("BACK")){
			
			UserFrame uF = new UserFrame(user);
			
			this.dispose();
			
			
		}	
		
		
		
		
	}
	
}