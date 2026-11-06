<%@ page import="java.util.List" %>
<%@ page import="com.example.demo.Student" %>

<!DOCTYPE html>
<html>
<head>
    <title>Student List</title>
</head>
<body>

    <h2>Student List</h2>

    <table border="1" cellpadding="10">
        <tr>
            <th>Regno</th>
            <th>Name</th>
            <th>CGPA</th>
            <th>Actions</th>
        </tr>

        <%
            List<Student> students =
                (List<Student>) request.getAttribute("students");

            for (Student student : students) {
        %>

        <tr>
            <td><%= student.getRegno() %></td>
            <td><%= student.getName() %></td>
            <td><%= student.getCgpa() %></td>
            <td>
                <a href="/editStudent/<%= student.getRegno() %>">Edit</a>
                <form action="/deleteStudent/<%= student.getRegno() %>" method="post" style="display:inline">
                    <input type="submit" value="Delete" onclick="return confirm('Delete this student?')">
                </form>
            </td>
        </tr>

        <%
            }
        %>
    </table>

    <br>
    <a href="/">Add New Student</a>

</body>
</html>