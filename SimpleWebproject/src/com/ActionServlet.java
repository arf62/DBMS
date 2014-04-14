package com;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 





import com.mysql.jdbc.Statement;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;

 
public class ActionServlet extends HttpServlet{
 
    private static final long serialVersionUID = 1L;
    public void doPost(HttpServletRequest request, HttpServletResponse response){
        System.out.println("----- InsertCustomerServlet -----");
        try {
        // Get the customer value submitted from Customer.jsp page through HttpServletRequest object
            String name=request.getParameter("name");
            String address=request.getParameter("address");
            String mobile=request.getParameter("mobile");
            String emailid=request.getParameter("emailid");
            int customerid = Integer.parseInt(request.getParameter("customerId"));
            
                
            
            try
            {
             // Load the database driver
            	Class.forName("com.mysql.jdbc.Driver");
                Connection conn = null;
               

             // Get a connection to the database
                conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test","root", "root");            // Print all warnings
             
                for( SQLWarning warn = conn.getWarnings(); warn != null; warn = warn.getNextWarning() )
                {
                 System.out.println( "SQL Warning:" ) ;
                 System.out.println( "State  : " + warn.getSQLState()  ) ;
                 System.out.println( "Message: " + warn.getMessage()   ) ;
                 System.out.println( "Error  : " + warn.getErrorCode() ) ;
                }

             // Get a statement from the connection
             java.sql.Statement stmt = conn.createStatement() ;

             // Execute the query
             int rs = stmt.executeUpdate( "insert into customer_table(name, address,mobile,emailid,customer_id) values(\""+name+"\",\""+address+"\",\""+mobile+"\",\""+emailid+"\","+customerid+")" ) ;

             // Loop through the result set
           //  while( rs.next() )
               System.out.println( rs ) ;

             // Close the result set, statement and the connection
            // rs.close() ;
             stmt.close() ;
             conn.close() ;
            }
         catch( SQLException se )
            {
        	 
             System.out.println( "SQL Exception:" ) ;

             // Loop through the SQL Exceptions
             while( se != null )
                {
                 System.out.println( "State  : " + se.getSQLState()  ) ;
                 System.out.println( "Message: " + se.getMessage()   ) ;
                 System.out.println( "Error  : " + se.getErrorCode() ) ;

                 se = se.getNextException() ;
                
                }
             
             
            }
         catch( Exception e )
            {
             System.out.println( e ) ;
             e.printStackTrace();
            }
        
            
            
            
            
             
            //Set the Customer values into Customer Bean or POJO(Plain Old Java Object) class
            Customer customer=new Customer();
            customer.setName(name);
            customer.setAddress(address);
            customer.setMobile(Long.valueOf(mobile));
            customer.setEmailid(emailid);
             
            RequestDispatcher dispatcher=request.getRequestDispatcher("/Output.jsp");
            //Set the customer instance into request.Then only the customer object 
            //will be available in the Welcome.jsp page
            request.setAttribute("cust",customer);
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
         
    }
 
}