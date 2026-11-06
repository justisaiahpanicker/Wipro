<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>${editMode ? 'Update Student' : 'Add Student'}</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 30px; }
        td { padding: 6px 10px; }
        .err { color: red; }
    </style>
</head>
<body>
    <h2>${editMode ? 'Update Student' : 'Add New Student'}</h2>
    <c:if test="${not empty error}"><p class="err">${error}</p></c:if>

    <form method="post"
          action="${pageContext.request.contextPath}${editMode ? '/students/update' : '/students/save'}">
        <table>
            <tr>
                <td>Reg No</td>
                <td>
                    <%-- Reg no is the primary key, so it can't be changed while updating --%>
                    <input type="number" name="regno" value="${(editMode or not empty error) ? student.regno : ''}" required
                           ${editMode ? 'readonly' : ''}/>
                </td>
            </tr>
            <tr>
                <td>Name</td>
                <td><input type="text" name="name" value="<c:out value='${student.name}'/>" required/></td>
            </tr>
            <tr>
                <td>CGPA</td>
                <td><input type="number" step="0.01" min="0" max="10" name="cgpa" value="${(editMode or not empty error) ? student.cgpa : ''}" required/></td>
            </tr>
            <tr>
                <td></td>
                <td>
                    <button type="submit">${editMode ? 'Update' : 'Save'}</button>
                    <a href="${pageContext.request.contextPath}/students">Cancel</a>
                </td>
            </tr>
        </table>
    </form>
</body>
</html>
