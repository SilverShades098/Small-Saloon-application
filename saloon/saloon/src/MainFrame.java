package assignment_3;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.*;
import java.util.*;
import javax.swing.*;

class MainFrame extends JFrame implements ActionListener
{
	JToolBar toolbar=new JToolBar();	
	JButton customer_pic,bill_pic,logout_pic,history_pic;
	GridBagConstraints jbc=new GridBagConstraints();
	
  MainFrame()
  {
	  this.setSize(700, 600);
	  this.setVisible(true);
	  this.setLayout(new BorderLayout());
	  toolbar.setRollover(true);
	  
	  customer_pic = new JButton();
	  bill_pic = new JButton();
	  logout_pic = new JButton();
          history_pic= new JButton();

	  customer_pic.setIcon(new ImageIcon(new ImageIcon("C:\\Users\\zohaib computer\\Downloads\\male.png")
	  .getImage().getScaledInstance(70,70,Image.SCALE_SMOOTH)));
	   
	  bill_pic.setIcon(new ImageIcon(new ImageIcon("C:\\Users\\zohaib computer\\Downloads\\recipt.png")
	  .getImage().getScaledInstance(70,70,Image.SCALE_SMOOTH)));
		   
	  logout_pic.setIcon(new ImageIcon(new ImageIcon("C:\\Users\\zohaib computer\\Downloads\\logout.png")
	  .getImage().getScaledInstance(70,70,Image.SCALE_SMOOTH)));
	  
          history_pic.setIcon(new ImageIcon(new ImageIcon("C:\\Users\\zohaib computer\\Downloads\\history.png")
	  .getImage().getScaledInstance(70,70,Image.SCALE_SMOOTH)));
	  
	  toolbar.add(customer_pic);
	  toolbar.add(bill_pic);
	  toolbar.add(logout_pic);  
	  toolbar.add(history_pic);  
	  
	  customer_pic.addActionListener(this);
	  bill_pic.addActionListener(this);
	  logout_pic.addActionListener(this);
	  history_pic.addActionListener(this);
	  
	  add(toolbar,BorderLayout.NORTH);
          this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
@Override
public void actionPerformed(ActionEvent e) {
	
	CustomerPanel cp = null;
	BillPanel bp = null;
	HistoryPanel hs=null;
        JLabel l=new JLabel("No Customer Created Yet!");
	
	if(e.getSource()==customer_pic) {
			try{	remove(bp); remove(l); remove(hs); }
			catch(Exception e2){   }
			cp=new CustomerPanel();
			add(cp,BorderLayout.CENTER);	
			validate();
	 }

	if(e.getSource()==bill_pic) {
			try{	remove(cp); remove(l); remove(hs); }
			catch(Exception e2){		}
	
			if(CustomerPanel.customercreated==false) {
				add(l,BorderLayout.CENTER);
				validate();
			}
			
			else {
				BillPanel.t.setText(CustomerPanel.bill.getText());
				bp=new BillPanel(); 
				JScrollPane s=new JScrollPane(bp);
				add(s,BorderLayout.CENTER);
				validate();
			}
	 }
	
	if(e.getSource()==logout_pic) {
		this.setVisible(false);
		StartFrame f = new StartFrame();
	 }
	
       	if(e.getSource()==history_pic) {
			try{	remove(cp); remove(l); remove(bp); }
			catch(Exception e2){		}
	
			if(CustomerPanel.customercreated==false) {
				add(l,BorderLayout.CENTER);
				validate();
			}
			
			else {
				HistoryPanel.t.setText(CustomerPanel.history.getText());
				hs=new HistoryPanel(); 
				JScrollPane s=new JScrollPane(hs);
				add(s,BorderLayout.CENTER);
				validate();
			}
	 }
	
}
}

class CustomerPanel extends JPanel implements ActionListener
{
	  Connection cn;
	  Statement st;
	  PreparedStatement ps;
	  ResultSet rs;	
	
	static int visit=0; 
	static boolean customercreated=false;
	static TextArea bill=new TextArea("");	
	static TextArea history=new TextArea("");	
	
        
	GridBagConstraints jbc=new GridBagConstraints();	
	
	JLabel customerdetailslabel=new JLabel("Customer Details");
	JLabel datelabel=new JLabel("Date: "+new SimpleDateFormat("d/MMM/yy").format(new Date()));
	JLabel stylelabel=new JLabel("Select Style");
	JLabel servicelabel=new JLabel("Services Charges");
	JLabel customernamelabel=new JLabel("Customer Name");
	JButton create=new JButton("Create");
	JTextField customerfield=new JTextField(15);
	JTextField servicefield=new JTextField(15);
	
	JLabel style1=new JLabel();
	JLabel style2=new JLabel();
	JLabel style3=new JLabel();

	JRadioButton hairstyle1=new JRadioButton("1000");
	JRadioButton hairstyle2=new JRadioButton("2000");	
	JRadioButton hairstyle3=new JRadioButton("3000");
	ButtonGroup hairstylesgroup=new ButtonGroup();
	
  CustomerPanel()
  {    
	JPanel p1=new JPanel(new GridBagLayout());    
	setLayout(new GridBagLayout());
	
	style1.setIcon(new ImageIcon(new ImageIcon("C:\\Users\\zohaib computer\\Downloads\\st1.png")
    .getImage().getScaledInstance(60,60,Image.SCALE_SMOOTH)));

	style2.setIcon(new ImageIcon(new ImageIcon("C:\\Users\\zohaib computer\\Downloads\\st2.png")
	.getImage().getScaledInstance(60,60,Image.SCALE_SMOOTH)));

	style3.setIcon(new ImageIcon(new ImageIcon("C:\\Users\\zohaib computer\\Downloads\\st3.png")
	.getImage().getScaledInstance(60,60,Image.SCALE_SMOOTH)));
    
    
    this.customerdetailslabel.setFont(new Font("",Font.BOLD,20));        
    this.hairstylesgroup.add(hairstyle1);
    this.hairstylesgroup.add(hairstyle2);
    this.hairstylesgroup.add(hairstyle3);
    
	jbc.insets=new Insets(30,10,0,30);
	jbc.anchor=GridBagConstraints.LINE_START;
	jbc.gridx=0; jbc.gridy=0;
	add(customerdetailslabel,jbc);
	jbc.gridx=0; jbc.gridy=1;
	add(datelabel,jbc);		
	jbc.gridx=0; jbc.gridy=2;
	add(customernamelabel,jbc);
	jbc.gridx=1; jbc.gridy=2;
	add(customerfield,jbc);
	jbc.gridx=0; jbc.gridy=3;
	add(stylelabel,jbc);
	
	hairstyle1.setBackground(Color.white);
	hairstyle2.setBackground(Color.white);
	hairstyle3.setBackground(Color.white);
	
	jbc.insets=new Insets(0,10,0,0);
	jbc.gridx=0; jbc.gridy=0;
	p1.add(hairstyle1,jbc);		
	jbc.gridx=1; jbc.gridy=0;
	p1.add(style1,jbc);
	
	jbc.gridx=2; jbc.gridy=0;
	p1.add(hairstyle2,jbc);
	jbc.gridx=3; jbc.gridy=0;
	p1.add(style2,jbc);
	
	jbc.gridx=4; jbc.gridy=0;
	p1.add(hairstyle3,jbc);
	jbc.gridx=5; jbc.gridy=0;
	p1.add(style3,jbc);
		
	jbc.insets=new Insets(30,10,0,30);
	jbc.gridx=1; jbc.gridy=3;
	add(p1,jbc);
	
	jbc.gridx=0; jbc.gridy=4;
	add(servicelabel,jbc);	
	jbc.gridx=1; jbc.gridy=4;
	add(servicefield,jbc);
	jbc.gridx=1; jbc.gridy=5;
	add(create,jbc);
	
	create.addActionListener(this);
	
	this.setBackground(Color.white);
	p1.setBackground(Color.white);
	
  }

  public String printBill()
  {
      try {
		Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
	  cn=DriverManager.getConnection("jdbc:ucanaccess://D:/New folder (2)/Assignment3Records.accdb");
      st=cn.createStatement();	   
      rs=st.executeQuery("select * from userinformation where fullname='"+customerfield.getText()+"'");	           
	if(rs.next()) {	
	 return "Customer Name: "+rs.getString("fullname")+"\nMemberShip Customer"+
	 "\tNo. of Visit: "+visit+"\nDate: "+rs.getString("creatingtime")+"\nService Charges: "+
	 rs.getString("ServiceCharges")+"\nProduct Charges: "+rs.getString("ProductCharges")+"\nTotal: "+
	 rs.getString("Total")+"\n----------------------------------------------\n\n";
	 }
      }
	catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
      
	return null;
  }
  
@Override
public void actionPerformed(ActionEvent e) {
	
	if(this.customerfield.getText().equals("")||this.servicefield.getText().equals("")
	||(this.hairstyle1.isSelected()==false&&this.hairstyle2.isSelected()==false&&
	this.hairstyle3.isSelected()==false))
	{
		JOptionPane.showMessageDialog(null,"Enter Detail Correctly (Don't Leave Anything)");
	}
	 
	   else {
		   try{
		       Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
		       cn=DriverManager.getConnection("jdbc:ucanaccess://D:/New folder (2)/Assignment3Records.accdb");
		       st=cn.createStatement();
			   
	          rs=st.executeQuery("select * from userinformation where fullname='"+customerfield.getText()+"'");	           
	          if(rs.next()) {
	        	  JOptionPane.showMessageDialog(null,"Name Already Exists");
	          }
	          else
	          {
	        	  String hairstyleprice = null;
	        		 if(hairstyle1.isSelected()==true) { hairstyleprice=hairstyle1.getText(); }
	        	     if(hairstyle2.isSelected()==true) { hairstyleprice=hairstyle2.getText(); }
	        		 if(hairstyle3.isSelected()==true) { hairstyleprice=hairstyle3.getText(); }
	        		
	        	  //for adding data PreparedStatement object or statement execute update method is used
	              ps=cn.prepareStatement("insert into userinformation (fullname,CreatingTime,ServiceCharges,ProductCharges,Total) values (?,?,?,?,?)");
	              ps.setString(1,customerfield.getText());
	              ps.setString(2,String.valueOf(new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss").format(new Date())));
	              ps.setString(3,servicefield.getText());
	              ps.setString(4,hairstyleprice);
	              ps.setString(5,String.valueOf(Double.valueOf(servicefield.getText())+Double.valueOf(hairstyleprice)));
	              ps.execute();            
	              cn.close();
		            st.close();
		            rs.close();	
	          }
	          }   
		       catch (Exception e1) 
		      {
		          System.out.println("Connection Error:\nException: "+e1);  
		      }
		       visit++;
		customercreated=true;
		CustomerPanel.history.setText(CustomerPanel.bill.getText()+printBill());
                CustomerPanel.bill.setText(printBill());
		this.customerfield.setText("");
		this.servicefield.setText("");
		this.hairstyle1.setSelected(false);
		this.hairstyle2.setSelected(false);
		this.hairstyle3.setSelected(false);
	   }	   
}
}

class BillPanel extends JPanel
{  
  static JTextArea t=new JTextArea();
  
  BillPanel()
  {
	GridBagConstraints jbc=new GridBagConstraints();	
	  
	JPanel p=new JPanel();  
	p.setLayout(new GridBagLayout());
	  
	JLabel l=new JLabel("Bill");
	l.setFont(new Font("",Font.BOLD,18));
	
	JLabel l1=new JLabel("--------------------------"
	+ "---------------------------------------------");
	l1.setFont(new Font("",Font.BOLD,18));
	
	jbc.anchor=GridBagConstraints.CENTER;
	jbc.gridx=0; jbc.gridy=0;
	p.add(l,jbc);
	jbc.anchor=GridBagConstraints.LINE_START;
	jbc.gridx=0; jbc.gridy=1;
	p.add(l1,jbc);
	jbc.gridx=0; jbc.gridy=2;
	p.add(t,jbc);

	add(p,jbc);

	this.setBackground(Color.white);
	p.setBackground(Color.white);
  }
}

class HistoryPanel extends JPanel
{  
  static JTextArea t=new JTextArea();
  
  HistoryPanel()
  {
	GridBagConstraints jbc=new GridBagConstraints();	
	  
	JPanel p=new JPanel();  
	p.setLayout(new GridBagLayout());
	  
	JLabel l=new JLabel("Bill");
	l.setFont(new Font("",Font.BOLD,18));
	
	JLabel l1=new JLabel("--------------------------"
	+ "---------------------------------------------");
	l1.setFont(new Font("",Font.BOLD,18));
	
	jbc.anchor=GridBagConstraints.CENTER;
	jbc.gridx=0; jbc.gridy=0;
	p.add(l,jbc);
	jbc.anchor=GridBagConstraints.LINE_START;
	jbc.gridx=0; jbc.gridy=1;
	p.add(l1,jbc);
	jbc.gridx=0; jbc.gridy=2;
	p.add(t,jbc);

	add(p,jbc);

	this.setBackground(Color.white);
	p.setBackground(Color.white);
  }
}
