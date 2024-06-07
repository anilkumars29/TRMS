import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/InsertTeacherServlet")
public class InsertTeacherServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    // JDBC URL, username, and password
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/TRMS";
    private static final String JDBC_USERNAME = "root";
    private static final String JDBC_PASSWORD = "tiger";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        String name = request.getParameter("name");
        String qualification = request.getParameter("qualification");
        String dob = request.getParameter("dob");
        int experience = Integer.parseInt(request.getParameter("experience"));
        String subjects = request.getParameter("subjects");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        
        // Generate a random 8-digit UniqueID for the teacher
        Random random = new Random();
        int uniqueID = 10000000 + random.nextInt(90000000); // Ensure it is 8 digits

        // Generate organization's email ID
        String namePart = name.replaceAll("\\s", "").substring(0, Math.min(4, name.length())).toLowerCase();
        String uniqueIDPart = String.valueOf(uniqueID).substring(5); // Get the last 3 digits of uniqueID
        String orgEmail = namePart + uniqueIDPart + "@presidencyuniversity.in";

        try {
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO teachers (name, qualification, dob, experience, subjects, address, phone, email, uniqueID, orgEmail) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
            );
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, qualification);
            preparedStatement.setString(3, dob);
            preparedStatement.setInt(4, experience);
            preparedStatement.setString(5, subjects);
            preparedStatement.setString(6, address);
            preparedStatement.setString(7, phone);
            preparedStatement.setString(8, email);
            preparedStatement.setInt(9, uniqueID);
            preparedStatement.setString(10, orgEmail);
            
            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                // Forward the request to teacherDetails.jsp
                request.setAttribute("name", name);
                request.setAttribute("uniqueID", uniqueID);
                request.setAttribute("orgEmail", orgEmail);
                request.getRequestDispatcher("teacherDetails.jsp").forward(request, response);
            }
            
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            out.println("An error occurred while processing your request.");
        }
    }
}