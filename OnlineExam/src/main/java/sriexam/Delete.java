package sriexam;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

@WebServlet("/Delete")
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Delete() {
        super();
        System.out.println("Hi! nenu Delete Class Constructor ni!");
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        pw.println("<html><head><title>DELETION PAGE</title><style>button{background-color:#1976D2;color:#fff;border:none;border-radius:5px;padding:10px 20px;cursor:pointer;}button:hover{background-color:#1565C0;}</style></head><body>");
        pw.println("<h1>Welcome to Delete Operation Page</h1>");
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SYSTEM", "SYSTEM");
                        
            Statement stmt = con.createStatement();
            stmt.execute("DROP TABLE STUDENTS");
            pw.println("<h2 style='font-size=24px'><b>TABLE STUDENTS DELETED SUCCESSFULLY !.</b></h2>");
            pw.println("<button onclick=\"history.back()\">GO BACK TO PREVIOUS PAGE</button>");
            pw.println("</body></html>");
            
        } catch(Exception e){
        	System.out.print("<p style='color:red;font-size=24px'><br><b>Exception Occurred is: "+e+"</b></p>");
        }
	}

}
