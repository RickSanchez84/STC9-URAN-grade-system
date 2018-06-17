<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../../header.jsp" %>
<%@ include file="../../../aside.jsp" %>

<h1>${speciality.name}</h1>
<p><b>Название специальности:</b> ${speciality.name}</p>
<p>Обучаясь на данной специальности можно освоить следующий объем дисциплин:</p>

<div class="table-responsive">
    <table class="table table-striped">
        <thead>
        <tr>
            <th>№</th>
            <th>Название дисциплины</th>
            <th>Семестр</th>
            <th>Число часов</th>
            <%--<th></th>--%>
        </tr>
        <c:forEach var="program" items="${programs}" varStatus="сounter">
        <tr>
            <td>${сounter.count}</td>
            <td><c:out value="${program.subject.name}"/></td>
            <td><c:out value="${program.semester}"/></td>
            <td><c:out value="${program.hours}"/></td>

            <td><a href="/program/updateProgram?id=${program.id}">редактировать</a></td>
            <td><a href="/program/deleteProgram?programId=${program.id}&id=${speciality.id}">удалить</a></td>
        </tr>
        </c:forEach>
    </table>
    <table class="table table-striped">
        <tr>
            <%--<c:set var="specialtyId" value="${speciality.id}"/>--%>
            <th><a href="/program/addOrUpdateProgram">Добавить новый предмет в учебную программу</a></th>
            <th></th>
            <th><a href="/speciality/specialityAll">назад</a></th>
        </tr>
    </table>
</div>



<%@ include file="../../../footer.jsp" %>