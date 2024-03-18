package servlet;

import db.Clinic;
import db.Nurse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

@WebServlet("/GetNursesOfPhysician")
public class GetNursesOfPhysicianServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public GetNursesOfPhysicianServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        String physicianName = request.getParameter("physicianName");

        try {
            Clinic clinic = new Clinic();
            List<Nurse> nurses = clinic.getNursesOfPhysician(physicianName);

            JSONArray jsonArray = new JSONArray();
            for (Nurse nurse : nurses) {
                jsonArray.put(nurse.toJSONObject());
            }

            JSONObject json = new JSONObject();
            json.put("nurses", jsonArray);

            out.print(json.toString());
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            JSONObject error = new JSONObject();
            error.put("error", e.getMessage());
            out.print(error.toString());
        } finally {
            out.flush();
        }
    }
}
