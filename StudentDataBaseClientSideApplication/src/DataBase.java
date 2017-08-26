/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Arpit
 
 */
import java.util.List;
    import java.sql.*;
    import java.io.*;
import java.util.ArrayList;
 
public class DataBase {



   // JDBC driver name and database URL
   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   static final String DB_URL = "jdbc:mysql://localhost/StudentDB";

   //  Database credentials
   static final String USER = "root";
   static final String PASS = "root";
  
   public static boolean verify(int sid,String first,String last,String email,String contact,String branch,int year,String address,String sec) {
    Connection conn = null;
   PreparedStatement stmt=null;
  // Statement stmt = null;
   try{
      //STEP 2: Register JDBC driver
      Class.forName("com.mysql.jdbc.Driver");

      //STEP 3: Open a connection
      System.out.println("Connecting to database...");
      conn = DriverManager.getConnection(DB_URL,USER,PASS);

      //STEP 4: Execute a query
      System.out.println("Creating statement...");
      //stmt = conn.createStatement();
      stmt=conn.prepareStatement("insert into Persons values(?,?,?,?,?,?,?,?,?)"); 
      stmt.setInt(1,sid);
      stmt.setString(2,last);
      stmt.setString(3,first);
      stmt.setString(4,address);
      stmt.setString(5,contact);
      stmt.setString(6,email);
      stmt.setString(7,branch);
      stmt.setString(8,sec);
      stmt.setInt(9,year);
      int i=stmt.executeUpdate();  
      if(i==0)
          return false;
      else
          return true;
   
     
    
      
     
   }catch(SQLException se){
      //Handle errors for JDBC
      se.printStackTrace();
   }catch(Exception e){
      //Handle errors for Class.forName
      e.printStackTrace();
   }finally{
      //finally block used to close resources
      try{
         if(stmt!=null)
            stmt.close();
      }catch(SQLException se2){
      }// nothing we can do
      try{
         if(conn!=null)
            conn.close();
      }catch(SQLException se){
         se.printStackTrace();
      }//end finally try
   }//end try
   
return false;
      
   }
   public ArrayList getAll() throws SQLException{
           ViewAll db=new ViewAll();
           StringBuilder st=new StringBuilder();
          Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
          PreparedStatement stmt=null; 
          ArrayList<ViewAll> list=new ArrayList();
          stmt=conn.prepareStatement("Select * from Persons");
          ResultSet rs=stmt.executeQuery();
          while(rs.next()){
            db.setSid(rs.getInt("sid"));
            db.setLanme(rs.getString("last"));
            db.setFname(rs.getString("first"));
            db.setAdds(rs.getString("address"));
            db.setContact(rs.getString("contact"));
            db.setEmail(rs.getString("email"));
            db.setBranch(rs.getString("branch"));
            db.setSec(rs.getString("sec"));
            db.setYear(rs.getInt("year"));
            list.add(db);
          }
           return list;
          
   }
    public String getData(int sid) throws SQLException{
       
          Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
          PreparedStatement stmt=null; 
        StringBuilder result=new StringBuilder();
        
          String data="select * from Persons  where SID=?";
          stmt= conn.prepareStatement(data);
          stmt.setInt(1,sid);
           ResultSet rs = stmt.executeQuery();
       while(rs.next()){
           result.append("Student Id :").append(rs.getInt(1)).append("\n ").append("First Name :").append(rs.getString(3)).append("\n ").
                    append("Last Name :").append(rs.getString(2)).append("\n ").append("Email :").append(rs.getString(6)).append("\n").
                   append("Contact No:").append(rs.getString(5)).append("\n ").
                    append("Branch :").append(rs.getString(7)).append(" \n").append("Section :").append(rs.getString(8)).append("\n ").
                    append("Year :").append(rs.getInt(9)).append("\n ").append("Address :").append(rs.getString(4)).append("\n");
        }
        
    
return result.toString();
}
     public static boolean updateDB(int sid,String first,String last,String email,String contact,String branch,int year,String add,String sec) {
    Connection conn = null;
   PreparedStatement stmt=null;
  // Statement stmt = null;
   try{
      //STEP 2: Register JDBC driver
      Class.forName("com.mysql.jdbc.Driver");

      //STEP 3: Open a connection
      System.out.println("Connecting to database...");
      conn = DriverManager.getConnection(DB_URL,USER,PASS);

      //STEP 4: Execute a query
      //System.out.println("Creating statement...");
      //stmt = conn.createStatement();
      stmt=conn.prepareStatement("UPDATE PERSONS SET SID =?,LastName=?,FirstName=?,Address=?,Contact=?,email=?,Branch=?,Section=?,year=? where SID=?");
      stmt.setInt(1,sid);
      stmt.setString(2,last);
      stmt.setString(3,first);
      stmt.setString(4,add);
      stmt.setString(5,contact);
      stmt.setString(6,email);
      stmt.setString(7,branch);
      stmt.setString(8,sec);
      stmt.setInt(9,year);
    
            int i=stmt.executeUpdate();  

     if(i==0)
          return false;
      else
          return true;
    }catch(SQLException se){
      //Handle errors for JDBC
      se.printStackTrace();
   }catch(Exception e){
      //Handle errors for Class.forName
      e.printStackTrace();
   }finally{
      //finally block used to close resources
      try{
         if(stmt!=null)
            stmt.close();
      }catch(SQLException se2){
      }// nothing we can do
      try{
         if(conn!=null)
            conn.close();
      }catch(SQLException se){
         se.printStackTrace();
      }//end finally try
   }//end try
   
return false;
      
   }
}

      //STEP 5: Extract data from result set
//      while(rs.next()){
//         //Retrieve by column name
//         int id  = rs.getInt("SID");
//         int year = rs.getInt("year");
//         String first = rs.getString("FirstName");
//         String last = rs.getString("LastName");
//         String address=rs.getString("Address");
//         String contact=rs.getString("Contact");
//         String email=rs.getString("email");
//         String branch=rs.getString("Branch");
//         String sec=rs.getString("Section");
//         
//
//         //Display values
//         System.out.print("Student ID: " + id);
//         System.out.print(", First Name: " + first);
//         System.out.print(", Last Name: " +last);
//         System.out.print(", Email: " +email);
//         System.out.print(", Contact: "+contact);
//         System.out.print(", Address: "+address);
//         System.out.print(", Branch: "+branch);
//         System.out.print(", Year: "+year);
//         System.out.print(", Section: "+sec);
//      }
      //STEP 6: Clean-up environment
    
    

