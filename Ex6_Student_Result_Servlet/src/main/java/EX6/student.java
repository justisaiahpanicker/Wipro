package EX6;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class student
 */
@WebServlet("/student")
public class student extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public student() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        // 1. Extract form parameters
        String regNo = request.getParameter("regNo");
        String name = request.getParameter("studentName");
        String dept = request.getParameter("department");
        String attStr = request.getParameter("attendance");
        String[] markStrings = request.getParameterValues("marks");

        // Fallback check to prevent crashes on invalid/empty submissions
        if (regNo == null || name == null || dept == null || attStr == null || markStrings == null) {
            out.println("<h3>Error: Missing form parameters. Please submit from the HTML form.</h3>");
            return;
        }

        // 2. Parse attendance & subject marks
        double attendance = 0.0;
        try {
            attendance = Double.parseDouble(attStr);
        } catch (NumberFormatException e) {
            attendance = 0.0;
        }

        int total = 0;
        boolean passedAllSubjects = true;
        int subjectCount = markStrings.length;

        for (String m : markStrings) {
            try {
                int mark = Integer.parseInt(m.trim());
                total += mark;
                if (mark < 40) { // Subject-level passing criterion
                    passedAllSubjects = false;
                }
            } catch (NumberFormatException e) {
                passedAllSubjects = false;
            }
        }

        // 3. Compute Total, Average, Grade, and Status
        double average = (subjectCount > 0) ? (double) total / subjectCount : 0.0;

        String grade;
        if (average >= 90) grade = "A+";
        else if (average >= 80) grade = "A";
        else if (average >= 70) grade = "B";
        else if (average >= 60) grade = "C";
        else if (average >= 50) grade = "D";
        else grade = "F";

        boolean isEligible = attendance >= 75.0;

        boolean overallPass = isEligible && passedAllSubjects && (average >= 50);
        String statusText;
        if (!isEligible) {
            statusText = "Fail (Low Attendance)";
        } else if (!passedAllSubjects) {
            statusText = "Fail (Subject Minimum Not Met)";
        } else if (average < 50) {
            statusText = "Fail (Low Average)";
        } else {
            statusText = "PASS";
        }

        String statusBadgeBg = overallPass ? "#10b981" : "#ef4444";

        // 4. Output Beautified Dynamic HTML Response
        out.println("<!DOCTYPE html>");
        out.println("<html lang='en'>");
        out.println("<head>");
        out.println("    <meta charset='UTF-8'>");
        out.println("    <meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("    <title>Student Marksheet</title>");
        out.println("    <style>");
        out.println("        :root {");
        out.println("            --primary: #4f46e5;");
        out.println("            --bg-gradient: linear-gradient(135deg, #667eea 0%, #764ba2 100%);");
        out.println("            --card-bg: #ffffff;");
        out.println("            --text-main: #1f2937;");
        out.println("            --text-muted: #6b7280;");
        out.println("            --border-color: #e5e7eb;");
        out.println("        }");
        out.println("        * { box-sizing: border-box; margin: 0; padding: 0; font-family: 'Segoe UI', system-ui, -apple-system, sans-serif; }");
        out.println("        body { background: var(--bg-gradient); min-height: 100vh; display: flex; justify-content: center; align-items: center; padding: 2rem 1rem; }");
        out.println("        .card { background: var(--card-bg); width: 100%; max-width: 520px; padding: 2.5rem; border-radius: 16px; box-shadow: 0 20px 25px -5px rgba(0, 0, 0, 0.15); }");
        out.println("        .card-header { text-align: center; margin-bottom: 1.5rem; }");
        out.println("        .card-header h2 { font-size: 1.75rem; color: var(--text-main); font-weight: 700; }");
        out.println("        .card-header p { color: var(--text-muted); font-size: 0.9rem; margin-top: 0.35rem; }");
        out.println("        .result-table { width: 100%; border-collapse: collapse; margin: 1.5rem 0; }");
        out.println("        .result-table th, .result-table td { padding: 0.85rem 1rem; text-align: left; border-bottom: 1px solid var(--border-color); font-size: 0.95rem; }");
        out.println("        .result-table th { background-color: #f9fafb; color: var(--text-muted); font-weight: 600; text-transform: uppercase; font-size: 0.75rem; letter-spacing: 0.05em; }");
        out.println("        .result-table td { color: var(--text-main); font-weight: 500; }");
        out.println("        .badge { display: inline-block; padding: 0.35rem 0.8rem; border-radius: 20px; color: #ffffff; font-weight: 700; font-size: 0.8rem; letter-spacing: 0.03em; }");
        out.println("        .btn { display: block; width: 100%; text-align: center; padding: 0.85rem; background-color: var(--primary); color: #ffffff; text-decoration: none; font-weight: 600; border-radius: 8px; margin-top: 1.5rem; transition: background-color 0.2s ease; }");
        out.println("        .btn:hover { background-color: #4338ca; }");
        out.println("    </style>");
        out.println("</head>");
        out.println("<body>");
        out.println("    <div class='card'>");
        out.println("        <div class='card-header'>");
        out.println("            <h2>Examination Result</h2>");
        out.println("            <p>Generated Official Report Card</p>");
        out.println("        </div>");
        out.println("        <table class='result-table'>");
        out.println("            <tr><th>Field</th><th>Details</th></tr>");
        out.println("            <tr><td>Register Number</td><td>" + regNo + "</td></tr>");
        out.println("            <tr><td>Student Name</td><td>" + name + "</td></tr>");
        out.println("            <tr><td>Department</td><td>" + dept + "</td></tr>");
        out.println("            <tr><td>Attendance</td><td>" + String.format("%.2f", attendance) + "% (" + (isEligible ? "<span style='color:#10b981; font-weight:600;'>Eligible</span>" : "<span style='color:#ef4444; font-weight:600;'>Ineligible</span>") + ")</td></tr>");
        out.println("            <tr><td>Total Marks</td><td>" + total + " / " + (subjectCount * 100) + "</td></tr>");
        out.println("            <tr><td>Average Marks</td><td>" + String.format("%.2f", average) + "%</td></tr>");
        out.println("            <tr><td>Grade</td><td><b>" + grade + "</b></td></tr>");
        out.println("            <tr><td>Final Status</td><td><span class='badge' style='background-color:" + statusBadgeBg + ";'>" + statusText + "</span></td></tr>");
        out.println("        </table>");
        out.println("        <a href='index.html' class='btn'>Calculate Another Result</a>");
        out.println("    </div>");
        out.println("</body>");
        out.println("</html>");
    }
}