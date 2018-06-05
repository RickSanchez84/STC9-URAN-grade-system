<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../../header.jsp" %>
<div class="container">
    <main class="content">
        <h1>Список типов пользователей</h1>
        <p><b><a href="/role/addOrUpdateRole">Добавить новый тип пользователя</a></b></p>
        <table border="1">
            <tr>
                <th>№</th>
                <th>Название типа пользователя</th>
                <th></th>
                <th></th>
            </tr>
            <c:forEach var="role" items="${roleList}">
                <tr>
                    <td>${role.id}</td>
                    <td><a href="/role/role?id=${role.id}">${role.name}</a></td>
                    <td><a href="/role/updateRole?id=${role.id}">редактировать</a></td>
                    <td><a href="/role/deleteRole?id=${role.id}">удалить</a></td>
                </tr>
            </c:forEach>
        </table>
    </main><!-- .content -->
</div>
<!-- .container-->
<%@ include file="../../../aside.jsp" %>
<%@ include file="../../../footer.jsp" %>