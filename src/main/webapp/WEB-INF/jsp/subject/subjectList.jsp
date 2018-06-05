<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../../header.jsp" %>
<div class="container">
    <main class="content">
        <h1>Список предметов</h1>
        <p><b><a href="/person/addOrUpdate">Добавить новый предмет</a></b></p>
        <table border="1">
            <tr>
                <th>№</th>
                <th>Название предмета</th>
                <th></th>
                <th></th>
            </tr>
            <c:forEach var="subject" items="${subjectList}">
                <tr>
                    <td>${person.id}</td>
                    <td><a href="/person/person?id=${subject.id}">${subject.name}</a></td>
                    <td><a href="/person/updatePerson?id=${person.id}">редактировать</a></td>
                    <td><a href="/person/deletePerson?id=${person.id}">удалить</a></td>
                </tr>
            </c:forEach>
        </table>
    </main><!-- .content -->
</div>
<!-- .container-->
<%@ include file="../../../aside.jsp" %>
<%@ include file="../../../footer.jsp" %>