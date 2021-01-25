package PFrames;
import java.util.ArrayList;
import javax.swing.table.*;
import javax.swing.*;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import PDBConnect.*;
import PEntity.*;
import java.io.*;


class PCEntity{
	public int id;
	public int stock;
	DbConnect dbo;
public PCEntity(){
	dbo = new DbConnect();
	
	
}
	
public void reset(){
	dbo.openConnection();
	try{String sql = "Update medicine set stock = "+this.stock+" where mid = "+this.id+" ";
	dbo.st.executeUpdate(sql);
	dbo.closeConnection();
	}catch(Exception ex){}
	}	
	
}


public class SellFrame extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 1;
	private JTable medT,cartT;
	private JTextField medicineIdF,mediQuanF,searchF;
	private JLabel mediQuanL,medicineIdL,medTL,carTL,doctorNameL,wrongId,wrongQ,UPLabel,searchL;
	private ImageIcon icon;
	private String[] clN={"Medicine Name","Medicine ID","Price","Stock"};
	private String[] clN2={"Medicine ID","Name","Quantity","Cost"};
	private DefaultTableModel tableModel1,tableModel2;
	private Container medC,cartC;
	private JScrollPane sp1,sp2;
	private JComboBox doctorList;
	private DbConnect dbo;
	private JButton addToCart,proceedB,cancleB;
	private ArrayList<String> docList = new ArrayList<String>();
	private Puser user;
	private Patient patient;
	private PCEntity[] pCanObj;
	private int pCanObjIndex=0;
	//int patientId;
	
	public SellFrame(Puser user,Patient patient){
			
		super("MEDICINE SHOP");
		
		
		
		pCanObj = new PCEntity[100];
		this.user = user;
		this.patient=patient;
		dbo = new DbConnect();
		dbo.openConnection();
		
		icon= new ImageIcon(this.getClass().getResource("logo.jpg"));
		this.setIconImage(icon.getImage());
		this.setLayout(null);
		
		//----
		
		doctorNameL = new JLabel("Reffral Doctor");
		doctorNameL.setBounds(425,20,100,25);
		this.add(doctorNameL);
		
		
		UPLabel = new JLabel("USERID : "+user.getUserId()+" DEALING WITH PATIENT NAME: "+patient.getPatientName());
		UPLabel.setBounds(300,155,450,25);
		UPLabel.setForeground(Color.decode("#C342F0"));
		UPLabel.setFont(new Font("Times New Roman",1,13));
		this.add(UPLabel);		
		medTL = new JLabel("Medicine Table");
		medTL.setBounds(120,150,100,25);
		this.add(medTL);
		
		carTL = new JLabel("Cart Table");
		carTL.setBounds(690,150,100,25);
		this.add(carTL);
		
		searchL = new JLabel("Search:");
		searchL.setBounds(30,100,150,25);
		this.add(searchL);
		
		medicineIdL = new JLabel("Medicine ID");
		medicineIdL.setBounds(30,25,150,25);
		this.add(medicineIdL);
		
		mediQuanL = new JLabel("Quantity");
		mediQuanL.setBounds(190,25,150,25);
		this.add(mediQuanL);
		
		wrongId = new JLabel("Check Again!");
		wrongId.setForeground(Color.RED);
		wrongId.setBounds(30,75,150,25);
		wrongId.setVisible(false);
		this.add(wrongId);
		
		wrongQ = new JLabel("Invalid Quantity!");
		wrongQ.setForeground(Color.RED);
		wrongQ.setBounds(190,75,150,25);
		wrongQ.setVisible(false);
		this.add(wrongQ);
		
		
		//------TEXT F
			medicineIdF = new JTextField("");
			medicineIdF.setBounds(30,50,150,25);
			this.add(medicineIdF);
			
			mediQuanF = new JTextField("");
			mediQuanF.setBounds(190,50,150,25);
			this.add(mediQuanF);
			
			searchF = new JTextField("");
			searchF.setBounds(30,120,150,25);
			this.add(searchF);
			searchF.addKeyListener(new KeyAdapter(){
				
			public void keyReleased(KeyEvent e) {
			JTextField textField = (JTextField) e.getSource();
			String text = textField.getText();
			
			
			DefaultTableModel tdm = (DefaultTableModel)medT.getModel();
			TableRowSorter<DefaultTableModel> tm = new TableRowSorter<DefaultTableModel>(tdm);
			medT.setRowSorter(tm);
			tm.setRowFilter(RowFilter.regexFilter(text));
			
			
			}
				
			}
			
			);
			
			
		//Finishings
		this.getContentPane().setBackground(Color.decode("#FDFEFF"));
		this.setVisible(true);
        this.setSize(950, 650);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		//----JTABLES
		
		 
		tableModel1 = new DefaultTableModel(clN,0);
		medT = new JTable(tableModel1);
		
		
			this.updateMedT();
			
			
			medC = new Container();
			medC.setBounds(1,190,400,420);
			medT.setBounds(1,100,400,420);
			sp1 = new JScrollPane(medT);
			sp1.setBounds(1,0,400,420);
			medC.add(sp1);
			medT.setRowHeight(30);
			medT.setDefaultEditor(Object.class,null);
			this.add(medC);
			medT.setAutoResizeMode(HEIGHT);
			medT.setFillsViewportHeight(true);
			medT.setPreferredScrollableViewportSize(medT.getPreferredSize());
			
		
		
		
		//--2nd Table
		tableModel2= new DefaultTableModel(clN2,0);
		cartT = new JTable(tableModel2);
		cartC = new Container();
		cartC.setBounds(530,190,400,420); 
		cartT.setBounds(0,100,400,420);
		sp2 = new JScrollPane(cartT);
		sp2.setBounds(0,0,400,420);
		cartC.add(sp2);
		cartT.setRowHeight(30);
		this.add(cartC);
		cartT.setDefaultEditor(Object.class,null);
		cartT.setPreferredScrollableViewportSize(cartT.getPreferredSize());
		
		
		
		
		
		
		//----
		//----------------JButton
		
		addToCart = new JButton("Add To Cart");
		addToCart.setBounds(250,100,100,40);
		addToCart.setBackground(Color.decode("#47CEC0"));
		this.add(addToCart);
		addToCart.addActionListener(this);
		
		proceedB = new JButton("PROCEED");
		proceedB.setBounds(800,40,100,40);
		proceedB.setBackground(Color.decode("#47CEC0"));
		this.add(proceedB);
		proceedB.addActionListener(this);
		
		cancleB = new JButton("CANCLE");
		cancleB.setBounds(800,90,100,40);
		cancleB.setBackground(Color.decode("#D23737"));
		this.add(cancleB);
		cancleB.addActionListener(this);
		
		//---JCOMBOBOX
		try{
		dbo.result = dbo.st.executeQuery("Select doctorName from doctor");
		while(dbo.result.next())
		{
			docList.add (dbo.result.getString(1));
		}
	
		
		
		}catch(Exception ex){}
		int ArraySize = docList.size();
		String docListAr[]= new String[ArraySize];
		
		for(int i=0;i<ArraySize;i++){
			docListAr[i] = docList.get(i);
			
		}
		
		
		doctorList = new JComboBox<String>(docListAr);
		doctorList.setBounds(425,50,200,30);
		this.add(doctorList);
		
	
	
}


public void actionPerformed(ActionEvent e){
	
		

	
		if(e.getActionCommand().equals("Add To Cart")){
			
			int id=0,quantity=-0,quantityAv=0;
			boolean idInputRight =true;
			boolean quantityInputRight=true;
			
			
			try{
				wrongId.setVisible(false);
				id = Integer.parseInt(medicineIdF.getText());
			}catch(Exception ex){
				idInputRight = false;
				wrongId.setVisible(true);
				
			}
			if(idInputRight){
				idInputRight=false;
				String sql = "Select stock from medicine where mid="+id+"";
				try{
					dbo.result = dbo.st.executeQuery(sql);
					while (dbo.result.next()){
						idInputRight = true;
						quantityAv = Integer.parseInt(dbo.result.getString(1));
						
					}
				}catch(Exception ex){}
					
			if(!idInputRight){
				wrongId.setVisible(true);
			}
			
			}
			
			
			try{
				wrongQ.setVisible(false);
				quantity = Integer.parseInt(mediQuanF.getText());
			}catch(Exception ex){
				quantityInputRight= false;
				wrongQ.setVisible(true);
				
			}
			
			
			if(quantityInputRight)
			{
				if(quantity<0 || quantity>quantityAv){
					quantityInputRight=false;
					
				}
				
				if(!quantityInputRight){
					
					wrongQ.setVisible(true);}
			}
			
			
			if(quantityInputRight && idInputRight){
				
				this.updateCartT(id,quantity,quantityAv);
				
			}
			
			
			
			
			
			
		}


	
		if(e.getActionCommand().equals("CANCLE")){
			
			UserFrame uObj = new UserFrame(user);
			
			
			for(int i  = pCanObjIndex;i>=0;i--)
			{
				if(pCanObj[i]!=null){
				pCanObj[i].reset();
				}
			}
			
			this.dispose();
			
		}
		
		if(e.getActionCommand().equals("PROCEED")){
			
			if(cartT.getRowCount()<=0){
				
				 JOptionPane.showMessageDialog(this,"INVALID REQUEST!\nADD SOMETHING TO CARD\nOR CHOOSE TO CANCLE!");  
				
			}
			else{
				
				this.createFile();
				
				UserFrame userObj = new UserFrame(user);
				
				this.dispose();
				
			}
			
		}
	
	
	
}


public void updateCartT(int medId,int quantity,int stock){
	
	String sql = "Select medicineName,medicinePrice from medicine where mid ="+medId+"";
	String sql2 = "Update medicine SET stock = stock - "+quantity+" where mid ="+medId+" ";
	int cost;
	
	pCanObj[pCanObjIndex] = new PCEntity();
	pCanObj[pCanObjIndex].id = medId;
	pCanObj[pCanObjIndex].stock = stock;
	pCanObjIndex++;
	
	try{
			
			dbo.result= dbo.st.executeQuery(sql);
			
			
			
			while(dbo.result.next()){	
								
								cost = Integer.parseInt(dbo.result.getString(2)) * quantity;
								
								String []rowsV = {Integer.toString(medId),dbo.result.getString(1),Integer.toString(quantity),Integer.toString(cost)};
								tableModel2.addRow(rowsV);
								
								
								}
								
								dbo.st.executeUpdate(sql2);
								this.updateMedT();
								
							}catch(Exception ex){}
	
	
}

public void updateMedT(){
	try{
			tableModel1.setRowCount(0);
			
			dbo.result= dbo.st.executeQuery("Select * from medicine ");
			
			
			
			while(dbo.result.next()){	
								
								String []rowsV = {dbo.result.getString(2),dbo.result.getString(1),dbo.result.getString(3),dbo.result.getString(4)};
								tableModel1.addRow(rowsV);
								
								}
							}catch(Exception ex){}
	
	
}


public void createFile(){
	
	//DBPART
	String sql = "Insert into filetable(fileType) values('TEXT')";
	String sql2 = "Select fileId from filetable ";
	int FileNumber=0;
	int totalCost=0;
	try{
		dbo.st.execute(sql);
		dbo.result = dbo.st.executeQuery(sql2);
		while (dbo.result.next()){
			
			FileNumber = dbo.result.getInt(1);
		}
		
		
	}catch(Exception ex){}
	//ENDOFDBPART
	try{
	File file = new File("C:\\PaySlip\\PaySlip"+FileNumber+".txt ");
	if(!file.exists()){
		file.createNewFile();
		
	}
	
	FileWriter fw = new FileWriter(file.getAbsoluteFile());
	BufferedWriter bw = new BufferedWriter(fw);
	
	
	bw.write("\t\t-------------------------------------LSPC PHARMA PAYSLIP: "+FileNumber+"-------------------------------------");
	
	bw.write("\nPATIENT NAME : "+patient.getPatientName()+"  (PATIENT ID: "+(patient.getPid())+")"+"\tMEDICINE SOLD BY: "+user.getUserName()+"  (SELLER ID:  "+user.getUserId()+")"+"\tREFFERED DOCTOR: "+doctorList.getSelectedItem().toString());
	
	bw.write("\nCOLUMNS : MEDICINE ID,MEDICINE NAME,QUANTITY,COST \n\n");
	
	for(int i=0;i<cartT.getRowCount();i++){
		for(int j=0;j<cartT.getColumnCount();j++){
			bw.write(cartT.getModel().getValueAt(i,j)+"  ||  ");
			if(j==3){
				totalCost = totalCost + Integer.parseInt((String)cartT.getModel().getValueAt(i,j));
			}
		}
		bw.write("\n");
		
		
		
	}
	bw.write("\nTOTAL COST: "+totalCost+"\n___________________________\n");
	
	bw.close();
	fw.close();
	JOptionPane.showMessageDialog(null,"CHECK FOR PAYSLIP"+FileNumber+".txt IN DRIVE C");
	
	
	
	
	
	}catch(Exception ex){System.out.println(ex.getMessage());}
}

}
