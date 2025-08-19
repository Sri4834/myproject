package sriexam;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.Statement;

@WebServlet("/Update")
public class Update extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Update() {
        super();
        System.out.println("Hi! nenu View Class Constructor ni!");
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
		String name=request.getParameter("user_name");
		String mail=request.getParameter("email");
		String sid=request.getParameter("sid");
		String phone=request.getParameter("phone");
		String year=request.getParameter("year");
		String branch=request.getParameter("branch");
		String password=request.getParameter("password");
		
		response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        
        try 
        {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SYSTEM", "SYSTEM");
                        
            Statement stmt=con.createStatement();
            
            stmt.executeUpdate("UPDATE STUDENTS SET name='"+name+"', email='"+mail+"', Student_id='"+sid+"', phone='"+phone+"', year='"+year+"', branch='"+branch+"', password='"+password+"' WHERE Student_id='503'");
//            pw.println("UPDATE STUDENTS SET name='"+name+"', email='"+mail+"', Student_id='"+sid+"', phone='"+phone+"', year='"+year+"', branch='"+branch+"', password='"+password+"' WHERE Student_id=503");
            stmt.execute("COMMIT");  
            pw.println("UPDATED STUDENTS Table Successfully!");
            
        } 
        catch(Exception e) 
        {
        	    pw.println("Exception IS : "+e.toString().toUpperCase());
        }
        
        pw.println("<html><head><title>UPDATE PAGE</title><style>button{background-color:#1976D2;color:#fff;border:none;border-radius:5px;padding:10px 20px;cursor:pointer;}button:hover{background-color:#1565C0;}</style></head><body>");
        pw.println("<h1>Welcome to Updations Page</h1>");
        
        pw.println("<h2 style='color:green;'><b>Data Updated Successfully to STUDENTS table.</h2></p>");
        pw.println("<button onclick='history.back()'>GO BACK TO PREVIOUS PAGE</button>");
	}

}
