<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../../header.jsp" %>
<%@ include file="../../../aside.jsp" %>
<h1>Список типов пользователей</h1>
<p><b><a href="/role/addOrUpdateRole">Добавить тип пользователя</a></b></p>
<div class="table-responsive">
    <table class="table table-striped">
        <thead>
        <tr>
            <th>№</th>
            <th>Тип пользователя</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="role" items="${roleList}">
            <tr>
                <td>${role.id}</td>
                <td><a href="/role/role?id=${role.id}">${role.name}</a></td>
                <td><a href="/role/updateRole?id=${role.id}">редактировать</a></td>
                <td><a href="/role/deleteRole?id=${role.id}">удалить</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<!-- .container-->

<%@ include file="../../../footer.jsp" %>