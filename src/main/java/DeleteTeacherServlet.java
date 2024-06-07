import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DeleteTeacherServlet")
public class DeleteTeacherServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    // JDBC URL, username, and password
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/TRMS";
    private static final String JDBC_USERNAME = "root";
    private static final String JDBC_PASSWORD = "tiger";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String uniqueID = request.getParameter("uniqueID");

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // Establish connection to the database
            connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);

            // Query to find the teacher record by uniqueID
            String query = "SELECT * FROM teachers WHERE uniqueID = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, uniqueID);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                // Set attributes to pass to the JSP
                request.setAttribute("uniqueID", uniqueID);
                request.setAttribute("name", resultSet.getString("name"));
                request.setAttribute("qualification", resultSet.getString("qualification"));
                request.setAttribute("dob", resultSet.getString("dob"));
                request.setAttribute("experience", resultSet.getInt("experience"));
                request.setAttribute("subjects", resultSet.getString("subjects"));
                request.setAttribute("address", resultSet.getString("address"));
                request.setAttribute("phone", resultSet.getString("phone"));
                request.setAttribute("email", resultSet.getString("email"));
                request.setAttribute("orgEmail", resultSet.getString("orgEmail"));

                // Forward to the confirmation JSP
                request.getRequestDispatcher("confirmDelete.jsp").forward(request, response);
            } else {
                out.println("<html><body>");
                out.println("<h2>No record found with UniqueID: " + uniqueID + "</h2>");
                out.println("</body></html>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            out.println("An error occurred while processing your request.");
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}