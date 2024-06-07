import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DeleteConfirmedServlet")
public class DeleteConfirmedServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

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

        try {
            connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);

            String query = "DELETE FROM teachers WHERE uniqueID = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, uniqueID);

            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted > 0) {
                request.setAttribute("uniqueID", uniqueID);
                request.getRequestDispatcher("deletionSuccess.jsp").forward(request, response);
            } else {
                out.println("<html><body>");
                out.println("<h2>No record found with UniqueID: " + uniqueID + "</h2>");
                out.println("</body></html>");
            }
        } catch (SQLException e) {
            // Handle any SQL exceptions
            e.printStackTrace();
        } finally {
            // Close the resources in finally block to ensure they are always closed
            try {
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