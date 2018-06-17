<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../../header.jsp" %>
<%@ include file="../../../aside.jsp" %>
<form class="editForm" action="/speciality/speciality" method="post" name="editForm">

    <input type="hidden" name="id" value="${speciality.id}">
    <h3>Добавить новый предмет в программу специальности</h3>
    <h2><c:out value="speciality.name"/></h2>

    <ul>
        <li>
            <label>Название:</label>
            <input type="text" placeholder="Специальность" required
                   value="<c:if test="${action=='update'}">${speciality.name}</c:if>" name="name"/>
        </li>

        <li>
            <label for="semesterCount">Кол-во семестров:</label>
            <input type="text" placeholder="семестры" required
                   value="<c:if test="${action=='update'}">${speciality.semesterCount}</c:if>" name="semesterCount"/>
        </li>

        <li>
            <button class="submit" type="submit">OK</button>
        </li>
    </ul>
</form>
<%@ include file="../../../footer.jsp" %>