package assignment_3;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class StartFrame extends JFrame implements ActionListener
{
  Connection cn;
  Statement st;
  PreparedStatement ps;
  ResultSet rs;	
	
  JLabel userlabel=new JLabel("User Name");
  JLabel passlabel=new JLabel("Password");
  JTextField userfield=new JTextField(17);
  JPasswordField passfield=new JPasswordField(17);
  JButton login=new JButton("Login");
  JButton register=new JButton("Register");
  
  JLabel salonpic=new JLabel();
  JLabel wrong_right=new JLabel();
  
  JPanel p=new JPanel();
  GridBagConstraints jbc=new GridBagConstraints();
  
  public StartFrame()
  {
	this.setSize(500,300);
	
	this.setLayout(new GridBagLayout());
	p.setLayout(new GridBagLayout());
	
	salonpic.setIcon(new ImageIcon(new ImageIcon("C:\\Users\\zohaib computer\\Downloads\\saloon.png")
	.getImage().getScaledInstance(130,130,Image.SCALE_SMOOTH)));
	
	login.addActionListener(this);
	login.setBackground(Color.blue);
	login.setForeground(Color.white);
	register.addActionListener(this);
	register.setBackground(Color.red);
	register.setForeground(Color.white);
	
	jbc.insets=new Insets(0,10,15,0);
	jbc.anchor=GridBagConstraints.LINE_START;
	jbc.gridx=0; jbc.gridy=0;
	p.add(userlabel,jbc);
	jbc.gridx=1; jbc.gridy=0;
	p.add(userfield,jbc);
	jbc.gridx=0; jbc.gridy=1;
	p.add(passlabel,jbc);
	jbc.gridx=1; jbc.gridy=1;
	p.add(passfield,jbc);
	jbc.gridx=1; jbc.gridy=2;
	p.add(login,jbc);
	jbc.anchor=GridBagConstraints.LINE_END;
	jbc.gridx=1; jbc.gridy=2;
	p.add(register,jbc);
	jbc.gridx=1; jbc.gridy=3;
	add(wrong_right,jbc);
	
	jbc.gridx=0; jbc.gridy=0; 
	add(salonpic,jbc); 
	
	jbc.insets=new Insets(25,10,0,0);

	jbc.gridx=1; jbc.gridy=0;
	add(p,jbc);
	
	this.setVisible(true);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setResizable(true);	
  }
  
  public void actionPerformed(ActionEvent e){ 
	
   if(userfield.getText().equals("")||passfield.getText().equals(""))
   {
	 JOptionPane.showMessageDialog(null,"Username or Password is missing");  
   }
	  
   else {
	   
	   try{
	       Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
	       cn=DriverManager.getConnection("jdbc:ucanaccess://D:/New folder (2)/Assignment3Records.accdb");
	       st=cn.createStatement();
		   } catch(Exception ee){  System.out.println("Connection Error:\nException: "+ee);  }
		   
	   
   if(e.getSource()==register)
   {	   

	   try 
      {
          rs=st.executeQuery("select * from loginuser where username='"+userfield.getText()+"'");	           
          if(rs.next()) {
        	  JOptionPane.showMessageDialog(null,"Username Already Exists");
          }
          else
          {
        	//for adding data PreparedStatement object or statement execute update method is used
              st.executeUpdate("insert into loginuser (username,password) values ('"+userfield.getText()+"','"+passfield.getText()+"')");
              /* ps=cn.prepareStatement("insert into recordtest.recordperson (name,age) values (?,?)");
              ps.setString(1,"jamshaid");
              ps.setString(2,String.valueOf(12));
              ps.execute(); */         
              
              wrong_right.setText("Record Added Successfully");  
          }
      } 
      catch (Exception e1) 
      {
          System.out.println("Connection Error:\nException: "+e1);  
      }
   }
	  else if(e.getSource()==login)
	   {	   
		  try 
	      {
	          //for adding data PreparedStatement object or statement execute update method is used
	          rs=st.executeQuery("select * from loginuser where username='"+userfield.getText()+"' and password='"+passfield.getText()+"'");	           
	          if(rs.next()) {
	        	  this.setVisible(false);
	        	  MainFrame m=new MainFrame();
	          }
	          else
	          {wrong_right.setText("Incorrect Username: "+this.userfield.getText()+", OR Password: "+this.passfield.getText());}

	      } 
	      catch (Exception e1) 
	      {
	          System.out.println("Connection Error:\nException: "+e1);  
	      }
	   }
   
   		try
   		{
            cn.close();
            st.close();
            rs.close();	
   		}
   		catch(Exception ee) {  System.out.println("Connection Error:\nException: "+ee);  }
   
  }   
  }
}
