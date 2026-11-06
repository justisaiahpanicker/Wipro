<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.example.demo.Student" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Student</title>
</head>
<body>
    <h2>Edit Student</h2>

    <%
        Student student = (Student) request.getAttribute("student");
    %>

    <form action="/updateStudent" method="post">
        <label>Student Regno:</label>
        <input type="number" name="regno" value="<%= student.getRegno() %>" readonly>
        <br><br>

        <label>Name:</label>
        <input type="text" name="name" value="<%= student.getName() %>">
        <br><br>

        <label>CGPA:</label>
        <input type="number" step="any" name="cgpa" value="<%= student.getCgpa() %>">
        <br><br>

        <input type="submit" value="Update">
    </form>

    <br>
    <a href="/students">Back to Student List</a>
</body>
</html>
