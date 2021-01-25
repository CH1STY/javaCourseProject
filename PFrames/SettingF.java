package PFrames;

import javax.swing.*;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import PDBConnect.*;
import PEntity.*;



public class SettingF extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1;
	private Puser user;
	private JLabel settingL,userIdL,nameL,addressL,mNumberL,userTypeL,oldPL,newPL;
	private JTextField nameF;
	private JTextArea addressF;
	private JPasswordField oldPassF,newPassF;
	private Font font,font2,font3;
	private DbConnect dbo;
	private ImageIcon icon;
	private JButton BackButton,updateInfoB,updatePassB;

	
	
	
	public SettingF(Puser user){
		super("SETTING");
		this.setLayout(null);
		
		
		dbo = new DbConnect();
		
		font = new Font("Times New Roman", Font.BOLD, 40);
		font2 =new Font("Roboto",1,12);
		font3 =new Font("Roboto",0,11);
		
		
		this.user=user;
        icon = new ImageIcon(this.getClass().getResource("icon.jpg"));
		
		this.setIconImage(icon.getImage());
		
		
		//LABEL
		
		settingL = new JLabel("SETTING");
		settingL.setFont(font);
		settingL.setBounds(5,5,350,50);
        this.add(this.settingL);
		String uT="";
		if(user.getUserType()==1){
			uT ="ADMIN";
		}
		else
			uT ="REGULAR";
		
		userIdL = new JLabel ("USER ID : "+user.getUserId()+"     USERTYPE: "+uT);
		userIdL.setBounds(15,75,350,25);
        this.add(this.userIdL);
		
		nameL = new JLabel ("USERNAME: ");
		nameL.setBounds (15,125,100,25);
		this.add(nameL);
		
		mNumberL = new JLabel ("NUMBER:                 "+user.getUserNumber());
		mNumberL.setForeground(Color.decode("#6D79AF"));
		mNumberL.setBounds (15,175,350,25);
		this.add(mNumberL);
		
		addressL = new JLabel ("ADDRESS: ");
		addressL.setBounds (15,225,100,25);
		this.add(addressL);
		
		oldPL = new JLabel("OLD PASSWORD");
		oldPL.setBounds(15,290,100,25);
		this.add(oldPL);
		
		newPL = new JLabel("NEW PASSWORD");
		newPL.setBounds(215,290,100,25);
		this.add(newPL);
		
		
		//JTextField
		
		nameF = new JTextField(user.getUserName());
		nameF.setBounds(115,125,200,25);
		this.add(nameF);
		
		addressF = new JTextArea(user.getUserAddress());
		addressF.setBounds(115,225,200,60);
		this.add(addressF);
		
		oldPassF = new JPasswordField("");
		oldPassF.setBounds(15,320,150,25);
		this.add(oldPassF);
		
		newPassF = new JPasswordField("");
		newPassF.setBounds(215,320,150,25);
		this.add(newPassF);
		
		
		//-JBUtton
		
		
		updatePassB= new JButton("UPDATE PASSWORD");
		updatePassB.setBackground(Color.decode("#47ab68"));
		updatePassB.setFont(font2);
		updatePassB.setBounds(115,350,175,25);
		this.add(updatePassB);
		updatePassB.addActionListener(this);
		
		
		
		updateInfoB = new JButton("UPDATE INFO");
		updateInfoB.setBackground(Color.decode("#47ab68"));
		updateInfoB.setFont(font2);
		updateInfoB.setBounds(145,450,140,50);
		this.add(updateInfoB);
		updateInfoB.addActionListener(this);
		
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
	
		
		if(e.getActionCommand().equals("UPDATE INFO")){
			
			if(nameF.getText().equals("") || addressF.getText().equals("") ){
				
				JOptionPane.showMessageDialog(this,"INPUT ERROR!", "UPDATE ERROR", JOptionPane.ERROR_MESSAGE);
				nameF.setText(user.getUserName());
				addressF.setText(user.getUserAddress());
			}
			else if(nameF.getText().equals(user.getUserName()) && addressF.getText().equals(user.getUserAddress())){
				
				JOptionPane.showMessageDialog(this,"NOTHING TO UPDATE!", "UPDATE ERROR", JOptionPane.ERROR_MESSAGE);
				nameF.setText(user.getUserName());
				addressF.setText(user.getUserAddress());
			}
			else{
				
				user.setUserName(nameF.getText());
				user.setUserAddress(addressF.getText());
				JOptionPane.showMessageDialog(this,"INFO UPDATED!", "UPDATE SUCCESSFUL", JOptionPane.INFORMATION_MESSAGE);
				nameF.setText(user.getUserName());
				addressF.setText(user.getUserAddress());
			}
			
		}
		
		
		if(e.getActionCommand().equals("UPDATE PASSWORD")){
			String pass,nPass;
			pass = new String(oldPassF.getPassword());
			nPass = new String(newPassF.getPassword());
			
			
			if(pass.equals("") || !(pass.equals(user.getUserPass())) || nPass.equals("") ){
				
				JOptionPane.showMessageDialog(this,"INVALID REQUEST!", "UPDATE ERROR", JOptionPane.ERROR_MESSAGE);
				oldPassF.setText("");
				newPassF.setText("");
			}
			else{
				
				user.setUserPass(nPass);
				
				JOptionPane.showMessageDialog(this,"Password Update Sucessfull \nNew Password: "+user.getUserPass()+" ", "UPDATE SUCCESSFUL", JOptionPane.INFORMATION_MESSAGE);
				
				oldPassF.setText("");
				newPassF.setText("");
				
			}
			
			
		}
		
		
		
		if(e.getActionCommand().equals("BACK")){
			
			UserFrame uF = new UserFrame(user);
			
			this.dispose();
			
			
		}
	
	
	
	}	


	
}