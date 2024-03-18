package servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import db.Clinic;
import db.User;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String userID = request.getParameter("userID"); // For 2FA

        Clinic clinic = new Clinic();

        if (password != null) {
            // Handle initial login
            User user = clinic.login(username, password);
            if (user != null) {
                out.write(user.toJSONObject().toString());
            } else {
                out.write(new JSONObject().put("status", "failed").toString());
            }
        } else if (userID != null) {
            // Handle 2FA
            boolean is2FASuccess = clinic.performSecondFactorAuthentication(username, Integer.parseInt(userID));
            if (is2FASuccess) {
                out.write(new JSONObject().put("status", "success").toString());
            } else {
                out.write(new JSONObject().put("status", "failed").toString());
            }
        } else {
            // Invalid request
            out.write(new JSONObject().put("status", "invalid request").toString());
        }
    }
}
