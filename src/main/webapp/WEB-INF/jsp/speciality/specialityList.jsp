<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../../header.jsp" %>
<%@ include file="../../../aside.jsp" %>
<h1>Список специальностей</h1>
<p><b><a href="/speciality/addOrUpdateSpeciality">Добавить новую специальность</a></b></p>
<div class="table-responsive">
    <table class="table table-striped">
        <thead>
        <tr>
            <th>№</th>
            <th>Название специальности</th>
            <th></th>
            <th></th>
        </tr>
        <c:forEach var="speciality" items="${specialityList}">
        <tr>
            <td>${speciality.id}</td>
            <td><a href="/speciality/speciality?id=${speciality.id}">${speciality.name}</a></td>
            <td><a href="/speciality/updateSpeciality?id=${speciality.id}">редактировать</a></td>
            <td><a href="/speciality/deleteSpeciality?id=${speciality.id}">удалить</a></td>
        </tr>
        </c:forEach>
    </table>
</div>
<%@ include file="../../../footer.jsp" %>