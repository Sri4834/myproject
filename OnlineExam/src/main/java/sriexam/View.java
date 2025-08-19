package sriexam;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.Statement;

@WebServlet("/View")
public class View extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public View() {
        super();
        System.out.println("Hi! nenu View Class Constructor ni!");
    }

    protected void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
    		response.setContentType("text/html");
    		PrintWriter pw=response.getWriter();
    	
    		pw.println("<html><head><title>VIEWING PAGE</title><style>table{width:60%; height:300px;}tr,td,th{padding:8px;font-weight:bold;} td{color:green;}button{background-color:#1976D2;color:#fff;border:none;border-radius:5px;padding:10px 20px;cursor:pointer;}button:hover{background-color:#1565C0;}</style></head><body>");
    		pw.println("<center><h1>Welcome to Viewers Page</h1></center>");

    		try
    		{
    			Class.forName("oracle.jdbc.driver.OracleDriver");
    			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","SYSTEM","SYSTEM");
    			Statement stmt=con.createStatement();
    			ResultSet rs=stmt.executeQuery("SELECT * FROM STUDENTS");
    		
    			pw.println("<center><h2 style='font-size=24px'><b>Data Retrieved Successfully From STUDENTS table.</b></h2>");
    			pw.println("<center> <table border='1'>");
    			pw.println("<tr><th>Name</th><th>Email</th><th>Student ID</th><th>Phone</th><th>Year</th><th>Branch</th><th>Password</th></tr>");
    		
    			while(rs.next())
    			{
    				pw.println("<tr><td>"+rs.getString("name")+"</td><td>"+rs.getString("email")+"</td><td>"+rs.getString("Student_id")+"</td><td>"+rs.getString("phone")+"</td><td>"+rs.getString("year")+"</td><td>"+rs.getString("branch")+"</td><td>"+rs.getString("password")+"</td></tr>");
    			}
    			pw.println("</table></center><br/>");
    			pw.println("<button onclick=\"history.back()\">GO BACK TO PREVIOUS PAGE</button>");
    			pw.println("</center></body></html>");
    		
    		}
    		catch(Exception e)
    		{
    			System.out.print("Exception Occurred is:"+e);
    		}
    }
    
}
