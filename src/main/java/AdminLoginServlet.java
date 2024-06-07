import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AdminLoginServlet")
public class AdminLoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Check if username and password match the admin credentials
        if (username.equals("admin") && password.equals("admin123")) {
            // Redirect to the page where you manage teacher records
            response.sendRedirect("home.html");
        } else {
            // If login fails, redirect back to the login page with an error message
            response.sendRedirect("adminLogin.html?error=1");
        }
    }
}