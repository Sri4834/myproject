package sriexam;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.DriverManager;

@WebServlet("/Povayya")
public class Povayya extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Povayya() {
        super();
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email=request.getParameter("email");
        String pass=request.getParameter("password");
        String name=null, de=null, dp=null;
        
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SYSTEM", "SYSTEM");
            Statement stmt=conn.createStatement();
            ResultSet rs=stmt.executeQuery("SELECT NAME,EMAIL,PASSWORD FROM STUDENTS");
            while(rs.next()) {
                de=rs.getString("email");
                dp=rs.getString("password");
                name=rs.getString("name");
            }
        }
        catch(Exception e)
        {
            System.out.println("Exception came: "+e);
        }
        response.setContentType("text/html");
        PrintWriter pw=response.getWriter();
        if(email!=null && pass!=null && email.toLowerCase().equals(de.toLowerCase()) && pass.toLowerCase().equals(dp.toLowerCase())) 
        {
        	
            pw.println("<html><head><title>Login Success</title>");
            pw.println("<style>");
            pw.println("a { padding: 10px 20px; background-color: #007bff; color: #fff; text-decoration: none; border-radius: 5px; font-weight: bold;}");
            pw.println("a:hover { background-color: #0056b3; }");
            pw.println("table { width:50%; height:70%; margin: 20px auto; font-size: 16px; }");
            pw.println("td { padding: 8px 12px; border: 1px solid #ddd; text-align: left; }");
            pw.println("h1 { text-align: center; color: green; }");
            pw.println("</style></head><body>");
            pw.println("<h1>LOGIN SUCCESSFUL !</h1><br/>");
            pw.println("<table>");
            pw.println("<tr><td>Full Name:</td><td>"+name+"</td></tr>");
            pw.println("<tr><td>Email:</td><td>"+email+"</td></tr>");
            pw.println("<tr><td>Password:</td><td>"+pass+"</td></tr>");
            pw.println("<tr><td> </td><td style='text-align:center'><a href='exam.html'>Proceed to Exams</a></td></tr>");
            pw.println("</table></body></html>");
        }
        else {
            pw.println("<html><head><title>Login Failed</title></head><body>");
            pw.println("<center><h1>Invalid username or password</h1></center>");
            pw.println("<center><p>DE:- "+de+"</p></center>");
            pw.println("<center><a href='login.html'>Try Again</a></center>");
            pw.println("</body></html>");
        }
    }
}
