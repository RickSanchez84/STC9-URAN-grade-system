<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../../header.jsp" %>
<%@ include file="../../../aside.jsp" %>
<h1>Добавление группы</h1>
<p><b><a href="/group/addGroup">Добавить новую группу</a></b></p>
<div class="table-responsive">
    <table class="table table-striped">
        <thead>
        <tr>
            <th>№ ID</th>
            <th>Название программы на группу</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="group" items="${groupList}">
            <tr>
                <td>${groupList.id}</td>
                <td><a href="/group/group?id=${groupList.id}">${groupList.program}</a></td>
                <td><a href="/group/updateGroup?id=${groupList.id}">редактировать</a></td>
                <td><a href="/group/deleteGroup?id=${groupList.id}">удалить</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<%@ include file="../../../footer.jsp" %>