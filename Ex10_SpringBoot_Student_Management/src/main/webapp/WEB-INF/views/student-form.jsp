<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Student Registration</title>
</head>
<body>
    <h2>Student Registration Form</h2>

    <form action="/saveStudent" method="post">
        <label>Student Regno:</label>
        <input type="number" name="regno">
        <br><br>

        <label>Name:</label>
        <input type="text" name="name">
        <br><br>

        <label>CGPA:</label>
        <input type="number" step="any" name="cgpa">
        <br><br>

        <input type="submit" value="Submit">
    </form>
</body>
</html>