<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<body>

<h2>All Users</h2>
<br>

<table>
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Surname</th>
        <th>Department</th>
        <th>Salary</th>
        <th>Operations</th>
    </tr>

    <c:forEach var="users" items="${allUsers}">

        <c:url var="updateButton" value="/updateInfo">
            <c:param name="userId" value="${users.id}"/>
        </c:url>


        <c:url var="deleteButton" value="/deleteUser">
            <c:param name="userId" value="${users.id}"/>
        </c:url>

        <tr>
            <td>${users.id}</td>
            <td>${users.name}</td>
            <td>${users.surname}</td>
            <td>${users.department}</td>
            <td>${users.salary}</td>
                <%--            Кнопка для Update--%>
            <td>

                <input type="button" value="Update"
                       onclick="window.location.href = '${updateButton}'"/>


                    <%--            Кнопка для Delete--%>
                <input type="button" value="Delete"
                       onclick="window.location.href = '${deleteButton}'"/>

            </td>
        </tr>
    </c:forEach>
</table>

<br>

<
<%--               Кнопка для Add--%>
<input type="button" value="Add"
       onclick="window.location.href = 'addNewUser'"/>


</body>
</html>