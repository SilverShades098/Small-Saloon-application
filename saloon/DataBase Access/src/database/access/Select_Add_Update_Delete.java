package database.access;

import java.sql.*;

class ConnectTest
{ 
   Connection cn;
   Statement st;
   PreparedStatement ps;
   ResultSet rs;
   
   ConnectTest()
   {
       try 
       {
           Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
           cn=DriverManager.getConnection("jdbc:ucanaccess://C:/Users/Jimmy/Documents/Databasejj.accdb");
           st=cn.createStatement();
           
           //for displaying ResultSet object is used
           rs=st.executeQuery("select * from recordperson");
           System.out.println("Before doing anything Record is\nID.\tName\t\tAge\n");
           int i=0;
           while(rs.next())
           {
               System.out.println(rs.getString("id")+"\t"+rs.getString("full name")+"\t\t"+rs.getString("age"));
           }
           
           
           //for adding data PreparedStatement object or statement execute update method is used
           //st.executeUpdate("insert into recordperson (name,age) values ('jamshaid',12)");
            ps=cn.prepareStatement("insert into recordperson (full name,age) values (?,?)");
           ps.setString(1,"ksdjfjds");
           ps.setString(2,String.valueOf(8788));
           ps.execute(); 
           
           //for displaying ResultSet object is used
           rs=st.executeQuery("select * from recordperson");
           System.out.println("\n\nAfter adding Record is\nID.\tName\t\tAge\n");
           i=0;
           while(rs.next())
           {
               System.out.println(rs.getString("id")+"\t"+rs.getString("full name")+"\t\t"+rs.getString("age"));
           }
           
           
           //for selecting ResultSet object is used
           rs=st.executeQuery("select * from recordperson where name='JJ'");
           System.out.println("\n\nAfter selecting Record is\nID.\tName\t\tAge\n");
           i=0;
           while(rs.next())
           {
               System.out.println(rs.getString("id")+"\t"+rs.getString("full name")+"\t\t"+rs.getString("age"));
           }
           
           
           //for updating data PreparedStatement object or statement execute update method is used
          st.executeUpdate("update recordperson set age=32 where name='jamshaid'");
           /* ps=cn.prepareStatement("update recordperson set age=32 where name='jamshaid'");
           ps.execute(); */
           //for displaying ResultSet object is used
           rs=st.executeQuery("select * from recordperson");
           System.out.println("\n\nAfter updating Record is\nID.\tName\t\tAge\n");
           i=0;
           while(rs.next())
           {
               System.out.println(rs.getString("id")+"\t"+rs.getString("full name")+"\t\t"+rs.getString("age"));
           }
           
           
           //for deleting data PreparedStatement object or statement execute update method is used
           st.executeUpdate("delete from recordperson where name='jamshaid'");
          /* ps=cn.prepareStatement("delete from recordperson where name='jamshaid'");
           ps.execute(); */
           
           //for displaying ResultSet object is used
           rs=st.executeQuery("select * from recordperson");
           System.out.println("\n\nAfter deleting Record is\nID.\tName\t\tAge\n");
           i=0;
           while(rs.next())
           {
               System.out.println(rs.getString("id")+"\t"+rs.getString("full name")+"\t\t"+rs.getString("age"));
           }
           
           
           cn.close();
           st.close();
           rs.close();
          
       } 
       catch (Exception e) 
       {
           System.out.println("Connection Error:\nException: "+e);  
       }
   
   }
}

public class Select_Add_Update_Delete {

  public static void main(String[] args)
  {
     ConnectTest s=new ConnectTest();
  }
	
}
