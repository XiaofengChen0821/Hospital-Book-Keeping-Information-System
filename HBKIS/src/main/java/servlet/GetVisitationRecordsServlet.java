package servlet;

import db.Clinic;
import db.VisitationRecord;
import java.io.IOException;
import java.time.LocalDate;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/getVisitationRecord")
public class GetVisitationRecordsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private Clinic clinic;

    public GetVisitationRecordsServlet() {
        super();
        clinic = new Clinic(); // Initialize Clinic
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String patientName = request.getParameter("patientName");
        String dateStr = request.getParameter("date");

        try {
            LocalDate date = LocalDate.parse(dateStr);
            VisitationRecord record = clinic.getVisitationRecord(patientName, date);

            // Check if the record is null and handle it
            if (record == null) {
                response.setContentType("application/json");
                response.getWriter().write("{\"message\": \"No record found\"}");
                return;
            }

            // Convert the record to JSON
            String jsonOutput = record.toJSONObject().toString();
            response.setContentType("application/json");
            response.getWriter().write(jsonOutput);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Server error occurred");
        }
    }

    // Implement doPost if needed
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
