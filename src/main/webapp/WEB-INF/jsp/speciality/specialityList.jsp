<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../../header.jsp" %>
<div class="container">
    <main class="content">
        <h1>Список специальностей</h1>
        <p><b><a href="/speciality/addOrUpdateSpeciality">Добавить новую специальность</a></b></p>
        <table border="1">
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
    </main><!-- .content -->
</div>
<!-- .container-->
<%@ include file="../../../aside.jsp" %>
<%@ include file="../../../footer.jsp" %>