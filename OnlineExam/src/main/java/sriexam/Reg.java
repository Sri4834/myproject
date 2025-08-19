package sriexam;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/Reg")
public class Reg extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Reg() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name=request.getParameter("user_name");
        String email=request.getParameter("email");
        String sid=request.getParameter("sid");
        String phone="+91"+request.getParameter("phone");
        String year=request.getParameter("year");
        String branch=request.getParameter("branch");
        String password=request.getParameter("password");
        
        response.setContentType("text/html");
        PrintWriter pw=response.getWriter();
        pw.println("<!DOCTYPE html><html><head><title>REGISTRATION</title><style>button{background-color:#1976D2;color:#fff;border:none;border-radius:5px;padding:10px 20px;cursor:pointer;}button:hover{background-color:#1565C0;}</style></head><body>");
        pw.println("<button onclick='history.back()'>GO TO REGISTRATION PAGE</button>");
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SYSTEM", "SYSTEM");
                        
            Statement stmt=con.createStatement();

                stmt.execute("CREATE TABLE STUDENTS (name VARCHAR2(50), email VARCHAR2(50), Student_id VARCHAR2(20), phone VARCHAR2(20), year VARCHAR2(10), branch VARCHAR2(30), password VARCHAR2(50))");
                pw.println("<p><b>Table created successfully.</b></p>");
//            catch(Exception ex) {
//                pw.println("Table already there!");
//            }
            stmt.executeUpdate("INSERT INTO STUDENTS(name,email,Student_id,phone,year,branch,password) VALUES('"+name+"','"+email+"','"+sid+"','"+phone+"','"+year+"','"+branch+"','"+password+"')");
        } 
        catch (Exception e) 
        {
            pw.println("<p style='color:red; border: 2px solid black;'>Error: " + e + "</p>");
        }
        
        pw.println("<p>Data inserted into STUDENTS TABLE SUCCESSFULLY.</p>");
        pw.println("<form action=View method='post'>");
        pw.println("<input type='submit' value='VIEW TABLE'>");
        pw.println("</form>");
        pw.println("<form action='Delete' method='post'>");
        pw.println("<input type='submit' value='DELETE TABLE'>");
        pw.println("</form><br>");
        pw.println("<h4><b>Please Fill below details for Update Operation !</b></h4>");
        pw.println("<form action='Update' method='post'>");
        pw.println("<label>Username:</label><input name=user_name type=text required><br>");
        pw.println("<label>Email:</label>");
        pw.println("<input name=email type=email><br>");
        pw.println("<label>Student ID:</label>");
        pw.println("<input name=sid type=text required><br>");
        pw.println("<label>Year of Study:</label><br>");
        pw.println("<select name=year>");
        pw.println("<option value=''>SELECT</option>");
        pw.println("<option value=1>1st Year</option>");
        pw.println("<option value=2>2nd Year</option>");
        pw.println("<option value=3>3rd Year</option>");
        pw.println("<option value=4>4th Year</option></select><br>");
        
        pw.println("<label>Contact No:</label>");
        pw.println("<input name=phone type=tel><br>");
        pw.println("<label>Branch:</label>");
        pw.println("<select name=branch required>");
        pw.println("<option value=''>SELECT</option>");
        pw.println("<option value=CSE>CSE</option>");
        pw.println("<option value=CSD>DSD</option>");
        pw.println("<option value=ECE>ECE</option>");
        pw.println("<option value=EEE>EEE</option>");
        pw.println("<option value=MECH>MECH</option>");
        pw.println("</select><br><label>Password:</label>");
        pw.println("<input name=password type=password/>");
        
        pw.println("<input type='submit' value='UPDATE TABLE'>");
        pw.println("</form>");
        
        pw.println("</body></html>");
    }
}
