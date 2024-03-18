package servlet;

import db.Clinic;
import db.LabTest;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@WebServlet("/GetLabTest")
public class GetLabTestServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public GetLabTestServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        String patientName = request.getParameter("patientName");
        String labTestType = request.getParameter("labTestType");
        String dateStr = request.getParameter("date");

        try {
            Clinic clinic = new Clinic();

            if (labTestType != null && !labTestType.isEmpty() && dateStr != null && !dateStr.isEmpty()) {
                LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                LabTest labTest = clinic.getOneParticularLabTest(patientName, date, labTestType);
                if (labTest != null) {
                    JSONObject json = new JSONObject();
                    json.put("labTest", labTest.toJSONObject());
                    out.print(json.toString());
                } else {
                    out.print(new JSONObject().put("message", "No specific lab test found.").toString());
                }
            } else if (labTestType != null && !labTestType.isEmpty()) {
                List<LabTest> labTests = clinic.getPatientsParticularLabTests(patientName, labTestType);
                JSONArray jsonArray = new JSONArray();
                for (LabTest labTest : labTests) {
                    jsonArray.put(labTest.toJSONObject());
                }
                out.print(jsonArray.toString());
            } else {
                List<LabTest> labTests = clinic.getPatientsAllLabTests(patientName);
                JSONArray jsonArray = new JSONArray();
                for (LabTest labTest : labTests) {
                    jsonArray.put(labTest.toJSONObject());
                }
                out.print(jsonArray.toString());
            }

        } catch (DateTimeParseException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.print(new JSONObject().put("error", "Invalid date format").toString());
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.print(new JSONObject().put("error", e.getMessage()).toString());
        } finally {
            out.flush();
        }
    }
}
