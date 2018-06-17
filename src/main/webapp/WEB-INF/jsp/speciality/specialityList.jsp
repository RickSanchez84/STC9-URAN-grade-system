<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../../header.jsp" %>
<%@ include file="../../../aside.jsp" %>
<h1>Список специальностей</h1>

<c:if test="${fn:length(specialityList) le 0}">
    <c:out value="Пока что мы не обучаем студентов ни по каким специальностям."/>
</c:if>
<p><b><a href="/speciality/addOrUpdateSpeciality">Добавить новую специальность</a></b></p>
<c:if test="${fn:length(specialityList) gt 0}">
    <%--<c:out value="${fn:length(users.rows)}">--%>
<div class="table-responsive">
    <table class="table table-striped">
        <thead>
        <tr>
            <th>№</th>
            <th>Название специальности</th>
            <th>Редактировать название специальности</th>
            <th>Удалить специальность</th>
        </tr>
        <c:forEach var="speciality" items="${specialityList}" varStatus="сounter">
        <tr>
            <td><c:out value="${сounter.count}"/></td>
            <td><a href="/speciality/speciality?id=${speciality.id}">${speciality.name}</a></td>
            <td><a href="/speciality/updateSpeciality?id=${speciality.id}">редактировать</a></td>
            <td><a href="/speciality/deleteSpeciality?id=${speciality.id}">удалить</a></td>
        </tr>
        </c:forEach>
    </table>
</div>
</c:if>
<%@ include file="../../../footer.jsp" %>