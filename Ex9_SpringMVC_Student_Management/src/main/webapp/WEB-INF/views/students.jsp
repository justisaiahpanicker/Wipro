<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Student Management</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 30px; }
        table { border-collapse: collapse; width: 60%; margin-top: 15px; }
        th, td { border: 1px solid #999; padding: 8px 12px; text-align: left; }
        th { background: #eee; }
        .msg { color: green; } .err { color: red; }
        nav a { margin-right: 15px; }
        form.inline { display: inline; }
    </style>
</head>
<body>
    <h2>Student Management</h2>
    <nav>
        <a href="${pageContext.request.contextPath}/students">All Students</a>
        <a href="${pageContext.request.contextPath}/students/new">Add Student</a>
        <a href="${pageContext.request.contextPath}/students/search">Search by ID</a>
    </nav>

    <c:if test="${not empty message}"><p class="msg">${message}</p></c:if>
    <c:if test="${not empty error}"><p class="err">${error}</p></c:if>

    <table>
        <tr><th>Reg No</th><th>Name</th><th>CGPA</th><th>Actions</th></tr>
        <c:forEach var="s" items="${students}">
            <tr>
                <td>${s.regno}</td>
                <td><c:out value="${s.name}"/></td>
                <td>${s.cgpa}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/students/edit/${s.regno}">Edit</a>
                    <form class="inline" method="post"
                          action="${pageContext.request.contextPath}/students/delete"
                          onsubmit="return confirm('Delete student ${s.regno}?');">
                        <input type="hidden" name="regno" value="${s.regno}"/>
                        <button type="submit">Delete</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        <c:if test="${empty students}">
            <tr><td colspan="4">No students found.</td></tr>
        </c:if>
    </table>
</body>
</html>
