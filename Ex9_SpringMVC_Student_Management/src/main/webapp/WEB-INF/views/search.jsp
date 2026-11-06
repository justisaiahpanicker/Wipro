<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Search Student</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 30px; }
        table { border-collapse: collapse; margin-top: 15px; }
        th, td { border: 1px solid #999; padding: 8px 12px; text-align: left; }
        th { background: #eee; }
        .err { color: red; }
        form.inline { display: inline; }
    </style>
</head>
<body>
    <h2>Search Student by ID</h2>
    <form method="get" action="${pageContext.request.contextPath}/students/search">
        Reg No: <input type="number" name="regno" value="${regno}" required/>
        <button type="submit">Search</button>
        <a href="${pageContext.request.contextPath}/students">Back</a>
    </form>

    <c:if test="${searched}">
        <c:choose>
            <c:when test="${not empty student}">
                <table>
                    <tr><th>Reg No</th><th>Name</th><th>CGPA</th><th>Actions</th></tr>
                    <tr>
                        <td>${student.regno}</td>
                        <td><c:out value="${student.name}"/></td>
                        <td>${student.cgpa}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/students/edit/${student.regno}">Edit</a>
                            <form class="inline" method="post"
                                  action="${pageContext.request.contextPath}/students/delete"
                                  onsubmit="return confirm('Delete student ${student.regno}?');">
                                <input type="hidden" name="regno" value="${student.regno}"/>
                                <button type="submit">Delete</button>
                            </form>
                        </td>
                    </tr>
                </table>
            </c:when>
            <c:otherwise>
                <p class="err">No student found with register number ${regno}.</p>
            </c:otherwise>
        </c:choose>
    </c:if>
</body>
</html>
